package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response; 
import static io.restassured.RestAssured.*;

public class GetOrReadAllProducts {

	@Test
	public void readAllProduct() {
//		https://techfios.com/api-prod/api/product
//			/read.php
		Response response=
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Contain-Type","application/json;charset=UTF-8")
				.when()
					.get("/read.php")
				.then().extract().response();
					
				int statusCode =response.getStatusCode();
				System.out.println("Response code: "+ statusCode);
				Assert.assertEquals(statusCode, 200);
				String responseBody = response.getBody().asString();
				
				System.out.println("Response Bode: "+ responseBody);
	}				

}
