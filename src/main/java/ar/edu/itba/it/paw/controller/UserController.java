package ar.edu.itba.it.paw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.FoodTypeService;
import ar.edu.itba.it.paw.service.RestaurantService;
import ar.edu.itba.it.paw.service.UserService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.command.RegisterForm;
import ar.edu.itba.it.paw.web.command.validator.RegisterFormValidator;
import org.springframework.validation.Errors;

@Controller
public class UserController {

	private UserService uService;
	private RegisterFormValidator rvalidator;

	@Autowired
	public UserController(UserService usersrv, RegisterFormValidator rvalidator) {
		this.uService = usersrv;
		this.rvalidator = rvalidator;

	}

	@RequestMapping
	public EnhancedModelAndView login(RegisterForm registerForm, Errors errors) {
		EnhancedModelAndView mav = new EnhancedModelAndView("login");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(RegisterForm registerForm, Errors errors) {
		rvalidator.validate(registerForm, errors);
		if (errors.hasErrors()) {
			return null;
		}

		try {
			User s = registerForm.build(); // TODO Hay que cambiar todo para que
											// use converters
			uService.register(s.getName(), s.getSurname(), s.getEmail(),
					s.getUsername(), s.getPassword());
		} catch (Exception e) {
			errors.rejectValue("SQL CODE", "DB ERROR");
			return null;
		}
		return "redirect:list";

	}

	@RequestMapping
	public ModelAndView error() {
		return null;
	}

}
