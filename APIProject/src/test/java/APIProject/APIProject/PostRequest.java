package APIProject.APIProject;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import  io.restassured.response.Response;
public class PostRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		POJO_PostReq Data=new POJO_PostReq();
		Data.setFirstName("RaghubirTanu");
		Data.setLastName("Singh_1");
		Data.setDesignation("Project_Lead");
		Data.setMentorName("Deepak Channa_1");
		Data.setCourseName("AP Testing");
		Data.setId("369_6");
		
		Response res=
				given()
				.contentType(ContentType.JSON).body(Data)
				.when()
				.post("http://localhost:3000/friends");
		System.out.println("StatusCode for Post Request=:"+res.getStatusCode());
		System.out.println("Data Posted is");
		System.out.println(res.asString());//store data
				
	}

}
