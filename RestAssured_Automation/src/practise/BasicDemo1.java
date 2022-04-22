package practise;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.Assert;



public class BasicDemo1 {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Post Operation
		
		String Addplace=given().queryParam("key", "qaclick123").body(DemoFile.jsonfile())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Extracted Data : "+Addplace);
		
		JsonPath js=DemoFile.json(Addplace);
	    String PlaceId=js.getString("place_id");
		System.out.println("Fetched place id : "+PlaceId);
		
		//Get operation
	     given().queryParam("key", "qaclick123").queryParam("place_id", PlaceId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		
		//Put Operation
		String Actual="Belgaum";
		given().queryParam("key", "qaclcick123").queryParam("place_id", PlaceId)
		.body("{\r\n" + 
				"\"place_id\":\""+PlaceId+"\",\r\n" + 
				"\"address\":\""+Actual+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200);
		
		
		//Get operation
	     String updated=given().queryParam("key", "qaclick123").queryParam("place_id", PlaceId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	     System.out.println("Extracted responsec from get response : "+updated);
	     
	      js=new JsonPath(updated);
	     String Expected=js.getString("address");
	     System.out.println("Fetched Addresss : "+Expected);
	     
	     Assert.assertEquals(Actual, Expected);
	     
	}

}
