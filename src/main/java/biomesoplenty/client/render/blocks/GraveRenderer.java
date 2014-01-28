package biomesoplenty.client.render.blocks;

import biomesoplenty.client.render.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class GraveRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == RenderUtils.graveModel)
		{
			int meta = world.getBlockMetadata(x, y, z);
			float pixel = 0.0625F;

			if (meta == 0)
			{
				//Base
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*5, 0, pixel*5, pixel*11, pixel*3, pixel*11);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Base pole
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*6, pixel*3, pixel*6, pixel*10, pixel*11, pixel*10);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal bottom 
				//TODO: setRenderBounds
				renderer.func_147782_a(0, pixel*11, pixel*5, pixel*16, pixel*14, pixel*11);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
			}
			else if (meta == 1)
			{
				//Head vertical side 0
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*13, pixel*-2, pixel*5, pixel*16, pixel*8, pixel*11);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head vertical side 1
				//TODO: setRenderBounds
				renderer.func_147782_a(0, pixel*-2, pixel*5, pixel*3, pixel*8, pixel*11);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Cross vertical side 1
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*6, pixel*-3, pixel*7, pixel*10, pixel*14, pixel*9);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal middle 0 
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*-4, pixel*1, pixel*7, pixel*6, pixel*5, pixel*9);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal middle 1 
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*10, pixel*1, pixel*7, pixel*20, pixel*5, pixel*9);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal top 
				//TODO: setRenderBounds
				renderer.func_147782_a(0, pixel*8, pixel*5, pixel*16, pixel*11, pixel*11);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
			}
			else if (meta == 2)
			{
				//Base
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*5, 0, pixel*5, pixel*11, pixel*3, pixel*11);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Base pole
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*6, pixel*3, pixel*6, pixel*10, pixel*11, pixel*10);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal bottom 
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*5, pixel*11, 0, pixel*11, pixel*14, pixel*16);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
			}
			else if (meta == 3)
			{
				//Head vertical side 0
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*5, pixel*-2, pixel*13, pixel*11, pixel*8, pixel*16);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head vertical side 1
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*5, pixel*-2, 0, pixel*11, pixel*8, pixel*3);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Cross vertical side 1
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*7, pixel*-3, pixel*6, pixel*9, pixel*14, pixel*10);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal middle 0 
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*7, pixel*1, pixel*-4, pixel*9, pixel*5, pixel*6);
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal middle 1 
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*7, pixel*1, pixel*10, pixel*9, pixel*5, pixel*20);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
				
				//Head horizontal top 
				//TODO: setRenderBounds
				renderer.func_147782_a(pixel*5, pixel*8, 0, pixel*11, pixel*11, pixel*16);
				//TODO: renderStandardBlock
				renderer.func_147784_q(block, x, y, z);
			}
		}
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return RenderUtils.graveModel;
	}
}
