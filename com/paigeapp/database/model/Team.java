package com.paigeapp.database.model;

public class Team {
	private int id;
	private String name;
	
	public Team(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return this.name;
	}
	
	public int hashCode() {
		return this.id;
	}
	
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		
		if (obj.getClass() != Team.class) {
			return false;
		}
		
		return ((Team) obj).getId() == id;
	}
}
