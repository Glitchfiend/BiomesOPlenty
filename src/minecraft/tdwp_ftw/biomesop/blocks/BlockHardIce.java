package tdwp_ftw.biomesop.blocks;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockHardIce extends Block
{
    public BlockHardIce(int par1, int par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
}
