package application.gameObject.unit;

import application.Player;
import application.gameObject.GameObject;
import application.inter.Placable;
import javafx.scene.shape.Circle;

public abstract class Unit extends GameObject implements Placable {
	private Player ownedBy;
	private int fovRadius = 0;
	private Circle fov = new Circle();
	private GameObject target;
	private int health;
	private int cost;
	
	public Unit(int x, int y) {
		super(x, y);
	}

	public Player getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
	}

	public int getFovRadius() {
		return fovRadius;
	}

	public void setFovRadius(int fovRadius) {
		this.fovRadius = fovRadius;
	}

	public Circle getFov() {
		return fov;
	}

	public void setFov(Circle fov) {
		this.fov = fov;
	}

	public GameObject getTarget() {
		return target;
	}

	public void setTarget(GameObject target) {
		this.target = target;
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
