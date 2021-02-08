import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class RestaurantRecommenderUtility{

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

	public HashMap<String,String> readOutputFile(){

		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> restRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\EWA_Project\\Recommendation.csv")));
			while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] rest_recm = line.split(cvsSplitBy,2);
				restRecmMap.put(rest_recm[0],rest_recm[1]);
            }

		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}

		return restRecmMap;
	}

	public static Restaurant getRestaurant(String restaurant){
		Restaurant restObj = new Restaurant();
		try
		{
			String msg = getConnection();
					String selectProd="select * from  restaurants where restaurantId=?";
					PreparedStatement pst = conn.prepareStatement(selectProd);
					pst.setString(1,restaurant);
					//pst.setString(2,"Yes");
					//pst.setString(3,"Disputed");
					ResultSet rs = pst.executeQuery();

					while(rs.next())
					{
						restObj = new Restaurant(rs.getString("restaurantId"),rs.getString("restaurantName"),rs.getString("address"),rs.getString("zipcode"),rs.getString("image"));
					}
					rs.close();
					pst.close();
			conn.close();
		}
		catch(Exception e)
		{
		}
		return restObj;
	}
}
