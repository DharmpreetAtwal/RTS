package application;
	
import java.util.Iterator;

import application.gameObject.GameObject;
import application.gameObject.building.HomeBase;
import application.gameObject.unit.Unit;
import application.gameObject.unit.HeavySoldier;
import application.gameObject.unit.Soldier;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,1000,1000);

            Player player = new Player(scene, "Black");
            scene.setCamera(player.getCamera());
            initTimer(player);

            HomeBase hmbs = new HomeBase(50, 50, player);
            Soldier soldier = new Soldier(200, 200, 100, player);
            HeavySoldier hsoldier = new HeavySoldier(300, 300, 1, player);

            Iterator<GameObject> iter = GameObject.gameObjectSet.iterator();
            while(iter.hasNext()) {
                GameObject obj = iter.next();
                root.getChildren().add(obj.getGroup());
            }

            Rectangle select = new Rectangle(100, 100);
            select.setFill(javafx.scene.paint.Color.GREY);
            select.setOpacity(0.2);
            root.getChildren().add(select);

            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            
            scene.setOnMouseReleased(e->{
                Iterator<GameObject> iterSelected = player.getSelected().iterator();
                System.out.println("-------------");
                int offset = 0;

                while(iterSelected.hasNext()) {
                    GameObject obj = iterSelected.next();
                    if (obj instanceof Unit) {
                        ((Unit)obj).move(e, offset);
                        offset = offset + 55;
                        System.out.println(obj.toString());
                    }
                }
            });

            scene.setOnMousePressed(e->{
                select.setTranslateX(e.getX());
                select.setTranslateY(e.getY());
            });

            scene.setOnMouseDragged(e->{
                double x = Math.abs(select.getTranslateX() - e.getX());
                double y = Math.abs(select.getTranslateY() - e.getY());

                select.setWidth(x);
                select.setHeight(y);

                Iterator<GameObject> iter2 = GameObject.gameObjectSet.iterator();
                while(iter2.hasNext()) {
                        GameObject gmObj = iter2.next();
                        Shape intersect = Shape.intersect(gmObj.getImage(), select);
                        if(intersect.getBoundsInParent().getWidth() > 0) {
                            player.getSelected().add(gmObj);
                        } else if (intersect.getBoundsInParent().getWidth() <= 0 && player.getSelected().contains(gmObj)) {
                            player.getSelected().remove(gmObj);
                        }
                }
            });
	}

	private void initTimer(Player player) {
            AnimationTimer timer = new AnimationTimer() {
                boolean[] keys = player.getPlayerState();
                @Override
                public void handle(long now) {
                    int dx=0, dy=0;
                    if(keys[0]) dy -= player.getCamera().getScaleY();
                    if(keys[1]) dy += player.getCamera().getScaleY();
                    if(keys[2]) dx -= player.getCamera().getScaleX();
                    if(keys[3]) dx += player.getCamera().getScaleX();
                    if(keys[4]) {dx *= 2 + player.getCamera().getScaleX(); 
                                 dy *= 2 + player.getCamera().getScaleY();}

                    player.updateCamera(dx, dy);
                }
            };
            timer.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
