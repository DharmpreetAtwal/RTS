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
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Main extends Application {
	public static Group root;
	@Override
	public void start(Stage primaryStage) {
        Player player = new Player("Black");
        
        root = new Group(player.getCamera());
        
	    SubScene subScene = new SubScene(root, 2000, 2000, true, SceneAntialiasing.BALANCED);
	    subScene.setFill(Color.AQUAMARINE);
        subScene.setCamera(player.getCamera());
        initTimer(player);
        
	    // 2D
	    BorderPane hud = new BorderPane();
	    VBox vbox = initHUD(player);
	    hud.setCenter(subScene);
	    hud.setLeft(vbox);

	    Scene scene = new Scene(hud);
        player.initKeyActions(scene);

        HomeBase hmbs = new HomeBase(50, 50, player);
        
        Player player_two = new Player("Orange");
        HomeBase hmbs_two = new HomeBase(1600, 50, player_two);
        Soldier soldier_two = new Soldier(1300, 100, player_two);
        HeavySoldier hsoldier_two = new HeavySoldier(1300, 300, player_two);
        
        Rectangle select = new Rectangle(100, 100);
        select.setFill(javafx.scene.paint.Color.GREY);
        select.setOpacity(0.2);
        root.getChildren().add(select);
        initMouseEvents(subScene, player, select);
                    
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
	
	private void initMouseEvents(SubScene subScene, Player player, Rectangle select) {		
		subScene.setOnMouseReleased(e->{
	        Iterator<GameObject> iterSelected = player.getSelected().iterator();

	        int offset = 0;
	        Point2D p = player.getCamera().localToScene(e.getScreenX() - 100, e.getScreenY() - 50);

	        while(iterSelected.hasNext()) {
	            GameObject obj = iterSelected.next();
	            if (obj instanceof Unit) {
	            	((Unit)obj).move(p.getX(), p.getY(), offset);
	                offset = offset + 30;
	            }
	        }
        });

        subScene.setOnMousePressed(e->{
	        Point2D p = player.getCamera().localToScene(e.getScreenX() - 100, e.getScreenY() - 50);

        	select.setOpacity(0.0);
            select.setTranslateX(p.getX());
            select.setTranslateY(p.getY());
        });
        
        subScene.setOnMouseDragged(e->{
	        Point2D p = player.getCamera().localToScene(e.getScreenX() - 100, e.getScreenY() - 50);

            double x = Math.abs(select.getTranslateX() - p.getX());
            double y = Math.abs(select.getTranslateY() - p.getY());
            
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
	
	public VBox initHUD(Player player) {
	    Button solBtn = new Button("Soldier");
	    Button hSolBtn = new Button("H Soldier"); 
	    solBtn.setPrefSize(100, 100);
	    hSolBtn.setPrefSize(100, 100);
	    
	    solBtn.setOnMousePressed(e->{
	        new Soldier(200, 200, player);
	    });
	    
	    hSolBtn.setOnMousePressed(e->{
	    	new HeavySoldier(200, 200, player);
	    });

		return new VBox(solBtn, hSolBtn);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
