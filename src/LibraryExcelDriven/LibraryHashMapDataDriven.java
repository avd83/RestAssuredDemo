package LibraryExcelDriven;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryHashMapDataDriven {
	JsonPath js;
	String id;
	
	@Test
	void Addbooks() throws IOException
	{	
		//to read data from excel use below class object code
		DataDriven d= new DataDriven();
		ArrayList data = d.getData("RestAddBook","RestAssured");
		
		//Convert Json body Payload into HAshMap 
		HashMap<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", data.get(1));
		jsonAsMap.put("isbn", data.get(2));
		jsonAsMap.put("aisle", data.get(3));
		jsonAsMap.put("author", data.get(4));
		
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
				.body(jsonAsMap)
		.when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200)
		.extract().response().asString();
		System.out.println(response);
		
		 js = new JsonPath(response);//parsing from raw string to Json
		 id = js.getString("ID");
		System.out.println(id);
	}
		
	@Test
	public void ViewBook()
	{
		HashMap<String,Object> jsonAsMap1 = new HashMap<>();
		jsonAsMap1.put("ID",id);
		
		RestAssured.baseURI="http://216.10.245.166"	;	
		String response2 = given().queryParam("ID",id)
		.when().post("/Library/GetBook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response2);
		
		JsonPath js2 = new JsonPath(response2);//parsing from raw string to Json
		String bookName = js2.getString("book_name");
		System.out.println(bookName);		
	}
	
	@Test
	public void DeleteBook()
	{
		HashMap<String,Object> jsonAsMap1 = new HashMap<>();
		jsonAsMap1.put("ID",id);
		
		RestAssured.baseURI="http://216.10.245.166"	;	
		String response1 = given().header("Content-Type","application/json")
		.body(jsonAsMap1)
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response1);
		
		JsonPath js1 = new JsonPath(response1);//parsing from raw string to Json
		String msg = js1.getString("msg");
		System.out.println(msg);		
	}
}
