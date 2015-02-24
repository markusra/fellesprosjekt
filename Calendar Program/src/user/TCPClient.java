package user;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
	
	private static String username = "Check";
	private String password;
	
	public TCPClient (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public static void main(String argv[]) throws Exception { 
		String modifiedSentence;
		Socket clientSocket = new Socket("rauhut.no", 9998);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes(username + "\n");
		modifiedSentence = inFromServer.readLine();
		System.out.println("This was recieved from server: " + modifiedSentence);
		clientSocket.close();
	}
}
