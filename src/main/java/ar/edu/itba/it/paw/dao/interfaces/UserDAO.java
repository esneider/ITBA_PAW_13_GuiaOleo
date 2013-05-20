package ar.edu.itba.it.paw.dao.interfaces;

import ar.edu.itba.it.paw.domain.User;

public interface UserDAO {

	public User login(String username, String password);
	
	public User getSingleUser(int id);

	public void register(User user);

	public boolean usernameExists(String username);

	public boolean emailExists(String email);
	
	public boolean emailExists(String email, int id);

	public void update(User user);
}
