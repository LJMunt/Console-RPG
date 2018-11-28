package characterPackage;

import java.util.Random;

import battlePackage.Battlefield;
import battlePackage.Terrain;

public class Rpg_Tester {

	public static void main(String[] args) {
		Battlefield mainStage = new Battlefield(Terrain.getRandom());
		mainStage.Battle();
	}
}
