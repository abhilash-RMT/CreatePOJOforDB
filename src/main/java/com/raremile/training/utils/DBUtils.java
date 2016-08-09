package com.raremile.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raremile.training.exceptions.FatalException;

public class DBUtils {
	private static final Logger LOG = LoggerFactory.getLogger(DBUtils.class);

	private String DBName = null;
	Connection connection = null;

	static {
		LOG.debug("Loading MySQL driver");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOG.error("Could not load the driver", e);
		}
	}

	public DBUtils(String DBName) {
		this.DBName = DBName;
	}

	public Connection getConnection() {
		LOG.debug("Fetching a connection");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DBName + "?user=root");
		} catch (SQLException e) {
			LOG.error("Failed to get a connection", e);
			throw new FatalException("Please check the DB name");
		}
		return connection;
	}

}
