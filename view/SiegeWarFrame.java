package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.SelectionController;
import model.Team;
import core.SiegeWarLineUp;
import view.LineupPanel;

@SuppressWarnings("unused")
public class SiegeWarFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private SiegeWarLineUp lineup;
	private LineupPanel lineupPanel;
	private TeamPanel teamPanel;
	private SelectionController mousec;
	private final int width = 1179;
	private final int height = 680;
	
	private AbstractAction quitAction;
	private AbstractAction newTeamAction;
	private AbstractAction saveTeamAction;
	private AbstractAction loadTeamAction;
	private AbstractAction changeTeamNameAction;
	private AbstractAction addPlayerAction;
	private AbstractAction removePlayerAction;
	// private AbstractAction editPlayerAction;
	private AbstractAction showTeamAction;
	private AbstractAction showFieldAction;
	private AbstractAction turnLineupAction;
	private AbstractAction toggleStatusAction;
	private AbstractAction importLineupAction;
	private AbstractAction exportLineupAction;
	// private AbstractAction convertLineupAction;
	// private AbstractAction switchEnglishAction;
	// private AbstractAction switchGermanAction;
	private AbstractAction showAboutAction;
	private AbstractAction changeFontsAction;
	private AbstractAction cleanLineupAction;
	
	private JPanel contentPane;
	private CardLayout cardLayout;
    
    public SiegeWarFrame(SiegeWarLineUp lineup) {
    	this.lineup = lineup;
    	initGUI();
    }
    
    private void initGUI() {
    	this.setFocusable(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(width, height);
    	this.setResizable(false);
    	this.setTitle("Siege War Line Up - Yottahertz / Feraraider (Ionsai)");
    	
    	contentPane = new JPanel();
    	contentPane.setBackground(Color.decode("#B6B6B6"));
    	cardLayout = new CardLayout();
    	contentPane.setLayout(cardLayout);
    	
    	// use first line in Eclipse, use second line for jar    	
    	lineupPanel = new LineupPanel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("siegewarmap2.jpg")));
    	//lineupPanel = new LineupPanel(new ImageIcon(getClass().getResource("/flags/siegewarmap2.jpg")));
    	teamPanel = new TeamPanel();
    	contentPane.add(lineupPanel, "Lineup Panel");
    	contentPane.add(teamPanel, "Team Panel");
    	
    	initActions();
    	
    	/* Make the menu */
    	JMenuBar menuBar = new JMenuBar();
    	JMenu menu1 = new JMenu("File");
    	JMenu menu2 = new JMenu("Edit");
    	JMenu menu3 = new JMenu("View");
    	// JMenu menu4 = new JMenu("Language/Sprache");
    	JMenu menu5 = new JMenu("Other");
    	menuBar.add(menu1);
    	menuBar.add(menu2);
    	menuBar.add(menu3);
    	// menuBar.add(menu4);
    	menuBar.add(menu5);
    	menu1.add(this.newTeamAction);
    	menu1.add(this.loadTeamAction);
    	menu1.add(this.saveTeamAction);
    	menu1.add(this.importLineupAction);
    	menu1.add(this.exportLineupAction);
    	// menu1.add(this.convertLineupAction);
    	menu1.add(this.quitAction);    	
    	menu2.add(this.addPlayerAction);
    	// menu2.add(this.editPlayerAction);
    	menu2.add(this.removePlayerAction);
    	menu2.add(this.changeTeamNameAction);
    	menu3.add(this.showTeamAction);
    	menu3.add(this.showFieldAction);
    	menu3.add(this.turnLineupAction);
    	menu3.add(this.toggleStatusAction);
    	menu3.add(this.cleanLineupAction);
    	menu3.add(this.changeFontsAction);
    	// menu4.add(this.switchEnglishAction);
    	// menu4.add(this.switchGermanAction);
    	menu5.add(this.showAboutAction);
    	this.setJMenuBar(menuBar);
    	
    	this.setContentPane(contentPane);
    	this.setVisible(true);
    }
    
    private void makeNewTeam() {
    	String name = "";
    	while(name == "") {
    		name = JOptionPane.showInputDialog("Please enter a team name.", "");
    	}
    	Team team = lineup.newTeam(name);
    	lineup.setActiveTeam(team);
    	teamPanel.setTeam(team);
    	lineupPanel.setTeam(team);
    	mousec = new SelectionController(team, width, height);
    	lineupPanel.setSelectionController(mousec);
    }
    
    private String giveFlag() {
    	String flag = null;
    	flag = JOptionPane.showInputDialog("Pick a country for the player.\n"
    			+ "Write the country's name in lowercase letters, in one word.\n"
    			+ "Example: unitedkingdom, england, holland, germany, bosniaherzogovina.\n"
    			+ "Note: May be left empty. "
    			+ "(Invalid names also count as empty, but you can still change later).", null);
    	return flag;
    }
    
    private String giveName() {
    	String name = "";
    	while(name == "") {
    		name = JOptionPane.showInputDialog("Give the player a name.", "");
    	}
    	return name;
    }
    
    private int giveNumber() {
    	int number = 0;
    	while(number < 1 || number > 99) {
    		number = Integer.parseInt(JOptionPane.showInputDialog("Give the player a number (1-99).", 0));
    	}
    	return number;
    }
    
    private int giveRole() {
    	int role = 0;
    	while(role < 1 || role > 7) {
    		Object[] roles = {"1. Tank","2. Healer","3. Rogue","4. Scout","5. Mdd","6. Pdd","7. Alt"};
    		String s = (String) JOptionPane.showInputDialog(this,"Choose the player's role:","",0,null,roles, "1. Tank");
    		role = Integer.parseInt(s.substring(0,1));
    	}
    	return role;
    }
    
    @SuppressWarnings("serial")
	private void initActions() {
    	
		this.newTeamAction = new AbstractAction("New Team") {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	makeNewTeam();
		    }
		};
		
		this.saveTeamAction = new AbstractAction("Save Team") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lineup.getActiveTeam().saveTeam();
			}
		};
		
		this.loadTeamAction = new AbstractAction("Load Team") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lineup.loadTeam();
			}
		};
		
		this.importLineupAction = new AbstractAction("Import Line-up") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lineup.loadLineup();
			}
		};
		
		this.exportLineupAction = new AbstractAction("Export Line-up") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lineup.getActiveTeam().saveLineup();
			}
		};
		
		/*this.convertLineupAction = new AbstractAction("Convert Line-up") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPane, "Lineup Panel");
				lineupPanel.convertLineup(contentPane, width, height);
			}
		};*/
		
		this.quitAction = new AbstractAction("Quit") {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	System.exit(0);
		    }
		};
		
		this.addPlayerAction = new AbstractAction("Add Player") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(lineup.getActiveTeam() == null) {
					makeNewTeam();
				} else {
					String name = giveName();
					int number = giveNumber();
					int role = giveRole();
					String flag = giveFlag();
					lineup.getActiveTeam().addPlayer(name, number, role, flag);
				}
			}
		};
		
		/*this.editPlayerAction = new AbstractAction("Edit Player") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(lineup.getActiveTeam() != null) {
					lineup.getActiveTeam().editPlayer();
				}
			}
		};*/
    
	    this.removePlayerAction = new AbstractAction("Remove Player") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(lineup.getActiveTeam().getPlayers().size() != 0) {
					int number = 0;
					while(number < 1 || number > 99) {
						number = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of the player"
								+ " that you want to remove.", 0));
					}
					lineup.getActiveTeam().setNumberFalse(number);
					lineup.getActiveTeam().removePlayer(number);
				}
			}
		};
		
		this.changeTeamNameAction = new AbstractAction("Change Team Name") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Enter a new name for the team.", null);
				if(name != null) {
					lineup.getActiveTeam().setName(name);
				}
			}
		};
		
		this.showTeamAction = new AbstractAction("Show Team") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPane, "Team Panel");
			}
		};
		
		this.showFieldAction = new AbstractAction("Show Field") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPane, "Lineup Panel");
			}
		};
		
		this.turnLineupAction = new AbstractAction("Turn Line-up") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lineup.getActiveTeam().turnLineup((double) width, (double) height);
			}
		};
		
		this.toggleStatusAction = new AbstractAction("Toggle Status") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(lineup.getActiveTeam() != null) {
					lineup.getActiveTeam().setToggleStatus();
				}
			}
		};
		
		this.toggleStatusAction = new AbstractAction("Clean Line-up") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(lineup.getActiveTeam() != null) {
					lineup.getActiveTeam().removeAllDisks();
				}
			}
		};
		
		this.changeFontsAction = new AbstractAction("Change Fonts") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(lineup.getActiveTeam() != null) {
					lineup.getActiveTeam().setFontSetting();
				}
			}
		};
		
		/*this.switchEnglishAction = new AbstractAction("English") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// do
			}
		};*/
		
		/*this.switchGermanAction = new AbstractAction("Deutsch") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// do
			}
		};*/
		
		this.showAboutAction = new AbstractAction("About") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Siege War Line Up v1.3 - 19/07/14.\n\n"
						+ "Created by Yottahertz (forums) / Feraraider (Ionsai).");
			}
		};
		
		/* Short keys for the Action objects */
		// this.quitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
		// this.newGameAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		// this.mainMenuAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		// this.pauseResumeAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
    }
    
    public LineupPanel getLineupPanel() {
    	return lineupPanel;
    }
    
    public TeamPanel getTeamPanel() {
    	return teamPanel;
    }
}
