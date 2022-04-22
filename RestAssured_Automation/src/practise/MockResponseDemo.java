package practise;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class MockResponseDemo {
	
	public static void main(String args[])
	{
		JsonPath js=new JsonPath(MockResponseFile.Payload());
		
		//Print No of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println("Available Courses : "+count);
		System.out.println("***************************************");
		
		//Print Purchase Amount
		int amount= js.getInt("dashboard.purchaseAmount");
		System.out.println("Prchase Amount : "+amount);
		System.out.println("********************************************");
		
		//Print Title of the first course
		String title=js.get("courses[0].title");
		System.out.println("First Course Title : "+title);
		System.out.println("******************************************");
		
		
		//Print All course titles and their respective Prices
		
		for(int i=0; i<count; i++)
		{
			String alltitle=js.get("courses["+i+"].title");
			System.out.println("All course title : "+alltitle);
			
			int price=js.getInt("courses["+i+"].price");
			System.out.println("All prices : "+price);
			
		}
		System.out.println("*****************************************");
		
		//Print no of copies sold by RPA Course
		for(int i=0;i<count;i++)
		{
			String alltitle=js.get("courses["+i+"].title");	
			if(alltitle.equalsIgnoreCase("RPA"))
			{
			int rpacopies=js.getInt("courses["+i+"].copies");
			System.out.println("No of RPA Copies : "+rpacopies);
		    }	
		}
		System.out.println("*******************************************");
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int ActualAmount=0;
		for(int i=0;i<count;i++)
		{
			int prices=js.getInt("courses["+i+"].price");
			//System.out.println(prices);
			int copies=js.getInt("courses["+i+"].copies");
			//System.out.println(copies);
			int result=prices*copies;
			ActualAmount=ActualAmount+result;	
		}
		System.out.println("Actual Amount : "+ActualAmount);
		
		
		int expectedAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Expected Amount : "+expectedAmount);
		
		Assert.assertEquals(ActualAmount, expectedAmount);
		
		
	}
		
}




