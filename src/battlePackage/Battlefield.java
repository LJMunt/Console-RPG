package battlePackage;

import java.util.Random;
import java.util.Scanner;

import characterPackage.CharClass;
import characterPackage.Character;

public class Battlefield {
	
	private Terrain terrain;
	private Character player;
	private Character enemy;
	private static int battleCount = 0;
	
	public Battlefield(Terrain terrain, Character player) {
		this.terrain = Terrain.getRandom();
		this.player = player;
		System.out.println("You are... ");
		System.out.println(player);
		

	}

	public void startNextBattle(Character player) {
		int enemyLevel = (Battlefield.battleCount/3)+1;
		this.enemy = this.createEnemy(enemyLevel);
		System.out.println("The"+(Battlefield.battleCount>0? " next ":" first ")+"battle is about to beginn. your Enemy is: \n"+this.enemy);
		this.Battle(player);
		battleCount++;
		player.levelUp();
	}
	
	
	public static int getBattleCount() {
		return battleCount;
	}

	
	private Character createEnemy(int lvl) {
		Random rnd = new Random();
		Enemies enemyStrings = Enemies.getRandom();
		EnemyTitles titles = EnemyTitles.getRandom();
		String enemyName = enemyStrings.getName()+" the "+titles.getTitle();
		String enemyBg = enemyStrings.getBackground();
		String enemyFeat = enemyStrings.getFeat();
		CharClass enemyClass = CharClass.getRandom();
		int enemyHP = 8+enemyClass.getMeleePower()+rnd.nextInt(6)+1;
		Character enemy = new Character(enemyName ,enemyBg ,enemyFeat , enemyHP, enemyClass, false);
		enemy.setLevel(lvl);
		return enemy;
	}



	public static Character CharacterCreation() {
		Random rnd = new Random();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Name: ");
		String name = scan.nextLine();
		System.out.println("Enter your Background: ");
		String background = scan.nextLine();
		System.out.println("Enter your feat: ");
		String feat = scan.next();
		CharClass playerClass = Battlefield.chooseClass();
		int hp = 8+playerClass.getMeleePower()+rnd.nextInt(4)+1;
		Character playerChar = new Character(name, background, feat, hp, playerClass, true);
		return playerChar;
	}

	private static CharClass chooseClass() {
		Scanner scan = new Scanner(System.in);
		CharClass playClass = null;
		System.out.println("Choose your Class. you may choose from: \n\tWizard \tFighter \tPaladin \tRogue");
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
		default:
			System.out.println("Class not recognized.");
			playClass = Battlefield.chooseClass();
		}
		return playClass;
	}
	
	public void Battle(Character player) {
		Scanner scan = new Scanner(System.in);
		System.out.println("The battle begins. Choose 1 to attack and 2 to heal yourself.");
		int playerChoice = scan.nextInt();
		int playerHeal = this.healCalc(player);
		int enemyHeal = this.healCalc(this.enemy);
		Boolean playerWins = false;
		while (player.isAlive() && this.enemy.isAlive()) {
			switch(playerChoice) {
			case 1:
				player.attack(this.enemy);
				break;
			case 2:
				player.heal(playerHeal);
				break;
			case 1337:
				if (player.getFeat().equalsIgnoreCase("Testcharacter")) {
					player.cheat(this.enemy);
					System.out.println("You dirty cheater..."); }
				else
					System.out.println("You can't do that.");
				break;
			default:
				System.out.println("Command not recognized.");
			}
			if (this.enemy.getHealth() <= 5 && player.getHealth() > this.enemy.getHealth()) {
				this.enemy.heal(enemyHeal);
			}
			else
				this.enemy.attack(player);
			//System.out.println(this.enemy);
			//System.out.println(this.player);
			if (player.isAlive() && this.enemy.isAlive())
				playerChoice = scan.nextInt();
		}
		playerWins = this.determineWinner(player);
		this.announceWinner(playerWins);
	}

	private void announceWinner(Boolean playerWins) {
		int playerWinInt = (playerWins? 1 : 0);
		switch (playerWinInt) {
		case 0:
			System.out.println("\n");
			System.out.println(this.enemy+" wins!");
			break;
		case 1:
			System.out.println("\n");
			System.out.println(this.player.getName()+" wins!");
		}
	}

	private Boolean determineWinner(Character player) {
		Boolean playerWins = false;
		if (player.isAlive() && !this.enemy.isAlive()) {
			playerWins = true;
		}
		else {
			if (this.enemy.isAlive() && !player.isAlive())
				playerWins = false;
		}
		return playerWins;
	}

	private int healCalc(Character character) {
		Random rnd = new Random();
		double heal = 6;
		if (character.getHealth() > 1)
		{
		heal = character.getHealth()/2+rnd.nextInt(4+character.getLevel());	}
		if (heal > this.terrain.getModifier()) {
			heal=-this.terrain.getModifier(); }
		else
			heal+=4;
		return (int) heal;

}
}
