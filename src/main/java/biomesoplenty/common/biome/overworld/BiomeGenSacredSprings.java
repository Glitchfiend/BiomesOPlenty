package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenSacredOak;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenSacredSprings extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(0.0F, 0.6F);
	
    public BiomeGenSacredSprings(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(39259);
        
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        
        this.theBiomeDecorator.treesPerChunk = 30;
        this.theBiomeDecorator.grassPerChunk = 4;
        this.theBiomeDecorator.waterlilyPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 2;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 6), 10);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(5, 5), 5);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand)
    {
    	return (WorldGenAbstractTree)(rand.nextInt(450) == 0 ? new WorldGenSacredOak(false) : new WorldGenShrub(0, 0));
    }
    
    @Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
    {
        return random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPCBlocks.foliage, 10) : (random.nextInt(4) == 0 ? new WorldGenTallGrass(BOPCBlocks.foliage, 11) : new WorldGenTallGrass(Blocks.tallgrass, 1));
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(6))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 6, 2);
			}
		}
	}
    
	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		double d0 = plantNoise.func_151601_a((double)p_150558_1_ * 0.0225D, (double)p_150558_3_ * 0.0225D);
        return d0 < -0.1D ? 39285 : 39259;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z)
    {
    	double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z* 0.0225D);
        return d0 < -0.1D ? 39285 : 39259;
    }
}
