package biomesoplenty.helpers;

import net.minecraft.block.BlockFluid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import biomesoplenty.api.Liquids;
import biomesoplenty.ftfluidsapi.FluidRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BOPLiquidHelper 
{
	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) 
	{
		//TODO: Remove upon Fluid API being integrated into Forge
		FluidRegistry.WATER.setIcons(BlockFluid.func_94424_b("water"), BlockFluid.func_94424_b("water_flow"));
		FluidRegistry.LAVA.setIcons(BlockFluid.func_94424_b("lava"), BlockFluid.func_94424_b("lava_flow"));
	}
	
	@ForgeSubscribe
	public void onBucketFill(FillBucketEvent event) 
	{
		ItemStack result = fillCustomBucket(event.world, event.target);

		if (result == null)
		{
			return;
		}

		event.result = result;
		event.setResult(Result.ALLOW);
	}

	public ItemStack fillCustomBucket(World world, MovingObjectPosition pos) 
	{
		int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
		int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

		if ((blockID == Liquids.springWater.get().blockID) && meta == 0) 
		{
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

			return new ItemStack(Liquids.bopBucket.get(), 1, 0);
		} 
		
		if ((blockID == Liquids.liquidPoison.get().blockID) && meta == 0) 
		{
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

			return new ItemStack(Liquids.bopBucket.get(), 1, 1);
		} 
		else
		{
			return null;	
		}
	}
}
