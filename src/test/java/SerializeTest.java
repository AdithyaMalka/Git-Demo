import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojoserialisation.AddPlace;
import pojoserialisation.Location;

public class SerializeTest {

	public static void main(String[] args) {
		
		//Creating object for AddPlace class
		
		AddPlace add = new AddPlace();
		add.setAccuracy(50);
		add.setAddress("29, side layout, cohen 09");
		add.setLanguage("French-IN");
		add.setName("Frontline house");
		add.setPhone_number("(+91) 983 893 3937");
		add.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("myList");
		myList.add("shop");
		
		add.setTypes(myList);
		
		//creating object for location class
		
		Location L = new Location();
		L.setLat(-38.383494);
		L.setLng(33.427362);
		
		add.setLocation(L);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response = given().log().all().queryParam("key", "qaclick123").body(args).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();

	}
	
	//This is Git
	
	//Git clone
	
	//Git something
	
}
