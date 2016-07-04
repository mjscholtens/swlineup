package model;

import java.awt.Color;

public class Pdd extends Player {
	
	private final String roleName = "Pdd";
	
	public Pdd (String name, int number, String country){
		super(name, number, 6, Color.red, Color.white, country);
	}
	
	public String getRoleName() {
		return roleName;
	}
}