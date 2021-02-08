import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerSignIn")

public class CustomerSignIn extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* User Information(username,password,usertype) is obtained from HttpServletRequest,
		Based on the Type of user(customer,retailer,manager) respective hashmap is called and the username and
		password are validated and added to session variable and display Login Function is called */

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = "customer";
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		//user details are validated using a file
		//if the file containts username and passoword user entered user will be directed to home page
		//else error message will be shown
		/*try
		{
          FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\EWA_Project\\UserDetails.txt"));
          ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		  		hm = (HashMap)objectInputStream.readObject();
		}
		catch(Exception e)
		{

		}*/
		try
		{
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{

		}
		User user = hm.get(username);
		if(user!=null)
		{
		 String user_password = user.getPassword();
		 if (password.equals(user_password))
			{
			HttpSession session = request.getSession(true);
			session.setAttribute("username", user.getName());
			session.setAttribute("usertype", usertype);
			response.sendRedirect("ShowStores");
			return;
			}
		}
		displayLogin(request, response, pw, true);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}


	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the BestDeal Application. */
	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div id='container'>");
		pw.print("<br />");
		
		pw.print("<div class='text-center' >"

		+ "</div>");
		pw.print("<div style='margin-top: 20px;'>"
	        + "<div class='media' style='margin-left: 540px;'>"
	           + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Customer Login</h4>"
	           + "</div></div></div>");
		if (error)
			pw.print("<div class='text-center' ><h4 style='color:red'>Please check your username, password and user type!</h4></div>");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login_msg")!=null){
			pw.print("<div class='text-center' ><h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4></div>");
			session.removeAttribute("login_msg");
		}
		pw.print("<form action='CustomerSignUp'>"
				+ "<div class='container' style='padding-top: 30px;'>"
				+ "<div class='center-page'>"
				+ "<div class='col-xs-11' style='margin-top: 10px;'>"
				+ "<input type='submit' class='btnbuy' value='DONT HAVE AN ACCOUNT?' CssClass='col-xs-offset-0' border-style='none;' style=' width: 303px; margin: 2rem; color:white; background: #22C125;'></input>"
				+ "</form>" +"</div>"
				+ "<form action='CustomerSignIn' method='Post'>"
				+ "<label class='col-xs-11' style='margin-top: 20px;'>Username</label>"
				+ "<div class='col-xs-11'>"
				+ "<input type='text' name='username' value='' class='input form-control' required></input>"
				+ "</div>"
				+ "<label class='col-xs-11' style='margin-top:20px;'>Password</label>"
				+ "<div class='col-xs-11'>"
				+ "<input type='text' name='password' value='' class='input form-control' required></input>"
				+ "</div>"
				+ "<div class='col-xs-11' style='margin-top: 10px;''>"
				+ "<input type='submit' class='btnbuy'  value='SIGN IN' CssClass='col-xs-offset-0' border-style='none;' style=' width: 303px; margin: 2rem; color:white; background: #22C125;'></input>"
				+ "</form>" +"</div></div></div>");
		utility.printHtml("Footer.html");
	}

}
