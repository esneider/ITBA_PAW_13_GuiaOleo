package ar.edu.itba.it.paw.service;

import java.util.List;

import ar.edu.itba.it.paw.dao.JDBCFoodTypesDAO;
import ar.edu.itba.it.paw.dao.interfaces.FoodTypesDAO;
import ar.edu.itba.it.paw.model.FoodType;

public class FoodTypeService {

	private static FoodTypeService self;
	private FoodTypesDAO DAO;
	
	public synchronized static FoodTypeService getInstance() {
		if (self == null) 
			self = new FoodTypeService(new JDBCFoodTypesDAO());
		return self;
	}
	
	private FoodTypeService (FoodTypesDAO ftDAO) {
		this.DAO = ftDAO;
	}
	
	public List<FoodType> getAll() {
		return DAO.getAll();
	}
	
	public FoodType getSingleFoodType(int id) {
		return DAO.getSingleFoodType(id);
	}
	
}
