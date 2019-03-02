# DBDAO Package

All the classes that implements the DAO package, with all the methods that will be used to create the changes in the DB through the connectionPool.
* CompaniesDBDAO
* CustomersDBDAO
* CouponssDBDAO
* ConnectionPool

---
## public class CompaniesDBDAO implements ICompaniesDAO
---



CompaniesDBDAO the class that implements the CompaniesDAO, with all the methods the Company class will use. this class create the changes in the DB through the connectionPool

 * **Author:** dan



## `private static ConnectionPool connectionPool = ConnectionPool.getInstance()`

an instance of the ConnectionPool

## `public boolean isCompanyExists(String email, String password) throws Exception`

isCompanyExists() a method that gets the company email and password and checks if the company exist in the DB

 * **Parameters:**
   * `email` — getting the company email
   * `Password` — getting the company password
 * **Returns:** True if company exist in the DB, False- if the company dose not exist
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCompanyNameExists(String companyName) throws Exception`

isCompanyNameExists() a method that checks if company name is already in the DB

 * **Parameters:** `companyName` — company name to check
 * **Returns:** True if company name already in the DB, False if dosen't
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCompanyEmailExists(String email) throws Exception`

isCompanyEmailExists() a method that checks if company email is already in the DB

 * **Parameters:** `email` — company email to check
 * **Returns:** True if company email already in the DB, False if dosen't
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCompanyExistsById(int companyId) throws Exception`

isCompanyExistsById() a method that gets the company ID and checks if the company ID exist in the DB

 * **Parameters:** `companyId` — getting the company ID
 * **Returns:** True if company ID exist in the DB, False- if the company ID dose not

     exist
 * **Exceptions:** `Exception` — can throws Exception

## `public void addCompany(Company addCompany) throws Exception`

addCompany() a method that adds a company to the companies table in the DB

 * **Parameters:** `addCompany` — a Company object to add to the DB
 * **Exceptions:** `Exception` — can throws Exception

## `public void updateCompany(Company updateCompany) throws Exception`

updateCompany() a method that updates company info in the companies table in the DB by its ID

 * **Parameters:** `updateCompany` — a Company object to be update
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteCompany(int deleteCompanyByID) throws Exception`

deleteCompany() a method that deletes a company from the companies table in the DB by company ID

 * **Parameters:** `deleteCompanyByID` — a company ID thats needs to be deleted
 * **Exceptions:** `Exception` — can throws Exception

## `public Company getOneCompany(int companyID) throws Exception`

getOneCompany() a method that gets a single company info from DB by company ID

 * **Parameters:** `companyID` — a company ID to get the info of
 * **Returns:** a Company object from DB with all the company data
 * **Exceptions:** `Exception` — can throws Exception

## `public int getCompanyIdByEmailAndPassword(String email, String password) throws Exception`

getCompanyIdByEmailAndPassword() a method that gets a single company info from DB by the company Email and Password

 * **Parameters:**
   * `email` — a company email to get the info of
   * `password` — a company password to get the info of
 * **Returns:** a Company object from DB with all the company data
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Company> getAllCompanies() throws Exception`

getAllCompanies() a method that gets all the companies info from the DB

 * **Returns:** an ArrayList of all the companies and their data in the DB
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getAllCompanyCoupons(int companyId) throws Exception`

getAllCompanyCoupons() a method that returns from the DB all the coupons of the company

 * **Parameters:** `companyId` — company ID to get the coupons for
 * **Returns:** an ArrayList of company coupons
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws Exception`

getAllCompanyCouponsByCategory() a method that returns from the DB all the coupons for the company that are in a specific Category

 * **Parameters:**
   * `companyId` — company ID to get the coupons for
   * `category` — category to get the coupons for
 * **Returns:** an ArrayList of company coupons per specific category
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws Exception`

getAllCompanyCouponsByMaxPrice() a method that returns from the DB all the coupons for the company that are less than a specific price

 * **Parameters:**
   * `companyId` — company ID to get the coupons for
   * `maxPrice` — the maximum price of company coupons to return
 * **Returns:** an ArrayList of company coupons up to a specific price
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCouponTitleExist(int companyId, String couponTitle) throws Exception`

isCouponTitleExist() a method that checks in the DB if company already have the same Title for an existing coupon

 * **Parameters:**
   * `companyId` — the company ID to check
   * `couponTitle` — the title to check if already exists for the company
 * **Returns:** True if the coupon title already exists for the company, and False if

     it doesn't
 * **Exceptions:** `Exception` — can throws Exception


---
## public class CustomerDBDAO implements ICustomersDAO
---


CustomerDBDAO the class that implements the CustomerDAO, with all the methods the Customer class will use. this class create the changes in the DB through the connectionPool

 * **Author:** dan



## `private static ConnectionPool connectionPool = ConnectionPool.getInstance()`

an instance of the ConnectionPool

## `public boolean isCustomerExists(String email, String password) throws Exception`

isCustomerExists() a method that gets the customer email and password and checks if the customer exist in the DB

 * **Parameters:**
   * `email` — getting the customer email
   * `Password` — getting the customer password
 * **Returns:** True- if customer exist in the DB, False- if the customer dose not

     exist
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCustomerExistsById(int customerId) throws Exception`

