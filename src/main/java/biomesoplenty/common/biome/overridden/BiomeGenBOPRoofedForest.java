package biomesoplenty.common.biome.overridden;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;

import java.util.Random;

public class BiomeGenBOPRoofedForest extends BOPInheritedOverworldBiome {
    protected static final WorldGenForest shortOakTrees = new WorldGenForest(false, false);
    protected static final WorldGenCanopyTree canopyTrees = new WorldGenCanopyTree(false);

    public BiomeGenBOPRoofedForest(int biomeID, BiomeGenBase inheritedBiome) {
        super(biomeID, inheritedBiome);

        this.theBiomeDecorator.treesPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.25D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.25D);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(3) > 0 ? canopyTrees : (random.nextInt(5) != 0 ? this.worldGeneratorTrees : shortOakTrees));
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        int k;
        int l;
        int i1;
        int j1;
        int k1;

        for (k = 0; k < 4; ++k) {
            for (l = 0; l < 4; ++l) {
                i1 = chunkX + k * 4 + 1 + 8 + random.nextInt(3);
                j1 = chunkZ + l * 4 + 1 + 8 + random.nextInt(3);
                k1 = world.getHeightValue(i1, j1);

                if (random.nextInt(20) == 0) {
                    WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
                    worldgenbigmushroom.generate(world, random, i1, k1, j1);
                } else {
                    WorldGenAbstractTree worldgenabstracttree = this.func_150567_a(random);
                    worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

                    if (worldgenabstracttree.generate(world, random, i1, k1, j1)) {
                        worldgenabstracttree.func_150524_b(world, random, i1, k1, j1);
                    }
                }
            }
        }

        k = random.nextInt(5) - 3;

        l = 0;

        while (l < k) {
            i1 = random.nextInt(3);

            if (i1 == 0) {
                genTallFlowers.func_150548_a(1);
            } else if (i1 == 1) {
                genTallFlowers.func_150548_a(4);
            } else if (i1 == 2) {
                genTallFlowers.func_150548_a(5);
            }

            j1 = 0;

            while (true) {
                if (j1 < 5) {
                    k1 = chunkX + random.nextInt(16) + 8;
                    int i2 = chunkZ + random.nextInt(16) + 8;
                    int l1 = random.nextInt(world.getHeightValue(k1, i2) + 32);

                    if (!genTallFlowers.generate(world, random, k1, l1, i2)) {
                        ++j1;
                        continue;
                    }
                }

                ++l;
                break;
            }
        }

        super.decorate(world, random, chunkX, chunkZ);
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        int l = super.getBiomeGrassColor(x, y, z);
        return (l & 16711422) + 2634762 >> 1;
    }
}