package biomesoplenty.common.items;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;
import biomesoplenty.BiomesOPlenty;

public class ItemBOPBucket extends ItemFluidContainer
{
	public ItemBOPBucket()
	{
		super(0);

		this.maxStackSize = 1;
		this.capacity = FluidContainerRegistry.BUCKET_VOLUME;

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	/*@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);

		String bucketType = itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("type") ? itemStack.getTagCompound().getString("type") : "";

		FluidStack fluid = getFluid(itemStack);

		if (pos != null)
		{
			int x = pos.blockX;
			int y = pos.blockY;
			int z = pos.blockZ;

			if (fluid == null || fluid.amount != capacity)
			{
				//TODO:				getBlock()
				Block block = world.getBlock(x, y, z);

				if (block instanceof BlockFluidBase)
				{
					BlockFluidBase fluidBlock = (BlockFluidBase)block;
					Fluid fluidBlockFluid = FluidRegistry.lookupFluidForBlock(fluidBlock);
					
					String fluidName = fluidBlockFluid != null ? allowedFluids.get(fluidBlockFluid.getName()) : null;
					
					if (fluidName != null && fluidName.equals(bucketType))
					{
						FluidStack blockFluid = fluidBlock.drain(world, x, y, z, true);

						this.fill(itemStack, new FluidStack(blockFluid, capacity), true);

						return itemStack;
					}
				}
			}
			else if (fluid != null)
			{
				Block block = fluid.getFluid().getBlock();

				if (block != null) 
				{
					world.setBlock(x, y, z, block);
					
					return new ItemStack(Items.bucket);
				}
			}
		}
		
		return itemStack;
	}*/
	
    @Override
	public String getItemStackDisplayName(ItemStack itemStack)
    {
    	FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(itemStack);
    	String fluidName = fluidStack != null ? fluidStack.getFluid().getName() : "";
    	
    	System.out.println(fluidName);
    	
        return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(itemStack) + fluidName + ".name")).trim();
    }
}
