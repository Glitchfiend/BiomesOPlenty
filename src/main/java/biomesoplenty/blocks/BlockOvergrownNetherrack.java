package biomesoplenty.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlocks;

public class BlockOvergrownNetherrack extends Block
{
	private Icon[] blockIcon = new Icon[6];

	public BlockOvergrownNetherrack(int par1)
	{
		super(par1, Material.rock);
		this.setTickRandomly(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		blockIcon[0] = par1IconRegister.registerIcon("biomesoplenty:overgrownnetherrack3");
		blockIcon[1] = par1IconRegister.registerIcon("biomesoplenty:overgrownnetherrack1");
		blockIcon[2] = par1IconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		blockIcon[3] = par1IconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		blockIcon[4] = par1IconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		blockIcon[5] = par1IconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	@Override
	public Icon getIcon(int par1, int par2)
	{
	    if (par1 < 0 || par1 >= blockIcon.length)
	        par1 = 1;
        
		return blockIcon[par1];
	}

	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	/*public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return 32;
        }
        else if (par5 == 0)
        {
            return 34;
        }
        else
        {
            Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
            return var6 != Material.snow && var6 != Material.craftedSnow ? 33 : 33;
        }
    }*/

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Block.netherrack.idDropped(0, par2Random, par3);
	}
}
