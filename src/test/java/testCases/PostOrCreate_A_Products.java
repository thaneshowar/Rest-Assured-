package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PostOrCreate_A_Products {

	@Test
	public void create_A_Product() {
//		https://techfios.com/api-prod/api/product
//		/create.php
		/*
		 * {
		 *  "name" :"iphone-13.0",
		 *   "price" : "199",
		 *    "description" : "The super phone",
		 *  "category_id" : 2 }
		 */
		//String payloadPath =".\\src\\main\\java\\data\\payload.json";
		String payloadPath ="./src/main/java/data/payload.json";
		
		/*
		 * HashMap <String, String> payload = new HashMap<String, String>();
		 * payload.put("name", "Fundamantals for QA people"); 
		 * payload.put("price", "149"); 
		 * payload.put("description", "you have to buy this book!!");
		 * payload.put("category_id", "6");
		 */
		
		Response response = 
				given()
			//		.log().all()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Contain-Type", "application/json;")
					.body(new File (payloadPath))
				.when().log().all()
					.post("/create.php")
				.then().log().all()
					.extract().response();

		
		  int statusCode = response.getStatusCode();
		  System.out.println("Response code: " + statusCode);
		  Assert.assertEquals(statusCode, 201);
		  
		  long actualresponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		  
		 System.out.println("Response Time : " + actualresponseTime);
		 
		 if (actualresponseTime <= 200) {
		  System.out.println("Response Time is within rang."); 
		  } else {
		  System.out.println("Response Time is out of  rang.");
		  } String responseBody =
		  response.getBody().asString();
		  System.out.println("Response Bode: " + responseBody);
		  
		 /* JsonPath jp = new JsonPath(responseBody);
		 * 
		 * String successMessage = jp.getString("message");
		 * System.out.println("Product name: " + successMessage);
		 * Assert.assertEquals(successMessage, "Product was created.");
		 */
		/*String productDescription = jp.getString("description");
		System.out.println("Product Description: " + productDescription);
		Assert.assertEquals(productDescription, "The super phone");
		
		String productPrice = jp.getString("price");
		System.out.println("Product Price: " + productPrice);
		Assert.assertEquals(productPrice, "1300");*/

	}

}
