DROP DATABASE IF EXISTS `coupon_system`;
CREATE DATABASE `coupon_system`;
USE `coupon_system`;

-- Create `companies` table
DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
   `company_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
   `company_name`    VARCHAR(15) NOT NULL DEFAULT '' UNIQUE, 
   `company_email`  VARCHAR(30) NOT NULL DEFAULT '' UNIQUE,
   `company_password`  VARCHAR(10) NOT NULL DEFAULT '', 
   PRIMARY KEY (`company_id`)
);

-- Create `categories` table
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
   `category_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT ,
   `category_name`    VARCHAR(15) NOT NULL DEFAULT '' UNIQUE, 
   PRIMARY KEY (`category_id`)
);

-- Create `customers` table
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
   `customer_id`  INT UNSIGNED  NOT NULL AUTO_INCREMENT,
   `first_name`         VARCHAR(15)   NOT NULL DEFAULT '',
   `last_name`         VARCHAR(15)   NOT NULL DEFAULT '',
   `customer_email`      VARCHAR(30)   NOT NULL DEFAULT '' UNIQUE,
   `customer_password`        VARCHAR(10)   NOT NULL DEFAULT '',
   PRIMARY KEY (`customer_id`)
);

-- Create `coupons` table
DROP TABLE IF EXISTS `coupons`;
CREATE TABLE `coupons` (
   `coupon_id`  INT UNSIGNED  NOT NULL AUTO_INCREMENT,
   `company_id`     INT UNSIGNED  NOT NULL,
   `category_id`    INT UNSIGNED  NOT NULL,
   `title`      VARCHAR(40)   NOT NULL DEFAULT '',
   `description`        VARCHAR(250)   NOT NULL DEFAULT '',
   `start_date`    DATE   NOT NULL DEFAULT '0000-00-00',
   `end_date`    DATE   NOT NULL DEFAULT '0000-00-00',
   `amount`    INT NOT NULL DEFAULT 0,
   `price`   DOUBLE NOT NULL DEFAULT 0.0,
   `image` VARCHAR(100) NULL DEFAULT '',   
   PRIMARY KEY (`coupon_id`),
   FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
   FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Create `customers_vs_coupons` table
DROP TABLE IF EXISTS `customers_vs_coupons`;
CREATE TABLE `customers_vs_coupons` (
   `coupon_id`  INT UNSIGNED  NOT NULL,
   `customer_id`     INT UNSIGNED  NOT NULL,   
   PRIMARY KEY (`coupon_id`,`customer_id`),
   FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`coupon_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
   FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);




-- Inserting the Categories of coupons to the categories table
INSERT INTO `categories` VALUES
   (NULL,'Baby'),
   (NULL,'Baking'),
   (NULL,'Dairy'),
   (NULL,'Medical'),
   (NULL,'Pets'),
   (NULL,'Snacks'),
   (NULL,'Restaurants'),
   (NULL,'Spa'),
   (NULL,'Health'),
   (NULL,'Birthday'),
   (NULL,'Vacation'),
   (NULL,'Concerts'),
   (NULL,'Photography'),
   (NULL,'Electronics'),
   (NULL,'Cars');
   
   
	
 