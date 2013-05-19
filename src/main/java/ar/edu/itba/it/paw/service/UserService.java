package ar.edu.itba.it.paw.service;

import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.model.User;

public interface UserService {

	public boolean usernameExists(String username);

	public boolean emailExists(String email, boolean canRepeat, int id);

	public User login(String username, String password);

	public User getSingleUser(int id);

	public User register(String name, String surname, String email,
			String username, String password);
	
	public User register(String name, String surname, String email,
			String username, String password, Picture avatar);

	public void update(User user);

}
