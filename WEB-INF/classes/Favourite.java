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

@WebServlet("/Favourite")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class Favourite extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	  Utilities Utility = new Utilities(request, pw);
		storeFavourites(request, response);
	}

	protected void storeFavourites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
				String userName = session.getAttribute("username").toString();
				String name =request.getParameter("name");
				Double price =Double.parseDouble(request.getParameter("price"));
				Double discount =Double.parseDouble(request.getParameter("discount"));
				String retailer = request.getParameter("maker");
				String id=request.getParameter("id");
				String type = request.getParameter("type");
				MySqlDataStoreUtilities.insertfavourites(id,userName,name,retailer,price,discount);
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
			               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Favourites</h4>"
			           + "</div></div></div>");
				//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

				pw.print("<div class='container' style='padding-top: 30px;'>");
					pw.print("<form name='favourite' action="+type+">");

				try
	         {
				 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "Ilovemyself");

	             Statement stmt = con.createStatement();
	             ResultSet rs = stmt.executeQuery("select * from favourites where userName='"+userName+"'");
							 pw.println("<table border=1 width=50% height=50%>");
	             pw.println("<tr><th>Id</th><th>Name</th><th>Price</th><th>Discount</th><tr>");
	             while (rs.next())
	             {
	                 String id1 = rs.getString("id");
	                 String name1 = rs.getString("name");
	                 double price1 = rs.getDouble("price");
									 double discount1 = rs.getDouble("discount");

	                 pw.println("<tr><td>" + id1 + "</td><td>" + name1 + "</td><td>" + price1 + "</td><td>" + discount1 + "</td><td><input type='submit' name='Buy' value='Buy' class='btnbuy'><td></tr>");
	             }
	             pw.println("</table>");
				 		 		con.close();

	            }
	             catch (Exception e)
	            {

	            }
							pw.print("</div></div></form>");


		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String type = request.getParameter("type");
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
								 + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Favourites</h4>"
						 + "</div></div></div>");
		//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

		pw.print("<div class='container' style='padding-top: 30px;'>");
			pw.print("<form name='favourite' action="+type+">");

		try
			 {
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "Ilovemyself");

					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery("select * from favourites");
					 pw.println("<table border=1 width=50% height=50%>");
					 pw.println("<tr><th>Id</th><th>Name</th><th>Price</th><th>Discount</th><tr>");
					 while (rs.next())
					 {
							 String id1 = rs.getString("id");
							 String name1 = rs.getString("name");
							 double price1 = rs.getDouble("price");
							 double discount1 = rs.getDouble("discount");

							 pw.println("<tr><td>" + id1 + "</td><td>" + name1 + "</td><td>" + price1 + "</td><td>" + discount1 + "</td><td><input type='submit' name='Buy' value='Buy' class='btnbuy'><td></tr>");
					 }
					 pw.println("</table>");
						con.close();

					}
					 catch (Exception e)
					{

					}
					pw.print("</div></div></form>");


	    }
}
