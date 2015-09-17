package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityMooshroom;
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
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenIvy;
import biomesoplenty.common.world.features.trees.WorldGenBOPJungle;

public class BiomeGenFungiForest extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);
	
	public BiomeGenFungiForest(int biomeID)
	{
		super(biomeID);
		
        this.setHeight(biomeHeight);
        this.setColor(15792496);
        this.setTemperatureRainfall(0.9F, 1.0F);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 3, 4, 8));

		this.waterColorMultiplier = 65326;
		
		this.theBiomeDecorator.treesPerChunk = 12;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.bigMushroomsPerChunk = 9;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 4;
		this.theBiomeDecorator.bopFeatures.bushesPerChunk = 7;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.portobellosPerChunk = 7;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.glowshroomsPerChunk = 1;
		this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.generateMycelium = true;
		
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 25;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 3;
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 1), 8);
        
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 2D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.25D);
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

		for (int i = 0; i < 50; i++)
		{
			int x = chunkX + random.nextInt(16) + 8;
            short y = 128;
			int z = chunkZ + random.nextInt(16) + 8;

			new WorldGenIvy().generate(world, random, x, y, z);
		}
	}

	 @Override
	 public WorldGenAbstractTree func_150567_a(Random random)
	 {
		 return random.nextInt(2) == 0 ? new WorldGenBOPJungle(false, 5, 20, 0, 0) : new WorldGenShrub(0, 0);
	 }

	 @Override
	 public int getBiomeGrassColor(int x, int y, int z)
	 {
		 return 5762404;
	 }

	 @Override
	 public int getBiomeFoliageColor(int x, int y, int z)
	 {
		 return 5762404;
	 }

	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors) return 7587486;
		 else return super.getSkyColorByTemp(par1);
	 }
	 
	 @Override
	    public int getFogColour(int x, int y, int z)
	    {
	        return 8043468;
	    }

    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.6F;
    }
}
