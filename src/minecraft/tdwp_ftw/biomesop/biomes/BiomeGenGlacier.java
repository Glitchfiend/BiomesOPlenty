package tdwp_ftw.biomesop.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import tdwp_ftw.biomesop.configuration.BOPBlocks;

public class BiomeGenGlacier extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenGlacier(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)BOPBlocks.hardIce.blockID;
        this.fillerBlock = (byte)BOPBlocks.hardIce.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
    }
}
