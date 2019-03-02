# JavaBeans Package


This package contains all the basic classes of the program that will in the end get into the DB. contains the following classes:


* PropertiesA
* Propertiesb
* Category
* Company
* Customer
* Coupon

---
## public abstract class PropertiesA
---

PropertiesA is a Class that creates the basic property that is common to the coupon, customer and company- the ID

 * **Author:** Dan

## `public PropertiesA(int id) throws GeneralException`

Constructor for the PropertiesA class that gets an id

 * **Parameters:** `id` — gets an ID
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public PropertiesA() throws GeneralException`

an empty constructor for the PropertiesA class

 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public int getId()`

getId() method that gets the ID

 * **Returns:** returns the ID

## `public void setId(int id) throws GeneralException`

setId() Method that sets the ID if it is valid must be more than 0

 * **Parameters:** `id` — Id to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `@Override  public String toString()`

toString() Override the toString method to return the ID

 * **Returns:** returns a string of all ID attributes

---
## public abstract class PropertiesB extends PropertiesA
---

PropertiesB is a Class that creates the second basic property that is common to the customer and company - Email, Passwors and an ArrayList of Coupons

 * **Author:** Dan

## `public PropertiesB(int id, String email, String password, ArrayList<Coupon> coupons) throws GeneralException`

Constructor for the PropertiesB class that gets an id, Email, password and an ArrayList of coupons

 * **Parameters:**
   * `id` — gets an ID
   * `email` — gets an email
   * `password` — gets a password
   * `coupons` — gets an ArrayList of coupons
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public PropertiesB(int id, String email, String password) throws GeneralException`

Constructor for the PropertiesB class that gets an id, Email and password

 * **Parameters:**
   * `id` — gets an ID
   * `email` — gets an email
   * `password` — gets a password
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public PropertiesB(String email, String password) throws GeneralException`

Constructor for the PropertiesB class that gets an Email and password

 * **Parameters:**
   * `email` — gets an email
   * `password` — gets a password
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public String getEmail()`

getEmail() method that gets the email

 * **Returns:** returns the email

## `public void setEmail(String email) throws GeneralException`

setEmail() Method that sets the email if it is valid must be right email format

 * **Parameters:** `email` — email to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public String getPassword()`

getPassword() method that gets the password

 * **Returns:** returns the password

## `public void setPassword(String password) throws GeneralException`

setPassword() Method that sets the password if it is valid must contain 6-10 charaters, at least one letter, at least one digit

 * **Parameters:** `password` — password to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public ArrayList<Coupon> getCoupons()`

getCoupons() method that gets the coupons ArrayList

 * **Returns:** returns the coupons ArrayList

## `public void setCoupons(ArrayList<Coupon> coupons)`

setCoupons() Method that sets the coupons ArrayList

 * **Parameters:** `coupons` — coupons ArrayList to set
 * **Exceptions:** `GeneralException` — 

## `@Override  public String toString()`

toString() Override the toString method to return the Email, Passwors and the Coupons ArrayList

 * **Returns:** returns a string of all PropertiesB attributes

---
## public enum Category
---


Category is an Enum Class that contains all category options for coupons

 * **Author:** Dan

---
## public class Company extends PropertiesB
---

Company is a Class that creates a Company and Extends the PropertiesB that Extends PropertiesA

 * **Author:** Dan

## `public Company(int id, String email, String password, String name, ArrayList<Coupon> coupons)    throws GeneralException`

Constructor for the Company class that gets id, email, password, name and ArrayList of coupons

 * **Parameters:**
   * `id` — gets a company ID
   * `email` — gets a company Email
   * `password` — gets a company password
   * `name` — gets a company name
   * `coupons` — gets an ArrayList of company coupons
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public Company(int id, String email, String password, String name) throws GeneralException`

Constructor for the Company class that gets id, email, password and name

 * **Parameters:**
   * `id` — gets a company ID
   * `email` — gets a company Email
   * `password` — gets a company password
   * `name` — gets a company name
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public Company(String email, String password, String name) throws GeneralException`

Constructor for the Company class that gets email, password and name

 * **Parameters:**
   * `email` — gets a company Email
   * `password` — gets a company password
   * `name` — gets a company name
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public String getName()`

getName() method that gets the company name

 * **Returns:** returns the company name

## `public void setName(String name) throws GeneralException`

setName() method that sets the company name if it is valid characters must be 2-15, only letters

 * **Parameters:** `name` — company name to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `@Override  public String toString()`

toString() Override the toString method to return the company attributes

 * **Returns:** returns a string all company attributes

---
## public class Customer extends PropertiesB
---

Customer is a Class that creates a Customer and Extends the PropertiesB that Extends PropertiesA

 * **Author:** Dan

## `public Customer(int id, String email, String password, String firstName, String lastName, ArrayList<Coupon> coupons)    throws GeneralException`

