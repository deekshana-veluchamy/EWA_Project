import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class MySqlDataStoreUtilities
{

	static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub","root","Ilovemyself");
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try{


		getConnection();


		String truncatetableprod = "delete from  Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();



		String insertProductQurey = "INSERT INTO  Productdetails(ProductType,ProductId,productName,productPrice,productImage,productManufacturer,productDiscount,quantity,manufacturerRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?);";

    for(Map.Entry<String,FruitsVeggies> entry : SaxParserDataStore.fvs.entrySet())
		{
			String name = "fvs";
	        FruitsVeggies fv = entry.getValue();

			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,fv.getId());
			pst.setString(3,fv.getName());
			pst.setDouble(4,fv.getPrice());
			pst.setString(5,fv.getImage());
			pst.setString(6,fv.getRetailer());
			pst.setDouble(7,fv.getDiscount());
			pst.setDouble(8,fv.getQuantity());
			pst.setString(9,fv.getManufacturerRebate());

			pst.executeUpdate();


		}


		for(Map.Entry<String,Grocery> entry : SaxParserDataStore.grocerys.entrySet())
		{
			String name = "grocerys";
	        Grocery grocery = entry.getValue();

			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,grocery.getId());
			pst.setString(3,grocery.getName());
			pst.setDouble(4,grocery.getPrice());
			pst.setString(5,grocery.getImage());
			pst.setString(6,grocery.getRetailer());
			pst.setDouble(7,grocery.getDiscount());
			pst.setDouble(8,grocery.getQuantity());
			pst.setString(9,grocery.getManufacturerRebate());

			pst.executeUpdate();


		}

		for(Map.Entry<String,Dairy> entry : SaxParserDataStore.dairies.entrySet())
		{
	        Dairy dairy = entry.getValue();
			String name = "dairies";

			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,dairy.getId());
			pst.setString(3,dairy.getName());
			pst.setDouble(4,dairy.getPrice());
			pst.setString(5,dairy.getImage());
			pst.setString(6,dairy.getRetailer());
			pst.setDouble(7,dairy.getDiscount());
			pst.setDouble(8,dairy.getQuantity());
			pst.setString(9,dairy.getManufacturerRebate());

			pst.executeUpdate();
		}

		for(Map.Entry<String,Beverages> entry : SaxParserDataStore.beverage.entrySet())
		{
			String name = "beverages";
	        Beverages beverages = entry.getValue();

			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,beverages.getId());
			pst.setString(3,beverages.getName());
			pst.setDouble(4,beverages.getPrice());
			pst.setString(5,beverages.getImage());
			pst.setString(6,beverages.getRetailer());
			pst.setDouble(7,beverages.getDiscount());
			pst.setDouble(8,beverages.getQuantity());
			pst.setString(9,beverages.getManufacturerRebate());

			pst.executeUpdate();


		}

    for(Map.Entry<String,Frozen> entry : SaxParserDataStore.frozens.entrySet())
		{
			String name = "frozens";
	        Frozen frozen = entry.getValue();

			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,frozen.getId());
			pst.setString(3,frozen.getName());
			pst.setDouble(4,frozen.getPrice());
			pst.setString(5,frozen.getImage());
			pst.setString(6,frozen.getRetailer());
			pst.setDouble(7,frozen.getDiscount());
			pst.setDouble(8,frozen.getQuantity());
			pst.setString(9,frozen.getManufacturerRebate());

			pst.executeUpdate();


		}

	}catch(Exception e)
	{
  		e.printStackTrace();
	}
}



public static void insertUser(String username,String password,String repassword,String name,String email,String mobile)
{
	try
	{

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO CustomerRegistration(username,password,repassword,name,email,mobile) "
		+ "VALUES (?,?,?,?,?,?);";

		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,name);
		pst.setString(5,email);
		pst.setString(6,mobile);
		pst.execute();
	}
	catch(Exception e)
	{

	}
}

