import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/*
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.

	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable{
	private int id;
	private String username;
	private String password;
	private String repassword;
	private String name;
	private String email;
	private String mobile;
	private String usertype;

	public User(String username,String password,String repassword,String name,String email,String mobile) {
		this.username=username;
		this.password=password;
		this.repassword=repassword;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
}
