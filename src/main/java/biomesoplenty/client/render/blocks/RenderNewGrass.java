package biomesoplenty.client.render.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.client.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderNewGrass implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) 
	{
		float inset = 0.0001F;
		
        renderer.setRenderBounds(inset, inset, inset, 1F - inset, 1F - inset, 1F - inset);
        RenderUtils.renderStandardInvBlock(renderer, Blocks.grass, 0);
        
        renderer.setRenderBounds(0F, 0F, 0F, 1F, 1F, 1F);
        RenderUtils.renderStandardInvBlock(renderer, block, metadata);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		if (renderer.hasOverrideBlockTexture()) 
		{
			renderer.renderBlockUsingTexture(Blocks.grass, x, y, z, renderer.overrideBlockTexture);
		}
		else
		{
			renderer.renderStandardBlock(Blocks.grass, x, y, z);
			renderer.renderStandardBlock(block, x, y, z);
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
		return RenderUtils.newGrassModel;
	}

}
