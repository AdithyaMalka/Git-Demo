import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlace {
	
	public static void main (String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		// add place
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server",  "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeid = js.getString("place_id");
		System.out.println("place id is: " + placeid);
		 
		//Update place
		
		String newAddress = "BC Colony";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + "\"place_id\":\""+placeid+"\",\r\n" + "\"address\":\""+newAddress+"\",\r\n" + "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// get place
		
		String getPlace = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getPlace);
		
		JsonPath js1 = new JsonPath(getPlace);
		String UpdatedAddress = js1.getString("address");
		System.out.println("Updated Address is: " + UpdatedAddress);
		
		Assert.assertEquals(newAddress, UpdatedAddress);
		
	}

}
