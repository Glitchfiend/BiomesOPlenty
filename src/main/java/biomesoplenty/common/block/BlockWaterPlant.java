/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import javax.annotation.Nullable;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

public class BlockWaterPlant extends BlockPlantBOP
{
	protected static final VoxelShape NORMAL = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
    public BlockWaterPlant(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return NORMAL;
    }

    @Override
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
    {
        IFluidState ifluidstate = worldIn.getFluidState(pos.down());
        Block ground = worldIn.getBlockState(pos.down(2)).getBlock();
        return (ifluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && (ground == Blocks.DIORITE || ground == Blocks.GRANITE || ground == Blocks.ANDESITE || ground == Blocks.STONE || ground == Blocks.DIRT || ground == Blocks.COARSE_DIRT || ground == Blocks.GRASS_BLOCK || ground == Blocks.GRAVEL || ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.white_sand || ground == BOPBlocks.mud || ground == BOPBlocks.dried_sand);
    }
    
    @Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
    {
        return Items.AIR;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
           player.addStat(StatList.BLOCK_MINED.get(this));
           player.addExhaustion(0.005F);
           spawnAsEntity(worldIn, pos, new ItemStack(this));
        }
        else
        {
           super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
     }
    
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune)
    {
       world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
       return java.util.Arrays.asList(new ItemStack(this));
    }
}
