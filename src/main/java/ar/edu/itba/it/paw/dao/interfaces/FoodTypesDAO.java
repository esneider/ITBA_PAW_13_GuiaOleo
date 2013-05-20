package ar.edu.itba.it.paw.dao.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.domain.FoodType;

public interface FoodTypesDAO {

	/**
	 * Obtains a list from all FoodTypes
	 */
	public List<FoodType> getAll();
	/**
	 * Obtains a single FoodType
	 */
	public FoodType getSingleFoodType(int id);
	
}
