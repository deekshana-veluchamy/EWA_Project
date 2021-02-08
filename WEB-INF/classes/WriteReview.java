import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WriteReview")

	//once the user clicks writereview button from products page he will be directed
 	//to write review page where he can provide reqview for item rating reviewtext

public class WriteReview extends HttpServlet {
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
			session.setAttribute("login_msg", "Please Login to Write a Review");
			response.sendRedirect("Login");
			return;
			}
	        String productname=request.getParameter("name");
	        String producttype=request.getParameter("type");
	        String productmaker=request.getParameter("maker");
	 		String productprice=request.getParameter("price");
	      	// on filling the form and clicking submit button user will be directed to submit review page
	 		User user=utility.getUser();
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

			pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
	        //pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>Review</a>");
			pw.print("<div class='entry'>");
	        pw.print("<table class='gridtable'>");
	        pw.print("<tr><td> UserID: </td><td>");
			pw.print(user.getName());
			pw.print("<input type='hidden' name='userid' value='"+user.getName()+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td> ProductModel Name: </td><td>");
			pw.print(productname);
			pw.print("<input type='hidden' name='productname' value='"+productname+"'>");
			pw.print("</td></tr>");
		    pw.print("<tr><td> Product Category:</td><td>");
	        pw.print(producttype);
	        pw.print("<input type='hidden' name='producttype' value='"+producttype+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td> Product Price:</td><td>");
	        pw.print(productprice);
	        pw.print("<input type='hidden' name='productprice' value='"+productprice+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td> Retailer ID:</td><td>");
			pw.print("GroceryHub of Chicago");
	        pw.print("<input type='hidden' name='retailername' value='GroceryHub'>");
			pw.print("</td></tr>");
	        pw.print("<tr><td> Manufacturer Name: </td><td>");
	        pw.print(productmaker);
			pw.print("<input type='hidden' name='manufacturername' value='"+productmaker+"'>");
	        pw.print("</td></tr><table>");
			pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
			pw.print("<td>");
			pw.print("<select name='reviewrating'>");
			pw.print("<option value='1' selected>1</option>");
			pw.print("<option value='2'>2</option>");
			pw.print("<option value='3'>3</option>");
			pw.print("<option value='4'>4</option>");
			pw.print("<option value='5'>5</option>");
			pw.print("</td></tr>");
			pw.print("<tr>");
			pw.print("<td> User Age: </td>");
			pw.print("<td> <input type='text' name='userage'> </td>");
	        pw.print("</tr>");
	        pw.print("<tr>");
			pw.print("<td> User Gender: </td>");
			pw.print("<td>");
			pw.print("<select name='usergender'>");
			pw.print("<option value='Male' selected>Male</option>");
			pw.print("<option value='Female'>Female</option>");
	        pw.print("</td></tr>");
	        pw.print("<tr>");
			pw.print("<td> User Occupation: </td>");
			pw.print("<td> <input type='text' name='useroccupation'> </td>");
	        pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> Product On Sale: </td>");
			pw.print("<td>");
			pw.print("<select name='productonsale'>");
			pw.print("<option value='Yes' selected>Yes</option>");
			pw.print("<option value='No'>No</option>");
	        pw.print("</td></tr>");
	        pw.print("<tr>");
			pw.print("<td> Manufacturer Rebate: </td>");
			pw.print("<td>");
			pw.print("<select name='manufacturerrebate'>");
			pw.print("<option value='Yes' selected>Yes</option>");
			pw.print("<option value='No'>No</option>");
	        pw.print("</td></tr>");
			pw.print("<tr>");
			pw.print("<td> Retailer Zip Code: </td>");
			pw.print("<td> <input type='text' name='zipcode'> </td>");
	        pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> Retailer City: </td>");
			pw.print("<td> <input type='text' name='retailercity'> </td>");
	        pw.print("</tr>");
	        pw.print("<tr>");
			pw.print("<td> Retailer State: </td>");
			pw.print("<td> <input type='text' name='retailerstate'> </td>");
	        pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> Review Date: </td>");
			pw.print("<td> <input type='date' name='reviewdate'> </td>");
	       	pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> Review Text: </td>");
			pw.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
			pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");
	    	pw.print("</h2></div></div></div>");
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