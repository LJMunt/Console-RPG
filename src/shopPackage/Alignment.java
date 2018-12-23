package shopPackage;

import java.util.Random;

public enum Alignment {
	LG("Lawful Good", 1),
	NG("Neutral Good", 0.8),
	CG("Chaotic Good", 0.9),
	LN("Lawful Neutral", 1),
	N("Neutral", 1),
	CN("Chaotic Neutral", 1.5),
	LE("Lawful Evil", 1),
	NE("Neutral Evil", 1.8),
	CE("Chaotic Evil", 2);
	
	private String alignment;
	private double modifier;
	private static Random rnd = new Random();
	
	private Alignment(String align, double mod) {
		this.alignment = align;
		this.modifier = mod;
	}
	
	public String getAlignment() {
		return alignment;
	}

	public double getModifier() {
		return modifier;
	}
	
	public static Alignment getRandom() {
		return Alignment.values()[rnd.nextInt(Alignment.values().length)];
	}
	
}
