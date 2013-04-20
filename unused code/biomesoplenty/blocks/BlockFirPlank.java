package biomesoplenty.blocks;

import biomesoplenty.BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

@Deprecated
public class BlockFirPlank extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"fir"};

    public BlockFirPlank(int par1)
    {
        super(par1, Material.wood);
		this.setBurnProperties(this.blockID, 5, 20);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:firplank");
	}    
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }
}
