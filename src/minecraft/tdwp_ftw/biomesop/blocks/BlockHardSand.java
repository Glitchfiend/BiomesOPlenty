package tdwp_ftw.biomesop.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;

public class BlockHardSand extends Block
{
    public BlockHardSand(int par1)
    {
        super(par1, Material.sand);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:hardsand");
	}
}
