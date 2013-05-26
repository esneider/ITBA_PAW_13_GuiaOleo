package ar.edu.itba.it.paw.domain.user;

import java.util.List;

/**
 * User repository.
 * 
 */
public interface UserRepo {

	/**
	 * Obtains a single User by its id
	 */
	public User get(int id);

	/**
	 * Check if emails already exists for non duplicate emails
	 */
	public boolean emailExists(String email, int id);

	/**
	 * Check if user name already exists for non duplicate usernames
	 */
	public boolean usernameExists(String username);

	/**
	 * Checks if a user exists, in that case, returns that user.
	 */
	public User login(String username, String password);

	/**
	 * Saves user
	 */
	public void save(User u);

	/**
	 * Get a list from all the Users
	 */
	public List<User> getAll();

}
