package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.mobs.EntityJungleSpider;
import biomesoplenty.worldgen.WorldGenRainforestTree1;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenRainforest extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenRainforest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 14;
        this.customBiomeDecorator.grassPerChunk = 25;
		this.customBiomeDecorator.pinkFlowersPerChunk = 2;
        this.customBiomeDecorator.flowersPerChunk = 25;
		this.customBiomeDecorator.rosesPerChunk = 10;
		this.customBiomeDecorator.mushroomsPerChunk = 25;
		this.customBiomeDecorator.orangeFlowersPerChunk = 6;
		this.customBiomeDecorator.generatePumpkins = false;
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(15) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(5) == 0 ? this.worldGeneratorBigTree : new WorldGenRainforestTree1(false)));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 1759340;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 1368687;
    }
}
