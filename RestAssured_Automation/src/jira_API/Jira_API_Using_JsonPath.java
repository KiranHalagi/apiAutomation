package jira_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class Jira_API_Using_JsonPath {

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://localhost:8080";
		String response=given().header("Content-Type","application/json").body("{ \"username\": \"admin\", \"password\": \"Kiran@7353\" }")
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String Sname=js.getString("session.name");
		String Svalue=js.getString("session.value");
		String concat=Sname+"="+Svalue;
		System.out.println(concat);
		
		given().pathParam("key", "10009").header("Cookie",concat).header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"body\": \"adding comment for 1008 id\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201);
		
		

	}

}
