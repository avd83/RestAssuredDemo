package PojoSerialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderAddPlace {
	

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
		
		/*
			//Actual Code without Spec Builder  : 
	RestAssured.baseURI = "https://rahulshettyacademy.com/";
	
	String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(sl)
			.when().log().all().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
	*/
		
	//Spec builder for response and request as well.
		
	RequestSpecification reqSpec =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
			.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	
	ResponseSpecification resSpec= new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
	RequestSpecification res = given().spec(reqSpec).body(sl);
	
	String response1 = res.when().log().all().post("maps/api/place/add/json")
		.then().spec(resSpec).extract().response().asString();	
	
	System.out.println(response1);
			
	}
}
