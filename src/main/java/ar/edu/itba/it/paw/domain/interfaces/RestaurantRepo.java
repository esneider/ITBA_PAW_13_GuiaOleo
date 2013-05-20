package ar.edu.itba.it.paw.domain.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.domain.Restaurant;

public interface RestaurantRepo {

	public List<Restaurant> getAll();

	public Restaurant get(int restaurantId);
	
	
}
