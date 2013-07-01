package biomesoplenty.blocks.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PuddleRender implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock (Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == RenderUtils.puddleModel)
		{
			RenderUtils.renderStandardInvBlock(renderer, block, metadata);
		}
	}

	@Override
	public boolean renderWorldBlock (IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		if (modelID == RenderUtils.puddleModel)
		{
			if (ClientProxy.puddleRenderPass == 0)
			{
				renderer.renderStandardBlock(block, x, y, z);
			}
			else
			{
				renderer.setRenderBounds(0.0, 0.8135, 0.0, 1.0, 0.8880, 1.0);

				Block liquidBlock = Block.blocksList[Block.waterStill.blockID];
				BlockSkinRenderHelper.renderMetadataBlock(liquidBlock, 0, x, y, z, renderer, world);
			}
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderUtils.puddleModel;
	}
}
