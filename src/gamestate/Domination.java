package gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import client.Game;
import client.GamePanel;
import map.Zone;
import player.Player;
import utils.Delay;

/**
 * Domination game mode: teams capture/hold a central zone to score points.
 * When points reach FINISH_POINTS (positive or negative) the game ends.
 */
public class Domination extends TeamMode {
	
	// Points threshold to end the match
	public static final int FINISH_POINTS = 10000;
	
	private Zone zone;

	public Domination(GamePanel panel, Player player, ArrayList<Player> players) {
		super(panel, player, players);
		zone = new Zone(panel.getDimension().getWidth()/2-200, panel.getDimension().getHeight()/2-200, 400, 400, this);
	}
	
	@Override
	public void update() {
		// Update zone control then default playing logic; set finish when threshold reached
		zone.update();
		super.update();
		if (zone.getPoints() >= FINISH_POINTS) {
			new Delay(3000, () -> {
				isFinish = 1;				
			});
			Game.printMessage("Fin de partie");
		} else if (zone.getPoints() <= -1*FINISH_POINTS) {
			new Delay(3000, () -> {
				isFinish = 2;				
			});
			Game.printMessage("Fin de partie");
		}
	}
	
	@Override
	public void draw(Graphics g) {
		// Draw domination zone and then base playing elements and a simple progress UI
		zone.draw(g);
		super.draw(g);
		g.setColor(Color.RED);
		g.fillRect(310, 10, 640, 30);
		g.setColor(Color.BLUE);
		g.fillRect(310, 10, 320+(zone.getPoints()*320/FINISH_POINTS), 30);
	}
	
	public Zone getZone() {
		return zone;
	}
}
