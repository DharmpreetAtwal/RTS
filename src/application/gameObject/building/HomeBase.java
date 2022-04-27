package application.gameObject.building;

import application.Player;
import javafx.scene.shape.Rectangle;

public class HomeBase extends Building {

	public HomeBase(int x, int y, Player ownedBy) {
		super(x, y);
		
		Rectangle image = new Rectangle(100, 100);
		image.setFill(javafx.scene.paint.Color.valueOf(ownedBy.getColour()));
		image.setTranslateX(x);
		image.setTranslateY(y);
		
		this.setImage(image);
		this.getGroup().getChildren().add(this.getImage());
	}

	public String toString() {
		return this.getImageFill() + " Home Base";
	}
	
}
