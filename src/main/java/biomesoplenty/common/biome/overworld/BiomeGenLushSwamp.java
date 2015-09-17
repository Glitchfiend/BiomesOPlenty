package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;

public class BiomeGenLushSwamp extends BOPOverworldBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.1F);

    public BiomeGenLushSwamp(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(5746228);
        this.setTemperatureRainfall(0.7F, 1.0F);
        
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
        
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = 16;
		this.theBiomeDecorator.waterlilyPerChunk = 4;

		this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.cattailsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.highCattailsPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 5;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 3;
        this.theBiomeDecorator.bopFeatures.poisonLakesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.wildCarrotsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.koruPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 6;
        this.theBiomeDecorator.bopFeatures.cloverPatchesPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 4;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 4), 8);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 4);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPSwampTree(Blocks.log, Blocks.leaves, 0, 0, 8, 6, BOPCBlocks.ivy, -1);
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

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(10))
            {
                world.setBlock(x, y, z, BOPCBlocks.gemOre, 10, 2);
            }
        }
    }
}
