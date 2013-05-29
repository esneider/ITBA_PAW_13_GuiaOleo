package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.command.RestaurantForm;


@Component
public class RestaurantFormValidator  implements Validator  {

    @Override
    public boolean supports(Class<?> clazz) {
        return RestaurantForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RestaurantForm form = (RestaurantForm) target;

        String name      = Utils.normalizeString(form.getName());
        String address   = Utils.normalizeString(form.getAddress());
        String area      = Utils.normalizeString(form.getArea());
        String telephone = Utils.normalizeString(form.getTelephone());
        String website   = Utils.normalizeString(form.getWebsite());
        String timerange = Utils.normalizeString(form.getTimerange());

        if (name.isEmpty()) {
            errors.rejectValue("name", "empty");
        }

        if (address.isEmpty()) {
            errors.rejectValue("address", "empty");
        }

        if (area.isEmpty()) {
            errors.rejectValue("area", "empty");
        }

        if (telephone.isEmpty()) {
            errors.rejectValue("telephone", "empty");
        }

        if (website.isEmpty()) {
            errors.rejectValue("website", "empty");
        }

        if (timerange.isEmpty()) {
            errors.rejectValue("timerange", "empty");
        }

        if (form.getAvgprice() <= 0) {
            errors.rejectValue("avgprice", "nonpositive");
        }

        if (form.getFoodTypes().isEmpty()) {
            errors.rejectValue("foodTypes", "empty");
        }
    }
}

