package ar.edu.itba.it.paw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.itba.it.paw.manager.ConnectionManager;

public abstract class AbstractDAO {
	
	protected ConnectionManager conn;
	
	protected AbstractDAO() {
		conn = ConnectionManager.getInstance();
	}
	
	protected ResultSet executeQuery(String query, Object... parameters) {
		try {
			int i;
			PreparedStatement sql = conn.getConnection().prepareStatement(query);
			for(i = 0; i < parameters.length; i++) {
				Object param = parameters[i];
				if (param instanceof String) 
					sql.setString(i, (String)param);
				if (param instanceof Integer) 
					sql.setInt(i, (Integer)param);
				if (param instanceof Float) 
					sql.setFloat(i, (Float)param);
				if (param instanceof Boolean) 
					sql.setBoolean(i, (Boolean)param);
				ResultSet rs = sql.executeQuery();
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
