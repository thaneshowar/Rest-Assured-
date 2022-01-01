package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Delete_A_Product {

	@Test
	public void delete_A_Product() {

		String payloadPath = ".\\src\\main\\java\\data\\deletePayload_2407.json";
		// https://techfios.com/api-prod/api/product/update.php

		Response response = 
			given()
				// .log().all()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Contain-Type", "application/json;")
				.body(new File(payloadPath))
			.when().log().all()
				.delete("/delete.php")
			.then().log().all()
				.extract()
				.response();

		int statusCode = response.getStatusCode();
		System.out.println("Response code: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		long actualresponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println("Response Time : " + actualresponseTime);
		if (actualresponseTime <= 200) {
			System.out.println("Response Time is within rang.");
		} else {
			System.out.println("Response Time is out of  rang.");
		}
		String responseBody = response.getBody().asString();
		System.out.println("Response Bode: " + responseBody);

		JsonPath jp = new JsonPath(responseBody);

		String successMessage = jp.getString("message");
		System.out.println("Product name: " + successMessage);
		Assert.assertEquals(successMessage, "Product was deleted.");

		/*
		 * String productDescription = jp.getString("description");
		 * System.out.println("Product Description: " + productDescription);
		 * Assert.assertEquals(productDescription, "The super phone");
		 * 
		 * String productPrice = jp.getString("price");
		 * System.out.println("Product Price: " + productPrice);
		 * Assert.assertEquals(productPrice, "1300");
		 */

	}

}
