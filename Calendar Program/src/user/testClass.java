package user;

import json.JsonArray;
import json.JsonValue;


public class testClass {

	public static void main(String[] args) {
		String streng = "[{\"passord\": \"test1234\", \"brukernavn\": \"markusra\"}]";
		
		
		JsonArray jsonArray = JsonArray.readFrom( streng );
		
		String username = null;
		String password = null;
		
		for( JsonValue value : jsonArray ) {
			username = value.asObject().get( "brukernavn" ).asString();
			password = value.asObject().get( "passord" ).asString();
		}
		
		System.out.println(username + " - " + password);
		
	}
}
