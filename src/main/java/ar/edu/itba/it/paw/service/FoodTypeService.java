package ar.edu.itba.it.paw.service;

import java.util.List;

import ar.edu.itba.it.paw.model.FoodType;


public interface FoodTypeService {
	
	public List<FoodType> getAll();
	
	public FoodType getSingleFoodType(int id);
	
}
