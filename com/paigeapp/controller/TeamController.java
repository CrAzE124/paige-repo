package com.paigeapp.controller;

import java.util.List;

import com.paigeapp.database.TeamDatabaseAccess;
import com.paigeapp.database.model.Team;
import com.paigeapp.database.result.TableResult;

public class TeamController {
	private TeamDatabaseAccess teamDatabaseAccess = new TeamDatabaseAccess();
	
	public TableResult getTeams() {
		return teamDatabaseAccess.getTeams();
	}
	
	public List<Team> getTeamsAsModel() {
		return teamDatabaseAccess.getTeamsAsModel();
	}
	
	public Object[] getTeam(int id) {
		return teamDatabaseAccess.findTeam(id);
	}
	
	public void addTeam(String teamName, int coachId) {
		teamDatabaseAccess.addTeam(teamName, coachId);
	}
	
	public void updateTeam(int id, String teamName, int coachId) {
		teamDatabaseAccess.updateTeam(id, teamName, coachId);
	}
}
