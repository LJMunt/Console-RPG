package characterPackage;

import java.util.Scanner;

import battlePackage.Battlefield;
import battlePackage.PlayerParty;
import battlePackage.Terrain;

public class Rpg_Tester {
static PlayerParty party = new PlayerParty();
	public static void main(String[] args) {
		party.addChar();
		Character player = party.getParty()[0];
		Scanner scan = new Scanner(System.in);
		Battlefield mainStage = new Battlefield(Terrain.getRandom(), player );
		System.out.println("Welcome to the Console-RPG.\n"+
				"Press 1 to start the battlemode with your character.\n"+
				"Press 2 to create another character and add them to the party.\n"+
				"Press 3 to list all the characters currently in your party.\n"+
				"Press 4 to change your current character.\n"+
				"Press 5 to view your current character.\n"+
				"Press 0 to end the game.");
		int playerChoice = scan.nextInt();
		while (playerChoice != 0) {
			
			switch (playerChoice) {
			case 1:
				System.out.println("Start "+(Battlefield.getBattleCount() > 0 ? " next": " first")+" battle?");
				String userChoice = scan.next();
				while (userChoice.equalsIgnoreCase("y") && player.isAlive()) {
					mainStage.startNextBattle();
					if (player.isAlive()) {
						System.out.println("Next battle? (y/n)");
						userChoice = scan.next(); } 
				}
				break;
			case 2:
					party.addChar();
					System.out.println("Character added.");
					break;
			case 3:
				System.out.println("The following Characters are in the party.");
				System.out.println(party);
				break;
				//TODO toString Methode schreiben.
			case 4:
				System.out.println("Enter the name of the character you want to play as.");
				String characterChoice = scan.next();
				for (int i = 0; i < party.MAX_SIZE ;i++) {
					if (characterChoice.equalsIgnoreCase(party.getParty()[i].getName())) {
						player = party.getParty()[i];
						/*TODO @Noel. Ich will das character Objekt (Player) mit einem der indexierten Character Objekte aus party tauschen.
						 * Der Spieler soll einen Charakter aus der Party wählen können und diesen dann für die nächsten Kämpfe spielen,
						 * Bis er sich einen anderen aussucht oder sein momentaner Charakter tot ist. 
						 * Wie muss ich das implementieren?
						 */
									}
				}
				break;
			case 5:
				System.out.println(player);
				break;
			case 0:
				break;
			default:
				System.out.println("Comand not recognized. Try again.");
			}
			System.out.println("Press 2 to create another character and add them to the party.\n"+
					"Press 3 to list all the characters currently in your party.\n"+
					"Press 4 to change your current character.\n"+
					"Press 5 to view your current character.\n"+
					"Press 0 to end the game.");
			playerChoice = scan.nextInt();
		}
	}
}