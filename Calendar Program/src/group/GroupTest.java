package group;

import java.io.IOException;

import json.JsonArray;
import client.ServerCodes;
import client.TCPClient;

public class GroupTest {
	
	public static void main(String[] args) throws IOException {
		String member = "Vebjorn Berg (vebjorn)";
		String[] memberArray = member.split("\\(");
		member = memberArray[1].substring(0, memberArray[1].length()-1);
		System.out.println("Member: " + member);
	}
	

}
