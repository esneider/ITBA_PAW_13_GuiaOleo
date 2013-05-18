package ar.edu.itba.it.paw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.UserService;

@Controller
public abstract class BaseController {

	@Autowired
	private UserService userService;

	public boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("userId") != null;
	}

	public User getLoggedInUser(HttpSession session) {
		if (!isLoggedIn(session))
			return null;
		else
			return userService.getSingleUser((Integer) session
					.getAttribute("userId"));
	}

	public void addContextVariables(ModelAndView mav, boolean sidebar,
			String parentMenuScope) {
		mav.addObject("sidebar", true);
		mav.addObject("parentMenuScope", "../index/");
	}

	public void addContextVariables(ModelAndView mav, boolean sidebar) {
		mav.addObject("sidebar", true);
		mav.addObject("parentMenuScope", "../index/");
	}
}
