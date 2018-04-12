package authoring.utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
        ImageView view = new DraggableImageView(image);
        return view;
    }
    public static DraggableImageView getDraggableImageView(String path, int width, int height){
        Image image = new Image(path, width, height, false, true);
        DraggableImageView view = new DraggableImageView(image);
        return view;
    }
    
    public static DraggableImageView getDraggableImageView(String path, int width, int height, Pane p){
        Image image = new Image(path, width, height, false, true);
        DraggableImageView view = new DraggableImageView(image);
        return view;
    }

    /**
     *
     * @param view  The ImageView to be resized
     * @param width The desired new width
     * @param height The desired new height
     * @return The newly sized ImageView
     */
    public static ImageView resize(ImageView view, int width, int height) {
        view.setFitHeight(height);
        view.setFitWidth(width);
        return view;
    }

    /**
     *
     * @param view ImageView to be resized
     * @param size New size (Used for both height and width)
     * @return the resized ImageView
     */
    public static ImageView resize(ImageView view, int size) {
        view.setFitHeight(size);
        view.setFitWidth(size);
        return view;
    }
}
