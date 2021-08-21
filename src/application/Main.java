package application;
	
import application.gameObject.unit.HeavySoldier;
import application.gameObject.unit.Soldier;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,1000,1000);
		
		Rectangle rect = new Rectangle(100, 100);
		rect.setFill(javafx.scene.paint.Color.RED);
		root.getChildren().add(rect);
		
		Player player = new Player(scene, "Black");
		scene.setCamera(player.getCamera());
		initTimer(player);
		
		Soldier soldier = new Soldier(200, 200, player);
		root.getChildren().add(soldier.getGroup());
		
		HeavySoldier hsoldier = new HeavySoldier(300, 300, player);
		root.getChildren().add(hsoldier.getGroup());
		hsoldier.setAngle(45);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
                if(keys[4]) {dx *= player.getCamera().getScaleX(); 
                			dy *= player.getCamera().getScaleY();
                			}
                                
                player.updateCamera(dx, dy);
			}
		};
		timer.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
