import java.util.*;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Restaurant")



public class Restaurant extends HttpServlet{
	private String restaurantId;
	private String restaurantName;
	private String address;
	private String zipcode;
	private String image;


	public Restaurant(String restaurantId,String restaurantName, String address, String zipcode,String image){
		this.restaurantId=restaurantId;
		this.restaurantName=restaurantName;
		this.address=address;
		this.zipcode=zipcode;
		this.image = image;
	}


	public Restaurant(){

	}

	public String getId() {
		return restaurantId;
	}
	public void setId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return restaurantName;
	}
	public void setName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
