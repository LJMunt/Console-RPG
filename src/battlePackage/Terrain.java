package battlePackage;

import java.util.Random;

//Terrain modifiers influence character healing.
public enum Terrain {
	Forest("Forest", 2), Hills("Hills", 1), Plains("Plains", 0), Wasteland("Wasteland", 3);
	private String name;
	private int modifier;
	private static Random rnd = new Random();

	public String getName() {
		return name;
	}

	public int getModifier() {
		return modifier;
	}

	private Terrain(String name, int modifier) {
		this.name = name;
		this.modifier = modifier;
	}

	public static Terrain getRandom() {
		return Terrain.values()[rnd.nextInt(Terrain.values().length)];
	}

}
