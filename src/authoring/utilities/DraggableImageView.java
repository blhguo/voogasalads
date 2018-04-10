package authoring.utilities;

import javafx.event.EventHandler;
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
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	/**
	 * makes a draggable image with given image
	 * @param image
	 */
	public DraggableImageView(Image image) {
		super(image);
		this.setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
			System.out.println("scene x: " + mouseX);
			System.out.println("scene y: " + mouseY);
			System.out.println("pane x: " + e.getX());
			System.out.println("pane y: " + e.getY());
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
		minX = p.getLayoutX();
		minY = p.getLayoutY();
		maxX = p.getLayoutX() + p.getWidth();
		maxY = p.getLayoutY() + p.getHeight();
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