Constructor for the Customer class that gets id, email, password, firstName, lastName and coupons

 * **Parameters:**
   * `id` — gets a customer ID
   * `email` — gets a customer email
   * `password` — gets a customer password
   * `firstName` — gets a customer firstName
   * `lastName` — gets a customer lastName
   * `coupons` — gets an ArrayList of customer coupons
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public Customer(int id, String email, String password, String firstName, String lastName) throws GeneralException`

Constructor for the Customer class that gets id, email, password, firstName, and lastName

 * **Parameters:**
   * `id` — gets a customer ID
   * `email` — gets a customer email
   * `password` — gets a customer password
   * `firstName` — gets a customer firstName
   * `lastName` — gets a customer lastName
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public Customer(String email, String password, String firstName, String lastName) throws GeneralException`

Constructor for the Customer class that gets email, password, firstName, and lastName

 * **Parameters:**
   * `email` — gets a customer email
   * `password` — gets a customer password
   * `firstName` — gets a customer firstName
   * `lastName` — gets a customer lastName
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public String getFirstName()`

getFirstName() method that gets the customer firstName

 * **Returns:** returns the customer firstName

## `public void setFirstName(String firstName) throws GeneralException`

setFirstName() Method that sets the customer firstName if it is valid must be between 2-15 letters, and letters only

 * **Parameters:** `firstName` — customer firstName to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public String getLastName()`

getLastName() method that gets the customer lastName

 * **Returns:** returns the customer lastName

## `public void setLastName(String lastName) throws GeneralException`

setLastName() Method that sets the customer lastName if it is valid must be between 2-15 letters, and letters only

 * **Parameters:** `lastName` — customer lastName to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `@Override  public String toString()`

toString() Override the toString method to return the customer attributes

 * **Returns:** returns a string all coupon attributes

---
## public class Coupon extends PropertiesA
---

Coupon is a Class that creates the Coupon and Extends PropertiesA

 * **Author:** Dan

## `public Coupon(int id, int companyId, Category category, String title, String description, Date startDate,    Date endDate, int amount, double price, String image) throws GeneralException`

Constructor for the Coupon class that gets id, companyId, category, title, description, startDate, endDate, amount, price and a path to image

 * **Parameters:**
   * `id` — gets a coupon ID
   * `companyId` — gets a coupon companyId
   * `category` — gets a coupon category
   * `title` — gets a coupon title
   * `description` — gets a coupon description
   * `startDate` — gets a coupon startDate
   * `endDate` — gets a coupon endDate
   * `amount` — gets a coupon amount
   * `price` — gets a coupon price
   * `image` — gets a coupon image
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public int getCompanyId()`

getCompanyId() method that gets the coupon companyId

 * **Returns:** returns the coupon companyId

## `public void setCompanyId(int companyId) throws GeneralException`

setCompanyId() Method that sets the coupon company ID if it is valid must be more than 0

 * **Parameters:** `companyId` — coupon company Id to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public Category getCategory()`

getCategory() method that gets the coupon category

 * **Returns:** returns the coupon category

## `public void setCategory(Category category)`

setCategory() Method that sets the coupon category

 * **Exceptions:** `GeneralException` — 

## `public String getTitle()`

getTitle() method that gets the coupon title

 * **Returns:** returns the coupon title

## `public void setTitle(String title) throws GeneralException`

setTitle() Method that sets the coupon title if it is valid must be between 4-40 letters

 * **Parameters:** `title` — coupon title to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public String getDescription()`

getDescription() method that gets the coupon description

 * **Returns:** returns the coupon description

## `public void setDescription(String description) throws GeneralException`

setDescription() Method that sets the coupon Description if it is valid must be between 10-250 letters

 * **Parameters:** `description` — coupon description to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public Date getStartDate()`

getStartDate() method that gets the coupon startDate

 * **Returns:** returns the coupon startDate

## `public void setStartDate(Date startDate)`

setStartDate() Method that sets the coupon startDate

 * **Parameters:** `startDate` — coupon startDate to set

## `public Date getEndDate()`

getEndDate() method that gets the coupon endDate

 * **Returns:** returns the coupon endDate

## `public void setEndDate(Date endDate) throws GeneralException`

setEndDate() Method that sets the coupon endDate if it is valid must be after current date and after the coupon startDate

 * **Parameters:** `endDate` — coupon endDate to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public int getAmount()`

getAmount() method that gets the coupon amount

 * **Returns:** returns the coupon amount

## `public void setAmount(int amount) throws GeneralException`

setAmount() Method that sets the coupon amount if it is valid must be at least 1

 * **Parameters:** `amount` — coupon amount to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public Double getPrice()`

getPrice() method that gets the coupon price

 * **Returns:** returns the coupon price

## `public void setPrice(double price) throws GeneralException`

setPrice() Method that sets the coupon price if it is valid must be more than 0

 * **Parameters:** `price` — coupon price to set
 * **Exceptions:** `GeneralException` — can throw GeneralException

## `public String getImage()`

getImage() method that gets the coupon image path

 * **Returns:** returns the coupon image path

## `public void setImage(String image)`

setImage() Method that sets the coupon image path

 * **Parameters:** `image` — coupon image path to set

## `@Override  public String toString()`

toString() Override the toString method to return the coupon attributes

 * **Returns:** returns a string of all coupon attributes