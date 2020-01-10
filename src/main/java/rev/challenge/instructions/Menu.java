package rev.challenge.instructions;

import java.util.InputMismatchException;
import java.util.Scanner;

import rev.challenge.Driver;
import rev.challenge.model.Map;
import rev.challenge.model.Player;

import static rev.challenge.instructions.HelpMe.showInstructions;

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
		try {
			int x = s.nextInt();
			switch(x) {
			case 1:
				start(s);
				break;
			case 2:
				System.out.println("TODO: INSTRUCTIONS");
				showInstructions();
				Driver.appRunning = true;
			break;
			case 3:
				System.out.println(Leaderboard.printLeaders());
				Driver.appRunning = true;
				break;
			default: Driver.appRunning = false;
			}
		}catch (InputMismatchException e) {
			Driver.main(new String[0]);
		}
	}
	private static void start(Scanner s) {
		Player p = new Player();

		System.out.println("Enter your name");
		System.out.print("> ");
		String name = s.next();
		p.setName(name);
		p.setHealth(500.0f);
		System.out.println("Here is the Map");
		Map m = new Map(p);
		while(p.getHealth() > 0) {
			System.out.println(m);
			System.out.println("Where would you like to move?");
			m.move(s.next());
			m.consequences(s);
		}
		System.out.println("you lose");
		Driver.appRunning = false;
	}

}
