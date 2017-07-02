package com.paigeapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {
	private static final String DATABASE_PATH = "/home/thomas/workspace/paige_app/database.db";
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				//See if we correctly imported the SQLite driver JAR
				Class.forName("org.sqlite.JDBC");
				//Let's get that connection 
				connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
				//Turn auto-commit on so we don't need to call '.commit()' after each insert/update/delete
				connection.setAutoCommit(true);
				//And do some logging
				System.out.println("Connected to " + DATABASE_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
}
