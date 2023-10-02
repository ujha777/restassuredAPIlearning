package API_First_Maven_Project.API_First_Maven_Project;

import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.message.ReusableMessage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReUsuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	String id="";
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
		.body(Payload.Addbook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = ReUsuableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);

	}
	//delete book
	@Test(dataProvider="BooksData")
	public void deleteBook(String isbn,String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		Response res=given().header("Content-Type", "application/json")
		.body(Payload.Addbook(isbn,aisle)).delete("Library/Deletebook.php");
		 System.out.println("The response code is - " +res.getStatusCode()+" for "+Payload.Addbook(isbn,aisle));
	     Assert.assertEquals(res.getStatusCode(),404);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][]  getData(){
	//array=collection of elements
	//multidimensional array= collection of arrays
		return new Object[][] {{"ojfwty","9363"},{"cwetee","4253"},{"okmfet","533"}};
	}
	

}
