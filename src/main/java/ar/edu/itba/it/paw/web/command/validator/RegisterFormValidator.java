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
		if (obj.getName().equals(""))
			errors.rejectValue("name", "empty");
		if (obj.getSurname().equals(""))
			errors.rejectValue("surname", "empty");
		if (obj.getEmail().equals(""))
			errors.rejectValue("email", "empty");
		if (!Utils.isEmail(obj.getEmail()))
			errors.rejectValue("email", "badformat");
		if (userRepo.emailExists(obj.getEmail(), -1))
			errors.rejectValue("email", "duplicated");
		boolean passwordSetted = !obj.getPassword().equals("");
		boolean repasswordSettted = !obj.getRepassword().equals("");
		if (!passwordSetted)
			errors.rejectValue("password", "empty");
		if (!repasswordSettted)
			errors.rejectValue("repassword", "empty");
		if (!obj.getPassword().equals(obj.getRepassword()))
			errors.rejectValue("password", "mismatch");
		if (obj.getUsername().equals(""))
			errors.rejectValue("username", "empty");
		else {
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
