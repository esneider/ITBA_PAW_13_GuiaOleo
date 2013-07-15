package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;

import ar.edu.itba.it.paw.domain.restaurant.Rating;

public class RatingConverter implements IConverter<Rating> {

	private static final long serialVersionUID = 2450169549791036737L;

	@Override
	public Rating convertToObject(String value, Locale arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String convertToString(Rating value, Locale arg1) {
		throw new UnsupportedOperationException();
	}

}
