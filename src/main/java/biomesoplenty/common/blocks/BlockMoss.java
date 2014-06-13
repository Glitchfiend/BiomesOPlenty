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
		this.setHardness(0.2F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:moss");
	}
	
	@Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        switch (side)
        {
            case 1:
                return (world.getBlock(x, y + 1, z).isWood(world, x, y + 1, z) || world.getBlock(x, y + 1, z) == Blocks.stone);
            case 2:
            	return (world.getBlock(x, y, z + 1).isWood(world, x, y, z + 1) || world.getBlock(x, y, z + 1) == Blocks.stone);
            case 3:
            	return (world.getBlock(x, y, z - 1).isWood(world, x, y, z - 1) || world.getBlock(x, y, z - 1) == Blocks.stone);
            case 4:
            	return (world.getBlock(x + 1, y, z).isWood(world, x + 1, y, z) || world.getBlock(x + 1, y, z) == Blocks.stone);
            case 5:
            	return (world.getBlock(x - 1, y, z).isWood(world, x - 1, y, z) || world.getBlock(x - 1, y, z) == Blocks.stone);
            default:
                return false;
        }
    }
}
