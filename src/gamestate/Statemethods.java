package gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Contract for all game state handlers (menu, playing, finish, ...).
 * Each state must implement update/draw and all input callbacks.
 */
public interface Statemethods {
	/** Update game logic for this state. */
	public void update();

	/** Render this state's UI / world. */
	public void draw(Graphics g);

	// Mouse events
	public void mouseDragged(MouseEvent e);

	public void mouseMoved(MouseEvent e);

	public void mouseClicked(MouseEvent e);

	public void mousePressed(MouseEvent e);

	public void mouseReleased(MouseEvent e);

	public void mouseEntered(MouseEvent e);

	public void mouseExited(MouseEvent e);

	// Keyboard events
	public void keyTyped(KeyEvent e);

	public void keyPressed(KeyEvent e);

	public void keyReleased(KeyEvent e);
}
