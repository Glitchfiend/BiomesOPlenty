package biomesoplenty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class OriginGrassBlock extends GrassBlock
{
    public OriginGrassBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) { return false; }
}
