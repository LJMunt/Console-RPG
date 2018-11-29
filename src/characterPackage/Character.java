package characterPackage;

import java.util.Random;

import battlePackage.Battlefield;

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
			finalAttack = rawAttackDie + this.characterClass.getMeleePower()+this.levelModify();
			break;

		}
			
		return finalAttack;
	}


	public String toString() {
		String definition;
		definition = this.name+"\t"+
		this.characterClass.getClassName()+"\tLevel "+this.level+
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

	public void cheat(Character otherCharacter) {
		this.health = 100;
		this.ArmorClass = 100;
		this.inflictDamage(100, otherCharacter, "Cheat");
	}
	
	public void levelUp() {
		if(this.alive && Battlefield.getBattleCount() % 3 == 0) {
			this.level++;
			if (this.playerControlled)
				System.out.println(this.name+" is now level "+this.level);}
		this.health+=this.levelModify();
		this.ArmorClass+=(this.levelModify()/2);
	}
	
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

	
	public boolean equals(Character otherCharacter) {
		if (this.name == otherCharacter.getName())
			return true;
		else
			return false;
	}
	
}

	
	
