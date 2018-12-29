import java.util.ArrayList;

import exeptions.ValueLengthIncorrectException;
import javaBeans.Company;
import javaBeans.Coupon;

public class App {

	public static void main(String[] args) throws ValueLengthIncorrectException {
		
		ArrayList<Coupon>couponList = new ArrayList<Coupon>();
		
		Company c1 = new Company("m", "dan@gkkl", "fdsd", couponList);
		
		System.out.println(c1);

	}

}
