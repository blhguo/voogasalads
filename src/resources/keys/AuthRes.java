package resources.keys;

import java.util.ResourceBundle;

/**
 * Used to read in resource bundles for authoring and data
 * @author Jennifer Chin
 *
 */

public class AuthRes {
	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("resources.keys/authoringResources");
	//public static final ResourceBundle VOOGLE = ResourceBundle.getBundle("resources.keys/English");
	//public static final ResourceBundle VOOGLEIMAGES = ResourceBundle.getBundle("resources.keys/Image");
	public static final ResourceBundle CONFIGKEYS = ResourceBundle.getBundle("gameData/configMap");

	/**
	 * Returns the corresponding String of a given String key from RESOURCEKEYS bundle
	 * @param key
	 * @return String
	 */
	public static String getString(String key) {
        return RESOURCEKEYS.getString(key);
    }

	/**
	 * Returns the corresponding String of a given String key from CONFIGKEYS bundle
	 * @param key
	 * @return
	 */
	public static String getStringKeys(String key) {
		return CONFIGKEYS.getString(key);
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
