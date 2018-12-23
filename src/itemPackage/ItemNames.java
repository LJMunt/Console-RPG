package itemPackage;

import java.util.Random;

public enum ItemNames {
	PotionHeal("Potion of Healing", "Makes the Pain go away.... but at what cost?", 10, 1),
	InvisPot("Potion of Invisibility", "Lets you evade attacks more easily", 20, 2),
	StrengthPot("Potion of unnatural Strength", "Makes you Stronger, but may lead to cancer.", 25, 3),
	InconceivableItem("The Inconceivable Item", "If you see this, something has gone horribly wrong.", 0, 536870912);
	private String iname, description;
	private int value, weight;
	private static Random rnd = new Random();

	private ItemNames(String iname, String description, int value, int weight) {
		this.iname = iname;
		this.description = description;
		this.value = value;
		this.weight = weight;
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

	public int getWeight() {
		return this.weight;
	}

}
