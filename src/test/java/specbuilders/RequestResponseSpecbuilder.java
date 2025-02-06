package specbuilders;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoserialisation.AddPlace;
import pojoserialisation.Location;

public class RequestResponseSpecbuilder {

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
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com/";
		RequestSpecification res = given().spec(req).body(add);
		
		Response response = res.when().post("maps/api/place/add/json")
		.then().spec(resspec).extract().response();
		
		String responseString  = response.asString();
		
		System.out.println("JSON Response is: " + responseString);

	}

}
