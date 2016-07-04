package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Disk;
import model.Team;

public class SelectionController extends MouseAdapter {
	
	private Team team;
	private Disk selectedDisk;
	private Disk testDisk;
	private int width;
	private int height;
	private JPopupMenu popup;
	private JMenuItem item;
	private JLabel label;
	private int activeNumber = 0;
	
	public SelectionController(Team team, int width, int height) {
		this.team = team;
		selectedDisk = null;
		this.width = width;
		this.height = height;
	}
	
	private void invokeMenu(String name, MouseEvent e, double x, double y, double r) {		
		popup = new JPopupMenu();
		label = new JLabel("  " + name);
		popup.add(label);
		popup.addSeparator();
		String toggled = "\u2715";
		
		if(testDisk != null) {
			if(testDisk.getAoe()) {
				toggled = "\u2713";
			}
			item = new JMenuItem(toggled + " AOE (A)");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//testDisk.setAoe();
					System.out.println("Setting aoe status");
					team.setStatusItem(testDisk, 1);
					System.out.println("Status aoe set");
					activeNumber = 0;
					team.setRightSelected(testDisk, false);
				}
			});
			popup.add(item);
			
			if(testDisk.getCaptain()) {
				toggled = "\u2713";
			} else {
				toggled = "\u2715";
			}
			item = new JMenuItem(toggled + " Captain (C)");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//testDisk.setCaptain();
					team.setStatusItem(testDisk, 2);
					activeNumber = 0;
					team.setRightSelected(testDisk, false);
				}
			});
			popup.add(item);
			
			if(testDisk.getFarmer()) {
				toggled = "\u2713";
			} else {
				toggled = "\u2715";
			}
			item = new JMenuItem(toggled + " Farmer (F)");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//testDisk.setFarmer();
					team.setStatusItem(testDisk, 3);
					activeNumber = 0;
					team.setRightSelected(testDisk, false);
				}
			});
			popup.add(item);
			
			if(testDisk.getHerald()) {
				toggled = "\u2713";
			} else {
				toggled = "\u2715";
			}
			item = new JMenuItem(toggled + " Herald (H)");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//testDisk.setHerald();
					team.setStatusItem(testDisk, 4);
					activeNumber = 0;
					team.setRightSelected(testDisk, false);
				}
			});
			popup.add(item);
			
			if(testDisk.getUpgrader()) {
				toggled = "\u2713";
			} else {
				toggled = "\u2715";
			}
			item = new JMenuItem(toggled + " Upgrader (U)");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//testDisk.setUpgrader();
					team.setStatusItem(testDisk, 5);
					activeNumber = 0;
					team.setRightSelected(testDisk, false);
				}
			});
			popup.add(item);
			popup.addSeparator();
			
			item = new JMenuItem("Remove Disk");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					team.removeDisk(activeNumber);
					activeNumber = 0;
					team.setRightSelected(testDisk, false);
				}
			});
			popup.add(item);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		boolean selected = false;
		
		// check for right click, show pop up menu if on a disk
		if(SwingUtilities.isRightMouseButton(e)) {
			for(int i = 0; i < team.getPlayers().size(); ++i) {
				if(team.getPlayers().get(i).getDiskSet()) {
					// testDisk = team.getPlayers().get(i).getDisk();
					if(team.getPlayers().get(i).getDisk().contains(p)) {
						testDisk = team.getPlayers().get(i).getDisk();
						activeNumber = team.getPlayers().get(i).getNumber();
						team.setRightSelected(testDisk, true);
						invokeMenu(team.getPlayers().get(i).getName(), e, testDisk.getX(), testDisk.getY(), testDisk.getR());
						if(!e.isPopupTrigger() && !e.isConsumed()) {
							popup.show(e.getComponent(),
										(int) (testDisk.getX() + testDisk.getR()), (int) (testDisk.getY() + testDisk.getR()));
						}
						testDisk = null;
					}
				}
			}
		
		// check for double click, show new disk window
		} else if(e.getClickCount() == 2 && !e.isConsumed()) {
				e.consume();
				String numberString = JOptionPane.showInputDialog("Typ in the number of the player (1-99) to line up."
						+ " Leave empty to cancel.\n"
						+ "If the player is already on the field, then this will be the player's new position", null);
				if(numberString != null) {
					try {
						int number = Integer.parseInt(numberString);
						if(number > 0 && number < 100) {
							team.addDisk(number, p.getX(), p.getY());
						} else {
							JOptionPane.showMessageDialog(null, "Error: Number not in 1-99.");
						}
					} catch(NumberFormatException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error: Numberformatexception.");
					}
				}
		
		// check for any selected disk with a single left click
		} else {
			for(int i = 0; i < team.getPlayers().size(); ++i) {
				if(team.getPlayers().get(i).getDiskSet()) {
					testDisk = team.getPlayers().get(i).getDisk();
					if(testDisk.contains(p)) {
						if(selectedDisk != null) {
							team.setSelectedDisk(selectedDisk, false);
						}
						team.setSelectedDisk(testDisk, true);
						selectedDisk = testDisk;
						selected = true;
					}
				}
			}
		}
		
		// nothing has been selected
		if(!selected && selectedDisk != null) {
			if(selectedDisk != null){
				team.setSelectedDisk(selectedDisk, false);
				selectedDisk = null;
			}
		}
		Component source = (Component)e.getSource();
		source.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		Point point = e.getPoint();
		
		if(selectedDisk != null && point.getX() < width-50 && point.getX() > 50 && point.getY() < height-50 && point.getY() > 50) {
			point.setLocation(point.getX() - selectedDisk.getR() ,point.getY() - selectedDisk.getR());			
			team.setPosition(selectedDisk, point);
			Component source = (Component)e.getSource();
			source.repaint();
		}
	}
	
	/*public void mouseReleased(MouseEvent e) {
		if(!e.isPopupTrigger() && !e.isConsumed()) {
			popup.show(e.getComponent(),
						(int) (testDisk.getX() + testDisk.getR()), (int) (testDisk.getY() + testDisk.getR()));
		}	
	}*/
}
