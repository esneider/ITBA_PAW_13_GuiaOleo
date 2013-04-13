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

	public boolean existsUser() {

		return false;
	}

	public void resetUser(int id) {

		
	}

	public void setUser(int id) {

		
	}

	public User login(String username, String password) {
		return userDAO.login(username, password);
	}

	public User register(String username, String password, String name,
			String surname, String mail) {

		return userDAO.register(username, password, name, surname, mail);
	}

	
}
