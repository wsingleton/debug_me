package rev.challenge.instructions;

import java.util.Scanner;

import rev.challenge.Driver;
import rev.challenge.model.Map;
import rev.challenge.model.Player;

public class Menu {
	public static String quit = "";
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
		System.out.println("\tSELECT ONE OF THE FOLLOWING");
		System.out.println("(1) START");
		System.out.println("(2) INSTRUCTIONS");
		System.out.println("(3) LEADERBOARD");
		System.out.println("(4) END");
		int x = s.nextInt();
		switch(x) {
		case 1:
			start(s);
		break;
		case 2: HelpMe.showInstructions();
			Scanner scanner = new Scanner(System.in);
			while(!quit.equals("q") || !quit.equals("Q")){
				System.out.print("\nPlease press q to quit back to the main menu: ");
				quit = scanner.nextLine();
				if(quit.equals("q") || quit.equals("Q")){
					Driver.main(new String[0]);
				}
			}
			break;
		case 3:
				Leaderboard.load();
				System.out.println(Leaderboard.printLeaders());
			Scanner scanner1 = new Scanner(System.in);
			while(!quit.equals("q") || !quit.equals("Q")) {
				System.out.print("\nPlease press q to quit back to the main menu: ");
				quit = scanner1.nextLine();
				if (quit.equals("q") || quit.equals("Q")) {
					Driver.main(new String[0]);
				}
			}
			break;
		case 4: break;
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
