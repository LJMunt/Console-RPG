package driverPackage;

import java.util.Random;
import java.util.Scanner;
import characterPackage.Character;

public abstract class ItemHandler {
	private final static int HEAL_CONSTANT = 12;
	private final static int AC_CONSTANT = 2;
	private static final int POWER_CONSTANT = 3;
	private static Random rnd = new Random();
	private static int healMod = 5;
	private static int HandlerLicense = rnd.nextInt(1337) + 1;

	public static int getHandlerLicense() {
		return HandlerLicense;
	}

	public static void setHandlerLicense(int handlerLicense) {
		HandlerLicense = handlerLicense;
	}

	protected static void handlePotionHeal(Character player, String name) {
		int healAmount = HEAL_CONSTANT + ItemHandler.rnd.nextInt(ItemHandler.healMod);
		int confirmation = player.getInventory().useItem(name);
		if (confirmation == 1) {
			player.heal(healAmount);
		} else
			return;

	}

	protected static void handleInvisPot(Character player, String name) {
		int currAC = player.getArmorClass();
		int newAC = currAC + AC_CONSTANT;
		int confirmation = player.getInventory().useItem(name);
		if (confirmation == 1) {
			player.setArmorClass(newAC);
		} else
			return;

	}

	protected static void handleStrengthPot(Character player, String name) {
		int currPower = player.getPower();
		int newPower = currPower + POWER_CONSTANT;
		int confirmation = player.getInventory().useItem(name);
		if (confirmation == 1) {
			player.setPower(newPower);
		} else
			return;

	}

	public static void chooseItem(Character player) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of the Item you wish to use.");
		String itemChoice = scan.nextLine();
		String healPot = "Potion of Healing";
		String invPot = "Potion of Invisiblity";
		String strPot = "Potion of unnatural Strength";
		switch (itemChoice) {
		case "Potion of Healing":
			handlePotionHeal(player, healPot);
			break;
		case "potion of Healing":
			handlePotionHeal(player, healPot);
			break;
		case "potion of healing":
			handlePotionHeal(player, healPot);
			break;
		case "Potion of Invisibility":
			handleInvisPot(player, invPot);
			break;
		case "potion of invisibility":
			handleInvisPot(player, invPot);
			break;
		case "potion of Invisibility":
			handleInvisPot(player, invPot);
			break;
		case "Potion of unnatural Strength":
			handleStrengthPot(player, strPot);
			break;
		case "potion of unnatural strength":
			handleStrengthPot(player, strPot);
			break;
		case "potion of unnatural Strength":
			handleStrengthPot(player, strPot);
			break;
		case "Potion of Strength":
			handleStrengthPot(player, strPot);
			break;
		default:
			System.out.println("this item is not in your characters inventory.");
			break;
		}
	}
}
