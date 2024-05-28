package Library;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class DynamicJson {
	@Test
	void Addbooks()
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type","application/json").body("{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\"wewe\",\r\n"
				+ "\"aisle\":\"3486\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200)
		.extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);//parsing from raw string to Json
		String id = js.getString("ID");
		System.out.println(id);
	}
	
	
	@Test
	public void DeleteBook()
	{
		RestAssured.baseURI="http://216.10.245.166"	;	
		String response = given().header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"ID\" : \"wewe3486\"\r\n"
				+ "} \r\n"
				+ "")
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);//parsing from raw string to Json
		String msg = js.getString("msg");
		System.out.println(msg);
		
	}
}
