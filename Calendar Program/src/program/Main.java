package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	//Husk å adde alle grensesnitt (.fxml filer) under screens, og lag et navn for den under ID.
	public final static String loginID = "Login";
	private final static String loginScreen = "/user/Login.fxml";
	public final static String forgotID = "Forgot";
	private final static String forgotScreen = "/user/Forgot.fxml";
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreensController screensController = new ScreensController();
		
		//Husk å laste inn grensesnitt med navn her.
		screensController.loadScreen(loginID, loginScreen);
		screensController.loadScreen(forgotID, forgotScreen);
		
		screensController.setScreen(loginID);
		
		Group root = new Group();
		root.getChildren().addAll(screensController);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
