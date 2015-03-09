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
import program.Main;
import program.ScreensController;
import program.ServerCodes;
import user.TCPClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import json.JsonArray;
import json.JsonValue;

public class GroupScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	private TextField txtGroupName;
	
	@FXML
	private TextField txtAddUsers;

	@FXML
	private ListView<String> lvFilteredUsers;
	
	@FXML
	private ListView<String> lvChosenMembers;
	
	@FXML
	private ComboBox<String> cmbSubgroupOf;
	
	@FXML
	Button backToMainPageButton;
	
	//Metode for backToMainPageButton
	@FXML
	public void handleBackToMainPageButton(ActionEvent event) {
		mainController.setScreen(Main.mainPageID);
	}
	
	
	TCPClient client;
	List<String> groupList;
	List<String> chosenMembers = new ArrayList<>();
	Map<String, Integer> availableUsers = new HashMap<String, Integer>();
	
	@FXML
	private void doConfirm() {
		System.out.println("CONFIRM");
	}

	@FXML
	private void doAddMember() {
		String chosenMember = lvFilteredUsers.getSelectionModel().getSelectedItem();
		if (! chosenMembers.contains(chosenMember) && chosenMember != null) {
			chosenMembers.add(chosenMember);
		}
		
		ObservableList<String> myObservableList = FXCollections.observableList(chosenMembers);
		lvChosenMembers.setItems(myObservableList);
	}
	
	@FXML
	private void doDeleteMember() {
		String chosenMember = lvChosenMembers.getSelectionModel().getSelectedItem();
		if (chosenMember != null) {
			System.out.println("test");
			chosenMembers.remove(chosenMembers.indexOf(chosenMember));
		}
		
		ObservableList<String> myObservableList = FXCollections.observableList(chosenMembers);
		lvChosenMembers.setItems(myObservableList);
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		
	}
	
	private void fillComboboxWithGroups(TCPClient client) throws IOException {
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
	}
	
	private boolean findUniqueUsers(TCPClient client, String streng) throws IOException {
		String serverReply = client.customQuery(ServerCodes.GetSpecificUser, "'" + streng + "'");
		String[] answer= serverReply.split("#");
		
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		List<String> userList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			//int  brukerID = value.asObject().get( "brukerID" ).asInt();
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String temp = fornavn + " " + etternavn + " (" + brukernavn + ")";
			
			
			//availableUsers.put(brukernavn, brukerID);
			userList.add(temp);
			
			//txtAddUsers.setText(temp);
		}

		//System.out.println(availableUsers.get( "markusra" ));
		
		ObservableList<String> myObservableList = FXCollections.observableList(userList);
		lvFilteredUsers.setItems(myObservableList);
		
		
		return false;
	}
	
	private void fetchData(TCPClient client) throws IOException {
		
		fillComboboxWithGroups(client);
		
	}
	
	private void createListeners(TCPClient client) {
		
		txtGroupName.textProperty().addListener((observable, oldValue, newValue) -> {
			
			System.out.println(cmbSubgroupOf.getValue());
			
			if (groupList.contains(newValue.trim())) {
				txtGroupName.setStyle("-fx-border-color: red; -fx-border-radius: 3; -fx-border-width: 0.5; -fx-background-color: #ffbbbb;");
			} else {
				txtGroupName.setStyle("");
			}
			
		});
		
		txtAddUsers.textProperty().addListener((observable, oldValue, newValue) -> {
			
			try {
				if (newValue.length() > 0 && findUniqueUsers(client, newValue)) {
					System.out.println("FOUND!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		lvFilteredUsers.setStyle("-fx-font-size:30;");
		lvChosenMembers.setStyle("-fx-font-size:30;");
		cmbSubgroupOf.setStyle("-fx-font-size:30;");
		txtGroupName.setStyle("-fx-font-size:30;");
		txtAddUsers.setStyle("-fx-font-size:30;");
		
		//lvFilteredUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			client = new TCPClient();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		createListeners(client);
		
		try {
			fetchData(client);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
