package biomesoplenty.handlers;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import biomesoplenty.handlers.versionhandlers.BOPModVersionHandler;
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

		if (BOPModVersionHandler.needsBOPWorldtypeAndMarkAsSeen(player.worldObj))
		{
			player.addChatMessage(String.format("\u00A7cThe Biomes O Plenty world type must be used in order for the new biomes to generate. This message will only display once."));
		}

		if (BOPModVersionHandler.needsUpdateNoticeAndMarkAsSeen()) 
		{
			player.addChatMessage(String.format("\u00A7cA new version of Biomes O Plenty is available: v%s for Minecraft %s", BOPModVersionHandler.getRecommendedVersion(), Loader.instance().getMinecraftModContainer().getVersion()));
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
