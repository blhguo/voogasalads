package voogasalad.util.voogle_images;

import java.io.File;

/**
 * This is the interface that any object calling the VoogleImages utility must implement. The
 * utility relies on a simplistic version of the observer pattern to update calling applications.
 * 
 * @author benhubsch
 */
public interface ImageObserver {

	/**
	 * Called whenever the VoogleImages utility has successfully downloaded an image from the internet.
	 * 
	 * @param file Describes the downloaded image.
	 */
	public void update(File file);
}
