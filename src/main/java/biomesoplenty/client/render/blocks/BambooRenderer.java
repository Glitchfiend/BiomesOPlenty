package biomesoplenty.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BambooRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) 
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		//TODO: setBlockBounds
		block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
		//TODO:		  colorMultiplier
        int l = block.func_149720_d(world, x, y, z);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float r = f3 * f;
        float f8 = f4 * f;
        float f9 = f5 * f;
        float f10 = f6 * f;
        float g = f3 * f1;
        float f12 = f4 * f1;
        float f13 = f5 * f1;
        float f14 = f6 * f1;
        float b = f3 * f2;
        float f16 = f4 * f2;
        float f17 = f5 * f2;
        float f18 = f6 * f2;
        float pixel = 0.0625F;
        //TODO:		  getMixedBrightnessForBlock()
        int m = block.func_149677_c(world, x, y, z);

        //TODO:		renderAllFaces			 shouldSideBeRendered()
        if (renderer.field_147837_f || block.func_149646_a(world, x, y - 1, z, 0))
        {
        	//TODO:								renderMinY				         getMixedBrightnessForBlock()
            tessellator.setBrightness(renderer.field_147855_j > 0.0D ? m : block.func_149677_c(world, x, y - 1, z));
            tessellator.setColorOpaque_F(r, g, b);
            //TODO:  renderFaceYNeg													getBlockIcon()
            renderer.func_147768_a(block, (double)x, (double)y, (double)z, renderer.func_147793_a(block, world, x, y, z, 0));
        }

        //TODO:		renderAllFaces			 shouldSideBeRendered()
        if (renderer.field_147837_f || block.func_149646_a(world, x, y + 1, z, 1))
        {
        	//TODO:								renderMaxY					     getMixedBrightnessForBlock()
            tessellator.setBrightness(renderer.field_147857_k < 1.0D ? m : block.func_149677_c(world, x, y + 1, z));
            tessellator.setColorOpaque_F(f8, f12, f16);
            //TODO:  renderFaceYPos													getBlockIcon()
            renderer.func_147806_b(block, (double)x, (double)y, (double)z, renderer.func_147793_a(block, world, x, y, z, 1));
        }

        tessellator.setBrightness(m);
        tessellator.setColorOpaque_F(f9, f13, f17);
        tessellator.addTranslation(0.0F, 0.0F, pixel);
		//TODO:		renderFaceZNeg								   								getBlockIcon()
        renderer.func_147761_c(block, (double)x, (double)y, (double)z + (pixel * 4), renderer.func_147793_a(block, world, x, y, z, 2));
        tessellator.addTranslation(0.0F, 0.0F, -pixel);
        tessellator.addTranslation(0.0F, 0.0F, -pixel);
		//TODO:		renderFaceZPos								   								getBlockIcon()
        renderer.func_147734_d(block, (double)x, (double)y, (double)z - (pixel * 4), renderer.func_147793_a(block, world, x, y, z, 3));
        tessellator.addTranslation(0.0F, 0.0F, pixel);
        tessellator.setColorOpaque_F(f10, f14, f18);
        tessellator.addTranslation(pixel, 0.0F, 0.0F);
		//TODO:		renderFaceXNeg								   								getBlockIcon()
        renderer.func_147798_e(block, (double)x + (pixel * 4), (double)y, (double)z, renderer.func_147793_a(block, world, x, y, z, 4));
        tessellator.addTranslation(-pixel, 0.0F, 0.0F);
        tessellator.addTranslation(-pixel, 0.0F, 0.0F);
		//TODO:		renderFaceXPos								   								getBlockIcon()
        renderer.func_147764_f(block, (double)x - (pixel * 4), (double)y, (double)z, renderer.func_147793_a(block, world, x, y, z, 5));
        tessellator.addTranslation(pixel, 0.0F, 0.0F);
        
        
        
        return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) 
	{
		return false;
	}

	@Override
	public int getRenderId() 
	{
		return RenderUtils.bambooModel;
	}
}
