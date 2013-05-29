package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class BiomeGenTaigaNew extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenTaigaNew(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 10;
        this.customBiomeDecorator.grassPerChunk = 1;
		this.customBiomeDecorator.violetsPerChunk = 1;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
        //return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenNorwaySpruce1() : new WorldGenNorwaySpruce2());
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
    }
}
