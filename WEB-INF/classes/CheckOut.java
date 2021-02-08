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
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	  Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}

	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);

		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("CustomerSignIn");
			return;
		}
        HttpSession session=request.getSession();

		//get the order product details	on clicking submit the form will be passed to submitorder page
		if(request.getParameter("remove") != null){
			for (OrderItem oi : utility.getCustomerOrders())
			{
			OrdersHashMap.orders.remove(utility.username());
			response.sendRedirect("Cart");
		}
		}



	    String userName = session.getAttribute("username").toString();
			String orderType = request.getParameter("deliverables");
			double total = Double.parseDouble(request.getParameter("orderTotal"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double shipping = 30;
			double orderTotal = 0;
			for (OrderItem oi1 : utility.getCustomerOrders())
			{
			if(orderType.equals("delivery")){
				orderTotal = total + shipping + (oi1.getPrice() * quantity);
			}else if(orderType.equals("pickup")){
				orderTotal = total + (oi1.getPrice() * quantity);
			}
		}
		String name = request.getParameter("name");

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
			+ "<form action='Trending'>"
			+ "<input type='submit' class='btnbuy'  value='TRENDING' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
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
		               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'></h4>"
		           + "</div></div></div>");

			//utility.printHtml("LeftNavigationBar.html");
			pw.print("<div class='container' style='padding-top: 30px;'>");
			pw.print("<form name ='CheckOut' action='Summary' method='post'>");
	        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
			pw.print(userName);
			pw.print("</td></tr>");
			// for each order iterate and display the order name price
			for (OrderItem oi : utility.getCustomerOrders())
			{
				pw.print("<tr><td> Product Purchased:</td><td>");
				pw.print(oi.getName()+"</td></tr><tr><td>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderDiscount' value='"+oi.getDiscount()+"'>");
				pw.print("<input type='hidden' name='quantity' value='"+quantity+"'>");
				pw.print("Quantity:</td><td>");
				pw.print(quantity+"</td></tr><tr><td>");
				pw.print("Discount:</td><td>");
				pw.print(oi.getDiscount()+"</td></tr><tr><td>");
				pw.print("Product Price:</td><td>"+ oi.getPrice() * quantity);
				pw.print("</td></tr>");
			}

		if(orderType.equals("delivery")){
			pw.print("<tr><td>");
			pw.println("<input type='hidden' name='deliverables' value='delivery'>");
			pw.print("Shipping Cost:</td><td>"+shipping);
			pw.print("</td></tr>");
		}

		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("<input type='hidden' name='name' value='"+name+"'>"+
				"<input type='hidden' name='type' value='tvs'>");
		pw.print("</td></tr></table><table><tr></tr><tr></tr>");
		pw.print("<tr>");
			pw.print("<td> Name: </td>");
			pw.print("<td> <input type='text' name='Name'> </td>");
			pw.print("</tr>");
			if(orderType.equals("delivery")){
				pw.print("<h2>Delivery Address</h2>");
			}else{
				pw.print("<h2>Customer Address</h2>");
			}
			pw.print("<tr>");
	    pw.print("<td> Customer Street</td>");
			pw.print("<td><input type='text' name='street'> </td>");
      pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td>Customer city</td>");
			pw.print("<td><input type='text' name='city'></td>");
      pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td>Customer state</td>");
			pw.print("<td><input type='text' name='state'></td>");
      pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> Zipcode: </td>");
			pw.print("<td> <input type='text' name='zipcode'> </td>");
			pw.print("</tr>");
			pw.print("<tr>");
		pw.print("<tr><td>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		if(orderType.equals("pickup")){
			pw.println("<td><input type='hidden' name='deliverables' value='pickup'></td>");
			pw.print("<td>Select the pickup location</td><td><select name='loc' class='input'><option value='Whole Foods Market,1 N Halsted St, Chicago, IL 60661' selected>Whole Foods Market,1 N Halsted St, Chicago, IL 60661</option><option value='Trader Joes,667 W Diversey Pkwy, Chicago, IL 60614'>Trader Joe's,667 W Diversey Pkwy, Chicago, IL 60614</option><option value='Walmart Neighborhood Market,4720 S Cottage Grove Ave, Chicago, IL 60615'>Walmart Neighborhood Market,4720 S Cottage Grove Ave, Chicago, IL 60615</option><option value='Petes Fresh Market,2333 W Madison St, Chicago, IL 60612'>Pete's Fresh Market,2333 W Madison St, Chicago, IL 60612</option><option value='Marianos,1615 S Clark St, Chicago, IL 60616'>Mariano's,1615 S Clark St, Chicago, IL 60616</option><option value='Patel Brothers,2610 W Devon Ave, Chicago, IL 60659'>Patel Brothers,2610 W Devon Ave, Chicago, IL 60659</option><option value='Food 4 Less,112 W 87th St, Chicago, IL 60620'>Food 4 Less,112 W 87th St, Chicago, IL 60620</option><option value='Park To Shop Supermarket,2425 S Wallace St, Chicago, IL 60616'>Park To Shop Supermarket,2425 S Wallace St, Chicago, IL 60616</option><option value='Fresh Thyme Farmers Market,2500 N Elston Ave, Chicago, IL 60647'>Fresh Thyme Farmers Market,2500 N Elston Ave, Chicago, IL 60647</option><option value='Fresh Market Place,2134 N Western Ave, Chicago, IL 60647'>Fresh Market Place,2134 N Western Ave, Chicago, IL 60647</option></select></td>");
		}

		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div></div>");

		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	    }
}
