package rev.challenge.instructions;

import java.util.Scanner;

import rev.challenge.Driver;
import rev.challenge.model.Map;
import rev.challenge.model.Player;

import static java.lang.System.exit;

public class Menu {

	public static void logo() {
		System.out.println("\t\t\t\t |||||    |||||   ||||    || ||   ||| ");
		System.out.println("\t\t\t\t ||  ||   ||      || ||   || ||   |   ");
		System.out.println("\t\t\t\t ||  ||   |||||   ||||    || ||   | |||");
		System.out.println("\t\t\t\t ||  ||   ||      || ||   || ||   |  |");
		System.out.println("\t\t\t\t |||||    |||||   ||||     |||    ||||");
		System.out.println("");
		System.out.println("\t\t\t\t               ||    ||     |||||    ");
		System.out.println("\t\t\t\t              ||||  ||||    ||       ");
		System.out.println("\t\t\t\t              ||  ||  ||    |||||    ");
		System.out.println("\t\t\t\t              ||      ||    ||       ");
		System.out.println("\t\t\t\t              ||      ||    |||||    ");
	}
	public static void menu(Scanner s) {

		boolean success = false;
		while(!success) {
			System.out.println("\n +=======================================================================+\n");
			System.out.println("\tSELECT ONE OF THE FOLLOWING");
			System.out.println("(1) START");
			System.out.println("(2) INSTRUCTIONS");
			System.out.println("(3) LEADERBOARD");
			System.out.println("(4) END");
			String x = s.next();
			switch (x) {
				case "1":
					start(s);
					break;
				case "2": //System.out.println("TODO: INSTRUCTIONS");
					HelpMe.showInstructions();
					break;
				case "3":
					System.out.println(Leaderboard.printLeaders());
					break;
				case "4":
					System.out.println("\nShutting Down Program");
					exit(0);
					break;
				default:
					//Driver.main(new String[0]);
					System.out.println("\nInvalid option, please choose again.");
			}
		}
	}
	private static void start(Scanner s) {
		System.out.println("what is your name?");
		Player p = new Player();
		String name = s.next();
		p.setName(name);
		p.setHealth(500.0f);
		System.out.println("\nhere is the Map");
		Map m = new Map(p);
		while(p.getHealth() > 0) {
			System.out.println(m);
			System.out.println("where would you like to move?");
			m.move(s.next());
			m.consequences(s);
		}
		System.out.println("you lose");
		exit(0);
	}

}
