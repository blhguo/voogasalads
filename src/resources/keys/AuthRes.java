package resources.keys;

import java.util.ResourceBundle;

public class AuthRes {

	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("resources.keys/authoringResources");
	//public static final ResourceBundle VOOGLE = ResourceBundle.getBundle("resources.keys/English");
	//public static final ResourceBundle VOOGLEIMAGES = ResourceBundle.getBundle("resources.keys/Image");
	//public static final ResourceBundle CONFIGKEYS = ResourceBundle.getBundle("gameData/configMap");

	/**
	 * Returns the corresponding String of a given String key
	 * @param key
	 * @return String
	 */
	public static String getString(String key) {
        return RESOURCEKEYS.getString(key);
    }
	
//	public static String getStringKeys(String key) {
//		return CONFIGKEYS.getString(key);
//	}
	
	/**
	 * Returns the corresponding int of a given String key
	 * @param key
	 * @return int
	 */
	public static int getInt(String key){
		return Integer.parseInt(RESOURCEKEYS.getString(key));
	}
	
}
