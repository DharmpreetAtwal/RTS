package application.gameObject.entity.unit;

import application.Player;
import javafx.scene.shape.Rectangle;

public class HeavySoldier extends Unit {

	public HeavySoldier(int x, int y, Player ownedBy) {
		super(x, y);
		this.setSpeed(0.5f);
		this.setFovRadius(400);
		this.setHealth(100);
	
		Rectangle image = new Rectangle();
		image.setFill(javafx.scene.paint.Color.valueOf(ownedBy.getColour()));
		image.setWidth(25);
		image.setHeight(25);
		image.setTranslateX(x);
		image.setTranslateY(y);
		
		this.setImage(image);
		this.getGroup().getChildren().add(this.getImage());
	}
	
	public String toString() {
		return this.getImageFill() + " Heavy Soldier";
	}
}
