package biomesoplenty.common.network.message;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import biomesoplenty.api.content.BOPCItems;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageBiomePosition implements IMessage, IMessageHandler<MessageBiomePosition, IMessage>
{
    private int x;
    private int z;
    private boolean foundBiome;
    
    public MessageBiomePosition() {}
    
    public MessageBiomePosition(int x, int z, boolean foundBiome)
    {
        this.x = x;
        this.z = z;
        this.foundBiome = foundBiome;
    }
    
	@Override
	public void fromBytes(ByteBuf buf) 
	{
        x = buf.readInt();
        z = buf.readInt();
        foundBiome = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(x);
		buf.writeInt(z);
		buf.writeBoolean(foundBiome);
	}

	@Override
	public IMessage onMessage(MessageBiomePosition message, MessageContext ctx) 
	{
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		
        NBTTagCompound biomeCompound = new NBTTagCompound();
        
        biomeCompound.setInteger("x", x);
        biomeCompound.setInteger("z", z);

        ItemStack currentItem = player.getCurrentEquippedItem();

        if (currentItem.getItem() == BOPCItems.biomeFinder)
        {
            if (!currentItem.hasTagCompound()) currentItem.setTagCompound(new NBTTagCompound());

            currentItem.getTagCompound().setBoolean("foundBiome", foundBiome);
            currentItem.getTagCompound().setTag("biomePosition",  biomeCompound);
        }
        
        return null;
	}
}
