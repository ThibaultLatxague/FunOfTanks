package utils;

import client.Game;

/**
 * Small string-format helpers. Currently contains a simple IP validation helper
 * (used by UI). Note: the implementation expects dotted decimal IPv4 strings.
 */
public class StringFormat {
	
	public static boolean isIP(String str) {
		String[] bytes = str.split(".");
		if (bytes.length != 4) return false;
		for (String b : bytes) {
			try {
				Integer.parseInt(b);
			} catch (NumberFormatException e) {
				Game.printErrorMessage("Try a valid IP address !");
				return false;
			}
		}
		return true;
	}
}
