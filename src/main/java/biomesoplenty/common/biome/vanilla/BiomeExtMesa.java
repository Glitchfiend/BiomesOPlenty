package biomesoplenty.common.biome.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.biome.ExtendedBiomeWrapper;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeExtMesa extends ExtendedBiomeWrapper
{
    public BiomeExtMesa()
    {
        super(BiomeGenBase.mesa);
        
        // other plants
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(0.5F).with(BOPPlants.DESERTGRASS).generationAttempts(8).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.TINYCACTUS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.2F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("bromeliad", 2, (new GeneratorFlora.Builder().with(BOPFlowers.BROMELIAD).create()));
        
        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());    
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("ruby");}
        
        if (!settings.generateBopFlowers) {this.removeGenerator("flowers");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopGrasses) {this.removeGenerator("desertgrass");}
    }
}
