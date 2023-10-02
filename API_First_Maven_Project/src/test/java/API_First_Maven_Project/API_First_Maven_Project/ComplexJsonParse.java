package API_First_Maven_Project.API_First_Maven_Project;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		/*
		 * 5. Print no of copies sold by RPA Course
		 * 
		 * 6. Verify if Sum of all Course prices matches with Purchase Amount
		 */
		JsonPath js= new JsonPath(Payload.coursePrice());
		// 1. Print No of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		//2.Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		//3. Print Title of the first course
		 String courseTitle = js.getString("courses[0].title");
		System.out.println(courseTitle);
		//4.Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			String CourseTitle = js.getString("courses["+i+"].title");
			System.out.println(CourseTitle);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		//5. Print no of copies sold by RPA Course
		for (int i = 0; i < count; i++) {
			String CourseTitle = js.getString("courses["+i+"].title");
			if (CourseTitle.contains("RPA")) {
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
			
		}
		
	}
}

