package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public  AddPlace AddPlacePayLoad(String name,String language ,String address )
	{
		AddPlace a=new AddPlace();

		a.setAccuracy(50);
		a.setName(name);
		a.setPhone_number("9449870820");
		a.setAddress(address);
		a.setWebsite("http://google.com");
		a.setLanguage(language);
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		a.setLocation(l);
		
		List<String> olist=new ArrayList<String>();
		olist.add("shoe park");
		olist.add("shop");
		
		a.setTypes(olist);
		
		return a;
	}
	
	public String DeletePlacePayload(String Place_Id)
	{
		return "{\r\n" + 
				"		    \"place_id\":\""+Place_Id+"\"\r\n" + 
				"		}";

	}
}
