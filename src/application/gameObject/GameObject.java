package application.gameObject;

import java.util.HashSet;

import application.Main;
import application.gameObject.entity.unit.Entity;
import application.gameObject.entity.unit.Unit;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public abstract class GameObject {
	public static HashSet<GameObject> gameObjectSet = new HashSet<GameObject>();
	private Group group = new Group();
	private Translate translate = new Translate(0, 0);
	private Rotate rotate = new Rotate(0, 0, 0);
	private Shape image;
//	private final int initX;
//	private final int initY;

	public GameObject(int x, int y) {
		this.group.getTransforms().addAll(translate, rotate);
		gameObjectSet.add(this);
//		this.initX = x;
//		this.initY = y;
	}
	
	public abstract void hit(int damage);
	
	public static GameObject checkCollision(Shape image, GameObject avoid) {
		for (GameObject obj: gameObjectSet) {
			if (obj != avoid) {
				if (obj.getImage().getBoundsInParent().intersects(image.getBoundsInParent())) {
					return obj;
				}
			}
		}
		return null;
	}

	public static void removeObject(GameObject obj) {
		Main.root.getChildren().remove(obj.getGroup());
		gameObjectSet.remove(obj);
		if (obj instanceof Entity) {
			Entity.entitySet.remove(obj);
		}

		if (obj instanceof Unit) {
			Unit.unitSet.remove(obj);
		}
		
	}
	

//	public Translate getTranslate() {
//		return translate;
//	}
//
//	public void setTranslate(Translate translate) {
//		this.translate = translate;
//	}
//
//	public Rotate getRotate() {
//		return rotate;
//	}
//
//	public void setRotate(Rotate rotate) {
//		this.rotate = rotate;
//	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	public Shape getImage() {
		return image;
	}

	public void setImage(Shape image) {
		this.image = image;
	}

//	public double getImageX() {
//		return this.image.getTranslateX();
//	}
//	
//	public double getImageY() {
//		return this.image.getTranslateY();
//	}
	
	public Paint getImageFill() {
		return this.image.getFill();
	} 
	
	public double getX() {
		return this.image.getTranslateX();
	}
	
	public void setX(double x) {
		this.image.setTranslateX(x);
	}

	public double getY() {
		return this.image.getTranslateY();
	}

	public void setY(double y) {
		this.image.setTranslateX(y);
	}
	
//	public void setAngle(double angle) {
//		this.rotate.setAngle(angle);
//	}
//	
//	public double getAngle() {
//		return this.rotate.getAngle();
//	}

//	public int getInitX() {
//		return initX;
//	}
//
//	public int getInitY() {
//		return initY;
//	}

}
