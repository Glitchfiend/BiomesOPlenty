package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBogBush;
import biomesoplenty.common.world.features.trees.WorldGenCypress;

public class BiomeGenBog extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);
    
    public BiomeGenBog(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(14193503);
        this.setTemperatureRainfall(0.8F, 0.9F);

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

        this.theBiomeDecorator.treesPerChunk = 12;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;

        this.theBiomeDecorator.bopFeatures.bushesPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.mudPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 3;
        this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 8;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waterLakesPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.poisonLakesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
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

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 10, 2);
            }
        }
	}

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? new WorldGenCypress(Blocks.log2, Blocks.leaves2, 1, 1, false, 8, 5, 0) : (random.nextInt(6) == 0 ? new WorldGenCypress(Blocks.log, Blocks.leaves, 2, 2, false, 4, 4, 1) : new WorldGenBogBush());
    }

    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 14193503;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 14345593;
    }
}
