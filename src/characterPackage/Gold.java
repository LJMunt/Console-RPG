package characterPackage;

import java.util.Random;

public class Gold {

	private int value = 0;
	private Character owner;

	public Gold(Character owner) {
		this.owner = owner;
		if (this.owner.getFeat().equalsIgnoreCase("wealthy")) {
			this.addGold(Denomination.TEN);
		}
	}

	public void addGold(Denomination denom) {
		if (this.owner.isAlive() && this.owner.isPlayerControlled()) {
			this.value += denom.getValue();
			System.out.println(this.owner.getName() + " gains " + denom.getName() + " Gold!");
		}
	}

	public void addRandomGold() {
		Random rnd = new Random();
		if (this.owner.isAlive() && this.owner.isPlayerControlled()) {
			int percentileDie = rnd.nextInt(100) + 1;
			if (percentileDie == 1) {
				return;
			} else {
				if (percentileDie < 30) {
					this.addGold(Denomination.ONE);
				} else {
					if (percentileDie < 45) {
						this.addGold(Denomination.FIVE);
					} else {
						if (percentileDie < 65) {
							this.addGold(Denomination.TEN);
						} else {
							if (percentileDie < 88) {
								this.addGold(Denomination.TWENTY);
							} else {
								if (percentileDie < 98) {
									this.addGold(Denomination.FIFTY);
								} else {
									if (percentileDie == 100) {
										this.addGold(Denomination.ONEHUNDRED);
									} else {
										return;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if (value % 5 == 0 ^ value == 1 ^ value == 0) {
			this.value = value;
		}
	}

	// Uses Denomination.getRandom()
	public void addRandomGoldSpecial() {
		if (this.owner.isAlive() && this.owner.isPlayerControlled()) {
			Denomination denom = Denomination.getRandom();
			if (denom.getValue() == 1) {
				denom = Denomination.FIVE;
			}
			this.value += denom.getValue();
			System.out.println(this.owner.getName() + " gains " + denom.getName() + " Gold!");
		}
	}

}
