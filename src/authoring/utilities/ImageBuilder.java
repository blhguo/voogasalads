package authoring.utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author liampulsifer
 *
 * Auxilary class with static methods that help convert image paths into displayable ImageView objects
 */

public class ImageBuilder {

    /**
     *
     * @param path Path to the Image desired
     * @return  Returns ImageView of that Image
     */
    public static ImageView getImageView(String path){
        Image image = new Image(path);
        ImageView view = new ImageView(image);
        return view;
    }

    /**
     *
     * @param path
     * @param width
     * @param height
     * @return ImageView with specified Image, width, and height
     */
    public static ImageView getImageView(String path, int width, int height){
        Image image = new Image(path, width, height, false, true);
        ImageView view = new ImageView(image);
        return view;
    }
}
