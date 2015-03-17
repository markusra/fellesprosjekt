package group;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import json.JsonArray;
import json.JsonValue;

public class ViewGroupsScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	AnchorPane mainPane;
	
	@FXML
	Button backToMainPageButton;
	
	@FXML
	ListView<String> lvGroups;
	
	TCPClient client;
	
	//Metode for backToMainPageButton
	@FXML
	public void handleBackToMainPageButton(ActionEvent event) throws IOException {
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "BACK_SPACE" || code.toString() == "ENTER" || code.toString() == "ESCAPE" || code.toString() == "LEFT" || code.toString() == "B"){
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
		
		lvGroups.setStyle("-fx-font-size:30;");
		
		//lvGroups.setMouseTransparent( true );
		//lvGroups.setFocusTraversable( false );
		
		try {
			client = new TCPClient();
			String serverReply = client.customQuery(ServerCodes.GetAllMemberGroups, "" + ScreensController.getUser().getUserID());
			String[] answer= serverReply.split("#");
			
			JsonArray jsonArray = JsonArray.readFrom( answer[1] );
			
			List<String> groupList = new ArrayList<>();
			
			for( JsonValue value : jsonArray ) {
				String gruppeNavn = value.asObject().get( "navn" ).asString();
				groupList.add(gruppeNavn);
			}

			ObservableList<String> myObservableList = FXCollections.observableList(groupList);
			lvGroups.setItems(myObservableList);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		lvGroups.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		            	
		            	System.out.println("clicked on " + lvGroups.getSelectionModel().getSelectedItem());
		            
		            	try {
							mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            
		            }
		        }
		    }
	    });
	}
	
}

