import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.lang.*;

@WebServlet("/UserSearchProductView")

/*
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Best Deal Application.

*/

public class UserSearchProductView extends HttpServlet {

	String PName="",PPrice="",PSelPrice="",PBrand="",Pcategory="",PSubCategory="",PSize="",PQuantity="",PDescription="",PDetail="",Pimage="",PDelivery="",PReturn="";
    int SID=0;
    int PID=0;
    String productid="";
    String productname="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        productname = request.getParameter("mypid");
        productid=request.getParameter("mypid");

        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "Ilovemyself");



            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products where PID= '1'");
            while (rs.next())
            {
                PID = rs.getInt("PID");
                PName = rs.getString("PName");
                PPrice = rs.getString("PPrice");
				PSelPrice  = rs.getString("PSelPrice");
				PBrand  = rs.getString("PBrand");
				Pcategory  = rs.getString("Pcategory");
				PSubCategory  = rs.getString("PSubCategory");
				PSize  = rs.getString("PSize");
				PQuantity  = rs.getString("PQuantity");
				PDescription  = rs.getString("PDescription");
				PDetail  = rs.getString("PDetail");
				Pimage  = rs.getString("Pimage");
				PDelivery  = rs.getString("PDelivery");
				PReturn  = rs.getString("PReturn");

            }
        }
        catch (Exception e)
        {

        }


		displayproducts(request, response, pw, false);
	}


	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
	public void displayproducts(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		//utility.printHtml("Content.html");
		pw.print("<div class='container' style='margin-top: 100px;'>"
        + "<div class='col-md-5'>"
        + "<div style='max-width: 480px' class='thumbnail'>"
        + "<img src='images/fvs/avocados.jpg' >"
        + "</div>"
        + "</div>"
        + "<div class='col-md-7'>"
        + "<div class='divdotline'>"
        + "<h1 class='proNameView'>"+PName+"</h1>"
        + "<span class='proogpriceview'>"+PPrice+"</span><span class='propricediscountview'> 10% OFF</span>"
        + "<p class='propriceview'>"+PSelPrice+"</p>"
        + "</div>"
        + "<div>"
        + "<h5 class='h5size'>Quantity</h5>"
        + "<div>"
        + "<h5 class='h5size'>"+PQuantity+" lb</h5>"
        + "</div>"
        + "</div>"
        + "<div>"
        + "<input type='submit' value='Buy Now' Style='margin-top: 13px;margin-right: 10px;text-transform: uppercase;background:#009900;BorderColor:#009900; BorderStyle:None; Font-Bold:True; color:White; Height:30px;></input>"
        + "<hr />"
        + "</div>"
        + "<div>"
        + "<input type='submit' value='Buy Now' Style='margin-top: 13px; margin-right: 10px; text-transform: uppercase; background:#009900; BorderColor:#009900; BorderStyle:None; Font-Bold:True; color:White; Height:30px;'></input>"
        + "<hr />"
        + "</div>"
        + "<div class='divdotline'>"
        + "<h5 class='h5size'>Description</h5>"
        + "<p>"+PDescription+"</p>"
        + "<h5 class='h5size'>Product details</h5>"
        + "<p></p>"
        + "</div>"
        + "<div>"
        + "<p>Free Standard Delivery</p>"
        + "<p>7 Day Return</p>"
        + "<p>Happy Shopping!</p>"
        + "</div>"
        + " </div>"
		+ "</div>");
	}

}
