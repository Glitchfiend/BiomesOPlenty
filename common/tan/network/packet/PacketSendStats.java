package tan.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import tan.api.PlayerStatRegistry;
import tan.network.PacketTypeHandler;
import tan.stats.TemperatureStat;
import cpw.mods.fml.common.network.Player;

public class PacketSendStats extends PacketTAN
{
    float temperature;
    
    public PacketSendStats()
    {
        super(PacketTypeHandler.sendStats);
    }
    
    public PacketSendStats(NBTTagCompound tanCompound)
    {
        super(PacketTypeHandler.sendStats);
        
        temperature = tanCompound.getFloat(PlayerStatRegistry.getStatName(TemperatureStat.class));
    }

    @Override
    public void readData(DataInputStream data) throws IOException 
    {
        temperature = data.readFloat();
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException 
    {
        data.writeFloat(temperature);
    }

    @Override
    public void execute(INetworkManager network, Player player) 
    {
        EntityPlayer entityPlayer = (EntityPlayer)player;
        
        NBTTagCompound tanCompound = entityPlayer.getEntityData().getCompoundTag("ToughAsNails");
        
        tanCompound.setFloat(PlayerStatRegistry.getStatName(TemperatureStat.class), temperature);
        
        entityPlayer.getEntityData().setCompoundTag("ToughAsNails", tanCompound);
    }
}
