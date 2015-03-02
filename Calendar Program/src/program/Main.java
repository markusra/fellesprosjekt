package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	//Husk aa adde alle grensesnitt (.fxml filer) under screens, og lag et navn for den under ID.
	public final static String loginID = "LoginScreen";
	private final static String loginScreen = "/user/LoginScreen.fxml";
	public final static String forgotID = "ForgotScreen";
	private final static String forgotScreen = "/user/ForgotScreen.fxml";
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreensController screensController = new ScreensController();
		
		//Husk aa laste inn grensesnitt med navn her.
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
