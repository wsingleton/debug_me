package rev.challenge;

import java.util.Scanner;

import rev.challenge.instructions.Leaderboard;
import rev.challenge.instructions.Menu;

public class Driver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Menu.logo();
		Menu.menu(scan);
		scan.close();
	Leaderboard.initialize();
	Leaderboard.load();
		System.out.println(Leaderboard.printLeaders());
	}
	

}
