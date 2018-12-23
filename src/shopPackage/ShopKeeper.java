package shopPackage;

import characterPackage.CharClass;
import characterPackage.Character;

public class ShopKeeper {
	private String name;
	private Alignment align;
	private int age;
	private Character person;

	public ShopKeeper(String name, Alignment align, int age) {
		this.name = name;
		this.align = align;
		this.age = age;
		this.person = new Character(this.name, "Shopkeeper", "Merchant", 8, CharClass.Fighter, false);

	}

	public static ShopKeeper createKeeper() {
		KeeperNames kname = KeeperNames.getRandom();
		String name = kname.getName();
		int age = kname.getAge();
		ShopKeeper newKeeper = new ShopKeeper(name, Alignment.getRandom(), age);
		return newKeeper;
	}

	public String getName() {
		return name;
	}

	public Alignment getAlign() {
		return align;
	}

	public Character getPerson() {
		return this.person;
	}

	public int getAge() {
		return age;
	}

}
