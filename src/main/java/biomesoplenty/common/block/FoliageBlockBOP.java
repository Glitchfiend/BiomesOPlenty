/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import biomesoplenty.common.world.gen.feature.BOPFeatures;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;

public class FoliageBlockBOP extends BushBlock implements IPlantable, IGrowable
{
	protected static final VoxelShape NORMAL = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	protected static final VoxelShape SHORT = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
	
    public FoliageBlockBOP(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
    {
    	Block block = state.getBlock();
        
        if (block == BOPBlocks.desert_grass || block == BOPBlocks.clover)
        {
        	return SHORT;
        }
        
        return NORMAL;
    }
    
    @Override
    public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isClientSide && stack.getItem() == Items.SHEARS)
        {
           player.awardStat(Stats.BLOCK_MINED.get(this));
           player.causeFoodExhaustion(0.005F);
           this.popResource(worldIn, pos, new ItemStack(this));
        }
        else
        {
           super.playerDestroy(worldIn, player, pos, state, te, stack);
        }
     }
    
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune)
    {
       world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
       return java.util.Arrays.asList(new ItemStack(this));
    }
    
    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());
        Block ground = groundState.getBlock();

        if (this == BOPBlocks.sprout)
        {
            return groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP) || super.canSurvive(state, worldIn, pos);
        }
        if (this == BOPBlocks.dune_grass)
        {
            return ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.white_sand || ground == BOPBlocks.orange_sand || ground == BOPBlocks.black_sand;
        }
        if (this == BOPBlocks.desert_grass || this == BOPBlocks.dead_grass)
        {
            return ground == BOPBlocks.dried_salt || ground == Blocks.GRAVEL || ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.white_sand || ground == BOPBlocks.orange_sand || ground == BOPBlocks.black_sand || ground == Blocks.NETHERRACK || super.canSurvive(state, worldIn, pos);
        }

        return super.canSurvive(state, worldIn, pos);
    }
    
    @Override
    public Block.OffsetType getOffsetType()
    {
        return Block.OffsetType.XYZ;
    }
    
    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
    	return PlantType.PLAINS;
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.clover)
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.clover) { return (double)rand.nextFloat() < 0.4D; }

        return false;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.clover) { this.growHugeClover(world, rand, pos, state); }
    }

    public boolean growHugeClover(ServerWorld world, Random rand, BlockPos pos, BlockState state)
    {
        world.removeBlock(pos, false);
        ConfiguredFeature<NoFeatureConfig, ?> configuredfeature = BOPFeatures.HUGE_CLOVER.configured(IFeatureConfig.NONE);

        if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos))
        {
            return true;
        }
        else
        {
            world.setBlock(pos, state, 3);
            return false;
        }
    }
}
