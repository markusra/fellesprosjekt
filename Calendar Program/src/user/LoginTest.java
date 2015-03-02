package user;

import java.io.IOException;
import java.net.UnknownHostException;

import org.json.simple.parser.ParseException;

public class LoginTest {
	
	public static void main(String[] args) throws UnknownHostException, IOException, ParseException {
		TCPClient client = new TCPClient();
		
		if (client.validLogin("markusra", "test1234")) {
			System.out.println("Successful login!");
			
			
		} else {
			System.out.println("Error");
			
		}
		//Hvis feil blir usernameboksen eller passordboksen rød
	}
}
