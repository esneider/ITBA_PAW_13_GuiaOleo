package ar.edu.itba.it.paw.domain;


/**
 * User repository.
 * 
 */
public interface UserRepo {

	/**
	 * Obtains a single User by its id
	 */
	public User getSingleUser(int id);
	
	/**
	 * Check if emails already exists for non duplicate emails
	 */
	public boolean emailExists(String email);
	
	/**
	 * Check if emails already exists for non duplicate emails
	 */
	public boolean emailExists(String email, int id);
	
	/**
	 * Check if user name already exists for non duplicate usernames
	 */
	public boolean usernameExists(String username);
}
