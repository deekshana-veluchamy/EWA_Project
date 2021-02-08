import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.sql.*;

@WebServlet("/ViewOrder")

public class ViewOrder extends HttpServlet {

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

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession(true);
		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin()){

			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("CustomerSignIn");
			return;
		}
		String username=utility.username();
		String deliveryDate = utility.expectedDeliveryDate();
    String currentDate = utility.currentDate();
    long diffdays=0;

		utility.printHtml("Header.html");
		//utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");


		/*check if the order button is clicked
		if order button is not clicked that means the view order page is visited freshly
		then user will get textbox to give order number by which he can view order
		if order button is clicked user will be directed to this same servlet and user has given order number
		then this page shows all the order details*/

		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");
		}

		//hashmap gets all the order details from file

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();


		String message=MySqlDataStoreUtilities.getConnection();
		if(message.equals("Successfull"))
		{
		/*if order button is clicked that is user provided a order number to view order
		order details will be fetched and displayed in  a table
		Also user will get an button to cancel the order */

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
			if (request.getParameter("orderId") != null)
			{
				int orderId=Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				//get the order details from file
				try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();

				}
				catch(Exception e)
				{

				}
				int size=0;


				/*get the order size and check if there exist an order with given order number
				if there is no order present give a message no order stored with this id */

				if(orderPayments.get(orderId)!=null)
				{
				for(OrderPayment od:orderPayments.get(orderId))
				if(od.getUserName().equals(username))
				size= orderPayments.get(orderId).size();
				}
				// display the orders if there exist order with order id
				if(size>0)
				{

					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td></tr>");
					for (OrderPayment oi : orderPayments.get(orderId))
					{
						pw.print("<tr>");
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");
						pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
						pw.print("<td><input type='submit' name='Order' value='viewStatus' class='btnbuy'></td>");
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						pw.print("</tr>");

					}
					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			}else

			{
				pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");
			}
		}


		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("viewStatus")){
			int orderId=Integer.parseInt(request.getParameter("orderId"));
			try{
				getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from customerorder where OrderId='"+orderId+"'");
				pw.print("<h2>Order Status:</h2>");
				pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
				pw.print(username);
				pw.print("</td></tr>");

		             while (rs.next())
		             {
		                int orderId1 = rs.getInt("OrderId");
		                String deliverables = rs.getString("deliverables");
										String orderName = rs.getString("orderName");
										String status = rs.getString("status");
										pw.print("<tr><td>Order Id:</td><td>");
							 			pw.print(orderId1);
							 			pw.print("</td></tr>");
										pw.print("<tr><td>Order Name:</td><td>");
							 			pw.print(orderName);
										pw.print("</td></tr>");
										pw.print("<tr><td>Order Status:</td><td>");
							 			pw.print(status);
										pw.print("</td></tr>");
										if(deliverables.equals("pickup")){
											String pickupdate = rs.getString("deliveryDate");
											String pickupaddress = rs.getString("pickupaddress");
											pw.print("<tr><td>Expected Pickup Date:</td><td>");
								 			pw.print(pickupdate);
								 			pw.print("</td></tr>");
											pw.print("<tr><td>Pickup Store Address:</td><td>");
								 			pw.print(pickupaddress);

										}
										if(deliverables.equals("delivery")){
											String edeliveryDate = rs.getString("deliveryDate");
											String street = rs.getString("street");
											String city = rs.getString("city");
											String state = rs.getString("state");
											String zipcode = rs.getString("zipcode");
											pw.print("<tr><td>Expected delivery Date:</td><td>");
								 			pw.print(edeliveryDate);
								 			pw.print("</td></tr>");
											pw.print("<tr><td>Delivery Street:</td><td>");
								 			pw.print(street);
								 			pw.print("</td></tr>");
											pw.print("<tr><td>Delivery city:</td><td>");
								 			pw.print(city);
								 			pw.print("</td></tr>");
											pw.print("<tr><td>Delivery state:</td><td>");
								 			pw.print(state);
								 			pw.print("</td></tr>");
											pw.print("<tr><td>Delivery Zipcode:</td><td>");
								 			pw.print(zipcode);

										}
		             }
								 pw.print("</td></tr></table></form>");
								 pw.print("</div></div></div>");

		             conn.close();
		      }
		      catch (Exception e)
		       {
		        pw.println("error");
		        }
		}

		//if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{
			String orderName=request.getParameter("orderName");
			if(request.getParameter("orderName") != null)
			{
				int orderId=0;
				orderId=Integer.parseInt(request.getParameter("orderId"));
				ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
				//get the order from file
				try
				{

					orderPayments=MySqlDataStoreUtilities.selectOrder();

				}
				catch(Exception e)
				{

				}
				//get the exact order with same ordername and add it into cancel list to remove it later
				for (OrderPayment oi : orderPayments.get(orderId))
					{
							if(oi.getOrderName().equals(orderName))
							{
								MySqlDataStoreUtilities.deleteOrders(orderId);
								ListOrderPayment.add(oi);
								pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
							}
					}
				//remove all the orders from hashmap that exist in cancel list
				orderPayments.get(orderId).removeAll(ListOrderPayment);
				if(orderPayments.get(orderId).size()==0)
					{
							orderPayments.remove(orderId);
					}
			}else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
	}
	else
	{pw.print("<h2>My Sql server is not up and running</h2>");

	}
		pw.print("</form></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
