package Library;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class DynamicJsonDataProvider {
	
@Test(dataProvider="Books")
public void AddBook(String isbn,String aisle)
{
	RestAssured.baseURI="http://216.10.245.166";	
	String response = given().log().all().header("Content-Type","application/json")
	.body(payload.AddBookParam(isbn, aisle))
	.when().log().all().post("/Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);//parsing from raw string to Json
	String id = js.getString("ID");
	System.out.println(id);
}

@Test(dataProvider="DeleteBooks")
public void DeleteBook(String id)
{
	RestAssured.baseURI="http://216.10.245.166";	
	String response1 = given().header("Content-Type","application/json")
	.body(payload.DeleteBookParam(id))
	.when().post("/Library/DeleteBook.php")
	.then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
	System.out.println(response1);
	
	JsonPath js1 = new JsonPath(response1);//parsing from raw string to Json
	String msg = js1.getString("msg");
	System.out.println(msg);
	
}


@DataProvider(name="Books")
public String[][] getData()
{
	String[][] a = {{"drfg","2343"},{"awer","1223"},{"cfgh","6578"}};
	return a;
}

@DataProvider(name="DeleteBooks")
public String[][] deleteData()
{
	String[][] b = {{"drfg2343"},{"awer1223"},{"cfgh6578"}};
	return b;
}
}
