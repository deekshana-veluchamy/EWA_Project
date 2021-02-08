import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

@WebServlet("/Utilities")

/*
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.

*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session;
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument,
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result;
			}
			else
				//result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				//result = result +"<div class='navbar navbar-inverse navbar-fixed-top' role='navigation' style='background-color: black; height:77px;'><div class='container-fluid'>";
				result = result;
				pw.print(result);
		} else
				pw.print(result);
	}


	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		}
		catch (Exception e) {
		}
		return result;
	}

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}

	/*  isLoggedin Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/

	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}

	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}

	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}
		User user = hm.get(username());
		return user;
	}

	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>();
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
/*	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\EWA_Project\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{

			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=size + 1;

			}
			return size;
	}*/

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
			return getCustomerOrders().size();
		return 0;
	}

	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("fvs")){
			FruitsVeggies fv;
			fv = SaxParserDataStore.fvs.get(name);
			OrderItem orderitem = new OrderItem(fv.getName(), fv.getPrice(), fv.getImage(), fv.getRetailer(),fv.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("grocerys")){
			Grocery grocery = null;
			grocery = SaxParserDataStore.grocerys.get(name);
			OrderItem orderitem = new OrderItem(grocery.getName(), grocery.getPrice(), grocery.getImage(), grocery.getRetailer(),grocery.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("dairies")){
			Dairy dairy = null;
			dairy = SaxParserDataStore.dairies.get(name);
			OrderItem orderitem = new OrderItem(dairy.getName(), dairy.getPrice(), dairy.getImage(), dairy.getRetailer(),dairy.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("beverage")){
			Beverages beverages = null;
			beverages = SaxParserDataStore.beverage.get(name);
			OrderItem orderitem = new OrderItem(beverages.getName(), beverages.getPrice(), beverages.getImage(), beverages.getRetailer(),beverages.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("frozens")){
			Frozen frozen = null;
			frozen = SaxParserDataStore.frozens.get(name);
			OrderItem orderitem = new OrderItem(frozen.getName(), frozen.getPrice(), frozen.getImage(), frozen.getRetailer(),frozen.getDiscount());
			orderItems.add(orderitem);
		}

	}

	public String currentDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -6);
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = format1.format(cal.getTime());
		return currentDate;
		//SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Date date = new Date();
		//return dateFormat.format(date).toString();
	}

	public String shipDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -4);
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		String deliveryDate = format1.format(cal.getTime());
		return deliveryDate;
	}

	public String expectedDeliveryDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		String deliveryDate = format1.format(cal.getTime());
		return deliveryDate;
	}
	public String actualDeliveryDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		String actualDate = format1.format(cal.getTime());
		return actualDate;
	}

	// store the payment details for orders
	public void storePayment(int orderId,String userName,String orderName,double orderPrice,int quantity,String street, String city,String state,String zipcode,String creditCardNo, String orderDate, String deliveryDate, String status, String deliverables, String storeaddress){
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		// get the payment details file
				try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{

				}
				if(orderPayments==null)
				{
					orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
				}
					// if there exist order id already add it into same list for order id or create a new record with order id

				if(!orderPayments.containsKey(orderId)){
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(orderId, arr);
				}
				ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);
				OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,quantity,street,city,state,zipcode,creditCardNo,currentDate(),expectedDeliveryDate(),status,deliverables,storeaddress);
				listOrderPayment.add(orderpayment);

					// add order details into database
				try
				{
					MySqlDataStoreUtilities.insertOrders(orderId,userName,orderName,orderPrice,quantity,street,city,state,zipcode,creditCardNo,orderDate,deliveryDate,status,deliverables,storeaddress);
				}
				catch(Exception e)
				{
					System.out.println("inside exception file not written properly");
				}
			}


	/* gettvs Functions returns the Hashmap with all tvs in the store.*/

	public HashMap<String, FruitsVeggies> getFruitsVeggies(){
			HashMap<String,FruitsVeggies> hm = new HashMap<String,FruitsVeggies>();
			hm.putAll(SaxParserDataStore.fvs);
			return hm;
	}

	/* getlaptops Functions returns the  Hashmap with all laptop in the store.*/

	public HashMap<String, Grocery> getGrocerys(){
			HashMap<String,Grocery> hm = new HashMap<String,Grocery>();
			hm.putAll(SaxParserDataStore.grocerys);
			return hm;
	}

	/* getPhones Functions returns the Hashmap with all phone in the store.*/

	public HashMap<String, Dairy> getDairies(){
			HashMap<String,Dairy> hm = new HashMap<String,Dairy>();
			hm.putAll(SaxParserDataStore.dairies);
			return hm;
	}

	public HashMap<String, Beverages> getBeverages(){
			HashMap<String,Beverages> hm = new HashMap<String,Beverages>();
			hm.putAll(SaxParserDataStore.beverage);
			return hm;
	}

	public HashMap<String, Frozen> getFrozens(){
			HashMap<String,Frozen> hm = new HashMap<String,Frozen>();
			hm.putAll(SaxParserDataStore.frozens);
			return hm;
	}

	public String storeReview(String userid,String productname,String producttype,String productprice,String retailername,String manufacturername,String reviewrating,String userage,String usergender,String useroccupation,String productonsale,String manufacturerrebate,String zipcode,String retailercity,String retailerstate,String reviewdate,String reviewtext){
		String message=MongoDBDataStoreUtilities.insertReview(userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext);
			if(!message.equals("Successfull"))
			{
				return "UnSuccessfull";
			}
			else
			{
				HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
				try
				{
					reviews=MongoDBDataStoreUtilities.selectReview();
				}
				catch(Exception e)
				{

				}
				if(reviews==null)
				{
					reviews = new HashMap<String, ArrayList<Review>>();
				}

				// if there exist product review already add it into same list for productname or create a new record with product name

				if(!reviews.containsKey(productname)){
					ArrayList<Review> arr = new ArrayList<Review>();
					reviews.put(productname, arr);
				}
				ArrayList<Review> listReview = reviews.get(productname);
				Review review = new Review(userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext);
				listReview.add(review);
				// add Reviews into database
				return "Successfull";
			}
		}

}
