package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.interfaces.UserService;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.command.EditForm;

@Component
public class EditFormValidator implements Validator {

private UserService userService;
	
	@Autowired
	public EditFormValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EditForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		EditForm obj = (EditForm) target;
		if (obj.getOldPassword() != null) {
			if (obj.getOldPassword().equals("")) {
				errors.rejectValue("oldPassword", "empty");
			} else {
				User user = userService.getSingleUser(obj.getUserId());
				if (user != null) {
					if (!user.getPassword().trim().equals(obj.getOldPassword()))
							errors.rejectValue("oldPassword", "mismatch");
				}
			}
		}
		if (!obj.getPassword().equals(obj.getRepassword()))
			errors.rejectValue("password", "mismatch");
		if (userService.emailExists(obj.getEmail(), true, obj.getUserId()))
			errors.rejectValue("email", "duplicated");
		if (!Utils.isEmail(obj.getEmail()))
			errors.rejectValue("email", "badformat");
	}
	
}
