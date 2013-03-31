package tdwp_ftw.biomesop.blocks;

import java.util.List;
import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDarkSlab extends BlockHalfSlab
{
    /** The type of tree this slab came from. */
    public static final String[] woodType = new String[] {"dark"};

    public BlockDarkSlab(int par1, boolean par2)
    {
        super(par1, par2, Material.wood);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
		this.setBurnProperties(this.blockID, 5, 20);
		this.useNeighborBrightness[blockID] = true;
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:darkplank");
	}

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    /*public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        switch (par2 & 7)
        {
            case 1:
                return 198;

            case 2:
                return 214;

            case 3:
                return 199;

            default:
                return 38;
        }
    }*/

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    /*public int getBlockTextureFromSide(int par1)
    {
        return this.getBlockTextureFromSideAndMetadata(par1, 0);
    }*/

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_BiomesOPlenty.darkSingleSlab.blockID;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(mod_BiomesOPlenty.darkSingleSlab.blockID, 2, par1 & 7);
    }

    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int par1)
    {
        if (par1 < 0 || par1 >= woodType.length)
        {
            par1 = 0;
        }

        return super.getUnlocalizedName() + "." + woodType[par1];
    }
	
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (par1 != mod_BiomesOPlenty.darkDoubleSlab.blockID)
        {
            par3List.add(new ItemStack(par1, 1, 0));
        }
    }
	
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return mod_BiomesOPlenty.darkSingleSlab.blockID;
    }
}
