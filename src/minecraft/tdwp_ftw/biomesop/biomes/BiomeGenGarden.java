package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.worldgen.WorldGenGiantFlowerRed;
import tdwp_ftw.biomesop.worldgen.WorldGenGiantFlowerYellow;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.mobs.EntityRosester;

public class BiomeGenGarden extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenGarden(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 1;
        this.customBiomeDecorator.flowersPerChunk = 20;
        this.customBiomeDecorator.whiteFlowersPerChunk = 25;
		this.customBiomeDecorator.tinyFlowersPerChunk = 15;
		this.customBiomeDecorator.sproutsPerChunk = 1;
        this.customBiomeDecorator.rosesPerChunk = 20;
        this.customBiomeDecorator.grassPerChunk = 25;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.customBiomeDecorator.bushesPerChunk = 10;
		this.customBiomeDecorator.generatePumpkins = false;
        this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityRosester.class, 10, 4, 4));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(mod_BiomesOPlenty.shortGrass.blockID, 1) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenGiantFlowerRed() : new WorldGenGiantFlowerYellow());
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if (var10 == Block.stone.blockID)
            {
                par1World.setBlockAndMetadataWithNotify(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
            }
        }
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 3785757;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 5364530;
    }
}
