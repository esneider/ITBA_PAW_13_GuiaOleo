package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;


public class RestaurantConverter implements IConverter<Restaurant> {

	private static final long serialVersionUID = 1540811699534810763L;
    
    @Override
	public Restaurant convertToObject(String value, Locale arg1) {
    	throw new UnsupportedOperationException();
	}

	@Override
	public String convertToString(Restaurant value, Locale arg1) {
		return value.getName();
	}
}

