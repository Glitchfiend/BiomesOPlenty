package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree;

public class BiomeGenSilkglades extends BOPSubBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

    public BiomeGenSilkglades(int id)
    {
        super(id);
        
        this.zoom = 0.25D;
		this.threshold = 0.25D;
        
        this.setHeight(biomeHeight);
        this.setColor(13420973);
        this.setTemperatureRainfall(0.5F, 0.9F);

        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntitySpider.class, 7, 1, 2));

        this.waterColorMultiplier = 16777079;

        this.theBiomeDecorator.treesPerChunk = 6;
        this.theBiomeDecorator.grassPerChunk = 2;
        this.theBiomeDecorator.mushroomsPerChunk = 4;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;

        this.theBiomeDecorator.bopFeatures.sproutsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.poisonIvyPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.cobwebsPerChunk = 9;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.cobwebNestsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 2;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 1D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenBOPSwampTree(Blocks.log, BOPCBlocks.leaves2, 0, 0, 6, 9, BOPCBlocks.leaves2, 0) : 
            (random.nextInt(7) == 0 ? new WorldGenDeadTree() : 
                new WorldGenBOPSwampTree(BOPCBlocks.logs3, BOPCBlocks.colorizedLeaves2, 1, 0, 6, 9, BOPCBlocks.colorizedLeaves2, 0));
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
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return 13420973;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 14146486;
    }


    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 13553096;
        else return super.getSkyColorByTemp(par1);
    }

    /*
	@Override
	public int getFogColour()
	{
		return 10062450;
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.8F;
    }
     */
}
