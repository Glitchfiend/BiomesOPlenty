package biomesoplenty.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderUtils 
{
	public static int foliageModel = -1;
	public static int plantsModel = -1;
	public static int bonesModel = -1;
	public static int graveModel = -1;
	public static int bambooModel = -1;
	
	public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		//TODO:		renderFaceYNeg								   getIcon()
		renderblocks.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		//TODO:		renderFaceYPos								   getIcon()
		renderblocks.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		//TODO:		renderFaceZNeg								   getIcon()
		renderblocks.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		//TODO:		renderFaceZPos								   getIcon()
		renderblocks.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		//TODO:		renderFaceXNeg								   getIcon()
		renderblocks.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		//TODO:		renderFaceXPos								   getIcon()
		renderblocks.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
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
