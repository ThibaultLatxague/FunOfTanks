package serverClass;

/**
 * Server-side representation of a connected player (name, ready flag, team).
 */
public class ServerPlayer {

	public String name;
	public boolean ready;
	public int team;
	
	public ServerPlayer(String n, boolean r, int t) {
		name = n;
		ready = r;
		team = t;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