public static HashMap<String,User> selectUser()
{
	HashMap<String,User> hm=new HashMap<String,User>();
	try
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  CustomerRegistration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("repassword"),rs.getString("name"),rs.getString("email"),rs.getString("mobile"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}

public static void insertRetailer(int sid, String fname,String lname,String mobile,String street,String city,String zipcode,String sname,String smobile,String semail,String sstreet,String scity,String szipcode,String username,String password,String repassword,String photo)
{
	try
	{
		System.out.println("in sql");

		getConnection();
		String insertIntoRegisterQuery = "INSERT INTO stores(fname,lname,mobile,street,city,zipcode,SID,sname,smobile,semail,sstreet,scity,szipcode,username,password,repassword,simage)" +
"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = conn.prepareStatement(insertIntoRegisterQuery);
		pst.setString(1,fname);
		pst.setString(2,lname);
		pst.setString(3,mobile);
		pst.setString(4,street);
		pst.setString(5,city);
		pst.setString(6,zipcode);
		pst.setInt(7,sid);
		pst.setString(8,sname);
		pst.setString(9,smobile);
		pst.setString(10,semail);
		pst.setString(11,sstreet);
		pst.setString(12,scity);
		pst.setString(13,szipcode);
		pst.setString(14,username);
		pst.setString(15,password);
		pst.setString(16,repassword);
		pst.setString(17, photo);
		System.out.println("done" );
		pst.executeUpdate();

	}
	catch(Exception e)
	{

	}
}

public static HashMap<String,Retailer> selectRetailer()
{
	HashMap<String,Retailer> hm=new HashMap<String,Retailer>();
	try
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from stores";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	Retailer user = new Retailer(rs.getString("fname"),rs.getString("lname"),rs.getString("mobile"),rs.getString("street"),rs.getString("city"),rs.getString("zipcode"),rs.getString("sname"),rs.getString("smobile"),rs.getString("semail"),rs.getString("sstreet"),rs.getString("scity"),rs.getString("szipcode"),rs.getString("username"),rs.getString("password"),rs.getString("repassword"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}


public static String insertOrders(int orderId,String userName,String orderName,double orderPrice,int quantity,String street, String city,String state,String zipcode,String creditCardNo, String orderDate, String deliveryDate, String status, String deliverables, String storeaddress)
{
	String msg = "order is inserted successfully";
	try
	{

		getConnection();
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrder(OrderId,storeId,UserName,OrderName,OrderPrice,quantity,street,city,state,zipcode,creditCardNo,orderDate,deliveryDate,status,deliverables,pickupaddress) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setInt(2,1);
		pst.setString(3,userName);
		pst.setString(4,orderName);
		pst.setDouble(5,orderPrice);
		pst.setInt(6,quantity);
		pst.setString(7,street);
		pst.setString(8,city);
		pst.setString(9,state);
    pst.setString(10,zipcode);
		pst.setString(11,creditCardNo);
		//SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Date date = new Date();
		//orderDate = dateFormat.format(date);
    pst.setString(12, orderDate);
		//Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, 14);
		//deliveryDate = dateFormat.format(cal.getTime());
    pst.setString(13, deliveryDate);
		pst.setString(14,status);
    pst.setString(15,deliverables);
		pst.setString(16,storeaddress);
		pst.execute();
		MySqlDataStoreUtilities.Updateproductquantity();
	}
	catch(Exception e)
	{

		msg = "Erro while adding the order";
		e.printStackTrace();

	}
	return msg;
}
public static String deleteOrders(int orderId)
{
	String status = "yes";
	try
	{

		getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT IF(timestampdiff(DAY,current_timestamp(),deliveryDate)>1 ,'yes','no') as status FROM customerorder where orderId='"+orderId+"'");
		while(rs.next()){
			status = rs.getString("status");
			if(status.equals("yes")){
				String deleteOrderQuery ="Delete from customerorder where orderId=?";
				PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
				pst.setInt(1,orderId);
				//pst.setString(2,orderName);
				pst.executeUpdate();
				MySqlDataStoreUtilities.Updateproductquantity();
			}else{

				status ="Cannot delete the order as it is within 5 days of deliverydate";
			}
		}

	}
	catch(Exception e)
	{

	}
	return status;
}


public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();

	try
	{

		getConnection();
        //select the table
		String selectOrderQuery ="select * from customerorder";
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));
			System.out.println("data is"+rs.getInt("OrderId")+orderPayments.get(rs.getInt("OrderId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getInt("quantity"),rs.getString("street"),rs.getString("city"),rs.getString("state"),rs.getString("zipcode"),rs.getString("creditCardNo"),rs.getString("orderDate"),rs.getString("deliveryDate"),rs.getString("status"),rs.getString("deliverables"),rs.getString("pickupaddress"));
			listOrderPayment.add(order);

		}


	}
	catch(Exception e)
	{

	}
	return orderPayments;
}
public static String updateOrders(int orderId,String userName,String orderName,double orderPrice,int quantity,String street, String city,String state,String zipcode,String deliveryDate,String status, String deliverables, String storeaddress)
{
    String msg = "order is updated successfully";
	try{

		getConnection();
		String updateProductQurey = "UPDATE customerOrder SET userName=?,orderName=?,orderPrice=?,quantity=?,street=?,city=?,state=?,zipcode=?,deliveryDate=?,status=?,deliverables=?,storeaddress=? where orderId =?;" ;



			PreparedStatement pst = conn.prepareStatement(updateProductQurey);


			pst.setString(1,userName);
			pst.setString(2,orderName);
			pst.setDouble(3,orderPrice);
			pst.setInt(4,quantity);
			pst.setString(5,street);
			pst.setString(6,city);
			pst.setString(7,state);
	    pst.setString(8,zipcode);
			pst.setString(9,deliveryDate);
			pst.setString(10,status);
			pst.setString(11,deliverables);
			pst.setString(12,storeaddress);
			pst.setInt(13,orderId);
			pst.executeUpdate();



	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();

	}
 return msg;
}
public static void insertfavourites(String id,String userName, String name,String retailer,Double price,Double discount)
{
	try
	{

		getConnection();
		String insertIntoCustomerOrderQuery = "INSERT INTO favourites(id,userName,name,retailer,price,discount) "
		+ "VALUES (?,?,?,?,?,?);";

		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setString(1,id);
		pst.setString(2,userName);
		pst.setString(3,name);
		pst.setString(4,retailer);
		pst.setDouble(5,price);
		pst.setDouble(6,discount);
		pst.execute();
	}
	catch(Exception e)
	{

		e.printStackTrace();

	}
}
public static Map getAllProductSold()
		{

			getConnection();
			Map<OrderItem,String> map= new HashMap<OrderItem,String>();
			String query = "select *,count(*) AS sold FROM customerorder GROUP BY orderName ORDER BY orderName ASC ;";
			try
			{
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				OrderItem order=null;
				while(rs.next())
				{
					order = new OrderItem();
					order.setName(rs.getString("orderName"));
					order.setPrice(rs.getDouble("orderPrice"));
					map.put(order,rs.getString("sold"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return map;
		}

		//Get productDetails
		public static HashMap<String,Products> getData()
		{
			HashMap<String,Products> hm=new HashMap<String,Products>();
			try
			{
				getConnection();
				Statement stmt=conn.createStatement();
				String selectCustomerQuery="select * from  Productdetails";
				ResultSet rs = stmt.executeQuery(selectCustomerQuery);
				while(rs.next())
				{	Products p = new Products(rs.getString("productId"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("ProductType"),rs.getDouble("productDiscount"),rs.getDouble("quantity"));
					hm.put(rs.getString("productId"), p);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return hm;
		}

		//getDaily sales data
		public static TreeMap getDailySales()
			{
				TreeMap<String,String> map= new TreeMap<String,String>();
				String query = "select orderDate,SUM(orderPrice) AS totalSales  from customerorder group by orderDate ORDER BY CAST(orderDate As DATETIME);";
				try
				{
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{

						String purchaseDate = rs.getString("orderDate");
						map.put(purchaseDate,rs.getString("totalSales"));
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return map;
			}

		//Update the productQuantity

		public static void Updateproductquantity(){
			try
			{
				getConnection();
				Map<OrderItem,String> map = new HashMap<OrderItem,String>();
				map = MySqlDataStoreUtilities.getAllProductSold();
				if(map!=null){
					Iterator it = map.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry entry = (Map.Entry) it.next();
						OrderItem key = (OrderItem) entry.getKey();
						String value = (String) entry.getValue();
						double d = Double.parseDouble(value);
						double qnew= 100-d;
						String updateProductQuantityQurey = "UPDATE Productdetails SET quantity=? where productName =?;" ;
						PreparedStatement pst = conn.prepareStatement(updateProductQuantityQurey);
						pst.setDouble(1,qnew);
						pst.setString(2,key.getName());
						pst.executeUpdate();
					}
				}

			}
			catch(Exception e)
			{

			}
		}

		//getAllProducts
		public static HashMap<String,Products> getAllProducts(){
			HashMap<String,Products> hm=new HashMap<String,Products>();
			try
			{
				getConnection();
				String selecttrackers="select * from  Productdetails";
				PreparedStatement pst = conn.prepareStatement(selecttrackers);
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
						Products trk = new Products(rs.getString("productId"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("ProductType"),rs.getDouble("productDiscount"),rs.getDouble("quantity"));
						hm.put(rs.getString("productId"), trk);
						trk.setId(rs.getString("productId"));
				}
			}
			catch(Exception e)
			{
			}
			return hm;

		}

		public static HashMap<String,Products> getAllProductsinSales(){
			HashMap<String,Products> hm=new HashMap<String,Products>();
			try
			{
				getConnection();
				String selecttrackers="SELECT * FROM productdetails where productDiscount > 0;";
				PreparedStatement pst = conn.prepareStatement(selecttrackers);
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
						Products trk = new Products(rs.getString("productId"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("ProductType"),rs.getDouble("productDiscount"),rs.getDouble("quantity"));
						hm.put(rs.getString("productId"), trk);
						trk.setId(rs.getString("productId"));
				}
			}
			catch(Exception e)
			{
			}
			return hm;

		}

		//getallmanufacturerrebate as yes products
		public static HashMap<String,Products> getAllManufactureRebateProducts(){
			HashMap<String,Products> hm=new HashMap<String,Products>();
			try
			{
				getConnection();
				String selecttrackers="select * from  Productdetails where manufacturerRebate=?";
				PreparedStatement pst = conn.prepareStatement(selecttrackers);
				pst.setString(1,"Yes");
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					Products trk = new Products(rs.getString("productId"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("ProductType"),rs.getDouble("productDiscount"),rs.getDouble("quantity"));
					hm.put(rs.getString("productId"), trk);
					trk.setId(rs.getString("productId"));
				}
			}
			catch(Exception e)
			{
			}
			return hm;
		}

		public static String transactions(String loginID,String userName,int age, String occupation,String creditCardNo, int orderId, String orderDate,String deliveryDate,String actualDeliveryDate, String productId, String productName,String category,String manufacturer, int reviewrating, int deliveryTrackingID, String deliverables, String zipcode, String status, String order_Returned, String order_Delivered)
		{
			String msg = "order is inserted successfully";
			try
			{
				System.out.println("in transactions");

				getConnection();
				String insertIntotransactionsQuery = "INSERT INTO transactions(loginID,Customer_Name,Customer_Age,Customer_Occupation,Credit_Card_Number,Order_ID,Order_Date,Expected_Delivery,Actual_Delivery,Product_ID,Product_Name,Category,Manufacturer,Review_Rating,Delivery_Tracking_ID,Delivery_Type, Delivery_Zip_Code,  Transaction_Status, order_Returned,  order_Delivered) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

				PreparedStatement pst = conn.prepareStatement(insertIntotransactionsQuery);
				//set the parameter for each column and execute the prepared statement
				pst.setString(1,loginID);
				pst.setString(2,userName);
				pst.setInt(3,age);
				pst.setString(4,occupation);
				pst.setString(5,creditCardNo);
				pst.setInt(6,orderId);
				pst.setString(7,orderDate);
				pst.setString(8,deliveryDate);
		    pst.setString(9,actualDeliveryDate);
				pst.setString(10,productId);
		    pst.setString(11, productName);
		    pst.setString(12, category);
				pst.setString(13,manufacturer);
		    pst.setInt(14,reviewrating);
				pst.setInt(15,deliveryTrackingID);
				pst.setString(16,deliverables);
				pst.setString(17,zipcode);
				pst.setString(18,status);
				pst.setString(19,order_Returned);
				pst.setString(20,order_Delivered);

				pst.execute();
			}
			catch(Exception e)
			{

				msg = "Erro while adding the transactions";
				e.printStackTrace();

			}
			return msg;
		}

}
