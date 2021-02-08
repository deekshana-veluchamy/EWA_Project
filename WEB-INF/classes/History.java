import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/History")

public class History extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
    {
      response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("CustomerSignIn");
				return;
			}
			HttpSession session=request.getSession();
			utility.printHtml("Header.html");
			//utility.printHtml("LeftNavigationBar.html");
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
							+ "<center><p style='color:white; font-family:Lucida Sans; font-size:19px; margin-top:5px;'>Owner's Name</p></center>"
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
			//pw.print("<div id='content'><div class='post'>");
			//pw.print("<a style='font-size: 24px;'>Account</a>");
			pw.print("<div class='entry'>");
			User user=utility.getUser();
			pw.print("<table class='gridtable'>");
			pw.print("<tr>");
			pw.print("<td> User Name: </td>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
			pw.print("</tr>");
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
					/*FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment_1\\PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
					orderPayments = (HashMap)objectInputStream.readObject();*/
				}
			catch(Exception e)
				{

				}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment od:entry.getValue())
					if(od.getUserName().equals(user.getName()))
					size= size+1;
				}

			if(size>0)
				{

					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td></tr>");
					for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
					{
						for(OrderPayment oi:entry.getValue())
						if(oi.getUserName().equals(user.getName()))
						{
							pw.print("<form method='get' action='ViewOrder'>");
							pw.print("<tr>");
							pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");
							pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
							pw.print("<td><input type='hidden' name='orderId' value='"+oi.getOrderId()+"'></td>");
							//pw.print("<td><input type='submit' name='warranty' value='Buy Warranty' class='btnbuy'></td>");
							pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
							pw.print("</tr>");
							pw.print("</form>");
						}

					}

					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}

			pw.print("</table>");
			pw.print("</h2></div></div></div></div>");


			utility.printHtml("Footer.html");
		}
		catch(Exception e)
		{
		}
	}
}
