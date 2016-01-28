package biomesoplenty.common.biome.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.biome.ExtendedBiomeWrapper;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPSand;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeExtDesert extends ExtendedBiomeWrapper
{
    public BiomeExtDesert()
    {
        super(BiomeGenBase.desert);
        
        // quicksand
        this.addGenerator("quicksand", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.2F).liquid(BOPBlocks.sand.getDefaultState().withProperty(BlockBOPSand.VARIANT, BlockBOPSand.SandType.QUICKSAND)).frozenLiquid((IBlockState)null).create());
        
        // other plants
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.TINYCACTUS).create());
        
        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());    
    }
}
