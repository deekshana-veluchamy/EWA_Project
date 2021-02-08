-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		                          	     				  *Grocery Hub*
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSTRUCTIONS TO DEPLOY AND RUN THE PROJECT:

 	1. Copy "EWA_Project" folder to tomcat webapps folder.

	2. Start the MongoDB server with mongod.exe and mongo.exe with respect to location.
		a) database name used is Reviews
		b) Collection name is myReviews
    c) MongodB Version used - 4.0

	3. Start the MySQL database server
		a) database used is groceryhub
		b) tables used to store data are customer,CustomerRegistration,stores,productdetails,restaurants,transactions,favourites,customerorder
	4. Start tomcat server after setting up the environment variables.

 	5. Open Google chrome and type url as ==> localhost/EWA_Project

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Instruction to run python script:

 1. Open jupyter notebook using anaconda prompt.
 2. Open DealMatches.ipnyb and specify the src path where the output has to be written.
 3. This will access Twitter API from "amazon_grocery" and access all amazon_grocery deals. The program will compare these details with the details in our database and gives the result
 4. Execute the python program to generate DealMatches.txt
 5. Open ProductRecommender.ipnyb and specify the src path where the csv file has to be written.
 6. This connects to the mongodb and generates the output.
 7. Run the website to see the results.

 Instructions Run the Graph DB
 1. First download Neo4j desktop and open it
 2. Download the Graph Data Science Library and APOC plugins
 3. Create the local DB
 4. Open db_tables_to_csv_files.ipnyb Python Script to get the table information from MYSQL and import to csv. All csv are stored in CSV folder
 5. Run the script from neo4j_script.txt
 6. Create a Query to load csv and its corresponding details
 7. Then do match (n) return n; to get all nodes and relations.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

ENVIRONMENT SETUP FILE:
 1. set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_65
 2. set PATH="C:\Program Files\Java\jdk1.8.0_65\bin";%PATH%
 3. set CLASSPATH=.;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\servlet-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\jsp-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\el-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mysql-connector-java-5.1.47-bin.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mongo-java-driver-3.2.2.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\gson-2.3.1.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mail.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\activation.jar
 4. set ANT_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34
 5. set TOMCAT_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34
 6. set CATALINA_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Below are the DDL commands used in this application.
 1. create table CustomerRegistration(username varchar(40),password varchar(40),repassword varchar(40),name varchar(40),email varchar(40),mobile varchar(40));

 2. create table Productdetails(ProductType varchar(40),ProductId varchar(40),productName varchar(40),productPrice double,productImage varchar(40),productManufacturer varchar(40),productDiscount double,quantity double,manufacturerRebate varchar(40),primary key(Id,productName));

 3. create table Customer(username varchar(40),Customer_Name varchar(40), age int, occupantion varchar(40),credit_card_number varchar(40),street varchar(40),city varchar(40),state varchar(40),zipcode varchar(40),latitute varchar(40),longitude varchar(40))

 4.  create table customerOrder(OrderId Integer,storeId int, UserName varchar(40),OrderName varchar(40),OrderPrice double,quantity double, street varchar(40),city varchar(40),state varchar(40),zipcode varchar(40),creditCardNo varchar(40),orderDate varchar(40),deliverydate varchar(40),status varchar(40),deliverables varchar(40), pickupaddress varchar(40),primary key(OrderId,UserName,OrderName));

 5. create table stores(fname varchar(40),lname varchar(40), mobile varchar(40),street varchar(40),city varchar(40),state varchar(40),zipcode varchar(40),SID int, sname varchar(40),smobile varchar(40),semail varchar(40), sstreet varchar(40),scity varchar(40),szipcode varchar(40),username varchar(40), password varchar(40), repassword varchar(40), simage varchar(40),primary key(SID,sname) )

 6. create table restaurants(restaurantId varchar(40),restaurantName varchar(40), address varchar(40), zipcode varchar(40),image varchar(40))

 7. create table transactions(loginID varchar(40), customer_name varchar(40), customer_age varchar(40),Customer_Occupation varchar(40),credit_card_number varchar(40),order_id int, order_date varchar(40),Expected_Delivery varchar(45) ,Actual_Delivery varchar(45) ,Product_ID varchar(45) ,Product_Name varchar(455) ,Category varchar(45) ,Manufacturer varchar(45) ,Review_Rating int ,Delivery_Tracking_ID varchar(45) ,Delivery_Type varchar(45) ,Delivery_Zip_Code varchar(45),Transaction_Status
  varchar(45),Order_Returned varchar(45),Order_Delivered varchar(45), primarykey(loginID,orderID,productID))

 8. create table favourites(id varchar(45) ,userName varchar(45),name varchar(455) ,retailer varchar(45) ,price double ,discount double)
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

