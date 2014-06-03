package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTreeMoss extends BlockVine
{
	public BlockTreeMoss()
	{
		//TODO: this.setHardness
		this.setHardness(0.2F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeGrass);
		
		//TODO: setTickRandomly()
		this.setTickRandomly(true);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
				//TODO: blockIcon
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:treemoss");
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 16777215;
    }

    // JAVADOC METHOD $$ func_149741_i
	@Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
        return 16777215;
    }

    // JAVADOC METHOD $$ func_149720_d
	@Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return 16777215;
    }
}
