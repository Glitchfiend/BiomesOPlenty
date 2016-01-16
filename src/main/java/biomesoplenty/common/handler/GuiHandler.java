package biomesoplenty.common.handler;

import biomesoplenty.client.gui.inventory.GuiFlowerBasket;
import biomesoplenty.common.inventory.ContainerFlowerBasket;
import biomesoplenty.common.inventory.InventoryFlowerBasket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    public static final int FLOWER_BASKET_ID = 0;
    
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
    {
        if (id == FLOWER_BASKET_ID)
        {
            //We assume if the flower basket gui is open, then the player must be holding a flower basket
            return new ContainerFlowerBasket(player, new InventoryFlowerBasket(player));
        }
        
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == FLOWER_BASKET_ID)
        {
            return new GuiFlowerBasket(player, new InventoryFlowerBasket(player));
        }
        
        return null;
    }

}
