package biomesoplenty.common.handler;

import java.util.Collections;

import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPGrassPath;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GrassPathEventHandler
{
    @SubscribeEvent
    public void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        EnumFacing facing = event.getFace();
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof BlockBOPGrass && stack != null && (stack.getItem() instanceof ItemSpade || stack.getItem().getToolClasses(stack) == Collections.singleton("shovel")))
        {
            Block dirtBlock = BlockBOPGrass.getDirtBlockState(state).getBlock();
            if (dirtBlock instanceof BlockBOPDirt)
            {
                if (facing != EnumFacing.DOWN && world.getBlockState(pos.up()).getMaterial() == Material.AIR)
                {
                    world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    if (!world.isRemote)
                    {
                        BlockBOPDirt.BOPDirtType dirtType = (BlockBOPDirt.BOPDirtType) BlockBOPGrass.getDirtBlockState(state).getValue(BlockBOPDirt.VARIANT);
                        world.setBlockState(pos, BlockBOPGrassPath.paging.getVariantState(dirtType), 11);
                        stack.damageItem(1, player);
                    }
                }
            }
        }
    }
}