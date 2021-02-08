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
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ShowStores")

/*
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Best Deal Application.

*/

public class ShowStores extends HttpServlet {
    String uname="",pwd="";
	String fname="",lname="",mobile="",strret="",city="",zipcode="",sname="",smobile="",semail="",sstreet="",scity="",szipcode="",username="",password="",simage="";
	int SID=0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        List<Store> storedata = new ArrayList<>();
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "7559");

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


		displaystores(request, response, pw, false);
	}


	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
	public void displaystores(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		//utility.printHtml("Content.html");
		pw.print("<div id='container' class=''>"
        + "<!--carousel-------------------------------------------------------->"
        + "<div id='carousel-example-generic' class='carousel slide' data-ride='carousel' style='padding-top: 57px;'>"
        + "<!-- Wrapper for slides -->"
        + "<div class='carousel-inner' role='listbox'>"
        + "<div class='item active'>"
        + "<img src='images/car2.jpg' style='height:300px; width:1500px;' />"
        + "</div>"
        + "</div>"
        + "</div>"
        + "<!--carousel----------------------------------------------------->"
        + "<div style='margin-top: 20px;'>"
        + "<div class='media' style='margin-left: 540px;'>"
        + "<div class='media-left'>"
        + "<a href='UserSearch?PID='>"
        + "<img class='media-object' src='images/loc.png' style='height: 60px; width: 60px;' alt='...'>"
        + "</a>"
        + "</div>"
        + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
        + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>NearBy Grocery Stores</h4>"
        + "</div>"
        + "</div>"
        + "</div>");
        HashMap<String,Products> selectedproducts=new HashMap<String,Products>();
		try
			{

		pw.print("<div id='content'>");

		pw.print("<div class='entry'>");


		String line=null;
		String TOMCAT_HOME = System.getProperty("catalina.home");

		HashMap<String,Products> productmap=MySqlDataStoreUtilities.getData();

		for(Map.Entry<String, Products> entry : productmap.entrySet())
		{

		if(selectedproducts.size()<3 && !selectedproducts.containsKey(entry.getKey()))
		{


		BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\EWA_Project\\DealMatches.txt")));
		line=reader.readLine().toLowerCase();


		if(line==null)
		{
			pw.print("<h2 align='center'>No Offers Found</h2>");
			break;
		}
		else
		{
		do {

				if(line.contains(entry.getKey()))
				{

				pw.print("<h2>"+line+"</h2>");
				pw.print("<br>");
				selectedproducts.put(entry.getKey(),entry.getValue());
				break;
				}

				}while((line = reader.readLine()) != null);

		 }
		 }
		}
		}
		catch(Exception e)
		{
		pw.print("<h2 align='center'>No Offers Found</h2>");
		}
    pw.print("</div></div>");



        pw.print("<div id='map'></div>"
        + "<!----------------------stores starts here----------------------->"
        + "<div class='container' style='padding-top: 30px;'>"
        + "<div class='row'>"

        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Whole Foods Market</h3>"
        + "<p>"
        + "1 N Halsted St, Chicago, IL 60661"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"


        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Trader Joe's</h3>"
        + "<p>"
        + "667 W Diversey Pkwy, Chicago, IL 60614"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"


        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Walmart Neighborhood Market</h3>"
        + "<p>"
        + "4720 S Cottage Grove Ave, Chicago, IL 60615"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"

        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Pete's Fresh Market</h3>"
        + "<p>"
        + "2333 W Madison St, Chicago, IL 60612"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"


        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Mariano's</h3>"
        + "<p>"
        + "1615 S Clark St, Chicago, IL 60616"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"


        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Patel Brothers</h3>"
        + "<p>"
        + "2610 W Devon Ave, Chicago, IL 60659"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"


        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Food 4 Less</h3>"
        + "<p>"
        + "112 W 87th St, Chicago, IL 60620"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"


        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Park To Shop Supermarket</h3>"
        + "<p>"
        + "2425 S Wallace St, Chicago, IL 60616"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"

        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Fresh Thyme Farmers Market</h3>"
        + "<p>"
        + "2500 N Elston Ave, Chicago, IL 60647"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"

        + "<div class='col-sm-5 col-md-3 hover-zoomin'>"
        + "<a href='#' style='text-decoration: none;'>"
        + "<div class='thumbnail'>"
        + "<a href='UserSearch'><img src='images/stores.jpg' style='height: 170px;' alt='...'></>"
        + "<div class='caption' style='height:140px;'>"
        + "<h3 class='txt8'>Fresh Market Place</h3>"
        + "<p>"
        + "2134 N Western Ave, Chicago, IL 60647"
        + "</p>"
        + "</div>"
        + "</div>"
        + "</a>"
        + "</div>"

        + "</div>"
        + "</div>"
        + "<!-------------------------------Stores end------------------------>"


        + ""
        + ""
		+ "</div></div></div>");

    pw.print("<div class='post'>");
	pw.print("<h2 class='title meta'>");
	pw.print("<a style='font-size: 24px;'>Deal Matches</a>");
	pw.print("</h2>");
	pw.print("<div class='entry'>");
	if(selectedproducts.size()==0)
	{
	pw.print("<h2 align='center'>No Deals Found</h2>");
	}
	else
	{
	pw.print("<table id='bestseller'>");
	pw.print("<tr>");
	for(Map.Entry<String, Products> entry : selectedproducts.entrySet()){
	pw.print("<td><div id='shop_item'><h3>"+entry.getValue().getName()+"</h3>");
	pw.print("<strong>"+entry.getValue().getPrice()+"$</strong>");
	pw.print("<ul>");
	pw.print("<li id='item'><img src='images/"+entry.getValue().getType()+"/"+entry.getValue().getImage()+"' alt='' />");
	pw.print("</li><li>");
	pw.print("<form action='Cart' method='post'><input type='submit' class='btnbuy' value='Buy Now'>");
	pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
	pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
	pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getRetailer()+"'>");
	pw.print("<input type='hidden' name='access' value=''>");
	pw.print("</form></li><li>");
	pw.print("<form action='WriteReview' method='post'><input type='submit' class='btnreview' value='WriteReview'>");
	pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
	pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
	pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getRetailer()+"'>");
	pw.print("<input type='hidden' name='price' value='"+entry.getValue().getPrice()+"'>");
	pw.print("</form></li>");
	pw.print("<li>");
	pw.print("<form action='ViewReview' method='post'><input type='submit' class='btnreview' value='ViewReview'>");
	pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
	pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
	pw.print("</form></li></ul></div></td>");
	}
	pw.print("</tr></table>");
	}
	pw.print("</div></div></div>");
	}

}
