package dataBase;

import java.util.ArrayList;
import javaBeans.Coupon;



public interface ICouponsDAO {


    void addCoupon(Coupon addCoupon) throws Exception;
    void updateCoupon(Coupon updateCoupon) throws Exception;   
    void deleteCoupon(int deleteCouponByID) throws Exception;   
	ArrayList<Coupon> getAllCoupons() throws Exception;
	Coupon getOneCoupon(int getCouponByID) throws Exception;
	void addCouponPurchase(int customerID, int couponId);
	void deleteCouponPurchase(int customerID, int couponId);
}
