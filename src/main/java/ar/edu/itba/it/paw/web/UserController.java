package ar.edu.itba.it.paw.web;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.command.EditForm;
import ar.edu.itba.it.paw.web.command.LoginForm;
import ar.edu.itba.it.paw.web.command.RegisterForm;
import ar.edu.itba.it.paw.web.command.validator.EditFormValidator;
import ar.edu.itba.it.paw.web.command.validator.LoginFormValidator;
import ar.edu.itba.it.paw.web.command.validator.RegisterFormValidator;

@Controller
public class UserController extends BaseController {

	private UserRepo userRepo;
	private EditFormValidator eValidator;
	private RegisterFormValidator rValidator;
	private LoginFormValidator lValidator;

	@Autowired
	public UserController(UserRepo userRepo, RegisterFormValidator rValidator,
			LoginFormValidator lValidator, EditFormValidator eValidator) {
		this.userRepo = userRepo;
		this.rValidator = rValidator;
		this.lValidator = lValidator;
		this.eValidator = eValidator;

	}

	@RequestMapping(method = RequestMethod.GET)
	public EnhancedModelAndView login(HttpSession session) {
		if (isLoggedIn(session))
			return indexContext();
		EnhancedModelAndView mav = generateContext("Login/Register", false,
				false, "user/login");
		mav.addObject(new RegisterForm());
		mav.addObject(new LoginForm());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public EnhancedModelAndView login(LoginForm loginForm, Errors errors,
			HttpSession session) {
		lValidator.validate(loginForm, errors);
		if (errors.hasErrors()) {
			return login(session);
		}
		User user = userRepo.login(loginForm.getUsername(),
				loginForm.getPassword());
		if (user != null) {
			setLoggedInUser(session, user);
			return indexContext();
		} else {
			errors.rejectValue("username", "mismatch");
			return login(session);

		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public EnhancedModelAndView register(HttpSession session) {
		return login(session);
	}

	@RequestMapping(value = { "/", "/user", "/user/list" })
	public EnhancedModelAndView list(HttpSession session) {
		if (!isLoggedIn(session) || !getLoggedInUser(session).isAdmin())
			return indexContext();
		EnhancedModelAndView mav = generateContext("User List", true, true);
		mav.addObject("userList", userRepo.getAll());

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public EnhancedModelAndView register(RegisterForm registerForm,
			Errors errors, HttpSession session) {
		if (isLoggedIn(session))
			return indexContext();
		rValidator.validate(registerForm, errors);
		if (errors.hasErrors()) {
			return login(session);
		}

		try {
			User s = registerForm.build();
			userRepo.save(s);
			setLoggedInUser(session, s);

		} catch (Exception e) {
			errors.rejectValue("SQL CODE", "DB ERROR");
			return login(session);
		}
		return indexContext();

	}

	@RequestMapping(method = RequestMethod.GET)
	public EnhancedModelAndView edit(HttpSession session) {
		if (!isLoggedIn(session))
			return indexContext();
		EnhancedModelAndView mav = generateContext("Login/Register", false,
				false);
		mav.addObject(new EditForm(getLoggedInUser(session), "", ""));
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public EnhancedModelAndView edit(EditForm editForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session))
			return indexContext();
		eValidator.validate(editForm, errors);
		if (errors.hasErrors()) {
			return edit(session);
		}

		try {
			User s = editForm.build(userRepo);
			userRepo.save(s);
			setLoggedInUser(session, s);
		} catch (Exception e) {
			e.printStackTrace();
			return login(session);
		}
		return indexContext();

	}

	@RequestMapping(method = RequestMethod.GET)
	public EnhancedModelAndView logout(HttpSession session) {
		logoutUser(session);
		return indexContext();
	}

	@RequestMapping(method = RequestMethod.GET)
	public EnhancedModelAndView profile(HttpSession session,
			@RequestParam(value = "userId", required = true) User u) {

		EnhancedModelAndView mav = generateContext(
				u.getName() + " " + u.getSurname(), true, true, "user/profile");
		if (isLoggedIn(session))
			mav.addObject("notMe", !getLoggedInUser(session).equals(u));
		else
			mav.addObject("notMe", false);
		mav.addObject("profileUser", u);
		System.out.println(u.getType());

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView administrate(@RequestParam("action") String action,
			@RequestParam("id") User u, HttpSession session) {
		if (action.equals("setadmin")) {
			u.setType("Admin");
		} else if (action.equals("unsetadmin")) {
			u.setType("Normal");
		}
		userRepo.save(u);

		return profile(session, u);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView likecomment(
			@RequestParam(value = "userId", required = true) User u,
			@RequestParam(value = "ratingId", required = true) Rating r,
			HttpSession session) {
		if (!isLoggedIn(session))
			return indexContext();
		Set<Rating> rr = u.getComments();
		for (Rating rating : rr) {
			if (rating.equals(r))
				if (!rating.getLikes().contains(getLoggedInUser(session))) {
					rating.addLikes(getLoggedInUser(session));
					userRepo.save(u);
					break;
				}
		}
		System.out.println("HOLAAAAAAAAAAAAA");
		return indexContext();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView unlikecomment(
			@RequestParam(value = "userId", required = true) User u,
			@RequestParam(value = "ratingId", required = true) Rating r,
			HttpSession session) {
		if (!isLoggedIn(session))
			return indexContext();

		Set<Rating> rr = u.getComments();
		for (Rating rating : rr) {
			if (rating.equals(r))
				if (!rating.getUnlikes().contains(getLoggedInUser(session))) {
					rating.addunLikes(getLoggedInUser(session));
					userRepo.save(u);
					break;
				}
		}

		System.out.println("HOLAAAAAAAAAAAAA");

		return indexContext();

	}
}
