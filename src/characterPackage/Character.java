package characterPackage;

import java.util.Random;

public class Character {
	Random rand = new Random();
	private String name, background, feat;
	private double health;
	private boolean alive;
	private CharClass characterClass;
	public static int characterCount = 0;
	private int ArmorClass;
	
	
	public Character(String name, String background,String feat, int health, CharClass characterClass) {
		this.name = name;
		this.background = background;
		this.feat = feat;
		this.health = health;
		this.characterClass = characterClass;
		this.alive = true;
		this.setArmorClass(this.characterClass.getMeleePower()+10+rand.nextInt(3)+1);
		characterCount++;
	}


	private void setArmorClass(int armorClass) {
		this.ArmorClass = armorClass;
		
	}


	public boolean isAlive() {
		return alive;
	}


	public double getHealth() {
		return health;
	}


	public String getName() {
		return name;
	}


	public String getBackground() {
		return background;
	}


	public String getFeat() {
		return feat;
	}


	public CharClass getCharacterClass() {
		return characterClass;
	}
	
	private void damage(int dmg, String type) {
		String charImmunity = this.characterClass.getImmunity();
		if (alive == true ) {
			if (!type.equals(charImmunity)) {
				this.health -= dmg;
				if (this.health <= 0) {
					this.die();
				}
			}
			else
				System.out.println(this.name+" is immune against "+type);
		}
	}


	private void inflictDamage(int dmg, Character otherCharacter, String type) {
		otherCharacter.damage(dmg, type);
	}
	
	
	private void die() {
		if (this.health <= 0) {
		this.alive = false;
		System.out.println(this.name+" dies."); }
	}
	
	public void attack(Character otherCharacter) {
		Random attackDice = new Random();
		int dmg = attackDice.nextInt(this.characterClass.getDamageDie())+1;
		if (this.alive == true && otherCharacter.isAlive()) {
			if (this.hit(otherCharacter)) {
				System.out.println(this.name+
						" uses "+this.characterClass.getAbillity()+
						" for "+dmg+" "+this.characterClass.getType()+
						" damage.");
				this.inflictDamage(dmg, otherCharacter, this.characterClass.getType());
			}
			else 
				System.out.println(this.name+" misses!");
		}
	}
	
	private boolean hit(Character otherCharacter) {
		boolean doesHit;
		int attack = this.attackThrow();
		if (attack >= otherCharacter.getArmorClass() || attack == 1)
			if (attack == 1) {
				this.crit(otherCharacter);
				doesHit = true;
			}
			else 
				doesHit = true;
		else
			if (attack == 0) {
				this.fumble(this.characterClass.getDamageDie(),this.characterClass.getType());
				doesHit = false;
			}
			else 
				doesHit = false;
		return doesHit;
	}


	private void fumble(int dmg, String type) {
		System.out.println(this.name+" fumbles!");
		this.damage(dmg, type);
	}


	private void crit(Character otherCharacter) {
		Random rand = new Random();
		int critdmg = rand.nextInt(this.characterClass.getDamageDie())+1;
		System.out.println(this.name+" crits and does "+critdmg+" damage!");
		this.inflictDamage(critdmg, otherCharacter, "Crit Damage");
		
	}


	private int attackThrow() {
		Random rand = new Random();
		int rawAttackDie = rand.nextInt(20)+1;
		int finalAttack;
		switch (rawAttackDie) {
		case 1:
			finalAttack = 0;
			break;
		case 20:
			finalAttack = 1;
			break;
		default:
			finalAttack = rawAttackDie + this.characterClass.getMeleePower();
			break;

		}
			
		return finalAttack;
	}


	public String toString() {
		String definition;
		definition = this.name+"\t"+
		this.characterClass.getClassName()+
		"\n"+"Health: "+this.health+"\t"+"Armor Class: "+this.ArmorClass+"\t"+
		"Background: "+ this.background+"\t"+"Feat: "+this.feat;
		
		return definition;
	}
	
	public void heal(int heal) {
		if (this.alive == true && this.health > 0) {
		this.health+= heal;
		System.out.println(this.name+" heals for "+heal); }
	}


	public int getArmorClass() {
		return ArmorClass;
	}

}

	
	
