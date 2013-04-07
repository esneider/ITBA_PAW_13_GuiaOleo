package ar.edu.itba.it.paw.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static ConnectionManager self;
	private Properties config = new Properties();

	public synchronized static ConnectionManager getInstance() {
		if (self == null)
			self = new ConnectionManager();
		return self;
	}

	private ConnectionManager() {
		try {
			config.load(getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn;
		try {
			Class.forName(config.getProperty("db.class"));
			conn = DriverManager.getConnection(config.getProperty("db.url"),
					config.getProperty("db.user"),
					config.getProperty("db.pass"));
			return conn;
		} catch (SQLException e) {
			System.out.println("Connection error");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			e.printStackTrace();
		}
		return null;
	}

}
