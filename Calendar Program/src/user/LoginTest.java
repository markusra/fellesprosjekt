package user;

import java.io.IOException;

public class LoginTest {
	
	public static void main(String[] args) throws IOException {
		TCPClient client = new TCPClient();
		
		if (client.validLogin("markusra", "test1234")) {
			System.out.println("Successful login!");
			
		} else {
			System.out.println("Error");
			
		}
		
		while (true) {
			client.test();
		}
		
		//Hvis feil blir usernameboksen eller passordboksen rød
	}
}
