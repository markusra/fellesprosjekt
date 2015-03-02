package user;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class testClass {

	public static void main(String[] args) throws ParseException {
		String streng = "{\"passord\": \"test1234\", \"brukernavn\": \"markusra\"}";
		
		JSONObject json = (JSONObject)new JSONParser().parse(streng);
		System.out.println("brukernavn=" + json.get("brukernavn"));
	}
}
