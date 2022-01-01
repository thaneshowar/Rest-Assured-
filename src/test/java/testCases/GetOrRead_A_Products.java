package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GetOrRead_A_Products {

	@Test
	public void read_A_Product() {
//		https://techfios.com/api-prod/api/product/read_one.php?id=1685

		Response response = 
				given()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Contain-Type", "application/json;charset=UTF-8")
				.queryParam("id", "1685")
				.when()
				.get("/read_one.php")
				.then()
				.extract().response();

		int statusCode = response.getStatusCode();
		System.out.println("Response code: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		long actualresponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println("Response Time : " + actualresponseTime);
		if (actualresponseTime <= 2000) {
			System.out.println("Response Time is within rang.");
		} else {
			System.out.println("Response Time is out of  rang.");
		}
		String responseBody = response.getBody().asString();
		System.out.println("Response Bode: " + responseBody);
		JsonPath jp = new JsonPath(responseBody);
		
		String productName = jp.getString("name");
		System.out.println("Product name: " + productName);
		Assert.assertEquals(productName, "iphone-13.0");
		
		String productDescription = jp.getString("description");
		System.out.println("Product Description: " + productDescription);
		Assert.assertEquals(productDescription, "The super phone");
		
		String productPrice = jp.getString("price");
		System.out.println("Product Price: " + productPrice);
		Assert.assertEquals(productPrice, "1300");

	}

}
