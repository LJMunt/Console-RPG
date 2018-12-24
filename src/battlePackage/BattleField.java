package battlePackage;

import java.util.Random;
import java.util.Scanner;

import characterPackage.CharClass;
import characterPackage.Character;
import characterPackage.Feats;
import shopPackage.Shop;

public class BattleField {

	private Terrain terrain;
	private Character enemy;
	private static int battleCount = 0;
	public static final int BASE_HP = 8;
	private static int maxHeals = 3;
	private static int playerHealCount = maxHeals;

	// Useless Constructor. Should probably do this differently. Keeping him here if
	// i'm adding more Variables in BattleField.
	public BattleField(Character player) {
		System.out.println("You are... ");
		System.out.println(player);

	}

	// creates a new enemy and starts the battle. Also levels the player and the
	// generated enemy and gives the player money.
	public void startNextBattle(Character player) {
		this.terrain = Terrain.getRandom();
		System.out.println("the Terrain is: " + this.terrain.getName());
		int enemyLevel = (BattleField.battleCount / 3) + 1;
		this.enemy = this.createEnemy(enemyLevel);
		System.out.println("The" + (BattleField.battleCount > 0 ? " next " : " first ")
				+ "battle is about to beginn. Your enemy is: \n" + this.enemy);
		this.Battle(player);
		battleCount++;
		playerHealCount = maxHeals;
		player.getGold().addRandomGold();
		player.levelUp();
		Shop.resetShopCount();
	}

	public static int getBattleCount() {
		return battleCount;
	}

	// Creates an Enemy at random from the enum Enemies and EnemyTitles. Der Feat
	// wird aus dem Enum feats genommen.
	private Character createEnemy(int lvl) {
		Random rnd = new Random();
		Enemies enemyStrings = Enemies.getRandom();
		EnemyTitles titles = EnemyTitles.getRandom();
		String enemyName = enemyStrings.getName() + " the " + titles.getTitle();
		String enemyBg = enemyStrings.getBackground();
		String enemyFeat = Feats.getRandom().getName();
		CharClass enemyClass = CharClass.getRandom();
		int enemyHP = BASE_HP + enemyClass.getMeleePower() + rnd.nextInt(6) + 1;
		Character enemy = new Character(enemyName, enemyBg, enemyFeat, enemyHP, enemyClass, false);
		enemy.setLevel(lvl);
		enemy.levelUp();
		return enemy;
	}

	// Guides the player through character creation with prompts.
	public static Character CharacterCreation() {
		Random rnd = new Random();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Name: ");
		String name = scan.nextLine();
		System.out.println("Enter your Background: ");
		String background = scan.nextLine();
		System.out.println("Enter your feat: ");
		String feat = scan.nextLine();
		CharClass playerClass = BattleField.chooseClass();
		int hp = BASE_HP + playerClass.getMeleePower() + rnd.nextInt(4) + 1;
		Character playerChar = new Character(name, background, feat, hp, playerClass, true);
		return playerChar;
	}

	// Lets the Player choose his class. Easily extendable once more classes are
	// added.
	private static CharClass chooseClass() {
		Scanner scan = new Scanner(System.in);
		CharClass playClass = null;
		System.out.println("Choose your Class. you may choose from the following:");
		System.out.println("Wizard   Fighter   Paladin   Rogue   Inquisitor   Death Knight");
		String choice = scan.nextLine();
		switch (choice) {
		case "Wizard":
			playClass = CharClass.Wizard;
			break;
		case "Fighter":
			playClass = CharClass.Fighter;
			break;
		case "Paladin":
			playClass = CharClass.Paladin;
			break;
		case "Rogue":
			playClass = CharClass.Rogue;
			break;
		case "Inquisitor":
			playClass = CharClass.Inquisitor;
			break;
		case "Death Knight":
			playClass = CharClass.Deathknight;
			break;
		default:
			System.out.println("Class not recognized.");
			playClass = BattleField.chooseClass();
		}
		return playClass;
	}

	// Main Battle method. Implements the Enemy AI at the moment (which is really
	// basic.) More improvements to come.
	public void Battle(Character player) {
		Scanner scan = new Scanner(System.in);
		System.out.println("The battle begins. Choose 1 to attack and 2 to heal yourself.");
		String playerChoice = scan.next();
		int playerHeal = this.healCalc(player);
		int enemyHeal = this.healCalc(this.enemy);
		Boolean playerWins = false;
		while (player.isAlive() && this.enemy.isAlive()) {
			switch (playerChoice) {
			case "1":
				player.attack(this.enemy);
				break;
			case "2":
				if (playerHealCount > 0) {
					playerHealCount--;
					player.heal(playerHeal);
					int remainingHeals = playerHealCount;
					System.out.println("Remaining Heals: " + remainingHeals);
				} else
					System.out.println("You have expended all your heals in this battle.");
				break;
			case "1337":
				if (player.getFeat().equalsIgnoreCase("Testcharacter")) {
					player.cheat(this.enemy);
					System.out.println("You dirty cheater...");
				} else
					System.out.println("You can't do that.");
				break;
			default:
				System.out.println("Command not recognized.");
			}
			// Enemy AI is defined here.
			if (this.enemy.getHealth() <= 5 && player.getHealth() > this.enemy.getHealth()) {
				this.enemy.heal(enemyHeal);
			} else
				this.enemy.attack(player);
			// System.out.println(this.enemy);
			// System.out.println(this.player);
			if (player.isAlive() && this.enemy.isAlive())
				playerChoice = scan.next();
		}
		playerWins = this.determineWinner(player);
		this.announceWinner(playerWins, player);
	}

	// Announces the Winner at the end of a battle. Uses determineWinner()
	private void announceWinner(Boolean playerWins, Character player) {
		int playerWinInt = (playerWins ? 1 : 0);
		switch (playerWinInt) {
		case 0:
			System.out.println("\n");
			System.out.println(this.enemy.getName() + " wins!");
			break;
		case 1:
			System.out.println("\n");
			System.out.println(player.getName() + " wins!");
		}
	}

	// Returns true if the Player wins and false if the enemey wins.
	private Boolean determineWinner(Character player) {
		Boolean playerWins = false;
		if (player.isAlive() && !this.enemy.isAlive()) {
			playerWins = true;
		} else {
			if (this.enemy.isAlive() && !player.isAlive())
				playerWins = false;
		}
		return playerWins;
	}

	// This is broken af. Uses the terrain Modifier to fuck up the players Healing.
	private int healCalc(Character character) {
		Random rnd = new Random();
		int heal = (BASE_HP + rnd.nextInt(3)) - this.terrain.getModifier();
		return heal;
	}

	public static int getMaxHeals() {
		return maxHeals;
	}

	public static void setMaxHeals(int maxHeals) {
		if (maxHeals > 0) {
			BattleField.maxHeals = maxHeals;
		}
	}

	public static void setBattleCount(int battleCount) {
		battleCount = BattleField.battleCount;

	}

}