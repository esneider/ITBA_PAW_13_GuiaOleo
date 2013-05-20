package ar.edu.itba.it.paw.domain;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.interfaces.FoodTypesRepo;

@Repository
public class HibernateFoodTypeRepo extends AbstractHibernateRepo implements
		FoodTypesRepo {
	@Autowired

	public HibernateFoodTypeRepo(SessionFactory sessionFactory) {
		super(sessionFactory);

	}

	@Override
	public List<FoodType> getAll() {
		return find("from foodtypes");
	}

	@Override
	public FoodType getSingleFoodType(int foodtypeid) {
		return get(FoodType.class, foodtypeid);
	}

}
