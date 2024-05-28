package Files;

public class payload {

	public static String  AddPlace()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383495,\r\n"
				+ "    \"lng\": 33.427363\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Shyama house\",\r\n"
				+ "  \"phone_number\": \"(+91) 973 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 10\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}" ;
	}
	
	public static String MockJson()
	{
		return "{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "},\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}\r\n"
				+ "" ;
	}
	
	public static String AddBook()
	{
		String payload =  "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\"dddd\",\r\n"
				+ "\"aisle\":\"5555\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
	return payload;
	}
	
	public static String Deletebook()
	{
		
		String request = "{\r\n"
				+ " \"ID\" : \"dddd5555\"\r\n"
				+ "}";
		return request; 
	}
	
	public static String DeleteBookParam(String id)
	{
		
		String request = "{\r\n"
				+ " \"ID\" : \""+id+"\"\r\n"
				+ "}";
		return request; 
	}

	
	
	public static String AddBookParam(String isbn,String aisle)
	{
		String payload1 = "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
	return payload1;
	}
	
}
