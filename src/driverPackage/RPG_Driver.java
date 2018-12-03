package driverPackage;

import java.io.IOException;
import java.util.Scanner;

import battlePackage.Battlefield;
import battlePackage.PlayerParty;
import battlePackage.Terrain;
import characterPackage.Character;

public class RPG_Driver {
static PlayerParty party = new PlayerParty();
	public static void main(String[] args) throws IOException {
	Scanner scan = new Scanner(System.in);
	try {
		System.out.println("  Welcome to the Console-RPG. Documentation can be found in the README");
		System.out.println("-------------------------------------------------------------------------");
		party.addChar();
		Character player = party.getParty()[0];
		Battlefield mainStage = new Battlefield(Terrain.getRandom(), player );
		System.out.println("Press 1 to start the battlemode with your character.\n"+
				"Press 2 to create another character and add them to the party.\n"+
				"Press 3 to list all the characters currently in your party.\n"+
				"Press 4 to change your current character.\n"+
				"Press 5 to view your current character.\n"+
				"Press 0 to end the game.");
		int playerChoice = scan.nextInt();
		while (playerChoice != 0) {
			//Menu-block. Could be done more compact.
			switch (playerChoice) {
			case 1:
				System.out.println("Start "+(Battlefield.getBattleCount() > 0 ? " next": " first")+" battle?");
				String userChoice = scan.next();
				while (userChoice.equalsIgnoreCase("y") && player.isAlive()) {
					mainStage.startNextBattle(player);
					clean();
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
			case 4:
				System.out.println("Enter the name of the character you want to play as.");
				String characterChoice = scan.nextLine();
				characterChoice = scan.nextLine();
				for (int i = 0; i < party.getCharCount() ;i++) {
					if (characterChoice.equalsIgnoreCase(party.getParty()[i].getName())) {
						player = party.getParty()[i];}
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
			System.out.println("Press 1 to start the battlemode with your character.\n"+
					"Press 2 to create another character and add them to the party.\n"+
					"Press 3 to list all the characters currently in your party.\n"+
					"Press 4 to change your current character.\n"+
					"Press 5 to view your current character.\n"+
					"Press 0 to end the game.");
			playerChoice = scan.nextInt();
			}
		}
	finally {scan.close();}
	}
	//removes all dead characters from the party.
	private static void clean() {
		for (int i = 0; i < party.getCharCount(); i++) {
			if (party.getParty()[i].isAlive() == false) {
				party.delChar(i);
			}
		}
		
	}
}