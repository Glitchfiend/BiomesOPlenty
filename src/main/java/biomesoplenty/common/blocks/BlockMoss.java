package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import biomesoplenty.BiomesOPlenty;

public class BlockMoss extends BlockVine
{
	public BlockMoss()
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
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:moss");
	}
	
    // JAVADOC METHOD $$ func_149707_d
	@Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        switch (side)
        {
            case 1:
                return ((OreDictionary.getOreID("logWood") == OreDictionary.getOreID(new ItemStack(world.getBlock(x, y + 1, z)))) || (world.getBlock(x, y + 1, z) == Blocks.stone));
            case 2:
            	return ((OreDictionary.getOreID("logWood") == OreDictionary.getOreID(new ItemStack(world.getBlock(x, y, z + 1)))) || (world.getBlock(x, y, z + 1) == Blocks.stone));
            case 3:
            	return ((OreDictionary.getOreID("logWood") == OreDictionary.getOreID(new ItemStack(world.getBlock(x, y, z - 1)))) || (world.getBlock(x, y, z - 1) == Blocks.stone));
            case 4:
            	return ((OreDictionary.getOreID("logWood") == OreDictionary.getOreID(new ItemStack(world.getBlock(x + 1, y, z)))) || (world.getBlock(x + 1, y, z) == Blocks.stone));
            case 5:
            	return ((OreDictionary.getOreID("logWood") == OreDictionary.getOreID(new ItemStack(world.getBlock(x - 1, y, z)))) || (world.getBlock(x - 1, y, z) == Blocks.stone));
            default:
                return false;
        }
    }
}
