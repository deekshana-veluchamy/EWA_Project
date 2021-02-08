import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@WebServlet("/CustomerHome")

public class CustomerHome extends HttpServlet {
  String uname="",pwd="";
	String fname="",lname="",mobile="",strret="",city="",zipcode="",sname="",smobile="",semail="",sstreet="",scity="",szipcode="",username="",password="",simage="";
	int SID=0;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
    PrintWriter pw = response.getWriter();

    List<Store> storedata = new ArrayList<>();
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "Ilovemyself");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from stores");
            while (rs.next())
            {
                SID = rs.getInt("SID");
                fname = rs.getString("fname");
                lname = rs.getString("lname");
				mobile  = rs.getString("mobile");
				strret  = rs.getString("strret");
				city  = rs.getString("city");
				zipcode  = rs.getString("zipcode");
				sname  = rs.getString("sname");
				smobile  = rs.getString("smobile");
				semail  = rs.getString("semail");
				sstreet  = rs.getString("sstreet");
				scity  = rs.getString("scity");
				szipcode  = rs.getString("szipcode");
				username  = rs.getString("username");
				password  = rs.getString("password");
				simage  = rs.getString("simage");

                Store store = new Store(SID, fname,lname,mobile,strret,city,zipcode,sname,smobile,semail,sstreet,scity,szipcode,username,password,simage);

                storedata.add(store);
            }
        }
        catch (Exception e)
        {

        }
		displaystore(request, response, pw, false);
    }


    protected void displaystore(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<br></br>");

    pw.print("<div class='container-fluid' style='margin-top: 80px;'>"
        + "<div class='col-sm-2' style='background-color: #22C125; height: 690px;'>"
        + "<center>"
				+ "<a href='ShowStores'> <p style='color:white; margin-top:19px;  font-family:Lucida Sans; font-size:25px;'>DASHBOARD</p></a>"
        + "<div style='height: 3px; width: 100%;background-color: white;'></div>"
        + "<img src='images/Retailer_Dashboard/guy.png' style='margin-top:65px; height:130px; width:130px;' class='img-circle'/>"
        + "<center><p style='color:white; font-family:Lucida Sans; font-size:19px; margin-top:5px;'>"+fname+" "+lname+"</p></center>"
        + "</center>"
        + "<div style='margin-top: 35px;'></div>"
				+ "<form action='CustomerProfile'><input type='submit' class='btnbuy' action='MyStore'  value='Profile' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy submit-button'  value='Home' action='/AddBrand' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='Favourite' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "<form action='#'><input type='submit' class='btnbuy'  value='History' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input></form>"
				+ "</div>"
        + "<div class='col-sm-10'>"
        + "<!----------------------stores starts here----------------------->"
        + "<div class='container' style='padding-top: 30px;'>"
        + "<div class='row'>"
        + "<c:forEach items='${storedata}' var='store'>"
        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='${store.simage}' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>${store.sname}</h3>"
        + "<p>"
        + "${store.sstreet}"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + "</div>"
        + "</div>"
        + "<!-------------------------------Stores end------------------------>"
				+ "</div></div>");


	}
}
