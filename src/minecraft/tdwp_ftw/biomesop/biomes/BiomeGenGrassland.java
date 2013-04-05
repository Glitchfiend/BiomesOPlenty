package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenGrassland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenGrassland(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = 2;
		this.customBiomeDecorator.reedsPerChunk = 25;
		this.customBiomeDecorator.mushroomsPerChunk = 20;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.customBiomeDecorator.waterLakesPerChunk = 15;
		this.customBiomeDecorator.generatePumpkins = false;
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 14, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 12, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 12, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 10, 4, 4));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(BOPBlocks.mediumGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.shortGrass.blockID, 1);
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
                par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
            }
        }
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 8379261;
    }
}
