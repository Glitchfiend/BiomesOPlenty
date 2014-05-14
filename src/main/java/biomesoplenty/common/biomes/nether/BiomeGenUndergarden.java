package biomesoplenty.common.biomes.nether;

import net.minecraft.init.Blocks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenUndergarden extends BOPNetherBiome
{
    public BiomeGenUndergarden(int id)
    {
        super(id);
        
        this.setColor(15657658);
        
        this.topBlock = BOPBlockHelper.get("overgrownNetherrack");
        this.fillerBlock = Blocks.netherrack;
        
        this.theBiomeDecorator.mushroomsPerChunk = 60;
		this.theBiomeDecorator.bigMushroomsPerChunk = 30;
        
        this.bopWorldFeatures.setFeature("netherVinesPerChunk", 20);
        this.bopWorldFeatures.setFeature("netherrackSplatterPerChunk", 45);
        //customBiomeDecorator.gravesPerChunk = 1;
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 3);
        this.bopWorldFeatures.setFeature("glowshroomsPerChunk", 1);
        
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 30);
		
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 0.25D);
		this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
    }
}
