package frontend_utilities;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.List;

/**
 * @author Liam Pulsifer
 * A simple class to create ComboBoxes out of Lists of Strings
 */
public class ComboBoxBuilder {

	public static ComboBox<String> getComboBox(List<String> list) {
		ComboBox<String> box = new ComboBox<>();
		box.setItems(FXCollections.observableArrayList(list));
		return box;
	}
}
