package com.paigeapp.database;

import java.util.ArrayList;
import java.util.List;

import com.paigeapp.database.model.Team;
import com.paigeapp.database.result.TableResult;

public class TeamDatabaseAccess extends AbstractDatabaseAccess {
	public TableResult getTeams() {
		String query = "SELECT t.ID, t.TEAM_NAME, s.NAME FROM TEAM AS t INNER JOIN USER AS s ON (s.ID = t.COACH_ID)";
		
		TableResult tableResult = this.executeSelectQueryForTable(query);
		
		return tableResult;
	}
	
	public void addTeam(String teamName, int coachId) {
		String query = "INSERT INTO TEAM (TEAM_NAME, COACH_ID) VALUES ('" + teamName + "', " + coachId + ")";
		
		this.executeUpdateQuery(query);
	}
	
	public void updateTeam(int id, String teamName, int coachId) {
		String query = "UPDATE TEAM SET TEAM_NAME = '" + teamName + "', COACH_ID = " + coachId + " WHERE ID = " + id;
		
		this.executeUpdateQuery(query);
	}
	
	public Object[] findTeam(int id) {
		String query = "SELECT t.ID, t.TEAM_NAME, t.COACH_ID, s.NAME FROM TEAM AS t INNER JOIN USER AS s ON (s.ID = t.COACH_ID) WHERE t.ID = " + id;
		
		TableResult result = this.executeSelectQueryForTable(query);
		
		return result.getRows()[0];
	}

	public List<Team> getTeamsAsModel() {
		TableResult teams = getTeams();
		List<Team> teamList = new ArrayList<>();
		
		for (Object[] team : teams.getRows()) {
			teamList.add(new Team((int) team[0], (String) team[1]));
		}
		
		return teamList;
	}
}
