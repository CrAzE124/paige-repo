package com.paigeapp.database;

import com.paigeapp.database.result.TableResult;

public class FixturesDatabaseAccess extends AbstractDatabaseAccess {
	public TableResult getFixtures() {
		String query = "SELECT f.id AS ID, ta.TEAM_NAME AS TEAM_A, tb.TEAM_NAME AS TEAM_B, f.date_played AS DATE_PLAYED, f.team_a_result AS TEAM_A_RESULT, f.team_b_result AS TEAM_B_RESULT FROM fixture f INNER JOIN TEAM AS ta ON (f.team_a = ta.ID) INNER JOIN TEAM AS tb ON (f.team_b = tb.ID)";
		
		TableResult result = this.executeSelectQueryForTable(query);
		
		return result;
	}
	
	public void saveFixture(int teamA, int teamB, int teamAResult, int teamBResult, String datePlayed) {
		String query = "INSERT INTO fixture (team_a, team_b, team_a_result, team_b_result, date_played) VALUES (" + 
						String.valueOf(teamA) + ", " + 
						String.valueOf(teamB) + ", " + 
						String.valueOf(teamAResult) + ", " + 
						String.valueOf(teamBResult) + ", " + 
						"\"" + datePlayed + "\"" +
					")";
		
		this.executeUpdateQuery(query);
	}

	public void deleteFixture(int selectedFixtureId) {
		String query = "DELETE FROM fixture WHERE id = " + selectedFixtureId;
		
		this.executeUpdateQuery(query);
	}

	public void updateFixture(int id, int teamA, int teamB, int teamAResult, int teamBResult, String date) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE fixture SET ")
			.append("team_a = ").append(teamA)
			.append(", team_b = ").append(teamB)
			.append(", team_a_result = ").append(teamAResult)
			.append(", team_b_result = ").append(teamBResult)
			.append(", date_played = \"").append(date).append("\"")
			.append(" WHERE id = ").append(id);
		
		this.executeUpdateQuery(sb.toString());
	}

	public Object[] getFixture(int id) {
		String query = "SELECT id, team_a, team_b, team_a_result, team_b_result, date_played FROM fixture WHERE id = " + id;
		
		return this.executeSelectQueryForTable(query).getRows()[0];
	}
}
