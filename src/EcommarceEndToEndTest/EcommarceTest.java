package EcommarceEndToEndTest;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import org.testng.Assert;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class EcommarceTest {

	public static void main(String[] args) {
		
		String expectedMessage = "Order Placed Successfully";
// login code
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.setContentType(ContentType.JSON).build();
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("avd@gmail.com");
		loginRequest.setUserPassword("Abc@1234");
		
	LoginResponse response = given().relaxedHTTPSValidation().log().all().spec(reqSpec).body(loginRequest)
			.when().post("api/ecom/auth/login")
		    .then().log().all().assertThat().statusCode(200).extract().response().as(LoginResponse.class);

	System.out.println(response.getToken());
	String token = response.getToken();
	System.out.println(response.getUserId());
	String userId = response.getUserId();
	System.out.println(response.getMessage());		
	
//Add Product	
	RequestSpecification addProductBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
			.addHeader("Authorization",token).build();
	
	RequestSpecification addProductRequest = given().log().all().spec(addProductBase).param("productName","Laptop").param("productAddedBy",userId)
	.param("productCategory", "fashion").param("productSubCategory","gadet").param("productPrice", "45768")
	.param("productDescription", "HP").param("productFor", "Men")
	.multiPart("productImage",new File("F:\\15dec22_laptop\\REST Assured API\\laptop.png"));
	
	String addProductResponse = addProductRequest.when().post("api/ecom/product/add-product")
	.then().log().all().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath js = new JsonPath(addProductResponse);
	String productId = js.get("productId");
	System.out.println(productId);	
	
//Create order
	RequestSpecification  createOrderBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
			.setContentType(ContentType.JSON).addHeader("Authorization",token).build();
	
	OrderRequest orderDetail = new OrderRequest();
	orderDetail.setCountry("India");
	orderDetail.setProductOrderedId(productId);
	
	List<OrderRequest> orderDetailsList = new ArrayList<OrderRequest>();
	orderDetailsList.add(orderDetail);
	
	Orders orders = new Orders ();
	orders.setOrders(orderDetailsList);
	
	RequestSpecification createOrderRequest = given().log().all().spec(createOrderBase).body(orders);		
			
	OrderResponse response1 = createOrderRequest.when().post("api/ecom/order/create-order")
				    .then().log().all().assertThat().statusCode(201).extract().response().as(OrderResponse.class);
	
	
	List<String> orderno = response1.getOrders();
	System.out.println(orderno);
	List<String> orderProductId = response1.getProductOrderId();
	System.out.println(orderProductId);
	String message = response1.getMessage();
	Assert.assertEquals(expectedMessage, message);
	
//Delete Product	
	RequestSpecification deleteProductBase= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
	.addHeader("Authorization", token).build();
	
	RequestSpecification deleteProductRequest= given().log().all().spec(deleteProductBase).pathParam("productId",productId);
	
	String deleteProductResponse = deleteProductRequest.when().delete("/api/ecom/product/delete-product/{productId}")
	.then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1 = new JsonPath(deleteProductResponse);
	String message1 = js1.get("message");
	Assert.assertEquals("Product Deleted Successfully", message1);

//Delete Order
	RequestSpecification deleteOrderBase= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
			.addHeader("Authorization", token).build();
			
			RequestSpecification deleteOrderRequest= given().log().all().spec(deleteOrderBase).pathParam("orders",orderno);
			
			String deleteOrderResponse = deleteOrderRequest.when().delete("api/ecom/order/delete-order/{orders}")
			.then().log().all().statusCode(200).extract().response().asString();
			
			JsonPath js2 = new JsonPath(deleteOrderResponse);
			String message2 = js1.get("message");
			Assert.assertEquals("Orders Deleted Successfully", message2);
	
	}

}
