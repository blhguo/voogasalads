package resources.keys;

import java.util.ResourceBundle;

public class AuthRes {

	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("resources.keys/authoringResources");

	/**
	 * Returns the corresponding String of a given String key
	 * @param key
	 * @return String
	 */
	public static String getString(String key) {
        return RESOURCEKEYS.getString(key);
    }
	
	/**
	 * Returns the corresponding int of a given String key
	 * @param key
	 * @return int
	 */
	public static int getInt(String key){
		return Integer.parseInt(RESOURCEKEYS.getString(key));
	}
	
}
