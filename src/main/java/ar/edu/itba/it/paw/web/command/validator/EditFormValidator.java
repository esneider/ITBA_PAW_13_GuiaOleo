package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.command.EditForm;


@Component
public class EditFormValidator implements Validator {

    private UserRepo userRepo;

    @Autowired
    public EditFormValidator(UserRepo userRepo) {

        this.userRepo = userRepo;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return EditForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        EditForm form = (EditForm) target;

        String oldPassword = Utils.normalizeString(form.getOldPassword());
        String password    = Utils.normalizeString(form.getPassword());
        String rePassword  = Utils.normalizeString(form.getRepassword());
        String name        = Utils.normalizeString(form.getName());
        String surname     = Utils.normalizeString(form.getSurname());
        String email       = Utils.normalizeString(form.getEmail());

        User user = userRepo.get(form.getUserId());

        if (!oldPassword.isEmpty()) {

            if (!user.getPassword().equals(oldPassword)) {
                errors.rejectValue("oldPassword", "mismatch");
            }

        } else {
            errors.rejectValue("oldPassword", "empty");
        }

        if (!password.equals(rePassword)) {
            errors.rejectValue("repassword", "mismatch");
        }

        if (name.isEmpty()) {
            errors.rejectValue("name", "empty");
        }

        if (surname.isEmpty()) {
            errors.rejectValue("surname", "empty");
        }

        if (userRepo.emailExists(email)) {
            errors.rejectValue("email", "duplicated");
        }

        if (email.isEmpty() || !Utils.isEmail(email)) {
            errors.rejectValue("email", "badformat");
        }
    }
}

