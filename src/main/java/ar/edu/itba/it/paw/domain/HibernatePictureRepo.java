package ar.edu.itba.it.paw.domain;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.interfaces.PictureRepo;

@Repository
public class HibernatePictureRepo extends AbstractHibernateRepo implements
		PictureRepo {
	@Autowired
	public HibernatePictureRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Picture getPictureById(int pictureid) {
		return get(Picture.class, pictureid);

	}

}
