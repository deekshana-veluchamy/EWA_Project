import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Product")

public class Product extends HttpServlet {
    private int PID;
    private String PName="",PPrice="",PSelPrice="",PBrand="",Pcategory="",PSubCategory="",PSize="",PQuantity="",PDescription="",PDetail="",Pimage="",PDelivery="",PReturn="";



    public Product(int PID, String PName, String PPrice, String PSelPrice, String PBrand, String Pcategory, String PSubCategory, String PSize, String PQuantity, String PDescription, String PDetail, String Pimage, String PDelivery, String PReturn)
    {
        super();
        this.PID = PID;
        this.PName = PName;
        this.PPrice=PPrice;
        this.PSelPrice=PSelPrice;
        this.PBrand=PBrand;
        this.Pcategory=Pcategory;
        this.PSubCategory=PSubCategory;
        this.PSize=PSize;
        this.PQuantity=PQuantity;
        this.PDescription=PDescription;
        this.PDetail=PDetail;
        this.Pimage=Pimage;
        this.PDelivery=PDelivery;
        this.PReturn=PReturn;
    }

    public int getPID()
    {
        return PID;
    }
    public void setSID(int PID) {
        this.PID = PID;
    }

    public String getPName()
    {
        return PName;
    }
    public void setPName(String PName)
    {
        this.PName = PName;
    }

    public String getPPrice()
    {
        return PPrice;
    }
    public void setPPrice(String PPrice)
    {
        this.PPrice=PPrice;
    }

    public String getPSelPrice()
    {
        return PSelPrice;
    }
    public void setPSelPrice(String PSelPrice)
    {
        this.PSelPrice=PSelPrice;
    }

    public String getPBrand()
    {
        return PBrand;
    }
    public void setPBrand(String PBrand)
    {
        this.PBrand=PBrand;
    }

    public String getPcategory()
    {
        return Pcategory;
    }
    public void setPcategory(String Pcategory)
    {
        this.Pcategory=Pcategory;
    }

    public String getPSubCategory()
    {
        return PSubCategory;
    }
    public void setPSubCategory(String PSubCategory)
    {
        this.PSubCategory=PSubCategory;
    }

    public String getPSize()
    {
        return PSize;
    }
    public void setPSize(String PSize)
    {
        this.PSize=PSize;
    }

    public String getPQuantity()
    {
        return PQuantity;
    }
    public void setPQuantity(String PQuantity)
    {
        this.PQuantity=PQuantity;
    }

    public String getPDescription()
    {
        return PDescription;
    }
    public void setPDescription(String PDescription)
    {
        this.PDescription=PDescription;
    }

    public String getPDetail()
    {
        return PDetail;
    }
    public void setPDetail(String PDetail)
    {
        this.PDetail=PDetail;
    }

    public String getPimage()
    {
        return Pimage;
    }
    public void setPimage(String Pimage)
    {
        this.Pimage=Pimage;
    }

    public String getPDelivery()
    {
        return PDelivery;
    }
    public void setPDelivery(String PDelivery)
    {
        this.PDelivery=PDelivery;
    }

    public String getPReturn()
    {
        return PReturn;
    }
    public void setPReturn(String PReturn)
    {
        this.PReturn=PReturn;
    }


}
