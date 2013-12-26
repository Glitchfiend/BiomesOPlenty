package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockOriginGrass extends Block
{
	private IIcon[] icon = new IIcon[6];

	public BlockOriginGrass()
	{
		super(Material.grass);
		
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		this.icon[0] = par1IconRegister.registerIcon("biomesoplenty:origingrass3");
		this.icon[1] = par1IconRegister.registerIcon("biomesoplenty:origingrass1");
		this.icon[2] = par1IconRegister.registerIcon("biomesoplenty:origingrass2");
		this.icon[3] = par1IconRegister.registerIcon("biomesoplenty:origingrass2");
		this.icon[4] = par1IconRegister.registerIcon("biomesoplenty:origingrass2");
		this.icon[5] = par1IconRegister.registerIcon("biomesoplenty:origingrass2");
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	@Override
	public IIcon getIcon(int par1, int par2)
	{
	    if (par1 < 0 || par1 >= 		//TODO: blockIcon
		this.field_149761_L.length)
	        par1 = 1;
        
		return icon[par1];
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par5EntityPlayer.getCurrentEquippedItem() != null)
		{
			if (par5EntityPlayer.getCurrentEquippedItem().getDisplayName().toLowerCase().contains(" hoe"))
			{
				Block block = Block.tilledField;

				world.playSoundEffect(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

				if (!world.isRemote)
				{
					world.setBlock(par2, par3, par4, block.blockID);
				}
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	//TODO:		updateTick()
	public void func_149674_a(World world, int x, int y, int z, Random random)(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && Block.lightOpacity[par1World.getBlockId(par2, par3 + 1, par4)] > 2)
			{
				par1World.setBlock(par2, par3, par4, Block.dirt.blockID);
			}
			else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
			{
				for (int var6 = 0; var6 < 4; ++var6)
				{
					int var7 = par2 + par5Random.nextInt(3) - 1;
					int var8 = par3 + par5Random.nextInt(5) - 3;
					int var9 = par4 + par5Random.nextInt(3) - 1;
					int var10 = par1World.getBlockId(var7, var8 + 1, var9);

					if (par1World.getBlockId(var7, var8, var9) == Block.dirt.blockID && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
					{
						par1World.setBlock(var7, var8, var9, Blocks.originGrass.get().blockID);
					}
				}
			}
		}
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		return Block.dirt.idDropped(0, par2Random, par3);
	}
}
