package tdwp_ftw.biomesop.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenHighland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenHighland(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
		this.customBiomeDecorator.highGrassPerChunk = 25;
        this.customBiomeDecorator.grassPerChunk = 25;
		this.customBiomeDecorator.potatoesPerChunk = -999;
		this.customBiomeDecorator.generateBoulders = true;
    }
}
