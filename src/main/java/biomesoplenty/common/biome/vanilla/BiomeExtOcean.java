package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import net.minecraft.init.Biomes;

public class BiomeExtOcean extends ExtendedBiomeWrapper
{
    public BiomeExtOcean()
    {
        super(Biomes.OCEAN);
        
        // gem
        this.addGenerator("sapphire", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.SAPPHIRE).create());    
    }
}
