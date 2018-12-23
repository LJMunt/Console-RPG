package driverPackage;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import battlePackage.Battlefield;
import battlePackage.PlayerParty;
import characterPackage.Character;
import itemPackage.ItemNames;
import shopPackage.Shop;

public class RPG_Driver {

	static PlayerParty party = new PlayerParty();

	public static void main(String[] args) throws IOException {
		GameUtilities.gameTitle();
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("");
			System.out.println("Welcome to the Console-RPG. Documentation can be found in the README");
			System.out.println("-------------------------------------------------------------------------");
			gameMenu(scan);
		} catch (InputMismatchException e) {
			System.out.println("Wrong input. Terminating Game. ");
		} finally {
			scan.close();
			System.out.flush();
		}
	}

	public static void gameMenu(Scanner scan) {
		party.addChar();
		GameUtilities.clearConsole();
		Character player = party.getParty()[0];
		Battlefield mainStage = new Battlefield(player);
		System.out.println("Press 1 to start the battlemode with your character.\n"
				+ "Press 2 to create another character and add them to the party.\n"
				+ "Press 3 to list all the characters currently in your party.\n"
				+ "Press 4 to change your current character.\n" + "Press 5 to view your current character.\n"
				+ "Press 6 to open the Inventory Menu.\n" + "Press 0 to end the game.");
		String playerChoice = scan.next();
		while (!playerChoice.equals("0")) {
			// Menu-block. Could be done more compact.
			switch (playerChoice) {
			case "1":
				System.out
						.println("Start " + (Battlefield.getBattleCount() > 0 ? " next" : " first") + " battle? (y/n)");
				String userChoice = scan.next();
				while (userChoice.equalsIgnoreCase("y") && player.isAlive()) {
					mainStage.startNextBattle(player);
					GameUtilities.clean();
					if (player.isAlive()) {
						System.out.println("Next battle? (y/n)");
						userChoice = scan.next();
					} else
						System.out
								.println(player.getName() + " has died. Choose or create a new character to continue.");
				}
				break;
			case "2":
				party.addChar();
				System.out.println("Character added.");
				break;
			case "3":
				System.out.println("The following Characters are in the party.");
				System.out.println(party);
				break;
			case "4":
				System.out.println("Enter the name of the character you want to play as.");
				String characterChoice = scan.nextLine();
				characterChoice = scan.nextLine();
				for (int i = 0; i < party.getCharCount(); i++) {
					if (characterChoice.equalsIgnoreCase(party.getParty()[i].getName())) {
						player = party.getParty()[i];
					}
				}
				break;
			case "5":
				System.out.println(player);
				break;
			case "6":
				GameUtilities.clearConsole();
				inventoryHandler(player);
				break;
			case "0":
				break;
			default:
				System.out.println("Comand not recognized. Try again.");
			}
			System.out.println("Press 1 to start the battlemode with your character.\n"
					+ "Press 2 to create another character and add them to the party.\n"
					+ "Press 3 to list all the characters currently in your party.\n"
					+ "Press 4 to change your current character.\n" + "Press 5 to view your current character.\n"
					+ "Press 6 to open the Inventory Menu.\n" + "Press 0 to end the game.");
			playerChoice = scan.next();
		}
	}

	// Implements the Inventory menu, where the player is able to choose an action
	// like looking at his inventory, using an item or visiting the shop.
	private static void inventoryHandler(Character player) {
		System.out.println("Inventory Menu \nPress 1 to view your Inventory.\n" + "Press 2 to use an Item.\n"
				+ "Press 3 to visit the shop\n" + "Press 0 to return to the main menu.");
		Scanner scan = new Scanner(System.in);
		String choice = scan.next();
		do {
			switch (choice) {
			case "1":
				System.out.println(player.getInventory());
				break;
			case "2":
				ItemHandler.chooseItem(player);
				break;
			case "3":
				shopHandler(player);
			}

			System.out.println("Inventory Menu \nPress 1 to view your Inventory.\n" + "Press 2 to use an Item.\n"
					+ "Press 3 to visit the shop\n" + "Press 0 to return to the main menu.");
			choice = scan.next();
		} while (!choice.equalsIgnoreCase("0"));
		System.out.println("");
	}

	private static void shopHandler(Character player) {
		Scanner scan = new Scanner(System.in);
		Shop shop = new Shop();
		System.out.println(shop);
		shop.buyItem(player, scan.next());
	}

}