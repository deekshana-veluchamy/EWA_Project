import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Beverages")


/*
	Beverages class contains class variables name,price,image,category,condition,discount and Accessories Hashmap.

	Beverages class constructor with Arguments name,price,image,category,condition,discount and Accessories .

	Accessory class contains getters and setters for name,price,image,category,condition,discount and Accessories .

*/

public class Beverages extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private double discount;
	private double quantity;
	private String manufacturerRebate;

	public Beverages(String name, double price, String image, String retailer,double discount){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.discount = discount;

	}
	public Beverages(String name, double price, String image, String retailer,double discount,double quantity, String manufacturerRebate){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.discount = discount;
		this.quantity = quantity;
		this.manufacturerRebate = manufacturerRebate;
	}



	public Beverages(){

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}


	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getManufacturerRebate() {
		return manufacturerRebate;
	}

	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}

}
