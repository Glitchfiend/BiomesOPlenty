package tdwp_ftw.biomesop.biomes;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenGlacier extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenGlacier(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)mod_BiomesOPlenty.hardIce.blockID;
        this.fillerBlock = (byte)mod_BiomesOPlenty.hardIce.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
    }
}
