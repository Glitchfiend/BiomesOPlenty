package biomesoplenty.blocks.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderUtils 
{
	public static int altarModel = -1;
	public static int foliageModel = -1;
	public static int plantsModel = -1;
	public static int puddleModel = -1;
	public static int bonesModel = -1;
	public static int graveModel = -1;
	
	public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
    public static boolean renderFace(RenderBlocks renderer, Block block, Icon icon, IBlockAccess world, int x, int y, int z, ForgeDirection face) 
    {
        return renderFace(renderer, block, icon, world, x, y, z, -1, face);
    }

    public static boolean renderFace(RenderBlocks renderer, Block block, Icon icon, IBlockAccess world, int x, int y, int z, int brightness, ForgeDirection face) 
    {
        if(icon == null) return false;
        int color = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        if(EntityRenderer.anaglyphEnable) {
            float redA = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
            float greenA = (red * 30.0F + green * 70.0F) / 100.0F;
            float blueA = (red * 30.0F + blue * 70.0F) / 100.0F;
            red = redA;
            green = greenA;
            blue = blueA;
        }
        if(Minecraft.isAmbientOcclusionEnabled()) { return renderFaceWithAmbientOcclusion(renderer, block, icon, world, x, y, z, red, green, blue, brightness, face); }
        return renderFaceWithColorMultiplier(renderer, block, world, icon, x, y, z, red, green, blue, brightness, face);
    }
	
    private static boolean renderFaceWithAmbientOcclusion(RenderBlocks renderer, Block block, Icon icon, IBlockAccess world, int x, int y, int z, float r, float g, float b, int brightness, ForgeDirection face) {
        renderer.enableAO = true;
        boolean renderedFace = false;
        boolean brightFlag = brightness > -1;
        float colorModTopLeft = 0.0F;
        float colorModBottomLeft = 0.0F;
        float colorModBottomRight = 0.0F;
        float colorModTopRight = 0.0F;
        boolean locationColorFlag = true;
        int mixedBrightnessForBlock = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if(renderer.getBlockIcon(block).getIconName().equals("grass_top")) {
            locationColorFlag = false;
        } else if(renderer.hasOverrideBlockTexture()) {
            locationColorFlag = false;
        }

        boolean grassXYNN;
        boolean grassXYPN;
        boolean grassYZNN;
        boolean grassYZNP;
        float lightValue;
        int mixedBrightnessForSide;

        if(face == ForgeDirection.DOWN && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1, z, 0))) {
            if(renderer.renderMinY <= 0.0D) {
                --y;
            }

            renderer.aoBrightnessXYNN = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZNP = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPN = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            renderer.aoLightValueScratchXYNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
            renderer.aoLightValueScratchYZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
            renderer.aoLightValueScratchYZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
            renderer.aoLightValueScratchXYPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
            grassXYPN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y - 1, z)];
            grassXYNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y - 1, z)];
            grassYZNP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y - 1, z + 1)];
            grassYZNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y - 1, z - 1)];

            if(!grassYZNN && !grassXYNN) {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNN = brightFlag ? brightness : renderer.aoBrightnessXYNN;
            } else {
                renderer.aoLightValueScratchXYZNNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z - 1);
                renderer.aoBrightnessXYZNNN = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
            }

            if(!grassYZNP && !grassXYNN) {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNP = brightFlag ? brightness : renderer.aoBrightnessXYNN;
            } else {
                renderer.aoLightValueScratchXYZNNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z + 1);
                renderer.aoBrightnessXYZNNP = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
            }

            if(!grassYZNN && !grassXYPN) {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNN = brightFlag ? brightness : renderer.aoBrightnessXYPN;
            } else {
                renderer.aoLightValueScratchXYZPNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z - 1);
                renderer.aoBrightnessXYZPNN = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
            }

            if(!grassYZNP && !grassXYPN) {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNP = brightFlag ? brightness : renderer.aoBrightnessXYPN;
            } else {
                renderer.aoLightValueScratchXYZPNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z + 1);
                renderer.aoBrightnessXYZPNP = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
            }

            if(renderer.renderMinY <= 0.0D) {
                ++y;
            }

            mixedBrightnessForSide = mixedBrightnessForBlock;

            if(renderer.renderMinY <= 0.0D || !renderer.blockAccess.isBlockOpaqueCube(x, y - 1, z)) {
                mixedBrightnessForSide = brightFlag ? brightness : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            }

            lightValue = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
            colorModTopLeft = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + lightValue) / 4.0F;
            colorModTopRight = (renderer.aoLightValueScratchYZNP + lightValue + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
            colorModBottomRight = (lightValue + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
            colorModBottomLeft = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + lightValue + renderer.aoLightValueScratchYZNN) / 4.0F;
            renderer.brightnessTopLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, mixedBrightnessForSide);
            renderer.brightnessTopRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, mixedBrightnessForSide);
            renderer.brightnessBottomRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, mixedBrightnessForSide);
            renderer.brightnessBottomLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, mixedBrightnessForSide);

            if(locationColorFlag) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.5F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
            }

            renderer.colorRedTopLeft *= colorModTopLeft;
            renderer.colorGreenTopLeft *= colorModTopLeft;
            renderer.colorBlueTopLeft *= colorModTopLeft;
            renderer.colorRedBottomLeft *= colorModBottomLeft;
            renderer.colorGreenBottomLeft *= colorModBottomLeft;
            renderer.colorBlueBottomLeft *= colorModBottomLeft;
            renderer.colorRedBottomRight *= colorModBottomRight;
            renderer.colorGreenBottomRight *= colorModBottomRight;
            renderer.colorBlueBottomRight *= colorModBottomRight;
            renderer.colorRedTopRight *= colorModTopRight;
            renderer.colorGreenTopRight *= colorModTopRight;
            renderer.colorBlueTopRight *= colorModTopRight;
            renderer.renderFaceYNeg(block, x, y, z, icon);
            renderedFace = true;
        }

        if(face == ForgeDirection.UP && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1, z, 1))) {
            if(renderer.renderMaxY >= 1.0D) {
                ++y;
            }

            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoLightValueScratchXYNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
            renderer.aoLightValueScratchXYPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
            renderer.aoLightValueScratchYZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
            renderer.aoLightValueScratchYZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
            grassXYPN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y + 1, z)];
            grassXYNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y + 1, z)];
            grassYZNP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y + 1, z + 1)];
            grassYZNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y + 1, z - 1)];

            if(!grassYZNN && !grassXYNN) {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
            } else {
                renderer.aoLightValueScratchXYZNPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z - 1);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
            }

            if(!grassYZNN && !grassXYPN) {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
            } else {
                renderer.aoLightValueScratchXYZPPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z - 1);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
            }

            if(!grassYZNP && !grassXYNN) {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
            } else {
                renderer.aoLightValueScratchXYZNPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z + 1);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
            }

            if(!grassYZNP && !grassXYPN) {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
            } else {
                renderer.aoLightValueScratchXYZPPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z + 1);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
            }

            if(renderer.renderMaxY >= 1.0D) {
                --y;
            }

            mixedBrightnessForSide = mixedBrightnessForBlock;

            if(renderer.renderMaxY >= 1.0D || !renderer.blockAccess.isBlockOpaqueCube(x, y + 1, z)) {
                mixedBrightnessForSide = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            }

            lightValue = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
            colorModTopRight = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + lightValue) / 4.0F;
            colorModTopLeft = (renderer.aoLightValueScratchYZPP + lightValue + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
            colorModBottomLeft = (lightValue + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            colorModBottomRight = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + lightValue + renderer.aoLightValueScratchYZPN) / 4.0F;
            renderer.brightnessTopRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, mixedBrightnessForSide);
            renderer.brightnessTopLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, mixedBrightnessForSide);
            renderer.brightnessBottomLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, mixedBrightnessForSide);
            renderer.brightnessBottomRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, mixedBrightnessForSide);
            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b;
            renderer.colorRedTopLeft *= colorModTopLeft;
            renderer.colorGreenTopLeft *= colorModTopLeft;
            renderer.colorBlueTopLeft *= colorModTopLeft;
            renderer.colorRedBottomLeft *= colorModBottomLeft;
            renderer.colorGreenBottomLeft *= colorModBottomLeft;
            renderer.colorBlueBottomLeft *= colorModBottomLeft;
            renderer.colorRedBottomRight *= colorModBottomRight;
            renderer.colorGreenBottomRight *= colorModBottomRight;
            renderer.colorBlueBottomRight *= colorModBottomRight;
            renderer.colorRedTopRight *= colorModTopRight;
            renderer.colorGreenTopRight *= colorModTopRight;
            renderer.colorBlueTopRight *= colorModTopRight;
            renderer.renderFaceYPos(block, x, y, z, icon);
            renderedFace = true;
        }

        if(face == ForgeDirection.NORTH && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z - 1, 2))) {
            if(renderer.renderMinZ <= 0.0D) {
                --z;
            }

            renderer.aoLightValueScratchXZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
            renderer.aoLightValueScratchYZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
            renderer.aoLightValueScratchYZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
            renderer.aoLightValueScratchXZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            grassXYPN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y, z - 1)];
            grassXYNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y, z - 1)];
            grassYZNP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y + 1, z - 1)];
            grassYZNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y - 1, z - 1)];

            if(!grassXYNN && !grassYZNN) {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y - 1, z);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
            }

            if(!grassXYNN && !grassYZNP) {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y + 1, z);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
            }

            if(!grassXYPN && !grassYZNN) {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y - 1, z);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
            }

            if(!grassXYPN && !grassYZNP) {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y + 1, z);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
            }

            if(renderer.renderMinZ <= 0.0D) {
                ++z;
            }

            mixedBrightnessForSide = mixedBrightnessForBlock;

            if(renderer.renderMinZ <= 0.0D || !renderer.blockAccess.isBlockOpaqueCube(x, y, z - 1)) {
                mixedBrightnessForSide = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            }

            lightValue = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
            colorModTopLeft = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + lightValue + renderer.aoLightValueScratchYZPN) / 4.0F;
            colorModBottomLeft = (lightValue + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            colorModBottomRight = (renderer.aoLightValueScratchYZNN + lightValue + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
            colorModTopRight = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + lightValue) / 4.0F;
            renderer.brightnessTopLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, mixedBrightnessForSide);
            renderer.brightnessBottomLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, mixedBrightnessForSide);
            renderer.brightnessBottomRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, mixedBrightnessForSide);
            renderer.brightnessTopRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, mixedBrightnessForSide);

            if(locationColorFlag) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }

            renderer.colorRedTopLeft *= colorModTopLeft;
            renderer.colorGreenTopLeft *= colorModTopLeft;
            renderer.colorBlueTopLeft *= colorModTopLeft;
            renderer.colorRedBottomLeft *= colorModBottomLeft;
            renderer.colorGreenBottomLeft *= colorModBottomLeft;
            renderer.colorBlueBottomLeft *= colorModBottomLeft;
            renderer.colorRedBottomRight *= colorModBottomRight;
            renderer.colorGreenBottomRight *= colorModBottomRight;
            renderer.colorBlueBottomRight *= colorModBottomRight;
            renderer.colorRedTopRight *= colorModTopRight;
            renderer.colorGreenTopRight *= colorModTopRight;
            renderer.colorBlueTopRight *= colorModTopRight;
            renderer.renderFaceZNeg(block, x, y, z, icon);

            renderedFace = true;
        }

        if(face == ForgeDirection.SOUTH && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z + 1, 3))) {
            if(renderer.renderMaxZ >= 1.0D) {
                ++z;
            }

            renderer.aoLightValueScratchXZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
            renderer.aoLightValueScratchXZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
            renderer.aoLightValueScratchYZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
            renderer.aoLightValueScratchYZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            grassXYPN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y, z + 1)];
            grassXYNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y, z + 1)];
            grassYZNP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y + 1, z + 1)];
            grassYZNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x, y - 1, z + 1)];

            if(!grassXYNN && !grassYZNN) {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y - 1, z);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
            }

            if(!grassXYNN && !grassYZNP) {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y + 1, z);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
            }

            if(!grassXYPN && !grassYZNN) {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y - 1, z);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
            }

            if(!grassXYPN && !grassYZNP) {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y + 1, z);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
            }

            if(renderer.renderMaxZ >= 1.0D) {
                --z;
            }

            mixedBrightnessForSide = mixedBrightnessForBlock;

            if(renderer.renderMaxZ >= 1.0D || !renderer.blockAccess.isBlockOpaqueCube(x, y, z + 1)) {
                mixedBrightnessForSide = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            }

            lightValue = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
            colorModTopLeft = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + lightValue + renderer.aoLightValueScratchYZPP) / 4.0F;
            colorModTopRight = (lightValue + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            colorModBottomRight = (renderer.aoLightValueScratchYZNP + lightValue + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
            colorModBottomLeft = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + lightValue) / 4.0F;
            renderer.brightnessTopLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, mixedBrightnessForSide);
            renderer.brightnessTopRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, mixedBrightnessForSide);
            renderer.brightnessBottomRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, mixedBrightnessForSide);
            renderer.brightnessBottomLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, mixedBrightnessForSide);

            if(locationColorFlag) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }

            renderer.colorRedTopLeft *= colorModTopLeft;
            renderer.colorGreenTopLeft *= colorModTopLeft;
            renderer.colorBlueTopLeft *= colorModTopLeft;
            renderer.colorRedBottomLeft *= colorModBottomLeft;
            renderer.colorGreenBottomLeft *= colorModBottomLeft;
            renderer.colorBlueBottomLeft *= colorModBottomLeft;
            renderer.colorRedBottomRight *= colorModBottomRight;
            renderer.colorGreenBottomRight *= colorModBottomRight;
            renderer.colorBlueBottomRight *= colorModBottomRight;
            renderer.colorRedTopRight *= colorModTopRight;
            renderer.colorGreenTopRight *= colorModTopRight;
            renderer.colorBlueTopRight *= colorModTopRight;
            renderer.renderFaceZPos(block, x, y, z, icon);

            renderedFace = true;
        }

        if(face == ForgeDirection.WEST && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y, z, 4))) {
            if(renderer.renderMinX <= 0.0D) {
                --x;
            }

            renderer.aoLightValueScratchXYNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
            renderer.aoLightValueScratchXZNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
            renderer.aoLightValueScratchXZNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
            renderer.aoLightValueScratchXYNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
            renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            grassXYPN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y + 1, z)];
            grassXYNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y - 1, z)];
            grassYZNP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y, z - 1)];
            grassYZNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1, y, z + 1)];

            if(!grassYZNP && !grassXYNN) {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z - 1);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
            }

            if(!grassYZNN && !grassXYNN) {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z + 1);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
            }

            if(!grassYZNP && !grassXYPN) {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z - 1);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
            }

            if(!grassYZNN && !grassXYPN) {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z + 1);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
            }

            if(renderer.renderMinX <= 0.0D) {
                ++x;
            }

            mixedBrightnessForSide = mixedBrightnessForBlock;

            if(renderer.renderMinX <= 0.0D || !renderer.blockAccess.isBlockOpaqueCube(x - 1, y, z)) {
                mixedBrightnessForSide = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            }

            lightValue = block.getAmbientOcclusionLightValue(renderer.blockAccess, x - 1, y, z);
            colorModTopRight = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + lightValue + renderer.aoLightValueScratchXZNP) / 4.0F;
            colorModTopLeft = (lightValue + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
            colorModBottomLeft = (renderer.aoLightValueScratchXZNN + lightValue + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
            colorModBottomRight = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + lightValue) / 4.0F;
            renderer.brightnessTopRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, mixedBrightnessForSide);
            renderer.brightnessTopLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, mixedBrightnessForSide);
            renderer.brightnessBottomLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, mixedBrightnessForSide);
            renderer.brightnessBottomRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, mixedBrightnessForSide);

            if(locationColorFlag) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }

            renderer.colorRedTopLeft *= colorModTopLeft;
            renderer.colorGreenTopLeft *= colorModTopLeft;
            renderer.colorBlueTopLeft *= colorModTopLeft;
            renderer.colorRedBottomLeft *= colorModBottomLeft;
            renderer.colorGreenBottomLeft *= colorModBottomLeft;
            renderer.colorBlueBottomLeft *= colorModBottomLeft;
            renderer.colorRedBottomRight *= colorModBottomRight;
            renderer.colorGreenBottomRight *= colorModBottomRight;
            renderer.colorBlueBottomRight *= colorModBottomRight;
            renderer.colorRedTopRight *= colorModTopRight;
            renderer.colorGreenTopRight *= colorModTopRight;
            renderer.colorBlueTopRight *= colorModTopRight;
            renderer.renderFaceXNeg(block, x, y, z, icon);

            renderedFace = true;
        }

        if(face == ForgeDirection.EAST && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y, z, 5))) {
            if(renderer.renderMaxX >= 1.0D) {
                ++x;
            }

            renderer.aoLightValueScratchXYPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z);
            renderer.aoLightValueScratchXZPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z - 1);
            renderer.aoLightValueScratchXZPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y, z + 1);
            renderer.aoLightValueScratchXYPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z);
            renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            grassXYPN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y + 1, z)];
            grassXYNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y - 1, z)];
            grassYZNP = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y, z + 1)];
            grassYZNN = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1, y, z - 1)];

            if(!grassXYNN && !grassYZNN) {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPNN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z - 1);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
            }

            if(!grassXYNN && !grassYZNP) {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPNP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y - 1, z + 1);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
            }

            if(!grassXYPN && !grassYZNN) {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPPN = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z - 1);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
            }

            if(!grassXYPN && !grassYZNP) {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPPP = block.getAmbientOcclusionLightValue(renderer.blockAccess, x, y + 1, z + 1);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
            }

            if(renderer.renderMaxX >= 1.0D) {
                --x;
            }

            mixedBrightnessForSide = mixedBrightnessForBlock;

            if(renderer.renderMaxX >= 1.0D || !renderer.blockAccess.isBlockOpaqueCube(x + 1, y, z)) {
                mixedBrightnessForSide = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            }

            lightValue = block.getAmbientOcclusionLightValue(renderer.blockAccess, x + 1, y, z);
            colorModTopLeft = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + lightValue + renderer.aoLightValueScratchXZPP) / 4.0F;
            colorModBottomLeft = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + lightValue) / 4.0F;
            colorModBottomRight = (renderer.aoLightValueScratchXZPN + lightValue + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
            colorModTopRight = (lightValue + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            renderer.brightnessTopLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, mixedBrightnessForSide);
            renderer.brightnessTopRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, mixedBrightnessForSide);
            renderer.brightnessBottomRight = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, mixedBrightnessForSide);
            renderer.brightnessBottomLeft = brightFlag ? brightness : renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, mixedBrightnessForSide);

            if(locationColorFlag) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }

            renderer.colorRedTopLeft *= colorModTopLeft;
            renderer.colorGreenTopLeft *= colorModTopLeft;
            renderer.colorBlueTopLeft *= colorModTopLeft;
            renderer.colorRedBottomLeft *= colorModBottomLeft;
            renderer.colorGreenBottomLeft *= colorModBottomLeft;
            renderer.colorBlueBottomLeft *= colorModBottomLeft;
            renderer.colorRedBottomRight *= colorModBottomRight;
            renderer.colorGreenBottomRight *= colorModBottomRight;
            renderer.colorBlueBottomRight *= colorModBottomRight;
            renderer.colorRedTopRight *= colorModTopRight;
            renderer.colorGreenTopRight *= colorModTopRight;
            renderer.colorBlueTopRight *= colorModTopRight;
            renderer.renderFaceXPos(block, x, y, z, icon);

            renderedFace = true;
        }

        renderer.enableAO = false;
        return renderedFace;
    }
    
    private static boolean renderFaceWithColorMultiplier(RenderBlocks renderer, Block block, IBlockAccess world, Icon icon, int x, int y, int z, float r, float g, float b, int brightness, ForgeDirection face) {
        //renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float yMod = 0.5F;
        float f4 = 1.0F;
        float zMod = 0.8F;
        float xMod = 0.6F;
        float f7 = f4 * r;
        float f8 = f4 * g;
        float f9 = f4 * b;
        float rY = yMod;
        float rZ = zMod;
        float rX = xMod;
        float gY = yMod;
        float gZ = zMod;
        float gX = xMod;
        float bY = yMod;
        float bZ = zMod;
        float bX = xMod;

        if(block != Block.grass) {
            rY = yMod * r;
            rZ = zMod * r;
            rX = xMod * r;
            gY = yMod * g;
            gZ = zMod * g;
            gX = xMod * g;
            bY = yMod * b;
            bZ = zMod * b;
            bX = xMod * b;
        }

        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if(face == ForgeDirection.DOWN && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1, z, 0))) {
            if(brightness != -1)
                tessellator.setBrightness(brightness);
            else
                tessellator.setBrightness(renderer.renderMinY > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z));
            tessellator.setColorOpaque_F(rY, gY, bY);
            renderer.renderFaceYNeg(block, x, y, z, icon);
            flag = true;
        }

        if(face == ForgeDirection.UP && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1, z, 1))) {
            if(brightness != -1)
                tessellator.setBrightness(brightness);
            else
                tessellator.setBrightness(renderer.renderMaxY < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z));
            tessellator.setColorOpaque_F(f7, f8, f9);
            renderer.renderFaceYPos(block, x, y, z, icon);
            flag = true;
        }

        if(face == ForgeDirection.NORTH && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z - 1, 2))) {
            if(brightness != -1)
                tessellator.setBrightness(brightness);
            else
                tessellator.setBrightness(renderer.renderMinZ > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1));
            tessellator.setColorOpaque_F(rZ, gZ, bZ);
            renderer.renderFaceZNeg(block, x, y, z, icon);

            flag = true;
        }

        if(face == ForgeDirection.SOUTH && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z + 1, 3))) {
            if(brightness != -1)
                tessellator.setBrightness(brightness);
            else
                tessellator.setBrightness(renderer.renderMaxZ < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1));
            tessellator.setColorOpaque_F(rZ, gZ, bZ);
            renderer.renderFaceZPos(block, x, y, z, icon);

            flag = true;
        }

        if(face == ForgeDirection.WEST && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y, z, 40))) {
            if(brightness != -1)
                tessellator.setBrightness(brightness);
            else
                tessellator.setBrightness(renderer.renderMinX > 0.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z));
            tessellator.setColorOpaque_F(rX, gX, bX);
            renderer.renderFaceXNeg(block, x, y, z, icon);

            flag = true;
        }

        if(face == ForgeDirection.EAST && (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y, z, 5))) {
            if(brightness != -1)
                tessellator.setBrightness(brightness);
            else
                tessellator.setBrightness(renderer.renderMaxX < 1.0D ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z));
            tessellator.setColorOpaque_F(rX, gX, bX);
            renderer.renderFaceXPos(block, x, y, z, icon);

            flag = true;
        }

        return flag;
    }	

}
