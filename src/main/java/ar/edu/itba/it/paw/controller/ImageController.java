package ar.edu.itba.it.paw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.interfaces.UserService;

@Controller
public class ImageController extends BaseController {

	private UserService userService;
	
	@Autowired
	public ImageController (UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping
	@ResponseBody
	public byte[] showUserImage(HttpSession session)  {
		if (!isLoggedIn(session))
			return null;
		return getLoggedInUser(session).getAvatar().getBytes();
	}
	
	@RequestMapping
	@ResponseBody
	public byte[] show(@RequestParam(value = "userId") Integer id)  {
		User user = userService.getSingleUser(id);
		if (user == null || user.getAvatar() == null)
			return null;
		return user.getAvatar().getBytes();
	}
	
}
