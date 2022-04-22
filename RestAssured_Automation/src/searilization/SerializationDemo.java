package searilization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
public class SerializationDemo {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlace a=new AddPlace();
		a.setAccuracy(50);
		a.setName("Frontline house");
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress("Dharwad");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		a.setLocation(l);
		
		List<String> oList=new ArrayList<String>();
		oList.add("shoe park");
		oList.add("shop");
		
		a.setTypes(oList);
		
		String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(a)
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response().asString();
				
				System.out.println(response);
		
	

	}

}
