package tests;

import java.io.IOException;

import user.TCPClient;

public class GetAllUsersTest {

	public static void main(String[] args) throws IOException {
		TCPClient client = new TCPClient();
		
		client.customQuery("u4sl29fjanz680slla0p", "'None'");
		
		//Hvis feil blir usernameboksen eller passordboksen rød
	}
}
