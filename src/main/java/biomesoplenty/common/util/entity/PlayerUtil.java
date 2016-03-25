package biomesoplenty.common.util.entity;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerUtil 
{
    @SideOnly(Side.CLIENT)
    public static UUID getClientPlayerUUID()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        
        return minecraft.getSession().getProfile().getId();
    }
    
    public static EnumHand getHandForItemAndMeta(EntityPlayer player, Item item, int meta)
    {
        for (EnumHand hand : EnumHand.values())
        {
            ItemStack heldStack = player.getHeldItem(hand);
            
            if (heldStack != null && heldStack.getItem() == item && heldStack.getMetadata() == meta)
                return hand;
        }
        
        return null;
    }
}
