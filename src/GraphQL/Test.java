package GraphQL;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test {

	public static void main(String[] args) {
		
		//Query GraphQL code
		//RestAssured.baseURI = "https://rahulshettyacademy.com/gq/graphql";
      	 String queryResponse = given().log().all().headers("Content-Type","application/json")
		.body("{\"query\":\"query ($characterId: Int!,$locationId : Int!) {\\n  character(characterId: $characterId) {\\n    id\\n    name\\n    type\\n    status\\n    species\\n    gender\\n  }\\n  location(locationId: $locationId) {\\n    name\\n    type\\n    dimension\\n  }\\n  episode(episodeId: 2415) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Ashok\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":3688,\"locationId\":4167}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().log().all().extract().response().asString();
				
		JsonPath js = new  JsonPath(queryResponse);
		String characerName = js.getString("data.character.name");
		System.out.println(characerName);
		Assert.assertEquals(characerName,"Ashok");
		
		//Mutation GraphQL code
		
		String mutaionResponse = given().log().all().headers("Content-Type","application/json")
		.body("{\"query\":\"mutation ($characterName : String!,$locationName : String!,$episodeName: String!)\\n{ \\n  createCharacter(character:{name:$characterName,type:\\\"macho\\\",status:\\\"Alive\\\",species:\\\"thrill\\\",gender:\\\"Male\\\",image:\\\"png\\\",originId:3434,locationId:2212})\\n  {\\n    id\\n  }  \\n  createLocation(location:{name:$locationName,type:\\\"Westzone\\\",dimension:\\\"456\\\"})\\n  {\\n    id\\n  }\\n  \\n  createEpisode(episode:{name:$episodeName,air_date:\\\"2012 May\\\",episode:\\\"Jio\\\"})\\n  {\\n    id\\n  } \\n  \\n}\",\"variables\":{\"characterName\":\"Mitansh\",\"locationName\":\"USA\",\"episodeName\":\"chothe Miya\"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().log().all().extract().response().asString();
		
		System.out.println(mutaionResponse);
		/*JsonPath js1 = new  JsonPath(mutaionResponse);
		String characerName1 = js1.getString("data.character.name");
		System.out.println(characerName1);
		Assert.assertEquals(characerName1,"Mitansh");*/
	}

}
