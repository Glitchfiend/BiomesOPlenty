package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockOvergrownNetherrack extends Block
{
	private IIcon[] blockIcon = new IIcon[6];

	public BlockOvergrownNetherrack()
	{
		super(Material.rock);
		this.setTickRandomly(true);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister)
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
	public IIcon getIcon(int par1, int par2)
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
