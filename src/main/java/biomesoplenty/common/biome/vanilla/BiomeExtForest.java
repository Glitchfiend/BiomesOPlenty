package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.biome.overworld.BOPOverworldBiome;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import net.minecraft.init.Biomes;

public class BiomeExtForest extends ExtendedBiomeWrapper
{
    public BiomeExtForest()
    {
        super(Biomes.FOREST);
        
        if (BOPBiomes.gravel_beach.isPresent())
        {
            this.beachBiomeLocation = ((BOPOverworldBiome)BOPBiomes.gravel_beach.get()).getResourceLocation();
        }
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("flowering_oak", 1, (new GeneratorBasicTree.Builder()).altLeaves(BOPTrees.FLOWERING).create());
        
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(0.2F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 3, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());        
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        
        // other plants
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.SPROUT).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SHRUB).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).placeOn(BlockQueries.fertile).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).placeOn(BlockQueries.fertile).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.15F).generationAttempts(128).with(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("poison_ivy", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(BOPPlants.POISONIVY).create());
        
        // shrooms
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("blue_milk_caps", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.BLUE_MILK_CAP).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.2F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("white_anemones", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("blue_hydrangeas", 2, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.AMBER).create());
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
        
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("amber");}
        if (!settings.isEnabled(GeneratorType.POISON_IVY)) {this.removeGenerator("poison_ivy");}
        
        if (!settings.isEnabled(GeneratorType.BERRY_BUSHES)) {this.removeGenerator("berry_bushes");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("flowers");}
        
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("toadstools"); this.removeGenerator("flat_mushroom"); this.removeGenerator("blue_milk_caps"); this.removeGenerator("portobellos");}
        
        if (!settings.isEnabled(GeneratorType.FOLIAGE)) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.isEnabled(GeneratorType.TREES)) {this.removeGenerator("trees");}
        
        if (!settings.isEnabled(GeneratorType.GRASSES)) {this.removeGenerator("grass");}
    }
}
