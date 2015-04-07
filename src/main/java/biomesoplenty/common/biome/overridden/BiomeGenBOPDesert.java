package biomesoplenty.common.biome.overridden;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;

public class BiomeGenBOPDesert extends BOPInheritedOverworldBiome implements IBiomeFog
{
	public BiomeGenBOPDesert(int biomeID, BiomeGenBase inheritedBiome)
	{
		super(biomeID, inheritedBiome);
		
        this.theBiomeDecorator.bopFeatures.tinyCactiPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.generateQuicksand = true;
	}
	
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 9359789;
		else return super.getSkyColorByTemp(par1);
	}
	

	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 13877903;
	}

    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.3F;
    }
}
