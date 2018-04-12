package authoring.utilities;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;

/**
 * improved form of imageview, allows for dragging
 * specifically, dragging it is possible, as well as double clicking allowing for rotation
 */
/**
 * improved form of imageview, allows for dragging
 * specifically, dragging it is possible, as well as double clicking allowing for rotation
 */
public class DraggableImageView extends ImageView {
	private double mouseX;
	private double mouseY;
	private double minX = 0;
	private double maxX = 610;
	private double minY = 0;
	private double maxY = 640;
	/**
	 * makes a draggable image with given image
	 * @param image
	 */
	public DraggableImageView(Image image) {
		super(image);
		this.setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		this.setOnMouseDragged(event -> {
			//System.out.println(event.getClass());
			double deltaX = event.getSceneX() - mouseX ;
			double deltaY = event.getSceneY() - mouseY ;
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			//Pane p = (Pane) event.getTarget();
//			System.out.println(event.getTarget().getClass().toString());
//			System.out.println(event.getSource().getClass().toString());
			setXPos(this.getX() + deltaX, minX, maxX);
			setYPos(this.getY() + deltaY, minY, maxY);
			this.toFront();
		});
	}
	public void addHandler(EventHandler<MouseDragEvent> eventHandler){
		this.setOnMouseDragReleased(eventHandler);
	}
	
	public void setBounds(Pane p){
		//Bounds boundsInScene = p.localToScene(p.getBoundsInLocal());
		//System.out.println(boundsInScene.getMinX());
		//Point2D point = p.localToScene(p.getLayoutX(), p.getLayoutY());
		//System.out.println(point.getX());
//		minX = p.getLayoutX();
//		//System.out.println(minX);
//		minY = p.getLayoutY();
//		maxX = p.getLayoutX() + p.getWidth();
//		maxY = p.getLayoutY() + p.getHeight();
		//System.out.println(p.getWidth());
	}
	
	private void setXPos(double pos, double minLimit, double maxLimit){
		if (pos > maxLimit){
			this.setX(maxLimit);
		}
		else if (pos < minLimit){
			this.setX(minLimit);
		}
		else{
			this.setX(pos);
		}
	}
	
	private void setYPos(double pos, double minLimit, double maxLimit){
		if (pos > maxLimit){
			this.setY(maxLimit);
		}
		else if (pos < minLimit){
			this.setY(minLimit);
		}
		else{
			this.setY(pos);
		}
	}
}

