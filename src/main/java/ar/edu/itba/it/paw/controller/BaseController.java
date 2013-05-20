package ar.edu.itba.it.paw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.interfaces.PictureService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
public abstract class BaseController {

	@Autowired
	private PictureService pictureService;

	public boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("user") != null;
	}

	public User getLoggedInUser(HttpSession session) {
		if (!isLoggedIn(session))
			return null;
		else
			return (User)session.getAttribute("user");
	}
	
	public void setLoggedInUser(HttpSession session, User user) {
		if (user != null) {
			if (user.getAvatar() != null)
				user.setAvatar(pictureService.getPictureById(user.getAvatar().getId()));
		}
		session.setAttribute("user", user);
	}
	
	public void logoutUser(HttpSession session) {
		session.invalidate();
	}

	public EnhancedModelAndView generateContext(String title, boolean sidebar) {
		EnhancedModelAndView mav = new EnhancedModelAndView(title);
		mav.addObject("sidebar", sidebar);
		mav.addObject("parentMenuScope", "../index/");
		return mav;
	}
	
	public EnhancedModelAndView generateContext(String title, boolean sidebar, String viewName) {
		EnhancedModelAndView mav = generateContext(title, sidebar);
		mav.setViewName(viewName);
		return mav;
	}
	
	public EnhancedModelAndView indexContext() {
		EnhancedModelAndView mav = new EnhancedModelAndView("Guia Oleo Facha");
		mav.setViewName("redirect:/bin/index/list");
		return mav;
	}
}
