package ar.edu.itba.it.paw.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.User;
import ar.edu.itba.it.paw.service.interfaces.UserService;

@Component
public class UserConverter implements Converter<String, User> {

	private UserService userService;
	
	@Autowired
	public UserConverter (UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User convert(String arg0) {
		return userService.getSingleUser(Integer.valueOf(arg0));
	}

}
