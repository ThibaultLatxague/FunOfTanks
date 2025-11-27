package client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import input.KeyboardInput;
import input.MouseInput;

/**
 * Swing panel that hosts the game's rendering surface and input listeners.
 * Holds fixed game window dimensions and tile size constants.
 */
public class GamePanel extends JPanel{
	
	// Fixed window dimension for the game
	public static final Dimension dimension = new Dimension(1250, 800);
	private Game game;
	
	// Tile size used throughout the game world rendering / layout
	public static final int tileSize = 50;
	
	public GamePanel(Game game) {
		// Setup panel and input listeners
		this.game = game;
		MouseInput mouse = new MouseInput(game);
		setPanelSize();
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		addKeyListener(new KeyboardInput(game));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		requestFocusInWindow();
	}
	
	/**
	 * Graphic loop - called by Swing when repainting.
	 * Delegates actual rendering to Game.render(...)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void setPanelSize() {
		setPreferredSize(dimension);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public int getTileSize() {
		return tileSize;
	}

	public Game getGame() {
		return game;
	}
	
	
}
