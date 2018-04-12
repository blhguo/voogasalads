package authoring.utilities;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import resources.keys.AuthRes;

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
	private double maxX;
	private double minY = 0;
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
		});
		this.setOnMouseDragged(event -> {
			int margin = AuthRes.getInt("Margin");
			maxX = getParent().getLayoutBounds().getWidth() - margin;
			maxY = getParent().getLayoutBounds().getHeight() - margin;
			double deltaX = event.getSceneX() - mouseX ;
			double deltaY = event.getSceneY() - mouseY ;
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			setXPos(this.getX() + deltaX, minX, maxX);
			setYPos(this.getY() + deltaY, minY, maxY);
			this.toFront();
		});
	}
	public void addHandler(EventHandler<MouseDragEvent> eventHandler){
		this.setOnMouseDragReleased(eventHandler);
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

