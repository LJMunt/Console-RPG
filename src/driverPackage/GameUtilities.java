package driverPackage;

public abstract class GameUtilities {
	
	public static void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.flush();
	}

	public static void gameTitle() {
		System.out.println(
				" _______  _______  _        _______  _______  _        _______                 _______  _______  _______ \n"
						+ "(  ____ \\(  ___  )( (    /|(  ____ \\(  ___  )( \\      (  ____ \\               (  ____ )(  ____ )(  ____ \\\n"
						+ "| (    \\/| (   ) ||  \\  ( || (    \\/| (   ) || (      | (    \\/               | (    )|| (    )|| (    \\/\n"
						+ "| |      | |   | ||   \\ | || (_____ | |   | || |      | (__         _____     | (____)|| (____)|| |      \n"
						+ "| |      | |   | || (\\ \\) |(_____  )| |   | || |      |  __)       (_____)    |     __)|  _____)| | ____ \n"
						+ "| |      | |   | || | \\   |      ) || |   | || |      | (                     | (\\ (   | (      | | \\_  )\n"
						+ "| (____/\\| (___) || )  \\  |/\\____) || (___) || (____/\\| (____/\\               | ) \\ \\__| )      | (___) |\n"
						+ "(_______/(_______)|/    )_)\\_______)(_______)(_______/(_______/               |/   \\__/|/       (_______)\n"
						+ "                                                                                                         ");
		System.out.println("A lightweight RPG that runs in RAM.");
	}
	
	// removes all dead characters from the party.
		public static void clean() {
			for (int i = 0; i < RPG_Driver.party.getCharCount(); i++) {
				if (RPG_Driver.party.getParty()[i].isAlive() == false) {
					RPG_Driver.party.delChar(i);
				}
			}
		}
}
