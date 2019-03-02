# Coupon System

This is the first stage of a Coupon System. This system contains 3 levels of users- Administrators, Companies and Customers, each one with its own premissions.

## Getting Started

In order to run the system, you will first need to download the files. Secondly, you will need to find the file sql>project.sql and run it on your visual tool for database. Thirdly, you will need to run the main>program.java files.

### Prerequisites

You will need the following:
* An IDE to run and edit the java files (like Eclipse).
* An unified visual tool for database (like mysql workbench).
* A  web server solution (like XAMPP).


## Packages

### src 
* **javaBean** - All the basic classes of the system.
* **dao**- All the interfaces that define a general CRUD actions possible on the DB.
* **dbdao** - An implementation of the dao interfaces withe the actual CRUD actions.
* **facades** - The bussines logic that defines what actions each client type could do.
* **jobs** - Jobs made with different threads.
* **exeptions** - defenition of General Exceptions which prints them.
* **main** - Contains the main program and a test file to test all the possible options of the system.

### lib
* **mysql-connector-java-5.1.47.jar**

### sql
* **project.sql** - The sql file which builds the DB, tables and insert the first run info.


## Authors

* **Dan Avraham**
* **Or Malka**
* **Nisan Nisanov**

