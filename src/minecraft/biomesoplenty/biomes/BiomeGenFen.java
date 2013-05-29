package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenDeadTree;
import biomesoplenty.worldgen.WorldGenFen1;
import biomesoplenty.worldgen.WorldGenFen2;
import biomesoplenty.worldgen.WorldGenMoss;

public class BiomeGenFen extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenFen(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 10;
        this.customBiomeDecorator.grassPerChunk = 15;
		this.customBiomeDecorator.highGrassPerChunk = 1;
		this.customBiomeDecorator.waterlilyPerChunk = 1;
		this.customBiomeDecorator.cattailsPerChunk = 1;
		this.customBiomeDecorator.highCattailsPerChunk = 1;
		this.customBiomeDecorator.pondsPerChunk = 99;
		this.customBiomeDecorator.toadstoolsPerChunk = 2;
		this.customBiomeDecorator.mushroomsPerChunk = 8;
		this.customBiomeDecorator.mudPerChunk = 1;
        this.customBiomeDecorator.mudPerChunk2 = 1;
		this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.customBiomeDecorator.reedsBOPPerChunk = 5;
		this.customBiomeDecorator.algaePerChunk = 1;
		this.customBiomeDecorator.portobellosPerChunk = 1;
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenFen2(false) : (par1Random.nextInt(20) == 0 ? new WorldGenDeadTree(false) : new WorldGenFen1()));
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenMoss var5 = new WorldGenMoss();

        for (int var6 = 0; var6 < 20; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 58;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
		return (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1)));
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 12240001;
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 13547897;
    }
}
