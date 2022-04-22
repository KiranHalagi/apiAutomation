package specBuilder;


import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Request_Response_Spec_Builder {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	
		RequestSpecification reqspec=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
		
		
		ResponseSpecification responsespec=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		AddPlace a=new AddPlace();
		a.setAccuracy(50);
		a.setAddress("Vtu Belagavi");
		a.setLanguage("Kannada");
		a.setName("Kiran");
		a.setPhone_number("7353272785");
		a.setWebsite("www.google.com");

		Location l=new Location();
		l.setLat(33.35);
		l.setLng(85.85);

		a.setLocation(l);

		List<String> oList=new ArrayList<String>();
		oList.add("shoe park");
		oList.add("French-In");

		a.setTypes(oList);
			
//		 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
//				 .setContentType(ContentType.JSON).build();
//				  
//				  
//		 ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//				
//				 RequestSpecification res=given().spec(req)
//				 .body(a);
//
//				 Response response =res.when().post("/maps/api/place/add/json").
//				 then().spec(resspec).extract().response();
//
//				 String responseString=response.asString();
//				 System.out.println(responseString);
		
		
		 
		 
		RequestSpecification FinalAns=given().spec(reqspec).body(a);
		 
		 
		 String rsp=FinalAns.when().post("/maps/api/place/add/json")
		 .then().spec(responsespec).extract().response().asString();
		 
//		 String Responsestring=rsp.asString();
		 
		 System.out.println(rsp);
		 

	}

}
