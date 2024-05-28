package GoogleMapsAPI;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.payload;

public class GoogleMapsAPI {
	public static void main(String[] args) {
/*Given: All input details like Base URL,Query Parameter,Header and Payload body
  When : Submit the API details like Resource and Request method
  Then : Validate the response like assertion, data validation, status code*/
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
//Add API request for google maps
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
			.body(payload.AddPlace())
		.when().log().all().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println(response);

		JsonPath js = new JsonPath(response);//parsing from raw string to Json
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
//Update API request for google maps
		String newAddress = "70 Summer walk, USA";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
        .body("{\r\n"
        		+ "\"place_id\":\""+placeId+"\",\r\n"
        		+ "\"address\":\""+newAddress+"\",\r\n"
        		+ "\"key\":\"qaclick123\"\r\n"
        		+ "}")
        .when().log().all().put("maps/api/place/update/json")
        .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
//Get API request for google maps
        String getPlaceresponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
        .when().log().all().get("maps/api/place/get/json")
        .then().log().all().assertThat().statusCode(200).extract().response().asString();
				
        JsonPath js1 = new JsonPath(getPlaceresponse);//parsing from raw string to Json
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress); 
        
        Assert.assertEquals(actualAddress,newAddress);
        
//Delete API request for google maps
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
        .body("{\r\n"
        		+ "\"place_id\":\""+placeId+"\"\r\n"
        		+ "}")
        .when().log().all().delete("maps/api/place/delete/json")
        .then().log().all().assertThat().statusCode(200).body("status",equalTo("OK"));     

	}

}
