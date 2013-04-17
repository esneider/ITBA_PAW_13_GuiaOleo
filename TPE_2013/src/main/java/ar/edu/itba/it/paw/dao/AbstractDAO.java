package ar.edu.itba.it.paw.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.database.ConnectionManager;

public abstract class AbstractDAO {
	
	protected ConnectionManager conn;
	
	protected AbstractDAO() {
		conn = ConnectionManager.getInstance();
	}
	
	protected ResultSet executeQuery(String query, Object... parameters) {
		try {	
			PreparedStatement sql = conn.getConnection().prepareStatement(query);
				setSQLParameters(sql, parameters);
				ResultSet rs = sql.executeQuery();
				return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	protected boolean execute(String query, Object... parameters) {
		try {	
			PreparedStatement sql = conn.getConnection().prepareStatement(query);
				setSQLParameters(sql, parameters);
				return sql.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected void executeUpdate(String query, Object... parameters) {
		try {	
			PreparedStatement sql = conn.getConnection().prepareStatement(query);
				setSQLParameters(sql, parameters);
				sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private void setSQLParameters(PreparedStatement sql, Object[] parameters){
		int i;
		try {
			for(i = 0; i < parameters.length; i++) {
				Object param = parameters[i];
				if (param instanceof String)
					sql.setString(i + 1, (String) param);
				if (param instanceof Integer) 
					sql.setInt(i+1, (Integer)param);
				if (param instanceof Float) 
					sql.setFloat(i+1, (Float)param);
				if (param instanceof Boolean) 
					sql.setBoolean(i+1, (Boolean)param);
				if (param instanceof Date)	
					sql.setDate(i+1, (Date)param);
				if (param instanceof InputStream) {
					sql.setBinaryStream(i+1, (FileInputStream)param, (int)parameters[i+1]);
					i++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
