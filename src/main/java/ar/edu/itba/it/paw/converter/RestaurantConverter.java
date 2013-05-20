package ar.edu.itba.it.paw.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.service.interfaces.RestaurantService;

@Component
public class RestaurantConverter implements Converter<String, Restaurant> {

	private RestaurantService restService;
	
	@Autowired
	public RestaurantConverter (RestaurantService restService) {
		this.restService = restService;
	}
	
	@Override
	public Restaurant convert(String arg0) {
		return restService.getSingleRestaurant(Integer.valueOf(arg0));
	}

}
