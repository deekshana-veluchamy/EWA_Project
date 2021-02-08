import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/*
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.

	Users  class contains getters and setters for id,name,password,usertype.

*/

public class Store implements Serializable{
	private int SID;
	private String fname;
	private String lname;
	private String strret;
	private String city;
	private String zipcode;
	private String smobile;
  private String mobile;
	private String sname;
  private String semail;
	private String sstreet;
	private String scity;
	private String szipcode;
	private String username;
	private String password;
  private String simage;
  
  public Store(){

	}

  public Store(int SID, String fname,String lname, String mobile, String strret, String city, String zipcode, String sname, String smobile, String semail, String sstreet, String scity, String szipcode, String username, String password, String simage)
    {

        this.SID = SID;
        this.fname = fname;
        this.lname=lname;
        this.mobile=mobile;
        this.strret=strret;
        this.city=city;
        this.zipcode=zipcode;
        this.sname=sname;
        this.smobile=smobile;
        this.semail=semail;
        this.sstreet=sstreet;
        this.scity=scity;
        this.szipcode=szipcode;
        this.username=username;
        this.password=password;
        this.simage=simage;
    }

    public int getSID()
    {
        return SID;
    }
    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getFname()
    {
        return fname;
    }
    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }
    public void setLname(String lname)
    {
        this.lname=lname;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile=mobile;
    }

    public String getStrret()
    {
        return strret;
    }
    public void setStrret(String strret)
    {
        this.strret=strret;
    }

    public String getCity()
    {
        return city;
    }
    public void setCity(String mobile)
    {
        this.city=city;
    }

    public String getZipcode()
    {
        return zipcode;
    }
    public void setZipcode(String zipcode)
    {
        this.zipcode=zipcode;
    }

    public String getSname()
    {
        return sname;
    }
    public void setSname(String sname)
    {
        this.sname=sname;
    }

    public String getSmobile()
    {
        return smobile;
    }
    public void setSmobile(String smobile)
    {
        this.smobile=smobile;
    }

    public String getSemail()
    {
        return semail;
    }
    public void setSemail(String semail)
    {
        this.semail=semail;
    }

    public String getSstreet()
    {
        return sstreet;
    }
    public void setSstreet(String sstreet)
    {
        this.sstreet=sstreet;
    }

    public String getScity()
    {
        return scity;
    }
    public void setScity(String scity)
    {
        this.scity=scity;
    }

    public String getSzipcode()
    {
        return szipcode;
    }
    public void setSzipcode(String szipcode)
    {
        this.szipcode=szipcode;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }

    public String getSimage()
    {
        return simage;
    }
    public void setSimage(String simage)
    {
        this.simage=simage;
    }

    private String base64Image;


    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

}
