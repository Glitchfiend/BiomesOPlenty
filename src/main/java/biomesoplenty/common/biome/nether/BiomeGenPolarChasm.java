package biomesoplenty.common.biome.nether;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPNetherBiome;

public class BiomeGenPolarChasm extends BOPNetherBiome implements IBiomeFog
{
    public BiomeGenPolarChasm(int id)
    {
        super(id);

        this.setColor(11091006);

        this.topBlock = BOPCBlocks.hardIce;
		this.fillerBlock = BOPCBlocks.hardIce;

        this.theBiomeDecorator.bopFeatures.gravesPerChunk = 1;
    }
    
    @Override
    public int getFogColour(int x, int y, int z)
    {
        return 6916525;
    }

    public float getFogDensity(int x, int y, int z)
    {
        return 0.99F;
    }
}
