package com.wang.utils;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;





public class JDBCUtils {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/shop";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "123";
	private static BasicDataSource datasource = new BasicDataSource();
	
	static {
		datasource.setDriverClassName(DRIVER_CLASS_NAME);
		datasource.setUrl(URL);
		datasource.setUsername(USERNAME);
		datasource.setPassword(PASSWORD);
	}

	public static DataSource getdatasource() {
		return datasource;
	}

	
}
