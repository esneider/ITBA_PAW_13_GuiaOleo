package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;

public class UserConverter implements IConverter<User> {

	private static final long serialVersionUID = -1539801986649439264L;

	private UserRepo userRepo;

	@Autowired
	public UserConverter(UserRepo userRepo) {

		this.userRepo = userRepo;
	}

	@Override
	public User convertToObject(String value, Locale arg1) {
		return userRepo.get(Integer.valueOf(value));

	}

	@Override
	public String convertToString(User value, Locale arg1) {
		return value.getUsername();
	}

}
