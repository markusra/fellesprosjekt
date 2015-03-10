package group;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import appointment.AppointmentScreenController;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/group/ManageGroupsScreen.fxml"));
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setController(new AppointmentScreenController());
			
	        final Scale scale = new Scale(0.53333, 0.5333);
		    root.getTransforms().add(scale);
		    Scene scene = new Scene(root,1024,576);
		    
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
		    //TODO: Må endre til bare "application.css" når prosjektet leveres
		    scene.getStylesheets().add("//src/application.css");
			
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




