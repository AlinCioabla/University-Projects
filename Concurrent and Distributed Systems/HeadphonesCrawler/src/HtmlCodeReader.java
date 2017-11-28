import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlCodeReader {
	
	public HtmlCodeReader(String aLink) throws MalformedURLException
	{
		mUrl = new URL(aLink);
	}
	
	public String Read() throws IOException
	{
		StringBuilder output = new StringBuilder();
		
        BufferedReader in = new BufferedReader(
        new InputStreamReader(mUrl.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
        	output.append(inputLine);
        }
        in.close();
        
        return output.toString().replaceAll("&(?!amp;)", "&amp;");
	}
	
	
	private URL mUrl;
}
