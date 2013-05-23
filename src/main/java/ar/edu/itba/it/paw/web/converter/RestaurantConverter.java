package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;

@Component
public class RestaurantConverter implements Converter<String, Restaurant> {

	private RestaurantRepo restRepo;
	
	@Autowired
	public RestaurantConverter (RestaurantRepo restRepo) {
		this.restRepo = restRepo;
	}
	
	@Override
	public Restaurant convert(String arg0) {
		return restRepo.get(Integer.valueOf(arg0));
	}

}
