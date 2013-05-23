package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.RestaurantForm;

@Component
public class RestaurantFormValidator  implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		return RestaurantForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RestaurantForm obj = (RestaurantForm) target;
		if (obj.getName().equals(""))
			errors.rejectValue("name", "empty");
		if (obj.getAddress().equals(""))
			errors.rejectValue("address", "empty");
		if (obj.getArea().equals(""))
			errors.rejectValue("area", "empty");
		if (obj.getArea().equals(""))
			errors.rejectValue("telephone", "empty");
		if (obj.getArea().equals(""))
			errors.rejectValue("website", "empty");
		if (obj.getArea().equals(""))
			errors.rejectValue("timerange", "empty");
		if (obj.getArea().equals(""))
			errors.rejectValue("avgprice", "empty");
		if (obj.getFoodTypes().isEmpty())
			errors.rejectValue("foodTypes", "empty");
		
	}

}
