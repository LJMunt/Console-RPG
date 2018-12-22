package itemPackage;

import characterPackage.Character;

public class CharacterInventory {
	private Item[] inventory;
	private int itemCount;
	private final int INVENTORY_MAX = 3;
	private Character owner;

	public CharacterInventory(Character owner) {
		this.inventory = new Item[INVENTORY_MAX];
		this.itemCount = 0;
		this.owner = owner;
	}

	public void addItem() {
		if (this.itemCount < this.INVENTORY_MAX) {
			Item newItem = this.createItem();
			this.inventory[itemCount] = newItem;
			itemCount++;
		}
	}

	public void addItem(ItemNames iNames) {
		if (this.itemCount < this.INVENTORY_MAX) {
			Item newItem = this.createItem(iNames);
			this.inventory[itemCount] = newItem;
			itemCount++;
		} else
			System.out.println("Inventory full.");
	}

	private Item createItem() {
		Item newItem = new Item(this.owner);
		return newItem;
	}

	private Item createItem(ItemNames iNames) {
		Item newItem = new Item(iNames, this.owner);
		return newItem;
	}

	private void delItem(String name) {
		int i = this.findIndex(name);
		this.inventory[i] = null;
		this.itemCount--;
	}

	public int useItem(String name) {
		int confirmation = 0;
		Integer i = this.findIndex(name);
		if (i != null) {
			this.inventory[i].use();
			this.delItem(name);
			confirmation = 1;
		}
		return confirmation;
	}

	private Integer findIndex(String name) {
		Integer index = null;
		for (int i = 0; i < this.itemCount; i++) {
			if (this.inventory[i].equals(name)) {
				index = i;
			} else {
				System.out.println("Item not found.");
				index = null;
			}

		}
		return index;
	}

	public String toString() {
		String returnString = "Inventory of " + this.owner.getName() + " : ";
		for (int i = 0; i < INVENTORY_MAX; i++) {
			if (inventory[i] == null)
				returnString += "";
			else
				returnString += inventory[i].getName() + ", ";
		}
		if (returnString.isEmpty())
			returnString = "empty";

		return returnString;
	}
}
