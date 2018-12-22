package characterPackage;

import java.util.Random;

public enum Denomination {

	ONE("one", 1), FIVE("five", 5), TEN("ten", 10), TWENTY("twenty", 20), FIFTY("fifty", 50),
	ONEHUNDRED("hundred", 100);

	private String name;
	private int value;
	private static Random rnd = new Random();

	private Denomination(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public static Denomination getRandom() {
		return Denomination.values()[rnd.nextInt(Denomination.values().length)];

	}
}
