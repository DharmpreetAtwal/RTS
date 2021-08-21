package application.gameObject;

import java.util.HashSet;

import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class GameObject {
	public static HashSet<GameObject> gameObjectSet = new HashSet<GameObject>();
	private Group group = new Group();
	private Translate translate = new Translate(0, 0);
	private Rotate rotate = new Rotate(0, 0, 0);
	private Shape image;

	public GameObject(int x, int y) {
		this.group.getTransforms().addAll(translate, rotate);
		gameObjectSet.add(this);
		this.setX(x);
		this.setY(y);
	}

	public Group getGroup() {
		return group;
	}


	public void setGroup(Group group) {
		this.group = group;
	}

	public Translate getTranslate() {
		return translate;
	}

	public void setTranslate(Translate translate) {
		this.translate = translate;
	}

	public Rotate getRotate() {
		return rotate;
	}

	public void setRotate(Rotate rotate) {
		this.rotate = rotate;
	}

	public Shape getImage() {
		return image;
	}

	public void setImage(Shape image) {
		this.image = image;
	}

	public int getX() {
		return (int) this.translate.getX();
	}

	public void setX(int x) {
		this.translate.setX(x);
	}

	public int getY() {
		return (int) this.translate.getY();
	}

	public void setY(int y) {
		this.translate.setY(y);
	}
	
	public void setAngle(double angle) {
		this.rotate.setAngle(angle);
	}
	
	public double getAngle() {
		return this.rotate.getAngle();
	}

}
