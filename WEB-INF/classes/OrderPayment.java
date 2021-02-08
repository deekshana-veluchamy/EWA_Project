import java.io.IOException;
import java.io.*;


/*
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno

	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private double orderPrice;
	private int quantity;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String orderDate;
	private String deliveryDate;
	private String status;
	private String creditCardNo;
	private String deliverables;
	private String storeaddress;

	public OrderPayment(int orderId,String userName,String orderName,double orderPrice,int quantity,String street, String city,String state,String zipcode,String creditCardNo, String orderDate, String deliveryDate, String status, String deliverables, String storeaddress){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.quantity = quantity;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode=zipcode;
	 	this.creditCardNo=creditCardNo;
		this.orderDate=orderDate;
		this.deliveryDate=deliveryDate;
		this.status = status;
		this.storeaddress = storeaddress;
		this.deliverables = deliverables;
		}

		public OrderPayment(){

		}
		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}





		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	public String getStoreAddress() {
		return storeaddress;
	}

	public void setStoreAddress(String storeaddress) {
		this.storeaddress = storeaddress;
	}

	public String getDeliverables() {
		return deliverables;
	}

	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public void updateOrder(OrderPayment updateOrder){
		this.zipcode = updateOrder.zipcode;
	}


}
