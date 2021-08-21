package application;

import java.util.Set;

import application.gameObject.GameObject;
import application.inter.Placable;
import javafx.event.EventHandler;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Player {
	private ParallelCamera camera;
	private boolean[] playerState = {false, false, false, false, false, false, false}; //	North, South, West, East, running
	private int money; 
	private String colour;
	private Set<GameObject> selected;
	private Set<Placable> placed;
	
	public Player(Scene scene, String colour) {
		this.camera = new ParallelCamera();
		this.colour = colour;
		initKeyActions(scene);
	}

	public void updateCamera(int dx, int dy) {
		this.camera.setLayoutX(this.camera.getLayoutX() + dx);
		this.camera.setLayoutY(this.camera.getLayoutY() + dy);
		
		
		if(this.camera.getScaleX() - 0.5 >= 1.0) {
			if(this.playerState[5]) {
				this.camera.setScaleX(this.camera.getScaleX() - 0.5);
				this.camera.setScaleY(this.camera.getScaleY() - 0.5);
				
			} 
		}
		
		if (this.playerState[6]) {
			this.camera.setScaleX(this.camera.getScaleX() + 0.5);
			this.camera.setScaleY(this.camera.getScaleY() + 0.5);
		}
		
	}
	
	public void initKeyActions(Scene scene) {
		boolean[] playerSta = this.playerState;
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
					case W: playerSta[0] = true; break;
					case S: playerSta[1] = true; break;
					case A: playerSta[2] = true; break;
					case D: playerSta[3] = true; break;
					case SHIFT: playerSta[4] = true; break;
					case N: playerSta[5] = true; break;
					case M: playerSta[6] = true; break;

				default:
					break;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    playerState[0] = false; break;
                    case S:  playerState[1] = false; break;
                    case A:  playerState[2]  = false; break;
                    case D: playerState[3]  = false; break;
                    case SHIFT: playerState[4] = false; break;
					case N: playerSta[5] = false; break;
					case M: playerSta[6] = false; break;
				default:
					break;
                }
            }
        });
	}
	
	public ParallelCamera getCamera() {
		return camera;
	}

	public void setCamera(ParallelCamera camera) {
		this.camera = camera;
	}

	public boolean[] getPlayerState() {
		return playerState;
	}

	public void setPlayerState(boolean[] playerState) {
		this.playerState = playerState;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Set<GameObject> getSelected() {
		return selected;
	}

	public void setSelected(Set<GameObject> selected) {
		this.selected = selected;
	}

	public Set<Placable> getPlaced() {
		return placed;
	}

	public void setPlaced(Set<Placable> placed) {
		this.placed = placed;
	}

}
