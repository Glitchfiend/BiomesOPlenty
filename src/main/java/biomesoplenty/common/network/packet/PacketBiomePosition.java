package biomesoplenty.common.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.network.AbstractPacket;

public class PacketBiomePosition extends AbstractPacket
{
    private int x;
    private int z;
    private boolean foundBiome;
    
    public PacketBiomePosition() {}
    
    public PacketBiomePosition(int x, int z, boolean foundBiome)
    {
        this.x = x;
        this.z = z;
        this.foundBiome = foundBiome;
    }
    
    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        buffer.writeInt(x);
        buffer.writeInt(z);
        buffer.writeBoolean(foundBiome);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        x = buffer.readInt();
        z = buffer.readInt();
        foundBiome = buffer.readBoolean();
    }

    @Override
    public void handleClientSide(EntityPlayer player)
    {
        NBTTagCompound biomeCompound = new NBTTagCompound();
        
        biomeCompound.setInteger("x", x);
        biomeCompound.setInteger("z", z);

        ItemStack currentItem = player.getCurrentEquippedItem();

        if (currentItem.getItem() == BOPItemHelper.get("biomeFinder"))
        {
            if (!currentItem.hasTagCompound()) currentItem.setTagCompound(new NBTTagCompound());

            currentItem.getTagCompound().setBoolean("foundBiome", foundBiome);
            currentItem.getTagCompound().setTag("biomePosition",  biomeCompound);
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
    }
}
