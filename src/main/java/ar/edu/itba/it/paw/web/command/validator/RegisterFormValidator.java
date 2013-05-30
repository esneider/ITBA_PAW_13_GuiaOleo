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

        RegisterForm form = (RegisterForm) target;

        String name       = Utils.normalizeString(form.getName());
        String surname    = Utils.normalizeString(form.getSurname());
        String email      = Utils.normalizeString(form.getEmail());
        String password   = Utils.normalizeString(form.getPassword());
        String rePassword = Utils.normalizeString(form.getRepassword());
        String username   = Utils.normalizeString(form.getUsername());

        if (name.isEmpty()) {
            errors.rejectValue("name", "empty");
        }

        if (surname.isEmpty()) {
            errors.rejectValue("surname", "empty");
        }

        if (email.isEmpty()) {
            errors.rejectValue("email", "empty");
        } else
        if (!Utils.isEmail(email)) {
            errors.rejectValue("email", "badformat");
        } else
        if (userRepo.emailExists(email)) {
            errors.rejectValue("email", "duplicated");
        }

        if (password.isEmpty()) {
            errors.rejectValue("password", "empty");
        } else
        if (rePassword.isEmpty()) {
            errors.rejectValue("repassword", "empty");
        } else
        if (!password.equals(rePassword)) {
            errors.rejectValue("repassword", "mismatch");
        }

        if (username.isEmpty()) {
            errors.rejectValue("username", "empty");
        }

        if (username.length() > 10) {
            errors.rejectValue("username", "toolong");
        }

        if (userRepo.usernameExists(username)) {
            errors.rejectValue("username", "duplicated");
        }

        if (form.getAvatar().isEmpty()) {
            errors.rejectValue("avatar", "empty");
        }
        if (!(form.getAvatar().getContentType().equals("image/jpeg") || form.getAvatar().getContentType().equals("image/jpg"))) {
        	errors.rejectValue("avatar", "invalidformat");
        }
    }
}

