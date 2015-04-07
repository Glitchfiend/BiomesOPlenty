package biomesoplenty.client.render.blocks;

import org.lwjgl.opengl.GL11;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ColorizedLeavesRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) 
	{
		float inset = 0.0001F;
		
		BlockBOPColorizedLeaves leavesBlock = (BlockBOPColorizedLeaves)block;
		
		renderer.setRenderBounds(inset, inset, inset, 1F - inset, 1F - inset, 1F - inset);
		RenderUtils.renderStandardInvBlock(renderer, block, metadata);
        
		if (block == BOPCBlocks.colorizedLeaves2 && metadata % 4 == 3)
		{
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
			renderer.setRenderBounds(0F, 0F, 0F, 1F, 1F, 1F);
			RenderUtils.renderStandardInvBlock(renderer, Blocks.stone, 0, leavesBlock.floweringIcon);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		BlockBOPColorizedLeaves leavesBlock = (BlockBOPColorizedLeaves)block;
		
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (block == BOPCBlocks.colorizedLeaves2 && metadata % 4 == 3 && renderer.hasOverrideBlockTexture()) 
		{
			renderer.setOverrideBlockTexture(renderer.overrideBlockTexture);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.clearOverrideBlockTexture();
		}
		else
		{
			renderer.renderStandardBlock(block, x, y, z);
			
			if (block == BOPCBlocks.colorizedLeaves2 && metadata % 4 == 3)
			{
				renderer.setOverrideBlockTexture(leavesBlock.floweringIcon);
				renderer.renderStandardBlock(Blocks.stone, x, y, z);
				renderer.clearOverrideBlockTexture();
			}
		}
		
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
		return RenderUtils.leavesModel;
	}
}
