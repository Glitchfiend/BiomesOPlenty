package biomesoplenty.common.utils.remote;

public interface IVersionChecker 
{
	public String getCurrentVersion(Object... args);
	public String getVersionString();
	
	public boolean isUpToDate(Object... args);
	
	public void markUpToDate(Object... args);
}
