package ar.edu.itba.it.paw.domain.user;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;
import ar.edu.itba.it.paw.domain.picture.PictureRepo;

@Repository
public class HibernateUserRepo extends AbstractHibernateRepo implements
		UserRepo {

	private PictureRepo pictureRepo;

	@Autowired
	public HibernateUserRepo(SessionFactory sessionFactory,
			PictureRepo pictureRepo) {
		super(sessionFactory);
		this.pictureRepo = pictureRepo;
	}

	@Override
	public User get(int userid) {
		return get(User.class, userid);
	}

	@Override
	public boolean emailExists(String email, int id) {
		if (id != -1)
			return !find("from User where email = ? and id != ?", email, id)
					.isEmpty();
		else
			return !find("from User where email = ?", email).isEmpty();
	}

	@Override
	public boolean usernameExists(String username) {
		return !find("from User where username = ?", username).isEmpty();
	}

	@Override
	public User login(String username, String password) {
		List<User> list = find("from User where username = ? and password = ?",
				username, password);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public void save(User u) {
		pictureRepo.save(u.getAvatar());
		super.save(u);
	}

	@Override
	public List<User> getAll() {
		List<User> list = find("from User");
		return list;
	}

}