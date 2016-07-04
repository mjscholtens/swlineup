package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.SelectionController;
import model.Team;
import view.SiegeWarFrame;

/*
 * To do list:
 * - Edit players
 * - Support multiple languages
 * - Import/Export roles
 * - Autoscreen
 * - Arrows / Gatherpoint
 */

public class SiegeWarLineUp {
	
	private SiegeWarFrame frame;
	private Team team = null;
	private Team propTeam = null;
	
	public SiegeWarLineUp() {
		frame = new SiegeWarFrame(this);
	}

	public static void main(String[] args) {
		new SiegeWarLineUp();
	}
	
	public Team newTeam(String name) {
		return new Team(name);
	}

	public void setActiveTeam(Team team) {
		this.team = team;
	}
	
	public Team getActiveTeam() {
		return team;
	}
	
	public void acceptTeam() {
		team = propTeam;
		team.setName(propTeam.getName());
		propTeam = null;
		setActiveTeam(team);
    	frame.getTeamPanel().setTeam(team);
    	frame.getLineupPanel().setTeam(team);
    	frame.getLineupPanel().setSelectionController(new SelectionController(team, frame.getWidth(), frame.getHeight()));
	}
	
	public int parseRole(String line) {
		int role = 0;
		int lineLength = line.length();
		if(lineLength == 6) {
			if(line.equals("healer") || line.equals("Healer")) {
				role = 2;
			}
		} else if(lineLength == 5) {
			if(line.equals("rogue") || line.equals("Rogue")) {
				role = 3;
			} else if(line.equals("scout") || line.equals("Scout")) {
				role = 4;
			}
		} else if(lineLength == 4) {
			if(line.equals("tank") || line.equals("Tank")) {
				role = 1;
			}
		} else if(lineLength == 3) {
			if(line.equals("mdd") || line.equals("Mdd")) {
				role = 5;
			} else if(line.equals("pdd") || line.equals("Pdd")) {
				role = 6;
			} else if(line.equals("alt") || line.equals("Alt")) {
				role = 7;
			}
		}
		
		return role;
	}
	
	public String parseName(String line) {		
		int i = 0;
		while(line.substring(i).length() != 0 && Character.toString(line.charAt(i)) != null && line.charAt(i) != ' ') {
			++i;
		}
		return line.substring(0, i);
	}
	
	public boolean isNumeric(String line) {
		if(line.substring(0, 1).matches("[0-9]+")) {
			return true;
		}
		return false;
	}
	
	public boolean isNumericNoZero(String line) {
		if(line.substring(0, 1).matches("[1-9]+")) {
			return true;
		}
		return false;
	}
	
	public int parseNumber(String line) {
		int propNumber = 0;
		
		if(isNumericNoZero(line) && line.substring(1, 2).matches("\\s")) {
			propNumber = Integer.parseInt(line.substring(0, 1));
		} else if((isNumericNoZero(line) && (isNumeric(line.substring(1)))) && line.substring(2, 3).matches("\\s")) {
			propNumber = Integer.parseInt(line.substring(0, 2));
		}
		
		return propNumber;
	}
	
	public boolean parseAndMake(BufferedReader reader, String loadTeamName) throws IOException {
		propTeam = new Team(loadTeamName);
		boolean approveTeam = true;
		int propNumber = 0;
		String propName = "";
		int propRole = 0;
		String propFlag = null;
		int linepos = 0;
		
		for(String line = reader.readLine(); line != null; line = reader.readLine()) {
			
			// parse number
			linepos = 2;
			propNumber = parseNumber(line);
			if(propNumber == 0) {
				JOptionPane.showMessageDialog(null, "Loading team failed: Error in number.\n"
						+ "Note: The format is <number> <space> <name>.");
				approveTeam = false;
				break;
			}
			
			// cut off number from string for name
			if(propNumber > 9) {
				++linepos;
			}
			line = line.substring(linepos);
			
			// parse name
			propName = parseName(line);
			if(propName == " ") {
				JOptionPane.showMessageDialog(null, "Loading team failed: Error in name.\n"
						+ "Note: Make sure that there is a space exactly before and after the name.");
				approveTeam = false;
				break;
			}
			
			// cut off name from string for role
			line = line.substring(propName.length() + 1);
			
			// parse role
			propRole = parseRole(parseName(line));
			if(propRole == 0) {
				JOptionPane.showMessageDialog(null, "Loading team failed: Error in role.\n"
						+ "Note: Make sure that the role has been spelled correctly in lowercase letters.");
				approveTeam = false;
				break;
			}
			
			// cut off name from string for flag
			line = line.substring(parseName(line).length());
			
			// parse flag
			if(line.length() != 0) {
				propFlag = line.substring(1);
			} else {
				propFlag = null;
			}
			if(!propTeam.addPlayer(propName, propNumber, propRole, propFlag)) {
				break;
			}
		}
		
		return approveTeam;
	}
	
