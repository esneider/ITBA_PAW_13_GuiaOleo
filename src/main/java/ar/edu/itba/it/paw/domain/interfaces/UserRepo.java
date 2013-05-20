package ar.edu.itba.it.paw.domain.interfaces;

import ar.edu.itba.it.paw.domain.User;

/**
 * User repository.
 * 
 */
public interface UserRepo {
	
	/**
	 * Obtains a single User by its id
	 */
	public User getSingleUser(int id);
}
