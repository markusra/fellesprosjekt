package program;

import java.io.IOException;
import java.util.HashMap;

import client.TCPClient;
import user.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

public class ScreensController extends StackPane {
	
	private UserModel user;
	
	private HashMap<String, String> screens = new HashMap<String, String>();
	
	final private Scale scale = new Scale(0.5333, 0.5333);
	
	
	
	//Legger til et grensesnitt i HashMappet
	public void addScreen(String name, String resource) {
		screens.put(name, resource);
	}
	
	
	//Henter et grensesnitt fra HashMappet
	private String getScreen(String name) {
		return screens.get(name);
	}
	
	
	//Laster inn FXML grensesnittet og har det klart i screens HashMappet
	private Parent loadScreen(String name) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(getScreen(name)));
		Parent screen = (Parent) loader.load();
		screen.getTransforms().add(scale);
		ControllerInterface controller = ((ControllerInterface) loader.getController());
		controller.setScreenParent(this);
		return screen;
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
	public void setScreen(String name, String resource) throws IOException {
		Node node = loadScreen(name);
		if (getScreen(name) != null) {
			if (!getChildren().isEmpty()) {
				getChildren().remove(0);
				getChildren().add(0, node);
			}
			else {
				getChildren().add(node);
			}
		}
		else {
			System.out.println("ScreensController.setScreen failed!");
		}
	}
	
	
	public UserModel getUser() {
		return user;
	}
	
	
	public void setUser(UserModel user) {
		this.user = user;
	}
}
