package model;

import java.awt.Color;

public class Scout extends Player {
	
	private final String roleName = "Scout";
	
	public Scout (String name, int number, String country){
		super(name, number, 4, Color.green, Color.black, country);
	}
	
	public String getRoleName() {
		return roleName;
	}
}