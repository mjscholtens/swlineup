package model;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import core.Country;
import core.CountryDet;

@SuppressWarnings("unused")
public abstract class Player {
	protected String name;
	protected int number;
	protected int role;
	protected Color diskcolor;
	protected Color fontcolor;
	protected String country = null;
	protected Disk disk;
	protected boolean diskSet = false;
	private final String roleName = "Player";
	
	public Player(String name, int number, int role, Color diskcolor, Color fontcolor) {
		this.name = name;
		this.number = number;
		this.role = role;
		this.diskcolor = diskcolor;
		this.fontcolor = fontcolor;
	}
	
	public Player(String name, int number, int role, Color diskcolor, Color fontcolor, String country) {
		this.name = name;
		this.number = number;
		this.role = role;
		this.diskcolor = diskcolor;
		this.fontcolor = fontcolor;
		if(country != null) {
			if(contains(country)) {
				this.country = country;
			}
		}
	}
	
	public static boolean contains(String value) {
		for(Country c : Country.values()) {
			if(c.name().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	public Color getDiskcolor() {
		return diskcolor;
	}
	
	public void setDiskcolor(Color diskcolor) {
		this.diskcolor = diskcolor;
	}
	
	public Color getFontcolor() {
		return fontcolor;
	}
	
	public void setFontcolor(Color fontcolor) {
		this.fontcolor = fontcolor;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setNewDisk(int x, int y) {
		disk = new Disk(x, y);
		diskSet = true;
	}
	
	public void hideDisk() {
		diskSet = false;
	}
	
	public Disk getDisk() {
		return disk;
	}
	
	public boolean getDiskSet() {
		return diskSet;
	}
	
	// use eclipse
	public Image getCountryImg() {
		if(country == null) {
			return null;
		}
		CountryDet det = new CountryDet();
		return det.fetchCountryAbbrv(country);
	}
	
	// use jar
	/*public ImageIcon getCountryImg() {
		if(country == null) {
			return null;
		}
		CountryDet det = new CountryDet();
		return det.fetchCountryAbbrv(country);
	}*/
}
