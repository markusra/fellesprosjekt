package menu;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/menu/MainPageScreen.fxml"));
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setController(new MainPageScreenController());
	     
			
			final Scale scale = new Scale(0.53333, 0.53333);
		    root.getTransforms().add(scale);
		    Scene scene = new Scene(root,1024,576);
		    //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		    primaryStage.setScene(scene);
			primaryStage.setTitle("MainPage");
			primaryStage.show();
			
			MainPageScreenController controller = fxmlLoader.<MainPageScreenController>getController();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



