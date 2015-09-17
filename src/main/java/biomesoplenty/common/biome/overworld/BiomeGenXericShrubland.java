package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenXericShrubland extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.05F, 0.1F);

	public BiomeGenXericShrubland(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setColor(14863781);
        this.setTemperatureRainfall(1.0F, 0.2F);

		this.spawnableCreatureList.clear();
		
		this.topBlock = Blocks.sand;
		this.fillerBlock = Blocks.sand;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = 2;
	    this.theBiomeDecorator.deadBushPerChunk = 7;
	    this.theBiomeDecorator.cactiPerChunk = 12;

	    this.theBiomeDecorator.bopFeatures.desertGrassPerChunk = 7;
	    this.theBiomeDecorator.bopFeatures.desertSproutsPerChunk = 9;
	    this.theBiomeDecorator.bopFeatures.bromeliadsPerChunk = 7;
	    this.theBiomeDecorator.bopFeatures.grassSplatterPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.xericSplatterPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.tinyCactiPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.bushesPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 20;
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 7), 8);
        
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
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

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(2))
            {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 2, 2);
            }
        }
    }
    
    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 14863781;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 14863781;
	}
}
