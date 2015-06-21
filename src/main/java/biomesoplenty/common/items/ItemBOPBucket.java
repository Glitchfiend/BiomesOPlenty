package biomesoplenty.common.items;

import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.ItemFluidContainer;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCFluids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.fluids.IFluidTank;

public class ItemBOPBucket extends ItemFluidContainer
{
	private static HashMap<String, IIcon> bucketIcons = new HashMap();
	
	public ItemBOPBucket()
	{
		super(0, FluidContainerRegistry.BUCKET_VOLUME);

		this.maxStackSize = 1;

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, false);
		ForgeDirection direction = ForgeDirection.getOrientation(movingobjectposition.sideHit);
		if (movingobjectposition != null)
		{
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;

        	TileEntity tile = world.getTileEntity(i, j, k);
        	
        	if(tile != null && tile instanceof IFluidHandler)
        	{
        		IFluidHandler tank = (IFluidHandler)tile;
        		if(tank.fill(direction,this.getFluid(itemStack), false) == this.getCapacity(itemStack))
        		{
        			tank.fill(direction,this.getFluid(itemStack), true);
        			if(!player.capabilities.isCreativeMode)
        				return new ItemStack(Items.bucket);
        		}
        		return itemStack;
        	}

        	if (movingobjectposition.sideHit == 0)
            {
                --j;
            }

            if (movingobjectposition.sideHit == 1)
            {
                ++j;
            }

            if (movingobjectposition.sideHit == 2)
            {
                --k;
            }

            if (movingobjectposition.sideHit == 3)
            {
                ++k;
            }

            if (movingobjectposition.sideHit == 4)
            {
                --i;
            }

            if (movingobjectposition.sideHit == 5)
            {
                ++i;
            }

            if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack))
            {
                return itemStack;
            }

            if (this.tryPlaceContainedLiquid(itemStack, world, i, j, k) && !player.capabilities.isCreativeMode)
            {
                return new ItemStack(Items.bucket);
            }
		}
		
		return itemStack;
	}
	
    public boolean tryPlaceContainedLiquid(ItemStack itemStack, World world, int x, int y, int z)
    {
    	FluidStack fluid = this.getFluid(itemStack);
    	
        if (fluid == null || fluid.amount == 0)
        {
            return false;
        }
        else
        {
        	
            Material material = world.getBlock(x, y, z).getMaterial();
            boolean isSolid = material.isSolid();
            
            if (!world.isAirBlock(x, y, z) && isSolid)
            {
                return false;
            }
            else
            {
            	if (!world.isRemote && !isSolid && !material.isLiquid())
            	{
            		world.func_147480_a(x, y, z, true);
            	}

            	int meta = fluid.getFluid() == BOPCFluids.honey ? 7 : 0;
            	
            	world.setBlock(x, y, z, fluid.getFluid().getBlock(), meta, 3);
            }

            return true;
        }
    }
    
	@Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		ItemStack fluid = new ItemStack(item);
		
		this.fill(fluid, new FluidStack(BOPCFluids.poison, FluidContainerRegistry.BUCKET_VOLUME), true); list.add(fluid);
		fluid = new ItemStack(item);
		this.fill(fluid, new FluidStack(BOPCFluids.blood, FluidContainerRegistry.BUCKET_VOLUME), true); list.add(fluid);
		fluid = new ItemStack(item);
		this.fill(fluid, new FluidStack(BOPCFluids.honey, FluidContainerRegistry.BUCKET_VOLUME), true); list.add(fluid);
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		bucketIcons.put("poison", iconRegister.registerIcon("biomesoplenty:bucket_poison"));
		bucketIcons.put("honey", iconRegister.registerIcon("biomesoplenty:bucket_honey"));
		bucketIcons.put("hell_blood", iconRegister.registerIcon("biomesoplenty:bucket_blood"));
	}
    
    @Override
    public IIcon getIcon(ItemStack itemStack, int renderPass)
    {
        FluidStack fluid = this.getFluid(itemStack);
        
        if (fluid != null && fluid.amount != 0) 
        {
        	IIcon icon = bucketIcons.get(fluid.getFluid().getName());
        	
        	if (icon != null) return icon;
        }
        
        return Items.bucket.getIconFromDamage(0);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
	
    @Override
	public String getItemStackDisplayName(ItemStack itemStack)
    {
    	FluidStack fluid = this.getFluid(itemStack);
    	
    	if (fluid != null && fluid.amount != 0)
    	{
    		return StatCollector.translateToLocal(fluid.getFluid().getUnlocalizedName().replace("fluid.", "item.") + "Bucket" + ".name");
    	}
        
        return Items.bucket.getUnlocalizedName() + ".name";
    }
}
