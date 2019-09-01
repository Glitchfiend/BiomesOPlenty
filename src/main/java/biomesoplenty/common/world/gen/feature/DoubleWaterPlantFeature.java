package biomesoplenty.common.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class DoubleWaterPlantFeature extends Feature<DoublePlantConfig> {
    public DoubleWaterPlantFeature(Function<Dynamic<?>, ? extends DoublePlantConfig> p_i49884_1_) {
        super(p_i49884_1_);
    }

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, DoublePlantConfig config) {
        boolean flag = false;

        for(int i = 0; i < 64; ++i) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER && worldIn.isAirBlock(blockpos.up()) && blockpos.getY() < worldIn.getWorld().getDimension().getHeight() - 2 && config.state.isValidPosition(worldIn, blockpos)) {
                ((DoublePlantBlock)config.state.getBlock()).placeAt(worldIn, blockpos, 2);
                flag = true;
            }
        }

        return flag;
    }
}