package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga3;
import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;
// BOPConfigurationTerrainGen.heightrootmod - BOPConfigurationTerrainGen.heightVarMod

public class BiomeGenFungiForest extends BOPOverworldBiome
{
    //private static final Height biomeHeight = new Height(0.2F, 0.5F);
	private static final Height biomeHeight = new Height(0.2F*BOPConfigurationTerrainGen.heightrootmod, 0.5F*BOPConfigurationTerrainGen.heightVarMod);
	
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
		
		this.theBiomeDecorator.treesPerChunk = 8;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.bigMushroomsPerChunk = 8;
		
        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 2;
		this.theBiomeDecorator.bopFeatures.bushesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.portobellosPerChunk = 7;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.glowshroomsPerChunk = 1;
		this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.generateMycelium = true;
		
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 12;
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
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
	 //TODO:                     getRandomWorldGenForTrees()
	 public WorldGenAbstractTree func_150567_a(Random random)
	 {
		 return random.nextInt(3) == 0 ? new WorldGenBOPTaiga3(Blocks.log, BOPCBlocks.leaves2, 0, 3, false, 25, 10, 1) : 
			 ((random.nextInt(5) == 0 ? new WorldGenBOPTaiga3(Blocks.log, BOPCBlocks.leaves1, 0, 0, false, 15, 15, 2) : 
				 (random.nextInt(2) == 0 ? new WorldGenShrub(0, 0) : 
			 new WorldGenBOPTaiga3(Blocks.log, Blocks.leaves, 0, 0, false, 35, 10, 0))));
	 }

	 @Override
	 public int getBiomeGrassColor(int x, int y, int z)
	 {
		 return 15792496;
	 }

	 @Override
	 public int getBiomeFoliageColor(int x, int y, int z)
	 {
		 return 11139946;
	 }

	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors) return 11513806;
		 else return super.getSkyColorByTemp(par1);
	 }
}
