package shopPackage;

import battlePackage.BattleField;
import itemPackage.Item;
import itemPackage.ItemNames;
import characterPackage.Character;

public class Shop {
	private Item[] itemList;
	private ShopKeeper keeper;
	private static int shopCount;
	private int maxSize;

	public Shop() {
		this.maxSize = this.determineMax();
		this.itemList = new Item[maxSize];
		this.keeper = ShopKeeper.createKeeper();
		Shop.shopCount++;
		this.createShopInventory();
	}

	private int determineMax() {
		int max = 1;
		int mod = BattleField.getBattleCount();
		if (mod < 100) {
			max = (mod / 5) + 1;
		} else {
			max = 20;
		}
		return max;
	}

	private void createShopInventory() {
		for (int i = 0; i < itemList.length; i++) {
			this.itemList[i] = new Item(this.insertWeightedItem(), this.keeper.getPerson());
		}
	}

	private ItemNames insertWeightedItem() {
		int mod = this.calculateItemWeight();
		ItemNames itemN = ItemNames.InconceivableItem;
		while (itemN.getWeight() > mod) {
			itemN = ItemNames.getRandom();
		}
		return itemN;
	}

	private int calculateItemWeight() {
		int itemWeight = (BattleField.getBattleCount() / 4) + 1;
		return itemWeight;
	}

	private void deleteItem(Integer index) {
		this.itemList[index] = null;
	}
	
	//TODO Fix this.
	// Something breaks here when trying to buy multiple items.
	private Integer findIndex(String name) {
		Integer index = 1;
		for (int i = 0; i < this.itemList.length;) {
			System.out.println(i);
			if (this.itemList[i].equals(name)) {
				index = i;
				System.out.println(i);
				return index;
			} else {
				index = null;
				return index;
			}
		}
		return index;
	}

	public void buyItem(Character player, String choice) {
		Integer index = this.findIndex(choice);
		if (index != null) {
			if (player.getGold().getValue() >= this.itemList[index].getValue()) {
				player.getInventory().addItem(this.itemList[index].getItemName());
				System.out.println(player.getName() + " buys " + this.itemList[index].getName());
				player.getGold().payGold(this.itemList[index]);
				this.deleteItem(index);
			} else {
				System.out.println(this.itemList[index].getName() + " costs too much. You need "
						+ this.itemList[index].getValue() + " Gold.");
			}
		} else {
			System.out.println("item not found.");
		}
	}

	public String toString() {
		String returnString = "Items in the Shop of " + this.keeper.getName() + " : \n";
		for (int i = 0; i < this.itemList.length; i++) {
			if (this.itemList[i] == null)
				returnString += "";
			else
				returnString += this.itemList[i].getName() + "  |  Cost: " + this.itemList[i].getValue() + " Gold "
						+ "\n";
		}
		return returnString;

	}

	public static int getShopCount() {
		return Shop.shopCount;
	}

	public static void resetShopCount() {
		Shop.shopCount = 0;
	}

}