package menu;

import java.io.IOException;
import java.util.Calendar;

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
			Calendar calendar = Calendar.getInstance();
			System.out.println(calendar.get(Calendar.YEAR) + (calendar.get(Calendar.MONTH)+1) + calendar.get(Calendar.DATE));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



