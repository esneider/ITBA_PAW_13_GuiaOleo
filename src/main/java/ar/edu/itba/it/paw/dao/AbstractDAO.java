package ar.edu.itba.it.paw.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ar.edu.itba.it.paw.database.ConnectionManager;

public abstract class AbstractDAO {
	
	private static Logger logger = Logger.getLogger(AbstractDAO.class);
	
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
			logger.error("SQL Error");
		}
		return null;
		
	}
	
	protected PreparedStatement execute(String query, Object... parameters) {
		try {	
			PreparedStatement sql = conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				setSQLParameters(sql, parameters);
				
				sql.execute();
				return sql;
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return null;
	}
	
	protected void executeUpdate(String query, Object... parameters) {
		try {	
			PreparedStatement sql = conn.getConnection().prepareStatement(query);
				setSQLParameters(sql, parameters);
				sql.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQL Error");
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

					byte[] buffer = new byte[4096];

					ByteArrayOutputStream os = new ByteArrayOutputStream();
					int bytesRead;
					while ((bytesRead = ((InputStream)param).read(buffer)) != -1) {  
					  os.write(buffer, 0, bytesRead);  
					}
					sql.setBytes(i+1, os.toByteArray());
				}
			}
		} catch (SQLException e) {
			logger.error("SQL Error");
		} catch (IOException e) {
			logger.error("IO Error");
		}
	}
}