isCustomerExistsById() a method that gets the customer ID and checks if the customer ID exist in the DB

 * **Parameters:** `customerId` — customerId to check
 * **Returns:** True- if customer ID exist in the DB, False- if the customer ID dose

     not exist
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCustomerEmailExists(String email) throws Exception`

isCustomerEmailExists() a method that gets the customer email and checks if the customer email exist in the DB

 * **Parameters:**
   * `email` — getting the customer email
   * `Password` — getting the customer password
 * **Returns:** True- if customer exist in the DB, False- if the customer dose not

     exist
 * **Exceptions:** `Exception` — can throws Exception

## `public void addCustomer(Customer addCustomer) throws Exception`

addCustomer() a method that adds a customer to the customers table in the DB

 * **Parameters:** `addCustomer` — a customer object to add
 * **Exceptions:** `Exception` — can throws Exception

## `public void updateCustomer(Customer updateCustomer) throws Exception`

updateCustomer() a method that updates customer info in the customers table in the DB

 * **Parameters:** `updateCustomer` — - a customer object to update
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteCustomer(int deleteCustomerByID) throws Exception`

deleteCustomer() a method that deletes a customer from the customers table in the DB by customer ID

 * **Parameters:** `deleteCustomerByID` — a customer ID thats needs to be deleted
 * **Exceptions:** `Exception` — can throws Exception

## `public Customer getOneCustomer(int getCustomerByID) throws Exception`

getOneCustomer() a method that gets a single customer info from DB by customer ID

 * **Parameters:** `getCustomerByID` — a customer ID to get the info of
 * **Returns:** a Customer object from DB with all the customer data
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Customer> getAllCustomers() throws Exception`

getAllCustomers() a method that gets all the customers info from the DB

 * **Returns:** an ArrayList of all the customers and their data in the DB
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCouponExistForCustomer(int customerId, int couponId) throws Exception`

isCouponExistForCustomer() a method that checks if the customer already purchased a specific coupon already

 * **Parameters:**
   * `customerId` — customer Id to check coupons
   * `couponId` — coupon id to check if already been purchased by customer
 * **Returns:** True- if already purchased this coupon, False- if not
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getAllCustomerCoupons(int customerId) throws Exception`

getAllCustomerCoupons() a method that returns all the purchased coupons of a specific customer

 * **Parameters:** `customerId` — customer id to get the coupons of
 * **Returns:** ArrayList of coupons purchased by customer
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getAllCustomerCouponsByCategory(int customerId, Category category) throws Exception`

getAllCustomerCouponsByCategory() a method that returns all the specific customer purchased coupons from specific category

 * **Parameters:**
   * `customerId` — customer id to check coupons of
   * `category` — the category of coupons to return
 * **Returns:** ArrayList of coupons purchased by customer for specific category
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getAllCustomerCouponsByMaxPrice(int customerId, int maxPrice) throws Exception`

getAllCustomerCouponsByMaxPrice() a method that returns all the specific customer purchased coupons up to a max price

 * **Parameters:**
   * `customerId` — customer id to check coupons of
   * `maxPrice` — the maximum price of coupons that will be returned
 * **Returns:** ArrayList of coupons purchased by customer up to a max price
 * **Exceptions:** `Exception` — can throws Exception

## `public int getCustomerIdByEmailAndPassword(String email, String password) throws Exception`

getCustomerIdByEmailAndPassword() a method that returns a customer ID by getting its email and password

 * **Parameters:**
   * `email` — customer email to check
   * `password` — customer password to check
 * **Returns:** ID of the specific customer
 * **Exceptions:** `Exception` — can throws Exception


---
## public class CouponsDBDAO implements ICouponsDAO
---


CouponsDBDAO the class that implements the CouponsDAO, with all the methods the Coupon class will use. this class create the changes in the DB through the connectionPool

 * **Author:** dan


## `private static ConnectionPool connectionPool = ConnectionPool.getInstance()`

an instance of the ConnectionPool

## `public static Category categoryIdToString(int CategoryIndex)`

* categoryIdToString() a method converts the category ID to its Category name from the Category Enum Class

 * **Parameters:** `CategoryIndex` — the category ID number to convert to its Category name
 * **Returns:** a Category name represented by the ID

## `public static int categoryStringToId(Category category) throws Exception`

* categoryStringToId() a method converts the Category name to its ID from the Category Enum Class

 * **Parameters:** `category` — the category name to convert to its ID number
 * **Returns:** a Category ID number representing the Category name
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCouponExistById(int couponId) throws Exception`

