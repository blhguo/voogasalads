package authoring;

import javafx.scene.control.ScrollPane;


public class ScrollingDrawingWindow extends ScrollPane {
	private DrawingWindow myDrawingWindow;
	/**
	 * Constructor that creates new internal canvas
	 * and sets up ScrollPane
	 */
	public ScrollingDrawingWindow() {
		myDrawingWindow = new DrawingWindow();
		setupScrollingWindow();
	}
	/**
	 * Method to initialize scrolling window
	 * Always show scrolling bars.
	 * Sets view to the center of internal canvas,
	 * as turtle is initialized in center
	 * 
	 */
	private void setupScrollingWindow() {
		this.setContent(myDrawingWindow);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		myDrawingWindow.setManaged(false);
		this.setHvalue(0.5);
		this.setVvalue(0.5);
		this.setPrefSize(400, 800);
	}
	/**
	 * Method to
	 * @return internal canvas DrawingWindow
	 */
	public DrawingWindow getInternalCanvas() {
		return myDrawingWindow;
	}
	
}
