package Library;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynmicJsonMethodParam {
	/*
	@Test
	public  void addBook()
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json")
		.body(payload.AddBookParam("kkok", "8989"))
		.when().log().all().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);//parsing from raw string to Json
		String id = js.getString("ID");
		System.out.println(id);
	}
	*/
	@Test
	public void DeleteBook()
	{
		RestAssured.baseURI="http://216.10.245.166";	
		String response1 = given().header("Content-Type","application/json")
		.body(payload.DeleteBookParam("kkok8989"))
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(response1);
		
		JsonPath js1 = new JsonPath(response1);//parsing from raw string to Json
		String msg = js1.getString("msg");
		System.out.println(msg);
		
	}

}
