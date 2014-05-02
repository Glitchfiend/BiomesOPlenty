package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockOvergrownNetherrack extends Block
{
	private IIcon[] icons = new IIcon[6];

	public BlockOvergrownNetherrack()
	{
		super(Material.rock);
		
		this.setHardness(0.4F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.icons[0] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack3");
		this.icons[1] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack1");
		this.icons[2] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		this.icons[3] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		this.icons[4] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		this.icons[5] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
	    if (side < 0 || side >= this.icons.length) side = 1;
        
		return this.icons[side];
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return Blocks.netherrack.getItemDropped(0, random, fortune);
	}
}
