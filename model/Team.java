package model;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Team extends Observable {
	
	private String name;
	private List<Player> players = new ArrayList<Player>();
	private boolean[] numbers = new boolean[99];
	private boolean toggleStatus = true;
	public boolean fontSetting = true; // true linux, false windows	
	
	public Team(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers();
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public boolean[] getNumbers() {
		return numbers;
	}
	
	public void setNumberTrue(int number) {
		numbers[number-1] = true;
		setChanged();
		notifyObservers();
	}
	
	public void setNumberFalse(int number) {
		numbers[number-1] = false;
		setChanged();
		notifyObservers();
	}
	
	/*public void editPlayer() {
		// perform edits
		
		setChanged();
		notifyObservers();
	}*/
	
	public Player findDiskPlayer(Disk d) {
		Player player = null;		
		for(Player p : players) {
			if(p.getDisk() == d) {
				return p;
			}
		}		
		return player;
	}
	
	public void setStatusItem(Disk d, int item) {
		System.out.println("finding disk");
		Player p = findDiskPlayer(d);
		System.out.println("found disk");
		if(p != null) {
			switch(item) {
			case 1: System.out.println("setting disk"); p.getDisk().setAoe(); System.out.println("disk got set"); break;
			case 2: p.getDisk().setCaptain(); break;
			case 3: p.getDisk().setFarmer(); break;
			case 4: p.getDisk().setHerald(); break;
			case 5: p.getDisk().setUpgrader(); break;
			}
		}			
		setChanged();
		notifyObservers();
	}
	
	public void setToggleStatus() {
		toggleStatus = !toggleStatus;
		setChanged();
		notifyObservers();
	}
	
	public boolean getToggleStatus() {
		return toggleStatus;
	}
	
	public void setFontSetting() {
		fontSetting = !fontSetting;
		setChanged();
		notifyObservers();
	}
	
	public boolean getFontSetting() {
		return fontSetting;
	}
	
	public void sortTeam() {
		Collections.sort(players,new TeamSort());
		setChanged();
		notifyObservers();
	}
	
	public void turnLineup(double width, double height) {
		for(Player p : players) {
			if(p.diskSet) {
				p.getDisk().setX(width - p.getDisk().getX() - 25);
				p.getDisk().setY(height - p.getDisk().getY() - 125);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public Player findNumberPlayer(int number) {
		Player player = null;		
		for(Player p : players) {
			if(p.getNumber() == number) {
				return p;
			}
		}		
		return player;
	}
	
	public void setPosition(Disk d, Point p){
		d.setPosition(p);
		setChanged();
		notifyObservers();
	}
	
	public void setSelectedDisk(Disk d, Boolean b){
		d.setSelected(b);
		setChanged();
		notifyObservers();
	}
	
	public void setRightSelected(Disk d, boolean b) {
		d.setRightSelected(b);
		setChanged();
		notifyObservers();
	}
	
	public void removeAllDisks() {
		for(Player p : players) {
			p.hideDisk();
		}
		setChanged();
		notifyObservers();
	}
	
	public void removeDisk(int number) {
		Player player = findNumberPlayer(number);
		if(player != null) {
			player.hideDisk();
		}
		setChanged();
		notifyObservers();
	}
	
	public void addDisk(int number, double x, double y) {
		if(numbers[number-1] == false) {
			JOptionPane.showMessageDialog(null, "Error: there is no player with number " + number + ".");
		} else {
			Player diskPlayer = findNumberPlayer(number);
			if(diskPlayer == null) {
				JOptionPane.showMessageDialog(null, "Error: Player is null.");
			} else {
				diskPlayer.setNewDisk((int) x, (int) y);
				setChanged();
				notifyObservers();
			}
		}
	}
	
	public boolean addPlayer(String playername, int number, int role, String flag){
		if(numbers[number-1] == true) {
			JOptionPane.showMessageDialog(null, "Error: number " + number + " has already been taken");
		} else {
			setNumberTrue(number);
			switch(role){
				case 1: players.add(new Tank(playername, number, flag));
					break;
				case 2: players.add(new Healer(playername, number, flag));
					break;
				case 3: players.add(new Rogue(playername, number, flag));
					break;
				case 4: players.add(new Scout(playername, number, flag));
					break;
				case 5: players.add(new Mdd(playername, number, flag));
					break;
				case 6: players.add(new Pdd(playername, number, flag));
					break;
				default: players.add(new Alt(playername, number, flag));
					break;
			}
			sortTeam();
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
	
	public void removePlayer(int number){
		setNumberFalse(number);
		Player player = findNumberPlayer(number);
		if(player != null) {
			players.remove(player);
			sortTeam();
			setChanged();
			notifyObservers();
			return;
		}
	}
	
	public void saveLineup() {
		String fileName = name + "lineup.txt";
		BufferedWriter writer = null;
		JFileChooser saveFile = new JFileChooser();
		saveFile.setSelectedFile(new File(fileName));
		int sf = saveFile.showSaveDialog(saveFile);
		
		if(sf == JFileChooser.APPROVE_OPTION) {
			try {
				writer = new BufferedWriter(new FileWriter(saveFile.getSelectedFile()));
				for(Player p : players) {
					if(p.getDiskSet()) {
						writer.write(p.getNumber() + " " + p.getDisk().getX() + " " + p.getDisk().getY());
						writer.write("\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Saving line-up failed: Exception 1 (Bad file).");
			} finally {
				try {
					if(writer != null) {
						writer.close();
						JOptionPane.showMessageDialog(null, "Saving line-up successful.");
					}
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Saving line-up failed: Exception 2 (Failed close).");
				}
			}			
		} else if(sf == JFileChooser.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null, "Saving line-up failed: cancelled.");
		}
	}
	
	public void saveTeam() {
		String fileName = name + ".txt";
		BufferedWriter writer = null;
		JFileChooser saveFile = new JFileChooser();
		saveFile.setSelectedFile(new File(fileName));
		int sf = saveFile.showSaveDialog(saveFile);
		
		if(sf == JFileChooser.APPROVE_OPTION) {
			try {
				writer = new BufferedWriter(new FileWriter(saveFile.getSelectedFile()));
				for(Player p : players) {
					if(p.getCountry() != null) {
						writer.write(p.getNumber() + " " + p.getName() + " " + p.getRoleName() + " " + p.getCountry());
					} else {
						writer.write(p.getNumber() + " " + p.getName() + " " + p.getRoleName());
					}
					writer.write("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Saving team failed: Exception 1 (Bad file).");
			} finally {
				try {
					if(writer != null) {
						writer.close();
						JOptionPane.showMessageDialog(null, "Saving team successful.");
					}
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Saving team failed: Exception 2 (Failed close).");
				}
			}			
		} else if(sf == JFileChooser.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null, "Saving team failed: cancelled.");
		}
	}
}
