package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.RegisterForm;

@Component
public class RegisterFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		RegisterForm obj = (RegisterForm) target;
		if (obj.getName() == null)
			errors.rejectValue("name", "You have to provide your name");
		if (obj.getSurname() == null)
			errors.rejectValue("surname", "You have to provide your surname.");
		if (obj.getEmail() == null)
			errors.rejectValue("email", "You have to provide an email.");
		boolean passwordSetted = obj.getPassword() != null;
		boolean repasswordSettted = obj.getRepassword() != null;
		if (!passwordSetted)
			errors.rejectValue("password", "You have to provide a password.");
		if (!repasswordSettted)
			errors.rejectValue("repassword", "You have to repeat your password.");
		if (passwordSetted && repasswordSettted && obj.getPassword() != obj.getRepassword())
			errors.rejectValue("repassword", "Passwords don't match!");
		if (obj.getUsername() == null)
			errors.rejectValue("username", "You have to provide an user name");
		else {
			if (obj.getUsername().length() > 10) {
				errors.rejectValue("username", "The user name is too long.");
			}
		}

	}
}
