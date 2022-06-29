package application;
	
import java.util.Iterator;

import application.gameObject.GameObject;
import application.gameObject.building.HomeBase;
import application.gameObject.entity.unit.Unit;
import application.gameObject.entity.unit.Entity;
import application.gameObject.entity.unit.HeavySoldier;
import application.gameObject.entity.unit.Soldier;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Main extends Application {
	public static BorderPane root;
	
	@Override
	public void start(Stage primaryStage) {
            root = new BorderPane();
            Scene scene = new Scene(root,1000,1000);

            Player player = new Player(scene, "Black");
            scene.setCamera(player.getCamera());
            initTimer(player);
            
            HomeBase hmbs = new HomeBase(50, 50, player);
            Soldier soldier = new Soldier(200, 200, player);
            HeavySoldier hsoldier = new HeavySoldier(300, 300, player);
            
            Player player_two = new Player("Orange");
            HomeBase hmbs_two = new HomeBase(1600, 50, player_two);
            Soldier soldier_two = new Soldier(1300, 100, player_two);
            HeavySoldier hsoldier_two = new HeavySoldier(1300, 300, player_two);

            Iterator<GameObject> iter = GameObject.gameObjectSet.iterator();
            while(iter.hasNext()) {
                GameObject obj = iter.next();
                root.getChildren().add(obj.getGroup());
            }
            
            Rectangle select = new Rectangle(100, 100);
            select.setFill(javafx.scene.paint.Color.GREY);
            select.setOpacity(0.2);
            root.getChildren().add(select);
            initMouseEvents(scene, player, select);

//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
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
                    checkUnitInteraction();
                }
                
            };
            timer.start();
	}
	
	private void initMouseEvents(Scene scene, Player player, Rectangle select) {
		scene.setOnMouseReleased(e->{
	        Iterator<GameObject> iterSelected = player.getSelected().iterator();
	        int offset = 0;
	
	        while(iterSelected.hasNext()) {
	            GameObject obj = iterSelected.next();
	            if (obj instanceof Unit) {
	            	((Unit)obj).move(e, offset);
	                offset = offset + 55;
	            }
	        }
        });

        scene.setOnMousePressed(e->{
        	select.setOpacity(0.0);
            select.setTranslateX(e.getX());
            select.setTranslateY(e.getY());
        });
        
        scene.setOnMouseDragged(e->{
            double x = Math.abs(select.getTranslateX() - e.getX());
            double y = Math.abs(select.getTranslateY() - e.getY());
            
            select.setWidth(x);
            select.setHeight(y);
        	select.setOpacity(0.2);

            Iterator<GameObject> iter2 = GameObject.gameObjectSet.iterator();
            while(iter2.hasNext()) {
	            GameObject gmObj = iter2.next();
	            Shape intersect = Shape.intersect(gmObj.getImage(), select);
	            if(intersect.getBoundsInParent().getWidth() > 0 && gmObj.getImage().getFill() == Paint.valueOf("Black")) {
	                player.getSelected().add(gmObj);
	            } else if (intersect.getBoundsInParent().getWidth() <= 0 && player.getSelected().contains(gmObj)) {
	                player.getSelected().remove(gmObj);
	            }
            }
        });
	}		
			
	private void checkUnitInteraction() {
		 Iterator<Unit> iterUnit = Unit.unitSet.iterator();
         while(iterUnit.hasNext()) {
             Unit unit = iterUnit.next();
    		 double unitX = unit.getX();
    		 double unitY = unit.getY();		 
          	
             if (unit.getTarget() != null && unit.getTarget().getHealth() > 0) {
            	 unit.shoot();
        		 double tarX = unit.getTarget().getX();
        		 double tarY = unit.getTarget().getY();

        		 double dist = Math.sqrt(Math.pow(tarX - unitX, 2) + Math.pow(tarY - unitY, 2));
        		 if (dist > unit.getFovRadius()) {
        			 unit.setTarget(null);
        		 }
             } else if(unit.getTarget() != null && unit.getTarget().getHealth() == 0) {
          		unit.setTarget(null);
             }
             
    		 Iterator<Entity> iterEntity = Entity.entitySet.iterator();
             while(iterEntity.hasNext()) {
            	 Entity obj = iterEntity.next();
            	 if(!unit.equals(obj) && unit.getTarget() == null) {
            		 double objX = obj.getX();
            		 double objY = obj.getY();
            		 
            		 double dist = Math.sqrt(Math.pow(objX - unitX, 2) + Math.pow(objY - unitY, 2));
            		 if (dist <= unit.getFovRadius() && unit.getImageFill() != obj.getImageFill()) {
            			 unit.setTarget(obj);
            		 } 
            		 
            	 } 
        	 }
         }
             
     }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
