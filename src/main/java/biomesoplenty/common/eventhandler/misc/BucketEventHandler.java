package biomesoplenty.common.eventhandler.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCFluids;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.common.fluids.blocks.BlockBloodFluid;
import biomesoplenty.common.fluids.blocks.BlockHoneyFluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;
import biomesoplenty.common.items.ItemBOPBucket;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BucketEventHandler 
{
	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		ItemBOPBucket bopBucket = (ItemBOPBucket)BOPCItems.bopBucket;
		ItemStack bopBucketStack = new ItemStack(bopBucket);

		World world = event.world;
		
		int x = event.target.blockX;
		int y = event.target.blockY;
		int z = event.target.blockZ;

		Fluid fluid = FluidRegistry.lookupFluidForBlock(world.getBlock(x, y, z));
		
		if (fluid == null)
		{
        	TileEntity tile=event.world.getTileEntity(x, y, z);
        	if(tile != null && tile instanceof IFluidHandler)
        	{
        		IFluidHandler tank = (IFluidHandler)tile;
        		FluidStack fluidStack = tank.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.BUCKET_VOLUME, false);
        		if(fluidStack==null)
        			return;
        		if(fluidStack.amount < FluidContainerRegistry.BUCKET_VOLUME)
        			return;
        		if(fluidStack.getFluid()==BOPCFluids.blood || fluidStack.getFluid()==BOPCFluids.honey || fluidStack.getFluid()==BOPCFluids.poison)
        		{
    				tank.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.BUCKET_VOLUME , true);
        			ItemStack filled=new ItemStack(BOPCItems.bopBucket);
        			((ItemBOPBucket)BOPCItems.bopBucket).fill(filled, fluidStack, true);
        			event.result=filled;
    				event.setResult(Result.ALLOW);
    				return;
        		}
        	}
		}

		if (fluid != null)
		{
			if ((fluid == BOPCFluids.poison && world.getBlockMetadata(x, y, z) == 0) || (fluid == BOPCFluids.blood && world.getBlockMetadata(x, y, z) == 0) || (fluid == BOPCFluids.honey && world.getBlockMetadata(x, y, z) == 7))
			{
				
				bopBucket.fill(bopBucketStack, new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME), true);

				world.setBlockToAir(x, y, z);

				event.result = bopBucketStack;
				event.setResult(Result.ALLOW);
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) {
		if (event.map.getTextureType() == 0) {
			BOPCFluids.blood.setIcons(BlockBloodFluid.bloodStillIcon, BlockBloodFluid.bloodFlowingIcon);
			BOPCFluids.honey.setIcons(BlockHoneyFluid.honeyStillIcon, BlockHoneyFluid.honeyFlowingIcon);
			BOPCFluids.poison.setIcons(BlockPoisonFluid.liquidPoisonStillIcon, BlockPoisonFluid.liquidPoisonFlowingIcon);
		}
	}}
