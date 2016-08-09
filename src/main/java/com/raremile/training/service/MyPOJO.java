package com.raremile.training.service;

import java.sql.Connection;

import com.raremile.training.utils.DBUtils;

public class MyPOJO {
	private String DBName = null;
	Connection connection = null;

	public MyPOJO(String DBName) {
		this.DBName = DBName;
	}
	
	public void createPOJO(){
		DBUtils dbUtils = new DBUtils(DBName);
		connection = dbUtils.getConnection();
	}

}
