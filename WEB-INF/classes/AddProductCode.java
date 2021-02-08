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

@WebServlet("/AddProductCode")

public class AddProductCode extends HttpServlet {
    public static final String UPLOAD_DIR = "productImages";
    // database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/groceryhub";
    private String dbUser = "root";
    private String dbPass = "Ilovemyself";
    Integer SID=0;
    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);
        String msg = "good";
        SID = (Integer)(request.getAttribute("SID"));

        String txtpname= "",  brand="",category="",subcategory="",txtsize = "",txtdescription="",txtqunatity="", txtpdetail="",pdelivery="",preturn="";
        double txtprice=0.0,txtselprice = 0.0;

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

        String contentType = request.getContentType();
        System.out.println(contentType);

        Part part = request.getPart("pimage");
        String fileName=extractFileName(part); //file name

        /**
         * *** Get The Absolute Path Of The Web Application ****
         */
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        //System.out.println("applicationPath:" + applicationPath);
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);
        String sRootPath = new File(savePath).getAbsolutePath();
        //System.out.println("sRootPath: " + sRootPath);
        part.write(savePath + File.separator);
        File fileSaveDir1 = new File(savePath);

        //dbFileName = UPLOAD_DIR + File.separator + fileName;
        part.write(savePath + File.separator);

        pdelivery = request.getParameter("pdelivery");
        preturn = request.getParameter("preturn");
        SID = (Integer)(request.getAttribute("SID"));





        Connection conn = null; // connection to the database

        utility.printHtml("RetailerHeader.html");
        msg = "Product is added successfully";
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            // constructs SQL statement
            String addProductQurey = "INSERT INTO  products(PID,PName,PPrice,PSelPrice,PBrand,Pcategory,PSubCategory,PSize,PQuantity,PDescription,PDetail,Pimage,PDelivery,PReturn)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

                PreparedStatement pst = conn.prepareStatement(addProductQurey);
                pst.setString(1,"grocery");
                pst.setString(2,txtpname);
                pst.setDouble(3,txtprice);
                pst.setDouble(4,txtselprice);
                pst.setString(5,brand);
                pst.setString(6,category);
                pst.setString(7,subcategory);
                pst.setString(8,txtsize);
                pst.setString(9,txtqunatity);
                pst.setString(10,txtdescription);
                pst.setString(11,txtpdetail);
                pst.setString(12,savePath);
                pst.setString(13,pdelivery);
                pst.setString(14,preturn);


                pst.executeUpdate();
        }
        catch(Exception e)
		{
			msg = "Product cannot be inserted";
		}



        pw.print("<div id='container'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<h4 style='color:blue'>"+msg+"</h4>");
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");
    }

    private String extractFileName(final Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
