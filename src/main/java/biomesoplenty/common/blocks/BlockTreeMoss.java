package biomesoplenty.common.blocks;

import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import biomesoplenty.BiomesOPlenty;

public class BlockTreeMoss extends BlockVine
{
	public BlockTreeMoss()
	{
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		//TODO:		setBurnProperties() getIdFromBlock()
		Blocks.fire.func_149842_a(func_149682_b(this), 15, 100);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
				//TODO: blockIcon
		this.field_149761_L = iconRegister.registerIcon("biomesoplenty:treemoss");
	}
}
