package auth2_0;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class AuthDemo {

	public static void main(String[] args) {
		ChromeDriver ch=null;
		try
		{
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
            String response= given().queryParam("access_token", AccessToken)
             .when().get("https://rahulshettyacademy.com/getCourse.php")
             .then().log().all().extract().response().asString();
            System.out.println(response);
             
             
             
             
             
             
		}catch(Exception e)
		{
			System.out.println(e);
		}finally
		{
			ch=null;
		}

	}

}
