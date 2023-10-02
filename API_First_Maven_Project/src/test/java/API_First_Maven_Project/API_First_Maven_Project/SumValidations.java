package API_First_Maven_Project.API_First_Maven_Project;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidations {
	@Test
	public void sumOfCourse() {
		int sum=0;
		JsonPath js= new JsonPath(Payload.coursePrice());
		int count=js.getInt("courses.size()");
		for (int i = 0; i < count; i++) {
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int totalprice = price*copies;
			System.out.println(totalprice);
			sum=sum+totalprice;
			System.out.println(sum);
			
		}
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}
}
