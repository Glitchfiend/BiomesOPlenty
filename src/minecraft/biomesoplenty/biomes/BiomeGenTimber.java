package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.worldgen.WorldGenChaparral2;
import biomesoplenty.worldgen.WorldGenChaparral3;
import biomesoplenty.worldgen.WorldGenDeciduous2;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTimber extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenTimber(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 20;
        this.customBiomeDecorator.grassPerChunk = 8;
		this.customBiomeDecorator.thornsPerChunk = 2;
		this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.toadstoolsPerChunk = 2;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenChaparral3() : (par1Random.nextInt(8) == 0 ? new WorldGenChaparral2() : new WorldGenDeciduous2(false)));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(5) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 10923366;
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 11049817;
    }
}
