package ar.edu.itba.it.paw.dao.interfaces;

import ar.edu.itba.it.paw.model.User;

public interface UserDAO {

	public User login(String username, String password);

	public User register(User user);

	public boolean usernameExists(String username);
}
