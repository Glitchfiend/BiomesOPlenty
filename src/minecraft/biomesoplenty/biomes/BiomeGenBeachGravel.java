package biomesoplenty.biomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBeachGravel extends BiomeGenBase
{
    public BiomeGenBeachGravel(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.gravel.blockID;
        this.fillerBlock = (byte)Block.gravel.blockID;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.cactiPerChunk = -999;
    }
}
