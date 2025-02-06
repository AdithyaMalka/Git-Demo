import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BugTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://adithyamalka6.atlassian.net/";
		
		/*String response = given().header("Content-Type", "application/json").header("Authorization", "Basic YWRpdGh5YW1hbGthNkBnbWFpbC5jb206QVRBVFQzeEZmR0YwWF8tT1VjWDVndzB0YUZQUTNzeDY3LXlMdy16YXFrbzIyWjFhbVNDM1FhQ2ViQ2g0X3dVdHhJbWxHVTJ5dlFVaXVMTHpsM2lZSFN3TFRkR1l0cjNad1VQWi1rdmtvdnJOQ3ZFbDdvdVJydTBYMVlJM25XdDBfWC1qSm5VM192eTFUY0lkOU91emVIVFZqcDFtTl9hemNoQndzMmhaRzNMUUtBQW9VLVJKcUFNPTkyM0FBQjFG")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM1\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Images are not displayed - Rest Assured\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}").log().all().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String issueid = js.get("id");
		System.out.println("Issue id is: " + issueid);
		
		//Add Attachment
		
		String addattachment = given().header("X-Atlassian-Token", "no-check").header("Authorization", "Basic YWRpdGh5YW1hbGthNkBnbWFpbC5jb206QVRBVFQzeEZmR0YwWF8tT1VjWDVndzB0YUZQUTNzeDY3LXlMdy16YXFrbzIyWjFhbVNDM1FhQ2ViQ2g0X3dVdHhJbWxHVTJ5dlFVaXVMTHpsM2lZSFN3TFRkR1l0cjNad1VQWi1rdmtvdnJOQ3ZFbDdvdVJydTBYMVlJM25XdDBfWC1qSm5VM192eTFUY0lkOU91emVIVFZqcDFtTl9hemNoQndzMmhaRzNMUUtBQW9VLVJKcUFNPTkyM0FBQjFG")
		.multiPart("file", new File("C:\\Users\\nash\\Downloads\\Laptop_PIC6.jpeg")).log().all().post("rest/api/3/issue/"+issueid+"/attachments").then().log().all().assertThat().statusCode(200)
		.extract().response().asString(); */
		
		String JiraDetails = given().header("Content-Type", "application/json").header("Authorization", "Basic YWRpdGh5YW1hbGthNkBnbWFpbC5jb206QVRBVFQzeEZmR0YwWF8tT1VjWDVndzB0YUZQUTNzeDY3LXlMdy16YXFrbzIyWjFhbVNDM1FhQ2ViQ2g0X3dVdHhJbWxHVTJ5dlFVaXVMTHpsM2lZSFN3TFRkR1l0cjNad1VQWi1rdmtvdnJOQ3ZFbDdvdVJydTBYMVlJM25XdDBfWC1qSm5VM192eTFUY0lkOU91emVIVFZqcDFtTl9hemNoQndzMmhaRzNMUUtBQW9VLVJKcUFNPTkyM0FBQjFG")
		.log().all().get("rest/api/3/issue/SCRUM1-8").then().log().all().assertThat().statusCode(200).extract().response().asString();	
		
		System.out.println("Issue details are: " + JiraDetails);
		
	}

}
