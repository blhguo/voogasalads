package authoring.controllers;

import authoring.Canvas;
import authoring.right_components.LevelPane;
import javafx.scene.image.Image;
import resources.keys.AuthRes;

/**
 * @author jennychin
 * Handles pane interactions (i.e. one pane needing to update another)
 */
public class PaneController {

	private Canvas canvas;
	private EntityController econtroller;
	
	/**
	 * Public PaneController constructor that takes in a Canvas object to update
	 * @param c
	 */
	public PaneController(Canvas c){
		canvas = c;
	}

	/**
	 * Checks if no background has been set and sets Canvas background accordingly
	 * @param image Sets the background to a specified image
	 */
	public void setBackground(String fname){
		if (fname.equals(AuthRes.getString("BackgroundDefault"))){
			canvas.setDefaultBackground();
		}
		else{
			Image im = new Image(fname);
			canvas.updateBackground(im);
		}
	}
	
	/**
	 * Updates canvas object with new entities 
	 */
	public void updateCanvas(){
		canvas.update(econtroller.getEntities());
	}
	
	/**
	 * Updates canvas object with new entities from a specific level
	 * @param id
	 */
	public void updateCanvas(int id){
		canvas.setLevel(id);
		canvas.update(econtroller.getEntities());
	}
	
	/**
	 * Sets EntityController for class
	 * @param ec
	 */
	public void setEntityController(EntityController ec){
		econtroller = ec;
	}
	
	/**
	 * Should restrict scrolling direction of canvas - canvas method contains bugs
	 * @param hscroll
	 * @param vscroll
	 */
	public void changeScrolling(boolean hscroll, boolean vscroll){
		canvas.changeScrolling(hscroll, vscroll);
	}
	
}
