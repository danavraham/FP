# Facades Package


This package contains all the bussiness logic of the program. Defining the Client Types, loggin option for each client and the actions thateach client can do in the program. contains the following classes:


* ClientType
* LoginManager
* ClientFacade
* AdminFacade
* CompanyFacade
* CustomerFacade

---
## public enum ClientType
---


ClientType is an Enum Class that contains all Client type options for the Login Manager -Administrator,Company and Customer

 * **Author:** dan

---
## public class LoginManager
---


LoginManager is a Singleton class that allows 3 type of clients to login to the system

 * **Author:** dan

## `private LoginManager()`

Private empty constructor suppresses generation of a (public) default constructor

## `public static LoginManager getInstance()`

 * **Returns:** returns a reference to the instance for the LoginManager Class.

## `public ClientFacade login(String email, String password, ClientType clientType) throws Exception`

login() a method that create the login for one of 3 client types

 * **Parameters:**
   * `email` — the email of the client trying to login
   * `password` — the password of the client trying to login
   * `clientType` — the client type(Enum) of the client trying to log in
 * **Returns:** the right client facade if the login details are correct
 * **Exceptions:** `Exception` — can throws Exception

---
## public abstract class ClientFacade
---


ClientFacade the abstract class which the client types facades will implement through the LoginManager

 * **Author:** dan


## `public boolean login(String email, String password) throws Exception`

login() the abstract method that through the implementation will return true if login successful

 * **Parameters:**
   * `email` — gets a client email for login
   * `password` — gets a client password for login
 * **Returns:** by default false, if login is successful through implementation- will

     return true
 * **Exceptions:** `Exception` — can throws Exception

---
## public class AdminFacade extends ClientFacade
---


AdminFacade This is a facade class of the administrator. it contains all the actions the administrator of the system can do. this class extends ClientFacade and Override it's login method

 * **Author:** dan


## `public AdminFacade(String email, String password) throws Exception`

AdminFacade constructor gets the email and the password of the Admin and checks if login is possible with this email and password

 * **Parameters:**
   * `email` — gets email to check for login details
   * `password` — gets password to check for login details
 * **Exceptions:** `Exception` — can throws Exception

## `@Override  public boolean login(String email, String password)`

login() method that Override the login method of the LoginManager and compares the login details of the Administrator to the hard-coded "admin@admin.com" and "admin" to see if details are correct

 * **Parameters:**
   * `email` — gets an email to check
   * `password` — gets a password to check
 * **Returns:** return true if login successful, and false if not

## `public void addCompany(Company company) throws Exception`

addCompany() a method that adds a company to the DB after it checks if new company email or name already exists in the DB. cannot add a company with a name that already exist in the DB. cannot add a company with a Email that already exist in the DB

 * **Parameters:** `company` — new company Object
 * **Exceptions:** `Exception` — if company name or email already exists in DB.

## `public void updateCompany(Company company) throws Exception`

updateCompany() a method that updates company details in the DB after it checks if company exists in DB by ID, and that company name hasen't changed. cannot update company Name

 * **Parameters:** `company` — company Object to update
 * **Exceptions:** `Exception` — if company dosen't exist or trying to change company name.

## `public void deleteCompany(int companyId) throws Exception`

deleteCompany() a method that deletes company from DB if the company exist in the DB. in addition it deletes company coupons and coupons from history purchases

 * **Parameters:** `companyId` — company ID to delete
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Company> getAllCompanies() throws Exception`

getAllCompanies() a method that gets all companies list and info from DB

 * **Returns:** an array list of the companies
 * **Exceptions:** `Exception` — can throws Exception

## `public Company getOneCompany(int companyId) throws Exception`

getOneCompany() a method that gets one company by its ID

 * **Parameters:** `companyId` — company ID to get
 * **Returns:** a company Object
 * **Exceptions:** `Exception` — can throws Exception

## `public void addCustomer(Customer newCustomer) throws Exception`

addCustomer() a method that adds a customer to the DB if the Email is not in the DB of customers

 * **Parameters:** `newCustomer` — company Object to add
 * **Exceptions:** `Exception` — if other customer has the same email

## `public void updateCustomer(Customer customerUpdate) throws Exception`

updateCustomer() a method that updates customer details by its ID so the ID can't be updated, after checking if it is exist

 * **Parameters:** `customerUpdate` — customer Object to update
 * **Exceptions:** `Exception` — if customer does not exist

## `public void deleteCustomer(int customerIdToDelete) throws Exception`

deleteCustomer() a method that deletes customer from DB it also deletes the customer coupons from history purchases, after checking if the customer exist

 * **Parameters:** `customerIdToDelete` — the customer ID needs to delete
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Customer> getAllCustomers() throws Exception`

getAllCustomers() a method that gets all the customers list and info from DB

 * **Returns:** an array list of the customers
 * **Exceptions:** `Exception` — can throws Exception

## `public Customer getOneCustomer(int customerId) throws Exception`

getOneCustomer() a method that gets one customer by its ID if it exist

 * **Parameters:** `customerId` — customer ID to get
 * **Returns:** a customer Object
 * **Exceptions:** `Exception` — can throws Exception

---
## public class CompanyFacade extends ClientFacade
---

CompanyFacade This is a facade class of the companies. it contains all the actions the companies in the system can do. this class extends ClientFacade and Override it's login method

 * **Author:** dan

## `public int getCompanyId()`

getCompanyId() a method that gets the company ID

 * **Returns:** the company ID

## `public void setCompanyId(int companyId)`

setCompanyId() method that sets the company ID

 * **Parameters:** `companyId` — company ID to set

