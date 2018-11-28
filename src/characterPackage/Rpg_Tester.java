package characterPackage;

import java.util.Random;

public class Rpg_Tester {

	public static void main(String[] args) {
		Character char1 = new Character("Van Darkholme", "Leatherman", "Lord of the Lockerroom", 10, CharClass.getRandom());
		Character char2 = new Character("Mark Wolf", "Boss", "Boss of this Gym", 12, CharClass.getRandom());
		Random rand = new Random();
		boolean firstFighter = true;
		System.out.println(Character.characterCount);
		
		while (char2.isAlive() == true && char1.isAlive() == true) {
			if(firstFighter) {
				if (char1.getHealth() > 5)
					char1.attack(char2);
				else
					char1.heal(rand.nextInt(5)+1);
			} else {
				if (char2.getHealth() > 6) {
					char2.attack(char1);
				}
				else char2.heal(rand.nextInt(6)+1);
			}
				firstFighter = !firstFighter;
		}
		
		if (char1.isAlive() == true && char2.isAlive() == false) {
			System.out.println(char1.getName()+" wins!");
		}
		else {
			if (char2.isAlive() == true && char1.isAlive() == false ) {
				System.out.println(char2.getName()+" wins!");
			}
		}

	}
}
