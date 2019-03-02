---
## public class CouponExpirationDailyJob implements Runnable
---

CouponExpirationDailyJob class is a daily job that checks to find all the expired coupons and delete them. This class runs in the background using a thread

 * **Author:** dan


## `@Override  public void run()`

run() Override run method of Runnable and starts the thread that deletes the expired coupons from the DB

## `public void stop()`

stop() method that stops the thread