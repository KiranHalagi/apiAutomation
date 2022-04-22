package pojoPractise;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Authorisation_Code {

	public static void main(String[] args) {
		
		ChromeDriver ch=null;
		
		try
		{
			System.setProperty("webdriver.chrome.driver", "F:\\webserviceqaclick\\Postman_Workspace\\RestAssured_Automation\\Library\\Drivers\\chromedriver.exe");
			ch=new ChromeDriver();
			ch.manage().window().maximize();
			ch.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyjdss");
			Thread.sleep(3000);
			ch.findElement(By.xpath("//input[@type='email']")).sendKeys("kiranhalagi7@gmail.com");
			Thread.sleep(3000);
			ch.findElement(By.xpath("//span[text()='Next']")).click();
			Thread.sleep(5000);
			ch.findElement(By.xpath("//input[@type='password']")).sendKeys("kiran@7353");
			Thread.sleep(3000);
			ch.findElement(By.xpath("//span[text()='Next']")).click();
			Thread.sleep(4000);
			
			String url=ch.getCurrentUrl();
			System.out.println("Current url is : "+url);
      		String Aurl=url.split("code=")[1];
      		System.out.println("Code Strting url : "+Aurl);
      		String Code=Aurl.split("&scope")[0];
      		System.out.println("Actual Code : "+Code);
      		
//      		RestAssured.baseURI="https://www.googleapis.com/oauth2/v4/token";
      	String AccessToken=given().queryParam("code", Code).urlEncodingEnabled(false)
      		       .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
      		       .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
      		       .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
      		       .queryParam("grant_type", "authorization_code").
      		when().post("https://www.googleapis.com/oauth2/v4/token")
      		.then().assertThat().statusCode(200).extract().response().asString();
      	System.out.println("AccessToken response is : "+AccessToken);
      	
      	JsonPath js=new JsonPath(AccessToken);
        String token=	js.get("access_token");
        System.out.println("Access Token is : "+token);
        
       String finalres= given().queryParam("access_token", token)
        .when().post("https://rahulshettyacademy.com/getCourse.php")
        .then().assertThat().statusCode(200).extract().response().asString();
       
       System.out.println("Final response : "+finalres);
        	
      		     
		}catch(Exception e)
		{
			System.out.println(e );
		}
		finally
		{
			
		}
		
		
		

	}

}
