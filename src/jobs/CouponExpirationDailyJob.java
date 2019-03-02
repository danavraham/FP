package jobs;

import dbdao.CouponsDBDAO;

/**
 * CouponExpirationDailyJob class is a daily job that checks to find all the expired coupons and
 * delete them. This class runs in the background using a thread
 * 
 * @author dan
 *
 */
public class CouponExpirationDailyJob implements Runnable {

	private boolean quit = false;
	private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

	// -------------------------Constructor------------------------
	public CouponExpirationDailyJob() {

	}

	// -------------------------Methods------------------------

	/**
	 * run() Override run method of Runnable and starts the thread that deletes the
	 * expired coupons from the DB
	 * 
	 */
	@Override
	public void run() {
		while (!quit) {
			System.out.println("Daily job is now running!");
			try {
				couponsDBDAO.deleteAllExpiredCoupons();
				System.out.println("---------------All expired coupons was deleted");

			} catch (Exception e) {
				System.out.println(e);
			}

			try {
				System.out.println("---------------Thread sleeps for 24H");
				// define sleeping period of 24H for the thread
				Thread.sleep(1000 * 60 * 60 * 24);

			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * stop() method that stops the thread 
	 * 
	 */
	public void stop() {
		try {
			System.out.println("---------------CouponExpirationDailyJob stoped!");
			quit = true;

			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
