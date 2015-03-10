package program;

import java.io.IOException;
import java.util.HashMap;

import user.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

public class ScreensController extends StackPane {
	
	public UserModel user;
	
	private HashMap<String, Node> screens = new HashMap<String, Node>();
	
	final Scale scale = new Scale(0.53333, 0.53333);
	
	
	public ScreensController() {
		super();
	}
	
	
	//Legger til et grensesnitt i HashMappet
	private void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}
	
	
	//Henter et grensesnitt fra HashMappet
	private Node getScreen(String name) {
		return screens.get(name);
	}
	
	
	//Laster inn FXML grensesnittet og har det klart i screens HashMappet
	public void loadScreen(String name, String resource) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent screen = (Parent) loader.load();
			screen.getTransforms().add(scale);
			ControllerInterface controller = ((ControllerInterface) loader.getController());
			controller.setScreenParent(this);
			addScreen(name, screen);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ScreensController.loadScreen failed!");
		}
	}
	
	
	//Brukes for � fjerne grensesnitt fra HashMappet, generelt sett un�dvendig.
	public void unloadScreen(String name) {
		if (getScreen(name) != null) {
			screens.remove(name);			
		}
		else {
			System.out.println("ScreensController.unloadScreen failed!");
		}
	}
	
	
	//Setter viewet til grensesnittet med navnet name, som eksisterer i screens HashMappet
	public void setScreen(String name) {
		if (getScreen(name) != null) {
			if (!getChildren().isEmpty()) {
				getChildren().remove(0);
				getChildren().add(0, getScreen(name));
			}
			else {
				getChildren().add(getScreen(name));
			}
		}
		else {
			System.out.println("ScreensController.setScreen failed!");
		}
	}
}
