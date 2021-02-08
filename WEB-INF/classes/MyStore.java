import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/MyStore")

public class MyStore extends HttpServlet {
	String uname="",pwd="";
	String fname="",lname="",mobile="",strret="",city="",zipcode="",sname="",smobile="",semail="",sstreet="",scity="",szipcode="",username="",password="",simage="";
		int SID=0;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);


        uname = "test";
		pwd = request.getParameter("password");

	    try
         {
			 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "Ilovemyself");

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from stores where username = '"+uname+"'");
             while (rs.next())
             {
				SID = rs.getInt("SID");
            	fname = rs.getString("fname");
				lname = rs.getString("lname");
				mobile  = rs.getString("mobile");
				strret  = rs.getString("strret");
				city  = rs.getString("city");
				zipcode  = rs.getString("zipcode");
				sname  = rs.getString("sname");
				smobile  = rs.getString("smobile");
				semail  = rs.getString("semail");
				sstreet  = rs.getString("sstreet");
				scity  = rs.getString("scity");
				szipcode  = rs.getString("szipcode");
				username  = rs.getString("username");
				password  = rs.getString("password");
				simage  = rs.getString("simage");
             }
			 con.close();
			 request.setAttribute("SID",SID);
			RequestDispatcher rd = request.getRequestDispatcher("AddProduct");
			RequestDispatcher rd1 = request.getRequestDispatcher("AddProductCode");
			//rd.forward(request,response);
            }
             catch (Exception e)
            {

            }


		displaystore(request, response, pw, false);
    }


    protected void displaystore(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);

		utility.printHtml("RetailerHeader.html");
		pw.print("<br></br>");
		//utility.printHtml("LeftNavigationBar.html");
		// pw.print("</div>");
		// pw.print("<div class='post' style='float: none; width: 100%'>");

        pw.print("<div class='container-fluid' style='margin-top: 60px;'>"
                + "<div class='col-sm-2' style='background-color: black; height: 690px;'>"
				+ "<center>"
				+ "<a href='MyStore'> <p style='color:white; margin-top:19px;  font-family:Lucida Sans; font-size:25px;'>DASHBOARD</p></a>"
                + "<div style='height: 3px; width: 100%;background-color: white;'></div>"
                + "<img src='images/Profile/man1.jpg' style='margin-top:65px; height:130px; width:130px;' class='img-circle'/>"
                + "<center><p style='color:white; font-family:Lucida Sans; font-size:19px; margin-top:5px;'>Hello Retailer</p></center>"
				+ "</center>"
				+ "<div style='margin-top: 35px;'></div>"
				+ "<form action='MyStore'><input type='submit' class='btnbuy' action='MyStore'  value='MY STORE' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddBrand'><input type='submit' class='btnbuy submit-button'  value='BRAND' action='/AddBrand' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddCategory'><input type='submit' class='btnbuy'  value='CATEGORY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='SUB CATEGORY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='QUANTITY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddProduct'><input type='submit' class='btnbuy'  value='ADD PRODUCT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='Home'><input type='submit' class='btnbuy'  value='LOGOUT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "</div>"

				+ "<div class='container' style='margin-top: 20px;'>"
				+ "<div class='col-sm-10'>"
				+ "<div class='col-sm-4 box4' style='margin-left: 20px;'>"
				+ "<div class='col-md-6'>"
				+ "<img src='images/Retailer_Dashboard/sales.png' style='height: 120px; margin-left: 0px; width: 120px; margin-top: 20px;' class='img-circle' />"
				+ "</div>"
				+ "<div class='col-md-6'>"
				+ "<center><p style='font-size:27px; color:black; font-family: Arial; padding-top:15px;'>Sales</p></center>"
				+ " <center><p style='font-size:24px; color:#AD2A0D;''>0%</p></center>"
				+ "<img src='images/Retailer_Dashboard/uparrow.png' style='height: 40px; width: 40px; margin-left: 23px;' />"
				+ "</div>"
				+ "</div>"
				+ "<div class='col-sm-4 box4' style='margin-left: 20px;'>"
				+ "<div class='col-md-6'>"
				+ "<img src='images/Retailer_Dashboard/cart.png' style='height: 120px; margin-left: 0px; width: 120px; margin-top: 20px;' />"
				+ "</div>"
				+ "<div class='col-md-6'>"
				+ "<center><p style='font-size:25px; color:black; font-family: Arial; padding-top:15px;'>Total Orders</p></center>"
				+ "<center><p style='font-size:24px; color:#AD2A0D;'>0</p></center>"
				+ "</div>"
				+ "</div>"
				+ "<div class='col-sm-4 box4' style='margin-left: 20px;'>"
				+ "<div class='col-md-6'>"
				+ "<img src='images/Retailer_Dashboard/revenue.png' style='height: 120px; padding-right: 10px; width: 150px; margin-top: 20px;' />"
				+ "</div>"
				+ "<div class='col-md-6'>"
				+ "<center><p style='font-size:29px; color:black; font-family: Arial; padding-top:15px;'>Revenue</p></center>"
				+ "<center><p style='font-size:24px; color:#AD2A0D;'>$ 0</p></center>"
				+ "</div>"
				+ "</div>"
				+ "<div class='col-sm-4 box4' style='margin-left: 20px;'>"
				+ "<div class='col-md-6'>"
				+ "<img src='images/Retailer_Dashboard/id.png' style='height: 120px; padding-right: 10px; width: 150px; margin-top: 20px;' />"
				+ "</div>"
				+ "<div class='col-md-6'>"
				+ "<center><p style='font-size:29px; color:black; font-family: Arial; padding-top:15px;'>Store Id</p></center>"
				+ "<center><p style='font-size:24px; color:#AD2A0D;'>"+SID+"</p></center>"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "<hr />"
				+ "<div class='container-fluid' style='margin-top: 20px;'>"
				+ "<div class='col-md-5 box5' >"
				+ "<img src='images/Retailer_Dashboard/green_grocer.jpg' style='height: 200px; width: 200px; margin-left: 30px; margin-top: 20px;' class='img-circle' />"
				+ "<center><p style='color:white; font-size:31px; font-family: calibri;'>"+sname+"</p></center>"
				+ "</div>"
				+ "<div class='col-md-7 container-fluid'>"
				+ "<div class='panel panel-default'>"
				+ "<div class='panel-heading panel-heading-black' style='background: black;color: whitesmoke;'>Information</div>"
				+ "</div>"
				+ "<div class='col-md-6 box6' style='margin-left: 0px;'>"
				+ "<img src='images/Retailer_Dashboard/address.png' style='height: 80px; width: 80px; margin-left: 40px; margin-top: 10px;' />"
				+ "<center><p style='font-size:20px; font-family: calibri; color:white; margin-top:10px;'>"+sstreet+","+scity+","+szipcode+"</p></center>"
				+ "</div>"
				+ "<div class='col-md-6 box7' style='margin-left: 0px; margin-top:0px;'>"
				+ "<img src='images/Retailer_Dashboard/items.png' style='height: 80px; width: 80px; margin-left: 40px; margin-top: 10px;' />"
				+ "<center><p style='font-size:20px; font-family: Arial; color:black; margin-top:10px; margin-left:20px;'>Total Items</p></center>"
				+ "<center><p style='font-size:20px; font-family: Arial; color:black; margin-top:10px; margin-left:20px;'>12</p></center>"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "<div>"
				+ "<div class='panel panel-default' style='margin-top: 20px;'>"
				+ "<div class='panel-heading panel-heading-black' style='background: black;color: whitesmoke;'>Total Items</div>"
				+ "</div>"
				+ "<div>"
				+ "<img src='images/Retailer_Dashboard/potato.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "<img src='images/Retailer_Dashboard/carret.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "<img src='images/Retailer_Dashboard/onion.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "<img src='images/Retailer_Dashboard/tomato.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "<img src='images/Retailer_Dashboard/spinach.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "<img src='images/Retailer_Dashboard/brocoli.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "<img src='images/Retailer_Dashboard/cabage.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "<img src='images/Retailer_Dashboard/pea.jpg' class='img-circle' style='height: 100px; width: 100px;' />"
				+ "</div>"
				+ "</div>"
				+ "<!------Table Starts------------->"
				+ "<div style='margin-top: 40px;'>"
				+ "<table class='table table-head-dark table-responsive table-bordered'>"
				+ "<thead>"
				+ "<tr>"
				+ "<th scope='col' style='background-color: black; color: white;'>#</th>"
				+ "<th scope='col' style='background-color: black; color: white;'>Item Name</th>"
				+ "<th scope='col' style='background-color: black; color: white;'>Item Quantity Available (lb)</th>"
				+ "<th scope='col' style='background-color: black; color: white;'>Item Price (Kg)</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>"
				+ "<tr>"
				+ "<th scope='row'>1</th>"
				+ "<td>Potato</td>"
				+ "<td>12 </td>"
				+ "<td>₹ 10</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<th scope='row'>2</th>"
				+ "<td>Carrot</td>"
				+ "<td>30</td>"
				+ "<td>₹ 60</td>"
				+ "</tr>"
				+ "</tbody>"
				+ "</table>"
				+ "</div></div></div>");

	}
}
