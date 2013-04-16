package com.bopteam.biomesop.blocks;

import java.util.List;
import java.util.Random;

import com.bopteam.biomesop.mod_BiomesOPlenty;
import com.bopteam.biomesop.configuration.BOPBlocks;
import com.bopteam.biomesop.worldgen.WorldGenMystic2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockMagicSapling extends BlockSapling
{
    public static final String[] WOOD_TYPES = new String[] {"magic"};
    private Icon[] blockIcon = new Icon[1];

    public BlockMagicSapling(int par1)
    {
        super(par1);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon[0] = par1IconRegister.registerIcon("BiomesOPlenty:magicsapling");
	}
    
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return this.blockIcon[0];
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && 
                (soil != null && soil.blockID == BOPBlocks.holyGrass.blockID || soil.blockID == Block.grass.blockID || soil.blockID == Block.dirt.blockID);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
                int var6 = par1World.getBlockMetadata(par2, par3, par4);

                if ((var6 & 8) == 0)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 8, 2);
                }
                else
                {
                    this.growTree(par1World, par2, par3, par4, par5Random);
                }
            }
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    /*public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        par2 &= 3;
        return par2 == 1 ? 63 : (par2 == 2 ? 79 : (par2 == 3 ? 30 : super.getBlockTextureFromSideAndMetadata(par1, par2)));
    }*/

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int var6 = par1World.getBlockMetadata(par2, par3, par4) & 3;
        Object var7 = null;
        int var8 = 0;
        int var9 = 0;
        boolean var10 = false;

		for (var8 = 0; var8 >= -1; --var8)
		{
			for (var9 = 0; var9 >= -1; --var9)
			{
				if (this.isSameSapling(par1World, par2 + var8, par3, par4 + var9, 0) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9, 0) && this.isSameSapling(par1World, par2 + var8, par3, par4 + var9 + 1, 0) && this.isSameSapling(par1World, par2 + var8 + 1, par3, par4 + var9 + 1, 0))
				{
					break;
				}
			}

			if (var7 != null)
			{
				break;
			}
		}

		if (var7 == null)
		{
			var9 = 0;
			var8 = 0;
			
			var7 = new WorldGenMystic2(false);
		}

        if (var10)
        {
            par1World.setBlock(par2 + var8, par3, par4 + var9, 0);
            par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, 0);
            par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, 0);
            par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, 0);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, 0);
        }

        if (!((WorldGenerator)var7).generate(par1World, par5Random, par2 + var8, par3, par4 + var9))
        {
            if (var10)
            {
                par1World.setBlock(par2 + var8, par3, par4 + var9, this.blockID, var6, 2);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9, this.blockID, var6, 2);
                par1World.setBlock(par2 + var8, par3, par4 + var9 + 1, this.blockID, var6, 2);
                par1World.setBlock(par2 + var8 + 1, par3, par4 + var9 + 1, this.blockID, var6, 2);
            }
            else
            {
                par1World.setBlock(par2, par3, par4, this.blockID, var6, 2);
            }
        }
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }
}
