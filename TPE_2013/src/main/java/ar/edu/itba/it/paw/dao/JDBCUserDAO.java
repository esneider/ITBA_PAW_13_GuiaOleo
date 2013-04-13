package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.dao.interfaces.UserDAO;
import ar.edu.itba.it.paw.model.User;

public class JDBCUserDAO extends AbstractDAO implements UserDAO {

	private static UserDAO self = null;

	public synchronized static UserDAO getInstance() {

		if (self == null) {

			self = new JDBCUserDAO();
		}

		return self;
	}

	public User login(String username, String password) {

		ResultSet rs = executeQuery(
				"SELECT * FROM users WHERE username = ? AND password = ?",
				username, password);

		try {
			if (!rs.next()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return newUser(rs);
	}

	@Override
	public User register(String username, String password, String name,
			String surname, String mail) {

		ResultSet rs = executeQuery(
				"SELECT * FROM users WHERE username = ? AND password = ?",
				username, password);

		// TODO Auto-generated method stub
		return null;
	}
	
	private User newUser(ResultSet rs) {

		try {
			return new User(rs.getInt("id"), rs.getString("name"),
					rs.getString("surname"), rs.getString("mail"),
					rs.getString("username"), rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
