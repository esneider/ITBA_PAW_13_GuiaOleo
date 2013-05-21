package ar.edu.itba.it.paw.domain;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserRepo extends AbstractHibernateRepo implements
		UserRepo {

	@Autowired
	public HibernateUserRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User getSingleUser(int userid) {
		return get(User.class, userid);
	}

	@Override
	public boolean emailExists(String email) {
		return !find("from User where email = ?", email).isEmpty();
	}

	@Override //TODO ELIMINAAAAMEEEE
	public boolean emailExists(String email, int id) {
		return !find("from User where email = ?", email).isEmpty();

	}

	@Override
	public boolean usernameExists(String username) {
		return !find("from User where username = ?", username).isEmpty();
	}

}
