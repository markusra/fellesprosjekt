package tests;

import java.io.IOException;

import program.ServerCodes;
import user.TCPClient;

public class GetRoomsTest {

	public static void main(String[] args) throws IOException {
		TCPClient client = new TCPClient();
		
		client.customQuery(ServerCodes.GetFilteredRooms, "'25.01.2015 11:59', '25.01.2015 12:00', 16");
	}
}
