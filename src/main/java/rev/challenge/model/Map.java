package rev.challenge.model;

import java.util.Arrays;
import java.util.Scanner;

import rev.challenge.action.Battle;
import rev.challenge.instructions.Leaderboard;

public class Map {
	private Space[][] spaces;
	private Player player;
	private int currentX;
	private int currentY;
	private int rooms;
	private int complete;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void reduceScore() {
		int currentScore=player.getScore();
		player.setScore(--currentScore);
	}
	
	public void printScore() {
		System.out.println("your score is "+player.getScore());
	}
	
	public Map(Player player) {
		super();
		this.player = player;
		spaces = new Space[5][5];
		fillSpaces();
	}

	public Map() {
		spaces = new Space[5][5];
		fillSpaces();
	}

	private void fillSpaces() {
		int x = (int)Math.floor(Math.random()*5);
		int y = (int)Math.floor(Math.random()*5);
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[i].length; j++) {
				if (((i == 0 || i == 4) && j == 2) || (i == 2 && (j == 0 || j == 4))) {
					spaces[i][j] = new Room();
					rooms++;
					continue;
				}
				spaces[i][j] = new Space();
			}
		}
		spaces[2][2].setCurrent(true);
		currentX = 2;
		currentY = 2;
		spaces[x][y].setWeapon(Weapon.makeWeapon("sword"));
		checkWeapon();
	}

	@Override
	public String toString() {
		String str = "";
		for (Space[] s : spaces) {
			str += Arrays.toString(s) + "\n";
		}
		return str;
	}

	public void move(String s) {
		switch (s.toLowerCase()) {
		case "w":
			up();
			break;
		case "s":
			down();
			break;
		case "a":
			left();
			break;
		case "d":
			right();
			break;
		default:
			break;
		}
		checkWeapon();
		reduceScore();
		System.out.println(player.getScore());
		if(player.getScore()==0) {youLose();}
	}

	private void down() {
		if (currentY!=4) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[++currentY][currentX].setCurrent(true);
		}
		else {
			System.out.println("You cannot walk through walls!");
		}
	}

	private void up() {
		if (currentY!=0) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[--currentY][currentX].setCurrent(true);
		}
		else {
			System.out.println("You cannot walk through walls!");
		}
	}

	private void right() {
		if (currentX!=4) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[currentY][++currentX].setCurrent(true);
		}
		else {
			System.out.println("You cannot walk through walls!");
		}
	}

	private void left() {
		if (currentX!=0) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[currentY][--currentX].setCurrent(true);
		}
		else {
			System.out.println("You cannot walk through walls!");
		}
	}

	public void consequences(Scanner s) {
		if(spaces[currentY][currentX] instanceof Room) {
			if(((Room)spaces[currentY][currentX]).getEnemy()!=null) {
				Battle.battle(player, ((Room)spaces[currentY][currentX]).getEnemy(), s);
				complete++;
			win();
			}
		if(spaces[currentY][currentX].getWeapon()!=null)
			player.setWeapon(spaces[currentY][currentX].getWeapon());
		
		}
	}
	private void win() {
		if(complete==rooms) {
			System.out.println("you win");
			printScore();
			Leaderboard.load();
			Leaderboard.add(new User(player.getName(), player.getScore()));
			System.out.println(Leaderboard.printLeaders());
			Leaderboard.save();
			System.exit(0);
		}
	}
	private void checkWeapon() {
		if(spaces[currentY][currentX].getWeapon()!=null) {
			player.setWeapon(spaces[currentY][currentX].getWeapon());
			System.out.println("you got the sword!!!");
		}
	}
	public static void youLose() {
		System.out.println("you lose, your score is 0");
		System.exit(0);
	}
}
