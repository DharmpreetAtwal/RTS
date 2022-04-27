package application.gameObject.unit;

import application.Player;
import javafx.scene.shape.Circle;

public class Soldier extends Unit{
	
	public Soldier(int x, int y, Player ownedBy) {
		super(x, y);
		this.setSpeed(1.0f);
		this.setFovRadius(20);
		
		Circle image = new Circle();
		image.setFill(javafx.scene.paint.Color.valueOf(ownedBy.getColour()));
		image.setRadius(10);
		
		image.setTranslateX(x);
		image.setTranslateY(y);
		
		this.setImage(image);
		this.getGroup().getChildren().add(this.getImage());
		this.setFovRadius(150);
	}
	
	public String toString() {
		return this.getImageFill() + " Soldier";
	}

}
