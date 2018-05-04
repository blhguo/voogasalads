package authoring.controllers;

import authoring.Canvas;
import authoring.right_components.LevelPane;
import javafx.scene.image.Image;
import resources.keys.AuthRes;

/**
 * @author jennychin
 * @author elizabethschulman
 * Handles pane interactions (i.e. one pane needing to update another)
 */
public class PaneController {

	private Canvas canvas;
	private EntityController econtroller;
	
	public PaneController(Canvas c){
		canvas = c;
	}

	/**
	 *
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
	
//	private void setCanvasLevel(int id){
//		canvas.setLevel(id);
//	}
	
	public void updateCanvas(){
		canvas.update(econtroller.getEntities());
	}
	
	public void updateCanvas(int id){
		canvas.setLevel(id);
		canvas.update(econtroller.getEntities());
	}
	
	public void setEntityController(EntityController ec){
		econtroller = ec;
	}
	
	public void changeScrolling(boolean hscroll, boolean vscroll){
		canvas.changeScrolling(hscroll, vscroll);
	}
	
}
