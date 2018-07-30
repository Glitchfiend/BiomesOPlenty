package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BlockQueries;
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

public class BiomeExtForestHills extends ExtendedBiomeWrapper
{
    public BiomeExtForestHills()
    {
        super(Biomes.FOREST_HILLS);
        
        if (BOPBiomes.gravel_beach.isPresent())
        {
            this.beachBiomeLocation = ((BOPOverworldBiome)BOPBiomes.gravel_beach.get()).getResourceLocation();
        }
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(0.2F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 3, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());        
        //grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        //grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        
        // other plants
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.SPROUT).create());
        //this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SHRUB).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).placeOn(BlockQueries.fertile).generationAttempts(16).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).placeOn(BlockQueries.fertile).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("poison_ivy", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.25F).with(BOPPlants.POISONIVY).create());

        // shrooms
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("blue_milk_caps", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.BLUE_MILK_CAP).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.15F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("white_anemones", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("blue_hydrangeas", 1, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.AMBER).create());
    }
}
