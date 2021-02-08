import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.util.Random;
import javax.servlet.http.Part;
import java.sql.*;
//import Dao.UploadFileDao;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

@WebServlet("/RetailerSignUp")

public class RetailerSignUp extends HttpServlet {
	private String error_msg;
  public static final String UPLOAD_DIR = "Retailer_Images";


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


		String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String mobile=request.getParameter("mobile");
        String street=request.getParameter("street");
        String city=request.getParameter("city");
        String zipcode=request.getParameter("zipcode");
        String sname=request.getParameter("sname");
        String smobile=request.getParameter("smobile");
        String semail=request.getParameter("semail");
        String sstreet=request.getParameter("sstreet");
        String scity=request.getParameter("scity");
        String szipcode=request.getParameter("szipcode");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
				String repassword=request.getParameter("repassword");
				//String simage=request.getParameter("simage");

        Part part = request.getPart("simage");
        String fileName=extractFileName(part); //file name

        /**
         * *** Get The Absolute Path Of The Web Application ****
         */
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        //System.out.println("applicationPath:" + applicationPath);
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);

		String usertype = "retailer";
		if(!utility.isLoggedin())
			usertype = "retailer";

		//if password and repassword does not match show error message

		if(!password.equals(repassword))
		{
			error_msg = "Passwords doesn't match!";
		}
		else
		{
			HashMap<String, Retailer> hm=new HashMap<String, Retailer>();

			String message=MySqlDataStoreUtilities.getConnection();

			if(message.equals("Successfull"))
			{
				hm=MySqlDataStoreUtilities.selectRetailer();

			// if the user already exist show error that already exist



			if(hm.containsKey(username))
				error_msg = "Username already exist as " + usertype;
			else
			{
				/*create a user object and store details into hashmap
				store the user hashmap into file  */
				Random rand = new Random();
			  int sid = rand.nextInt(1000);

				Retailer user = new Retailer(fname, lname, mobile, street, city, zipcode, sname, smobile, semail, sstreet, scity, szipcode, username, password, repassword);
				hm.put(username, user);
				System.out.println("in else");
				MySqlDataStoreUtilities.insertRetailer(sid,  fname, lname, mobile, street, city, zipcode, sname, smobile, semail, sstreet, scity, szipcode, username, password, repassword,savePath);
				HttpSession session = request.getSession(true);

				if(!utility.isLoggedin()){
					session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
					response.sendRedirect("RetailerSignIn"); return;
				} else {
					response.sendRedirect("RetailerSignIn"); return;
				}
			}
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
		
		pw.print("<div class='text-center' >"

		+ "</div>");
		pw.print("<div style='margin-top: 20px;'>"
	        + "<div class='media' style='margin-left: 540px;'>"
	           + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Retailer SignUp</h4>"
	           + "</div></div></div>");
		if (error)
			pw.print("<div class='text-center' ><h4 style='color:red'>"+error_msg+"</h4></div>");

		pw.print("<form action='RetailerSignUp' method='post' enctype='multipart/form-data'>"
		+ "<div class='container' style='padding-top: 80px;'>"
		+ "<div class='panel panel-default'>"
		+ "<div class='panel-heading' style='background: #22C125; color: whitesmoke;'>PERSONAL INFORMATION</div>"
		+ "</div>"
		+ "<br />"
		+ "<div class='form-horizontal'>"
		+ "<div class='col-md-10'>"
		+ "<div class='form-group'>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='fname' class='form-control' placeholder='First Name' required></input>"
		+ "</div>"
		+ "<div class='col-md-4'>"
		+ "<input type='text' name='lname' class='form-control' placeholder='Last Name' required></input>"
		+ "</div>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='mobile' class='form-control' placeholder='Mobile No' required></input>"
		+ "</div>"
		+ "</div>"
		+ "<div class='form-group'>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='street' class='form-control' placeholder='Street' required></input>"
		+ "</div>"
		+ "<div class='col-md-4'>"
		+ "<input type='text' name='city' class='form-control' placeholder='City' required></input>"
		+ "</div>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='zipcode' class='form-control' placeholder='Zipcode' required></input>"
		+ "</div>"
		+ "</div>"
		+ "</div>"
		+ "</div>"
		+ "<br />");
		pw.print("<br /><br /><br />");

		pw.print("<div class='panel panel-default'>"
		+ "<div class='panel-heading' style='background: black; color: whitesmoke;'>STORE'S INFORMATION</div>"
		+ "</div>"
		+ "<br />"
		+ "<div class='form-horizontal'>"
		+ "<div class='col-md-10'>"
		+ "<div class='form-group'>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='sname' class='form-control' placeholder='Store Name' required></input>"
		+ "</div>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='smobile' class='form-control' placeholder='Contact No' required></input>"
		+ "</div>"
		+ "<div class='col-md-4'>"
		+ "<input type='text' name='semail' class='input form-control' placeholder='Email'required></input>"
		+ "</div>"
		+ "</div>"
		+ "<div class='form-group'>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='sstreet' class='form-control' placeholder='Street' required></input>"
		+ "</div>"
		+ "<div class='col-md-4'>"
		+ "<input type='text' name='scity' class='form-control' placeholder='City' required></input>"
		+ "</div>"
        + "<div class='col-md-4'>"
		+ "<input type='text' name='szipcode' class='form-control' placeholder='Zipcode' required></input>"
		+ "</div>"
		+ "</div>"
		+ "<div class='form-group'>"
		+ "<div class='col-xs-11'>"
		+ "<input type='text' name='username' class='input form-control' placeholder='Username' required></input>"
		+ "</div>"
		+ "<div class='col-xs-11'>"
		+ "<input type='text' name='password' class='input form-control' placeholder='Password' required></input>"
		+ "</div>"
		+ "<div class='col-xs-11'>"
		+ "<input type='text' name='repassword' class='input form-control' placeholder='Confirm Password' required></input>"
		+ "</div>"
		+ "</div>"
		+ "<div class='col-md-2'>"
		+ "<div class='form-group'>"
		+ "<input type='file' name='simage' size='50' required></input>"
		+ "</div>"
		+ "</div>"
		+ "<div class='form-group'>"
		+ "<div class='col-xs-11' style='margin-top: 10px;'>"
		+ "<input type='submit' class='btnbuy'  value='SIGN UP' CssClass='col-xs-offset-0' border-style='none;' style=' width: 303px; margin: 2rem; color:white; background: black;'></input>"
		+ "</div>"
		+ "</form>"
		+ "</div>"
		+ "</div>"
		+ "</div>"
		+ "</div>");

		utility.printHtml("Footer.html");
	}
  private String extractFileName(final Part part) {//This method will print the file name.
      String contentDisp = part.getHeader("content-disposition");
      String[] items = contentDisp.split(";");
      for (String s : items) {
          if (s.trim().startsWith("filename")) {
              return s.substring(s.indexOf("=") + 2, s.length() - 1);
          }
      }
      return "";
  }
}
