package MockWithoutAPI;

import org.testng.Assert;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class CountPriceOfCourses {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload.MockJson());
		int count = js.getInt("courses.size()");
		System.out.println(count+": No of courses returned by API");

		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount+": total amount to be returned by API");
		
		String firstTitleCourse = js.get("courses[0].title");
		System.out.println("Title of the first course is :"+firstTitleCourse+"");

		System.out.println("All course titles and their respective Prices : ");
		for(int i=0;i<count;i++)
		{
			
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(js.getInt("courses["+i+"].price"));
			
		}
		int sum = 0;
		for(int i=0;i<count;i++)
			
		{
			
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int totalAmount = price*copies;
			
			sum = sum + totalAmount;
					
		}
		System.out.println(sum);
		Assert.assertEquals(sum, purchaseAmount);
	}

}
