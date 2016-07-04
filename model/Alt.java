package model;

import java.awt.Color;

public class Alt extends Player {
	
	private final String roleName = "Alt";
	
	public Alt (String name, int number, String country){
		super(name, number, 7, Color.magenta, Color.white, country);
	}
	
	public String getRoleName() {
		return roleName;
	}
}