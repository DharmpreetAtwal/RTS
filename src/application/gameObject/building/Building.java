package application.gameObject.building;

import application.Player;
import application.gameObject.GameObject;

public abstract class Building extends GameObject {
	private Player ownedBy;
	private int health;
	private int cost;
	
	public Building(int x, int y) {
		super(x, y);
	}

	public Player getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
