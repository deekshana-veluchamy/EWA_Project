import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Part;
import java.sql.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

@WebServlet("/DeleteProductCode")

public class DeleteProductCode extends HttpServlet {
    public static final String UPLOAD_DIR = "productImages";
    // database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/groceryhub";
    private String dbUser = "root";
    private String dbPass = "Ilovemyself";
    Integer SID=0;
    String pid="";
    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);
        String msg = "good";
        //SID = (Integer)(request.getAttribute("SID"));

        String txtpname= "",pimg="", brand="",category="",subcategory="",txtsize = "",txtqunatity="",txtdescription="", txtpdetail="",pdelivery="",preturn="";
        double txtprice=0.0,txtselprice = 0.0;

        pid=request.getParameter("txtpid");
        





        Connection conn = null; // connection to the database

        utility.printHtml("Header.html");
        msg = "Product is deleted successfully";
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
		String deleteproductsQuery ="Delete from products where PID=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,pid);

		pst.executeUpdate();
        }
        catch(Exception e)
		{
			msg = "Product cannot be deleted";
		}



        pw.print("<div id='container'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<h4 style='color:green'>"+msg+"</h4>");
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");
    }



}
