package battlePackage;

import java.util.Random;

public enum Enemies {
	// All possible Enemy Names. Gender is not used at the moment.
	Algir(false, "Warrior"), Arik(false, "Hero"), Torvald(false, "Smith"), Akahan(false, "Noble"),
	Ulaban(false, "Villager"), Grom(false, "Servant"), Gror(false, "Hero"), Kritzix(false, "Merchant"),
	Alvarion(false, "Priest"), Marcian(false, "Knight"), Sogrim(false, "Dwarf"), Van(false, "Artist"),
	Erza(true, "Warrior"), Avalia(true, "Priest"), Selena(true, "Noble"), Kyriana(true, "Goddess"),
	Dorena(true, "Villager"), Narmora(true, "Merchant"), Namira(true, "Hero"), Leandra(true, "Smith"),
	Grotoka(true, "Dwarf"), Eirene(true, "Hero");

	private boolean female;
	private String background;
	private static Random rnd = new Random();

	private Enemies(boolean female, String background) {
		this.female = female;
		this.background = background;
	}

	public boolean isFemale() {
		return female;
	}

	public String getBackground() {
		return background;
	}

	// Chooses a random name.
	public static Enemies getRandom() {
		return Enemies.values()[rnd.nextInt(Enemies.values().length)];
	}

	public String getName() {
		String name = this.name();
		return name;
	}
}