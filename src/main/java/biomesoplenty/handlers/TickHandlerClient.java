package biomesoplenty.handlers;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;
import biomesoplenty.helpers.Version;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;

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
		    ChatMessageComponent updateMessage = new ChatMessageComponent();
		    updateMessage.setColor(EnumChatFormatting.RED);
		    
		    updateMessage.addKey("phrase.bop.useBOPWorldtype");
	        if (Loader.isModLoaded("ATG")) updateMessage.addKey("phrase.bop.useBOPATGWorldtype");
	        else updateMessage.addKey("phrase.bop.useBOPWorldtype");
		    
		    player.sendChatToPlayer(updateMessage);
		}

		if (Version.needsUpdateNoticeAndMarkAsSeen() && Version.getRecommendedVersion() != null) 
		{
		    ChatMessageComponent updateMessage = new ChatMessageComponent();
		    updateMessage.setColor(EnumChatFormatting.RED);    
		    updateMessage.addFormatted("phrase.bop.updateAvaliable", Version.getRecommendedVersion(), Loader.instance().getMinecraftModContainer().getVersion());
		    player.sendChatToPlayer(updateMessage);
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
