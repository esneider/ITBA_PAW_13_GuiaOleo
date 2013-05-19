package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.LoginForm;

@Component
public class LoginFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		LoginForm obj = (LoginForm) target;
		if (obj.getUsername().equals(""))
			errors.rejectValue("username", "empty");
		if (obj.getUsername().length() > 10)
			errors.rejectValue("username", "toolong");
		if (obj.getPassword().equals(""))
			errors.rejectValue("password", "empty");
		
	}

}
