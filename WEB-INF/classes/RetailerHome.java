import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RetailerHome")

public class RetailerHome extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}


	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("RetailerHeader.html");
		pw.print("<br></br>");
    //utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='container'>");
		pw.print("<br />");
		pw.print("<br />");
		pw.print("<br />");


		pw.print("<div class='container-fluid' style='margin-top: 60px;'>"
						+ "<div class='col-sm-2' style='background-color: black; height: 690px;'>"
		+ "<center>"
		+ "<a href='CustomerProfile'> <p style='color:white; margin-top:19px;  font-family:Lucida Sans; font-size:25px;'>DASHBOARD</p></a>"
						+ "<div style='height: 3px; width: 100%;background-color: white;'></div>"
						+ "<img src='images/Profile/man1.jpg' style='margin-top:65px; height:100px; width:100px;' class='img-circle'/>"
						+ "<center><p style='color:white; font-family:Lucida Sans; font-size:19px; margin-top:5px;'>Hi Retailer</p></center>"
		+ "</center>"
    + "<div style='margin-top: 35px;'></div>"
				+ "<form action='MyStore'><input type='submit' class='btnbuy' action='MyStore'  value='MY STORE' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddBrand'><input type='submit' class='btnbuy submit-button'  value='BRAND' action='/AddBrand' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddCategory'><input type='submit' class='btnbuy'  value='CATEGORY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='SUB CATEGORY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='QUANTITY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddProduct'><input type='submit' class='btnbuy'  value='ADD PRODUCT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='Home'><input type='submit' class='btnbuy'  value='LOGOUT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "</div>");
		pw.print("<div class='text-center' >"

		+ "</div>");
		pw.print("<div style='margin-top: 20px;'>"
	        + "<div class='media' style='margin-left: 540px;'>"
	           + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'></h4>"
	           + "</div></div></div>");
		//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

		pw.print("<div class='container' style='padding-top: 30px;'>");



			pw.print("<div class='entry'>");
			pw.print("<form name='get' action='ProductModify'>"

				+"<table style='width:100%'><tr>"
				+ "<td>"
				+"<h2>Inventory Report</h2>"
				+"<ul>"
					+"<li><a href=InventoryReport?inventoryreport=allproducts><span class='glyphicon'>All Products</span></a></li>"
					+"<li><a href=InventoryReport?inventoryreport=barchart><span class='glyphicon'>Generate Bar Chart</span></a></li>"
					+"<li><a href=InventoryReport?inventoryreport=productsonsale><span class='glyphicon'>Products Currently on sale</span></a></li>"
					+"<li><a href=InventoryReport?inventoryreport=productsonrebate><span class='glyphicon'>Products with manufacturer rebate</span></a></li>"
				+"</ul>"
				+ "</td>"
				+ "<td>"
				+"<h2>Sales Report</h2>"
				+"<ul>"
					+"<li><a href=SalesReport?sales=ProductAvailability>Sold Products</a></li>"
					+"<li><a href=SalesReport?sales=GraphicalView>Generate Bar Chart</a></li>"
					+"<li><a href=SalesReport?sales=SalesByDate>Daily Transaction</a></li>"
				+"</ul>"
				+ "</td>"
				+ "</tr>"
				+ "</table></form>" + "</div></div>");
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");

	}
}
