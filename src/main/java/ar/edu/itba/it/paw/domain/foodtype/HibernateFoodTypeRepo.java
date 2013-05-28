package ar.edu.itba.it.paw.domain.foodtype;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;

@Repository
public class HibernateFoodTypeRepo extends AbstractHibernateRepo implements FoodTypeRepo {

	@Autowired
	public HibernateFoodTypeRepo(SessionFactory sessionFactory) {

		super(sessionFactory);
	}

	@Override
	public List<FoodType> getAll() {

		return find("from FoodType");
	}

	@Override
	public FoodType get(int foodtypeid) {

		return get(FoodType.class, foodtypeid);
	}
}

