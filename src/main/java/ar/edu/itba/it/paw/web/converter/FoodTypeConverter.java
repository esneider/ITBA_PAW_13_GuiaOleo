package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;

public class FoodTypeConverter implements IConverter<FoodType> {
	
	private static final long serialVersionUID = -4106262983304101899L;

	@Override
	public FoodType convertToObject(String value, Locale locale) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String convertToString(FoodType value, Locale locale) {
		return value.getName();
	}
}