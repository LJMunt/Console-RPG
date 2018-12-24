package characterPackage;

import java.util.Random;

public enum Feats {

	CORRUPTED("Corrupted"), HOMOSEXUAL("Homosexual"), GRPOWER("Great Power"), UNHOLY("Unholy"), HOLY("Holy"),
	GOOD("Good"), SUBMISSIVE("Submissive"), CONNIVING("Conniving"), STONELORD("Stone Lord"), EVIL("Evil"),
	WEALTHY("Wealthy"), PERFARTIST("Performance Artist");

	private String name;
	private static Random rnd = new Random();

	private Feats(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static Feats getRandom() {
		return Feats.values()[rnd.nextInt(Feats.values().length)];
	}

}
