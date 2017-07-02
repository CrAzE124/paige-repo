package com.paigeapp.database;

import java.util.List;
import java.util.Map;

import com.paigeapp.database.result.TableResult;

public class UserDatabaseAccess extends AbstractDatabaseAccess {
	public Map<String, Object> getUserByUsername(String username) {
		String query = "SELECT USERNAME, PASSWORD, NAME, AGE FROM USER WHERE USERNAME = '" + username + "'";
		
		List<Map<String, Object>> results = this.executeSelectQuery(query);
		
		if (results.size() > 0) {
			return results.get(0);
		} else {
			throw new RuntimeException("No user with username " + username + " found");
		}
	}
	
	public TableResult getUsers() {
		String query = "SELECT ID, USERNAME, NAME, AGE FROM USER";
		
		TableResult tableResult = this.executeSelectQueryForTable(query);
		
		return tableResult;
	}
	
	public void deleteUserId(int userId) {
		String query = "DELETE FROM USER WHERE ID = " + userId;
		
		this.executeUpdateQuery(query);
	}
	
	public void addUser(String username, String password, String name, int age, double salary) {
		String query = 
				"INSERT INTO USER (USERNAME, PASSWORD, NAME, AGE, SALARY) VALUES ("
				+ "'" + username + "', '" + password + "', '" + name + "', " + age + ", " + salary
				+ ");";
		
		this.executeUpdateQuery(query);
	}

	public Object[] getUserById(int userId) {
		String query = "SELECT USERNAME, PASSWORD, NAME, AGE, SALARY FROM USER WHERE ID = " + userId;
		
		TableResult tableResult = this.executeSelectQueryForTable(query);
		
		return tableResult.getRows()[0];
	}

	public void updateUser(int id, String username, String password, String name, int age, double salary) {
		String query = "UPDATE USER SET USERNAME = '" + username + "', PASSWORD = '" + password + "', NAME = '" + name + "', AGE = " + age + ", SALARY = " + salary + " WHERE ID = " + id;
		System.out.println(query);
		this.executeUpdateQuery(query);		
	}
}
