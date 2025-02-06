package files;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	/*String[] ids = new String[3];
	int i = 0;*/
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166/";
		String response = given().log().all().header("Content-Type", "application/json").body(Payload.Addbook(isbn, aisle)).when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		//this.ids[i++] = id;
		System.out.println("ID is: " + id);
	}
	
	/*@Test(dataProvider="getBooks")
	
	public void getBook(String id) {
		
		System.out.println("Get ID is: " + id);
		
		RestAssured.baseURI = "http://216.10.245.166/";
		String getResponse = given().log().all().queryParam("ID", id).when().get("Library/GetBook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(getResponse);
		String Book = js.get("Book");
		System.out.println("Book is: " + Book);
		
		
	}*/
	
	
	@DataProvider(name="BooksData")
	
	public Object[][] addData() {
		
		return new Object[][] {{"gdgf7jhjfdfkjdhjf", "1174989556587204"}, {"hjblgdjjesfhfac", "237837579582283443"}, {"heakrgvdwjreh5kfka", "19314j65e8937398852jwd"}};
		
	}
	
	/*@DataProvider(name="getBooks")
	
	public Object[][] getData() {
		
		
		return new Object[][] {{""+this.ids[0]+""}, {""+this.ids[1]+""}, {""+this.ids[2]+""}};
		
	}*/

}
