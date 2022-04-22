package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefination extends Utils   {

	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response responce;
	static String Place_Id;
	
	
	TestDataBuild data=new TestDataBuild();
	
	@Given("Add place payload {string},{string},{string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		
	res=given().spec(requestSpecification()).body(data.AddPlacePayLoad(name, language, address));
	  
	}

	@When("when user calls {string} with {string} http request")
	public void when_user_calls_with_http_request(String resource, String method) {
	   
	APIResources resourcesAPI=APIResources.valueOf(resource);
	System.out.println(resourcesAPI.getResource());
	
	
		  resspec=new ResponseSpecBuilder()
					.expectStatusCode(200).expectContentType(ContentType.JSON).build();
		  
		  
		  
		 
		 if(method.equalsIgnoreCase("POST"))
		 {
			 responce=res.when().post(resourcesAPI.getResource());
		 }
		 else if((method.equalsIgnoreCase("GET")))
		 {
			 responce=res.when().get(resourcesAPI.getResource());
		 }
				
				 //.then().spec(resspec).extract().response();
	}

	@Then("the API call is successs with status code {int}")
	public void the_API_call_is_successs_with_status_code(Integer int1) {
	   assertEquals(responce.getStatusCode(),200);
	    
	}

	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String keyValue, String Expectedvalue) {

	    assertEquals(getJsonPath(responce, keyValue),Expectedvalue);
	}
	
	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_ID_created_maps_to_using(String Expectedname, String resource) throws IOException {
	   //GetApi Call
			Place_Id=getJsonPath(responce, "place_id");
			res=given().spec(requestSpecification()).queryParam("place_id", Place_Id);
			when_user_calls_with_http_request(resource, "GET");
			
			String ActualName=getJsonPath(responce, "name");
			Assert.assertEquals(Expectedname,ActualName);
	    
	}
	
	@Given("Add deletePlace Payload")
	public void add_deletePlace_Payload() throws IOException {
		res=given().spec(requestSpecification()).body(data.DeletePlacePayload(Place_Id));
		
	}



}