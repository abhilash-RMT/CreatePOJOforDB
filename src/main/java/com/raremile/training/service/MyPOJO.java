package com.raremile.training.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raremile.training.exceptions.NonFatalException;
import com.raremile.training.io.FileOperations;
import com.raremile.training.utils.DBUtils;

public class MyPOJO {
	private static final Logger LOG = LoggerFactory.getLogger(MyPOJO.class);

	private String DBName = null;
	Connection connection = null;
	ResultSet resultSet = null;
	DatabaseMetaData metadata = null;

	private List<String> tableList;
	private List<String> tableFieldsList;
	private List<String> tableFieldTypeList;

	public MyPOJO(String DBName) {
		this.DBName = DBName;
		this.tableList = new ArrayList<String>();
		this.tableFieldsList = new ArrayList<String>();
		this.tableFieldTypeList = new ArrayList<String>();
	}

	public String getDBName() {
		return DBName;
	}

	public void setDBName(String dBName) {
		DBName = dBName;
	}

	public DatabaseMetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(DatabaseMetaData metadata) {
		this.metadata = metadata;
	}

	public void createPOJO() throws NonFatalException {
		DBUtils dbUtils = new DBUtils(DBName);
		connection = dbUtils.getConnection();
		LOG.debug("Connected to DB Successfully");

		LOG.debug("fetching meta data of " + getDBName());
		try {
			metadata = connection.getMetaData();
		} catch (SQLException e) {
			LOG.error("Failed to fetch metadata from " + getDBName(), e);
			throw new NonFatalException("Failed to fetch metadata from " + getDBName(), e);
		}

		try {
			tableList = getTableNames();
			if (tableList.isEmpty()) {
				throw new NonFatalException("There are no tables in " + getDBName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// printList(tableList);

		for (String tableName : tableList) {
			try {
				getTableFieldProperties(tableName);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// printList(tableFieldsList);
			// printTable(tableFieldsList, tableFieldTypeList);

			FileOperations.createPOJOClass(tableName, tableFieldTypeList, tableFieldsList);
			tableFieldsList.clear();
			tableFieldTypeList.clear();

		}

	}

	private List<String> getTableNames() throws SQLException {
		LOG.debug("Fetching Table names from " + getDBName());

		String tableName = "";
		String[] types = { "TABLE" };
		resultSet = getMetadata().getTables(getDBName(), null, "%", types);

		while (resultSet.next()) {
			tableName = resultSet.getString(3);
			tableList.add(tableName);
		}

		return tableList;
	}

	private void getTableFieldProperties(String tableName) throws SQLException {
		String tableField = "";
		String tableFieldType = "";
		resultSet = getMetadata().getColumns(getDBName(), null, tableName, "%");

		while (resultSet.next()) {
			tableField = resultSet.getString(4);
			tableFieldType = resultSet.getString(6);
			tableFieldsList.add(tableField);
			tableFieldTypeList.add(tableFieldType);
		}

	}

	void printList(List<String> list) {
		for (String tableName : list) {
			System.out.println(tableName);
		}
	}

	void printTable(List<String> tableFieldsList, List<String> tableFieldTypeList) {
		for (int i = 0; i < tableFieldsList.size(); i++) {
			System.out.println(tableFieldTypeList.get(i) + " " + tableFieldsList.get(i));
		}
	}

}
