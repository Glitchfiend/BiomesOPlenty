package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenAcacia;
import biomesoplenty.worldgen.WorldGenDeadTree3;

public class BiomeGenLushDesert extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenLushDesert(int par1)
    {
        super(par1);
        this.topBlock = (byte)Blocks.redRock.get().blockID;
        this.fillerBlock = (byte)Blocks.redRock.get().blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 12;
        this.customBiomeDecorator.grassPerChunk = 8;
        this.customBiomeDecorator.oasesPerChunk = 999;
        this.customBiomeDecorator.oasesPerChunk2 = 999;
        this.customBiomeDecorator.deadBushPerChunk = 2;
        this.customBiomeDecorator.purpleFlowersPerChunk = 5;
        this.customBiomeDecorator.desertGrassPerChunk = 10;
		this.customBiomeDecorator.desertCactiPerChunk = 10;
        this.customBiomeDecorator.cactiPerChunk = 20;
		this.customBiomeDecorator.tinyCactiPerChunk = 5;
		this.customBiomeDecorator.waterLakesPerChunk = 5;
		this.customBiomeDecorator.aloePerChunk = 3;
        this.customBiomeDecorator.generateGrass = true;
        this.customBiomeDecorator.generateSand = true;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenAcacia(false) : (par1Random.nextInt(24) == 0 ? new WorldGenDeadTree3(false) : (par1Random.nextInt(2) == 0 ? this.worldGeneratorTrees : new WorldGenShrub(0,0))));
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = par2Random.nextInt(50);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(53) + 75;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if (var10 == Block.stone.blockID || var10 == Blocks.redRock.get().blockID)
            {
                par1World.setBlock(var7, var8, var9, Block.waterMoving.blockID, 0, 2);
            }
        }
    }
}
