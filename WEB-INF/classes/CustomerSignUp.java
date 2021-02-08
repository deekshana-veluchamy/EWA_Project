import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/CustomerSignUp")

public class CustomerSignUp extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}

	/*   Username,Password,Usertype information are Obtained from HttpServletRequest variable and validates whether
		 the User Credential Already Exists or else User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String usertype = "retailer";
		if(!utility.isLoggedin())
			usertype = "customer";

		//if password and repassword does not match show error message

		if(!password.equals(repassword))
		{
			error_msg = "Passwords doesn't match!";
		}
		else
		{

			//get the user details from file

			HashMap<String, User> hm=new HashMap<String, User>();

			String message=MySqlDataStoreUtilities.getConnection();

			if(message.equals("Successfull"))
			{
				hm=MySqlDataStoreUtilities.selectUser();

			// if the user already exist show error that already exist

			if(hm.containsKey(username))
				error_msg = "Username already exist as " + usertype;
			else
			{
				/*create a user object and store details into hashmap
				store the user hashmap into file  */

				User user = new User(username,password,repassword,name,email,mobile);
				hm.put(username, user);
				MySqlDataStoreUtilities.insertUser(username,password,repassword,name,email,mobile);
				HttpSession session = request.getSession(true);

				if(!utility.isLoggedin()){
					session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
					response.sendRedirect("CustomerSignIn"); return;
				} else {
					response.sendRedirect("CustomerSignIn"); return;
				}
			}

					//response.sendRedirect("CustomerSignIn"); return;


			}
		}

		//display the error message
		if(utility.isLoggedin()){
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", error_msg);
			//response.sendRedirect("Account"); return;
		}
		displayRegistration(request, response, pw, true);

	}

	/*  displayRegistration function displays the Registration page of New User */

	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div id='container'>");
		pw.print("<br />");
		pw.print("<br />");
		pw.print("<br />");
		pw.print("<br />");
		pw.print("<div class='text-center' >"

		+ "</div>");
		pw.print("<div style='margin-top: 20px;'>"
	        + "<div class='media' style='margin-left: 540px;'>"
	           + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Customer SignUp</h4>"
	           + "</div></div></div>");
		if (error)
			pw.print("<div class='text-center' ><h4 style='color:red'>"+error_msg+"</h4></div>");

			pw.print("<form method='Post' action='CustomerSignUp' >"
									+ "<div class='container' style='padding-top: 70px; margin-bottom: 90px; height: 610px; width: 400px;'>"
	                + "<div class='center-page'>"
	                + "<div class='col-xs-11' style='margin-top: 10px;''>"
	                + "<input type='submit' class='btnbuy'  value='ALREADY HAVE AN ACCOUNT?' CssClass='col-xs-offset-0' border-style='none;' style=' width: 303px; margin: 2rem; color:white; background: #22C125;'></input>"
	                + "</div>"
	                + "<label class='col-xs-11' style='margin-top: 20px;'>Username</label>"
	                + "<div class='col-xs-11'>"
	                + "<input type='text' name='username' value='' class='input form-control' required></input>"
	                + "</div>"
	                + "<label class='col-xs-11'>Password</label>"
	                + "<div class='col-xs-11'>"
	                + "<input type='password' name='password' value='' class='input form-control' required></input>"
	                + "</div>"
	                + "<label class='col-xs-11'>Confirm Password</label>"
	                + "<div class='col-xs-11'>"
	                + "<input type='password' name='repassword' value='' class='input form-control' required></input>"
	                + "</div>"
	                + "<label class='col-xs-11'>Name</label>"
	                + "<div class='col-xs-11'>"
	                + "<input type='text' name='name' value='' class='input form-control' required></input>"
	                + "</div>"
	                + "<label class='col-xs-11'>Email</label>"
	                + "<div class='col-xs-11'>"
	                + "<input type='text' name='email' value='' class='input form-control' required></input>"
	                + "</div>"
	                + "<label class='col-xs-11' style='margin-top: 20px;'>Mobile</label>"
	                + "<div class='col-xs-11'>"
	                + "<input type='text' name='mobile' value='' class='input form-control' required></input>"
	                + "</div>"
	                + "<div class='col-xs-11' style='margin-top: 10px;'>"
	                + "<input type='submit' class='btnbuy'  value='SIGN UP' name='ByUser' CssClass='col-xs-offset-0' border-style='none;' style=' width: 303px; margin: 2rem; color:white; background: black;'></input>"
					+ "</form>" +"</div></div></div>");
			utility.printHtml("Footer.html");
	}
}
