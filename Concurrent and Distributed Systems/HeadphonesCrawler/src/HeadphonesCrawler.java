import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HeadphonesCrawler {
	
	private String mCelHeadphonesCategoryUrl;
	private String mEmagHeadphonesCategoryUrl;
	private int mThreadNumber;
	private ArrayList<Thread> mThreads;
	private ProductList mProducts;
	private Dekker mDekker;
	
	
	public HeadphonesCrawler(String aCelHeadphonesCategoryUrl, String aEmagHeadphonesCategoryUrl, int aThreadNumber)
	{
		mCelHeadphonesCategoryUrl = aCelHeadphonesCategoryUrl;
		mEmagHeadphonesCategoryUrl = aEmagHeadphonesCategoryUrl;
		mThreadNumber = aThreadNumber;
		mThreads = new ArrayList<Thread>();
		mProducts = new ProductList();
		mDekker = new Dekker(aThreadNumber);
	}
	
	public void Start() throws IOException, InterruptedException
	{
		for(int i = 0; i< mThreadNumber;i++)
		{
			Thread t = new Thread(new EmagCelProductExtractor(mCelHeadphonesCategoryUrl, mEmagHeadphonesCategoryUrl, mProducts, mDekker,i));
			mThreads.add(t);
			
			mCelHeadphonesCategoryUrl = mCelHeadphonesCategoryUrl + "0a-" + (i+2);
			mEmagHeadphonesCategoryUrl = "https://www.emag.ro/casti-pc/p" + (i+2) + "/c";

		}
		
		for(Thread t : mThreads)
		{
			t.start();
		}
		
		for(Thread t : mThreads)
		{
			t.join();
		}
		
		
	}
	
	public void PrintLowestPrices()
	{
		mProducts.PrintLowestPrices();
	}
	
	public void Print()
	{
		mProducts.Print();
	}
	
	public static void main(String[] args) 
	{
		String celUrl = "http://www.cel.ro/casti/";
		String emagUrl = "https://www.emag.ro/casti-pc/c";
		int threadNumber = 69;
		
		HeadphonesCrawler hc = new HeadphonesCrawler(celUrl, emagUrl, threadNumber);
		try {
			hc.Start();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hc.PrintLowestPrices();
		//hc.Print();
		
	}
}