## `public CompanyFacade(String email, String password) throws Exception`

CompanyFacade constructor gets the email and the password of the Company and checks if login is possible with this email and password

 * **Parameters:**
   * `email` — gets email to check for login details
   * `password` — gets password to check for login details
 * **Exceptions:** `Exception` — can throws Exception

## `@Override  public boolean login(String email, String password) throws Exception`

login() method that Override the login method of the LoginManager and compares the login details of the company to the details in the DB to see if the details are correct

 * **Parameters:**
   * `email` — gets an email to check
   * `password` — gets a password to check
 * **Returns:** return true if login successful, and false if not

## `public void addCoupon(Coupon coupon) throws Exception`

addCoupon() a method that adds a coupon to the DB after it checks if the new coupon title already exist for one of the company coupons coupon title cannot match a coupon title of the same company' but can be the same of other company

 * **Parameters:** `coupon` — new Coupon Object to add to the DB
 * **Exceptions:** `Exception` — if company already has this coupon title

## `public void updateCoupon(Coupon coupon) throws Exception`

updateCoupon() a method that updates a coupon in the DB by the coupon ID,after it checks if coupon ID exist in DB and that coupon-company-ID isn't changed. cannot update coupon code and cannot update coupon company code

 * **Parameters:** `coupon` — new Coupon Object to update
 * **Exceptions:** `Exception` — if trying to update company ID

## `public void deleteCoupon(int couponIdToDelete) throws Exception`

deleteCoupon() a method that deletes a coupon from the DB, the method also deletes this coupon from coupon purchase history, after it checks the coupon ID exist in the DB

 * **Parameters:** `couponIdToDelete` — the ID of coupon needs to be deleted
 * **Exceptions:** `Exception` — if the coupon ID don't exist in DB

## `public ArrayList<Coupon> getCompanyCoupons() throws Exception`

getCompanyCoupons() a method that gets all the company coupons and info from the DB

 * **Returns:** an Array List of company coupons
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getCompanyCouponsByCategory(Category category) throws Exception`

getCompanyCouponsByCategory() a method that gets all the company coupons that are from a specific category

 * **Parameters:** `category` — the specific category to return coupons for
 * **Returns:** an Array List of company category coupons
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws Exception`

getCompanyCouponsByMaxPrice() a method that gets all the company coupons that are up to a maximum price

 * **Parameters:** `maxPrice` — - the maximum price of the coupons to return
 * **Returns:** an Array List of company coupons up to max price
 * **Exceptions:** `Exception` — can throws Exception

## `public Company getCompanyDetails() throws Exception`

getCompanyDetails() a method that gets back the company that is logged in details

 * **Returns:** an Company Object of the registered company
 * **Exceptions:** `Exception` — can throws Exception

---
## public class CustomerFacade extends ClientFacade
---

CustomerFacade This is a facade class of the customers. it contains all the actions the customers in the system can do. this class extends ClientFacade and Override it's login method

 * **Author:** dan

     <p>

## `public int getCustomerId()`

getCompanyId() a method that gets the customer ID

 * **Returns:** 

## `public void setCustomerId(int customerId)`

setCustomerId() method that sets the customer ID

 * **Parameters:** `customerId` — customer ID to set

## `public CustomerFacade(String email, String password) throws Exception`

CustomerFacade constructor gets the email and the password of the Customer and checks if login is possible with this email and password

 * **Parameters:**
   * `email` — gets email to check for login details
   * `password` — gets password to check for login details
 * **Exceptions:** `Exception` — can throws Exception

## `@Override  public boolean login(String email, String password) throws Exception`

login() method that Override the login method of the LoginManager and compares the login details of the customer to the details in the DB to see if the details are correct

 * **Parameters:**
   * `email` — gets an email to check
   * `password` — gets a password to check
 * **Returns:** return true if login successful, and false if not
 * **Exceptions:** `Exception` — can throws Exception

## `public void purchaseCoupon(Coupon coupon) throws Exception`

purchaseCoupon() is a method that used to purchase a coupon for customer. customer cannot purchase a coupon more than once. coupon can be purchased only if its amount is more than 0. coupon can be purchased only if not expired. once purchased, the amount of coupons reduced by 1

 * **Parameters:** `coupon` — - a Coupon object to be purchased by current customer
 * **Exceptions:** `Exception` — 

## `public Coupon getCouponByID(int couponId) throws Exception`

getCouponByID() is a method gets a Coupon Object from DB by its ID

 * **Parameters:** `couponId` — the ID of the coupon to return
 * **Returns:** an Coupon Object
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getCustomerCoupons() throws Exception`

getCustomerCoupons() is a method gets all coupons and their info of current logged in customer

 * **Returns:** an ArrayList of customer coupons
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) throws Exception`

getCustomerCouponsByCategory() is a method gets all coupons of current customer from a specific category

 * **Parameters:** `category` — the Category of coupons of customer to return
 * **Returns:** an ArrayList of current customer coupons from a specific category
 * **Exceptions:** `Exception` — can throws Exception

## `public ArrayList<Coupon> getCustomerCouponsByMaxPrice(int maxPrice) throws Exception`

getCustomerCouponsByMaxPrice() is a method gets all coupons of current customer up to a max price

 * **Parameters:** `maxPrice-` — the max price of current customer coupons to return
 * **Returns:** an ArrayList of current customer coupons up to max price
 * **Exceptions:** `Exception` — can throws Exception

## `public Customer getCustomerDetails() throws Exception`

getCustomerDetails() is a method that returns the logged in customer details

 * **Returns:** an Customer object of the logged in customer
 * **Exceptions:** `Exception` — can throws Exception