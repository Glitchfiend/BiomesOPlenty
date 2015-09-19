/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import biomesoplenty.common.remote.TrailManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TrailsEventHandler 
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
			World world = player.worldObj;
			float groundYOffset = 0.015625F; //Prevents particles from z-fighting with the ground
			BlockPos playerPos = player.getPosition();

			if (world.getBlockState(playerPos.down()).getBlock().isSideSolid(world, playerPos.down(), EnumFacing.UP)) //Only place particles on blocks with a solid top
			{
				if (player.posX != player.prevPosX || player.posZ != player.prevPosZ) //Particles should only spawn if the player is moving
				{
					//Makes placement more interesting, scatter slightly on the x and z axis
					double offsetX = 0.3 - world.rand.nextFloat() * 0.6;
					double offsetZ = 0.3 - world.rand.nextFloat() * 0.6;
					
					//BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.PLAYER_TRAIL, player.posX + offsetX, ((int)player.posY) + groundYOffset, player.posZ + offsetZ);	
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.worldObj;
		
		if (world.isRemote)
		{
			//Send client trail preferences
		}
		else
		{
			//Server sends other player preferences
		}
	}
}
