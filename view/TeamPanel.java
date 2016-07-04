package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Team;

public class TeamPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private Team team;
	private Image img;
	private int fontSize = 15;
	private int fontSpace = 18;
	private String fontType = "Calibri";
	private String checked = "\u26AB";
	private String unchecked = "\u26AA";
	
	public void setTeam(Team team) {
		if (this.team != null) {
			this.team.deleteObserver(this);
		}
		this.team = team;
		this.team.addObserver(this);
    }
	
	static Image iconToImage(Icon icon) {
		if(icon instanceof ImageIcon) {
			return ((ImageIcon)icon).getImage();
		} else {
			int w = icon.getIconWidth();
			int h = icon.getIconHeight();
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			BufferedImage image = gc.createCompatibleImage(w, h);
			Graphics2D g = image.createGraphics();
			icon.paintIcon(null, g, 0, 0);
			g.dispose();
			return image;
		}
	}
	
	public String makeRole(int roleNumber) {
		String role = "";
		switch(roleNumber) {
		case 1: role = "Tank"; break;
		case 2: role = "Healer"; break;
		case 3: role = "Rogue"; break;
		case 4: role = "Scout"; break;
		case 5: role = "Mdd"; break;
		case 6: role = "Pdd"; break;
		default: role = "Alt"; break;
		}
		return role;
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);		
		
		if(team != null) {
			
			int j = 1;
			int x = 30;
			int y = 30;
			String s = "Team list of " + team.getName();
			
			// linux true, windows false
			if(team.getFontSetting()) {
				fontSpace = 18;
				fontSize = 15;
				fontType = "Calibri";
				checked = "\u26AB";
				unchecked = "\u26AA";
			} else {
				fontSpace = 17;
				fontSize = 14;
				fontType = "Arial";
				checked = "+";
				unchecked = "-";
			}
			
			g.setFont(new Font("Calibri", Font.BOLD, 28));
			g.drawString(s, x, y);
			g.setFont(new Font(fontType, Font.PLAIN, fontSize));
			
			x = 30;
			y = 60;
			for(int i = 0; i < team.getPlayers().size(); ++i) {
				while(j < team.getPlayers().get(i).getNumber()) {
					++j;
					y = y + fontSpace;
					if(j == 34 || j == 67) {
						y = 60;
						x = x + 400;
					}
				}
				if(team.getPlayers().get(i).getDiskSet()) {
					g.drawString(checked, x, y);
				} else {
					g.drawString(unchecked, x, y);
				}
			}
			
			x = 50;
			y = 60;
			j = 1;
			for(int i = 0; i < team.getPlayers().size(); ++i) {
				while(j < team.getPlayers().get(i).getNumber()) {
					++j;
					y = y + fontSpace;
					if(j == 34 || j == 67) {
						y = 60;
						x = x + 400;
					}
				}
				g.drawString(j + ".", x, y);
			}
			x = 90;
			y = 49;
			j = 1;
			for(int i = 0; i < team.getPlayers().size(); ++i) {
				while(j < team.getPlayers().get(i).getNumber()) {
					++j;
					y = y + fontSpace;
					if(j == 34 || j == 67) {
						y = 49;
						x = x + 400;
					}
				}
				
				if(team.getPlayers().get(i).getCountry() != null) {
					// eclipse
					img = team.getPlayers().get(i).getCountryImg();
					if(img != null) {
						g.drawImage(img, x, y, null);
					}
					
					// jar
					/*img = iconToImage(team.getPlayers().get(i).getCountryImg());
					if(img != null) {
						g.drawImage(img, x, y, null);
					}*/
				}
			}
			x = 115;
			y = 60;
			j = 1;
			for(int i = 0; i < team.getPlayers().size(); ++i) {
				while(j < team.getPlayers().get(i).getNumber()) {
					++j;
					y = y + fontSpace;
					if(j == 34 || j == 67) {
						y = 60;
						x = x + 400;
					}
				}
				g.drawString(team.getPlayers().get(i).getName(), x, y);
			}
			x = 270;
			y = 60;
			j = 1;
			for(int i = 0; i < team.getPlayers().size(); ++i) {
				while(j < team.getPlayers().get(i).getNumber()) {
					++j;
					y = y + fontSpace;
					if(j == 34 || j == 67) {
						y = 60;
						x = x + 400;
					}
				}
				g.drawString(makeRole(team.getPlayers().get(i).getRole()), x, y);
			}
		}
	}
	
	@Override
    public void update(Observable arg0, Object arg1) {
    	repaint();
    }
}
