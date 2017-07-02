package com.paigeapp.database.model;

/**
 * This is a simple class that models
 * a staff object that we get from the
 * database
 */
public class Staff {
	private int id;
	private String name;
	
	public Staff(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	public int hashCode() {
		return id;
	}
}
