package PojoSerialization;

import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.ArrayList;

import io.restassured.RestAssured;

public class AddPlace {
	public static void main(String[] args) {
		
		SetLocation sl = new SetLocation();
		sl.setAccuracy(60);
		sl.setAddress("Shyama estate,newar spine road,pune19");
		sl.setLanguage("marathi");
		sl.setPhone_number("(+91) 7876543456");
		sl.setWebsite("https://rahulshettyacademy.com");
		sl.setName("abyas house");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");		
		myList.add("shop");		
		sl.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		sl.setLocation(l);
		
			
	RestAssured.baseURI = "https://rahulshettyacademy.com/";
	
	String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(sl)
			.when().log().all().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	System.out.println(response);
			
	}
}
