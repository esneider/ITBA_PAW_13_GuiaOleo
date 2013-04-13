package ar.edu.itba.it.paw.manager;

import java.util.List;

import ar.edu.itba.it.paw.dao.JDBCFoodTypesDAO;
import ar.edu.itba.it.paw.dao.interfaces.FoodTypesDAO;
import ar.edu.itba.it.paw.model.FoodType;

public class FoodTypeManager {

	private static FoodTypeManager self;
	private FoodTypesDAO DAO;
	
	public synchronized static FoodTypeManager getInstance() {
		if (self == null) 
			self = new FoodTypeManager(new JDBCFoodTypesDAO());
		return self;
	}
	
	private FoodTypeManager (FoodTypesDAO ftDAO) {
		this.DAO = ftDAO;
	}
	
	public List<FoodType> getAll() {
		return DAO.getAll();
	}
	
	public FoodType getSingleFoodType(int id) {
		return DAO.getSingleFoodType(id);
	}
	
}
