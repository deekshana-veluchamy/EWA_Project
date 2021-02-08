import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BeveragesList")

public class BeveragesList extends HttpServlet {

	/* Beverages Page Displays all the beverage and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");


		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Beverages> hm = new HashMap<String, Beverages>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.beverage);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("teacoffee"))
		   {
			 for(Map.Entry<String,Beverages> entry : SaxParserDataStore.beverage.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Coffee"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Tea & Coffee";
		   }
		   else if(CategoryName.equals("fruitjuice"))
		    {
				for(Map.Entry<String,Beverages> entry : SaxParserDataStore.beverage.entrySet())
					{
					 if(entry.getValue().getRetailer().equals("FruitsJuices"))
					 {
						 hm.put(entry.getValue().getId(),entry.getValue());
					 }
					}
					 name = "FruitsJuices";
				}
			else if(CategoryName.equals("softdrinks"))
			{
				for(Map.Entry<String,Beverages> entry : SaxParserDataStore.beverage.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("SoftDrinks"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "SoftDrinks";
			}
			else if(CategoryName.equals("water"))
			{
				for(Map.Entry<String,Beverages> entry : SaxParserDataStore.beverage.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Water"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Water";
			}
			else if(CategoryName.equals("energydrinks"))
			{
				for(Map.Entry<String,Beverages> entry : SaxParserDataStore.beverage.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("EnergyDrinks"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "EnergyDrinks";
			}
		}


		/* Header, Left Navigation Bar are Printed.

		All the Beverages and Beverages information are dispalyed in the Content Section

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
		+ "<input type='hidden' name='type' value='BeveragesList'>"
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
		for(Map.Entry<String, Beverages> entry : hm.entrySet())
		{
			Beverages beverages = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+beverages.getName()+"</h3>");
			pw.print("<strong>$"+beverages.getPrice()+"</strong><ul>");
			pw.print("<strong>Discount:" + beverages.getDiscount() + "%</strong><ul>");
			pw.print("<li id='item'><img src='images/beverages/"+beverages.getImage()+"' alt='' /></li>");

			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='beverage'>"+
					"<input type='hidden' name='maker' value='"+beverages.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='beverage'>"+
					"<input type='hidden' name='maker' value='"+beverages.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+beverages.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='beverage'>"+
					"<input type='hidden' name='maker' value='"+beverages.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
						pw.print("<li><form method='post' action='Favourite'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
								"<input type='hidden' name='type' value='BeveragesList'>"+
								"<input type='hidden' name='id' value='"+beverages.getId()+"'>"+
								"<input type='hidden' name='maker' value='"+beverages.getRetailer()+"'>"+
								"<input type='hidden' name='discount' value='"+beverages.getDiscount()+"'>"+
								"<input type='hidden' name='price' value='"+beverages.getPrice()+"'>"+
							  "<input type='submit' value='Add to favourites' class='btnbuy'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");

		utility.printHtml("Footer.html");

	}
}
