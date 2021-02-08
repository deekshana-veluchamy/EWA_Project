import java.io.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;


@WebServlet("/InventoryReport")


public class InventoryReport extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("inventoryreport");
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
	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Inventory and Sales Report</h4>"
	           + "</div></div></div>");
		//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

		pw.print("<div class='container' style='padding-top: 30px;'>");
		if(action.equals("allproducts"))
		{
			//pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products Inventory</a></h2>"
						+ "<div class='entry'>");
			HashMap<String,Products> allproducts = new HashMap<String,Products> ();

			allproducts =MySqlDataStoreUtilities.getAllProducts();
				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"+
					"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name </th><th style='font-size:14px;font-weight: bold;text-align:center'>Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Quantity</th></tr>");
			for (Map.Entry<String, Products> entry : allproducts.entrySet()) {
				Products prdct = entry.getValue();
				pw.println("<tr><td style='font-size:12px;text-align:center'>" +prdct.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +prdct.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getQuantity()+ "</td></tr>");

			}
			pw.println("</table>");
			pw.print("</div>");




		}
		else if(action.equals("productsonsale")){

			//pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products On Sale</a></h2>"
						+ "<div class='entry'>");
			HashMap<String,Products> allproducts = new HashMap<String,Products> ();
			//productDiscount
			allproducts =MySqlDataStoreUtilities.getAllProductsinSales();
				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"+
					"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name </th><th style='font-size:14px;font-weight: bold;text-align:center'>Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Quantity</th><th style='font-size:14px;font-weight: bold;text-align:center'>Discount</th><th style='font-size:14px;font-weight: bold;text-align:center'>Manufacturer Rebate</th></tr>");
			for (Map.Entry<String, Products> entry : allproducts.entrySet()) {
				Products prdct = entry.getValue();
				pw.println("<tr><td style='font-size:12px;text-align:center'>" +prdct.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +prdct.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getQuantity()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getDiscount()+ " </td><td style='font-size:12px;text-align:center'>" +prdct.getManufacturerRebate()+ " </td></tr>");

			}
			pw.println("</table>");
			pw.print("</div>");
		}
		else if(action.equals("productsonrebate")){
			HashMap<String,Products> allrebateproducts = new HashMap<String,Products> ();
			allrebateproducts =MySqlDataStoreUtilities.getAllManufactureRebateProducts();
			if(!allrebateproducts.isEmpty()){
				//pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Manufacture Rebate Products</a></h2>"
							+ "<div class='entry'>");

				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"
					+"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name </th><th style='font-size:14px;font-weight: bold;text-align:center'>Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Quantity</th><th style='font-size:14px;font-weight: bold;text-align:center'>Manufacturer Rebate</th></tr>");
				for (Map.Entry<String, Products> entry : allrebateproducts.entrySet()) {
					Products prdct = entry.getValue();
					pw.println("<tr><td style='font-size:12px;text-align:center'>" +prdct.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +prdct.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getQuantity()+ "</td><td style='font-size:12px;text-align:center'>" +prdct.getManufacturerRebate()+ " </td></tr>");

				}
				pw.println("</table>");

				pw.print("</div>");
			}


		}
		else if(action.equals("barchart")) {
				//pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Graphical View Of Products Quantity</a></h2>"
						+ "<div class='entry'>");
		   		pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		   		pw.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>"+
							"<script type='text/javascript' src='https://www.google.com/jsapi'></script>");

		   		pw.println("<script type='text/javascript'>");
				HashMap<String,Products> map1 = new HashMap<String,Products> ();
				map1 = MySqlDataStoreUtilities.getAllProducts();
				pw.println("google.charts.load('current', {'packages':['corechart']});");
				pw.println("google.charts.setOnLoadCallback(drawChart);");
				pw.println("function drawChart() {");
				pw.println("var data = new google.visualization.DataTable();");
				pw.println("data.addColumn('string', 'Product Name');");
				pw.println("data.addColumn('number', 'Quantity');");
			   	pw.println(" data.addRows([");

			   	for (Map.Entry<String, Products> entry : map1.entrySet()) {
					Products prdct = entry.getValue();
					pw.println(" ['"+prdct.getName()+"', "+prdct.getQuantity()+"],");
				}
				pw.println("]);");
				// Set chart options
				pw.println(" var options = {'title':'Items Quantity',");
				pw.println("        'width':600,");
				pw.println("       'height':650};");

				// Instantiate and draw our chart, passing in some options.
				pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
				pw.println("  chart.draw(data, options);     }");
				pw.println(" </script>");
			//Draw chart
		 		pw.println("<div id='chart_div'></div>");


		   		pw.print("</div>");
		}
		pw.print("</div></div>");
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}
