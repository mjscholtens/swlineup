package model;

import java.util.Comparator;

public class TeamSort implements Comparator<Player> {
	
	public int compare(Player p1, Player p2) {
		if(p1.getNumber() > p2.getNumber()) {
			return 1;
		} else {
			return -1;
		}
	}
}