	public String findPropTeamName(String propTeamName) {
		propTeamName = propTeamName.substring(0, propTeamName.length() - 4);
		int i = propTeamName.length() - 1;
		while(i != 0) {
			if(!Character.isLetterOrDigit(propTeamName.charAt(i))) {
				break;
			}
			--i;
		}
		propTeamName = propTeamName.substring(i+1);
		return propTeamName;
	}
	
	public boolean loadCorrectTeam(String propTeamName, BufferedReader reader) throws IOException {
		propTeamName = findPropTeamName(propTeamName);
		
		String loadTeamName = JOptionPane.showInputDialog("The proposed team name is: " + propTeamName
				+ ".\nIs this correct? If not, then please enter your desired team name below.\n"
				+ "Otherwise do nothing and click Ok.", null);
		
		if(loadTeamName != null) {
			loadTeamName = propTeamName;
		}
		if(parseAndMake(reader, loadTeamName)) {
			propTeam.setName(loadTeamName);
			acceptTeam();
			return true;
		}
		propTeam = null;
		return false;
	}

	public void loadTeam() {
		BufferedReader reader = null;
		JFileChooser loadFile = new JFileChooser();
		int lf = loadFile.showOpenDialog(loadFile);
		boolean loadSuccess = false;
		
		if(lf == JFileChooser.APPROVE_OPTION) {
			try {
				reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
				String propTeamName = loadFile.getSelectedFile().toString();
				
				// check for correctness
				if(!propTeamName.endsWith(".txt")) {
					JOptionPane.showMessageDialog(null, "Loading team failed: Input file must be of type .txt");
				} else {
					loadSuccess = loadCorrectTeam(propTeamName, reader);				
				}
				
				// exception handling and closing
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Loading team failed: Exception 1 (Bad file).");
			} finally {
				try {
					if(reader != null) {
						reader.close();
						if(loadSuccess) {
							JOptionPane.showMessageDialog(null, "Loading team successful.");
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Loading team failed: Exception 2 (Failed close).");
				}
			}			
		} else if(lf == JFileChooser.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null, "Loading team failed: cancelled.");
		}
	}
	
	public boolean parseAndLineup(BufferedReader reader) throws IOException {
		boolean approveLineup = true;
		int propNumber = 0;
		String propCoordinate = "";
		double propX = -1;
		double propY = -1;
		int linepos = 0;
		
		for(String line = reader.readLine(); line != null; line = reader.readLine()) {
			
			// parse number
			linepos = 2;
			propNumber = parseNumber(line);
			if(propNumber == 0) {
				JOptionPane.showMessageDialog(null, "Loading line-up failed: Error in number.\n"
						+ "Note: The format is <number> <space> <x coordinate>.");
				approveLineup = false;
				break;
			}
			
			// cut off number from string for coordinates
			if(propNumber > 9) {
				++linepos;
			}
			line = line.substring(linepos);
			
			// parse X coordinate
			propCoordinate = parseName(line);
			if(propCoordinate == " ") {
				JOptionPane.showMessageDialog(null, "Loading team failed: Error in x coordinate.\n"
						+ "Note: The coordinates must be numeric (0-9).");
				approveLineup = false;
				break;
			}
			propX = Double.parseDouble(propCoordinate);
			
			// cut off name from string for role
			line = line.substring(propCoordinate.length() + 1);
			
			// parse role
			propCoordinate = parseName(line);
			if(propCoordinate == " ") {
				JOptionPane.showMessageDialog(null, "Loading team failed: Error in x coordinate.\n"
						+ "Note: The coordinates must be numeric (0-9).");
				approveLineup = false;
				break;
			}
			propY = Double.parseDouble(propCoordinate);
			
			team.addDisk(propNumber, propX, propY);
		}
		
		return approveLineup;
	}
	
	public boolean loadCorrectLineup(BufferedReader reader) throws IOException {
		if(team == null) {
			JOptionPane.showMessageDialog(null, "Error: There is no team set.\n"
					+ "Either load or create a new team first\n"
					+ "before you try to import a line-up.");
		} else if(parseAndLineup(reader)) {
				return true;
		}
		return false;
	}
	
	public void loadLineup() {
		BufferedReader reader = null;
		JFileChooser loadFile = new JFileChooser();
		int lf = loadFile.showOpenDialog(loadFile);
		boolean loadSuccess = false;
		
		if(lf == JFileChooser.APPROVE_OPTION) {
			try {
				reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
				String propTeamName = loadFile.getSelectedFile().toString();
				
				// check for correctness
				if(!propTeamName.endsWith(".txt")) {
					JOptionPane.showMessageDialog(null, "Loading line-up failed: Input file must be of type .txt");
				} else {
					loadSuccess = loadCorrectLineup(reader);				
				}
				
				// exception handling and closing
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Loading line-up failed: Exception 1 (Bad file).");
			} finally {
				try {
					if(reader != null) {
						reader.close();
						if(loadSuccess) {
							JOptionPane.showMessageDialog(null, "Loading line-up successful.");
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Loading line-up failed: Exception 2 (Failed close).");
				}
			}			
		} else if(lf == JFileChooser.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null, "Loading line-up failed: cancelled.");
		}
	}
}
