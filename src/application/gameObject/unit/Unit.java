package application.gameObject.unit;

import java.util.HashSet;

import application.Player;
import application.gameObject.GameObject;
import application.inter.Placable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class Unit extends GameObject implements Placable {
	public static HashSet<Unit> unitSet = new HashSet<Unit>();
	private Player ownedBy;
	private int fovRadius = 0;
	private Circle fov = new Circle();
	private GameObject target;
	private int health;
	private int cost;
	private float speed = 1;
	
	public Unit(int x, int y) {
		super(x, y);
		unitSet.add(this);
	}
        
    public void move(MouseEvent e, int offset) {        	
        TranslateTransition translate = new TranslateTransition();
        double oldPosX = this.getImage().getTranslateX();
        double oldPosY = this.getImage().getTranslateY();
        double newPosX = e.getX() + offset;
        double newPosY = e.getY() + offset;
        
        double dist = Math.sqrt(Math.pow(oldPosX - newPosX, 2) + Math.pow(oldPosY - newPosY, 2));
        Duration dur = new Duration(dist / this.speed);
        
        translate.setByX(1);
        translate.setByY(1);
        translate.setDuration(dur);
        translate.setFromX(oldPosX);
        translate.setFromY(oldPosY);
        translate.setToX(newPosX);
        translate.setToY(newPosY);
        translate.setCycleCount(1);  
        translate.setAutoReverse(false);  
        translate.setNode(this.getImage());
        translate.play();
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
