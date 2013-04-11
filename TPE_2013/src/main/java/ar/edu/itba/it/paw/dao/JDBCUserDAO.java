package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;

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

		ResultSet rs = executeQuery("SELECT * FROM users WHERE username = ? AND password = ?", username, password);

		try {
			if (!rs.next()) {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
}
