package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.command.LoginForm;


@Component
public class LoginFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {

        return LoginForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LoginForm form = (LoginForm) target;

        String username = Utils.normalizeString(form.getUsername());
        String password = Utils.normalizeString(form.getPassword());

        if (username.isEmpty()) {
            errors.rejectValue("username", "empty");
        }

        if (password.isEmpty()) {
            errors.rejectValue("password", "empty");
        }
    }
}

