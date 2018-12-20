package itemPackage;

import java.util.Random;

public enum ItemNames {
	PotionHeal("Potion of Healing", "Makes the Pain go away.... but at what cost?", 10),
	InvisPot("Potion of Invisibility", "Lets you evade attacks more easily", 18),
	StrengthPot("Potion of unnatural Strength", "Makes you Stronger, but may lead to cancer.", 12);

	private String iname, description;
	private int value;
	private static Random rnd = new Random();

	private ItemNames(String iname, String description, int value) {
		this.iname = iname;
		this.description = description;
		this.value = value;
	}

	public String getName() {
		return this.iname;
	}

	public String getDescription() {
		return this.description;
	}

	public int getValue() {
		return this.value;
	}

	public static ItemNames getRandom() {
		return ItemNames.values()[rnd.nextInt(ItemNames.values().length)];
	}
}
