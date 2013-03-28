package tdwp_ftw.biomesop.blocks;

import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHolyGrass extends Block
{
    public BlockHolyGrass(int par1)
    {
        super(par1, Material.grass);
        this.blockIndexInTexture = 28;
        this.setTickRandomly(true);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par1 == 1 ? 29 : (par1 == 0 ? 27 : 28);
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return 29;
        }
        else if (par5 == 0)
        {
            return 27;
        }
        else
        {
            Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
            return var6 != Material.snow && var6 != Material.craftedSnow ? 28 : 28;
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && Block.lightOpacity[par1World.getBlockId(par2, par3 + 1, par4)] > 2)
            {
                par1World.setBlockWithNotify(par2, par3, par4, mod_BiomesOPlenty.holyStone.blockID);
            }
            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = par2 + par5Random.nextInt(3) - 1;
                    int var8 = par3 + par5Random.nextInt(5) - 3;
                    int var9 = par4 + par5Random.nextInt(3) - 1;
                    int var10 = par1World.getBlockId(var7, var8 + 1, var9);

                    if (par1World.getBlockId(var7, var8, var9) == mod_BiomesOPlenty.holyStone.blockID && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
                    {
                        par1World.setBlockWithNotify(var7, var8, var9, mod_BiomesOPlenty.holyGrass.blockID);
                    }
                }
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_BiomesOPlenty.holyStone.idDropped(0, par2Random, par3);
    }
}
