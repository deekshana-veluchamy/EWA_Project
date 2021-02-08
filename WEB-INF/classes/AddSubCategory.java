import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddSubCategory")

public class AddSubCategory extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayaddsubcategory(request, response, pw, false);
    }


    protected void displayaddsubcategory(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("RetailerHeader.html");
		pw.print("<br></br>");
		//utility.printHtml("LeftNavigationBar.html");
		// pw.print("</div>");
		// pw.print("<div class='post' style='float: none; width: 100%'>");

        pw.print("<div class='container-fluid' style='margin-top: 60px; margin-left:0px;'>"
                + "<div class='col-sm-2' style='background-color: black; height: 690px;'>"
				+ "<center>"
				+ "<a href='MyStore'> <p style='color:white; margin-top:19px; margin-left:20px;  font-family:Lucida Sans; font-size:25px;'>DASHBOARD</p></a>"
                + "<div style='height: 3px; width: 100%;background-color: white;'></div>"
                + "<img src='images/Profile/man1.jpg' style='margin-top:65px; height:100px; width:100px;' class='img-circle'/>"
                + "<center><p style='color:white; font-family:Lucida Sans; font-size:19px; margin-top:5px; margin-left:28px;'>Owner's Name</p></center>"
				+ "</center>"
				+ "<div style='margin-top: 35px;'></div>"
				+ "<form action='MyStore'><input type='submit' class='btnbuy' action='MyStore'  value='MY STORE' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddBrand'><input type='submit' class='btnbuy submit-button'  value='BRAND' action='/AddBrand' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddCategory'><input type='submit' class='btnbuy'  value='CATEGORY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='SUB CATEGORY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='QUANTITY' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='AddProduct'><input type='submit' class='btnbuy'  value='ADD PRODUCT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='Home'><input type='submit' class='btnbuy'  value='LOGOUT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "</div>"
				+ "<div class='container' style='margin-top: 20px;'>"
                + "<div class='col-sm-10'>"

                + "<div class='panel panel-default'>"
                + "<div class='panel-heading panel-heading-green' style='background: #22C125;color: whitesmoke;'>ADD SUBCATEGORY</div>"
                + "</div>"
                + "<div class='container'>"
                + "<div class='form-horizontal'>"
                + "<div class='form-group'>"
                + "<label ID='Label1' CssClass='control-label col-md-2'>Main Category<label>"
                + "<div class='col-md-3'>"
                + "<select name='ddlcategory' CssClass='form-control' required><option value='grocery'>Grocery</option><option value='vegetable'>Vegetable</option><option value='dairy'>Dairy</option></select>"
                + "</div>"
                + "</div>"
                + "<div class='form-group'>"
                + "<label ID='Label1' CssClass='control-label col-md-2'>Sub Category<label>"
                + "<div class='col-md-3'>"
                + "<input type='text' name='txtsubcatname' CssClass='form-control' required></input>"
                + "</div>"
                + "</div>"
                + "<div class='form-group'>"
                + "<div class='col-md-2'></div>"
                + "<div class='col-md-3'>"
                + "<input type='submit' class='btn btn-danger'  value='ADD SUBCATEGORY' CssClass='col-xs-offset-0' border-style='none;' style='Width=303px; height:40px;font-size:15px; margin: 2rem; color:white; background: black;'></input>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "<!--------------Table starts------------>"
                + "<div style='margin-top: 40px;'>"
                + "<table class='table table-head-dark table-responsive table-bordered'>"
                + "<thead>"
                + "<tr>"
                + "<th scope='col' style='background-color: black; color: white;'>#</th>"
                + "<th scope='col' style='background-color: black; color: white;'>SubCategory Name</th>"
                + "<th scope='col' style='background-color: black; color: white;'>Edit</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>"
                + "<tr>"
                + "</tr>"
                + "</tbody>"
                + "</table>"
				+ "</div></div></div>");

	}
}
