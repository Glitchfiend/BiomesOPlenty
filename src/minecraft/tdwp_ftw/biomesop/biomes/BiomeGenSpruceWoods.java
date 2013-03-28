package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.worldgen.WorldGenTaiga5;
import tdwp_ftw.biomesop.worldgen.WorldGenLarch1;
import tdwp_ftw.biomesop.worldgen.WorldGenLarch2;

public class BiomeGenSpruceWoods extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenSpruceWoods(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 10;
        this.customBiomeDecorator.grassPerChunk = 6;
		this.customBiomeDecorator.sproutsPerChunk = 3;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga5(false) : new WorldGenTaiga2(false));
    }
}
