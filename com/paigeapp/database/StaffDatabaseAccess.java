package com.paigeapp.database;

import java.util.ArrayList;
import java.util.List;

import com.paigeapp.database.model.Staff;
import com.paigeapp.database.result.TableResult;

public class StaffDatabaseAccess extends AbstractDatabaseAccess {
	public TableResult getStaff() {
		String query = "SELECT ID, USERNAME, NAME, AGE, SALARY FROM USER WHERE USER_TYPE = 'STAFF'";
		
		TableResult staff = this.executeSelectQueryForTable(query);
		
		return staff;

	}
	
	public List<Staff> getStaffAsModel() {
		TableResult staff = getStaff();
		List<Staff> staffList = new ArrayList<>();
		
		for(Object[] row : staff.getRows()) {
			staffList.add(new Staff((int) row[0], (String) row[2]));
		}
		
		return staffList;
	}
	
	public Object[] getStaffById(int staffId) {
		String query = "SELECT USERNAME, PASSWORD, NAME, SURNAME, AGE, POSITION, SALARY, YEARS_PRESENT FROM USER WHERE ID = " + staffId;
		
		TableResult tableResult = this.executeSelectQueryForTable(query);
		
		return tableResult.getRows()[0];
	}
	
	public void deleteStaffID(int StaffID) {
		String query = "DELETE FROM USER WHERE ID = " + StaffID;
		
		this.executeUpdateQuery(query);
	}
	
	public void addStaffMember(String username, String password, String name, String surname, int age, String position, double salary, int yearsPresent) {
		String query = "INSERT INTO USER (USERNAME, PASSWORD, NAME, SURNAME, AGE, POSITION, SALARY, YEARS_PRESENT, USER_TYPE) VALUES ("
				+ "'" + username + "', '" + password + "', '" + name + "', '" + surname + "', " + age + ", '" + position + "', " + salary + ", " + yearsPresent
				+ ", 'STAFF')";
		
		System.out.println(query);
		
		this.executeUpdateQuery(query);
	}
	
	public void updateStaffMember(int staffId, String username, String password, String name, String surname, int age, String position, double salary, int yearsPresent) {
		String query = "UPDATE USER SET "
				+ "USERNAME = '" + username + "', "
				+ "PASSWORD = '" + password + "', "
				+ "NAME = '" + name + "', "
				+ "SURNAME = '" + surname + "', "
				+ "AGE = " + age + ", "
				+ "POSITION = '" + position + "', "
				+ "SALARY = " + salary + ", "
				+ "YEARS_PRESENT = " + yearsPresent 
				+ " WHERE ID = " + staffId;
		
		System.out.println(query);
		
		this.executeUpdateQuery(query);
	}
}
