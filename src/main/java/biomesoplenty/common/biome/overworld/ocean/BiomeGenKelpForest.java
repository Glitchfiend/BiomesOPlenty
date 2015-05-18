package biomesoplenty.common.biome.overworld.ocean;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPOceanBiome;
import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BiomeGenKelpForest extends BOPOceanBiome {
    private static final Height biomeHeight = new Height(-1.7F, 1.1F);

    public BiomeGenKelpForest(int biomeID) {
        super(biomeID);

        this.zoom = 0.25D;
        this.threshold = 0.25D;

        this.setHeight(biomeHeight);
        this.setColor(27468);
        this.setTemperatureRainfall(0.5F, 0.9F);

        this.theBiomeDecorator.bopFeatures.kelpPerChunk = 999;
        this.theBiomeDecorator.bopFeatures.kelpThickPerChunk = 999;
        this.theBiomeDecorator.bopFeatures.shortKelpPerChunk = 200;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 20;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6) {
            int x = chunkX + random.nextInt(16);
            int y = random.nextInt(28) + 4;
            int z = chunkZ + random.nextInt(16);

            Block block = world.getBlock(x, y, z);

            if (block != null && BOPConfigurationTerrainGen.generateGems && block.isReplaceableOreGen(world, x, y, z, Blocks.stone)) {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 12, 2);
            }
        }
    }
}
