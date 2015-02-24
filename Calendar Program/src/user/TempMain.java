package user;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TempMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			LoginController controller = new LoginController();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			loader.setController(controller);
			Pane login = (Pane) loader.load();
			primaryStage.setScene(new Scene(login));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}