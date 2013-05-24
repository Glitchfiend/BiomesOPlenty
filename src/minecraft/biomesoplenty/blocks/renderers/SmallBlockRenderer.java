package biomesoplenty.blocks.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import biomesoplenty.BiomesOPlenty;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class SmallBlockRenderer implements ISimpleBlockRenderingHandler
{
	public static int bonesModel = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == bonesModel)
		{
			int meta = world.getBlockMetadata(x, y, z);
			
			//0.062 Approx (Per pixel)
			if (meta == 0)
			{
				renderer.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 1)
			{
				renderer.setRenderBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 3)
			{
				renderer.setRenderBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 4)
			{
				renderer.setRenderBounds(0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 5)
			{
				renderer.setRenderBounds(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (meta == 6)
			{
				renderer.setRenderBounds(0.0F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else
			{
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
		}
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == bonesModel)
		{
			Tessellator tessellator = Tessellator.instance;
			
			if (metadata == 0)
			{
				renderer.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
				BiomesOPlenty.proxy.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 1)
			{
				renderer.setRenderBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
				BiomesOPlenty.proxy.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 3)
			{
				renderer.setRenderBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
				BiomesOPlenty.proxy.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 4)
			{
				renderer.setRenderBounds(0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.875F);
				BiomesOPlenty.proxy.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 5)
			{
				renderer.setRenderBounds(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 1.0F);
				BiomesOPlenty.proxy.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 6)
			{
				renderer.setRenderBounds(0.0F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
				BiomesOPlenty.proxy.renderStandardInvBlock(renderer, block, metadata);
			}
			else
			{
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				BiomesOPlenty.proxy.renderStandardInvBlock(renderer, block, metadata);
			}
		}
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return bonesModel;
	}
}
