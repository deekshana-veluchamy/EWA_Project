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


class MyComparator implements Comparator<String>
    {
        public int compare(String o1,String o2)
        {
           return (o1.compareTo(o2));
        }
    }

@WebServlet("/SalesReport")


public class SalesReport extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("sales");
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
		if(action.equals("ProductAvailability"))
		{

			Map<OrderItem,String> map = new HashMap<OrderItem,String>();
			map = MySqlDataStoreUtilities.getAllProductSold();

			if(map!=null){
				//pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products Sold</a></h2>"
						+ "<div class='entry'>");
				if(map.isEmpty()){
	              pw.print("<h4 style='color:red'>No Product sold details are available..</h4>");
	            }
	            else{

					pw.print("<table border='2' style='width:80%;Margin-top: 30px'>");
					pw.print("<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name</th><th style='font-size:14px;font-weight: bold;text-align:center'>Product Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Items Sold</th><th style='font-size:14px;font-weight: bold;text-align:center'>Total Sales</th></tr>");

					Iterator it = map.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry entry = (Map.Entry) it.next();
						OrderItem key = (OrderItem) entry.getKey();
						String value = (String) entry.getValue();
						pw.println("<tr><td style='font-size:12px;text-align:center'>" +key.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +key.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +value+ "</td><td style='font-size:12px;text-align:center'>$" + key.getPrice() * Float.parseFloat(value) + "</td></tr>");
					}

					pw.print("</table>");
				}

				pw.print("</div>");
			}
			else{
				//pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products Sold</a></h2>"
						+ "<div class='entry'>");

				pw.print("<h4 style='color:red'>MySQL server is Down..</h4>");

				pw.print("</div>");
			}




		}else if (action.equals("GraphicalView"))
		{
				//pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Graphical View Of Products Sold</a></h2>"
						+ "<div class='entry'>");
		   		pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		   		pw.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>"+
							"<script type='text/javascript' src='https://www.google.com/jsapi'></script>");

		   		pw.println("<script type='text/javascript'>");
				Map<OrderItem,String> map1 = new HashMap<OrderItem,String>();
				map1 = MySqlDataStoreUtilities.getAllProductSold();
				pw.println("google.charts.load('current', {'packages':['corechart']});");
				pw.println("google.charts.setOnLoadCallback(drawChart);");
				pw.println("function drawChart() {");
				pw.println("var data = new google.visualization.DataTable();");
				pw.println("data.addColumn('string', 'Product Name');");
				pw.println("data.addColumn('number', 'Total Sales');");
			   	pw.println(" data.addRows([");

					Iterator it = map1.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry entry = (Map.Entry) it.next();
						OrderItem key = (OrderItem) entry.getKey();
						String value = (String) entry.getValue();
						//Float totalsale= Float.parseFloat(key.getPrice() * Float.parseFloat(value));
						pw.println(" ['"+key.getName()+"', "+value+"],");
						//pw.println("<tr><td style='font-size:12px;text-align:center'>" +key.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +key.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +value+ "</td><td style='font-size:12px;text-align:center'>$" + key.getPrice() * Float.parseFloat(value) + "</td></tr>");
					}


				pw.println("]);");
				// Set chart options
				pw.println(" var options = {'title':'Items Sold',");
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

		else if (action.equals("SalesByDate"))
		{
			//pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Sales Report By Date</a></h2>"
						+ "<div class='entry'>");

				TreeMap<String, Float> dailySales = new TreeMap<String , Float>(new MyComparator());
				dailySales = MySqlDataStoreUtilities.getDailySales();
				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"+
					"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Date </th><th style='font-size:14px;font-weight: bold;text-align:center'>Total Sales</th></tr>");
				Iterator it = dailySales.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry entry = (Map.Entry) it.next();
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					double d = Double.parseDouble(value);
					DecimalFormat df = new DecimalFormat("#.####");
					pw.println("<tr><td style='font-size:12px;text-align:center'>" +key+ " </td><td style='font-size:12px;text-align:center'>$" +df.format(d)+ "</td></tr>");
				}
				pw.println("</table>");


			pw.print("</div>");
		}
		else{
			pw.print("hello4");
		}
    pw.print("</div></div>");
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}
