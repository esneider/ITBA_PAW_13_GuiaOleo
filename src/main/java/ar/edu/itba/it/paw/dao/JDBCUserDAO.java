package ar.edu.itba.it.paw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ar.edu.itba.it.paw.dao.interfaces.UserDAO;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.PictureService;

public class JDBCUserDAO extends AbstractDAO implements UserDAO {

	private static UserDAO self = null;
	private static Logger logger = Logger.getLogger(JDBCUserDAO.class);

	public synchronized static UserDAO getInstance() {

		if (self == null) {

			self = new JDBCUserDAO();
		}

		return self;
	}

	public User login(String username, String password) {

		ResultSet rs = executeQuery( "SELECT * FROM users WHERE username = ? AND password = ?", username, password);

		try {

			User u = null;

			if (rs.next()) {
				u = newUser(rs);
			}
			rs.close();

			return u;

		} catch (Exception e) {

			logger.error("SQL Error");
			return null;
		}
	}

	public void register(User user) {

		PreparedStatement ps = execute(
				"INSERT INTO users (name, surname, mail, username, password, pictureId) VALUES (?, ?, ?, ?, ?, ?)",
				user.getName(), user.getSurname(), user.getEmail(), user.getUsername(),
				user.getPassword(), user.getAvatar().getId());

		try {
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				user.setId(rs.getInt("id"));
			}
			rs.close();

		} catch (SQLException e) {
			logger.error("SQL Error");
		}
	}

	@Override
	public boolean usernameExists(String username) {

		ResultSet rs = executeQuery("SELECT id FROM users WHERE username = ?",
				username);

		try {

			if (!rs.next()) {
				rs.close();
				return false;
			}

			rs.close();

		} catch (Exception e) {

			logger.error("SQL Error");
			return false;
		}

		return true;
	}

	@Override
	public boolean emailExists(String email) {

		ResultSet rs = executeQuery("SELECT id FROM users WHERE mail = ?",
				email);

		try {

			if (!rs.next()) {
				rs.close();
				return false;
			}

			rs.close();

		} catch (Exception e) {

			logger.error("SQL Error");
			return false;
		}

		return true;
	}
	
	public boolean emailExists(String email, int id) {

		ResultSet rs = executeQuery("SELECT id FROM users WHERE mail = ? AND id != ?",
				email, id);

		try {

			if (!rs.next()) {
				rs.close();
				return false;
			}

			rs.close();

		} catch (Exception e) {

			logger.error("SQL Error");
			return false;
		}

		return true;
	}

	private User newUser(ResultSet rs) {

		try {

			User u = new User(rs.getString("name"),
					rs.getString("surname"), rs.getString("mail"),
					rs.getString("username"), rs.getString("password"),
					JDBCPictureDAO.getInstance().getPictureById(rs.getInt("pictureId")));
			u.setId(rs.getInt("id"));
			return u;
		} catch (SQLException e) {
			logger.error("SQL Error");
		}

		return null;
	}

	@Override
	public User getSingleUser(int id) {

		ResultSet rs = executeQuery("SELECT * FROM users WHERE id = ?", id);

		try {

			User u = null;

			if (rs.next()) {
				u = newUser(rs);
			}
			rs.close();

			return u;

		} catch (SQLException e) {

			logger.error("SQL Error");
		}

		return null;
	}
	@Override
	public void update(User user) {

		executeUpdate(
				"UPDATE users SET name = ?, surname = ?, mail = ?, username = ?, password = ?, pictureId = ? WHERE id = ?",
				user.getName(), user.getSurname(), user.getEmail(), user.getUsername(),
				user.getPassword(), user.getAvatar().getId(), user.getId());
	}
}
