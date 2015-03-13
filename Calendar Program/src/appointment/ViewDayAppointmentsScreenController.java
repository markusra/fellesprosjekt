package appointment;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import json.JsonArray;
import json.JsonValue;

public class ViewDayAppointmentsScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	AnchorPane mainPane;
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
		if(code.toString() == "BACK_SPACE" || code.toString() == "ESCAPE" || code.toString() == "ENTER" || code.toString() == "LEFT" || code.toString() == "B"){
			mainController.setScreen(Main.mainPageID, Main.mainPageScreen);	
		}else{
			event.consume();
		}
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mainPane.setFocusTraversable(true);
		
	}
}

