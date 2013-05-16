package ar.edu.itba.it.paw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.dao.interfaces.UserDAO;
import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.model.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	private UserServiceImpl(UserDAO userDAO) {

		this.userDAO = userDAO;
	}

	public boolean usernameExists(String username) {

		return userDAO.usernameExists(username);
	}

	public boolean emailExists(String email, boolean canRepeat, int id) {
		if (!canRepeat)
			return userDAO.emailExists(email);
		else
			return userDAO.emailExists(email, id);
	}

	public User login(String username, String password) {

		return userDAO.login(username, password);
	}

	public User getSingleUser(int id) {

		return userDAO.getSingleUser(id);
	}

	public User register(String name, String surname, String email,
			String username, String password) {

		return register(name, surname, email, username, password);
	}

	public User register(String name, String surname, String email,
			String username, String password, Picture avatar) {

		User user = new User(name, surname, email, username, password, avatar);
		userDAO.register(user);
		return user;
	}

	public void update(User user) {

		userDAO.update(user);
	}

}
