package biomesoplenty.blocks;

import java.util.Random;

import biomesoplenty.mod_BiomesOPlenty;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

@Deprecated
public class BlockDarkLog extends Block
{
    /** The type of tree this log came from. */
    public static final String[] woodType = new String[] {"dark"};
    private Icon[] blockIcon = new Icon[2];

    public BlockDarkLog(int par1)
    {
        super(par1, Material.wood);
		this.setBurnProperties(this.blockID, 5, 5);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon[0] = par1IconRegister.registerIcon("BiomesOPlenty:logTopBottum");
		this.blockIcon[1] = par1IconRegister.registerIcon("BiomesOPlenty:darklog");
	}
	
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
    	int pos = par2 & 12;
        if (pos == 0 && (par1 == 1 || par1 == 0) || pos == 4 && (par1 == 5 || par1 == 4) || pos == 8 && (par1 == 2 || par1 == 3))
            return blockIcon[0];
        return blockIcon[1];
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 31;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return BOPBlocks.darkWood.blockID;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        byte var7 = 4;
        int var8 = var7 + 1;

        if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
        {
            for (int var9 = -var7; var9 <= var7; ++var9)
            {
                for (int var10 = -var7; var10 <= var7; ++var10)
                {
                    for (int var11 = -var7; var11 <= var7; ++var11)
                    {
                        int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);

                        if (var12 == BOPBlocks.darkLeaves.blockID)
                        {
                            int var13 = par1World.getBlockMetadata(par2 + var9, par3 + var10, par4 + var11);

                            if ((var13 & 8) == 0)
                            {
                                par1World.setBlockMetadataWithNotify(par2 + var9, par3 + var10, par4 + var11, var13 | 8, 2);
                            }
                        }
                    }
                }
            }
        }
    }

    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int var10 = par9 & 3;
        byte var11 = 0;

        switch (par5)
        {
            case 0:
            case 1:
                var11 = 0;
                break;

            case 2:
            case 3:
                var11 = 8;
                break;

            case 4:
            case 5:
                var11 = 4;
        }

        return var10 | var11;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    /*public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        int var3 = par2 & 12;
        int var4 = par2 & 3;
        return var3 == 0 && (par1 == 1 || par1 == 0) ? 255 : (var3 == 4 && (par1 == 5 || par1 == 4) ? 255 : (var3 == 8 && (par1 == 2 || par1 == 3) ? 255 : (var4 == 1 ? 116 : (var4 == 2 ? 117 : (var4 == 3 ? 153 : 37)))));
    }*/

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    /**
     * returns a number between 0 and 3
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, limitToValidMetadata(par1));
    }

    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(World world, int x, int y, int z)
    {
        return true;
    }
}
