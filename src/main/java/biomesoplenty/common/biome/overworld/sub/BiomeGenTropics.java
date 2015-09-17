package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenPalmTree1;
import biomesoplenty.common.world.features.trees.WorldGenTropicsShrub;

public class BiomeGenTropics extends BOPSubBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.3F);

    public BiomeGenTropics(int id)
    {
        super(id);
        
        this.zoom = 0.01D;
		this.threshold = 0.5D;
        
        this.setHeight(biomeHeight);
        this.setColor(2211330);
        this.setTemperatureRainfall(1.2F, 1.0F);
        
        this.spawnableCreatureList.clear();
        
        this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));

        this.theBiomeDecorator.treesPerChunk = 12;
        this.theBiomeDecorator.grassPerChunk = 7;
        this.theBiomeDecorator.flowersPerChunk = 10;
        this.theBiomeDecorator.sandPerChunk = 50;
        this.theBiomeDecorator.sandPerChunk2 = 50;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 30;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 7;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 8);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 5), 10);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers2, 0), 15);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 7);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(4, 5), 6);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(0, 3), 2);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(3), 0.25D);
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(2) == 0 ? new WorldGenPalmTree1() : 
        (random.nextInt(2) == 0 ? new WorldGenTropicsShrub() : 
        new WorldGenShrub(0, 0));
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
        if (BOPConfigurationMisc.skyColors) return 507391;
        else return super.getSkyColorByTemp(par1);
    }

    /**
     * Fog Color
     */
    /*
    @Override
    public int getFogColour()
    {
        return 7724287;
    }
    */

    /*@Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
    */
}
