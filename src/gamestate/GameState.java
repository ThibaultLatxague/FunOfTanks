package gamestate;

/**
 * Enumeration of high-level game states.
 * The static 'state' field holds the current global state used by Game.
 */
public enum GameState {
	PLAYING, MENU, FINISH;
	
	public static GameState state = MENU;
}
