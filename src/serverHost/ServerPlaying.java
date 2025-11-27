package serverHost;

import java.util.ArrayList;

import map.Obstacle;
import serverClass.*;

/**
 * Server-side model of the playing state. Holds lists of server-side players, tanks, bullets and obstacles.
 * Used by the authoritative server logic to track and broadcast game state.
 */
public class ServerPlaying {
	
	ArrayList<ServerPlayer> players;
	
	ArrayList<ServerTank> tanks;
	
	ArrayList<ServerBullet> bullets;
	
	ArrayList<Obstacle> obstacles;
	
	public ServerPlaying() {
		players = new ArrayList<ServerPlayer>();
		tanks = new ArrayList<ServerTank>();
		bullets = new ArrayList<ServerBullet>();
		obstacles = new ArrayList<Obstacle>();
	}

	public ArrayList<ServerPlayer> getPlayers() {
		return players;
	}
	
	public ArrayList<ServerTank> getTanks() {
		return tanks;
	}
	
	public ArrayList<ServerBullet> getBullets() {
		return bullets;
	}
	
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}
}






