package PojoDeSerialization;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;


public class OAuthTest {

	public static <WebDriver> void main(String[] args) {
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

		       
		String response1 =given().queryParams("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response1);

	}

}
