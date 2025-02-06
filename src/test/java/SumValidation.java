import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	
	public void SumofCourses()
	{
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount is: " + totalPurchaseAmount);
		int coursescount = js.getInt("courses.size()");
		System.out.println("No.of courses are: " + coursescount);
		
		int totalprice = 0;
		
		for(int i = 0; i<coursescount; i++) {
			
					
			int price = js.get("courses["+i+"].price");
			
			
			int copies = js.get("courses["+i+"].copies");
			
			int amount = price * copies;
			System.out.println(amount);
			
			 totalprice = totalprice + amount;
			
			
			
			
		}
		
		System.out.println("Total price of all copies is: " + totalprice);
		
		Assert.assertTrue(totalPurchaseAmount == totalprice);
		
		
	}
}
