package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	
	private String serverReply;
	private Writer outToServer;
	private BufferedReader inFromServer;
	private Socket clientSocket;
	private String splitChar = "#";
	
	
	public TCPClient() throws UnknownHostException, IOException {
		clientSocket = new Socket("rauhut.no", 9998);
		outToServer = new OutputStreamWriter(clientSocket.getOutputStream(), "UTF8");
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	
	public String customQuery(String cmd, String data) throws IOException {
		String raw = cmd + splitChar + "(" + data + ")";
		
		System.out.println(raw);
		outToServer.write(raw + "\n");
		outToServer.flush();
		serverReply = inFromServer.readLine();
		
		return serverReply;
	}
	
	public void disconnect() throws IOException {
		clientSocket.close();
	}
	
	public static void main(String argv[]) throws Exception { 
		System.out.println("Test");
	}
}
