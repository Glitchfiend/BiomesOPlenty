package biomesoplenty.common.blocks;

import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;

public class BlockIvy extends BlockVine implements IShearable
{
	public BlockIvy()
	{
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:ivy");
	}
}
