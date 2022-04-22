package pojo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuth {

	public static void main(String[] args) {
		ChromeDriver ch=null;
		try
		{
			String Expectedtitle[]= {"Selenium Webdriver Java","Cypress","Protractor"};
             System.setProperty("webdriver.chrome.driver","F:\\webserviceqaclick\\Postman_Workspace\\RestAssured_Automation\\Library\\Drivers\\chromedriver.exe");	
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
             Thread.sleep(5000);
             
             String finalurl=ch.getCurrentUrl();
             System.out.println(finalurl);
             Thread.sleep(2000);
             String partialcode=finalurl.split("code=")[1];
             String code=partialcode.split("&scope")[0];
             System.out.println("Actual Code : "+code);
             
             //exchange cod [getting code]
        String accessTokenResponse= given().urlEncodingEnabled(false)
        		    .queryParam("code", code)
                    .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                    .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                    .queryParam("grant_type", "authorization_code")
             .when().post("https://www.googleapis.com/oauth2/v4/token")
             .then().log().all().extract().response().asString();
        
        JsonPath js=new JsonPath(accessTokenResponse);
         String AccessToken=js.get("access_token");
             
             
             
             //end point url [actual request]
            GetCourses gc= given().queryParam("access_token", AccessToken).expect().defaultParser(Parser.JSON)
             .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
             
            String linkedInUrl=gc.getLinkedIn();
            System.out.println("Linked in url is : "+linkedInUrl);
            
          //  System.out.println(response);
            
            //get me the amount of soapUi Webservices testing price
            
            List<Api> ApiPrice=gc.getCourses().getApi();
            for(int i=0;i<ApiPrice.size();i++)
            {
            	
            	
            	if(ApiPrice.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            	{
            		String Price=ApiPrice.get(i).getPrice();
            		System.out.println("Price of the course is : "+Price);
            		
            	}
            	
            }
             
            //print all the course title names of web Automation
            ArrayList<String> a=new ArrayList<String>();
            List<WebAutomation> cou=gc.getCourses().getWebAutomation();
            for(int i=0;i<cou.size();i++)
            {
            	a.add(cou.get(i).getCourseTitle());
            }
             
            List<String> expectedList=Arrays.asList(Expectedtitle);
            
            Assert.assertTrue(a.equals(expectedList));
             
             
             
             
		}catch(Exception e)
		{
			System.out.println(e);
		}finally
		{
			ch=null;
		}


	}

}
