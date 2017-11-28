import java.net.MalformedURLException;
import java.util.ArrayList;



import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CelExtractor extends Extractor {

	public CelExtractor(Document aCelDoc, ProductList aProducts, Dekker aDek,int aID) {
		super(aCelDoc,aProducts, aDek,aID);
	}

	@Override
	public void Run() 
	{
		Elements prices = mDoc.select("b[itemprop=price]");
		Elements ids = mDoc.getElementsByClass("stoc_list");
		Elements titles = mDoc.getElementsByClass("productTitle");
		Elements links = mDoc.getElementsByClass("productTitle");
		
		for(int i=0; i < prices.size(); i++)
		{
			float price = Float.valueOf(prices.get(i).getElementsByAttribute("content").text());
			int id = Integer.parseInt(trimId(ids.get(i).getElementsByAttribute("id").attr("id")));
			String link = links.get(i).select("a").first().attr("href");
			String title = titles.get(i).text();
			Headphones current = new Headphones(title,id);

			current.AddPrice(price, link);
			
			mProducts.Add(current);
			
		
		}
		
		
		

	}
	
	private String trimId(String aId)
	{
		aId = aId.substring(aId.indexOf("s") + 1, aId.indexOf("-"));
		return aId;
	}

}
