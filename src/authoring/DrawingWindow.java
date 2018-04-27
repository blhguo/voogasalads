package authoring;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class DrawingWindow extends Pane {

	/**
	 * Constructor sets up canvas
	 */
	public DrawingWindow() {
		setupInitialCanvas();
	}
	/**
	 * Initializes canvas size and background color
	 */
	private void setupInitialCanvas() {
		this.setPrefSize(4000,4000);
		this.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		this.setBackgroundColor(Color.rgb(179, 179, 179, 0.7));
	}
	/**
	 * Method to change background color by
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		String hex = String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
		this.setStyle(
				"-fx-background-color: " + hex + ";"
				);
	} }