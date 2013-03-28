package tdwp_ftw.biomesop.blocks;

import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockHardSand extends Block
{
    public BlockHardSand(int par1, int par2)
    {
        super(par1, par2, Material.sand);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
}
