package itemPackage;

import characterPackage.Character;

public class Item {
	private int value;
	private String name, description;
	private ItemNames itemName;
	private static int itemCount;
	private Character user;

	public Item(Character user) {
		this.user = user;
		this.itemName = ItemNames.getRandom();
		this.value = this.itemName.getValue();
		this.name = this.itemName.getName();
		this.description = this.itemName.getDescription();
		itemCount++;
	}

	public Item(ItemNames iName, Character user) {
		this.user = user;
		this.itemName = iName;
		this.value = this.itemName.getValue();
		this.name = this.itemName.getName();
		this.description = this.itemName.getDescription();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public static int getItemCount() {
		return itemCount;
	}

	public static void setItemCount(int itemCount) {
		Item.itemCount = itemCount;
	}

	public void use() {
		System.out.println(this.user.getName() + " " + "uses " + this.name);

	}

	public boolean equals(Item otherItem) {
		Boolean equals = false;
		if (this.name == otherItem.getName()) {
			equals = true;
		} else
			equals = false;
		return equals;
	}

	public boolean equals(String otherName) {
		Boolean equals = false;
		if (this.name == otherName) {
			equals = true;
		} else
			equals = false;
		return equals;
	}

	public String toString() {
		return this.name + " " + this.description + " Cost: " + this.value;
	}

}