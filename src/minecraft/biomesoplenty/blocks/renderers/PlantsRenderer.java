package biomesoplenty.blocks.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class PlantsRenderer implements ISimpleBlockRenderingHandler
{
    public static int render = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        // Doesn't render in inventory
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        if (modelId == render)
        {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta > 5)
                return renderer.renderBlockCrops(block, x, y, z);
            else
                return renderer.renderCrossedSquares(block, x, y, z);
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
        return render;
    }
}
