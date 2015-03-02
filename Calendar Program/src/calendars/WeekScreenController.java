package calendars;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class WeekScreenController {
	//Uke
	
	////Ukenummer
	@FXML
	private Text weekNumber;
	
		//weekNumber må settes etter hva som er riktig ukenummer.
	
	////Bytte uke
	@FXML
	private ImageView previousWeekButton, nextWeekButton;
	
	@FXML
	private void changeToPreviousWeek(ActionEvent event){
		//Hvordan kan vi få byttet til forrige ukesoversikt?
	}
	
	@FXML
	private void changeToNextWeek(ActionEvent event){
		//Hvordan bytte til neste ukesoversikt?
	}
	
	
	//Mandag
	
	////Datofelt
	@FXML
	private Text mondayDate, mondayMonth;
	
		/*
		 * Dette gjelder for alle dagene:
		 * 
		 * Text mondayDate og Text mondayMonth må settes ut etter hva som er riktig dato og måned for den gitte dagen. 
		 */
	
	////Avtalefelt
	
	@FXML
	private Label ingenAvtalerMonday;
	
		/* 
		 * Dette gjelder for alle dagene:
		 * 
		 * a) Dersom det ikke er satt opp noen møter på en dag vil bare Label "Ingen avtaler" være visible.
		 * b) Dersom det er satt opp noen avtaler vil Label "Ingen avtaler" være satt til visible=False.
		 * 		1) Dersom det er satt opp 1 avtale vil avtaleformatet bestemme hva som skjer:
		 * 			x) Dersom avtalen er tildelt et rom må GridPane (3 x 1) (0,0) settes til visible=True
		 * 			   og Label for tid, avtalenavn og rom i GridPane må settes.
		 * 			y) Dersom avtalen ikke er tildelt et rom må GridPane (2 x 1) (0,0) settes til visible=True
		 * 			   og Label for tid og avtalenavn i GridPane må settes.
		 * 		2) Dersom det er satt opp 2 avtaler gjøres tilsvarende for den tidligste avtalen som beskrevet i 1).
		 * 		   Avtale nr. 2 vil også gjøres på tilsvarende måte basert på om det er lagt inn rom eller ikke,
		 * 		   men vi må sette visible=True for GridPane (3 x 1) (0,1) eller GridPane (2 x 1) (0,1).
		 * 		3) Dersom det er satt opp 3 avtaler gjøres det samme som beskrevet i 1) og vi settes visible=True
		 * 		   på enten GridPane (3 x 1) (0,2) eller GridPane (2 x 1) (0,2).
		 * 		4) Dersom det er satt opp mer enn 3 avtaler vil vi gjøre som beskrevet i 1) og 2) for de 2 første avtalene,
		 * 		   mens vi i rad 3 vil sette visible=True for Label "+ ? andre avtaler" der vi vil sette ?=(antallAvtaler-2)
		 *    
		 */
	
	
	
	
	
	//Tirsdag
	
	////Datofelt
	@FXML
	private Text tuesdayDate, tuesdayMonth;
	
		//*
	
	////Avtalefelt
		
		//*
	
	
	
	
	
	
	//Onsdag
	
	////Datofelt
	@FXML
	private Text wednesdayDate, wednesdayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	
	
	//Torsdag
	
	////Datofelt
	@FXML
	private Text thursdayDate, thursdayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	
	
	//Fredag
	
	////Datofelt
	@FXML
	private Text fridayDate, fridayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	
	//Lørdag
	
	////Datofelt
	@FXML
	private Text saturdayDate, saturdayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	//Søndag
	
	////Datofelt
	@FXML
	private Text sundayDate, sundayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
}
