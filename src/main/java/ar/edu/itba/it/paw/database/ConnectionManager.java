package ar.edu.itba.it.paw.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import ar.edu.itba.it.paw.exceptions.SQLNoConnectionException;

public class ConnectionManager {

	private static ConnectionManager self;
	private static Properties config = new Properties();
	private static Logger logger = Logger.getLogger(ConnectionManager.class);
	
	private Connection conn;

	public synchronized static ConnectionManager getInstance() {
		if (self == null)
			self = new ConnectionManager();
		return self;
	}

	private ConnectionManager() {
		Connection conn = null;
		try {
			config.load(getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			logger.error("IO Error @ Connection Manager");
		}
		try {
			Class.forName(config.getProperty("db.class"));
			conn = DriverManager.getConnection(config.getProperty("db.url"),
					config.getProperty("db.user"),
					config.getProperty("db.pass"));
		} catch (SQLException e) {
			logger.error("Connection Error in Connection Manager - ¿DB Disconnected ?");
			throw new SQLNoConnectionException();

		} catch (ClassNotFoundException e) {
			logger.error("Class Not Found");
		}
		this.conn = conn;
	}

	public Connection getConnection() {
		return conn;
	}

}
