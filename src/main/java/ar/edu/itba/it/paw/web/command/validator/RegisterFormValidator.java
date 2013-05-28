package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.command.RegisterForm;

@Component
public class RegisterFormValidator implements Validator {
	
	private UserRepo userRepo;
	
	@Autowired
	public RegisterFormValidator(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		RegisterForm obj = (RegisterForm) target;

		if (obj.getName().isEmpty())
			errors.rejectValue("name", "empty");

		if (obj.getSurname().isEmpty())
			errors.rejectValue("surname", "empty");

		if (obj.getEmail().isEmpty())
			errors.rejectValue("email", "empty");

		if (!Utils.isEmail(obj.getEmail()))
			errors.rejectValue("email", "badformat");

		if (userRepo.emailExists(obj.getEmail()))
			errors.rejectValue("email", "duplicated");

		if (obj.getPassword().isEmpty())
			errors.rejectValue("password", "empty");

		if (obj.getRepassword().isEmpty())
			errors.rejectValue("repassword", "empty");

		if (!obj.getPassword().equals(obj.getRepassword()))
			errors.rejectValue("password", "mismatch");

		if (obj.getUsername().isEmpty()) {
			errors.rejectValue("username", "empty");
		} else {
			if (obj.getUsername().length() > 10) {
				errors.rejectValue("username", "toolong");
			}
			if (userRepo.usernameExists(obj.getUsername())) {
				errors.rejectValue("username", "duplicated");
			}
		}

		if (obj.getAvatar().isEmpty())
			errors.rejectValue("avatar", "empty");

	}
}
