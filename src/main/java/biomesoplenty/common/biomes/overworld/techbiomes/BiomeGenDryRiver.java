package biomesoplenty.common.biomes.overworld.techbiomes;

import biomesoplenty.api.BOPBlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenDryRiver extends BiomeGenBase
{
	private static final Height biomeHeight = new Height(-0.1F, 0.0F);

    public BiomeGenDryRiver(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        
        this.setHeight(biomeHeight);
        
        this.topBlock = BOPBlockHelper.get("driedDirt");
		this.fillerBlock = BOPBlockHelper.get("driedDirt");
    }
}