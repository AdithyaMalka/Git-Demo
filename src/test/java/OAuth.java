import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojodeserialisation.GetCourse;
import pojodeserialisation.WebAutomation;

public class OAuth {

	public static void main(String[] args) {
		
		String[] CourseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String postresponse = given().log().all().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials").formParam("scope", "trust").when().post("oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Post Response is: " + postresponse);
		
		JsonPath js = new JsonPath(postresponse);
		String accessToken = js.get("access_token");
		System.out.println("Access Token is: " +  accessToken);
		
		GetCourse getcourseresponse = given().log().all().queryParam("access_token", accessToken).when().log().all().get("oauthapi/getCourseDetails")
				.as(GetCourse.class);
		
		System.out.println("Get Response is: " + getcourseresponse);
		
		System.out.println("Linkedin is: " + getcourseresponse.getLinkedIn());
		System.out.println("INstructor is: " + getcourseresponse.getInstructor());
		
		System.out.println("Course is: " + getcourseresponse.getCourses().getWebAutomation().get(0).getCourseTitle());
		//System.out.println("Courses under WebAutomation are: " + getcourseresponse.getCourses().getWebAutomation());
		
		List<WebAutomation> WebCourses = getcourseresponse.getCourses().getWebAutomation();
		

		if (WebCourses == null) {
		    System.out.println("WebAutomation list is null.");
		} else if (WebCourses.isEmpty()) {
		    System.out.println("WebAutomation list is empty.");
		} else {
		    System.out.println("WebAutomation list has data.");
		}

		
		for (int i = 0; i<WebCourses.size(); i++) {
			
			//System.out.println("WebAutomation Course Titles are: " + WebCourses.get(i).getCourseTitle());
			
			if(WebCourses.get(i).getCourseTitle().equalsIgnoreCase("Cypress")) {
				
				System.out.println("price of cypress is : " + WebCourses.get(i).getPrice());
				
			}
			
		}
		
		ArrayList<String> list = new ArrayList<String>();
		List<WebAutomation> AllWebCourses = getcourseresponse.getCourses().getWebAutomation();
		
		for (int i = 0; i<AllWebCourses.size(); i++) {
			
			list.add(AllWebCourses.get(i).getCourseTitle());
			
		}
		
		//Converting the CourseTitles string array to ArrayList string
		List<String> expectedcourses = Arrays.asList(CourseTitles);
		Assert.assertTrue(list.equals(expectedcourses));
		
		
	}

}
