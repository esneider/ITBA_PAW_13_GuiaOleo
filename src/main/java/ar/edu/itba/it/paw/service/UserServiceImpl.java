package ar.edu.itba.it.paw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.dao.interfaces.UserDAO;
import ar.edu.itba.it.paw.domain.Picture;
import ar.edu.itba.it.paw.domain.User;
import ar.edu.itba.it.paw.service.interfaces.PictureService;
import ar.edu.itba.it.paw.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	private PictureService picService;

	@Autowired
	private UserServiceImpl(UserDAO userDAO, PictureService picService) {
		this.userDAO = userDAO;
		this.picService = picService;
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
		return register(name, surname, email, username, password, null);
	}

	public User register(String name, String surname, String email,
			String username, String password, Picture avatar) {

		int picId = 0;
		if (avatar != null) {
			picId = picService
					.insert(avatar.getInputStream(), avatar.getMime()).getId();
		}
		User user = new User(name, surname, email, username, password,
				picService.getPictureById(picId));
		userDAO.register(user);
		return user;
	}

	public void update(User user) {

		int picId = user.getAvatar().getId();
		if (picId == -1) {
			user.getAvatar().setId(
					picService.insert(user.getAvatar().getInputStream(),
							user.getAvatar().getMime()).getId());
		}
		userDAO.update(user);
	}

}
