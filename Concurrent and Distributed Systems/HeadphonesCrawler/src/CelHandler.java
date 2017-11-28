import java.net.MalformedURLException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class CelHandler extends DefaultHandler{

	private Headphones currentHeadphones;
	private Price currentPrice;
	
	
	boolean bPrice = false;
	boolean bTitle = false;
	boolean bDivStarted = false;
	boolean bh4Started = false;
	boolean bName = false;
	
	
	@Override
	 public void startElement(String uri, String localName, String qName, Attributes attributes)
	            throws SAXException 
	{
	        if (qName.equalsIgnoreCase("b")) 
	        {
	        	String attr = attributes.getValue("itemprop");
	        	if(attr.equalsIgnoreCase("price"))
	        	{
	        		currentHeadphones = new Headphones();
		        	bPrice = true;
	        	}

	        } 
	        else if (qName.equalsIgnoreCase("span") && bDivStarted) 
	        {
	        	String attr = attributes.getValue("id");
	        	if(attr != null) 
	        	{
	        		int id = ExtractDigits(attr);
	        		currentHeadphones.SetID(id);
	        	}
	        }
	        else if(qName.equalsIgnoreCase("span") && bTitle)
	        {
	        	String attr = attributes.getValue("itemprop");
	        	if(attr.equalsIgnoreCase("name"))
	        	{
	        		bName = true;
	        	}
	        }
	        else if (qName.equalsIgnoreCase("div")) 
	        {
	        	String attr = attributes.getValue("class");
	        	if(attr.equalsIgnoreCase("stoc_list"))
	        	{
		        	bDivStarted = true;
	        	}

	        } 
	        else if (qName.equalsIgnoreCase("h4")) 
	        {
	        	String attr = attributes.getValue("class");
	        	
	        	if(attr.equalsIgnoreCase("productTitle"))
	        	{
		        	bh4Started = true;
	        	}

	        }
	        else
	        	if(qName.equalsIgnoreCase("a") && bh4Started)
	        	{
	        		String attr = attributes.getValue("href");
	        		if(attr != null)
	        		{
						currentPrice.SetLink(attr);
	        			bTitle = true;
	        		}
	        	}
	}

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException 
	    {
	        if (qName.equalsIgnoreCase("div")) 
	        {
	            bDivStarted = false;
	        }
	        else if(qName.equalsIgnoreCase("h4"))
	        {
	        	bh4Started = false;
	        	if(bName)
	        	{
	        		bName = false;
	        		currentHeadphones.AddPrice(currentPrice);
	        		System.out.println(currentHeadphones.GetTitle());
	        		System.out.println(currentHeadphones.GetID());
	        		System.out.println(currentHeadphones.GetLowestPrice().GetValue());
	        		System.out.println(currentHeadphones.GetLowestPrice().GetLink());
	        	}
	        }
	        else if(qName.equalsIgnoreCase("a"))
	        {
	        	bTitle = false;
	        }
	    }

	    @Override
	    public void characters(char ch[], int start, int length) throws SAXException 
	    {
	        if (bPrice) 
	        {
	            currentPrice.SetPriceValue(Integer.parseInt(new String(ch, start, length)));
	            bPrice = false;
	        } 
	        else if (bName) 
	        {
	            currentHeadphones.SetTitle(new String(ch, start, length));
	        } 

	    }
	
	    private int ExtractDigits(String aString)
	    {
	    	int index = 0;
	    	while(aString.indexOf(index) < '0' || aString.indexOf(index) > '9')
	    	{
	    		aString = aString.substring(1);
	    	}
	    	
	    	while(aString.indexOf(index) >= '0' && aString.indexOf(index) <= '9')
	    	{
	    		index++;
	    	}
	    	aString = aString.substring(0, index-1);
	    	
	    	return Integer.parseInt(aString);
	    	
	    	
	    }
	
}
