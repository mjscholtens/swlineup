package model;

import java.awt.Color;

public class Rogue extends Player {
	
	private final String roleName = "Rogue";
	
	public Rogue (String name, int number, String country){
		super(name, number, 3, Color.cyan, Color.black, country);
	}
	
	public String getRoleName() {
		return roleName;
	}
}