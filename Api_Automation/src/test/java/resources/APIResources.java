package resources;

public enum APIResources {

	AddPlaceApi("/maps/api/place/add/json"),
	deletePlaceApi("/maps/api/place/delete/json"),
	getPlaceApi("/maps/api/place/get/json");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
