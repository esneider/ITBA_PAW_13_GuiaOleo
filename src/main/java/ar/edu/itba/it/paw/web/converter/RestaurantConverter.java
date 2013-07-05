package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;


public class RestaurantConverter implements IConverter<Restaurant> {

    private RestaurantRepo restRepo;

    @Autowired
    public RestaurantConverter (RestaurantRepo restRepo) {

        this.restRepo = restRepo;
    }
    
    @Override
	public Restaurant convertToObject(String value, Locale arg1) {
		return restRepo.get(Integer.valueOf(value));

	}

	@Override
	public String convertToString(Restaurant value, Locale arg1) {
		return value.getName();
	}
}

