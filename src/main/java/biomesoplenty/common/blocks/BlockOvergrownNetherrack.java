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
		//TODO: Material.rock
		super(Material.field_151576_e);
		
		//TODO: this.setHardness
		this.func_149711_c(0.4F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);
		
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		this.icons[0] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack3");
		this.icons[1] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack1");
		this.icons[2] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		this.icons[3] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		this.icons[4] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
		this.icons[5] = iconRegister.registerIcon("biomesoplenty:overgrownnetherrack2");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
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
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		return Blocks.netherrack.func_149650_a(0, random, fortune);
	}
}
