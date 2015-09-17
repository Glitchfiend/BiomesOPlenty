package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBayou1;
import biomesoplenty.common.world.features.trees.WorldGenBayou2;
import biomesoplenty.common.world.features.trees.WorldGenBayou3;

public class BiomeGenBayou extends BOPOverworldBiome implements IBiomeFog
{
    private static final Height biomeHeight = new Height(-0.1F, 0.1F);

	public BiomeGenBayou(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(9154411);
        this.setTemperatureRainfall(0.8F, 0.9F);

		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

		this.waterColorMultiplier = 16767282;

		this.theBiomeDecorator.treesPerChunk = 15;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.reedsPerChunk = 25;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.waterlilyPerChunk = 4;

		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.waterLakesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.mudPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.cattailsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.highCattailsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 15;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}

	@Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(8) == 0 ? new WorldGenBayou3(BOPCBlocks.logs3, 1) : 
		(random.nextInt(2) == 0 ? new WorldGenBayou1(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves2, 1, 0) : 
		new WorldGenBayou2(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves2, 1, 0));
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(10))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 10, 2);
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
		return 9154411;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 11591816;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 11322556;
		else return super.getSkyColorByTemp(par1);
	}

	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 9482133;
	}

    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.6F;
    }
}
