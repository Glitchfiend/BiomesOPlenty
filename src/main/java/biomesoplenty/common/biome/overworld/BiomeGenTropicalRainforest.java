package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenRainforestTree1;

public class BiomeGenTropicalRainforest extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.2F, 0.3F);

    public BiomeGenTropicalRainforest(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(8970560);
        this.setTemperatureRainfall(1.2F, 1.0F);

        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        
        this.waterColorMultiplier = 6160128;

        this.theBiomeDecorator.treesPerChunk = 12;
        this.theBiomeDecorator.grassPerChunk = 9;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.waterlilyPerChunk = 4;

        this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
        this.theBiomeDecorator.bopFeatures.generateMelons = true;
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.generateQuicksand = true;
        this.theBiomeDecorator.bopFeatures.poisonIvyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 9;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 5), 12);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.75D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(3), 1D);
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenTrees(false, 4 + random.nextInt(7), 3, 3, true) : 
        new WorldGenRainforestTree1(BOPCBlocks.logs4, BOPCBlocks.colorizedLeaves2, 3, 2, false, 8, 8);
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

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(6))
            {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 6, 2);
            }
        }
    }
    
    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 11002176 : 12836929;
	}
    
    @Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 8970560 : 10870849;
	}
    
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 11128415;
        else return super.getSkyColorByTemp(par1);
    }

    @Override
    public int getFogColour(int x, int y, int z)
    {
        return 16228194;
    }

    public float getFogDensity(int x, int y, int z)
    {
        return 0.99F;
    }
}
