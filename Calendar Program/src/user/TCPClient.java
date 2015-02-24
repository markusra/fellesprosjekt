package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
		Writer outToServer = new OutputStreamWriter(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.write(username + "\n");
		outToServer.flush();
		modifiedSentence = inFromServer.readLine();
		System.out.println("This was recieved from server: " + modifiedSentence);
		clientSocket.close();
	}
}
