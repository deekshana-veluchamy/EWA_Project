import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.*;
import java.sql.*;

@WebServlet("/Summary")

public class Summary extends HttpServlet {

	static Connection conn = null;

	public static void getConnection()
	{

		try
		{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub","root","Ilovemyself");
		}
		catch(Exception e)
		{

		}
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		Utilities utility = new Utilities(request, pw);
		HttpSession session = request.getSession(true);
		if(!utility.isLoggedin())
		{

			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("CustomerSignIn");
			return;
		}

		// get the payment details like credit card no address processed from cart servlet
		String street =request.getParameter("street");
		String city =request.getParameter("city");
		String state =request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String creditCardNo=request.getParameter("creditCardNo");
		String orderDate = utility.currentDate();
		String deliveryDate = utility.expectedDeliveryDate();
		String shipDate = utility.shipDate();
		String deliverables = request.getParameter("deliverables");
		double discount = Double.parseDouble(request.getParameter("orderDiscount"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double orderTotal = Double.parseDouble(request.getParameter("orderTotal"));
		String userName = session.getAttribute("username").toString();
		String Customer_Name = request.getParameter("Name");
		String pickup_location = request.getParameter("loc");
		String productId = request.getParameter("name");
		String category = request.getParameter("type");
		String status = "Order placed";
		double shipping = 30;
		System.out.print("the user address is" +zipcode);
		System.out.print(creditCardNo);
		if(!zipcode.isEmpty() && !creditCardNo.isEmpty() )
		{
			Random rand = new Random();
			int orderId = rand.nextInt(100);
			//int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order
		String message=MySqlDataStoreUtilities.getConnection();
		if(message.equals("Successfull"))
		{

			for (OrderItem oi : utility.getCustomerOrders())
			{

				//set the parameter for each column and execute the prepared statement

				utility.storePayment(orderId,userName,oi.getName(),oi.getPrice(),quantity,street,city,state,zipcode,creditCardNo,orderDate,deliveryDate,status,deliverables,pickup_location);
				try
			{
				getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs1 = stmt.executeQuery("select * from customers where userName='"+userName+"'");
				while(rs1.next()){
					int age = rs1.getInt("Age");
					String occupation = rs1.getString("Occupation");
					int deliveryTrackingID = rand.nextInt(10000);
					int order_Returnedrandom = rand.nextInt(1);
					int reviewrating = rand.nextInt(5)+1;
					String[] status1 = {"Approved","Disputed"};
					int orderstatus1 = rand.nextInt(status1.length);
					String orderStatus = status1[orderstatus1];
					String actualdeliveryDate = utility.actualDeliveryDate();
					String order_Delivered;
					String order_Returned;
					if(order_Returnedrandom == 0){
						order_Returned = "No";
					}else{
						order_Returned = "Yes";
					}
					if(actualdeliveryDate.equals(deliveryDate))
						order_Delivered = "Yes";
					else
						order_Delivered = "No";
					MySqlDataStoreUtilities.transactions(userName, Customer_Name, age, occupation, creditCardNo, orderId, orderDate, deliveryDate, actualdeliveryDate,productId, oi.getName(), category,  oi.getRetailer(), reviewrating,deliveryTrackingID,deliverables,zipcode,orderStatus,order_Returned,order_Delivered);
				}
			}catch(Exception e)
			{

			}
			}
			/*try{
				MySqlDataStoreUtilities.customer(userName,street,city,state,zipcode);
			}catch(Exception e)
			{

			}*/



			//remove the order details from cart after processing


			utility.printHtml("Header.html");
			//utility.printHtml("LeftNavigationBar.html");
			double user_id=Math.random();
			long userId=Math.round(user_id*100);
			pw.print("<div id='container'>");
			pw.print("<br />");
			pw.print("<br />");
			pw.print("<br />");

			if(utility.isLoggedin()){
			pw.print("<div class='container-fluid' style='margin-top: 60px;'>"
							+ "<div class='col-sm-2' style='background-color: black; height: 690px;'>"
			+ "<center>"
			+ "<a href='CustomerProfile'> <p style='color:white; margin-top:19px;  font-family:Lucida Sans; font-size:25px;'>DASHBOARD</p></a>"
							+ "<div style='height: 3px; width: 100%;background-color: white;'></div>"
							+ "<img src='images/Profile/man1.jpg' style='margin-top:65px; height:100px; width:100px;' class='img-circle'/>"
							+ "<center><p style='color:white; font-family:Lucida Sans; font-size:19px; margin-top:5px;'>Hi John</p></center>"
			+ "</center>"
			+ "<div style='margin-top: 35px;'></div>"
			+ "<form action='CustomerProfile'>"
			+ "<input type='submit' class='btnbuy' value='PROFILE' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
			+ "</form>"
			+ "<form action='ShowStores'>"
			+ "<input type='submit' class='btnbuy submit-button'  value='HOME' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
			+ "</form>"
			+ "<form action='Favourite'>"
			+ "<input type='submit' class='btnbuy'  value='FAVOURITE' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
			+ "</form>"
			+ "<form action='History'>"
			+ "<input type='submit' class='btnbuy'  value='HISTORY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
			+ "</form>"
			+ "<form action='Logout'>"
			+ "<input type='submit' class='btnbuy'  value='LOGOUT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
			+ "</form>"
			+ "</div>");}
			pw.print("<div class='text-center' >"

			+ "</div>");
			pw.print("<div style='margin-top: 20px;'>"
		        + "<div class='media' style='margin-left: 540px;'>"
		           + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
		               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Account</h4>"
		           + "</div></div></div>");
			//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

			pw.print("<div class='container' style='padding-top: 30px;'>");


			pw.print("<form name='location' action='History'>");

			pw.print("<div class='entry'>");
			pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
			pw.print(userName);
			pw.print("</td></tr>");
			pw.print("<tr><td>User Id:</td><td>");
			pw.print(userId);
			pw.print("</td></tr>");
			pw.print("<tr><td>Order Id:</td><td>");
			pw.print(orderId);
			pw.print("</td></tr>");
			// for each order iterate and display the order name price
			for (OrderItem oic : utility.getCustomerOrders())
			{

				pw.print("<tr><td> Product Purchased:</td><td>");
				pw.print(oic.getName()+"</td></tr><tr><td>");
				pw.print("Product Id</td><td>");
				pw.print(productId+"</td></tr><tr><td>");
				pw.print("Category</td><td>");
				pw.print(category+"</td></tr><tr><td>");
				pw.print("Discount</td><td>");
				pw.print(oic.getDiscount()+"</td></tr><tr><td>");
				pw.print("Quantity:</td><td>");
				pw.print(quantity+"</td></tr><tr><td>");
				pw.print("Product Price:</td><td>"+ oic.getPrice() * quantity);
				pw.print("</td></tr>");
			}

		pw.print("<tr><td>Purchase Date:</td><td>");
		pw.print(orderDate);
		pw.print("</td></tr>");
		if(deliverables.equals("delivery")){
			pw.print("<tr><td>");
			pw.println("<input type='hidden' name='deliverables' value='delivery'>");
			pw.print("Shipping Cost:</td><td>"+shipping);
			pw.print("<tr><td>Ship Date:</td><td>");
			pw.print(shipDate);
			pw.print("</td></tr>");
			pw.print("<tr><td>Delivery Date:</td><td>");
			pw.print(deliveryDate);
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total_Sales(Total Cost)</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>creditCardNo:</td><td>");
		pw.print(creditCardNo);
		pw.print("</td></tr>");
		pw.print("<tr><td>Customer Street:</td><td>");
		pw.print(street);
		pw.print("</td></tr>");
		pw.print("<tr><td>Customer city:</td><td>");
		pw.print(city);
		pw.print("</td></tr>");
		pw.print("<tr><td>Customer state:</td><td>");
		pw.print(state);
		pw.print("</td></tr>");
		pw.print("<tr><td>Zipcode:</td><td>");
		pw.print(zipcode);
		pw.print("</td></tr>");
		if(deliverables.equals("pickup")){
			pw.print("<tr><td>Pickup Date:</td><td>");
			pw.print(deliveryDate);
			pw.print("</td></tr>");

			try
			{
				getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from pickupaddress where Address='"+pickup_location+"'");
				while(rs.next()){
					int storeid = rs.getInt("Store_ID");
					String pickuploc = rs.getString("pickuploc");
					String pickupcity = rs.getString("pickupcity");
					String pickupstate = rs.getString("pickupstate");
					String pickupzip = rs.getString("pickupzip");
					pw.print("<tr><td>Store ID:</td><td>");
					pw.print(storeid);
					pw.print("</td></tr>");
					pw.print("<tr><td>Pickup Store Name:</td><td>");
					pw.print(pickuploc);
					pw.print("</td></tr>");
					pw.print("<tr><td>Pickup Store City:</td><td>");
					pw.print(pickupcity);
					pw.print("</td></tr>");
					pw.print("<tr><td>Pickup Store State:</td><td>");
					pw.print(pickupstate);
					pw.print("</td></tr>");
					pw.print("<tr><td>Pickup Store Zipcode:</td><td>");
					pw.print(pickupzip);
					pw.print("</td></tr>");
				}
			}
			catch(Exception e)
			{

			}
		}
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
			}
			else
			{pw.print("<h2>My Sql server is not up and running</h2>");

			}
				pw.print("</div></div></div></div>");
				OrdersHashMap.orders.remove(utility.username());

			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			//utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);


	}
}
