package model;

import java.awt.Color;

public class Healer extends Player {
	
	private final String roleName = "Healer";
	
	public Healer (String name, int number, String country){
		super(name, number, 2, Color.blue, Color.white, country);
	}
	
	public String getRoleName() {
		return roleName;
	}
}