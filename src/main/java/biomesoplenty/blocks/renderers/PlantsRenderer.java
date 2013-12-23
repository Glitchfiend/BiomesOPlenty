package biomesoplenty.blocks.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.blocks.BlockBOPPlant;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PlantsRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		// Doesn't render in inventory
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == RenderUtils.plantsModel)
		{
			int meta = world.getBlockMetadata(x, y, z);
			if (meta < 5)
				return renderCrossedSquares(block, x, y, z, renderer, true);
			if (meta == 5)
				return renderer.renderCrossedSquares(block, x, y, z);
			if (meta == 6)
				return renderBlockCrops(block, x, y, z, renderer);
			if (meta == 7)
				return renderer.renderBlockCrops(block, x, y, z);
			if (meta == 8)
				return renderer.renderBlockCrops(block, x, y, z);
			if (meta == 9)
				return renderer.renderBlockCrops(block, x, y, z);
			if (meta == 10)
				return renderer.renderBlockCrops(block, x, y, z);
			if (meta == 11)
				return renderer.renderBlockCrops(block, x, y, z);
			if (meta == 12)
				return renderCrossedSquares(block, x, y, z, renderer, true);
			if (meta == 13)
				return renderer.renderBlockCrops(block, x, y, z);
			if (meta == 14)
			{
				return renderCrossedSquares(block, x, y, z, renderer, false);
			}
			if (meta == 15)
				return renderCrossedSquares(block, x, y, z, renderer, true);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return RenderUtils.plantsModel;
	}

	private boolean renderBlockCrops(Block par1Block, int par2, int par3, int par4, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4));
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

		double d0 = par2;
		double d1 = par3;
		double d2 = par4;

		long i1 = par2 * 3129871 ^ par4 * 116129781L ^ par3;

		i1 = i1 * i1 * 42317861L + i1 * 11L;
		d0 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.125D;
		d2 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.125D;

		renderer.renderBlockCropsImpl(par1Block, renderer.blockAccess.getBlockMetadata(par2, par3, par4), d0, par3 - 0.0625F, d2);
		return true;
	}

	private boolean renderCrossedSquares(Block par1Block, int par2, int par3, int par4, RenderBlocks renderer, boolean colourMultiply)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4));
		float f = 1.0F;
		int l = par1Block.colorMultiplier(renderer.blockAccess, par2, par3, par4);
		float f1 = (l >> 16 & 255) / 255.0F;
		float f2 = (l >> 8 & 255) / 255.0F;
		float f3 = (l & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable)
		{
			float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f4;
			f2 = f5;
			f3 = f6;
		}
		
		if (!colourMultiply)
		{
			f1 = f;
			f2 = f;
			f3 = f;
		}

		tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
		double d0 = par2;
		double d1 = par3;
		double d2 = par4;

		long i1 = par2 * 3129871 ^ par4 * 116129781L ^ par3;
		
		int meta = renderer.blockAccess.getBlockMetadata(par2, par3, par4);

		if (meta == 15)
		{
			i1 = i1 * i1 * 42317861L + i1 * 11L;
			d0 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.2D;
			d1 -= ((i1 >> 20 & 15L) / 15.0F - 1.0D) * 0.4D;
			d2 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.2D;
		}
		else
		{
			i1 = i1 * i1 * 42317861L + i1 * 11L;
			d0 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.5D;
			d1 += ((i1 >> 20 & 15L) / 15.0F - 1.0D) * 0.2D;
			d2 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.5D;
		}
		
		if (meta == 14)
		{
			renderer.drawCrossedSquares(par1Block, meta, d0, d1, d2, 1.0F);
			RenderUtils.renderCrossedSquaresFromIcon(((BlockBOPPlant)BOPBlocks.plants.get()).reedbottom, d0, d1 - 1, d2, 1.0F, renderer);
		}
		else
		{
			renderer.drawCrossedSquares(par1Block, meta, d0, d1, d2, 1.0F);
		}
		return true;
	}
}
