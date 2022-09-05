package application.gameObject.entity.unit;

import java.util.HashSet;

import application.Player;
import application.gameObject.GameObject;
import application.gameObject.projectile.Bullet;
import application.inter.Placable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseEvent;

public abstract class Unit extends Entity implements Placable, Runnable {
	public static HashSet<Unit> unitSet = new HashSet<Unit>();
	private int fovRadius = 0;
	private Circle fov = new Circle();
	private Entity target;
	private float speed = 1;
	private int fireRate;
	private boolean firing;
	
	public Unit(int x, int y) {
		super(x, y);
		unitSet.add(this);
	}
        
    public void move(double x, double y, int offset) {        	
        TranslateTransition translate = new TranslateTransition();
        double oldPosX = this.getImage().getTranslateX();
        double oldPosY = this.getImage().getTranslateY();
        double newPosX = x + offset;
        double newPosY = y + offset;
        
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
    
    public void shoot() {		
    	if (this.target != null && !this.isFiring()) {
	    	double x = this.getX() - this.target.getX();
	    	double y = this.getY() - this.target.getY();
	    	double angle = Math.atan(y/x);
			if (x >= 0  && y <=0) {
				angle = 180 + Math.toDegrees(angle);
			} else if (x >= 0  && y >= 0) {
				angle = Math.toDegrees(angle) - 180;
			} else {
				angle = Math.toDegrees(angle);
			}	
			
	    	Bullet b = new Bullet(this, 10, this.fovRadius, angle, this.getImage().getTranslateX(), this.getImage().getTranslateY());
			Thread thread = new Thread(this);
			thread.start();
    	}
    }
    
//	@Override
	public void run() {
		this.setFiring(true);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.setFiring(false);

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

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public boolean isFiring() {
		return firing;
	}

	public void setFiring(boolean firing) {
		this.firing = firing;
	}
	
}
