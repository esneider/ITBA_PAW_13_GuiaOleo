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
			errors.rejectValue("name", "empty");
		if (obj.getSurname() == null)
			errors.rejectValue("surname", "empty");
		if (obj.getEmail() == null)
			errors.rejectValue("email", "empty");
		boolean passwordSetted = obj.getPassword() != null;
		boolean repasswordSettted = obj.getRepassword() != null;
		if (!passwordSetted)
			errors.rejectValue("password", "empty");
		if (!repasswordSettted)
			errors.rejectValue("repassword", "empty");
		if (passwordSetted && repasswordSettted && obj.getPassword() != obj.getRepassword())
			errors.rejectValue("password", "mismatch");
		if (obj.getUsername() == null)
			errors.rejectValue("username", "empty");
		else {
			if (obj.getUsername().length() > 10) {
				errors.rejectValue("username", "toolong");
			}
		}

	}
}
