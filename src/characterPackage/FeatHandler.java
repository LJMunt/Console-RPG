package characterPackage;

import battlePackage.BattleField;
import itemPackage.ItemNames;

public abstract class FeatHandler {

	private static final int FEAT_SL_CONSTANT = 5;
	private static final int FEAT_CN_CONSTANT = 4;
	private static final int FEAT_GP_CONSTANT = 3;
	private static final int FEAT_PA_CONSTANT = 2;

	public static void parseFeat(Character character) {
		String feat = character.getFeat();
		switch (feat) {
		case "Corrupted": // 1
			handleCorrupted(character);
			break;
		case "Homosexual": // 2
			handleHomosexual(character);
			break;
		case "Great Power": // 3
			handleGreatPower(character);
			break;
		case "Unholy": // 4
			handleUnholy(character);
			break;
		case "Holy": // 5
			handleHoly(character);
			break;
		case "Good": // 6
			handleGood(character);
			break;
		case "Submissive": // 7
			handleSubmissive(character);
			break;
		case "Conniving": // 8
			handleConniving(character);
			break;
		case "Stone Lord": // 9
			handleStoneLord(character);
			break;
		case "Evil": // 10
			handleEvil(character);
			break;
		case "Wealthy": // 11
			handleWealthy(character);
			break;
		case "Performance Artist": // 12
			handlePerformanceArtist(character);
			break;
		default:
			return;
		}
	}

	// The feat Performance Artist increases max Heals by 2
	private static void handlePerformanceArtist(Character character) {
		BattleField.setMaxHeals(BattleField.getMaxHeals() + FEAT_PA_CONSTANT);
	}

	// Wealthy increases a characters starting capital by 10
	private static void handleWealthy(Character character) {
		character.getGold().setValue(10);

	}

	// Makes the character resistant against light attacks. If he already is
	// resistant to light, he gains 1 AC instead.
	private static void handleEvil(Character character) {
		if (!character.getCharResistance().equalsIgnoreCase("Light")) {
			character.setCharResistance("Light");
		} else {
			character.setArmorClass(character.getArmorClass() + 1);
		}

	}

	// Increases Character Level by an amount.
	private static void handleStoneLord(Character character) {
		character.setHealth(character.getHealth() + FEAT_SL_CONSTANT);

	}

	// Increases Character AC by an amount. Lowers Character power by half that
	// amount..
	private static void handleConniving(Character character) {
		character.setArmorClass(character.getArmorClass() + FEAT_CN_CONSTANT);
		character.setPower(character.getPower() - (FEAT_CN_CONSTANT / 2));
	}

	// Adds a Potion of Invisibility to the Characters Inventory at start.
	private static void handleSubmissive(Character character) {
		character.getInventory().addItem(ItemNames.InvisPot);
	}

	// Changes the Characters Resistance to Shadow. If the Character already has
	// shadow resistance, he gains 1 AC instead.
	private static void handleGood(Character character) {
		if (!character.getCharResistance().equalsIgnoreCase("Shadow")) {
			character.setCharResistance("Shadow");
		} else {
			character.setArmorClass(character.getArmorClass() + 1);
		}
	}

	// Changes the Characters Resistance to Unholy. If the Character already has
	// unholy resistance, he gains 1 AC instead.
	private static void handleHoly(Character character) {
		// TODO Add Inquisitor class
		if (!character.getCharResistance().equalsIgnoreCase("Unholy")) {
			character.setCharResistance("Unholy");
		} else {
			character.setArmorClass(character.getArmorClass() + 1);
		}

	}

	// Changes the Characters resistance to holy, if the character already has holy
	// resistance, he gains 1 AC instead.
	private static void handleUnholy(Character character) {
		// TODO Add Death Knight class.
		if (!character.getCharResistance().equalsIgnoreCase("Holy")) {
			character.setCharResistance("Holy");
		} else {
			character.setArmorClass(character.getArmorClass() + 1);
		}
	}

	// Increases Character Power by an amount.
	private static void handleGreatPower(Character character) {
		character.setPower(character.getPower() + FEAT_GP_CONSTANT);
	}

	// Increases character Health by a dynamic amount that increases with time.
	private static void handleHomosexual(Character character) {
		int mod = (BattleField.getBattleCount() / 4) + 1;
		character.setHealth(character.getHealth() + mod);
	}

	// Halves Character Health, doubles character Power.
	private static void handleCorrupted(Character character) {
		character.setHealth(character.getHealth() / 2);
		character.setPower(character.getPower() * 2);
	}
}
