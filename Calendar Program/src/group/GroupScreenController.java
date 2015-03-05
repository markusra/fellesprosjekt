package group;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.ScreensController;
import program.ServerCodes;
import user.TCPClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import json.JsonArray;
import json.JsonValue;

public class GroupScreenController implements Initializable, ControllerInterface {
	
	@FXML
	private TextField txtGroupName;

	@FXML
	private ListView<String> lvGroupmember;
	
	@FXML
	private ComboBox<String> cmbSubgroupOf;
	
	TCPClient client;
	List<String> groupList;
	Map<String, Integer> availableUsers = new HashMap<String, Integer>();
	
	@FXML
	private void doConfirm() {
		System.out.println("CONFIRM");
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		
	}
	
	private void fetchData() throws UnknownHostException, IOException {
		client = new TCPClient();
		
		String serverReply = client.customQuery(ServerCodes.GetAllGroups, "'None'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		groupList = new ArrayList<>();
		
		groupList.add("None");
		for( JsonValue value : jsonArray ) {
			String gruppeNavn = value.asObject().get( "navn" ).asString();
			groupList.add(gruppeNavn);
		}
		
		ObservableList<String> myObservableList = FXCollections.observableList(groupList);
		cmbSubgroupOf.setItems(myObservableList);
		
		//lvGroupmember.getItems().addAll(myObservableList);
	    
	    serverReply = client.customQuery(ServerCodes.GetAllUsers, "'None'");
		answer = serverReply.split("#");
		
		jsonArray = JsonArray.readFrom( answer[1] );
		
		List<String> userList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			
			int  brukerID = value.asObject().get( "brukerID" ).asInt();
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String temp = fornavn + " " + etternavn + " (" + brukernavn + ")";
			
			
			availableUsers.put(brukernavn, brukerID);
			userList.add(temp);
		}
		
		System.out.println(availableUsers.get( "markusra" ));
		
		myObservableList = FXCollections.observableList(userList);
	    lvGroupmember.setItems(myObservableList);
	     
	}
	
	private void createListeners() {
		
		txtGroupName.textProperty().addListener((observable, oldValue, newValue) -> {
			
			System.out.println(cmbSubgroupOf.getValue());
			
			if (groupList.contains(newValue)) {
				txtGroupName.setStyle("-fx-border-color: red; -fx-border-radius: 3; -fx-border-width: 0.5; -fx-background-color: #ffbbbb;");
			} else {
				txtGroupName.setStyle("");
			}
			
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		lvGroupmember.setStyle("-fx-font-size:30;");
		cmbSubgroupOf.setStyle("-fx-font-size:30;");
		txtGroupName.setStyle("-fx-font-size:30;");
		
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
		
		createListeners();
		
		

	}
}
