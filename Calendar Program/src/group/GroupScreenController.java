package group;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.ScreensController;
import program.ServerCodes;
import user.TCPClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import json.JsonArray;
import json.JsonValue;

public class GroupScreenController implements Initializable, ControllerInterface {
	
	@FXML
	private TextField txtName;

	@FXML
	private ListView<String> lvGroupmember;
	
	
	
	@FXML
	private void doConfirm() {
		
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		
	}
	
	private void fetchData() throws UnknownHostException, IOException {
		TCPClient client = new TCPClient();
		String serverReply = client.customQuery(ServerCodes.GETALLGROUPS, "'None'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		List<String> groupList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			String gruppeNavn = value.asObject().get( "navn" ).asString();
			groupList.add(gruppeNavn);
		}
		
		ObservableList<String> myObservableList = FXCollections.observableList(groupList);
	    lvGroupmember.setItems(myObservableList);
	    
	    
	    
	    serverReply = client.customQuery(ServerCodes.GETALLUSERS, "'None'");
		answer = serverReply.split("#");
		
		jsonArray = JsonArray.readFrom( answer[1] );
		
		List<String> userList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String temp = fornavn + " " + etternavn + " (" + brukernavn + ")";
			userList.add(temp);
		}
		
		myObservableList = FXCollections.observableList(userList);
	    lvGroupmember.setItems(myObservableList);
	     
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		lvGroupmember.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			fetchData();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	 
	}
}
