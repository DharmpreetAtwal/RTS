package application.gameObject.projectile;

import application.Main;
import application.gameObject.GameObject;
import application.gameObject.entity.unit.Entity;
import application.gameObject.entity.unit.Unit;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bullet extends Projectile {

	public Bullet(Unit unit, int damage, float projectileSpeed, double angle, double x, double y) {
        this.setProjectileSpeed(projectileSpeed);
        Rectangle image = new Rectangle(0, 0, 10, 10);
        image.setFill(javafx.scene.paint.Color.RED);
        image.setRotate(angle);
        this.setImage(image);

        Main.root.getChildren().add(this.getImage());

        double angleRad = Math.toRadians(angle);
        float xVel = (float) (this.getProjectileSpeed() * Math.cos(angleRad));
        float yVel = (float) (this.getProjectileSpeed() * Math.sin(angleRad));

        TranslateTransition translate = new TranslateTransition();
        Duration dur = new Duration(250);

        translate.setByX(1);
        translate.setByY(1);
        translate.setDuration(dur);
        translate.setFromX(x);
        translate.setFromY(y);
        translate.setToX(x + xVel);
        translate.setToY(y + yVel);
        translate.setCycleCount(1);  
        translate.setAutoReverse(false);  
        translate.setNode(this.getImage());
        translate.play();
        
        translate.setOnFinished(event -> {
        	Main.root.getChildren().remove(this.getImage());
        });
        
	    this.getImage().translateXProperty().addListener((observable, oldValue, newValue) -> {
	    	GameObject obj = GameObject.checkCollision(this.getImage(), unit);
	    	if(obj != null) {
	    		translate.stop();
	    		obj.hit(damage);
	            Main.root.getChildren().remove(this.getImage());
	    	}
	    });
	    
	}

}
