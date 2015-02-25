package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
	
	private static String username = "Check";
	private String password;
	
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
		
		Scanner scanner = new Scanner(System.in);
			
		System.out.println("Enter your username: ");
		String username = scanner.next();
			
		outToServer.write(username + "\n");
		outToServer.flush();
		modifiedSentence = inFromServer.readLine();
		System.out.println("This was recieved from server: " + modifiedSentence);
		
		System.out.println("Enter your password: ");
		String password = scanner.next();
			
		outToServer.write(password + "\n");
		outToServer.flush();
		modifiedSentence = inFromServer.readLine();
		System.out.println("This was recieved from server: " + modifiedSentence);
		
		clientSocket.close();
	}
}
