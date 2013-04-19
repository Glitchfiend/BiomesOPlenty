package biomesoplenty.blocks;

import biomesoplenty.BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.IBlockAccess;

@Deprecated
public class BlockAshStone extends Block
{
    public BlockAshStone(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:ashstone");
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
