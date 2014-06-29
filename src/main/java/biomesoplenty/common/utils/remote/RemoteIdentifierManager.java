package biomesoplenty.common.utils.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;

import biomesoplenty.common.utils.BOPLogger;

public abstract class RemoteIdentifierManager 
{
	protected String serverLocation;
	protected HashMap<String, String> identifierMap = new HashMap<String, String>();
	
	protected final int timeout = 1000;
	
	protected RemoteIdentifierManager(String serverLocation)
	{
		this.serverLocation = serverLocation;
		
		retrieve();
	}
	
	protected void retrieve()
	{
		URL url;
		
		try 
		{
			url = new URL(this.serverLocation);
	        URLConnection connection = url.openConnection();
	        connection.setConnectTimeout(timeout);
	        connection.setReadTimeout(timeout);
	        InputStream io = connection.getInputStream();
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(io));
			
	        String line;
	        int linetracker = 1;

	        while ((line = bufferedReader.readLine()) != null)
	        {
	        	if (!line.startsWith("//") && !line.isEmpty())
	        	{
	        		if (line.contains(":"))
	        		{
	        			String key = line.substring(0, line.indexOf(":"));
	        			String value = line.substring(line.indexOf(":") + 1);
	        			
	        			identifierMap.put(key, value);
	        		}
	        		else
	        		{
	        			BOPLogger.log(Level.WARN, "[" + getFileName() + ".txt] Syntax error on line " + linetracker + ": " + line);
	        		}
	        	}
	        	linetracker++;
	        }

	        bufferedReader.close();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getFileName()
	{
		return FilenameUtils.getBaseName(serverLocation);
	}
}
