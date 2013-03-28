package tdwp_ftw.biomesop.blocks;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockAshStone extends Block
{
    public BlockAshStone(int par1, int par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
	
    public int getBlockColor()
    {
        return 12895428;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return 12895428;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 12895428;
    }
}
