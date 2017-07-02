package com.paigeapp.controller;

import com.paigeapp.database.FixturesDatabaseAccess;
import com.paigeapp.database.result.TableResult;

public class FixturesController {
	private FixturesDatabaseAccess fixturesDatabaseAccess;
	
	public FixturesController() {
		this.fixturesDatabaseAccess = new FixturesDatabaseAccess();
	}
	
	public Object[] getFixture(int id) {
		return this.fixturesDatabaseAccess.getFixture(id);
	}
	
	public void saveFixture(int teamA, int teamB, int teamAResult, int teamBResult, String datePlayed) {
		this.fixturesDatabaseAccess.saveFixture(teamA, teamB, teamAResult, teamBResult, datePlayed);
	}
	
	public TableResult getFixtures() {
		return this.fixturesDatabaseAccess.getFixtures();
	}

	public void deleteFixture(int selectedFixtureId) {
		this.fixturesDatabaseAccess.deleteFixture(selectedFixtureId);
	}

	public void updateFixture(int id, int teamA, int teamB, int teamAResult, int teamBResult, String date) {
		this.fixturesDatabaseAccess.updateFixture(id, teamA, teamB, teamAResult, teamBResult, date);
	}
}
