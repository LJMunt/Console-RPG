package itemPackage;

public class CharacterInventory {
	private Item[] inventory;
	private int itemCount;
	private final int INVENTORY_MAX = 3;
	
	public CharacterInventory() {
		this.inventory = new Item[INVENTORY_MAX];
		this.itemCount = 0;	
		
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
		Item newItem = new Item();
		return newItem;
	}
	private Item createItem(ItemNames iNames) {
		Item newItem = new Item(iNames);
		return newItem;
	}

}
