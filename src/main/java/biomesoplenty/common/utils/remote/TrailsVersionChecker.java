package biomesoplenty.common.utils.remote;

import java.util.Map.Entry;

import org.apache.commons.lang3.builder.ToStringBuilder;

import biomesoplenty.common.configuration.BOPConfigurationMain;

public class TrailsVersionChecker extends RemoteIdentifierManager implements IVersionChecker
{
	private static TrailsVersionChecker instance;
	
	private TrailsVersionChecker()
	{
		super("https://raw.githubusercontent.com/Glitchfiend/BiomesOPlenty/master/trails/version.txt");
	}
	
	@Override
	public String getCurrentVersion(Object... args)
	{
		String trailsVersion = BOPConfigurationMain.trailsVersion.getString();
		
		if (trailsVersion != null && !trailsVersion.isEmpty())
		{
			String type = (String)args[0];
			String find = trailsVersion.substring(trailsVersion.indexOf(type));
			
			return find.substring(find.indexOf(":") + 1, find.indexOf(";"));
		}
		
		return null;
	}
	
	@Override
	public boolean isUpToDate(Object... args) 
	{
		String key = (String)args[0];
		
		if (this.identifierMap.containsKey(key))
		{
			String currentVersion = getCurrentVersion(key);
			int version = Integer.parseInt(this.identifierMap.get(key));
			
			if (currentVersion != null && Integer.parseInt(currentVersion) == version)
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void markUpToDate(Object... args) 
	{
		BOPConfigurationMain.trailsVersion.set(getVersionString());
		BOPConfigurationMain.config.save();
	}
	
	@Override
	public String getVersionString()
	{
		String versionString = "";
		
		for (Entry<String, String> entry : this.identifierMap.entrySet())
		{
			versionString += entry.getKey() + ":" + entry.getValue() + ";";
		}
		
		return versionString;
	}
	
	public static TrailsVersionChecker getInstance()
	{
		return instance == null ? instance = new TrailsVersionChecker() : instance;
	}
}
