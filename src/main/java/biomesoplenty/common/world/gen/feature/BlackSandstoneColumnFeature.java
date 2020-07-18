package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.NetherCrystalBlock;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ColumnConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import javax.annotation.Nullable;
import java.util.Random;

public class BlackSandstoneColumnFeature extends Feature<ColumnConfig> {
    private static final ImmutableList<Block> CANNOT_PLACE_ON = ImmutableList.of(Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, Blocks.WATER, Blocks.LAVA, Blocks.BEDROCK, Blocks.MAGMA_BLOCK, Blocks.SOUL_SAND, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_WART, Blocks.CHEST, Blocks.SPAWNER);

    public BlackSandstoneColumnFeature(Codec<ColumnConfig> p_i231925_1_) {
        super(p_i231925_1_);
    }

    public boolean place(ISeedReader p_230362_1_, StructureManager p_230362_2_, ChunkGenerator p_230362_3_, Random p_230362_4_, BlockPos p_230362_5_, ColumnConfig p_230362_6_) {
        int i = p_230362_3_.getSeaLevel();
        BlockPos blockpos = findSurface(p_230362_1_, i, p_230362_5_.mutable().clamp(Direction.Axis.Y, 1, p_230362_1_.getMaxBuildHeight() - 1), Integer.MAX_VALUE);
        if (blockpos == null) {
            return false;
        } else {
            int j = calculateHeight(p_230362_4_, p_230362_6_);
            boolean flag = p_230362_4_.nextFloat() < 0.9F;
            int k = Math.min(j, flag ? 5 : 8);
            int l = flag ? 50 : 15;
            boolean flag1 = false;

            for(BlockPos blockpos1 : BlockPos.randomBetweenClosed(p_230362_4_, l, blockpos.getX() - k, blockpos.getY(), blockpos.getZ() - k, blockpos.getX() + k, blockpos.getY(), blockpos.getZ() + k)) {
                int i1 = j - blockpos1.distManhattan(blockpos);
                if (i1 >= 0) {
                    flag1 |= this.placeColumn(p_230362_1_, i, blockpos1, i1, calculateReach(p_230362_4_, p_230362_6_));
                }
            }

            return flag1;
        }
    }

    private boolean placeColumn(IWorld p_236248_1_, int p_236248_2_, BlockPos p_236248_3_, int p_236248_4_, int p_236248_5_) {
        boolean flag = false;

        for(BlockPos blockpos : BlockPos.betweenClosed(p_236248_3_.getX() - p_236248_5_, p_236248_3_.getY(), p_236248_3_.getZ() - p_236248_5_, p_236248_3_.getX() + p_236248_5_, p_236248_3_.getY(), p_236248_3_.getZ() + p_236248_5_)) {
            int i = blockpos.distManhattan(p_236248_3_);
            BlockPos blockpos1 = isAirOrLavaOcean(p_236248_1_, p_236248_2_, blockpos) ? findSurface(p_236248_1_, p_236248_2_, blockpos.mutable(), i) : findAir(p_236248_1_, blockpos.mutable(), i);
            if (blockpos1 != null) {
                int j = p_236248_4_ - i / 2;

                for(BlockPos.Mutable blockpos$mutable = blockpos1.mutable(); j >= 0; --j) {
                    if (isAirOrLavaOcean(p_236248_1_, p_236248_2_, blockpos$mutable)) {
                        this.setBlock(p_236248_1_, blockpos$mutable, BOPBlocks.black_sandstone.defaultBlockState());
                        blockpos$mutable.move(Direction.UP);
                        flag = true;
                    } else {
                        if (!p_236248_1_.getBlockState(blockpos$mutable).is( BOPBlocks.black_sandstone)) {
                            break;
                        }

                        blockpos$mutable.move(Direction.UP);
                    }
                }
            }
        }

        return flag;
    }

    @Nullable
    private static BlockPos findSurface(IWorld p_236246_0_, int p_236246_1_, BlockPos.Mutable p_236246_2_, int p_236246_3_) {
        for(; p_236246_2_.getY() > 1 && p_236246_3_ > 0; p_236246_2_.move(Direction.DOWN)) {
            --p_236246_3_;
            if (isAirOrLavaOcean(p_236246_0_, p_236246_1_, p_236246_2_)) {
                BlockState blockstate = p_236246_0_.getBlockState(p_236246_2_.move(Direction.DOWN));
                p_236246_2_.move(Direction.UP);
                if (!blockstate.isAir() && !(blockstate.getBlock() instanceof BushBlock) && !CANNOT_PLACE_ON.contains(blockstate.getBlock())) {
                    return p_236246_2_;
                }
            }
        }

        return null;
    }

    @Nullable
    private static BlockPos findAir(IWorld p_236249_0_, BlockPos.Mutable p_236249_1_, int p_236249_2_) {
        while(p_236249_1_.getY() < p_236249_0_.getMaxBuildHeight() && p_236249_2_ > 0) {
            --p_236249_2_;
            BlockState blockstate = p_236249_0_.getBlockState(p_236249_1_);
            if (CANNOT_PLACE_ON.contains(blockstate.getBlock())) {
                return null;
            }

            if (blockstate.isAir() || blockstate.getBlock() instanceof BushBlock) {
                return p_236249_1_;
            }

            p_236249_1_.move(Direction.UP);
        }

        return null;
    }

    private static int calculateHeight(Random p_236250_0_, ColumnConfig p_236250_1_) {
        return p_236250_1_.minimumHeight + p_236250_0_.nextInt(p_236250_1_.maximumHeight - p_236250_1_.minimumHeight + 1);
    }

    private static int calculateReach(Random p_236251_0_, ColumnConfig p_236251_1_) {
        return p_236251_1_.minimumReach + p_236251_0_.nextInt(p_236251_1_.maximumReach - p_236251_1_.minimumReach + 1);
    }

    private static boolean isAirOrLavaOcean(IWorld p_236247_0_, int p_236247_1_, BlockPos p_236247_2_) {
        BlockState blockstate = p_236247_0_.getBlockState(p_236247_2_);
        return blockstate.isAir() || blockstate.getBlock() instanceof BushBlock || blockstate.is(Blocks.LAVA) && p_236247_2_.getY() <= p_236247_1_;
    }
}