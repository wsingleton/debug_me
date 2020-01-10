package rev.challenge.instructions;

import java.util.Scanner;

import rev.challenge.Driver;
import rev.challenge.model.Map;
import rev.challenge.model.Player;

public class Menu {
	public static int quit = 0;
	public static String name = "";



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
		int x;
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tSELECT ONE OF THE FOLLOWING");
		System.out.println("(1) START");
		System.out.println("(2) INSTRUCTIONS");
		System.out.println("(3) LEADERBOARD");
		System.out.println("(4) END");
		x = s.nextInt();
		quit = 0;
		switch(x) {
		case 1:
			start(s);
		break;
		case 2: HelpMe.showInstructions();
			while(quit != 5){
				System.out.print("\nPlease press 5 to quit back to the main menu: ");
				quit = scanner.nextInt();
				if(quit == 5){
					Driver.main(new String[0]);
					break;
				}


			}
			break;
		case 3:
				Leaderboard.load();
				System.out.println(Leaderboard.printLeaders());
			while(quit != 5) {
				System.out.print("\nPlease press 5 to quit back to the main menu: ");
				quit = scanner.nextInt();
				if (quit == 5) {
					Driver.main(new String[0]);
					break;
				}

			}
			break;
		case 4:

			break;
		default: Driver.main(new String[0]);
		}
	}
	private static void start(Scanner s) {
		s.nextLine();
		System.out.println("what is your name?");
		name = s.next();
		Player p = new Player();
		p.setName(name);
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
