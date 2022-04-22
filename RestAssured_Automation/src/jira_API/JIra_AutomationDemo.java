package jira_API;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JIra_AutomationDemo {

	public static void main(String[] args) {
		
    
		RestAssured.baseURI="http://localhost:8080";
		
		//Login to Jira
		SessionFilter session=new SessionFilter();
		
		String response=	given().header("Content-Type","application/json").body("{ \"username\": \"admin\", \"password\": \"Kiran@7353\" }").
		filters(session).when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
		// Adding comment to an existing issue
		String ExpectedComment="How Are you ?";
	String IdParcing=	given().pathParam("key", "10006").header("Content-Type","application/json").body("{\r\n" + 
				"    \"body\": \""+ExpectedComment+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").filter(session).when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
	
	 JsonPath js=new JsonPath(IdParcing);
	 String ParcedId=js.get("id");
	 System.out.println("Parced id is : "+ParcedId);
	  
		
		//Add an attachment to existing issue
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key","10006")
		.header("Content-Type","multipart/form-data")
		.multiPart("file",new File("F:\\webserviceqaclick\\Postman_Workspace\\RestAssured_Automation\\AttachFile.txt")).when().post("/rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//Get issue
	String issueDetails=	given().filter(session).pathParam("key", "10006").queryParam("fields", "comment").log().all().when().get("/rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	 JsonPath js1=new JsonPath(issueDetails);
	 int commentId= js1.get("fields.comment.comments.size()");
	 System.out.println(commentId);
	 
	 for(int i=0;i<commentId;i++)
	 {
		String ActualId= js1.get("fields.comment.comments["+i+"].id").toString();
				System.out.println("Actual Id : "+ActualId);
				
				if(ParcedId.equalsIgnoreCase(ActualId))
				{
					String message=js1.get("fields.comment.comments["+i+"].body").toString();
					System.out.println("Final Message : "+message);
					Assert.assertEquals(ExpectedComment, message);
				}
				
	 }
	 
	
		

	}

}


//JSESSIONID
//A3A4B69D981461EF524D181A187F6456
	 
	 
