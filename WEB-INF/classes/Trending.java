import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

    ArrayList<Mostsold> mostsold = new ArrayList<Mostsold>();
    ArrayList<Mostsoldzip> mostsoldzip = new ArrayList<Mostsoldzip>();
    ArrayList<Bestrating> bestrated = new ArrayList<Bestrating>();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        mostsold = MongoDBDataStoreUtilities.mostsoldProducts();
        //System.out.println("mostsold size:" + mostsold.size());
        mostsoldzip = MongoDBDataStoreUtilities.mostsoldZip();
        System.out.println("mostsoldzip: "+mostsoldzip.size());
        bestrated = MongoDBDataStoreUtilities.topProducts();

        for (Mostsoldzip mostsoldzip1: mostsoldzip){
            System.out.println(mostsoldzip1.getZipcode());
            System.out.println(mostsoldzip1.getCount());
        }


        String name = "Trending";
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        //utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='container'>");
    		pw.print("<br />");
    		pw.print("<br />");
    		pw.print("<br />");


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
    		+ "<form action='Logout'>"
    		+ "<input type='submit' class='btnbuy'  value='LOGOUT' CssClass='col-xs-offset-0' border-style='none;' style=' width: 100%; height:30px; color:black; background: white;'></input>"
    		+ "</form>"
    		+ "</div>");
    		pw.print("<div class='text-center' >"

    		+ "</div>");
    		pw.print("<div style='margin-top: 20px;'>"
    	        + "<div class='media' style='margin-left: 540px;'>"
    	           + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
    	               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Trending</h4>"
    	           + "</div></div></div>");
    		//pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

    		pw.print("<div class='container' style='padding-top: 30px;'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Top 5 most liked products</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        Iterator itr2 = bestrated.iterator();
        while (itr2.hasNext()) {
            Bestrating best = (Bestrating) itr2.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(best.getProductname().replace("_"," "));
            pw.print("</td>");
            pw.print("<td>");
            pw.print(best.getRating());
            pw.print("</td>");
            pw.print("</tr>");
        }
        pw.print("</table></div>");

        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Top 5 zip-codes where maximum number of products sold</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        Iterator itr1 = mostsoldzip.iterator();
        while (itr1.hasNext()) {
            Mostsoldzip mostzip = (Mostsoldzip) itr1.next();
            pw.print("<tr>");
            pw.println("<td border: 1px >");
            System.out.println(mostzip.getZipcode());
            pw.println(mostzip.getZipcode());
            pw.println("</td>");
            pw.println("<td border: 1px >");
            pw.println(mostzip.getCount());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.print("</table></div>");

        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Top 5 most sold products regardless of the rating</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");

        Iterator itr = mostsold.iterator();
        while (itr.hasNext()) {
            Mostsold most = (Mostsold) itr.next();
            pw.println("<tr>");
            pw.println("<td border: 1px >");
            pw.println(most.getProductname().replace("_"," "));
            pw.println("</td>");
            pw.println("<td border: 1px >");
            pw.println(most.getCount());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.print("</table></div></div></div></div>");

        //	pw.print("</table></div></div></div>");
        utility.printHtml("Footer.html");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }

}
