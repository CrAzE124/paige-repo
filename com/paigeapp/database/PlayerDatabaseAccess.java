package com.paigeapp.database;

import com.paigeapp.database.result.TableResult;

public class PlayerDatabaseAccess extends AbstractDatabaseAccess {
	public TableResult getPlayers() {
		String query = "SELECT u.ID, u.NAME, u.AGE, u.SURNAME, t.TEAM_NAME FROM USER AS u INNER JOIN TEAM AS t ON (t.ID = u.TEAM_ID) WHERE u.USER_TYPE = 'PLAYER'";
		
		return this.executeSelectQueryForTable(query);
	}
	
	public void addPlayer(String username, String password, String name, int age, double salary, String surname, int teamId) {
		String query = "INSERT INTO USER (USERNAME, PASSWORD, NAME, AGE, SALARY, USER_TYPE, SURNAME, TEAM_ID) VALUES ("
				+ "\"" + username + "\", "
				+ "\"" + password + "\", "
				+ "\"" + name + "\", "
				+ age + ", "
				+ salary + ", "
				+ "\"PLAYER\", "
				+ "\"" + surname + "\", "
				+ teamId
				+ ")";
		
		this.executeUpdateQuery(query);
	}
}
