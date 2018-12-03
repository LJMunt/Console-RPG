package characterPackage;

import java.util.Random;

import battlePackage.Battlefield;
//this is a massive Class. Could probably be made a little bit smaller (but i'll extend it.) Used to generate a character.
public class Character {
	Random rand = new Random();
	private String name, background, feat;
	private double health;
	private boolean alive;
	private CharClass characterClass;
	public static int characterCount = 0;
	private int ArmorClass;
	private int level = 1;
	private boolean playerControlled;
	
	
	public Character(String name, String background,String feat, int health, CharClass characterClass, boolean playerControlled) {
		this.name = name;
		this.background = background;
		this.feat = feat;
		this.health = health;
		this.characterClass = characterClass;
		this.alive = true;
		this.playerControlled = playerControlled;
		this.setArmorClass(this.characterClass.getMeleePower()+10+rand.nextInt(3)+1);
		characterCount++;
	}

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
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
	
	
	public boolean isPlayerControlled() {
		return playerControlled;
	}
	//Implements the damage a player takes.
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

	//activates the Damage Method in the other Character.
	private void inflictDamage(int dmg, Character otherCharacter, String type) {
		otherCharacter.damage(dmg, type);
	}
	
	//confirms the character death and sets alive false.
	private void die() {
		if (this.health <= 0) {
		this.alive = false;
		System.out.println(this.name+" dies."); }
	}
	//Base Combat Method. Uses Character.hit() to check if the char hits.
	//Then it gives out an attack message and uses inflictDamage.
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
	//Checks if the characters AttackThrow hits the enemy. 
	//Also implements crit() and fumble() which are at 20 and 1 respectively.
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

	//Characters take damage if they roll a natural 1.
	private void fumble(int dmg, String type) {
		System.out.println(this.name+" fumbles!");
		this.damage(dmg, type);
	}

	//Characters do extra damage if they roll a natural 20.
	private void crit(Character otherCharacter) {
		Random rand = new Random();
		int critdmg = rand.nextInt(this.characterClass.getDamageDie())+1;
		System.out.println(this.name+" crits and does "+critdmg+" damage!");
		this.inflictDamage(critdmg, otherCharacter, "Crit Damage");
		
	}

	//Calculates the characters attackThrow that is evaluated by hit. Gives out 0 if the char fumbles and 1 if he crits.
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
			finalAttack = rawAttackDie + this.characterClass.getMeleePower()+this.levelModify();
			break;

		}
			
		return finalAttack;
	}

	//Should be formatted better.
	public String toString() {
		String definition;
		definition = this.name+"\t"+
		this.characterClass.getClassName()+"\tLevel "+this.level+
		"\n"+"Health: "+this.health+"\t"+"Armor Class: "+this.ArmorClass+"\t"+
		"Background: "+ this.background+"\t"+"Feat: "+this.feat;
		
		return definition;
	}
	//restores health to the character. 
	public void heal(int heal) {
		if (this.alive == true && this.health > 0) {
		this.health+= heal;
		System.out.println(this.name+" heals for "+heal); }
	}


	public int getArmorClass() {
		return ArmorClass;
	}
	//instantly does 100 damage to the other character.
	public void cheat(Character otherCharacter) {
		this.health = 100;
		this.ArmorClass = 100;
		this.inflictDamage(1337, otherCharacter, "Cheat");
	}
	//Determines if the character gets a level up and uses levelModify() to apply the changes.
	public void levelUp() {
		if(this.alive && Battlefield.getBattleCount() % 3 == 0) {
			this.level++;
			if (this.playerControlled)
				System.out.println(this.name+" is now level "+this.level);}
		this.health+=this.levelModify();
		this.ArmorClass+=(this.levelModify()/2);
	}
	//Generates a specific modifier based on character level.
	public int levelModify() {
		int mod = 1;
		if (this.level < 10) {
			mod = level;
		}
		else {
			if (this.level < 20) {
				mod = level+4;
			}
			else {
				mod = level+6;
			}
		}
		
		return mod;
	}

	//Checks if 2 characters have the same name (later used to search for characters in PlayerParty)
	public boolean equals(Character otherCharacter) {
		if (this.name == otherCharacter.getName())
			return true;
		else
			return false;
	}
	
}