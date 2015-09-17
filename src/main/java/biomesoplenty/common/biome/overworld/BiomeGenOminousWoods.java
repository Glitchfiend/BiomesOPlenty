package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree;

public class BiomeGenOminousWoods extends BOPOverworldBiome implements IBiomeFog
{
	private static final Height biomeHeight = new Height(0.1F, 0.2F);
	
	public BiomeGenOminousWoods(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(4145489);
        this.setTemperatureRainfall(0.5F, 0.9F);
		
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 5, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 4));
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
		
		this.waterColorMultiplier = 1973030;
		
		this.theBiomeDecorator.treesPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

        this.theBiomeDecorator.bopFeatures.thornsPerChunk = 9;
        this.theBiomeDecorator.bopFeatures.poisonIvyPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.poisonLakesPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 2), 20);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 0), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(2) == 0 ? new WorldGenBOPTaiga2(BOPCBlocks.logs1, BOPCBlocks.leaves1, 2, 3, false, 14, 6, 0, -1) : (random.nextInt(6) == 0 ? new WorldGenDeadTree() : new WorldGenBOPSwampTree(BOPCBlocks.logs1, BOPCBlocks.leaves1, 2, 3, 5, 4, BOPCBlocks.treeMoss, -1));
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
    public void genTerrainBlocks(World world, Random random, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.topBlock = BOPCBlocks.newBopGrass;
        this.field_150604_aj = 0;
        this.fillerBlock = BOPCBlocks.newBopDirt;

        this.genBiomeTerrain(world, random, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 4145489;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 4145489;
	}
	
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 3420989;
		else return super.getSkyColorByTemp(par1);
	}
	

	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 3420989;
	}

    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.1f;
    }
}
