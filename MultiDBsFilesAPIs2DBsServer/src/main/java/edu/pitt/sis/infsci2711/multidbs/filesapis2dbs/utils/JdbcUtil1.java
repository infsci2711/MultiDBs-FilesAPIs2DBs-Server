package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil1 {
	public static final String DEFAULT_HOST = "localhost";
	
	public static final int DEFAULT_PORT = 3306;
	
	public static final String DEFAULT_USER = "root"; //CHANGE TO YOUR MYSQL USER NAME
	
	public static final String DEFAULT_PASSWOD = "12345"; // CHANGE TO YOUR MYSQL PASSWORD
	
	public static final String DEFAULT_DATABASE = "infsci2711_filesapis2dbs";
	
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection(getConnectionString(), DEFAULT_USER, DEFAULT_PASSWOD);
	}
	
	public static String getConnectionString() {
		return String.format("jdbc:mysql://%s:%d/%s", DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DATABASE);
	}
}