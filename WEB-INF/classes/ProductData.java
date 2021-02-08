
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/ProductData")
public class ProductData extends HttpServlet {


public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  try{

PrintWriter pw= response.getWriter();

  Utilities utility = new Utilities(request,pw);
  utility.printHtml("Header.html");
  //utility.printHtml("LeftNavigationBar.html");
  pw.print("<div id='container'>");
  pw.print("<br />");
  pw.print("<br />");
  pw.print("<br />");

  if(utility.isLoggedin()){
  pw.print("<div class='container-fluid' style='margin-top: 60px;'>"
          + "<div class='col-sm-2' style='background-color: black; height: 690px;'>"
  + "<center>"
  + "<a href='CustomerProfile'> <p style='color:white; margin-top:19px;  font-family:Lucida Sans; font-size:25px;'>DASHBOARD</p></a>"
          + "<div style='height: 3px; width: 100%;background-color: white;'></div>"
          + "<img src='images/Profile/man1.jpg' style='margin-top:65px; height:100px; width:100px;' class='img-circle'/>"
          + "<center><p style='color:white; font-family:Lucida Sans; font-size:19px; margin-top:5px;'>Hi John</p></center>"
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
  + "</div>");}
  pw.print("<div class='text-center' >"

  + "</div>");
  pw.print("<div style='margin-top: 20px;'>"
        + "<div class='media' style='margin-left: 540px;'>"
           + "<div class='media-body' style='margin-top: 20px; padding-top: 20px;'>"
               + "<h4 class='media-heading' style='font-family: Verdana; font-weight: 800; font-size:24px;'>Products</h4>"
           + "</div></div></div>");
  //pw.print("<a style='font-size: 24px;'>"+name+" Grocery</a>");

  pw.print("<div class='container' style='padding-top: 30px;'>");

  pw.print("<div class='entry'><table id='bestseller'>");
  pw.print("<tr>");
  pw.print("<td><div id='shop_item'>");
    Products data= (Products)request.getAttribute("data");
    pw.print("<h3>"+data.getName()+"</h3>");
    pw.print("<strong>$"+data.getPrice()+"</strong><ul>");
    pw.print("<li id='item'><img src='images/"+data.getType()+"/"+data.getImage()+"' alt='' /></li>");
    pw.print("<li><form method='post' action='Cart'>" +
        "<input type='hidden' name='name' value='"+data.getId()+"'>"+
        "<input type='hidden' name='type' value='"+data.getType()+"'>"+
        "<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
        "<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
        "<input type='hidden' name='access' value=''>"+
        "<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
    pw.print("<li><form method='post' action='WriteReview'>"+
    "<input type='hidden' name='name' value='"+data.getId()+"'>"+
        "<input type='hidden' name='type' value='"+data.getType()+"'>"+
        "<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
        "<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
        "<input type='hidden' name='access' value=''>"+
            "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
    pw.print("<li><form method='post' action='ViewReview'>"+
        "<input type='hidden' name='name' value='"+data.getId()+"'>"+
        "<input type='hidden' name='type' value='"+data.getType()+"'>"+
        "<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
        "<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
        "<input type='hidden' name='access' value=''>"+
          "<input type='submit' value='ViewReview' class='btnreview'></form></li>");

    pw.print("</ul></div></td>");
    pw.print("</tr>");
    pw.print("</table></div></div></div>");
  utility.printHtml("Footer.html");
  }
  catch(Exception e)
  {

  }
}

public void destroy()	{
    // do nothing.
}


}
