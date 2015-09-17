package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga3;

public class BiomeGenTemperateRainforest extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.0F, 0.3F);

	public BiomeGenTemperateRainforest(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(12311907);
        this.setTemperatureRainfall(0.8F, 1.2F);
		
		this.theBiomeDecorator.treesPerChunk = 22;
		this.theBiomeDecorator.grassPerChunk = 6;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.waterlilyPerChunk = 2;

		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 2;
		this.theBiomeDecorator.bopFeatures.gravelPerChunk = 9;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.poisonIvyPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.wildCarrotsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 5;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 25;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 2D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(3), 0.25D);
	}

	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(10) == 0 ? new WorldGenBOPSwampTree(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves2, 1, 0, 6, 9, BOPCBlocks.colorizedLeaves2, 0)  : 
		(random.nextInt(6) == 0 ? new WorldGenBOPTaiga3(Blocks.log, Blocks.leaves, 0, 0, false, 35, 10, 0, -1) : 
		(random.nextInt(2) == 0 ? new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 0, 0, false, 10, 25, 8, -1) : 
		new WorldGenShrub(0, 0)));
	}

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
        super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);

        for (int i = 0; i < var5; ++i)
        {
            int x = chunkX + random.nextInt(16);
            int y = random.nextInt(28) + 4;
            int z = chunkZ + random.nextInt(16);

            Block block = world.getBlock(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(14))
            {
            	world.setBlock(x, y, z, BOPCBlocks.gemOre, 14, 2);
            }
        }

        for (int i = 0; i < 20; i++)
        {
            int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
            int z = chunkZ + random.nextInt(16) + 8;

            new WorldGenMoss().generate(world, random, x, y, z);
        }
    }

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		return 11981671;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 12311907;
	}
	
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 11061213;
        else return super.getSkyColorByTemp(par1);
    }
	
	public int getFogColour(int x, int y, int z)
	{
		return 13753294;
	}
	
    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.8F;
    }
}
