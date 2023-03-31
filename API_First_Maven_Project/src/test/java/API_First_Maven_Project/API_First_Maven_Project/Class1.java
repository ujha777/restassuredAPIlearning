package API_First_Maven_Project.API_First_Maven_Project;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import  io.restassured.response.Response;
public class Class1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Response res=
		given()
		.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/friends");
		
		System.out.println("Status Code"+res.getStatusCode());
		System.out.println("Data is");
		System.out.println(res.asString());
				
	}

}
