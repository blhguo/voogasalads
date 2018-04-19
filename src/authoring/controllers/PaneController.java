package authoring.controllers;

import java.io.File;

import authoring.Canvas;
import authoring.right_components.LevelPane;
import javafx.scene.image.Image;

/**
 * @author jennychin
 * @author elizabethschulman
 * Handles pane interactions (i.e. one pane needing to update another)
 */
public class PaneController {

	private LevelPane levelPane;
	private Canvas canvas;
	// more instance variables can be added as pane controller develops
	// more responsibilities
	
	public PaneController(LevelPane lp, Canvas c){
		levelPane = lp;
		canvas = c;
	}

	/**
	 *
	 * @param image Sets the background to a specified image
	 */
	public void setBackground(File image){
		System.out.println(image.getPath());
		System.out.println(image.getName());
		Image im = new Image(image.getName());
		canvas.updateBackground(im);
	}
}
