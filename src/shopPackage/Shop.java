package shopPackage;

import java.util.Scanner;

import battlePackage.Battlefield;
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
		int mod = Battlefield.getBattleCount();
		if (mod < 100) {
			max = (mod / 5) + 1;
		}
		else {
			max = 20; }
		return max;
	}

	private void createShopInventory() {
		for (int i = 0; i < itemList.length; i++) {
			this.itemList[i] = new Item(this.insertWeightedItem(),this.keeper.getPerson());
		}
	}

	private ItemNames insertWeightedItem() {
		int mod = this.calculateItemWeight();
		ItemNames itemN = ItemNames.InconceivableItem;
		while(itemN.getWeight() > mod) {
			itemN = ItemNames.getRandom();
			}
		return itemN;
		}

	private int calculateItemWeight() {
		int itemWeight = (Battlefield.getBattleCount()/4)+1;
		return itemWeight;
	}
	
	private void deleteItem(Integer index) {
		this.itemList[index] = null;
	}

	private Integer findIndex(String name) {
		Integer index = 1;
		for (int i = 0; i < this.itemList.length; i++) {
			if (this.itemList[i].equals(name)) {
				index = i;
			} else {
				System.out.println("Item not found.");
				index = null;
			}
		}
		return index;
	}
	//Everything breaks here. Throws a NullPointerException
	//TODO Fix this.
	public void buyItem(Character player, String choice) {
				Integer index = this.findIndex(choice);
				this.deleteItem(index);
				player.getInventory().addItem(this.itemList[index].getItemName());
				System.out.println("Enter the name of the Item you wish to buy. Type 0 to leave this menu.");
	}
	
	public String toString() {
		String returnString = "Items in the Shop of "+this.keeper.getName()+ " : \n";
		for (int i = 0; i < itemList.length; i++) {
			if (itemList[i] == null)
				returnString += "";
			else
				returnString += itemList[i].getName() + "\n";
		}
		return returnString;

	}
	
}