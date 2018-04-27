package frontend_utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * @author jennychin
 * for alerts
 */

public class UserFeedback {

	public static Alert getErrorMessage(String header, String content, Stage stage){
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle(AuthRes.getString("ErrorTitle"));
		a.setHeaderText(header);
		a.setContentText(content);
		a.initOwner(stage);
		return a;
	}
	
}
