package group;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.ScreensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		lvGroupmember.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		List<String> myList;
		myList = new ArrayList<>();
	    myList.add(new String("Tøffel"));
	    myList.add(new String("Vebbi"));
	    myList.add(new String("Tommy"));
	    myList.add(new String("Dani"));
	    myList.add(new String("Marki"));
	    
		ObservableList<String> myObservableList = FXCollections.observableList(myList);
	    lvGroupmember.setItems(myObservableList);
	     
	 
	}
}
