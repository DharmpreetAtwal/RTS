package application.gameObject.entity.unit;

import java.util.HashSet;

import application.Player;
import application.gameObject.GameObject;

public abstract class Entity extends GameObject {
	public static HashSet<Entity> entitySet = new HashSet<Entity>();
	private Player ownedBy;
	private int health;
	private int cost;
	
	public Entity(int x, int y) {
		super(x, y);
		entitySet.add(this);
	}
	
    public void hit(int damage) {
    	int netHealth = this.getHealth() - damage;
    	if (netHealth > 0) {
    		this.setHealth(netHealth);
    	} else {
    		this.setHealth(0);
    		this.die();
    	}
    }
   
    public void die() {
		GameObject.removeObject(this);
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
