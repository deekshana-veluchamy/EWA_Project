
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API.
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard.
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java.

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure.
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    FruitsVeggies fv;
    Grocery grocery;
    Dairy dairy;
    Beverages beverages;
    Frozen frozen;
    static HashMap<String,FruitsVeggies> fvs;
    static HashMap<String,Grocery> grocerys;
    static HashMap<String,Dairy> dairies;
    static HashMap<String,Beverages> beverage;
    static HashMap<String,Frozen> frozens;
    String groceryXmlFileName;

    String elementValueRead;
	  String currentElement="";
    //static ArrayList<Product> products;
    public SaxParserDataStore()
  	{
  	}
	public SaxParserDataStore(String groceryXmlFileName) {
    this.groceryXmlFileName = groceryXmlFileName;
    fvs = new HashMap<String, FruitsVeggies>();
  	grocerys =new  HashMap<String, Grocery>();
  	dairies =new HashMap<String, Dairy>();
    beverage =new HashMap<String, Beverages>();
    frozens =new HashMap<String, Frozen>();
    //products = new ArrayList<Product>();
    parseDocument();
  }

   //parse the xml using sax parser to get the data
    private void parseDocument()
  	{
          SAXParserFactory factory = SAXParserFactory.newInstance();
          try
  		{
  	    SAXParser parser = factory.newSAXParser();
  	    parser.parse(groceryXmlFileName, this);
          } catch (ParserConfigurationException e) {
              System.out.println("ParserConfig error");
          } catch (SAXException e) {
              System.out.println("SAXException : xml not well formed");
          } catch (IOException e) {
              System.out.println("IO error");
          }
  	}



////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document.
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////

	// when xml start element is parsed store the id into respective hashmap for fv,grocerys etc
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("fv"))
    		{
    			currentElement="fv";
    			fv = new FruitsVeggies();
          fv.setId(attributes.getValue("id"));
    		}
        if (elementName.equalsIgnoreCase("grocery"))
    		{
    			currentElement="grocery";
    			grocery = new Grocery();
          grocery.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("dairy"))
    		{
    			currentElement="dairy";
    			dairy= new Dairy();
          dairy.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("beverages"))
    		{
    			currentElement="beverages";
    			beverages= new Beverages();
          beverages.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("frozen"))
    		{
    			currentElement="frozen";
    			frozen= new Frozen();
          frozen.setId(attributes.getValue("id"));
        }

    }
	// when xml end element is parsed store the data into respective hashmap for fv,grocerys etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {

      if (element.equals("fv")) {
  			fvs.put(fv.getId(),fv);
  			return;
      }

      if (element.equals("grocery")) {
  			grocerys.put(grocery.getId(),grocery);
  			return;
      }
      if (element.equals("dairy")) {
  			dairies.put(dairy.getId(),dairy);
  			return;
      }
      if (element.equals("beverages")) {
  			beverage.put(beverages.getId(),beverages);
  			return;
      }
      if (element.equals("frozen")) {
  			frozens.put(frozen.getId(),frozen);
  			return;
      }

      if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("fv"))
				    fv.setImage(elementValueRead);
        if(currentElement.equals("grocery"))
				    grocery.setImage(elementValueRead);
        if(currentElement.equals("dairy"))
				    dairy.setImage(elementValueRead);
        if(currentElement.equals("beverages"))
    		    beverages.setImage(elementValueRead);
        if(currentElement.equals("frozen"))
    		    frozen.setImage(elementValueRead);
			  return;
      }


		 if (element.equalsIgnoreCase("discount")) {
      if(currentElement.equals("fv"))
          fv.setDiscount(Double.parseDouble(elementValueRead));
      if(currentElement.equals("grocery"))
				 grocery.setDiscount(Double.parseDouble(elementValueRead));
      if(currentElement.equals("dairy"))
				 dairy.setDiscount(Double.parseDouble(elementValueRead));
      if(currentElement.equals("beverages"))
         beverages.setDiscount(Double.parseDouble(elementValueRead));
      if(currentElement.equals("frozen"))
         frozen.setDiscount(Double.parseDouble(elementValueRead));
			return;
	   }


		if (element.equalsIgnoreCase("manufacturer")) {
        if(currentElement.equals("fv"))
				    fv.setRetailer(elementValueRead);
        if(currentElement.equals("grocery"))
				    grocery.setRetailer(elementValueRead);
        if(currentElement.equals("dairy"))
				    dairy.setRetailer(elementValueRead);
        if(currentElement.equals("beverages"))
            beverages.setRetailer(elementValueRead);
        if(currentElement.equals("frozen"))
            frozen.setRetailer(elementValueRead);
			return;
		}

      if (element.equalsIgnoreCase("name")) {
          if(currentElement.equals("fv"))
				      fv.setName(elementValueRead);
        	if(currentElement.equals("grocery"))
				      grocery.setName(elementValueRead);
          if(currentElement.equals("dairy"))
				      dairy.setName(elementValueRead);
          if(currentElement.equals("beverages"))
              beverages.setName(elementValueRead);
          if(currentElement.equals("frozen"))
              frozen.setName(elementValueRead);
			    return;
	    }

      if(element.equalsIgnoreCase("price")){
			     if(currentElement.equals("fv"))
				       fv.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("grocery"))
				      grocery.setPrice(Double.parseDouble(elementValueRead));
          if(currentElement.equals("dairy"))
				      dairy.setPrice(Double.parseDouble(elementValueRead));
          if(currentElement.equals("beverages"))
              beverages.setPrice(Double.parseDouble(elementValueRead));
          if(currentElement.equals("frozen"))
              frozen.setPrice(Double.parseDouble(elementValueRead));
			    return;
      }

      if(element.equalsIgnoreCase("quantity")){
			     if(currentElement.equals("fv"))
				       fv.setQuantity(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("grocery"))
				      grocery.setQuantity(Double.parseDouble(elementValueRead));
          if(currentElement.equals("dairy"))
				      dairy.setQuantity(Double.parseDouble(elementValueRead));
          if(currentElement.equals("beverages"))
              beverages.setQuantity(Double.parseDouble(elementValueRead));
          if(currentElement.equals("frozen"))
              frozen.setQuantity(Double.parseDouble(elementValueRead));
			    return;
      }
      if (element.equalsIgnoreCase("manufacturerRebate")) {
          if(currentElement.equals("fv"))
				      fv.setManufacturerRebate(elementValueRead);
        	if(currentElement.equals("grocery"))
				      grocery.setManufacturerRebate(elementValueRead);
          if(currentElement.equals("dairy"))
				      dairy.setManufacturerRebate(elementValueRead);
          if(currentElement.equals("beverages"))
              beverages.setManufacturerRebate(elementValueRead);
          if(currentElement.equals("frozen"))
              frozen.setManufacturerRebate(elementValueRead);
			    return;
	    }


	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    //public static void addProduct(Product p) {
		//products.add(p);
	//}



    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////

//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\EWA_Project\\ProductCatalog.xml");
    }
}
