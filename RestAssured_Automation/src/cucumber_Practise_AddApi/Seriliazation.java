package cucumber_Practise_AddApi;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Seriliazation {

	public static void main(String[] args) {
		
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		
		RequestSpecification req=new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").build();
		
		
				
		
		AddPlace a=new AddPlace();

		a.setAccuracy(50);
		a.setName("Frontline house");
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		a.setLocation(l);
		
		List<String> olist=new ArrayList<String>();
		olist.add("shoe park");
		olist.add("shop");
		
		a.setTypes(olist);
		
		ResponseSpecification response=new ResponseSpecBuilder()
				.expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		Response resp=given().spec(req).body(a).post("/maps/api/place/add/json")
		.then().spec(response).extract().response();
		
		String Finalresp=resp.asString();
		System.out.println(Finalresp);
		
//		String result=given().log().all().queryParam("key", "qaclick123").body(a).contentType("application/json")
//		.when().post("/maps/api/place/add/json")
//		.then().log().all().assertThat().statusCode(200).extract().response().asString();
//		System.out.println("Result is : "+result);
		

	}

}
