/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkProviderGenerateBOPHell implements IChunkGenerator
{
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState NETHERRACK = Blocks.NETHERRACK.getDefaultState();
    protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    protected static final IBlockState LAVA = Blocks.LAVA.getDefaultState();
    protected static final IBlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
    protected static final IBlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();
    private final World world;
    private final boolean generateStructures;
    private final Random rand;
    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] depthBuffer = new double[256];
    private double[] noiseArray;
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    private NoiseGeneratorPerlin stoneNoiseGen;

    private final WorldGenFire fireFeature = new WorldGenFire();
    private final WorldGenGlowStone1 lightGemGen = new WorldGenGlowStone1();
    private final WorldGenGlowStone2 hellPortalGen = new WorldGenGlowStone2();
    private final WorldGenerator quartzGen = new WorldGenMinable(Blocks.QUARTZ_ORE.getDefaultState(), 14, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenerator magmaGen = new WorldGenMinable(Blocks.MAGMA.getDefaultState(), 33, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenHellLava lavaTrapGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, true);
    private final WorldGenHellLava hellSpringGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, false);
    private final WorldGenBush brownMushroomFeature = new WorldGenBush(Blocks.BROWN_MUSHROOM);
    private final WorldGenBush redMushroomFeature = new WorldGenBush(Blocks.RED_MUSHROOM);

    private MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
    private MapGenBase genNetherCaves = new MapGenCavesHell();
    private double[] xyzBalanceNoiseArray;
    private double[] xyzNoiseArrayA;
    private double[] xyzNoiseArrayB;
    private double[] noiseData4;
    private double[] depthRegion;
    private double[] stoneNoiseArray;

    public ChunkProviderGenerateBOPHell(World worldIn, boolean p_i45637_2_, long seed)
    {
        this.world = worldIn;
        this.generateStructures = p_i45637_2_;
        this.rand = new Random(seed);
        this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.stoneNoiseGen = new NoiseGeneratorPerlin(this.rand, 4);
        worldIn.setSeaLevel(63);

        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextHell ctx =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextHell(lperlinNoise1, lperlinNoise2, perlinNoise1, slowsandGravelNoiseGen, netherrackExculsivityNoiseGen, scaleNoise, depthNoise);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        this.lperlinNoise1 = ctx.getLPerlin1();
        this.lperlinNoise2 = ctx.getLPerlin2();
        this.perlinNoise1 = ctx.getPerlin();
        this.slowsandGravelNoiseGen = ctx.getPerlin2();
        this.netherrackExculsivityNoiseGen = ctx.getPerlin3();
        this.scaleNoise = ctx.getScale();
        this.depthNoise = ctx.getDepth();

        this.stoneNoiseArray = new double[256];

        this.genNetherBridge = (MapGenNetherBridge)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(genNetherBridge, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE);
        this.genNetherCaves = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(genNetherCaves, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE);
    }

    public void setChunkLavaNetherrack(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        int seaLevel = this.world.getSeaLevel() / 2 + 1;
        this.noiseArray = this.getHeights(this.noiseArray, chunkX * 4, 0, chunkZ * 4, 5, 17, 5);

        double oneEighth = 0.125D;
        double oneQuarter = 0.25D;

        // entire chunk is 16x128x16
        // process chunk in subchunks, each one 4x8x4 blocks in size
        // 4 subchunks in x direction, each 4 blocks long
        // 16 subchunks in y direction, each 8 blocks long
        // 4 subchunks in z direction, each 4 blocks long
        // for a total of 256 subchunks

        // divide chunk into 4 subchunks in x direction, index as ix
        for (int ix = 0; ix < 4; ++ix)
        {
            int k_x0 = ix * 5;
            int k_x1 = (ix + 1) * 5;

            // divide chunk into 4 subchunks in z direction, index as iz
            for (int iz = 0; iz < 4; ++iz)
            {
                int k_x0z0 = (k_x0 + iz) * 17;
                int k_x0z1 = (k_x0 + iz + 1) * 17;
                int k_x1z0 = (k_x1 + iz) * 17;
                int k_x1z1 = (k_x1 + iz + 1) * 17;

                // divide chunk into 16 subchunks in y direction, index as iy
                for (int iy = 0; iy < 16; ++iy)
                {
                    // get the noise values from the noise array
                    // these are the values at the corners of the subchunk
                    double n_x0y0z0 = this.noiseArray[k_x0z0 + iy];
                    double n_x0y0z1 = this.noiseArray[k_x0z1 + iy];
                    double n_x1y0z0 = this.noiseArray[k_x1z0 + iy];
                    double n_x1y0z1 = this.noiseArray[k_x1z1 + iy];
                    double n_x0y1z0 = this.noiseArray[k_x0z0 + iy + 1];
                    double n_x0y1z1 = this.noiseArray[k_x0z1 + iy + 1];
                    double n_x1y1z0 = this.noiseArray[k_x1z0 + iy + 1];
                    double n_x1y1z1 = this.noiseArray[k_x1z1 + iy + 1];

                    // linearly interpolate between the noise points to get a noise value for each block in the subchunk

                    double noiseStepY00 = (n_x0y1z0 - n_x0y0z0) * oneEighth;
                    double noiseStepY01 = (n_x0y1z1 - n_x0y0z1) * oneEighth;
                    double noiseStepY10 = (n_x1y1z0 - n_x1y0z0) * oneEighth;
                    double noiseStepY11 = (n_x1y1z1 - n_x1y0z1) * oneEighth;

                    double noiseStartX0 = n_x0y0z0;
                    double noiseStartX1 = n_x0y0z1;
                    double noiseEndX0 = n_x1y0z0;
                    double noiseEndX1 = n_x1y0z1;

                    // subchunk is 8 blocks high in y direction, index as jy
                    for (int jy = 0; jy < 8; ++jy)
                    {
                        double noiseStartZ = noiseStartX0;
                        double noiseEndZ = noiseStartX1;

                        double noiseStepX0 = (noiseEndX0 - noiseStartX0) * oneQuarter;
                        double noiseStepX1 = (noiseEndX1 - noiseStartX1) * oneQuarter;

                        // subchunk is 4 blocks long in x direction, index as jx
                        for (int jx = 0; jx < 4; ++jx)
                        {
                            double noiseStepZ = (noiseEndZ - noiseStartZ) * oneQuarter;
                            double noiseVal = noiseStartZ;

                            // subchunk is 4 blocks long in x direction, index as jz
                            for (int jz = 0; jz < 4; ++jz)
                            {
                                // If the noise value is above zero, this block starts as netherrack
                                // Otherwise it's 'empty' - air above sealevel and lava below it
                                if (noiseVal > 0.0D)
                                {
                                    primer.setBlockState(ix * 4 + jx, iy * 8 + jy, iz * 4 + jz, NETHERRACK);
                                }
                                else if (iy * 8 + jy < seaLevel)
                                {
                                    primer.setBlockState(ix * 4 + jx, iy * 8 + jy, iz * 4 + jz, LAVA);
                                }
                                noiseVal += noiseStepZ;
                            }

                            noiseStartZ += noiseStepX0;
                            noiseEndZ += noiseStepX1;
                        }

                        noiseStartX0 += noiseStepY00;
                        noiseStartX1 += noiseStepY01;
                        noiseEndX0 += noiseStepY10;
                        noiseEndX1 += noiseStepY11;
                    }
                }
            }
        }
    }

    // Biomes add their top blocks and filler blocks to the primer here
    public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomes)
    {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.world)) return;

        double d0 = 0.03125D;
        this.stoneNoiseArray = this.stoneNoiseGen.getRegion(this.stoneNoiseArray, (double)(chunkX * 16), (double)(chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int localX = 0; localX < 16; ++localX)
        {
            for (int localZ = 0; localZ < 16; ++localZ)
            {
                Biome biome = biomes[localZ + localX * 16];
                biome.genTerrainBlocks(this.world, this.rand, primer, chunkX * 16 + localX, chunkZ * 16 + localZ, this.stoneNoiseArray[localZ + localX * 16]);
            }
        }
    }

    public void buildSurfaces(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.world)) return;
        int i = this.world.getSeaLevel() + 1;
        double d0 = 0.03125D;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, 0.03125D, 0.03125D, 1.0D);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, chunkX * 16, 109, chunkZ * 16, 16, 1, 16, 0.03125D, 1.0D, 0.03125D);
        this.depthBuffer = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.depthBuffer, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, 0.0625D, 0.0625D, 0.0625D);

        for (int ix = 0; ix < 16; ++ix)
        {
            for (int iz = 0; iz < 16; ++iz)
            {
                boolean flag = this.slowsandNoise[ix + iz * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[ix + iz * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
                int l = (int)(this.depthBuffer[ix + iz * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int i1 = -1;
                IBlockState iblockstate = NETHERRACK;
                IBlockState iblockstate1 = NETHERRACK;

                for (int iy = 127; iy >= 0; --iy)
                {
                    if (iy < 127 - this.rand.nextInt(5) && iy > this.rand.nextInt(5))
                    {
                        IBlockState iblockstate2 = primer.getBlockState(iz, iy, ix);

                        if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != Material.AIR)
                        {
                            if (iblockstate2.getBlock() == Blocks.NETHERRACK)
                            {
                                if (i1 == -1)
                                {
                                    if (l <= 0)
                                    {
                                        iblockstate = AIR;
                                        iblockstate1 = NETHERRACK;
                                    }
                                    else if (iy >= i - 4 && iy <= i + 1)
                                    {
                                        iblockstate = NETHERRACK;
                                        iblockstate1 = NETHERRACK;

                                        if (flag1)
                                        {
                                            iblockstate = GRAVEL;
                                            iblockstate1 = NETHERRACK;
                                        }

                                        if (flag)
                                        {
                                            iblockstate = SOUL_SAND;
                                            iblockstate1 = SOUL_SAND;
                                        }
                                    }

                                    if (iy < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                                    {
                                        iblockstate = LAVA;
                                    }

                                    i1 = l;

                                    if (iy >= i - 1)
                                    {
                                        primer.setBlockState(iz, iy, ix, iblockstate);
                                    }
                                    else
                                    {
                                        primer.setBlockState(iz, iy, ix, iblockstate1);
                                    }
                                }
                                else if (i1 > 0)
                                {
                                    --i1;
                                    primer.setBlockState(iz, iy, ix, iblockstate1);
                                }
                            }
                        }
                        else
                        {
                            i1 = -1;
                        }
                    }
                    else
                    {
                        primer.setBlockState(iz, iy, ix, BEDROCK);
                    }
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        // initialize the random generator using the chunk coordinates
        this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);

        // create the primer
        ChunkPrimer chunkprimer = new ChunkPrimer();

        // start off by adding the basic terrain shape with air netherrack and lava blocks
        this.setChunkLavaNetherrack(chunkX, chunkZ, chunkprimer);

        this.buildSurfaces(chunkX, chunkZ, chunkprimer);
        this.genNetherCaves.generate(this.world, chunkX, chunkZ, chunkprimer);

        if (this.generateStructures)
        {
            this.genNetherBridge.generate(this.world, chunkX, chunkZ, chunkprimer);
        }

        Biome[] biomes = this.world.getBiomeProvider().getBiomes((Biome[])null, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBlocksForBiome(chunkX, chunkZ, chunkprimer, biomes);

        Chunk chunk = new Chunk(this.world, chunkprimer, chunkX, chunkZ);
        byte[] chunkBiomes = chunk.getBiomeArray();

        for (int i = 0; i < chunkBiomes.length; ++i)
        {
            chunkBiomes[i] = (byte)Biome.getIdForBiome(biomes[i]);
        }

        chunk.resetRelightChecks();
        return chunk;
    }

    // by default, sizeX = 5, sizeY = 17 and sizeZ = 5
    private double[] getHeights(double[] noiseArray, int subchunkX, int subchunkY, int subchunkZ, int sizeX, int sizeY, int sizeZ)
    {
        if (noiseArray == null)
        {
            noiseArray = new double[sizeX * sizeY * sizeZ];
        }

        ChunkGeneratorEvent.InitNoiseField event = new ChunkGeneratorEvent.InitNoiseField(this, noiseArray, subchunkX, subchunkY, subchunkZ, sizeX, sizeY, sizeZ);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return event.getNoisefield();

        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, subchunkX, subchunkY, subchunkZ, sizeX, 1, sizeZ, 100.0D, 0.0D, 100.0D);

        // terrain settings
        double coordinateScale = 684.412;
        double heightScale = 2053.236D;
        double mainNoiseScaleX = 80.0;
        double mainNoiseScaleY = 60.0;
        double mainNoiseScaleZ = 80.0;

        // generate the xyz noise for the chunk
        this.xyzBalanceNoiseArray = this.perlinNoise1.generateNoiseOctaves(this.xyzBalanceNoiseArray, subchunkX, subchunkY, subchunkZ, sizeX, sizeY, sizeZ, coordinateScale / mainNoiseScaleX, heightScale / mainNoiseScaleY, coordinateScale / mainNoiseScaleZ);
        this.xyzNoiseArrayA = this.lperlinNoise1.generateNoiseOctaves(this.xyzNoiseArrayA, subchunkX, subchunkY, subchunkZ, sizeX, sizeY, sizeZ, coordinateScale, heightScale, coordinateScale);
        this.xyzNoiseArrayB = this.lperlinNoise2.generateNoiseOctaves(this.xyzNoiseArrayB, subchunkX, subchunkY, subchunkZ, sizeX, sizeY, sizeZ, coordinateScale, heightScale, coordinateScale);

        // loop over the subchunks and calculate the overall noise value
        int xyzCounter = 0;
        // adjustments made to the noise based on the y position of the subchunk
        double[] yBalanceArray = new double[sizeY];

        // generate noise adjustment values based on the y position
        for (int iy = 0; iy < sizeY; ++iy)
        {
            // slightly bias towards a platform in the centre of the y axis with air above and below
            yBalanceArray[iy] = Math.cos((double)iy * Math.PI * 6.0D / (double)sizeY) * 2.0D;
            double jy = (double)iy;

            // jy should reach a max of 8 at the centre subchunk, and decreases above and below that
            if (iy > sizeY / 2)
            {
                jy = (double)(sizeY - 1 - iy);
            }

            if (jy < 4.0D)
            {
                jy = 4.0D - jy;

                // drastically increase netherrack at the roof and floor of the nether
                yBalanceArray[iy] -= jy * jy * jy * 10.0D;
            }
        }

        // loop over the subchunks and calculate the overall noise value
        for (int ix = 0; ix < sizeX; ++ix)
        {
            for (int iz = 0; iz < sizeZ; ++iz)
            {
                for (int iy = 0; iy < sizeY; ++iy)
                {
                    double yBalance = yBalanceArray[iy];

                    // calculate the xzy noise value
                    double xyzNoiseA = this.xyzNoiseArrayA[xyzCounter] / 512.0D;
                    double xyzNoiseB = this.xyzNoiseArrayB[xyzCounter] / 512.0D;
                    double balance = (this.xyzBalanceNoiseArray[xyzCounter] / 10.0D + 1.0D) / 2.0D;
                    double xyzNoiseValue;

                    if (balance < 0.0D)
                    {
                        xyzNoiseValue = xyzNoiseA;
                    }
                    else if (balance > 1.0D)
                    {
                        xyzNoiseValue = xyzNoiseB;
                    }
                    else
                    {
                        xyzNoiseValue = xyzNoiseA + (xyzNoiseB - xyzNoiseA) * balance;
                    }

                    xyzNoiseValue = xyzNoiseValue - yBalance;

                    // make the noiseVal decrease sharply when we're close to the top of the chunk
                    // guarantees value of -10 at iy=16, so that there is always some air at the top
                    if (iy > sizeY - 4)
                    {
                        double closeToTopOfChunkFactor = (double)((float)(iy - (sizeY - 4)) / 3.0F); // 1/3, 2/3, or 1
                        xyzNoiseValue = xyzNoiseValue * (1.0D - closeToTopOfChunkFactor) + -10.0D * closeToTopOfChunkFactor;
                    }

                    noiseArray[xyzCounter] = xyzNoiseValue;
                    ++xyzCounter;
                }
            }
        }

        return noiseArray;
    }

    @Override
    public void populate(int chunkX, int chunkZ)
    {
        boolean prevLogging = ForgeModContainer.logCascadingWorldGeneration;
        ForgeModContainer.logCascadingWorldGeneration = false;

        BlockFalling.fallInstantly = true;
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BlockPos blockpos = new BlockPos(x, 0, z);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        ChunkPos chunkpos = new ChunkPos(chunkX, chunkZ);
        this.genNetherBridge.generateStructure(this.world, this.rand, chunkpos);

        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.NETHER_LAVA))
            for (int k = 0; k < 8; ++k)
            {
                this.hellSpringGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }

        // don't do this to prevent double-ups
        //MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.world, this.rand, blockpos));

        // note: this was moved earlier to be more similar to overworld biome decoration, however
        // it's possible that this may cause issues with other mods
        biome.decorate(this.world, this.rand, new BlockPos(x, 0, z));

        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.FIRE))
            for (int i1 = 0; i1 < this.rand.nextInt(this.rand.nextInt(10) + 1) + 1; ++i1)
            {
                this.fireFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }

        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.GLOWSTONE))
        {
            for (int j1 = 0; j1 < this.rand.nextInt(this.rand.nextInt(10) + 1); ++j1)
            {
                this.lightGemGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }

            for (int k1 = 0; k1 < 10; ++k1)
            {
                this.hellPortalGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }
        }//Forge: End doGLowstone

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, chunkX, chunkZ, false);

        if (TerrainGen.decorate(this.world, this.rand, blockpos, DecorateBiomeEvent.Decorate.EventType.SHROOM))
        {
            if (this.rand.nextBoolean())
            {
                this.brownMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }

            if (this.rand.nextBoolean())
            {
                this.redMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }
        }

        if (TerrainGen.generateOre(this.world, this.rand, quartzGen, blockpos, OreGenEvent.GenerateMinable.EventType.QUARTZ))
            for (int l1 = 0; l1 < 16; ++l1)
            {
                this.quartzGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
            }

        int i2 = this.world.getSeaLevel() / 2 + 1;

        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.NETHER_MAGMA))
            for (int l = 0; l < 4; ++l)
            {
                this.magmaGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), i2 - 5 + this.rand.nextInt(10), this.rand.nextInt(16)));
            }

        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, false, PopulateChunkEvent.Populate.EventType.NETHER_LAVA2))
            for (int j2 = 0; j2 < 16; ++j2)
            {
                this.lavaTrapGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
            }

        // this should already be called during biome decoration (Vanilla doesn't usually call this for the Nether
        // though, since the decoration method is empty)
        //MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.world, this.rand, blockpos));

        BlockFalling.fallInstantly = false;
        ForgeModContainer.logCascadingWorldGeneration = prevLogging;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        if (creatureType == EnumCreatureType.MONSTER)
        {
            if (this.genNetherBridge.isInsideStructure(pos))
            {
                return this.genNetherBridge.getSpawnList();
            }

            if (this.genNetherBridge.isPositionInStructure(this.world, pos) && this.world.getBlockState(pos.down()).getBlock() == Blocks.NETHER_BRICK)
            {
                return this.genNetherBridge.getSpawnList();
            }
        }

        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Override
    @Nullable
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_)
    {
        return "Fortress".equals(structureName) && this.genNetherBridge != null ? this.genNetherBridge.getClosestStrongholdPos(worldIn, position, p_180513_4_) : null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
        this.genNetherBridge.generate(this.world, x, z, (ChunkPrimer)null);
    }
}
