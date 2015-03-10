package group;

import java.io.IOException;

import json.JsonArray;
import client.ServerCodes;
import client.TCPClient;

public class GroupTest {
	
	public static void main(String[] args) throws IOException {
		TCPClient client = new TCPClient();
		
		String serverReply = client.customQuery(ServerCodes.GetSpecificGroup, "'Test'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );

		int groupID = jsonArray.get(0).asObject().get( "gruppeID" ).asInt();
		
		System.out.println("GruppeID --> " + groupID);
	}
	

}
