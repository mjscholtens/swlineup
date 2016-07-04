package view;

// import java.awt.AWTException;
import java.awt.Color;
// import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
// import java.awt.Rectangle;
// import java.awt.Robot;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
// import javax.imageio.ImageIO;
// import javax.swing.JFileChooser;
// import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.SelectionController;
import model.Player;
import model.Team;

public class LineupPanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private Image img;
	private Team team;
	private String fontType = "Calibri";
	private int fontSize = 11;
	private int singleNumberSpace = 7;
	private int doubleNumberSpace = 3;
	
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
	
	public LineupPanel(ImageIcon imageIcon) {
		this.img = iconToImage(imageIcon);
	}
	
	public void setSelectionController(SelectionController mousec) {
		this.addMouseListener(mousec);
		this.addMouseMotionListener(mousec);
	}
	
	public void setTeam(Team team) {
		if (this.team != null) {
			this.team.deleteObserver(this);
		}
		this.team = team;
		this.team.addObserver(this);
    }
	
	public Team getTeam() {
		return team;
	}
	
	/*public void convertLineup(Container c, int width, int height) {
		try {
			Rectangle screenRect = c.getBounds();
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			String fileName = team.getName() + ".bmp";
			JFileChooser saveFile = new JFileChooser();
			saveFile.setSelectedFile(new File(fileName));
			int sf = saveFile.showSaveDialog(saveFile);
			
			if(sf == JFileChooser.APPROVE_OPTION) {
				try {
					
					ImageIO.write(capture, "bmp", saveFile.getSelectedFile());
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Saving line-up failed: Exception 1 (IO failed).");
				}
			} else if(sf == JFileChooser.CANCEL_OPTION){
				JOptionPane.showMessageDialog(null, "Saving line-up failed: cancelled.");
			}
		} catch (AWTException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Saving line-up failed: Exception 2 (AWT failed).");
		}
	}*/

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		
		if(team != null) {
			
			if(team.getFontSetting()) {
				fontType = "Calibri";
				fontSize = 11;
				singleNumberSpace = 7;
				doubleNumberSpace = 3;
			} else {
				fontType = "Arial";
				fontSize = 12;
				singleNumberSpace = 7;
				doubleNumberSpace = 4;
			}
			for(Player p : team.getPlayers()) {
				if(p.getDiskSet()) {
					g.setColor(p.getDiskcolor());
					g.fillOval((int) p.getDisk().getX(), (int) p.getDisk().getY(), (int) p.getDisk().getR(), (int) p.getDisk().getR());
					if(p.getDisk().getRightSelected()) {
						g.setColor(Color.black);
						g.drawOval((int) p.getDisk().getX(), (int) p.getDisk().getY(), (int) p.getDisk().getR(), (int) p.getDisk().getR());
					}
					g.setColor(p.getFontcolor());
					g.setFont(new Font(fontType, Font.PLAIN, fontSize));
					if(p.getNumber() < 10) {
						g.drawString(Integer.toString(p.getNumber()), (int) p.getDisk().getX() + singleNumberSpace, (int) p.getDisk().getY() + 14);
					} else {
						g.drawString(Integer.toString(p.getNumber()), (int) p.getDisk().getX() + doubleNumberSpace, (int) p.getDisk().getY() + 14);
					}
					
					if(team.getToggleStatus() && p.getDisk().getStatus() != 0) {
						g.setColor(Color.white);
						g.fillRect((int) (p.getDisk().getX()) - p.getDisk().getStatus()*9 - 12,
								(int) p.getDisk().getY(), p.getDisk().getStatus()*9 + 12, 20);
						g.setColor(Color.black);
						g.drawRect((int) (p.getDisk().getX()) - p.getDisk().getStatus()*9 - 12,
								(int) p.getDisk().getY(), p.getDisk().getStatus()*9 + 12, 20);
						String letters = "";
						if(p.getDisk().getAoe()) {
							letters = letters + "A";
						}
						if(p.getDisk().getCaptain()) {
							letters = letters + "C";
						}
						if(p.getDisk().getFarmer()) {
							letters = letters + "F";
						}
						if(p.getDisk().getHerald()) {
							letters = letters + "H";
						}
						if(p.getDisk().getUpgrader()) {
							letters = letters + "U";
						}
						g.drawString(letters, (int) p.getDisk().getX() - p.getDisk().getStatus()*9 - 4,
											(int) p.getDisk().getY() + 14);
					}
				}				
			}
		}
	}
	
	@Override
    public void update(Observable arg0, Object arg1) {
    	repaint();
    }
}
