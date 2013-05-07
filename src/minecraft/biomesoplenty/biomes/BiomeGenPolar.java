package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.worldgen.WorldGenIceSheet;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenPolar extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenPolar(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.waterColorMultiplier = 3685739;
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenIceSheet var5 = new WorldGenIceSheet();

        for (int var6 = 0; var6 < 75; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 62;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }
}
