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

@WebServlet("/UpdateProductCode")

public class UpdateProductCode extends HttpServlet {
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

        String txtpname= "",pimg="", brand="",category="",subcategory="",txtqunatity="",txtsize = "",txtdescription="", txtpdetail="",pdelivery="",preturn="";
        double txtprice=0.0,txtselprice = 0.0;

        pid=request.getParameter("txtpid");
        txtpname = request.getParameter("txtpname");
        txtprice   = Double.parseDouble(request.getParameter("txtprice"));
    		txtselprice = Double.parseDouble(request.getParameter("txtselprice"));
        brand = request.getParameter("brand");
        category = request.getParameter("category");
        subcategory = request.getParameter("subcategory");
        txtsize = request.getParameter("txtsize");
        txtqunatity = request.getParameter("txtqunatity");
        txtdescription = request.getParameter("txtdescription");
        txtpdetail = request.getParameter("txtpdetail");
        pimg=request.getParameter("pimage");
        pdelivery = request.getParameter("pdelivery");
        preturn = request.getParameter("preturn");
        SID = (Integer)(request.getAttribute("SID"));

        System.out.println(pid);





        Connection conn = null; // connection to the database

        utility.printHtml("Header.html");
        msg = "Product is updated successfully";
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            // constructs SQL statement
            String updateProductQurey = "UPDATE products SET PName=?,PPrice=?,PSelPrice=?,PBrand=?,Pcategory=?,PSubCategory=?,PSize=?,PQuantity=?,PDescription=?,PDetail=?,PDelivery=?,PReturn=? where PID =?;" ;



			PreparedStatement pst = conn.prepareStatement(updateProductQurey);

			pst.setString(1,txtpname);
			pst.setDouble(2,txtprice);
			pst.setDouble(3,txtselprice);
			pst.setString(4,brand);
			pst.setString(5,category);
			pst.setString(6,subcategory);
            pst.setString(7,txtsize);
            pst.setString(8,txtqunatity);
            pst.setString(9,txtdescription);
            pst.setString(10,txtpdetail);
            pst.setString(11,pdelivery);
            pst.setString(12,preturn);
            pst.setString(13,pid);
			pst.executeUpdate();
        }
        catch(Exception e)
		{
			msg = "Product cannot be updated";
		}



        pw.print("<div id='container'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<h4 style='color:blue'>"+msg+"</h4>");
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");
    }



}
