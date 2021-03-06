package restAssured_Automation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
public class BasicDemo {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
		           .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		           .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("Response is : "+response);

		//Add place-->update place with new address-->get place to validate if new address is present in response
		
		JsonPath js=new JsonPath(response); //for parsing of json
		String placeID=js.getString("place_id");
		
		System.out.println("Place id is : "+placeID);
		
		//put operation [update place]
		String newaddress="Visvesvaraya";
	String updatedAddress=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body("{\r\n" + 
					"\"place_id\":\""+placeID+"\",\r\n" + 
					"\"address\":\""+newaddress+"\",\r\n" + 
					"\"key\":\"qaclick123\"\r\n" + 
					"}\r\n" + 
					"")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
	
	//get api
	String getPlaceResopnse=given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js1=new JsonPath(getPlaceResopnse);
	String updated=js1.getString("address");
	System.out.println("Final result is : "+updated);
	
	Assert.assertEquals(newaddress, "pacific");
	
	}

}
