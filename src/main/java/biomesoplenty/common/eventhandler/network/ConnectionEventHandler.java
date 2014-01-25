package biomesoplenty.common.eventhandler.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.network.packet.PacketBiomePosition;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;

public class ConnectionEventHandler
{
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event)
    {
        EntityPlayer player = (EntityPlayer)event.player;
        
        NBTTagCompound biomeToFindCompound = player.getEntityData().getCompoundTag("biomePosition");
        boolean foundBiome = player.getEntityData().getBoolean("foundBiome");
        
        if (biomeToFindCompound != null)
        {
            BiomesOPlenty.packetPipeline.sendTo(new PacketBiomePosition(biomeToFindCompound.getInteger("x"), biomeToFindCompound.getInteger("z"), foundBiome), (EntityPlayerMP)player);
        }
    }
}
