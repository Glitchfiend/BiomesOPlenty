package tan.api;

import tan.network.PacketTypeHandler;
import tan.network.packet.PacketSendStats;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class TANStat
{
    public World world;
    public EntityPlayerMP player;
    public NBTTagCompound tanData;
        
    public abstract void update();
    
    public abstract void setDefaults();
    
    public abstract String getStatName();
    
    public static void updatePlayerData(NBTTagCompound tanData, EntityPlayerMP player)
    {
        player.getEntityData().setCompoundTag("ToughAsNails", tanData);
        PacketDispatcher.sendPacketToPlayer(PacketTypeHandler.populatePacket(new PacketSendStats(tanData)), (Player)player);
    }
    
    public void setDefaultInt(String key, int value)
    {
        if (!tanData.hasKey(key)) 
        {
            tanData.setInteger(key, value);
            updatePlayerData(tanData, player);
        }
    }
    
    public void setDefaultFloat(String key, float value)
    {
        if (!tanData.hasKey(key)) 
        {
            tanData.setFloat(key, value);
            updatePlayerData(tanData, player);
        }
    }
}
