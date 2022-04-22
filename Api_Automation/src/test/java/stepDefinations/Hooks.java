package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//write a code that will give you a placeId
		//execute this code only when placeId is null
		StepDefination step=new StepDefination();
		if(StepDefination.Place_Id==null)
		{
			step.add_place_payload("tv", "kannada", "LG");
			step.when_user_calls_with_http_request("AddPlaceApi", "POST");
			step.verify_place_ID_created_maps_to_using("tv", "getPlaceApi");	
		}
		
		
	}
}
