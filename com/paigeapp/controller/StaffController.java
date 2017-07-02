package com.paigeapp.controller;

import java.util.List;

import com.paigeapp.database.StaffDatabaseAccess;
import com.paigeapp.database.model.Staff;
import com.paigeapp.database.result.TableResult;

public class StaffController {
	StaffDatabaseAccess staffDatabaseAccess = new StaffDatabaseAccess();
	
	public TableResult getStaff() {
		return staffDatabaseAccess.getStaff();
	}
	
	public void deleteStaff(int staffId) {
		staffDatabaseAccess.deleteStaffID(staffId);
	}
	
	public void addStaffMember(String username, String password, String name, String surname, int age, String position, double salary, int yearsPresent) {
		this.staffDatabaseAccess.addStaffMember(username, password, name, surname, age, position, salary, yearsPresent);
	}
	
	public void updateStaffMember(int staffId, String username, String password, String name, String surname, int age, String position, double salary, int yearsPresent) {
		this.staffDatabaseAccess.updateStaffMember(staffId, username, password, name, surname, age, position, salary, yearsPresent);
	}
	
	public Object[] getStaffMember(int staffId) {
		return this.staffDatabaseAccess.getStaffById(staffId);
	}
	
	public List<Staff> getStaffAsModel() {
		return this.staffDatabaseAccess.getStaffAsModel();
	}
}
