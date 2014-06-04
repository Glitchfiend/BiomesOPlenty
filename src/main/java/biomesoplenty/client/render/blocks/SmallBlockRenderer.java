package biomesoplenty.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.client.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SmallBlockRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == RenderUtils.bonesModel)
		{
			int meta = world.getBlockMetadata(x, y, z);

			//0.062 Approx (Per pixel)
			if (meta == 0)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.374F, 0.0F, 0.374F, 0.626F, 1.0F, 0.626F);
				//TODO: renderStandardBlock
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 1)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.187F, 0.0F, 0.187F, 0.813F, 1.0F, 0.813F);
				//TODO: renderStandardBlock
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 3)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.374F, 0.374F, 0.0F, 0.626F, 0.626F, 1.0F);
				//TODO: renderStandardBlock
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 4)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.0F, 0.374F, 0.374F, 1.0F, 0.626F, 0.626F);
				//TODO: renderStandardBlock
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 5)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.187F, 0.187F, 0.0F, 0.813F, 0.813F, 1.0F);
				//TODO: renderStandardBlock
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 6)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.0F, 0.187F, 0.187F, 1.0F, 0.813F, 0.813F);
				//TODO: renderStandardBlock
				renderer.renderStandardBlock(block, x, y, z);
			}
			else
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				//TODO: renderStandardBlock
				renderer.renderStandardBlock(block, x, y, z);
			}
		}
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == RenderUtils.bonesModel)
		{
			Tessellator tessellator = Tessellator.instance;

			if (metadata == 0)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.374F, 0.0F, 0.374F, 0.626F, 1.0F, 0.626F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 1)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.187F, 0.0F, 0.187F, 0.813F, 1.0F, 0.813F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 3)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.374F, 0.374F, 0.0F, 0.626F, 0.626F, 1.0F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 4)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.0F, 0.374F, 0.374F, 1.0F, 0.626F, 0.626F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 5)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.187F, 0.187F, 0.0F, 0.813F, 0.813F, 1.0F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 6)
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.0F, 0.187F, 0.187F, 1.0F, 0.813F, 0.813F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else
			{
				//TODO: setRenderBounds
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
		}
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderUtils.bonesModel;
	}
}
