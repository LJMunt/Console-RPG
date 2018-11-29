package battlePackage;

import java.util.Random;

public enum Enemies {
	
	Algir(false, "Warrior", "Corrupted"),
	Arik(false, "Hero", "Great Power"),
	Torvald(false, "Smith", "Evil"),
	Akahan(false, "Noble", "Homosexual"),
	Ulaban(false, "Villager", "Run"),
	Grom(false, "Servant", "Submissive"),
	Gror(false, "Hero", "Great Power"),
	Kritzix(false, "Merchant", "Conniving"),
	Alvarion(false, "Priest", "Holy"),
	Marcian(false, "Knight", "Unholy"),
	Sogrim(false, "Dwarf", "Stonelord"),
	Van(false, "Artist", "Performance Artist"),
	Erza(true, "Warrior", "Holy"),
	Avalia(true, "Priest", "Unholy"),
	Selena(true, "Noble", "Corrupted"),
	Kyriana(true, "Goddess", "Good"),
	Ayatra(true, "Goddess", "Corrupted"),
	Dorena(true, "Villager", "Run"),
	Narmora(true, "Merchant", "Submissive"),
	Namira(true, "Hero", "Corrupted"),
	Leandra(true, "Smith", "Stonelord"),
	Grotoka(true, "Dwarf", "Homosexual"),
	Eirene(true, "Hero", "Holy");
	
	private boolean female;
	private String background;
	private String feat;
	private static Random rnd = new Random();
	
	private Enemies(boolean female, String background, String feat) {
		this.female = female;
		this.background = background;
		this.feat = feat;
	}

	public boolean isFemale() {
		return female;
	}

	

	public String getBackground() {
		return background;
	}

	public String getFeat() {
		return feat;
	}
	
	public static Enemies getRandom() {
		return Enemies.values()[rnd.nextInt(Enemies.values().length)];
	}

	public String getName() {
		String name = this.name();
		return name;
	}
}