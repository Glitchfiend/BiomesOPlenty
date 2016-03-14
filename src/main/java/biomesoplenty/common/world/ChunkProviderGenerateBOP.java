/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.OCEAN_MONUMENT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.util.biome.BiomeUtils;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class ChunkProviderGenerateBOP implements IChunkGenerator
{
    
    private Random rand;
    private NoiseGeneratorOctaves xyzNoiseGenA;
    private NoiseGeneratorOctaves xyzNoiseGenB;
    private NoiseGeneratorOctaves xyzBalanceNoiseGen;
    private NoiseGeneratorPerlin stoneNoiseGen;
    public NoiseGeneratorBOPByte byteNoiseGen;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private BOPWorldSettings settings;
    private IBlockState seaBlockState;
    private IBlockState stoneBlockState;
    private MapGenBase caveGenerator;
    private MapGenStronghold strongholdGenerator;
    private MapGenVillage villageGenerator;
    private MapGenMineshaft mineshaftGenerator;
    private MapGenScatteredFeature scatteredFeatureGenerator;
    private MapGenBase ravineGenerator;
    private StructureOceanMonument oceanMonumentGenerator;
    private double[] xyzBalanceNoiseArray;
    private double[] xyzNoiseArrayA;
    private double[] xyzNoiseArrayB;
    private double[] stoneNoiseArray;
    private final double[] noiseArray;
    private Map<BiomeGenBase, TerrainSettings> biomeTerrainSettings;
    
    public ChunkProviderGenerateBOP(World worldIn, long seed, boolean mapFeaturesEnabled, String chunkProviderSettingsString)
    {
        System.out.println("ChunkProviderGenerateBOP json: "+chunkProviderSettingsString);
        
        this.worldObj = worldIn;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.rand = new Random(seed);
        
        this.settings = new BOPWorldSettings(chunkProviderSettingsString);
        System.out.println("ChunkProviderGenerateBOP settings: "+this.settings.toJson());
                
        // set up structure generators (overridable by forge)
        this.caveGenerator = TerrainGen.getModdedMapGen(new MapGenCaves(), CAVE);
        this.strongholdGenerator = (MapGenStronghold)TerrainGen.getModdedMapGen(new MapGenStronghold(), STRONGHOLD);
        this.villageGenerator = (MapGenVillage)TerrainGen.getModdedMapGen(new MapGenVillage(), VILLAGE);
        this.mineshaftGenerator = (MapGenMineshaft)TerrainGen.getModdedMapGen(new MapGenMineshaft(), MINESHAFT);
        this.scatteredFeatureGenerator = (MapGenScatteredFeature)TerrainGen.getModdedMapGen(new MapGenScatteredFeature(), SCATTERED_FEATURE);
        this.ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavine(), RAVINE);
        this.oceanMonumentGenerator = (StructureOceanMonument)TerrainGen.getModdedMapGen(new StructureOceanMonument(), OCEAN_MONUMENT);
                
        // set up the noise generators
        this.xyzNoiseGenA = new NoiseGeneratorOctaves(this.rand, 16);
        this.xyzNoiseGenB = new NoiseGeneratorOctaves(this.rand, 16);
        this.xyzBalanceNoiseGen = new NoiseGeneratorOctaves(this.rand, 8);
        this.stoneNoiseGen = new NoiseGeneratorPerlin(this.rand, 4);
        this.byteNoiseGen = new NoiseGeneratorBOPByte(this.rand, 6, 5, 5); // 6 octaves, 5x5 xz grid
        this.stoneNoiseArray = new double[256];
        this.noiseArray = new double[825];

        // blockstates for stone and sea blocks
        this.stoneBlockState = Blocks.stone.getDefaultState();
        this.seaBlockState = Blocks.water.getDefaultState();
        
        // store a TerrainSettings object for each biome
        this.biomeTerrainSettings = new HashMap<BiomeGenBase, TerrainSettings>();
        for (BiomeGenBase biome : BiomeUtils.getRegisteredBiomes())
        {
            if (biome == null) {continue;}
            this.biomeTerrainSettings.put(biome, (biome instanceof BOPBiome) ? ((BOPBiome)biome).terrainSettings : TerrainSettings.forVanillaBiome(biome));
        }
        
    }
    
    
    
    @Override
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        // initialize the random generator using the chunk coordinates
        this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
        
        // create the primer
        ChunkPrimer chunkprimer = new ChunkPrimer();

        // start off by adding the basic terrain shape with air stone and water blocks
        this.setChunkAirStoneWater(chunkX, chunkZ, chunkprimer);
        
        // hand over to the biomes for them to set bedrock grass and dirt
        BiomeGenBase[] biomes = this.worldObj.getBiomeProvider().loadBlockGeneratorData(null, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBlocksForBiome(chunkX, chunkZ, chunkprimer, biomes);

        // add structures
        if (this.settings.useCaves)
        {
            this.caveGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        }
        if (this.settings.useRavines)
        {
            this.ravineGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        }
        if (this.settings.useMineShafts && this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        }
        if (this.settings.useVillages && this.mapFeaturesEnabled)
        {
            this.villageGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        }
        if (this.settings.useStrongholds && this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        }
        if (this.settings.useTemples && this.mapFeaturesEnabled)
        {
            this.scatteredFeatureGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        }
        if (this.settings.useMonuments && this.mapFeaturesEnabled)
        {
            this.oceanMonumentGenerator.generate(this.worldObj, chunkX, chunkZ, chunkprimer);
        }

        // create and return the chunk
        Chunk chunk = new Chunk(this.worldObj, chunkprimer, chunkX, chunkZ);
        byte[] chunkBiomes = chunk.getBiomeArray();
        for (int k = 0; k < chunkBiomes.length; ++k)
        {
            chunkBiomes[k] = (byte)BiomeGenBase.getIdForBiome(biomes[k]);
        }
        chunk.generateSkylightMap();
        return chunk;
    }
    
   

    
    
    public void setChunkAirStoneWater(int chunkX, int chunkZ, ChunkPrimer primer)
    {
                
        // get noise values for the whole chunk
        this.populateNoiseArray(chunkX, chunkZ);
        
        double oneEighth = 0.125D;
        double oneQuarter = 0.25D;
        
        // entire chunk is 16x256x16
        // process chunk in subchunks, each one 4x8x4 blocks in size
        // 4 subchunks in x direction, each 4 blocks long
        // 32 subchunks in y direction, each 8 blocks long
        // 4 subchunks in z direction, each 4 blocks long
        // for a total of 512 subchunks

        // divide chunk into 4 subchunks in x direction, index as ix
        for (int ix = 0; ix < 4; ++ix)
        {
            int k_x0 = ix * 5;
            int k_x1 = (ix + 1) * 5;

            // divide chunk into 4 subchunks in z direction, index as iz
            for (int iz = 0; iz < 4; ++iz)
            {
                int k_x0z0 = (k_x0 + iz) * 33;
                int k_x0z1 = (k_x0 + iz + 1) * 33;
                int k_x1z0 = (k_x1 + iz) * 33;
                int k_x1z1 = (k_x1 + iz + 1) * 33;

                // divide chunk into 32 subchunks in y direction, index as iy
                for (int iy = 0; iy < 32; ++iy)
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
                                
                                // If the noise value is above zero, this block starts as stone
                                // Otherwise it's 'empty' - air above sealevel and water below it
                                if (noiseVal > 0.0D)
                                {
                                    primer.setBlockState(ix * 4 + jx, iy * 8 + jy, iz * 4 + jz, this.stoneBlockState);
                                }
                                else if (iy * 8 + jy < this.settings.seaLevel)
                                {
                                    primer.setBlockState(ix * 4 + jx, iy * 8 + jy, iz * 4 + jz, this.seaBlockState);
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
    public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer primer, BiomeGenBase[] biomes)
    {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.worldObj)) return;

        double d0 = 0.03125D;
        this.stoneNoiseArray = this.stoneNoiseGen.func_151599_a(this.stoneNoiseArray, (double)(chunkX * 16), (double)(chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int localX = 0; localX < 16; ++localX)
        {
            for (int localZ = 0; localZ < 16; ++localZ)
            {
                BiomeGenBase biome = biomes[localZ + localX * 16];
                biome.genTerrainBlocks(this.worldObj, this.rand, primer, chunkX * 16 + localX, chunkZ * 16 + localZ, this.stoneNoiseArray[localZ + localX * 16]);
            }
        }
    }

    
 
    
    // a 5x5 bell-shaped curve which can be multiplied over to quickly get a weighted average with a radial falloff - the constant was chosen so that it sums to 1
    private static float[] radialFalloff5x5 = new float[25];
    // similar to the above but falls off faster, so giving stronger weight to the center item
    private static float[] radialStrongFalloff5x5 = new float[25];
    static {
        for (int j = -2; j <= 2; ++j)
        {
            for (int k = -2; k <= 2; ++k)
            {
                radialFalloff5x5[j + 2 + (k + 2) * 5] = 0.06476162171F / MathHelper.sqrt_float((float)(j * j + k * k) + 0.2F);
                radialStrongFalloff5x5[j + 2 + (k + 2) * 5] = 0.076160519601F / ((float)(j * j + k * k) + 0.2F);
            }
        }
    }
       
    
    private TerrainSettings getWeightedTerrainSettings(int localX, int localZ, BiomeGenBase[] biomes)
    {
        
        // Rivers shouldn't be influenced by the neighbors
        BiomeGenBase centerBiome = biomes[localX + 2 + (localZ + 2) * 10];
        if (centerBiome == Biomes.river || centerBiome == Biomes.frozenRiver || ((centerBiome instanceof BOPBiome) && ((BOPBiome)centerBiome).noNeighborTerrainInfuence))
        {
            return this.biomeTerrainSettings.get(centerBiome);
        }
        
        // Otherwise, get weighted average of properties from this and surrounding biomes
        TerrainSettings settings = new TerrainSettings();
        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {                
                float weight = radialFalloff5x5[i + 2 + (j + 2) * 5];
                TerrainSettings biomeSettings = this.biomeTerrainSettings.get(biomes[localX + i + 2 + (localZ + j + 2) * 10]);              
                
                if (biomeSettings != null)
                {
                    settings.avgHeight += weight * biomeSettings.avgHeight;
                    settings.variationAbove += weight * biomeSettings.variationAbove;
                    settings.variationBelow += weight * biomeSettings.variationBelow;                
                    settings.minHeight += weight * biomeSettings.minHeight;
                    settings.maxHeight += weight * biomeSettings.maxHeight;
                    settings.sidewaysNoiseAmount += weight * biomeSettings.sidewaysNoiseAmount;
                    for (int k = 0; k < settings.octaveWeights.length; k++)
                    {
                        settings.octaveWeights[k] += weight * biomeSettings.octaveWeights[k];
                    }
                }
            }
        }  
               
        return settings;
    }    
    

    
    private void populateNoiseArray(int chunkX, int chunkZ)
    {

        BiomeGenBase[] biomes = this.worldObj.getBiomeProvider().getBiomesForGeneration(null, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);

        // values from vanilla
        float coordinateScale = 684.412F;
        float heightScale = 684.412F;
        double upperLimitScale = 512.0D;
        double lowerLimitScale = 512.0D;
        float mainNoiseScaleX = 80.0F;
        float mainNoiseScaleY = 160.0F;
        float mainNoiseScaleZ = 80.0F;

        int subchunkX = chunkX * 4;
        int subchunkY = 0;
        int subchunkZ = chunkZ * 4;

        // generate the xz noise for the chunk
        this.byteNoiseGen.generateNoise(subchunkX, subchunkZ);

        // generate the xyz noise for the chunk
        this.xyzBalanceNoiseArray = this.xyzBalanceNoiseGen.generateNoiseOctaves(this.xyzBalanceNoiseArray, subchunkX, subchunkY, subchunkZ, 5, 33, 5, (double)(coordinateScale / mainNoiseScaleX), (double)(heightScale / mainNoiseScaleY), (double)(coordinateScale / mainNoiseScaleZ));
        this.xyzNoiseArrayA = this.xyzNoiseGenA.generateNoiseOctaves(this.xyzNoiseArrayA, subchunkX, subchunkY, subchunkZ, 5, 33, 5, (double)coordinateScale, (double)heightScale, (double)coordinateScale);
        this.xyzNoiseArrayB = this.xyzNoiseGenB.generateNoiseOctaves(this.xyzNoiseArrayB, subchunkX, subchunkY, subchunkZ, 5, 33, 5, (double)coordinateScale, (double)heightScale, (double)coordinateScale);

        // loop over the subchunks and calculate the overall noise value
        int xyzCounter = 0;
        int xzCounter = 0;
        for (int ix = 0; ix < 5; ++ix)
        {
            for (int iz = 0; iz < 5; ++iz)
            {
                // get the terrain settings to use for this subchunk as a weighted average of the settings from the nearby biomes                
                TerrainSettings settings = this.getWeightedTerrainSettings(ix, iz, biomes);

                // get the xz noise value                
                double xzNoiseVal = this.byteNoiseGen.getWeightedDouble(xzCounter, settings.octaveWeights);

                // get the amplitudes
                double xzAmplitude = this.settings.amplitude * (xzNoiseVal < 0 ? settings.variationBelow : settings.variationAbove) * (1 - settings.sidewaysNoiseAmount);
                double xyzAmplitude = this.settings.amplitude * (xzNoiseVal < 0 ? settings.variationBelow : settings.variationAbove) * (settings.sidewaysNoiseAmount);

                // the 'base level' is the average height, plus the height from the xz noise
                double baseLevel = settings.avgHeight + (xzNoiseVal * xzAmplitude);

                for (int iy = 0; iy < 33; ++iy)
                {
                    int y = iy * 8;

                    if (y < settings.minHeight)
                    {
                        this.noiseArray[xyzCounter] = settings.minHeight - y;
                    }
                    else if (y > settings.maxHeight)
                    {
                        this.noiseArray[xyzCounter] = settings.maxHeight - y;
                    }
                    else
                    {
                        // calculate the xzy noise value
                        double xyzNoiseA = this.xyzNoiseArrayA[xyzCounter] / lowerLimitScale;
                        double xyzNoiseB = this.xyzNoiseArrayB[xyzCounter] / upperLimitScale;
                        double balance = (this.xyzBalanceNoiseArray[xyzCounter] / 10.0D + 1.0D) / 2.0D;
                        double xyzNoiseValue = MathHelper.denormalizeClamp(xyzNoiseA, xyzNoiseB, balance) / 50.0D;

                        // calculate the depth
                        double depth = baseLevel - y + (xyzAmplitude * xyzNoiseValue);

                        // make the noiseVal decrease sharply when we're close to the top of the chunk
                        // guarantees value of -10 at iy=32, so that there is always some air at the top
                        if (iy > 29)
                        {
                            double closeToTopOfChunkFactor = (double)((float)(iy - 29) / 3.0F); // 1/3, 2/3 or 1
                            depth = depth * (1.0D - closeToTopOfChunkFactor) + -10.0D * closeToTopOfChunkFactor;
                        }

                        this.noiseArray[xyzCounter] = depth;
                    }
                    ++xyzCounter;
                }

                xzCounter++;

            }
        }
    }
    
    @Override
    public void populate(int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        
        BlockPos blockpos = new BlockPos(x, 0, z);
        
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
        
        this.rand.setSeed(this.worldObj.getSeed());
        long l0 = this.rand.nextLong() / 2L * 2L + 1L;
        long l1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)chunkX * l0 + (long)chunkZ * l1 ^ this.worldObj.getSeed());
        boolean hasVillageGenerated = false;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(chunkX, chunkZ);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, worldObj, rand, chunkX, chunkZ, hasVillageGenerated));

        // populate the structures
        if (this.settings.useMineShafts && this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        }
        if (this.settings.useVillages && this.mapFeaturesEnabled)
        {
            hasVillageGenerated = this.villageGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        }
        if (this.settings.useStrongholds && this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        }
        if (this.settings.useTemples && this.mapFeaturesEnabled)
        {
            this.scatteredFeatureGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        }
        if (this.settings.useMonuments && this.mapFeaturesEnabled)
        {
            this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
        }

        BlockPos decorateStart = blockpos.add(8, 0, 8);
        BlockPos target;
        
        // add water lakes
        if (biomegenbase.getRainfall() > 0.01F && biomegenbase != Biomes.desert && biomegenbase != Biomes.desertHills && this.settings.useWaterLakes && !hasVillageGenerated && this.rand.nextInt(this.settings.waterLakeChance) == 0 && TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, hasVillageGenerated, LAKE))
        {
            target = decorateStart.add(this.rand.nextInt(16), this.rand.nextInt(256), this.rand.nextInt(16));
            (new WorldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, target);
        }

        // add lava lakes
        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, hasVillageGenerated, LAVA) && !hasVillageGenerated && this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes)
        {
            target = decorateStart.add(this.rand.nextInt(16), this.rand.nextInt(248) + 8, this.rand.nextInt(16));
            if (target.getY() < 63 || this.rand.nextInt(this.settings.lavaLakeChance / 8) == 0)
            {
                (new WorldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, target);
            }
        }

        // add dungeons
        if (this.settings.useDungeons && TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, hasVillageGenerated, DUNGEON))
        {
            for (int i = 0; i < this.settings.dungeonChance; ++i)
            {
                target = decorateStart.add(this.rand.nextInt(16), this.rand.nextInt(256), this.rand.nextInt(16));
                (new WorldGenDungeons()).generate(this.worldObj, this.rand, target);
            }
        }

        // hand over to the biome to decorate itself
        biomegenbase.decorate(this.worldObj, this.rand, new BlockPos(x, 0, z));
        
        // add animals
        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, hasVillageGenerated, ANIMALS))
        {
            WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biomegenbase, x + 8, z + 8, 16, 16, this.rand);
        }
        
        // add ice and snow
        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, hasVillageGenerated, ICE))
        {
            for (int i = 0; i < 16; ++i)
            {
                for (int j = 0; j < 16; ++j)
                {
                    target = this.worldObj.getPrecipitationHeight(decorateStart.add(i, 0, j));
                    BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(target);
                    // if it's cold enough for ice, and there's exposed water, then freeze it
                    if (this.worldObj.canBlockFreezeWater(target.down()))
                    {
                        this.worldObj.setBlockState(target.down(), Blocks.ice.getDefaultState(), 2);
                    }
                    // if it's cold enough for snow, add a layer of snow
                    if (biome.getRainfall() > 0.01F && this.worldObj.canSnowAt(target, true))
                    {
                        this.worldObj.setBlockState(target, Blocks.snow_layer.getDefaultState(), 2);
                    }
                }
            }           
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this, worldObj, rand, chunkX, chunkZ, hasVillageGenerated));

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        boolean flag = false;

        if (this.settings.useMonuments && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L)
        {
            flag |= this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, new ChunkCoordIntPair(x, z));
        }

        return flag;
    }

    @Override
    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);

        if (this.mapFeaturesEnabled)
        {
            if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.func_175798_a(pos))
            {
                return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
            }

            if (creatureType == EnumCreatureType.MONSTER && this.settings.useMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.worldObj, pos))
            {
                return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
            }
        }

        return biomegenbase.getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position)
    {
        return "Stronghold".equals(structureName) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn, position) : null;
    }

    @Override
    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
    {
        if (this.settings.useMineShafts && this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generate(this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
        }

        if (this.settings.useVillages && this.mapFeaturesEnabled)
        {
            this.villageGenerator.generate(this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
        }

        if (this.settings.useStrongholds && this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
        }

        if (this.settings.useTemples && this.mapFeaturesEnabled)
        {
            this.scatteredFeatureGenerator.generate(this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
        }

        if (this.settings.useMonuments && this.mapFeaturesEnabled)
        {
            this.oceanMonumentGenerator.generate(this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
        }
    }
}