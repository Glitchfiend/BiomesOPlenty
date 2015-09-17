package biomesoplenty.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class ItemBOPSeeds extends Item implements IPlantable
{
    private Block blockType;
    private Block soilBlock;

    public ItemBOPSeeds(Block blockType, Block soilBlock)
    {
        this.blockType = blockType;
        this.soilBlock = soilBlock;
        
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:turnipseeds");
	}

	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitVecX, float hitVecY, float hitVecZ)
    {
        if (side != 1)
        {
            return false;
        }
        else if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack))
        {
    		Block soil = world.getBlock(x, y, z);

            if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z))
            {
                world.setBlock(x, y + 1, z, this.blockType, 0, 2);
                --itemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return (blockType == Blocks.nether_wart ? EnumPlantType.Nether : EnumPlantType.Crop);
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return blockType;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }
}
