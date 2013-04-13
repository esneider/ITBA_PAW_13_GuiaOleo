package ar.edu.itba.it.paw.dao.interfaces;

import ar.edu.itba.it.paw.model.User;

public interface UserDAO {

	public User login(String username, String password);

	public User register(String username, String password, String name,
			String surname, String mail);
}
