package frontend_utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * @author jennychin
 * for alerts
 */

public class UserFeedback {

	public static Alert getErrorMessage(String header, String content, Stage stage){
		return makeAlert(AlertType.ERROR, AuthRes.getString("ErrorTitle"), header, content, stage);
	}
	
	public static Alert getInfoMessage(String header, String content, Stage stage){
		return makeAlert(AlertType.INFORMATION, AuthRes.getString("InfoTitle"), header, content, stage);
	}
	
	public static Alert getWarningMessage(String header, String content, Stage stage){
		Alert a = new Alert(AlertType.WARNING, content, ButtonType.CANCEL, ButtonType.OK);
		a.setTitle(AuthRes.getString("WarningTitle"));
		a.setHeaderText(header);
		a.initOwner(stage);
		return a;
	}
	
	private static Alert makeAlert(AlertType at, String title, String header, String content, Stage stage){
		Alert a = new Alert(at);
		a.setTitle(title);
		a.setHeaderText(header);
		a.setContentText(content);
		a.initOwner(stage);
		return a;
	}
	
}
