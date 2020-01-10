package rev.challenge.model;

import java.util.Arrays;
import java.util.Scanner;

import rev.challenge.action.Battle;
import rev.challenge.instructions.Leaderboard;

public class Map {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";


	private Space[][] spaces;
	private Player player;
	private int currentX;
	private int currentY;
	private int rooms;
	private int complete;
	private int score=30;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void reduceScore() {
		score--;
	}
	
	public void printScore() {
		System.out.println("your score is "+score);
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
		if(score==0) {
			System.out.println(ANSI_RED + "you lose, your score is 0" + ANSI_RESET);
			System.exit(0);
		}
	}

	private void down() {
		if(currentY != spaces.length - 1) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[++currentY][currentX].setCurrent(true);
		}
		else System.out.println("You have reached the edge of the map");
	}

	private void up() {
		if(currentY != 0) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[--currentY][currentX].setCurrent(true);
		}
		else System.out.println("You have reached the edge of the map");
	}

	private void right() {
		if(currentX != spaces[1].length - 1) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[currentY][++currentX].setCurrent(true);
		}
		else System.out.println("You have reached the edge of the map");
	}

	private void left() {
		if(currentX != 0) {
			spaces[currentY][currentX].setCurrent(false);
			spaces[currentY][currentX].setVisited(true);
			spaces[currentY][--currentX].setCurrent(true);
		}
		else System.out.println("You have reached the edge of the map");
	}

	public void consequences(Scanner s) {
		if(spaces[currentY][currentX] instanceof Room) {
			if(((Room)spaces[currentY][currentX]).getEnemy()!=null) {
				Battle.battle(player, ((Room)spaces[currentY][currentX]).getEnemy(), s);
				((Room)spaces[currentY][currentX]).setEnemy(null);
				complete++;
			win();
			}

			checkWeapon();
		}

	}
	private void win() {
		if(complete==rooms) {
			System.out.println(ANSI_BLUE + "you win" + ANSI_RESET);
			printScore();
			Leaderboard.load();
			Leaderboard.add(new User(player.getName(), score));
			System.out.println(Leaderboard.printLeaders());
			Leaderboard.save();
			System.exit(0);
		}
	}
	private void checkWeapon() {
		if(spaces[currentY][currentX].getWeapon()!=null) {
			player.setWeapon(spaces[currentY][currentX].getWeapon());
			spaces[currentY][currentX].setWeapon(null);
			System.out.println("you got the sword!!!");
		}
	}
}
