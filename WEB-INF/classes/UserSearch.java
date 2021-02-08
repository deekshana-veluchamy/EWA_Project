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

@WebServlet("/UserSearch")

/*
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Best Deal Application.

*/

public class UserSearch extends HttpServlet {

	String PName="",PPrice="",PSelPrice="",PBrand="",Pcategory="",PSubCategory="",PSize="",PQuantity="",PDescription="",PDetail="",Pimage="",PDelivery="",PReturn="";
    int SID=0;
    int PID=0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        List<Product> productdata = new ArrayList<>();
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "7559");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products where SID= '"+SID+"'");
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


                Product product = new Product(PID, PName,PPrice,PSelPrice,PBrand,Pcategory,PSubCategory,PSize,PQuantity,PDescription,PDetail,Pimage,PDelivery,PReturn);

                productdata.add(product);
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
        pw.print("<div class='container-fluid' style='margin-top: 80px;'>"
        + ""
        +"<div class='row' style='margin-top: 80px;'>"
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<input type='hidden' id='mypid' value='1' name='mypid'>"
        + "<img src='images/fvs/avocados.jpg'>"
        + "<div class='caption'>"
        + "<div class='probrand' name='productname' value='Avocado'>Avocado</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 2.99</span>   2.00<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 10% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/fvs/garlic.jpeg'>"
        + "<div class='caption'>"
        + "<div class='probrand' style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>Garlic</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 4.99</span>   3.00<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 10% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/fvs/carrots.jpg'>"
        + "<div class='caption'>"
        + "<div class='probrand' style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>Carrot</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 3.99</span>   2.00<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 30% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/fvs/onion.jpg'>"
        + "<div class='caption'>"
        + "<div class='probrand' style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>Onion-red</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 1.99</span>   0.99<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 20% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/fvs/redraddish.jpeg'>"
        + "<div class='caption'>"
        + "<div class='probrand' style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>RedRaddish</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 4.99</span>   1.99<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 40% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/fvs/lettuce.jpeg'>"
        + "<div class='caption'>"
        + "<div class='probrand' style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>Green Leaf Lettuce</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 4.99</span>   1.99<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 40% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/frozens/chocoice.jpeg'>"
        + "<div class='caption'>"
        + "<div class='probrand'  style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>Great Value Cookies & Cream Ice Cream, 48 fl oz</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 5.99</span>   4.99<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 20% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/frozens/onionrings.jpeg'>"
        + "<div class='caption'>"
        + "<div class='probrand' style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>Tyson Fully Cooked Crispy Chicken Strips, 25 oz</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 3.99</span>   2.99<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 20% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
        + "<div class='col-sm-3 col-md-3'>"
        + "<a style='text-decoration:none;' href='UserSearchProductView'>"
        + "<div class='thumbnail'>"
        + "<img src='images/frozens/breakfastwraps.jpeg'>"
        + "<div class='caption'>"
        + "<div class='probrand' style='font-size:15px;font-weight:600;line-height:15px; text-transform:uppercase;font-family:sans-serif;'>The Cheesecake Factory At Home</div>"
        + "<div class='proname' style='font-size:13px; line-height:17px;font-family:sans-serif;'>Buy fresh with us!</div>"
        + "<div class='proprice' style='font-size: 14px;line-height: 17px;font-family: sans-serif;font-weight:600;'><span class='proogprice' style='font-size: 13px; text-decoration:line-through;font-family: sans-serif;font-weight: 400;color:grey;'> 1.99</span>   0.99<span class='propricediscount' style='font-size: 13px;font-family: sans-serif;font-weight: 400;color: #22C125;'> 20% Discount</span></div>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"
        + ""
        + ""
		+ "</div></div>");
	}

}
