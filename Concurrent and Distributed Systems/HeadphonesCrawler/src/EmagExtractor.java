import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class EmagExtractor extends Extractor{

	public EmagExtractor(Document aEmagDoc, ProductList aProducts, Dekker aDekker,int aID) {
		super(aEmagDoc, aProducts, aDekker, aID);
	}

	@Override
	public void Run() {
		Elements prices = mDoc.select("p.product-new-price");
		Elements ids = mDoc.select("input[name=product[]]");
		Elements titles = mDoc.select("h2[class='card-body product-title-zone'] > a[href]");

		
		for(int i=0; i < ids.size(); i++)
		{
			float price = GetPrice(prices.get(i).text());
			int id = Integer.parseInt(ids.get(i).attr("value"));
			String link = titles.get(i).attr("href");
			String title = titles.get(i).text();
	
			Headphones currentHeadphones = new Headphones(title,id);
			currentHeadphones.AddPrice(price, link);
			
			
			mProducts.Add(currentHeadphones);
		}
			
		
		
	}

	private float GetPrice(String aPrice) {
		String price = aPrice.trim();
		if (price.contains(" ")) 
		{
			price = price.substring(0, price.indexOf(" "));
		}

		price = price.replace(".", "");

		return Float.valueOf(price) / 100;

	}

	private Product currentProduct;
	private Price currentPrice;
	
}
