package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;


public class TCPClient {
	
	private String serverReply;
	private Writer outToServer;
	private BufferedReader inFromServer;
	private Socket clientSocket;
	
	
	public TCPClient () throws UnknownHostException, IOException {
		clientSocket = new Socket("rauhut.no", 9998);
		outToServer = new OutputStreamWriter(clientSocket.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public boolean validLogin(String username, String password) throws IOException, ParseException {
		String command = "k2pj39as9d0uo34jkh41";
		String raw = command + ":('" + username + "', '" + password + "')";
		
		outToServer.write(raw + "\n");
		outToServer.flush();
		
		serverReply = inFromServer.readLine();
		System.out.println("This was recieved from server: " + serverReply);
		
		String[] answer = serverReply.split(":");
		
		JSONArray mJsonArray = new JSONArray(answer[1]);
		JSONObject mJsonObject = new JSONObject();
		for (int i = 0; i < mJsonArray.length(); i++) {
		    mJsonObject = mJsonArray.getJSONObject(i);
		    mJsonObject.getString("0");
		    mJsonObject.getString("id");
		    mJsonObject.getString("1");
		    mJsonObject.getString("name");
		}
		
		if (answer[0].contains(command) && answer[1].contains("")) {
			System.out.println("Correct username and password!");
			return true;
		} else {
			System.out.println("Wrong username or password!");
			return false;
		}
		
	}
	
	public void disconnect() throws IOException {
		clientSocket.close();
	}
	
	public static void main(String argv[]) throws Exception { 
		System.out.println("Test");
	}
}
