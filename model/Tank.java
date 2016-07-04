package model;

import java.awt.Color;

public class Tank extends Player {
	
	private final String roleName = "Tank";
	
	public Tank (String name, int number, String country){
		super(name, number, 1, Color.black, Color.white, country);
	}
	
	public String getRoleName() {
		return roleName;
	}
}
