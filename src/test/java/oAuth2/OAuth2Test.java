package oAuth2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static io.restassured.RestAssured.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;

public class OAuth2Test {

	public static void main(String[] args) throws InterruptedException {
		
		
//		/*String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0ASVgi3I1d6ucBTrdBQaYdQyo-TrcrKNJxHkUYi3LSGyftzARaQmfosaqXXTkExdIQudVpw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=2&prompt=consent";
//		String partialcode = url.split("code=")[1];
		
		//String code = partialcode.split("&scope")[0];*/
		String code = "4%2F0ASVgi3J7LnfwUbOQFXJb5lACM4TPBcb_A4gb7kKdaig5sr9-VX0tndu7YPprNeVXKX9dvQ";
		System.out.println("code is : " + code);
		
		
		/*String Accesstokenresponse = given().urlEncodingEnabled(false)
				.queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_url", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code").when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
	
		JsonPath js = new JsonPath(Accesstokenresponse);
		String Access_Token = js.get("access_token");
		System.out.println("Access Token is: " + Access_Token);
		
		String response = given().queryParam("access_token", Access_Token).when().get("https://rahulshettyacademy.com/getCourde.php").asString();
		System.out.println("Response is: " + response); */
		
		String accessTokenResponse = given()
				.urlEncodingEnabled(false)
				.queryParams("code", code)
			.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
			.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
			.queryParams("grant_type","authorization_code")
			.when().log().all()
			.post("https://www.googleapis.com/oauth2/v4/token")//.then().extract().response();
			.asString();
		
		System.out.println(accessTokenResponse);
		JsonPath jp = new JsonPath(accessTokenResponse);
		String access_token = jp.getString("access_token");
		
		System.out.println("Access Token is: " + access_token);
			
			
			String response = given().queryParam("access_token", access_token).
			when().get("https://rahulshettyacademy.com/getCourse.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
			
			System.out.println("Response is : " + response);
			
			
			
		

	}

}
