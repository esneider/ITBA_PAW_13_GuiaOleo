package ar.edu.itba.it.paw.domain.user;

import java.util.List;


public interface UserRepo {

    /**
     * Returns a single User by its id
     */
    public User get(int id);

    /**
     * Checks if email already exists
     */
    public boolean emailExists(String email);

    /**
     * Checks if user name already exists
     */
    public boolean usernameExists(String username);

    /**
     * Returns a User by username and password
     */
    public User login(String username, String password);

    /**
     * Saves user
     */
    public void save(User user);

    /**
     * Returns a list of all the Users
     */
    public List<User> getAll();
}

