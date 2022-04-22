package libraryApi;

public class PayLoadFile {

	public static String payloadfile(String bookname,String bookid)
	{
		String file= "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+bookname+"\",\r\n" + 
				"\"aisle\":\""+bookid+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}";
		
		return file;
	}
	
	public static String deletePayload(String id)
	{
		
	  return  "{\r\n" + 
			"\r\n" + 
			"\"ID\" : \""+id+"\"\r\n" + 
			"\r\n" + 
			"}\r\n" + 
			"";	
	}
}

