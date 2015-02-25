package program;

import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class ScreensController {
	private HashMap<String, Node> screens;
	
	private void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}
	
	private Node getScreen(String name) {
		return screens.get(name);
	}
	
	public boolean loadScreen(String name, String resource) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
		Parent loadScreen = (Parent) loader.load();
		ControllerInterface controller
	}
}
