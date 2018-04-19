package user_interface;

import javafx.scene.ParallelCamera;

public class GameCamera {
	private ParallelCamera cam;
	
	public ParallelCamera initCamera() {
		cam = new ParallelCamera();
		return cam;
	}
	public void setCamera(double xLayout, double yLayout) {
		cam.setLayoutX(xLayout);
		cam.setLayoutY(yLayout);
	}

}
