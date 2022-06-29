package application.gameObject.entity.unit;

import application.Player;
import javafx.scene.shape.Circle;

public class Soldier extends Unit{
	
	public Soldier(int x, int y, Player ownedBy) {
		super(x, y);
		this.setSpeed(1.0f);
		this.setFovRadius(200);
		this.setFireRate(1000);
		this.setFiring(false);
		this.setHealth(50);
		
		Circle image = new Circle();
		image.setFill(javafx.scene.paint.Color.valueOf(ownedBy.getColour()));
		image.setRadius(10);
		
		image.setTranslateX(x);
		image.setTranslateY(y);
		
		this.setImage(image);
		this.getGroup().getChildren().add(this.getImage());
	}
	
	public String toString() {
		return this.getImageFill() + " Soldier";
	}

}
