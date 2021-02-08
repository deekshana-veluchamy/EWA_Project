import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GroceryList")

public class GroceryList extends HttpServlet {

	/* Grocery Page Displays all the grocerys and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");


		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Grocery> hm = new HashMap<String, Grocery>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.grocerys);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("herbsspices"))
		   {
			 for(Map.Entry<String,Grocery> entry : SaxParserDataStore.grocerys.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Spices"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Herbs & Spices";
		   }
		   else if(CategoryName.equals("rgd"))
		    {
				for(Map.Entry<String,Grocery> entry : SaxParserDataStore.grocerys.entrySet())
					{
					 if(entry.getValue().getRetailer().equals("DriedBeans"))
					 {
						 hm.put(entry.getValue().getId(),entry.getValue());
					 }
					}
					 name = "Rice,Grains & DriedBeans";
				}
			else if(CategoryName.equals("pastaspizzas"))
			{
				for(Map.Entry<String,Grocery> entry : SaxParserDataStore.grocerys.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Pizzas"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Pastas & Pizzas";
			}
			else if(CategoryName.equals("oilvinegar"))
			{
				for(Map.Entry<String,Grocery> entry : SaxParserDataStore.grocerys.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Vinegar"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Oils & Vinegar";
			}
			else if(CategoryName.equals("cereals"))
			{
				for(Map.Entry<String,Grocery> entry : SaxParserDataStore.grocerys.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("BreakfastFoods"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Cereals & BreakfastFoods";
			}
		}


		/* Header, Left Navigation Bar are Printed.

		All the Grocery and Grocery information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		//utility.printHtml("LeftNavigationBar.html");

		pw.print("<div id='container'>");
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
	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>"+name+"</h4>"
	           + "</div></div></div>");
		//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

		pw.print("<div class='container' style='padding-top: 30px;'>");
		pw.print("<div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Grocery> entry : hm.entrySet())
		{
			Grocery grocery = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+grocery.getName()+"</h3>");
			pw.print("<strong>$"+grocery.getPrice()+"</strong><ul>");
			pw.print("<strong>Discount:" + grocery.getDiscount() + "%</strong><ul>");
			pw.print("<li id='item'><img src='images/grocerys/"+grocery.getImage()+"' alt='' /></li>");

			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='grocerys'>"+
					"<input type='hidden' name='maker' value='"+grocery.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='grocerys'>"+
					"<input type='hidden' name='maker' value='"+grocery.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+grocery.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='grocerys'>"+
					"<input type='hidden' name='maker' value='"+grocery.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
						pw.print("<li><form method='post' action='Favourite'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
								"<input type='hidden' name='type' value='GroceryList'>"+
								"<input type='hidden' name='id' value='"+grocery.getId()+"'>"+
								"<input type='hidden' name='maker' value='"+grocery.getRetailer()+"'>"+
								"<input type='hidden' name='discount' value='"+grocery.getDiscount()+"'>"+
								"<input type='hidden' name='price' value='"+grocery.getPrice()+"'>"+
								"<input type='submit' value='Add to favourites' class='btnbuy'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");

		utility.printHtml("Footer.html");

	}
}
