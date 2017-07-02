package com.paigeapp.controller;

import java.util.Map;

import com.paigeapp.database.UserDatabaseAccess;

public class LoginController {
	private UserDatabaseAccess userDatabaseAccess = new UserDatabaseAccess();
	
	public boolean isUsernamePasswordValid(String username, String unencryptedPassword) {
		Map<String, Object> userMap = userDatabaseAccess.getUserByUsername(username);
		
		if (!userMap.get("PASSWORD").equals(unencryptedPassword)) {
			throw new RuntimeException("Passwords don't match");
		}
		
		return true;
	}
}
