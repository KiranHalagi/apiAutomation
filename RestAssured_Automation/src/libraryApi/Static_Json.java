package libraryApi;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;





public class Static_Json {

	public static void main(String[] args) throws IOException {
      
		RestAssured.baseURI="http://216.10.245.166";
		given().body(file("F:\\webserviceqaclick\\Static_Json.json"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200);
		
		    
		
	}
	
	public static String file(String Path) throws IOException
	{
       return new String(Files.readAllBytes(Paths.get(Path)));		
	}

}
