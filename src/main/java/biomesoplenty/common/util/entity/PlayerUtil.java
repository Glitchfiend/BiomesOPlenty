package biomesoplenty.common.util.entity;

import java.util.UUID;

import net.minecraft.client.Minecraft;
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
}
