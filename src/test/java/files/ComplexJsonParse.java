package files;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main (String[] args) {
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		//Print no.of courses returned by API
		
		int coursescount = js.getInt("courses.size()");
		System.out.println("No.of courses are: " + coursescount);
		
		//print purchase amount
		
		int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount is: " + totalPurchaseAmount);
		
		//Print title of the first course
		
		String titlefirstcourse = js.getString("courses[0].title");
		System.out.println("Title of first course is: " + titlefirstcourse);
		
		
		//Print all course titles and prices
		int totalprice= 0;
		for(int i = 0; i<coursescount; i++) {
			
			String allcourses = js.getString("courses["+i+"].title");
			System.out.println("All courses are: " + allcourses);
			
			int allprices = js.get("courses["+i+"].price");
			System.out.println("All prices are: " + allprices);
			
			//Print no.of copies sold for Selenium Python course
			
			/*if(allcourses.equalsIgnoreCase("Selenium Python")) {
				
				String totalcopies = js.getString("courses["+i+"].copies");
				System.out.println("Total Selenium Python sold are: " + totalcopies);
				
			}*/
			
			int totalcopies = js.get("courses["+i+"].copies");
			System.out.println("Total copies sold are: " + totalcopies);
			
			int price = allprices * totalcopies;
			System.out.println(price);
			
			 totalprice = totalprice + price;
			
			
			
			
		}
		
		System.out.println("Total price of all copies is: " + totalprice);
		
		Assert.assertTrue(totalPurchaseAmount == totalprice);
		
		
		
		
		for(int i=0; i<coursescount; i++) {
			
			String courses = js.getString("courses["+i+"].title");
			if(courses.equalsIgnoreCase("Selenium Python")) {
				
				String totalcopies = js.getString("courses["+i+"].copies");
				System.out.println("Total Selenium Python sold are: " + totalcopies);
				break;
				
			}
		
		}
		
		
		
		
	}

}
