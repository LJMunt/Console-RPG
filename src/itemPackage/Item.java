package itemPackage;

public class Item {
	private int value;
	private String name, description;
	private ItemNames itemName;
	private static int itemCount;
	
	public Item() {
		this.itemName = ItemNames.getRandom();
		this.value = this.itemName.getValue();
		this.name = this.itemName.getName();
		this.description = this.itemName.getDescription();
	}
	public Item(ItemNames iName) {
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

	
}

