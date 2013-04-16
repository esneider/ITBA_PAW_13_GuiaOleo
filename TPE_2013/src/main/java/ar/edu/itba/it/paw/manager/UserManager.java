package ar.edu.itba.it.paw.manager;

import ar.edu.itba.it.paw.dao.JDBCUserDAO;
import ar.edu.itba.it.paw.dao.interfaces.UserDAO;
import ar.edu.itba.it.paw.model.User;

public class UserManager {

	private static UserManager self = null;
	private UserDAO userDAO;
	
	private UserManager(UserDAO userDAO) {
		
		this.userDAO = userDAO;
	}

	public synchronized static UserManager getInstance() {
		
		if (self == null) {
			self = new UserManager(JDBCUserDAO.getInstance());
		}
		
		return self;
	}

	public boolean usernameExists(String username) {

		return userDAO.usernameExists(username);
	}

	public boolean emailExists(String email) {

		return userDAO.emailExists(email);
	}

	public User login(String username, String password) {
		
		return userDAO.login(username, password);
	}
	
	public User getSingleUser (int id) {

		return userDAO.getSingleUser(id);
	}

	public User register(String name, String surname, String email, String username, String password) {

		return userDAO.register(new User(name, surname, email, username, password));
	}

	public void update(User user) {

		userDAO.update(user);
	}

	
}
