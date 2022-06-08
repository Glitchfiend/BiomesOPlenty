package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GlowingMossBlock extends MossBlock implements BonemealableBlock
{
    public GlowingMossBlock(BlockBehaviour.Properties p_153790_) {
        super(p_153790_);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_153797_, BlockPos p_153798_, BlockState p_153799_, boolean p_153800_)
    {
        return p_153797_.getBlockState(p_153798_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_153802_, RandomSource p_153803_, BlockPos p_153804_, BlockState p_153805_)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_153792_, RandomSource p_153793_, BlockPos p_153794_, BlockState p_153795_)
    {
// TODO:
//        Feature.VEGETATION_PATCH.place(new FeaturePlaceContext<>(p_153792_, p_153792_.getChunkSource().getGenerator(), p_153793_, p_153794_.above(), BOPConfiguredFeatures.GLOWING_MOSS_PATCH_BONEMEAL.config()));
    }
}