package ar.edu.itba.it.paw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.UserService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.command.LoginForm;
import ar.edu.itba.it.paw.web.command.RegisterForm;
import ar.edu.itba.it.paw.web.command.validator.LoginFormValidator;
import ar.edu.itba.it.paw.web.command.validator.RegisterFormValidator;

@Controller
public class UserController extends BaseController {

	private UserService userService;
	private RegisterFormValidator rValidator;
	private LoginFormValidator lValidator;

	@Autowired
	public UserController(UserService usersrv,
			RegisterFormValidator rValidator, LoginFormValidator lValidator) {
		this.userService = usersrv;
		this.rValidator = rValidator;
		this.lValidator = lValidator;

	}

	@RequestMapping(method = RequestMethod.GET)
	public EnhancedModelAndView login(HttpSession session) {
		if (isLoggedIn(session))
			return indexContext();
		EnhancedModelAndView mav = generateContext("Login/Register", false,
				"user/login");
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
		User user = userService.login(loginForm.getUsername(), loginForm.getPassword());
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
			User s = registerForm.build(); // TODO Hay que cambiar todo para que
											// use converters
			s = userService.register(s.getName(), s.getSurname(), s.getEmail(),
					s.getUsername(), s.getPassword(), s.getAvatar());
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
		EnhancedModelAndView mav = generateContext("Login/Register", false);
		mav.addObject(new RegisterForm(getLoggedInUser(session), "", ""));
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public EnhancedModelAndView edit(RegisterForm registerForm,
			Errors errors, HttpSession session) {
		if (!isLoggedIn(session))
			return indexContext();
		rValidator.validate(registerForm, errors);
		if (errors.hasErrors()) {
			return edit(session);
		}

		try {
			User s = registerForm.build(); // TODO Hay que cambiar todo para que
											// use converters
			userService.register(s.getName(), s.getSurname(), s.getEmail(),
					s.getUsername(), s.getPassword());
		} catch (Exception e) {
			errors.rejectValue("SQL CODE", "DB ERROR");
			return login(session);
		}
		return indexContext();

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public EnhancedModelAndView logout(HttpSession session){
		logoutUser(session);
		return indexContext();
	}

}
