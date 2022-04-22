package specBuilderPractise;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializationTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		
		AddPlace a=new AddPlace();
		a.setAccuracy("50");
		a.setAddress("Bailhongal");
		a.setLanguage("Kannada");
		a.setName("KIRAN H");
		a.setPhone_number("7353272785");
		a.setWebsite("abc.com");
		
		Location loc=new Location();
		loc.setLat(200.86);
		loc.setLng(96.63);
		
		a.setLocation(loc);

		List<String> oList=new ArrayList<String>();
		oList.add("abc");
		oList.add("xyz");
		
		a.setTypes(oList);
		
		String response=given().queryParam("key", "qaclick123").body(a)
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println("Response is :"+response);
		
		

	}

}
