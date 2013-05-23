package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;

@Component
public class UserConverter implements Converter<String, User> {

	private UserRepo userRepo;
	
	@Autowired
	public UserConverter (UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public User convert(String arg0) {
		return userRepo.get(Integer.valueOf(arg0));
	}

}
