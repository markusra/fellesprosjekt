package group;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import appointment.AppointmentScreenController;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/group/CreateGroupScreen.fxml"));
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setController(new AppointmentScreenController());
			Scene scene = new Scene(root,350,430);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Appointment");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}




