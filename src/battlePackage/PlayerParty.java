package battlePackage;

import characterPackage.Character;

public class PlayerParty {
	private Character[] party;
	private int charCount;
	public final int MAX_SIZE = 4;

	public PlayerParty() {
		this.party = new Character[MAX_SIZE];
		this.charCount = 0;
	}

	// adds a Character to the party.
	public void addChar() {
		if (this.charCount < this.MAX_SIZE) {
			Character newPlayer = BattleField.CharacterCreation();
			this.party[this.charCount] = newPlayer;
			charCount++;
		} else
			System.out.println("Party is full.");
	}

	// deletes a character. Used in RPG_Driver.clean()
	public void delChar(int index) {
		if (this.charCount != 0) {
			party[index] = null;
			this.charCount--;
		} else
			System.out.println("Party is empty. Create a new Character.");
	}

	public Character[] getParty() {
		return party;
	}

	public int getCharCount() {
		return charCount;
	}
	// returns the Character Names of the party members.

	public String toString() {
		String returnString = "Party: ";
		for (int i = 0; i < this.charCount; i++) {
			if (this.party[i] == null)
				return null;

			returnString += " " + this.party[i].getName();
		}
		return returnString;
	}
}
