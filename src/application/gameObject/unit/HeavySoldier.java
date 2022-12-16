package application.gameObject.unit;

import application.Player;
import javafx.scene.shape.Rectangle;

public class HeavySoldier extends Unit {

	public HeavySoldier(int x, int y, Player ownedBy) {
		super(x, y);
		this.setSpeed(0.5f);
		this.setFovRadius(20);
	
		Rectangle image = new Rectangle();
		image.setFill(javafx.scene.paint.Color.valueOf(ownedBy.getColour()));
		image.setWidth(50);
		image.setHeight(50);
		image.setTranslateX(x);
		image.setTranslateY(y);
		
		this.setImage(image);
		this.getGroup().getChildren().add(this.getImage());
		this.setFovRadius(100);
	}
	
	public String toString() {
		return this.getImageFill() + " Heavy Soldier";
	}

	@Override
	public void hit(int damage) {
		// TODO Auto-generated method stub
		
	}
}
