package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;
// BOPConfigurationTerrainGen.heightrootmod - BOPConfigurationTerrainGen.heightVarMod

public class BiomeGenAlps extends BOPOverworldBiome
{
	//private static final Height biomeHeight = new Height(8.0F, 0.025F);
	private static final Height biomeHeight = new Height(8.0F*BOPConfigurationTerrainGen.heightrootmod, 0.025F*BOPConfigurationTerrainGen.heightVarMod);
	
	public BiomeGenAlps(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(13421772);
        this.setEnableSnow();
        this.setTemperatureRainfall(0.0F, 0.5F);
		
		this.topBlock = Blocks.snow;
		this.fillerBlock = Blocks.snow;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
	}
	
	@Override
    public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
        super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int x = chunkX + random.nextInt(16);
            int y = random.nextInt(28) + 4;
            int z = chunkZ + random.nextInt(16);
            
            Block block = world.getBlock(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                world.setBlock(x, y, z, Blocks.emerald_ore, 0, 2);
            }
        }
    }
}
