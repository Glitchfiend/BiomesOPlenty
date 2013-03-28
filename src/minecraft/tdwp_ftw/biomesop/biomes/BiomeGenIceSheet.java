package tdwp_ftw.biomesop.biomes;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;

public class BiomeGenIceSheet extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenIceSheet(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.ice.blockID;
        this.fillerBlock = (byte)Block.ice.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
    }
}
