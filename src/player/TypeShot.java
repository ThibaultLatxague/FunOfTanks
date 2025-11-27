package player;

/**
 * Types of shots supported by the cannon.
 * NORMAL  - standard bullet
 * BERTHA  - heavy AOE projectile
 * GRENADE - timed explosive grenade
 * TRIPLE  - spawns two additional bullets at angles
 */
public enum TypeShot {
	NORMAL, BERTHA, GRENADE, TRIPLE;
	
	/**
	 * Parses a string to find the corresponding TypeShot.
	 * @param str The string representation of the TypeShot.
	 * @return The matching TypeShot, or null if no match is found.
	 */
	public static TypeShot parseTypeShot(String str) {
		switch (str) {
		case "NORMAL":return NORMAL;
		case "BERTHA":return BERTHA;
		case "GRENADE":return GRENADE;
		case "TRIPLE":return TRIPLE;
		default: return null;
		}
	}
}