FEATURES IMPLEMENTED:
1.Assignment #1 

  	a) Registration - Login,Logout (Two user types - customer,Retailer)
  	b) Header access to product details with each category features.
    CUSTOMER FEATURES:
    a) Customers can select the nearby stores by looking at the google maps. Each customer is assigned a store for buying products.
   	b) Adding an Item to cart.
    c) Customer shall be able to shop online to buy one or multiple items in the same session.
   	d) Add/Remove Item from the cart before checkout process.
   	e) Can choose pickup/delivery feature. And for delivery feature customer can pickup the store.
   	f) Payment using credit card and Summary can be viewed (Order Id and delivery date).
   	g) Cancelling an order from their account based on order id.
   	f) View order history.

    Retailer FEATURES:
        a)Create new customer accounts.
        b)Add/delete/update product details.

2.Assignment #2 

	 a) All accounts login information is stored in MySQL database.
	 b)All Customers transactions/orders is stored in SQL database (MySQL).
	 c)All order updates to insert/delete/update orders is reflected in the MySQL database; not only the HashMap objects.
	 d)Customer must be able to write and view product reviews.
	 e)Product reviews(using product review form) is stored in NoSQL database (MongoDB).
	 f)All implementation for MySQL is placed in a class called MySQLDataStoreUtilities.java.
	 g)All implementation  added for MongoDB shall be placed in a class called MongoDBDataStoreUtilities.java.
	 h) Added Trending feature for Top five most liked products, Top five zip-codes where maximum number of products sold, Top five most sold products regardless of the rating.

3. ASSIGNMENT #3 
Implemented inventory and Sales report that is accessible only to Store Manager.
INVENTORY REPORT:
    	a) Generated a table of all products and how many items of every product currently available in the store using product name, price, number of item items that product available.
	b) Generated a Bar Chart that shows the product names and the total number of items available for every product.
	c) Generated a table of all products currently on sale.
	d) Generated a table of all products currently that have manufacturer rebates.
SALES REPORT:
    	a) Generated a table of all products sold and number of items of every product sold by listing the product name, product price, number of items sold, and total sales of every product sold.
	b) Generated a Bar Chart by showing the product names and the total sales for every product.
	c) Generated a table of total daily sales transactions by listing the dates and total sales for every day-date.

4. ASSIGNMENT #4 (ALL FEATURES IMPLEMENTED)

	AUTO-COMPLETION SEARCH FEATURE:
	  a) When the app-server starts up, the Products are first read into a hashmap from ProductCatalog.xml file and then stored in MySQL database.
	  b) Retailer can insert/update/delete products are reflected in the hashmap and then in MySQL database
	  c) All new code added for the auto-complete feature are added in a class called AjaxUtility.java
  GraphDB:
    a) Crated the CSV filr for the transaction table in MYSQL and the graphdb application is built.
    b) The cypher codes are written and the ouputs are viewed


5.ASSIGNMENT #5

	DEAL MATCHES FEATURE:
         a)The deal match component uses DealMatches.java servlet and the provided python script to extract deals from twitter, cross check them with products in the MySQL server and write
           them into a text file - DealMatches.txt.
         b)Then the relevant product deals are displayed on the home page of grocery hub Web Application.

        PRODUCT RECOMMENDATION SYSTEM:
         a)Based on the history of the user purchases and after the user makes a purchase, your enterprise web application, shall make three product recommendations for the logged in user.

FEATURES IMPLEMENTED:
      1 User Account/Profile/Transaction management & MySQL
      2 Recommender system - restaurant and product recommendation
      3 Twitter matches - amazon_fresh deals
      4 Analytics & Visual Reports - inventory and sales report
      5 Reviews & Trending & MongoDB
      6 Auto-Complete Search feature
      7 Google MAPS - Near ME search feature
      8 Knowledge Graph Searches & Neo4J

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
