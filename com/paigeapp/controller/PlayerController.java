package com.paigeapp.controller;

import com.paigeapp.database.PlayerDatabaseAccess;
import com.paigeapp.database.result.TableResult;

public class PlayerController {
	private PlayerDatabaseAccess playerDatabaseAccess = new PlayerDatabaseAccess();

	public TableResult getPlayers() {
		return this.playerDatabaseAccess.getPlayers();
	}
	
	public void addPlayer(String username, String password, String name, int age, double salary, String surname, int teamId) {
		this.playerDatabaseAccess.addPlayer(username, password, name, age, salary, surname, teamId);
	}

	public void deletePlayer(int id) {
		
	}
}
