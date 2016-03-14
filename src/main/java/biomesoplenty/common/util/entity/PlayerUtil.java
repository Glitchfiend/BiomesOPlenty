package biomesoplenty.common.util.entity;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
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
    
    public static EnumHand getHandForItem(EntityPlayer player, ItemStack stack)
    {
        for (EnumHand hand : EnumHand.values())
        {
            if (player.getHeldItem(hand).equals(stack))
                return hand;
        }
        
        return null;
    }
}
