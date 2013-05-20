package ar.edu.itba.it.paw.service.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.domain.FoodType;


public interface FoodTypeService {
	
	public List<FoodType> getAll();
	
	public FoodType getSingleFoodType(int id);
	
}
