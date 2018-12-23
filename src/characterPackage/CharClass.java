package characterPackage;

import java.util.Random;

//Character classes, used by player and enemy.
//Other classes can easily be added, but need to be synced with Battlefield.chooseClass().
public enum CharClass {

	Wizard(4, 12, "Fireball", "Magic", "Physical", "Wizard"), 
	Rogue(4, 8, "Backstab", "Shadow", "Light", "Rogue"),
	Paladin(5, 6, "Lay on Hands", "Light", "Shadow", "Paladin"),
	Fighter(6, 16, "Power Attack", "Physical", "Magic", "Fighter");

	private String ability, type, immunity;
	private int meleePower, damageDie;
	private String className;
	private static Random rnd = new Random();

	private CharClass(int meleePower, int damageDie, String ability, String type, String immunity, String className) {
		this.meleePower = meleePower;
		this.ability = ability;
		this.immunity = immunity;
		this.damageDie = damageDie;
		this.type = type;
		this.className = className;
	}

	public int getMeleePower() {
		return meleePower;
	}

	public String getType() {
		return type;
	}

	public String getImmunity() {
		return immunity;
	}

	public int getDamageDie() {
		return this.damageDie;
	}

	public String getAbillity() {
		return this.ability;
	}

	public String getClassName() {
		return this.className;
	}

	public static CharClass getRandom() {
		return CharClass.values()[rnd.nextInt(CharClass.values().length)];
	}
}
