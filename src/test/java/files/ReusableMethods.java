package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath rawTojson(String response) {
	
		JsonPath js1 = new JsonPath(response);
		return js1;
	}

}
