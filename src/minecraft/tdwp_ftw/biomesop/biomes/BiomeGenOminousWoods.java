package tdwp_ftw.biomesop.biomes;

import java.util.Random;
import java.awt.Color;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.worldgen.WorldGenDarkTree1;
import tdwp_ftw.biomesop.worldgen.WorldGenDarkTree2;
import tdwp_ftw.biomesop.worldgen.WorldGenWillow1;
import tdwp_ftw.biomesop.worldgen.WorldGenWillow2;
import tdwp_ftw.biomesop.worldgen.WorldGenOminous1;
import tdwp_ftw.biomesop.worldgen.WorldGenOminous2;

public class BiomeGenOminousWoods extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenOminousWoods(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 10;
        this.customBiomeDecorator.grassPerChunk = 1;
        this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.deathbloomsPerChunk = 1;
        this.customBiomeDecorator.mushroomsPerChunk = 8;
        this.customBiomeDecorator.reedsPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
        this.customBiomeDecorator.thornsPerChunk = 9;
        this.waterColorMultiplier = 1973030;
		this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 15, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 4));
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {	
		//return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenWillow2() : (par1Random.nextInt(7) == 0 ? new WorldGenDarkTree1() : (par1Random.nextInt(5) == 0 ? new WorldGenWillow1() : new WorldGenDarkTree2())));
		return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenOminous1(false) : new WorldGenOminous2());
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(6) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 0) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 4145489;
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 4145489;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (mod_BiomesOPlenty.skyColors = true)
		{
        return 5069168;
		}	
		else
		{
			par1 /= 3.0F;

			if (par1 < -1.0F)
			{
				par1 = -1.0F;
			}

			if (par1 > 1.0F)
			{
				par1 = 1.0F;
			}

			return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		}
    }
}
