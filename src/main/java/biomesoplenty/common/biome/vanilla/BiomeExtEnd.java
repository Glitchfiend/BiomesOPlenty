package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.biome.ExtendedBiomeWrapper;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.world.feature.GeneratorCrystals;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeExtEnd extends ExtendedBiomeWrapper
{
    public BiomeExtEnd()
    {
        super(BiomeGenBase.sky);
        
        this.addGenerator("crystals", GeneratorStage.ORE_PRE, (new GeneratorCrystals.Builder()).amountPerChunk(6.0F).placeOn(BlockQueries.endish).with(BOPBlocks.crystal.getDefaultState()).create());
    }
}
