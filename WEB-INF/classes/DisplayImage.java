import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/DisplayImage")
public class DisplayImage extends  HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse
  response) throws ServletException, IOException{
  //PrintWriter pw = response.getWriter();
  PrintWriter pw= response.getWriter();

    Utilities utility = new Utilities(request,pw);
    utility.printHtml("Header.html");


  try{
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "Ilovemyself");
  Statement st1=con.createStatement();
  ResultSet rs1 = st1.executeQuery("SELECT Data FROM images WHERE id ='1'");
  String imgLen="";
  if(rs1.next()){
  imgLen = rs1.getString(1);
  System.out.println(imgLen.length());
  }
  rs1 = st1.executeQuery
  ("SELECT Data FROM images WHERE id ='1'");
  if(rs1.next()){
  int len = imgLen.length();
  byte [] rb = new byte[len];
  InputStream readImg = rs1.getBinaryStream(1);
  int index=readImg.read(rb, 0, len);
  System.out.println("index"+index);
  st1.close();
  response.reset();
  response.setContentType("image/jpg");
  response.getOutputStream().write(rb,0,len);
  response.getOutputStream().flush();
  }
  }
  catch (Exception e){
  e.printStackTrace();
  }
  }
}
