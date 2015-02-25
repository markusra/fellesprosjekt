package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class TCPClient {
	
	private static String username = "markusra";
	private static String password = "test1234";
	
	public TCPClient () {
		
	}
	public boolean validLogin(String username, String password) {
		return false;
	}
	
	public static void main(String argv[]) throws Exception { 
		String modifiedSentence;
		
		Socket clientSocket = new Socket("rauhut.no", 9998);
		Writer outToServer = new OutputStreamWriter(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		
		System.out.println("Sends k2pj39as9d0uo34jkh41:('markusra', 'test')");
		String command = "k2pj39as9d0uo34jkh41";
		String raw = command + ":('" + username + "', '" + password + "')";
			
		outToServer.write(raw + "\n");
		outToServer.flush();
		modifiedSentence = inFromServer.readLine();
		System.out.println("This was recieved from server: " + modifiedSentence);
		
		String[] answer = modifiedSentence.split(":");
		
		if (answer[0].contains(command) && answer[1].contains("('True')")) {
			System.out.println("Correct username and password!");
		}
		
		clientSocket.close();
	}
}
