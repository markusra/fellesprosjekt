package group;

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
import javafx.scene.layout.Pane;
import json.JsonArray;
import json.JsonValue;

public class GroupScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Pane mainPane;
	
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
	
	TCPClient client;
	List<String> groupList;
	List<String> chosenMembers = new ArrayList<>();
	
	boolean validInput = false;
	
	//Metode for backToMainPageButton
	@FXML
	public void handleBackToMainPageButton(ActionEvent event) throws IOException {
		client.disconnect();
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	
	Map<String, Integer> groups = new HashMap<String, Integer>();
	Map<String, Integer> availableUsers = new HashMap<String, Integer>();
	
	private void createGroup() throws IOException {
		String chosenSuperGroup = cmbSubgroupOf.getValue();
		
		String supergruppeID = "null";
		
		System.out.println(chosenSuperGroup);
		if (! chosenSuperGroup.equals("None")) {
			supergruppeID = groups.get( chosenSuperGroup ).toString();
		} 
		
		client.customQuery(ServerCodes.CreateGroup, "'" + txtGroupName.getText() + "', '" + supergruppeID + "'");
	}
	
	private void getAllUsers() throws IOException {
		String serverReply = client.customQuery(ServerCodes.GetAllUsers, "'None'");
		String[] answer= serverReply.split("#");
		
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		availableUsers.clear();
		
		for( JsonValue value : jsonArray ) {
			int  brukerID = value.asObject().get( "brukerID" ).asInt();
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			
			availableUsers.put(brukernavn, brukerID);
		}
		
		
	}
	
	private void addMembersToGroup() throws IOException {
		// First things first: Get the groupID of this group
		
		String groupName = txtGroupName.getText();
		
		String serverReply = client.customQuery(ServerCodes.GetSpecificGroup, "'" + groupName + "'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
	
		int groupID = jsonArray.get(0).asObject().get( "gruppeID" ).asInt();
		
		System.out.println("UserID: " + ScreensController.getUser().getUserID());
		client.customQuery(ServerCodes.CreateGroupMember, ScreensController.getUser().getUserID() + ", " + groupID + ", " + "True");

		// Then add all the other members to the group
		for (String member : chosenMembers) {
			String[] memberArray = member.split("\\(");
			member = memberArray[1].substring(0, memberArray[1].length()-1);
			
			System.out.println("Member: " + member);
			
			client.customQuery(ServerCodes.CreateGroupMember, availableUsers.get(  member ) + ", " + groupID + ", " + "False");
		}
		
	}
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
		if(code.toString() == "BACK_SPACE" || code.toString() == "ESCAPE"){
			mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
		}else if(code.toString() == "ENTER"){
			doConfirm();
		}else{
			event.consume();
		}
	}
	
	
	@FXML
	private void doConfirm() throws IOException {
		if (validInput) {
			createGroup();
			addMembersToGroup();
		
			client.disconnect();
			System.out.println("GROUP CREATED");
			mainController.setScreen(Main.groupSucceededID, Main.groupSucceededScreen);
			//System.out.println(mainController.user.getUserID());
		}
		
		
	}

	@FXML
	private void doAddMember() {
		String chosenFilteredMember = lvFilteredUsers.getSelectionModel().getSelectedItem().toString();
		
		if (! chosenMembers.equals(chosenFilteredMember) && chosenFilteredMember != null) {
			chosenMembers.add(chosenFilteredMember);
			
			ObservableList<String> myObservableList = FXCollections.observableList(chosenMembers);
			lvChosenMembers.setItems(myObservableList);
		}
		
		
	}
	
	@FXML
	private void doDeleteMember() {
		String chosenMember = lvChosenMembers.selectionModelProperty().get().getSelectedItem().toString();

		System.out.println(chosenMember);
		if (chosenMember != null) {			
			chosenMembers.remove(chosenMembers.indexOf(chosenMember));
			
			ObservableList<String> myObservableList = FXCollections.observableList(chosenMembers);
			lvChosenMembers.setItems(myObservableList);
		}
		
	}
	


	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	
	private void fillComboboxWithGroups(TCPClient client) throws IOException {
		String serverReply = client.customQuery(ServerCodes.GetAllGroups, "'None'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		groupList = new ArrayList<>();
		
		groupList.add("None");
		for( JsonValue value : jsonArray ) {
			int gruppeID = value.asObject().get( "gruppeID" ).asInt();
			String gruppeNavn = value.asObject().get( "navn" ).asString();
			groupList.add(gruppeNavn);
			groups.put(gruppeNavn, gruppeID);
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
			int  brukerID = value.asObject().get( "brukerID" ).asInt();
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String temp = fornavn + " " + etternavn + " (" + brukernavn + ")";
			
			if (brukerID != ScreensController.getUser().getUserID()) {
				userList.add(temp);
			}
			
		}

		//System.out.println(availableUsers.get( "(markusra)" ));
		
		ObservableList<String> myObservableList = FXCollections.observableList(userList);
		lvFilteredUsers.setItems(myObservableList);
		
		
		return false;
	}
	
	private void fetchData(TCPClient client) throws IOException {
		
		fillComboboxWithGroups(client);
		
	}
	
	private void createListeners(TCPClient client) {
		
		txtGroupName.textProperty().addListener((observable, oldValue, newValue) -> {
			
			//System.out.println(cmbSubgroupOf.getValue());
			
			if (groupList.contains(newValue.trim())) {
				txtGroupName.setStyle("-fx-border-color: red; -fx-border-radius: 3; -fx-border-width: 0.5; -fx-background-color: #ffbbbb;");
				validInput = false;
			} else {
				txtGroupName.setStyle("");
				validInput = true;
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
		mainPane.setFocusTraversable(true);
		
		lvFilteredUsers.setStyle("-fx-font-size:30;");
		lvChosenMembers.setStyle("-fx-font-size:30;");
		cmbSubgroupOf.setStyle("-fx-font-size:30;");
		txtGroupName.setStyle("-fx-font-size:30;");
		txtAddUsers.setStyle("-fx-font-size:30;");
		
		cmbSubgroupOf.setValue("None");
		
		//lvFilteredUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		try {
			client = new TCPClient();
			getAllUsers();
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
