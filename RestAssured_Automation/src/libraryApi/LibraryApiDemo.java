package libraryApi;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import practise.DemoFile;


import static io.restassured.RestAssured.*;

public class LibraryApiDemo {

	public static String id=null;
	
	@Test(dataProvider="BooksData")
	public static void library(String bookname, String bookid)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String lib=given().body(PayLoadFile.payloadfile(bookname,bookid))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(lib);
		System.out.println("*************************************");
		
		JsonPath js=DemoFile.json(lib);
		 id=js.get("ID");
		System.out.println("Extracted ID value : "+id);
		
		System.out.println("**********************************************");
	
	

		String delete=given().body(PayLoadFile.deletePayload(id))
		.when().delete("/Library/DeleteBook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(delete);
		
	}
	
	
	@DataProvider(name="BooksData")
	public static Object[][] getData()
	{
		 return new Object[][] {{"manual","1022"},{"sql","1023"},{"selenium","1024"}};
	}
	
	

}
