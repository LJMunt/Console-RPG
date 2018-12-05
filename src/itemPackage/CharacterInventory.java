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
	
	public void additem() {
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
		}
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
	public void useItem(String name) {
		int i = this.findIndex(name);
		this.inventory[i].use();
		this.delItem(name);
		
	}
	
	private Integer findIndex(String name) {
		Integer index = 0;
		for (int i = 0; i < this.itemCount;i++) {
			if (this.inventory[i].equals(name)) {
				index = i;
			}
			else {
				System.out.println("Item not found.");
				return null;
			}
				
		}
		
		return index;
	}

}
