package API_First_Maven_Project.API_First_Maven_Project;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.Payload;
import files.ReUsuableMethods;
public class Basic {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			//given-all input details
			//when-submit the API
			//then
		//Add place ->Update place with new Address ->Get place to validate if new address is present in response
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("E:\\RESTAssuredAPI\\API_First_Maven_Project\\payload_addplace.json"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);//parsing json
		String PlaceID=js.get("place_id");
		System.out.println(PlaceID);
		//update place
		String  newAddress="Summer Walk Africa";
		given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
		//get method to fetch the place
		//no header is required
		String getPlaceResponse = given().queryParam("key", "qaclick123")
		.queryParam("place_id", PlaceID)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		//JsonPath js1=ReUsuableMethods.rawToJson(getPlaceResponse);
		JsonPath js1=ReUsuableMethods.rawToJson(getPlaceResponse);
		String actualAddress=js1.get("address");
		Assert.assertEquals(actualAddress, newAddress);
		System.out.println(actualAddress);		
	}

}
