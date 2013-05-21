package ar.edu.itba.it.paw.domain.foodtype;

import java.util.List;


public interface FoodTypeRepo {

	/**
	 * FoodTypes repository.
	 * 
	 */

	/**
	 * Obtains a list from all FoodTypes
	 */
	public List<FoodType> getAll();

	/**
	 * Obtains a single FoodType
	 */
	
	public FoodType get(int id);

}
