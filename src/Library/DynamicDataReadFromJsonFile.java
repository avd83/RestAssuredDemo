package Library;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicDataReadFromJsonFile {
	
	public static void main(String[] args) throws IOException
	{
	
		RestAssured.baseURI="http://216.10.245.166";		
		String response = given().log().all().header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("F:\\15dec22_laptop\\REST Assured API\\abh.json"))))
		.when().log().all().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);//parsing from raw string to Json
		String id = js.getString("ID");
		System.out.println(id);
	
		
			RestAssured.baseURI="http://216.10.245.166";	
			String response1 = given().header("Content-Type","application/json")
			.body(payload.DeleteBookParam(id))
			.when().post("/Library/DeleteBook.php")
			.then().log().all().assertThat().statusCode(200)
			.extract().response().asString();
			System.out.println(id);
			System.out.println(response1);
			
			JsonPath js1 = new JsonPath(response1);//parsing from raw string to Json
			String msg = js1.getString("msg");
			System.out.println(msg);
			
		
	}	
}
