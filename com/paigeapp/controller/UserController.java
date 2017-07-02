package com.paigeapp.controller;

import java.util.Map;

import com.paigeapp.database.PlayerDatabaseAccess;
import com.paigeapp.database.UserDatabaseAccess;
import com.paigeapp.database.result.TableResult;

public class UserController {
	private UserDatabaseAccess userDatabaseAccess;
	private PlayerDatabaseAccess playerDatabaseAccess;
	
	public UserController() {
		this.userDatabaseAccess = new UserDatabaseAccess();
		this.playerDatabaseAccess = new PlayerDatabaseAccess();
	}
	
	public TableResult getUsers() {
		return userDatabaseAccess.getUsers();
	}
	
	public Map<String, Object> getUserByUsername(String username) {
		return userDatabaseAccess.getUserByUsername(username);
	}
	
	public Object[] getUserById(int userId) {
		return userDatabaseAccess.getUserById(userId);
	}
	
	public void deleteUser(int userId) {
		System.out.println("Going to delete user ID " + userId);
		
		this.userDatabaseAccess.deleteUserId(userId);
	}
	
	public void addUser(String username, String password, String name, int age, double salary) {
		userDatabaseAccess.addUser(username, password, name, age, salary);
	}
	
	public void addPlayer(String username, String password, String name, int age, double salary, String surname, int teamId) {
		this.playerDatabaseAccess.addPlayer(username, password, name, age, salary, surname, teamId);
	}
	
	public void updateUser(int id, String username, String password, String name, int age, double salary) {
		userDatabaseAccess.updateUser(id, username, password, name, age, salary);
	}
}
