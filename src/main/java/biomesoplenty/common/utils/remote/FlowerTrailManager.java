package biomesoplenty.common.utils.remote;

import java.util.UUID;

import com.mojang.util.UUIDTypeAdapter;

public class FlowerTrailManager extends RemoteIdentifierManager
{
	private static FlowerTrailManager instance;
	
	protected FlowerTrailManager() 
	{
		super("https://raw.githubusercontent.com/Glitchfiend/BiomesOPlenty/master/trails.txt");
	}
	
	public boolean hasTrail(UUID uuid)
	{
		return this.identifierMap.containsKey(UUIDTypeAdapter.fromUUID(uuid));
	}
	
	public String getTrailType(UUID uuid)
	{
		return this.identifierMap.get(UUIDTypeAdapter.fromUUID(uuid));
	}

	public static FlowerTrailManager getInstance()
	{
		return instance == null ? instance = new FlowerTrailManager() : instance;
	}
}
