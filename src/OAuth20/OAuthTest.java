package OAuth20;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import PojoDeSerialization.Api;
import PojoDeSerialization.GetCourse;
import PojoDeSerialization.Mobile;
import PojoDeSerialization.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static <WebDriver> void main(String[] args) {
		
		String[]coursetitles ={"Appitum"};
		/*
		System.setProperty("webdriver.chrome.driver","F:\\15dec22_laptop\\Selenium\\Setup\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?Scope=https://www.googleapis.com/auth/userinfo.email&Auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_url=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		
		driver.findelement(By.cssSelector("input[type='email']")).sendkeys("abhaydere");
		driver.findelement(By.cssSelector("input[type='password']")).sendkeys("abc123");
		Thread.sleep(3000);
		*/
		
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
					
		//if SSL certificate need to skip then use this method "urlEncodingEnabled(false)"
		String accessTokenResponse =given().urlEncodingEnabled(false)
		                       .queryParams("code",code)             
		                       .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	                           .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	                           .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		                       .queryParams("grant_type", "authorization_code")                   
		                  .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
             System.out.println(accessTokenResponse);
             
		JsonPath jsonPath = new JsonPath(accessTokenResponse);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);

		   //convert below code using POJO classesGetCourse and if Get method content-type is text the use="expect().defaultParser(Parser.JSON)"   
		GetCourse gc =given().queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		gc.getExperties();
		gc.getInstructor();
		System.out.println(gc);
		
		gc.getCourses().getWebAutomation().get(1).getCourseTitle();
		
		List<WebAutomation> getWebCourses = gc.getCourses().getWebAutomation();
		
		for (int i=0;i<getWebCourses.size();i++)
		{
			if(getWebCourses.get(i).getCourseTitle().equalsIgnoreCase("Cypress"))
			 	{
				System.out.println(getWebCourses.get(i).getCourseTitle());
				System.out.println(getWebCourses.get(i).getPrice());
			 	}
		}
		
		//below code for to get print the API all courses title and price
		List<Api> getApiCourses = gc.getCourses().getApi();
		
		for (int i=0;i<getApiCourses.size();i++)
		{
				System.out.println(getApiCourses.get(i).getCourseTitle());
				System.out.println(getApiCourses.get(i).getPrice());
		}
		
		//Below code is for Assertion between expected and Actual result
		ArrayList<String> a = new ArrayList<String>();
		
		List<Mobile> w = gc.getCourses().getMobile();
		
		for (int i=0;i<w.size();i++)
		{
			a.add(w.get(i).getCourseTitle());
		}
		
		List<String> expectedList = Arrays.asList(coursetitles);
		Assert.assertTrue(a.equals(expectedList));
		
	}
}
