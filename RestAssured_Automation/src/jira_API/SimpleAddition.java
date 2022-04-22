package jira_API;

public class SimpleAddition {

	public static void main(String[] args) {
		//Addition(10, 2);
		splitMethod();

	}
	
	static void Addition(int x,int y)
	{
		int result=x+y;
		System.out.println("Addition result is : "+result);
		
	}
	
	static void splitMethod()
	{
		String cod="https://rahulshettyacademy.com/getCourse.php?state=verifyjdss&code=4%2FzAFAoDTteFDuJVZ5fihPKirYQoSQmsy0AdVv-M7xV3dsXTP-p2pSbBQRh6WsFfkNCpsC7pO_COe6Mb7kr8y6x1E&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
      String out=cod.split("code=")[1];
//      System.out.println("At index 1 : "+out);
     String code=out.split("&scope")[0];
     System.out.println(code);
	}

}
