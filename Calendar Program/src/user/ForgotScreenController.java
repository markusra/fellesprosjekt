package user;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import json.JsonArray;

public class ForgotScreenController implements Initializable, ControllerInterface {
	
	
	ScreensController mainController;
	
	@FXML
	Pane mainPane;
	
	@FXML
	TextField txtEmail;
	
	@FXML
	Button back;
	
	@FXML
	Button btnGetPassword;
	

	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "ESCAPE"){
        	mainController.setScreen(Main.loginID, Main.loginScreen);
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
	

	@FXML
	public void handleForgotPasswordButton(ActionEvent event) throws UnknownHostException, IOException {
		TCPClient client = new TCPClient();
		
		String serverReply = client.customQuery(ServerCodes.GetUserWithEmail, "'" + txtEmail.getText() + "'");
		
		String[] answer = serverReply.split("#");

		try {
			JsonArray jsonArray = JsonArray.readFrom( answer[1] );
			
			String username = jsonArray.get(0).asObject().get( "brukernavn" ).asString();
			String password = jsonArray.get(0).asObject().get( "passord" ).asString();
			
			client.customQuery(ServerCodes.SendPassword, "'" + txtEmail.getText() + "'" + ", '" + username + "'" + ", '" + password + "'");
			
			mainController.setScreen(Main.loginID, Main.loginScreen);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@FXML
	public void handleBackButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.loginID, Main.loginScreen);
	}
}