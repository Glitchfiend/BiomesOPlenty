package biomesoplenty.common.network.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import biomesoplenty.common.network.AbstractPacket;

public class PacketBiomePosition extends AbstractPacket
{
    private String biomeName;
    private int x;
    private int z;
    
    public PacketBiomePosition() {}
    
    public PacketBiomePosition(String biomeName, int x, int z)
    {
        this.biomeName = biomeName;
        this.x = x;
        this.z = z;
    }
    
    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, biomeName);
        buffer.writeInt(x);
        buffer.writeInt(z);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        biomeName = ByteBufUtils.readUTF8String(buffer);
        x = buffer.readInt();
        z = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player)
    {
        NBTTagCompound biomeCompound = new NBTTagCompound();
        
        biomeCompound.setInteger("x", x);
        biomeCompound.setInteger("z", z);
        
        if (!player.getEntityData().hasKey("biomePositions")) player.getEntityData().setTag("biomePositions", new NBTTagCompound());
        
        NBTTagCompound biomePositions = player.getEntityData().getCompoundTag("biomePositions");
        
        if (!biomePositions.hasKey(biomeName)) biomePositions.setTag(biomeName, biomeCompound);
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
    }
}
