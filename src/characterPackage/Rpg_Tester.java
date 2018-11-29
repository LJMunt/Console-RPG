package characterPackage;

import battlePackage.Battlefield;
import battlePackage.Terrain;

public class Rpg_Tester {
static Character player = Battlefield.CharacterCreation();
static Character player2 = new Character("Test", "Test", "Test", 20, CharClass.Fighter);
	public static void main(String[] args) {
		Battlefield mainStage = new Battlefield(Terrain.getRandom(), player );
		mainStage.Battle();
		
	}
}
