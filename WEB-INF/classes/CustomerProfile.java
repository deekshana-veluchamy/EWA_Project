import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerProfile")

public class CustomerProfile extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displaystore(request, response, pw, false);
    }


    protected void displaystore(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<br></br>");
		//utility.printHtml("LeftNavigationBar.html");
		// pw.print("</div>");
		// pw.print("<div class='post' style='float: none; width: 100%'>");

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
        + "<form action='HomePage'>"
				+ "<input type='submit' class='btnbuy'  value='LOGOUT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
        + "</form>"
        + "</div>"
				+ "<div class='col-sm-10'>"
				+ "<div class='container-fluid' style='margin-top: 20px;'>"
				+ "<form action='CustomerProfile' method='Post'>"
				+ "<label class='col-xs-11' style='margin-top: 20px;'>Username</label>"
        + "<div class='col-xs-11'>"
        + "<input type='text' name='username' class='input form-control' required></input>"
        + "</div>"
				+ "<label class='col-xs-11'>Password</label>"
        + "<div class='col-xs-11'>"
        + "<input type='password' name='password' class='input form-control' required></input>"
        + "</div>"
        + "<label class='col-xs-11'>Name</label>"
        + "<div class='col-xs-11'>"
        + "<input type='text' name='name' class='input form-control' required></input>"
        + "</div>"
        + "<label class='col-xs-11'>Email</label>"
        + "<div class='col-xs-11'>"
        + "<input type='text' name='email' class='input form-control' required></input>"
        + "</div>"
        + "<label class='col-xs-11' style='margin-top: 20px;'>Mobile</label>"
        + "<div class='col-xs-11'>"
        + "<input type='text' name='mobile' class='input form-control' required></input>"
        + "</div>"
        + "<div class='col-xs-11' style='margin-top: 10px;'>"
        + "<input type='submit' class='btnbuy'  value='Save' CssClass='col-xs-offset-0' border-style='none;' style=' width: 303px; margin: 2rem; color:white; background: black;'></input>"
        + "</form>"
				+ "</div>"
				+ "<hr />"
				+ "</div></div></div>");

	}
}
