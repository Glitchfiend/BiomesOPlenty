package biomesoplenty.helpers;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.TickType;

public class TickHandlerClient implements ITickHandler 
{
	private boolean nagged;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		if (nagged)
			return;

		EntityPlayer player = (EntityPlayer) tickData[0];

		if (Version.needsBOPWorldtypeAndMarkAsSeen(player.worldObj))
		{
			player.sendChatToPlayer(String.format("\u00A7cThe Biomes O Plenty world type must be used in order for the new biomes to generate. This message will only display once."));
		}

		if (Version.needsUpdateNoticeAndMarkAsSeen()) 
		{
			player.sendChatToPlayer(String.format("\u00A7cA new version of Biomes O Plenty is available: %s for Minecraft %s", Version.getRecommendedVersion(), Loader.instance().getMinecraftModContainer().getVersion()));
			
			for (String updateLine : Version.getChangelog()) 
			{
				player.sendChatToPlayer("\u00A79" + updateLine);
			}
		}

		nagged = true;
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() 
	{
		return "BiomesOPlenty - Player update tick";
	}

}
