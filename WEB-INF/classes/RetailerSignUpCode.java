import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.lang.Object;
import java.beans.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Part;

@WebServlet("/RetailerSignUpCode")

public class RetailerSignUpCode extends HttpServlet {
    // database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/groceryhub";
    private String dbUser = "root";
    private String dbPass = "Ilovemyself";

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String msg = "good";

        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String mobile=request.getParameter("mobile");
        String strret=request.getParameter("street");
        String city=request.getParameter("city");
        String zipcode=request.getParameter("zipcode");
        String sname=request.getParameter("sname");
        String smobile=request.getParameter("smobile");
        String semail=request.getParameter("semail");
        String sstreet=request.getParameter("sstreet");
        String scity=request.getParameter("scity");
        String szipcode=request.getParameter("szipcode");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        InputStream inputStream = null;
        Part filePart = request.getPart("Simage");

        String photo="";
        String path="/images/RetailerImages";

        File file=new File(path);
        file.mkdir();
        String fileName = getFileName(filePart);

        OutputStream out = null;

        InputStream filecontent = null;

        PrintWriter writer = response.getWriter();

        Connection conn = null; // connection to the database

        utility.printHtml("Header.html");
        msg = "Product is added successfully";
        try
        {
            out = new FileOutputStream(new File(path + File.separator
                + fileName));
            filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1)
             {
                out.write(bytes, 0, read);
                photo=path+"/"+fileName;
             }

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String addProductQurey = "INSERT INTO stores(fname,lname,mobile,strret,city,zipcode,sname,smobile,semail,sstreet,scity,szipcode,username,password,simage)" +
		    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1,fname);
            pst.setString(2,lname);
            pst.setString(3,mobile);
            pst.setString(4,strret);
            pst.setString(5,city);
            pst.setString(6,zipcode);
            pst.setString(7,sname);
            pst.setString(8,smobile);
            pst.setString(9,semail);
            pst.setString(10,sstreet);
            pst.setString(11,scity);
            pst.setString(12,szipcode);
            pst.setString(13,username);
            pst.setString(14,password);
            pst.setString(15,photo);
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
}
