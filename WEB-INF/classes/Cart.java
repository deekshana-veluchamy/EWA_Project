import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		System.out.print("name" + name + "type" + type + "maker" + maker + "access" + access);

		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/

		utility.storeProduct(name, type, maker, access);
		displayCart(request, response);
	}


/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		String name1 = request.getParameter("name");
		DecimalFormat df = new DecimalFormat("###.##");
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("CustomerSignIn");
			return;
		}

		utility.printHtml("Header.html");
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
	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Cart</h4>"
	           + "</div></div></div>");

		//utility.printHtml("LeftNavigationBar.html");
		pw.print("<div class='container' style='padding-top: 30px;'>");
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry'>");

		if(utility.CartCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			double price;
			double discount;
			for (OrderItem oi : utility.getCustomerOrders())
			{
				pw.print("<tr>");
				pw.print("<td><input type='radio' name='orderN' value='"+oi.getName()+"'></td>");
				pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>Price: "+oi.getPrice()+"</td><td>Discount: "+oi.getDiscount()+"%</td><td>Quantity:<input type='number' id='quantity' name='quantity'></td>");
				//pw.print("<input type='number' id='quantity' name='quantity' min='1' max='100'>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("<input type='hidden' name='orderDiscount' value='"+oi.getDiscount()+"'>");
				pw.print("<td><input type='submit' name='remove' value='Remove' class='btnbuy'></td>");
				pw.print("</tr>");
				price = oi.getPrice();
				discount = ((100 - oi.getDiscount()) * price) / 100;
				total = total +discount;
				i++;
			}

			pw.print("<input type='hidden' name='orderTotal' value='"+total+"'>");
			pw.print("<tr><th></th><th>Total after discount</th><th>"+total+"</th>");
			pw.print("<input type='hidden' name='name' value='"+name1+"'>"+
					"<input type='hidden' name='type' value='fvs'>");
			pw.println("<tr><td>");
				pw.println("<td><input type='radio' name='deliverables' value='pickup'>Pick-up </td>");
				pw.println("<td><input type='radio' name='deliverables' value='delivery'>Home-Delivery</td>");
				pw.println("</td></tr>");
			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			pw.print("</table></form>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			pw.print(carousel.carouselfeature(utility));
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		pw.print("</div></div></div></div></div>");


		utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		displayCart(request, response);
	}
}
