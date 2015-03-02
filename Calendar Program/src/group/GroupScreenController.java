package group;

import java.net.URL;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.ScreensController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GroupScreenController implements Initializable, ControllerInterface {
	
	@FXML
	private TextField txtName;
	@FXML
	private ListView supergroupID;
	@FXML
	private ListView groupmember;
	
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
		
	}
}
