package application.gameObject.unit;

import application.Player;
import javafx.scene.shape.Circle;

public class Soldier extends Unit{
	
	public Soldier(int x, int y, Player ownedBy) {
		super(x, y);
		Circle image = new Circle();
		image.setFill(javafx.scene.paint.Color.valueOf(ownedBy.getColour()));
		image.setRadius(10);
		
		this.setImage(image);
		this.getGroup().getChildren().add(this.getImage());
		this.setFovRadius(50);
	}

}