isCouponExistById() a method that check if the coupon exist by its ID

 * **Parameters:** `couponId` — coupon ID to check if exist in DB
 * **Returns:** True if coupon ID exist in the DB, false if not
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCouponLeft(int couponId) throws Exception`

isCouponLeft() a method that check if the coupon has more than 0 amount by coupon ID

 * **Parameters:** `couponId` — coupon ID to check if any left
 * **Returns:** True if there is 1 or more coupons left, false if not
 * **Exceptions:** `Exception` — can throws Exception

## `public void addCoupon(Coupon addCoupon) throws Exception`

addCoupon() a method that adds a coupon to the coupons table in the DB

 * **Parameters:** `addCoupon` — a Coupon object to add to DB
 * **Exceptions:** `Exception` — can throws Exception

## `public boolean isCouponExpiered(int couponId) throws Exception`

isCouponExpiered() a method that check if the coupon has expired by its ID

 * **Parameters:** `couponId` — coupon ID to check if expired
 * **Returns:** True if coupon has expired, False if not
 * **Exceptions:** `Exception` — can throws Exception

## `public void updateCoupon(Coupon updateCoupon) throws Exception`

updateCoupon() a method that Updates a coupon in the coupons table in the DB

 * **Parameters:** `updateCoupon` — a Coupon object to be update
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteCoupon(int deleteCouponByID) throws Exception`

deleteCoupon() a method that deletes a coupon from the coupons table in the DB by coupon ID

 * **Parameters:** `deleteCouponByID` — a coupon ID thats needs to be deleted
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteAllCompanyCoupons(int companyId) throws Exception`

deleteAllCompanyCoupons() a method that deletes all company coupons from the coupons table in the DB by company ID

 * **Parameters:** `companyId` — a company ID thats needs to be delete its coupons
 * **Exceptions:** `Exception` — can throws Exception

## `public Coupon getOneCoupon(int couponID) throws Exception`

getOneCoupon() a method that gets a single coupon info from DB by coupon ID

 * **Parameters:** `couponID` — a coupon ID to get the info of
 * **Returns:** a Coupon object from DB with all the coupon data
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getAllCoupons() throws Exception`

getAllCoupons() a method that gets all the coupons and their info from the DB

 * **Returns:** an ArrayList of all the coupons and their data in the DB
 * **Exceptions:** `Exception` — can throws Exception

## `public void updateCouponAmount(int couponId, int couponManipulation) throws Exception`

updateCouponAmount() a method that updates a specific coupon amount. -1 if purchased and 1 if purchase is canceled.

 * **Parameters:**
   * `couponId` — the coupon ID that needed to update its amount
   * `couponManipulation` — the manipulation on the coupon amount that is

     needed -1 if purchased and 1 if purchase is

     canceled.
 * **Exceptions:** `Exception` — can throws Exception

## `public void addCouponPurchase(int couponId, int customerId) throws Exception`

addCouponPurchase() a method that adds a purchase of a coupon by a customer in the coustomers_vs_coupons table in the DB

 * **Parameters:**
   * `customerID` — the customer that purchased ID
   * `couponId` — the coupon purchased ID
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteCouponPurchase(int couponId, int customerId) throws Exception`

deleteCouponPurchase() a method that deletes a specific coupon purchase made by a specific customer from the coustomers_vs_coupons table in the DB

 * **Parameters:**
   * `customerID` — the customer that purchased ID
   * `couponId` — the coupon purchased ID
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteAllCouponPurchaseByCouponId(int couponId) throws Exception`

deleteAllCouponPurchaseByCouponId() a method that deletes all the coupon purchases made of a specific coupon from the coustomers_vs_coupons table in the DB

 * **Parameters:** `couponId` — the coupon ID needs to delete
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteAllCouponPurchaseByCustomerId(int deleteAllCouponPurchaseByCustomerId) throws Exception`

deleteAllCouponPurchaseByCustomerId() a method that deletes all the coupon purchases made by a specific customer from the coustomers_vs_coupons table in the DB

 * **Parameters:** `deleteAllCouponPurchaseByCustomerId` — the customer ID needs to delete
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteAllCouponPurchaseByCompanyId(int companyId) throws Exception`

deleteAllCouponPurchaseByCompanyId() a method that deletes all the coupon purchases of coupons related to a specific company from the coustomers_vs_coupons table in the DB

 * **Parameters:** `companyId` — the company ID needs to delete
 * **Exceptions:** `Exception` — can throws Exception

## `public void deleteAllExpiredCoupons() throws Exception`

deleteAllExpiredCoupons() a method for the use of the daily job that deletes all the coupon that expired and from the purchases history

 * **Exceptions:** `Exception` — can throws Exception

---
## public class ConnectionPool
---


ConnectionPool class creates a singleTon to allow basic connection to the DB for all the DBDAO classes

 * **Author:** dan



## `private ConnectionPool()`

A constructor that creates a stacking of 10 connections

## `public static ConnectionPool getInstance()`

getInstance() returns an instance for the connection

 * **Returns:** returns the instance for this connection pool

## `public Connection getConnection() throws InterruptedException`

getConnection() returns the first available connection from the Stack

 * **Returns:** the first available connection from the Stack
 * **Exceptions:** `InterruptedException` — Can throw an InterruptedException

## `public void restoreConnection(Connection conn)`

restoreConnection() gets the connection after used and returns it to the Stack of connections

 * **Parameters:** `conn` — the connection to return to the Stack

## `public void closeAllConnection() throws InterruptedException`

closeAllConnection() closes all the connections

 * **Exceptions:** `InterruptedException` — Can throw an InterruptedException