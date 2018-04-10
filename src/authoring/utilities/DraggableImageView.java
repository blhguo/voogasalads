package authoring.utilities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

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
	/**
	 * makes a draggable image with given image
	 * @param image
	 */
	public DraggableImageView(Image image) {
		super(image);
		this.setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
			this.setClick(ev -> System.out.println(ev));
		});
		this.setOnMouseDragged(event -> {
			double deltaX = event.getSceneX() - mouseX ;
			double deltaY = event.getSceneY() - mouseY ;
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			this.setX(this.getX() + deltaX);
			this.setY(this.getY() + deltaY);
			this.toFront();
		});
	}
	public void addHandler(EventHandler<MouseDragEvent> eventHandler){
		this.setOnMouseDragReleased(eventHandler);
	}
	public void setClick(EventHandler<MouseEvent> e){
		this.setOnMouseClicked(e);
	}
}

