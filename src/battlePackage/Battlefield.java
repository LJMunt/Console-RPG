package battlePackage;

import java.util.Random;
import java.util.Scanner;

import characterPackage.CharClass;
import characterPackage.Character;

public class Battlefield {
	
	private Terrain terrain;
	private Character player;
	private Character enemy;
	
	public Battlefield(Terrain terrain) {
		this.terrain = Terrain.getRandom();
		this.enemy = this.createEnemy();
		this.player = this.CharacterCreation();
		System.out.println("You are... ");
		System.out.println(player);
		System.out.println("Your Enemy is: ");
		System.out.println(enemy);

	}

	private Character createEnemy() {
		Random rnd = new Random();
		Enemies enemyStrings = Enemies.getRandom();
		EnemyTitles titles = EnemyTitles.getRandom();
		String enemyName = enemyStrings.getName()+" the "+titles.getTitle();
		String enemyBg = enemyStrings.getBackground();
		String enemyFeat = enemyStrings.getFeat();
		CharClass enemyClass = CharClass.getRandom();
		int enemyHP = 8+enemyClass.getMeleePower()+rnd.nextInt(6)+1;
		Character enemy = new Character(enemyName ,enemyBg ,enemyFeat , enemyHP, enemyClass);
		return enemy;
	}



	private Character CharacterCreation() {
		Random rnd = new Random();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Name: ");
		String name = scan.next();
		System.out.println("Enter your Background: ");
		String background = scan.next();
		System.out.println("Enter your feat: ");
		String feat = scan.next();
		CharClass playerClass = this.chooseClass();
		int hp = 8+playerClass.getMeleePower()+rnd.nextInt(4)+1;
		Character playerChar = new Character(name, background, feat, hp, playerClass);
		return playerChar;
	}

	private CharClass chooseClass() {
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
			playClass = this.chooseClass();
		}
		return playClass;
	}
	
	public void Battle() {
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		System.out.println("The battle begins. Choose 1 to attack and 2 to heal yourself.");
		int playerChoice = scan.nextInt();
	
		while (this.player.isAlive() && this.enemy.isAlive()) {
			switch(playerChoice) {
			case 1:
				this.player.attack(this.enemy);
				break;
			case 2:
				this.player.heal(rnd.nextInt((int) ((int) (this.player.getHealth())-this.terrain.getModifier())));
				break;
			default:
				System.out.println("Command not recognized.");
			}
			if (this.enemy.getHealth() <= 5 && this.player.getHealth() > this.enemy.getHealth()) {
				this.enemy.heal(rnd.nextInt((int) ((int) (this.enemy.getHealth())-this.terrain.getModifier())));
			}
			else
				this.enemy.attack(this.player);
			//System.out.println(this.enemy);
			//System.out.println(this.player);
			playerChoice = scan.nextInt();
		}
	}

}
