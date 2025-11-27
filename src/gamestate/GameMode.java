package gamestate;

/**
 * Available game modes (Free-For-All, Team, Domination).
 * Holds a static selected mode and helpers to convert to/from string.
 */
public enum GameMode {
	FFA, TEAM, DOMINATION;
	public static GameMode gameMode = FFA;
	
	public String toString() {
		// Return current selected mode as lowercase string
		switch (gameMode) {
		case FFA: return "ffa";
		case TEAM: return "team";
		case DOMINATION: return "domination";
		default: return "ffa";
		}
	}
	
	public static GameMode toMode(String mode) {
		// Parse mode string to enum value (default FFA)
		switch (mode) {
		case "ffa": return FFA;
		case "team": return TEAM;
		case "domination": return DOMINATION;
		default: return FFA;
		}
	}
}
