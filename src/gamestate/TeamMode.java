package gamestate;

import java.util.ArrayList;

import client.GamePanel;
import player.Player;

/**
 * Specialized Playing mode for team-based game variants.
 * Currently inherits behavior from Playing and exists as a semantic marker
 * for team-oriented game logic/extensions.
 */
public class TeamMode extends Playing {

	public TeamMode(GamePanel panel, Player player, ArrayList<Player> players) {
		// Construct Playing with provided panel and player list
		super(panel, player, players);
	}
}
