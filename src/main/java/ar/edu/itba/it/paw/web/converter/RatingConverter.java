package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.restaurant.Rating;

@SuppressWarnings("serial")
@Component
public class RatingConverter implements IConverter<Rating> {


	@Override
	public Rating convertToObject(String value, Locale arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String convertToString(Rating value, Locale arg1) {
		throw new UnsupportedOperationException();
	}

}
