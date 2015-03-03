package tests;

import java.io.IOException;

import program.ServerCodes;
import user.TCPClient;

public class GetRoomsTest {

	public static void main(String[] args) throws IOException {
		TCPClient client = new TCPClient();
		
		client.customQuery(ServerCodes.GETFILTEREDROOMS, "'None'");
}
