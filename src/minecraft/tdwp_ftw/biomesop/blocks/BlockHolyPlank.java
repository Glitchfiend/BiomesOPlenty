package tdwp_ftw.biomesop.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;

@Deprecated
public class BlockHolyPlank extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"holy"};

    public BlockHolyPlank(int par1)
    {
        super(par1, Material.wood);
		this.setBurnProperties(this.blockID, 5, 20);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:holyplank");
	}

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }
}
