package rev.challenge;

import java.util.Scanner;

import rev.challenge.instructions.Leaderboard;
import rev.challenge.instructions.Menu;

public class Driver {
	public static void main(String[] args) {
		Leaderboard.load();
		Scanner scan = new Scanner(System.in);
		Menu.logo();
		Menu.menu(scan);
		scan.close();
//		System.out.println(Leaderboard.printLeaders());
	}
	

}
