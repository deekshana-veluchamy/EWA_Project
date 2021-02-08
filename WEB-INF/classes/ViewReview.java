import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

/* Displays the reviews of a product if there are any reviews for the corresponding product */

public class ViewReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}

	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request,pw);
			if(!utility.isLoggedin()){
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to view Review");
				response.sendRedirect("Login");
				return;
			}
			String productName=request.getParameter("name");
			HashMap<String, ArrayList<Review>> hm= MongoDBDataStoreUtilities.selectReview();
			String userName = "",userAge = "",userOccupation = "",userGender = "",retailerName = "",retailerPin="",retailerZipcode = "";
			String reviewRating = "";
			String reviewDate;
			String reviewText = "",manufacturerrebate="",productonsale="",productType="";
			String price = "";
			String city ="";
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
		               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Review</h4>"
		           + "</div></div></div>");
			//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

			pw.print("<div class='container' style='padding-top: 30px;'>");
            ////pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Review</a>");
			pw.print("<div class='entry'>");
			//if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
			if(hm==null)
			{
				pw.println("<h2>Mongo Db server is not up and running</h2>");
			}
			else
			{
	            if(!hm.containsKey(productName))
	            {
					pw.println("<h2>There are no reviews for this product.</h2>");
				}
				else{
						for (Review r : hm.get(productName))
						{
							pw.print("<div style='padding-bottom:20px'><table class='gridtable'>");
							pw.print("<tr>");
							pw.print("<td> Product Name: </td>");
							productName = r.getProductName();
							pw.print("<td>" +productName+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Product Type: </td>");
							productType = r.getProductType();
							pw.print("<td>" +productType+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Product on Sale: </td>");
							productonsale = r.getproductonsale();
							pw.print("<td>" +productonsale+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Manufacturer Rebate: </td>");
							manufacturerrebate = r.getmanufacturerrebate();
							pw.print("<td>" +manufacturerrebate+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> userName: </td>");
							userName = r.getUserName();
							pw.print("<td>" +userName+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> userAge: </td>");
							userAge = r.getuserage();
							pw.print("<td>" +userAge+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> userOccupation: </td>");
							userOccupation = r.getuseroccupation();
							pw.print("<td>" +userOccupation+ "</td>");
							pw.print("</tr>");


							pw.print("<tr>");
							pw.print("<td> price: </td>");
							price = r.getPrice();
							pw.print("<td>" +price+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Retailer Name: </td>");
							retailerName = r.getretailername();
							pw.print("<td>" +retailerName+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Retailer City: </td>");
							city = r.getRetailerCity();
							pw.print("<td>" +city+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Retailer Zipcode: </td>");
							retailerZipcode = r.getRetailerPin();
							pw.print("<td>" +retailerZipcode+ "</td>");
							pw.print("</tr>");
							pw.println("<tr>");
							pw.println("<td> Review Rating: </td>");
							reviewRating = r.getReviewRating().toString();
							pw.print("<td>" +reviewRating+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Review Date: </td>");
							reviewDate = r.getReviewDate().toString();
							pw.print("<td>" +reviewDate+ "</td>");
							pw.print("</tr>");
							pw.print("<tr>");
							pw.print("<td> Review Text: </td>");
							reviewText = r.getReviewText();
							pw.print("<td>" +reviewText+ "</td>");
							pw.print("</tr>");
							pw.println("</table></div>");
						}

					}
			}
            pw.print("</div></div></div>");
			utility.printHtml("Footer.html");

        }
      	catch(Exception e)
		{
         	System.out.println(e.getMessage());
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
    }
}
