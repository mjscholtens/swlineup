package model;

import java.awt.Color;

public class Mdd extends Player {
	
	private final String roleName = "Mdd";
	
	public Mdd (String name, int number, String country){
		super(name, number, 5, Color.yellow, Color.black, country);
	}
	
	public String getRoleName() {
		return roleName;
	}
}