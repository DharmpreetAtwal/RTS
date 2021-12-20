package application.gameObject.unit;

import application.Player;
import javafx.scene.shape.Rectangle;

public class HeavySoldier extends Unit {

	public HeavySoldier(int x, int y, int speed, Player ownedBy) {
		super(x, y, speed);
		Rectangle image = new Rectangle();
		image.setFill(javafx.scene.paint.Color.valueOf(ownedBy.getColour()));
		image.setWidth(50);
		image.setHeight(50);
		
		this.setImage(image);
		this.getGroup().getChildren().add(this.getImage());
		this.setFovRadius(25);
	}
	

}
