/* This code is used to implement carousel feature in Website. Carousels are used to implement slide show feature. This  code is used to display
all the accessories related to a particular product. This java code is getting called from cart.java. The product which is added to cart, all the
accessories realated to product will get displayed in the carousel.*/


import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;



public class Carousel{

	public String  carouselfeature(Utilities utility){

		RestaurantRecommenderUtility prodRecUtility = new RestaurantRecommenderUtility();
		System.out.println("inside caurosel");



		StringBuilder sb = new StringBuilder();
		String myCarousel = null;

		String name = null;
		String CategoryName = null;

		HashMap<String,String> resRecmMap = new HashMap<String,String>();
		resRecmMap = prodRecUtility.readOutputFile();
		int l =0;
		for(String user: resRecmMap.keySet())
		{
			System.out.println("inside for");

				System.out.println("inside if");
				String restaurants = resRecmMap.get(user);
				restaurants=restaurants.replace("[","");
				restaurants=restaurants.replace("]","");
				restaurants=restaurants.replace("\"", " ");
				ArrayList<String> restaurantsList = new ArrayList<String>(Arrays.asList(restaurants.split(",")));

		        myCarousel = "myCarousel"+l;

				sb.append("<div id='content'><div class='post'><h2 class='title meta'>");
				sb.append("<a style='font-size: 24px;'>"+""+" Recommended Nearby Restaurant</a>");

				sb.append("</h2>");

				sb.append("<div class='container'>");
				/* Carousels require the use of an id (in this case id="myCarousel") for carousel controls to function properly.
				The .slide class adds a CSS transition and animation effect, which makes the items slide when showing a new item.
				Omit this class if you do not want this effect.
				The data-ride="carousel" attribute tells Bootstrap to begin animating the carousel immediately when the page loads.

				*/
				sb.append("<div class='carousel slide' id='"+myCarousel+"' data-ride='carousel'>");

				/*The slides are specified in a <div> with class .carousel-inner.
				The content of each slide is defined in a <div> with class .item. This can be text or images.
				The .active class needs to be added to one of the slides. Otherwise, the carousel will not be visible.
				*/
				sb.append("<div class='carousel-inner'>");

				int k = 0;
				for(String prod : restaurantsList){
					prod= prod.replace("'", "");
					Restaurant prodObj = new Restaurant();
					prodObj = RestaurantRecommenderUtility.getRestaurant(prod.trim());
					if (k==0 )
					{

						sb.append("<div class='item active'><div class='col-md-6'");
					}
					else
					{
						sb.append("<div class='item'><div class='col-md-6' >");
					}
					sb.append("<div id='shop_item'>");
					sb.append("<h3>"+prodObj.getName()+"</h3>");
					sb.append("<li id='item'><img src='images/restaurants/"+prodObj.getImage()+"' alt='' /></li>");
					sb.append("<h2>"+prodObj.getAddress()+"</h2>");
					sb.append("<h2>"+prodObj.getZipcode()+"</h2>");


					sb.append("</ul></div></div>");
					sb.append("</div>");

									sb.append("</div>");

									sb.append("</div></div>");
									sb.append("</div>");


					k++;

				}




				l++;


			}
			return sb.toString();
		}
	}
