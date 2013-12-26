package biomesoplenty.blocks.renderers;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.BlockBOPColorizedLeaves;
import biomesoplenty.blocks.BlockBOPLeaves;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class LeavesRenderer implements ISimpleBlockRenderingHandler
{

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        RenderUtils.renderStandardInvBlock(renderer, block, metadata);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        BlockBOPLeaves leaves = (BlockBOPLeaves)block;
        
        Icon christmasLights = leaves.christmasLights;
        
        renderer.renderStandardBlock(block, x, y, z);
        
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
        {
        	if (world.getBlockId(x, y, z) == Blocks.leaves2.get().blockID)
        	{
	            if (world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 5 || world.getBlockMetadata(x, y, z) == 9)
	            {
	                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
	                {
	                    if (direction != ForgeDirection.UP && direction != ForgeDirection.DOWN)
	                    {
	                    	RenderUtils.renderFace(renderer, block, christmasLights, world, x, y, z, 200, direction);
	                    }
	                    else
	                    {
	                        renderer.aoBrightnessXYNN = 150;
	                        renderer.aoBrightnessYZNN = 150;
	                        renderer.aoBrightnessYZNP = 150;
	                        renderer.aoBrightnessXYPN = 150;
	                    }
	                }
	            }
        	}
        }
        


        return true;
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return RenderUtils.leavesModel;
    }
}
