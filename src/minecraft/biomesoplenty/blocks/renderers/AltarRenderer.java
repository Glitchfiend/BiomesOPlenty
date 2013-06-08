package biomesoplenty.blocks.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.BlockAltar;
import biomesoplenty.tileentity.TileEntityAltar;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class AltarRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		TileEntityAltar tileentityaltar = (TileEntityAltar) world.getBlockTileEntity(x, y, z);
		
		if (modelId == RenderUtils.altarModel)
		{
			if (tileentityaltar != null)
			{
				renderer.renderStandardBlock(block, x, y, z);

				this.renderAltarSlotFaces(renderer, block, BlockAltar.altarFrame, world, x, y, z);

				if (tileentityaltar.getPresent("apatite"))
				{
					this.renderAltarSlotFaces(renderer, block, BlockAltar.frameApatite, world, x, y, z);
				}

				if (tileentityaltar.getPresent("peridot"))
				{
					this.renderAltarSlotFaces(renderer, block, BlockAltar.framePeridot, world, x, y, z);
				}

				if (tileentityaltar.getPresent("ruby"))
				{
					this.renderAltarSlotFaces(renderer, block, BlockAltar.frameRuby, world, x, y, z);
				}

				if (tileentityaltar.getPresent("sapphire"))
				{
					this.renderAltarSlotFaces(renderer, block, BlockAltar.frameSapphire, world, x, y, z);
				}

				if (tileentityaltar.getPresent("tanzanite"))
				{
					this.renderAltarSlotFaces(renderer, block, BlockAltar.frameTanzanite, world, x, y, z);
				}

				if (tileentityaltar.getPresent("topaz"))
				{
					this.renderAltarSlotFaces(renderer, block, BlockAltar.frameTopaz, world, x, y, z);
				}
			}
		}

		return true;
	}
	
	public static void renderAltarSlotFaces(RenderBlocks renderer, Block block, Icon icon, IBlockAccess world, int x, int y, int z)
	{	
		RenderUtils.renderFace(renderer, block, icon, world, x, y, z, ForgeDirection.NORTH);
		RenderUtils.renderFace(renderer, block, icon, world, x, y, z, ForgeDirection.EAST);
		RenderUtils.renderFace(renderer, block, icon, world, x, y, z, ForgeDirection.SOUTH);
		RenderUtils.renderFace(renderer, block, icon, world, x, y, z, ForgeDirection.WEST);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;

		renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		RenderUtils.renderStandardInvBlock(renderer, block, metadata);
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderUtils.altarModel;
	}
}
