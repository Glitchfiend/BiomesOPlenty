package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree;

public class BiomeGenWasteland extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);

    public BiomeGenWasteland(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(5919808);
        this.setTemperatureRainfall(1.0F, 0.05F);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.waterColorMultiplier = 15073024;
        
        this.topBlock = BOPCBlocks.driedDirt;
        this.fillerBlock = BOPCBlocks.driedDirt;
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 20;
        
        this.theBiomeDecorator.bopFeatures.poisonLakesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterLakesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.wasteland1PerChunk = 1;
        this.theBiomeDecorator.bopFeatures.wasteland2PerChunk = 1;
        this.theBiomeDecorator.bopFeatures.wasteland3PerChunk = 1;
        this.theBiomeDecorator.bopFeatures.wasteland4PerChunk = 1;
        this.theBiomeDecorator.bopFeatures.wastelandRockPilesPerChunk = 2;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 20;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.plants, 0), 1D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenDeadTree();
    }
    
    @Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int x = chunkX + random.nextInt(16);
            int y = random.nextInt(28) + 4;
            int z = chunkZ + random.nextInt(16);

            Block block = world.getBlock(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(10))
            {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 10, 2);
            }
        }
	}

    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 10330232;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 10067541;
    }
    
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 9477744;
        else return super.getSkyColorByTemp(par1);
    }

    @Override
    public int getFogColour(int x, int y, int z)
    {
        return 12106885;
    }

    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.3F;
    }
}
