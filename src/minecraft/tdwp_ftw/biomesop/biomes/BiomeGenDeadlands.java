package tdwp_ftw.biomesop.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.declarations.BOPConfiguration;
import tdwp_ftw.biomesop.worldgen.WorldGenDeadTree3;
import tdwp_ftw.biomesop.worldgen.WorldGenDeadlands;

public class BiomeGenDeadlands extends BiomeGenBase
{
	private WorldGenerator theWorldGenerator;
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenDeadlands(int par1)
    {
        super(par1);
        this.topBlock = (byte)BOPBlocks.ash.blockID;
        this.fillerBlock = (byte)BOPBlocks.ash.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 1;
        this.customBiomeDecorator.grassPerChunk = 15;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.mushroomsPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.customBiomeDecorator.lavaLakesPerChunk = 25;
		this.customBiomeDecorator.generatePits = true;
		this.customBiomeDecorator.generateSmolderingGrass = true;
        this.waterColorMultiplier = 16711680;
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCreeper.class, 30, 1, 7));
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
		this.theWorldGenerator = new WorldGenMinable(Block.silverfish.blockID, 8);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenDeadTree3(false);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenDeadlands();
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;

        for (var5 = 0; var5 < 7; ++var5)
        {
            var6 = par3 + par2Random.nextInt(16);
            var7 = par2Random.nextInt(64);
            var8 = par4 + par2Random.nextInt(16);
            this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 4464929;
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
