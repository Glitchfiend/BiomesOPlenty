package biomesoplenty.client.render.blocks;

import biomesoplenty.client.render.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderNewGrass implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) 
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		renderer.renderStandardBlock(Blocks.grass, x, y, z);
		
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) 
	{
		return true;
	}

	@Override
	public int getRenderId() 
	{
		return RenderUtils.newGrassModel;
	}

}
