package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;

public class BiomeGenVolcano extends BOPSubBiome
{
    private static final Height biomeHeight = new Height(2.5F, 0.5F);

    public BiomeGenVolcano(int id)
    {
        super(id);
        
        this.zoom = 0.01D;
		this.threshold = 0.5D;
        
        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(6645093);
        this.setTemperatureRainfall(2.0F, 0.05F);

        this.spawnableCreatureList.clear();

        this.topBlock = BOPCBlocks.ashStone;
        this.fillerBlock = BOPCBlocks.ashStone;
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.lavaLakesPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.lavaSpoutsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.generateAsh = true;
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

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(12))
            {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 12, 2);
            }
        }
    }

    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 8026746;
        else return super.getSkyColorByTemp(par1);
    }
}
