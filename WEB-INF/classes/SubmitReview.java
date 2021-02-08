

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitReview")

/* SubmitReview java file is used to store the reviews of the products into the MongoDB Database */

public class SubmitReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    Utilities utility= new Utilities(request, pw);
		storeReview(request, response);
	}
	protected void storeReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request,pw);
			if(!utility.isLoggedin()){
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			String userid=request.getParameter("userid");
            String productname=request.getParameter("productname");
            String producttype=request.getParameter("producttype");
			String productprice=request.getParameter("productprice");
			String retailername=request.getParameter("retailername");
            String manufacturername=request.getParameter("manufacturername");
            String reviewrating=request.getParameter("reviewrating");
            String userage=request.getParameter("userage");
            String usergender=request.getParameter("usergender");
            String useroccupation=request.getParameter("useroccupation");
            String productonsale=request.getParameter("productonsale");
            String manufacturerrebate=request.getParameter("manufacturerrebate");
            String zipcode=request.getParameter("zipcode");
			String retailercity = request.getParameter("retailercity");
            String retailerstate = request.getParameter("retailerstate");
            String reviewdate=request.getParameter("reviewdate");
            String reviewtext=request.getParameter("reviewtext");
			String message=utility.storeReview(userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext);
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

		//	pw.print("<form name ='Cart' action='CheckOut' method='post'>");
	        //pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>Review</a>");
			pw.print("</h2><div class='entry'>");
	      		if(message.equals("Successfull")){
							pw.print("<h2>Review for &nbsp"+productname+" Stored </h2>");
						}else{
							pw.print("<h2>Mongo Db is not up and running </h2>");
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
