package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.Game;
import gamestate.GameState;

/**
 * Keyboard listener that routes key events to the active game state.
 * Keeps keyboard input handling centralized and simple.
 */
public class KeyboardInput implements KeyListener {
	
	private Game game;
	
	public KeyboardInput(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Delegate to current state's keyTyped handler
		switch (GameState.state) {
		case MENU:
			game.getMenu().keyTyped(e);
			break;
		case PLAYING:
			game.getPlaying().keyTyped(e);
			break;
		case FINISH:
			game.getFinish().keyTyped(e);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Delegate to current state's keyPressed handler
		switch (GameState.state) {
		case MENU:
			game.getMenu().keyPressed(e);
			break;
		case PLAYING:
			game.getPlaying().keyPressed(e);
			break;
		case FINISH:
			game.getFinish().keyPressed(e);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Delegate to current state's keyReleased handler
		switch (GameState.state) {
		case MENU:
			game.getMenu().keyReleased(e);
			break;
		case PLAYING:
			game.getPlaying().keyReleased(e);
			break;
		case FINISH:
			game.getFinish().keyReleased(e);
			break;
		}
	}

}