package biomesoplenty.common.eventhandler.client;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.utils.remote.TrailManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class FlowerScatterEventHandler 
{
    static
    {
        TrailManager.retrieveTrails();
    }
    
	@SubscribeEvent(receiveCanceled = true)
	public void onEntityUpdate(PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			EntityPlayer player = (EntityPlayer)event.player;
			
			//Check if the player has a trail
			if (TrailManager.trailsMap.containsKey(player.getUniqueID()))
			{
			    String trailName = TrailManager.trailsMap.get(player.getUniqueID());
			    
			    World world = player.worldObj;

				if (player.posX != player.prevPosX || player.posZ != player.prevPosZ)
				{
					double dx = player.posX + 0.3F - (0.6F * world.rand.nextFloat());
					double dy = player.posY - 2;
					double dz = player.posZ + 0.3F - (0.6F * world.rand.nextFloat());

					if (player != Minecraft.getMinecraft().thePlayer) dy += 1;
					
					int x = MathHelper.floor_double(dx);
					int y = MathHelper.floor_double(dy);
					int z = MathHelper.floor_double(dz);

					if (world.getBlock(x, y, z).isBlockSolid(world, x, y, z, 0))
					{
						BiomesOPlenty.proxy.spawnParticle("flowerscatter", dx, y + 1.01D, dz, trailName);	
					}
				}
			}
		}
	}
}
