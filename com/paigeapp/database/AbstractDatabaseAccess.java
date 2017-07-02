package com.paigeapp.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paigeapp.database.result.TableResult;
import com.paigeapp.util.DatabaseUtils;

abstract public class AbstractDatabaseAccess {
	protected Connection connection = DatabaseUtils.getConnection();
	
	protected List<Map<String, Object>> executeSelectQuery(String query) {
		try {
			//A statement is basically how you talk to your database
			Statement statement = connection.createStatement();
			//Result sets are what we get back from the database before converting it
			ResultSet resultSet = statement.executeQuery(query);
				
			//This is what we expect our final results to be. An array, containing maps of string/Object pairs
			List<Map<String, Object>> finalResults = new ArrayList<>();
			
			//Check if the result set has a next row to retrieve
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				
				//Just how we get the column names when using this kind of low-level database interaction
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				//Column and value indexes start at 1, for some unkown reason
				for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
					//Here we loop through the total number of column in the result. For each column, 
					//we get the column name and its corresponding value, and add it to the map
					row.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
				}
				
				//Finally, we add the map we created above to the final results
				finalResults.add(row);
			}
			
			//Remember, as with files, we need to close any open connections to the database
			statement.close();
			resultSet.close();
			
			return finalResults;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	protected TableResult executeSelectQueryForTable(String query) {
		try {
			//A statement is basically how you talk to your database
			Statement statement = connection.createStatement();
			//Result sets are what we get back from the database before converting it
			ResultSet resultSet = statement.executeQuery(query);
			//Just how we get the column names when using this kind of low-level database interaction
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				
			//This is what we expect our final results to be. An array, containing maps of string/Object pairs
			TableResult finalResults = new TableResult();
			
			//Let's set up the columns first
			int colCount = resultSetMetaData.getColumnCount();
			
			String[] columns = new String[colCount];
			
			for (int i = 1; i <= colCount; i++) {
				columns[i - 1] = resultSetMetaData.getColumnLabel(i);
			}
			
			finalResults.setColumns(columns);
			
			//Now let's do the actual rows
			List<Object[]> rows = new ArrayList<>();
			
			//Check if the result set has a next row to retrieve
			while (resultSet.next()) {
				//List works like a typed array, with infinite bounds
				List<Object> row = new ArrayList<>();
				
				for (int i = 1; i <= colCount; i++) {
					//Here we loop through the total number of column in the result. For each column, 
					//we get its corresponding value, and add it to the list
					row.add(resultSet.getObject(i));
				}
				
				//Finally, we add the map we created above to the final results
				rows.add(row.toArray());
			}
			
			Object[][] finalRows = new Object[rows.size()][colCount];
			
			for (int i = 0; i < rows.size(); i++) {
				finalRows[i] = rows.get(i);
			}
			
			finalResults.setRows(finalRows);
			
			//Remember, as with files, we need to close any open connections to the database
			statement.close();
			resultSet.close();
			
			return finalResults;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Do any kind of "modifying" query on the database
	 * 
	 * @param query
	 */
	protected void executeUpdateQuery(String query) {
		try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate(query);
			
			statement.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
