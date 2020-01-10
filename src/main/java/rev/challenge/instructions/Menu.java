package rev.challenge.instructions;

import java.util.Scanner;

import rev.challenge.Driver;
import rev.challenge.model.Map;
import rev.challenge.model.Player;

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
		System.out.println("\tSELECT ONE OF THE FOLLOWING");
		System.out.println("(1) START");
		System.out.println("(2) INSTRUCTIONS");
		System.out.println("(3) LEADERBOARD");
		System.out.println("(4) END");
		String temp =  s.next();
		if (!temp.matches("^[1-4]+$")){
			System.out.println("invalid input, must be 1-4");
			menu(s);
		}
		int x = Integer.parseInt(temp);
		switch(x) {
		case 1: start(s);
		break;
		case 2:
			HelpMe.showInstructions();
			System.out.println("\nPRESS ANY KEY TO RETURN TO THE MAIN MENU");
			String foo = s.next();
			Driver.main(new String[0]);
		break;
		case 3:
			Leaderboard.load();
			System.out.println(Leaderboard.printLeaders());
			System.out.println("PRESS ANY KEY TO RETURN TO THE MAIN MENU");
			String pause = s.next();
			Driver.main(new String[0]);
			break;
		case 4: break;
		default: Driver.main(new String[0]);
		}
	}
	private static void start(Scanner s) {
		System.out.println("what is your name?");
		Player p = new Player();
		p.setName(s.next());
		p.setHealth(500.0f);
		System.out.println("here is the Map");
		Map m = new Map(p);
		while(p.getHealth() > 0) {
			System.out.println(m);
			System.out.println("where would you like to move?");
			m.move(s.next());
			m.consequences(s);
		}
		System.out.println("you lose");
		System.exit(0);
	}

}
