package utils;

import java.util.Random;

/**
 * Miscellaneous calculation utilities used throughout the game:
 * - rounding positions to tile grid
 * - clamping target ranges
 * - normalization helpers
 */
public class Calcul {
	
	public static final Random r = new Random();
	
	public final static int MAX_RANGE = 200;
	
	/**
	 * Rounds the given x-coordinate to the nearest tile position.
	 * @param x the x-coordinate to round
	 * @return the rounded x-coordinate
	 */
	public static int roundToTile(int x) {
		return x - x%50;
	}
	
	/**
	 * Limits the range of the target position to prevent it from moving too far
	 * from the current position.
	 * @param target the desired target position
	 * @param pos the current position
	 * @return the adjusted target position, clamped within the max range
	 */
    public static int limitRange(int target, int pos) {
    	int range = target - pos;
		if (range > MAX_RANGE) {
			return pos + MAX_RANGE;			
		} else if (range < MAX_RANGE *-1) {
			return pos - MAX_RANGE;
		} else {
			return target;
		}
	}
    
    /**
     * Normalizes a 2D vector (x, y) to a unit vector.
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @return a double array containing the normalized x and y components
     */
    public static double[] normalizeVector(int x, int y) {
		double norme = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		double[] res = new double[2];
		res[0] = ((double) x / norme);
		res[1] = ((double) y / norme);
		return res;
	}
}
