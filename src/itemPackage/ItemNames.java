package itemPackage;

import java.util.Random;


public enum ItemNames {
	PotionHeal("Potion of Healing", "Makes the Pain go away.... but at what cost?", 10);
	
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
