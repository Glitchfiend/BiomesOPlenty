package tan.core;

import java.util.HashMap;

import tan.network.PacketTypeHandler;
import tan.network.packet.PacketSendStats;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TANPlayerStats
{
    public static void update(World world, EntityPlayerMP player)
    {
        NBTTagCompound tanData = player.getEntityData().getCompoundTag("ToughAsNails");
        
        setDefaults(tanData, player);
        
        float originalTemperature = tanData.getFloat("Temp");
        float temperature = originalTemperature;
        
        if (world.rand.nextInt(25) == 0)
        {
            temperature--;
        }
        
        if (temperature != originalTemperature)
        {
            tanData.setFloat("Temp", MathHelper.clamp_float(temperature, -50F, 50F));

            updatePlayerData(tanData, player);
        }
    }
    
    public static void updatePlayerData(NBTTagCompound tanData, EntityPlayerMP player)
    {
        player.getEntityData().setCompoundTag("ToughAsNails", tanData);
        PacketDispatcher.sendPacketToPlayer(PacketTypeHandler.populatePacket(new PacketSendStats(tanData)), (Player)player);
    }
    
    public static void setDefaults(NBTTagCompound tanData, EntityPlayerMP player)
    {
        setDefaultFloat(tanData, player, "Temp", 20F);
    }
    
    private static void setDefaultInt(NBTTagCompound tanData, EntityPlayerMP player, String key, int value)
    {
        if (!tanData.hasKey(key)) 
        {
            tanData.setInteger(key, value);
            updatePlayerData(tanData, player);
        }
    }
    
    private static void setDefaultFloat(NBTTagCompound tanData, EntityPlayerMP player, String key, float value)
    {
        if (!tanData.hasKey(key)) 
        {
            tanData.setFloat(key, value);
            updatePlayerData(tanData, player);
        }
    }
}
