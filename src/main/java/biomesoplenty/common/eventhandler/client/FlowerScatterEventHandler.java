package biomesoplenty.common.eventhandler.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.client.utils.ParticleRegistry;
import biomesoplenty.common.utils.remote.FlowerTrailManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FlowerScatterEventHandler 
{
	private FlowerTrailManager flowerTrailManager;
	
	public FlowerScatterEventHandler()
	{
		this.flowerTrailManager = FlowerTrailManager.getInstance();
	}
	
	@SubscribeEvent
	public void playerTick(TickEvent.PlayerTickEvent event)
	{
		if (event.side == Side.CLIENT && event.phase == Phase.START)
		{
			EntityPlayer player = event.player;
			WorldClient world = Minecraft.getMinecraft().theWorld;

			if (flowerTrailManager.hasTrail(player.getUniqueID()))
			{
				if (player.posX != player.prevPosX || player.posZ != player.prevPosZ)
				{
					double dx = player.posX + 0.3F - (0.6F * world.rand.nextFloat());
					double dy = player.posY - 2;
					double dz = player.posZ + 0.3F - (0.6F * world.rand.nextFloat());

					int x = MathHelper.floor_double(dx);
					int y = MathHelper.floor_double(dy);
					int z = MathHelper.floor_double(dz);

					if (world.getBlock(x, y, z).isBlockSolid(world, x, y, z, 0))
					{
						BiomesOPlenty.proxy.spawnParticle("flowerscatter", dx, y + 1.01D, dz, ParticleRegistry.trailMap.get(flowerTrailManager.getTrailType(player.getUniqueID())));
					}
				}
			}
		}
	}
}
