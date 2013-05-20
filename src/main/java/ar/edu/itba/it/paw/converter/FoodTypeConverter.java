package ar.edu.itba.it.paw.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.service.interfaces.FoodTypeService;

@Component
public class FoodTypeConverter implements Converter<String, FoodType> {

	private FoodTypeService ftService;
	
	@Autowired
	public FoodTypeConverter (FoodTypeService ftService) {
		this.ftService = ftService;
	}
	
	@Override
	public FoodType convert(String arg0) {
		return ftService.getSingleFoodType(Integer.valueOf(arg0));
	}

}
