import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub","root","Ilovemyself");
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}

	public  StringBuffer readdata(String searchId)
	{
		HashMap<String,Products> data;
		data=getData();

 	    Iterator it = data.entrySet().iterator();
        while (it.hasNext())
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Products p=(Products)pi.getValue();
                if (p.getName().toLowerCase().startsWith(searchId))
                {
                        sb.append("<product>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<productName>" + p.getName() + "</productName>");
                        sb.append("</product>");
                }
			}
       }

	   return sb;
	}

	public static HashMap<String,Products> getData()
	{
		HashMap<String,Products> hm=new HashMap<String,Products>();
		try
		{
			getConnection();

		    String selectproduct="select * from  Productdetails";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();

			while(rs.next())
			{	Products p = new Products(rs.getString("productId"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("ProductType"),rs.getDouble("productDiscount"),rs.getDouble("quantity"));
				hm.put(rs.getString("productId"), p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;
	}


}
