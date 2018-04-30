package frontend_utilities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * @author Liam Pulsifer
 * @author Elizabeth Shulman
 * Improved form of ImageView, allows use to click an ImageView and drag it around.
 * Does not allow the user to drag the ImageView outside of the Canvas
 */
public class DraggableImageView extends ImageView {
	private double mouseX;
	private double mouseY;
	private double minX = 0;
	private double maxX;
	private double minY = 0;
	private double maxY;
	private boolean clicked;

	/**
	 * Makes a Draggable ImageView with given image
	 * @param image
	 */
	public DraggableImageView(Image image) {
		super(image);
		clicked = false;
		this.setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
			System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
		});
		this.setOnMouseDragged(event -> {
			int margin = 2 * AuthRes.getInt("Margin");
			maxX = getParent().getLayoutBounds().getWidth() - margin;
			maxY = getParent().getLayoutBounds().getHeight() - margin;
			//System.out.println("Dragged -- Mouse X: " + mouseX + " Mouse Y: " + mouseY);
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

	public void setOnMouse(EventHandler<MouseEvent> eventHandler) {
		this.setOnMousePressed(e -> {
			eventHandler.handle(e);
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
	}

	public void toggleStyle() {
		if (!clicked){
			this.setStyle("-fx-opacity: .5;");
			clicked = true;
			System.out.println("Set style to clicked");
		}
		else {
			System.out.println("Set style to null");
			this.setStyle("");
		}
	}
}
