package application;
	
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
		
		Player player = new Player(scene);
		scene.setCamera(player.getCamera());
		initTimer(player);
		
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
				if(keys[0]) dy -= 1;
				if(keys[1]) dy += 1;
				if(keys[2]) dx -= 1;
				if(keys[3]) dx += 1;
                if(keys[4]) {dx *= 3; dy *= 3;}
                
                System.out.println(dx + " " + dy);
                
                player.updateCamera(dx, dy);
			}
		};
		timer.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
