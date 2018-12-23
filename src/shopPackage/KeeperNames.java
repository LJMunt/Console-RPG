package shopPackage;

import java.util.Random;

public enum KeeperNames {
	MARC("Marc"), VINCENT("Vincent"), TATSUMA("Tatsuma"), ROBIN("Robin"), NOEL("Noel"), PATRICK("Patrick"),
	CHANGBO("Chang Bo"), JAN("Jan"), MICHAEL("Michael"), ELIAS("Elias");

	private String name;
	private int age;
	private static Random rnd = new Random();

	private KeeperNames(String name) {
		Random rnd = new Random();
		this.name = name;
		this.age = rnd.nextInt(30) + 16;
	}

	public static KeeperNames getRandom() {
		return KeeperNames.values()[rnd.nextInt(KeeperNames.values().length)];
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

}
