package model;

import java.awt.Point;

public class Disk {
	
	private double x;
	private double y;
	private double r = 20; // this is the diameter, not the radius
	private Boolean selected = false;
	private Boolean rightSelected = false;
	private boolean herald = false;
	private boolean captain = false;
	private boolean aoe = false;
	private boolean upgrader = false;
	private boolean farmer = false;
	private int status = 0;
	
	public Disk(double x, double y) {
		this.x = x - (r/2);
		this.y = y - (r/2);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getR() {
		return r;
	}
	
	public void setR(double r) {
		this.r = r;
	}
	
	public void setPosition(Point p){
		x = p.getX();
		y = p.getY();
	}
	
	public void setSelected(Boolean b){
		selected = b;
	}
	
	public boolean getSelected(){
		return selected;
	}
	
	public void setHerald() {
		herald = !herald;
		setStatus(herald);
	}
	
	public boolean getHerald() {
		return herald;
	}
	
	public void setCaptain() {
		captain = !captain;
		setStatus(captain);
	}
	
	public boolean getCaptain() {
		return captain;
	}
	
	public void setAoe() {
		aoe = !aoe;
		setStatus(aoe);
	}
	
	public boolean getAoe() {
		return aoe;
	}
	
	public void setUpgrader() {
		upgrader = !upgrader;
		setStatus(upgrader);
	}
	
	public boolean getUpgrader() {
		return upgrader;
	}
		
	public void setFarmer() {
		farmer = !farmer;
		setStatus(farmer);
	}
	
	public boolean getFarmer() {
		return farmer;
	}
	
	public void setStatus(boolean b) {
		if(b) {
			++status;
		} else {
			--status;
		}
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setRightSelected(Boolean b) {
		rightSelected = b;
	}
	
	public Boolean getRightSelected() {
		return rightSelected;
	}
	
	public boolean contains(Point p) {
		double xCenter = x + r;
		double yCenter = y + r;
		double hypotenuse = Math.sqrt(Math.pow(xCenter - p.getX(), 2) + Math.pow(yCenter - p.getY(), 2));
		if(hypotenuse < r) {
			return true;
		}
		return false;
	}
}
