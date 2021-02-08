import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/*
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.

	Users  class contains getters and setters for id,name,password,usertype.

*/

public class Retailer implements Serializable{
	private int id;
	private String fname;
	private String lname;
	private String mobile;
  private String street;
  private String city;
  private String zipcode;
  private String sname;
  private String smobile;
  private String semail;
  private String sstreet;
  private String scity;
  private String szipcode;
  private String username;
  private String password;
	private String repassword;
	private String usertype;
	private byte[] simage;

	public Retailer(String fname,String lname,String mobile,String street,String city,String zipcode,String sname,String smobile,String semail,String sstreet,String scity,String szipcode,String username,String password,String repassword) {
		this.fname=fname;
		this.lname=lname;
		this.street=street;
		this.city = city;
		this.zipcode = zipcode;
		this.mobile = mobile;
		this.sname = sname;
		this.semail = semail;
		this.smobile = smobile;
		this.sstreet=sstreet;
		this.scity = scity;
		this.szipcode = szipcode;
		this.username=username;
		this.password=password;
		this.repassword=repassword;


	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return fname;
	}

	public void setName(String fname) {
		this.fname = fname;
	}

	public String getLName() {
		return lname;
	}

	public void setLName(String lname) {
		this.lname = lname;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSStreet() {
		return sstreet;
	}

	public void setSStreet(String sstreet) {
		this.sstreet = sstreet;
	}

	public String getSCity() {
		return scity;
	}

	public void setSCity(String scity) {
		this.scity = scity;
	}

	public String getSZipcode() {
		return szipcode;
	}

	public void setSZipcode(String szipcode) {
		this.szipcode = szipcode;
	}




	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getSName() {
		return sname;
	}

	public void setSName(String Sname) {
		this.sname = sname;
	}
	public String getSEmail() {
		return semail;
	}

	public void setSEmail(String semail) {
		this.semail = semail;
	}
	public String getSMobile() {
		return smobile;
	}

	public void setSMobile(String smobile) {
		this.smobile = smobile;
	}


	public String getrePassword() {
		return password;
	}

	public void setrePassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public byte[] getSImage() {
        return simage;
    }

    public void setSImage(byte[] simage) {
        this.simage = simage;
    }
}
