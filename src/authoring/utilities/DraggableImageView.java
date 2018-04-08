package authoring.utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * improved form of imageview, allowes for dragging
 * specifically, dragging it is possible, as well as double clicking allowing for rotation
 */
public class DraggableImageView extends ImageView {
	private double mouseX;
	private double mouseY;
	/**
	 * makes a draggable image with given turtle and image
	 * @param image
	 */
	public DraggableImageView(Image image) {
		super(image);
		this.setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		this.setOnMouseDragged(event -> {
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			this.setX(event.getSceneX());
			this.setY(event.getSceneY());
		});
		this.setOnMouseDragReleased(event -> {
			this.setX(mouseX);
			this.setY(mouseY);
		});
	}
}