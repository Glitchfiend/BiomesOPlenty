package tdwp_ftw.biomesop.blocks;

import java.util.List;
import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedRockBrickSlab extends BlockHalfSlab
{
    /** The type of tree this slab came from. */
    public static final String[] woodType = new String[] {"redRock"};

    public BlockRedRockBrickSlab(int par1, boolean par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
		this.useNeighborBrightness[blockID] = true;
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:redbrick");
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
                return 24;
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
        return mod_BiomesOPlenty.redRockBrickSingleSlab.blockID;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(mod_BiomesOPlenty.redRockBrickSingleSlab.blockID, 2, par1 & 7);
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if (par5EntityPlayer.getCurrentEquippedItem().itemID == this.blockID)
    	{
    		
    		if (world.getBlockMetadata(par2, par3, par4) == 0 /*0 = Slab at bottom half*/ )
    		{
    			if (par6 == 1 /*1 = top of the block*/)
    			{
    	    		if (!par5EntityPlayer.capabilities.isCreativeMode)
    	    		{
    	    			--par5EntityPlayer.getCurrentEquippedItem().stackSize;
    	    		}
    	    		
    	    		world.playSoundEffect((double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), this.stepSound.getPlaceSound(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
    	    		world.setBlock(par2, par3, par4, mod_BiomesOPlenty.redRockBrickDoubleSlab.blockID);
    	    		
    	    		return true;
    			}
    		}
    		
    		if (world.getBlockMetadata(par2, par3, par4) == 8 /*8 = Slab at top half*/ )
    		{
    			if (par6 == 0 /*0 = bottom of the block*/)
    			{
    	    		if (!par5EntityPlayer.capabilities.isCreativeMode)
    	    		{
    	    			--par5EntityPlayer.getCurrentEquippedItem().stackSize;
    	    		}
    	    		
    	    		world.playSoundEffect((double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), this.stepSound.getPlaceSound(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
    	    		world.setBlock(par2, par3, par4, mod_BiomesOPlenty.redRockBrickDoubleSlab.blockID);
    	    		
    	    		return true;
    			}
    		}
    	}
    	return false;
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
        if (par1 != mod_BiomesOPlenty.redRockBrickDoubleSlab.blockID)
        {
            par3List.add(new ItemStack(par1, 1, 0));
        }
    }
	
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return mod_BiomesOPlenty.redRockBrickSingleSlab.blockID;
    }
}
