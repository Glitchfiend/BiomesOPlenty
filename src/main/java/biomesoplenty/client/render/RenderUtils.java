package biomesoplenty.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import biomesoplenty.api.content.BOPCBlocks;

public class RenderUtils 
{
	public static int foliageModel = -1;
	public static int plantsModel = -1;
	public static int bonesModel = -1;
	public static int graveModel = -1;
	public static int bambooModel = -1;
	public static int newGrassModel = -1;
	public static int leavesModel = -1;
	
	public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
	{
		renderStandardInvBlock(renderblocks, block, meta, null);
	}
	
	public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta, IIcon icon)
	{
		boolean flag = block == BOPCBlocks.newBopGrass;
		
		GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
		
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon == null ? block.getIcon(0, meta) : icon);
		tessellator.draw();
		
        if (flag && renderblocks.useInventoryTint)
        {
            int colour = 16777215;

            float f1 = (float)(colour >> 16 & 255) / 255.0F;
            float f2 = (float)(colour >> 8 & 255) / 255.0F;
            float f3 = (float)(colour & 255) / 255.0F;
            GL11.glColor4f(f1, f2, f3, 1.0F);
        }
		
        if (!flag)
        {
        	tessellator.startDrawingQuads();
        	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        	renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon == null ? block.getIcon(1, meta) : icon);
        	tessellator.draw();
        }
        
		if (flag || block == Blocks.grass) GL11.glColor4f(1F, 1F, 1F, 1.0F);
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon == null ? block.getIcon(2, meta) : icon);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon == null ? block.getIcon(3, meta) : icon);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon == null ? block.getIcon(4, meta) : icon);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon == null ? block.getIcon(5, meta) : icon);
		tessellator.draw();
		
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
    public static void renderIcon(IIcon icon, double size, double z, float nx, float ny, float nz) 
    {
        renderIcon(icon, 0.0D, 0.0D, size, size, z, nx, ny, nz);
    }

    public static void renderIcon(IIcon icon, double xStart, double yStart, double xEnd, double yEnd, double z, float nx, float ny, float nz) 
    {
        if (icon == null) icon = getMissingIcon(TextureMap.locationItemsTexture);

        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();
        tessellator.setNormal(nx, ny, nz);

        if (nz > 0.0F) 
        {
            tessellator.addVertexWithUV(xStart, yStart, z, icon.getMinU(), icon.getMinV());
            tessellator.addVertexWithUV(xEnd, yStart, z, icon.getMaxU(), icon.getMinV());
            tessellator.addVertexWithUV(xEnd, yEnd, z, icon.getMaxU(), icon.getMaxV());
            tessellator.addVertexWithUV(xStart, yEnd, z, icon.getMinU(), icon.getMaxV());
        } 
        else 
        {
            tessellator.addVertexWithUV(xStart, yEnd, z, icon.getMinU(), icon.getMaxV());
            tessellator.addVertexWithUV(xEnd, yEnd, z, icon.getMaxU(), icon.getMaxV());
            tessellator.addVertexWithUV(xEnd, yStart, z, icon.getMaxU(), icon.getMinV());
            tessellator.addVertexWithUV(xStart, yStart, z, icon.getMinU(), icon.getMinV());
        }

        tessellator.draw();
    }
    
    public static void renderIcon(int indexX, int indexY, float minU, float maxU, float minV, float maxV, double z) 
    {
        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        
        //0.5000098 0.5156152 0.71876955 0.74998045

        tessellator.addVertexWithUV(0D, 16D, z, minU, maxV);
        tessellator.addVertexWithUV(16D, 16D, z, maxU, maxV);
        tessellator.addVertexWithUV(16D, 0D, z, maxU, minV);
        tessellator.addVertexWithUV(0D, 0D, z, minU, minV);

        tessellator.draw();
    }

    public static IIcon getMissingIcon(ResourceLocation textureSheet) 
    {
        return ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(textureSheet)).getAtlasSprite("missingno");
    }
}
