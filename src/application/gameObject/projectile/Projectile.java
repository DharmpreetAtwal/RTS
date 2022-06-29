package application.gameObject.projectile;

import java.util.HashSet;

import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public abstract class Projectile {
//	public static HashSet<Projectile> projectileSet = new HashSet<Projectile>();
	private Group group = new Group();
	private Translate translate = new Translate(0, 0);
	private Rotate rotate = new Rotate(0, 0, 0);
	private Shape image;
	private float projectileSpeed = 0.0f;
	
	public Projectile() {
		this.group.getTransforms().addAll(translate, rotate);
//		projectileSet.add(this);
	}

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

	public double getX() {
		return this.image.getTranslateX();
	}
	
	public double getY() {
		return this.image.getTranslateY();
	}

	public float getProjectileSpeed() {
		return projectileSpeed;
	}

	public void setProjectileSpeed(float projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}
	
}
