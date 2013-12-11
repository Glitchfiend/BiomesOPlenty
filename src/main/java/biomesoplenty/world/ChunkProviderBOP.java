package biomesoplenty.world;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.BOPConfigurationIDs;
import biomesoplenty.configuration.BOPConfigurationTerrainGen;
import biomesoplenty.world.map.MapGenCavesBOP;
import biomesoplenty.world.map.MapGenRavineBOP;
import biomesoplenty.world.noise.NoiseOctaves;
import biomesoplenty.worldgen.structure.BOPMapGenScatteredFeature;
import biomesoplenty.worldgen.structure.BOPMapGenVillage;

public class ChunkProviderBOP implements IChunkProvider
{
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenCavesBOP();
	private MapGenStronghold strongholdGenerator = new MapGenStronghold();
	private BOPMapGenVillage villageGenerator = new BOPMapGenVillage();
	private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
	private BOPMapGenScatteredFeature scatteredFeatureGenerator = new BOPMapGenScatteredFeature();
	private MapGenBase ravineGenerator = new MapGenRavineBOP();
	private BiomeGenBase[] biomesForGeneration;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;

	private NoiseOctaves beachnoise;
	private double[] sandNoise = new double[256];
	private double[] gravelNoise = new double[256];

	float[] parabolicField;
	int[][] field_73219_j = new int[32][32];

	{
		caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
		strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGenerator, STRONGHOLD);
		villageGenerator = (BOPMapGenVillage) TerrainGen.getModdedMapGen(villageGenerator, VILLAGE);
		mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGenerator, MINESHAFT);
		scatteredFeatureGenerator = (BOPMapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
		ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
	}

	public ChunkProviderBOP(World par1World, long par2, boolean par4)
	{
		worldObj = par1World;
		mapFeaturesEnabled = par4;
		rand = new Random(par2);
		noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
		noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
		noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
		noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
		mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);

		beachnoise = new NoiseOctaves(rand, 4);

		NoiseGeneratorOctaves[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6, mobSpawnerNoise};
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, rand, noiseGens);
		noiseGen1 = noiseGens[0];
		noiseGen2 = noiseGens[1];
		noiseGen3 = noiseGens[2];
		noiseGen4 = noiseGens[3];
		noiseGen5 = noiseGens[4];
		noiseGen6 = noiseGens[5];
		mobSpawnerNoise = noiseGens[6];
	}

	/**
	 * Generates the shape of the terrain for the chunk though its all stone though the water is frozen if the
	 * temperature is low enough
	 */
	 public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte)
	{
		 byte b0 = 4;
		 byte b1 = 32;
		 byte b2 = 63;
		 int k = b0 + 1;
		 byte b3 = 33;
		 int l = b0 + 1;
		 biomesForGeneration = worldObj.getWorldChunkManager().getBiomesForGeneration(biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, k + 5, l + 5);
		 noiseArray = this.initializeNoiseField(noiseArray, par1 * b0, 0, par2 * b0, k, b3, l);

		 for (int i1 = 0; i1 < b0; ++i1)
		 {
			 for (int j1 = 0; j1 < b0; ++j1)
			 {
				 for (int k1 = 0; k1 < b1; ++k1)
				 {
					 double d0 = 0.125D;
					 double d1 = noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 0];
					 double d2 = noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 0];
					 double d3 = noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 0];
					 double d4 = noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 0];
					 double d5 = (noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 1] - d1) * d0;
					 double d6 = (noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 1] - d2) * d0;
					 double d7 = (noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 1] - d3) * d0;
					 double d8 = (noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 1] - d4) * d0;

					 for (int l1 = 0; l1 < 8; ++l1)
					 {
						 double d9 = 0.25D;
						 double d10 = d1;
						 double d11 = d2;
						 double d12 = (d3 - d1) * d9;
						 double d13 = (d4 - d2) * d9;

						 for (int i2 = 0; i2 < 4; ++i2)
						 {
							 int j2 = i2 + i1 * 4 << 12 | 0 + j1 * 4 << 8 | k1 * 8 + l1;
							 short short1 = 256;
							 j2 -= short1;
							 double d14 = 0.25D;
							 double d15 = (d11 - d10) * d14;
							 double d16 = d10 - d15;

							 for (int k2 = 0; k2 < 4; ++k2)
							 {
								 if ((d16 += d15) > 0.0D)
								 {
									 par3ArrayOfByte[j2 += short1] = (byte)Block.stone.blockID;
								 }
								 else if (k1 * 8 + l1 < b2)
								 {
									 par3ArrayOfByte[j2 += short1] = (byte)Block.waterStill.blockID;
								 }
								 else
								 {
									 par3ArrayOfByte[j2 += short1] = 0;
								 }
							 }

							 d10 += d12;
							 d11 += d13;
						 }

						 d1 += d5;
						 d2 += d6;
						 d3 += d7;
						 d4 += d8;
					 }
				 }
			 }
		 }
	}

	 /**
	  * Replaces the stone that was placed in with blocks that match the biome
	  */
	 public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase)
	 {
		 ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3ArrayOfByte, par4ArrayOfBiomeGenBase);
		 MinecraftForge.EVENT_BUS.post(event);
		 if (event.getResult() == Result.DENY) return;

		 byte b0 = 63;
		 double d0 = 0.03125D;
		 sandNoise = beachnoise.generateNoiseOctaves(sandNoise, par1 * 16, par2 * 16, 0.0D, 16, 16, 1, d0, d0, 1.0D);
		 gravelNoise = beachnoise.generateNoiseOctaves(gravelNoise, par1 * 16, 109.0134D, par2 * 16, 16, 1, 16, d0, 1.0D, d0);
		 stoneNoise = noiseGen4.generateNoiseOctaves(stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		 for (int k = 0; k < 16; ++k)
		 {
			 for (int l = 0; l < 16; ++l)
			 {
				 BiomeGenBase biomegenbase = par4ArrayOfBiomeGenBase[l + k * 16];
				 float f = biomegenbase.getFloatTemperature();
				 int i1 = (int)(stoneNoise[k + l * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
				 boolean sandbeach = sandNoise[k + l * 16] + rand.nextDouble() * 0.20000000000000001D > 0.0D;
				 boolean gravelbeach = gravelNoise[k + l * 16] + rand.nextDouble() * 0.20000000000000001D > 3D;
				 int j1 = -1;
				 byte b1 = biomegenbase.topBlock;
				 byte b2 = biomegenbase.fillerBlock;

				 for (int k1 = 255; k1 >= 0; --k1)
				 {
					 int l1 = (l * 16 + k) * 256 + k1;

					 if (k1 <= 0 + rand.nextInt(5))
					 {
						 par3ArrayOfByte[l1] = (byte)Block.bedrock.blockID;
					 }
					 else
					 {
						 byte b3 = par3ArrayOfByte[l1];

						 if (b3 == 0)
						 {
							 j1 = -1;
						 }
						 else if (b3 == Block.stone.blockID)
						 {
							 if (j1 == -1)
							 {
								 if (i1 <= 0)
								 {
									 if (BOPConfigurationTerrainGen.exposedStone)
									 {
										 b1 = 0;
										 b2 = (byte)Block.stone.blockID;
									 }
									 else
									 {
										 b1 = biomegenbase.topBlock;
										 b2 = biomegenbase.fillerBlock;
									 }
								 }
								 else if (k1 >= b0 - 4 && k1 <= b0 + 1)
								 {
									 if(biomegenbase.biomeID == BOPConfigurationIDs.originValleyID)
									 {
										 if(gravelbeach)
										 {
											 b1 = 0;
											 b2 = (byte)Block.gravel.blockID;
										 }
										 else if(sandbeach)
										 {
											 b1 = (byte)Block.sand.blockID;
											 b2 = (byte)Block.sand.blockID;
										 }
										 else
										 {
											 b1 = biomegenbase.topBlock;
											 b2 = biomegenbase.fillerBlock;
										 }
									 }
									 else
									 {
										 b1 = biomegenbase.topBlock;
										 b2 = biomegenbase.fillerBlock;
									 }
								 }

								 if (k1 < b0 && b1 == 0)
								 {
									 if (f < 0.15F)
									 {
										 b1 = (byte)Block.ice.blockID;
									 }
									 else
									 {
										 b1 = (byte)Block.waterStill.blockID;
									 }
								 }

								 j1 = i1;

								 if (k1 >= b0 - 1)
								 {
									 par3ArrayOfByte[l1] = b1;
								 }
								 else
								 {
									 par3ArrayOfByte[l1] = b2;
								 }
							 }
							 else if (j1 > 0)
							 {
								 --j1;
								 par3ArrayOfByte[l1] = b2;

								 if (j1 == 0 && b2 == Block.sand.blockID)
								 {
									 j1 = rand.nextInt(4);
									 b2 = (byte)Block.sandStone.blockID;
								 }
							 }
						 }
					 }
				 }
			 }
		 }
	 }

	 /**
	  * loads or generates the chunk at the chunk location specified
	  */
	 @Override
	 public Chunk loadChunk(int par1, int par2)
	 {
		 return this.provideChunk(par1, par2);
	 }

	 /**
	  * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	  * specified chunk from the map seed and chunk seed
	  */
	 @Override
	 public Chunk provideChunk(int par1, int par2)
	 {
		 rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		 byte[] abyte = new byte[0x65536];
		 this.generateTerrain(par1, par2, abyte);
		 biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		 this.replaceBlocksForBiome(par1, par2, abyte, biomesForGeneration);
		 caveGenerator.generate(this, worldObj, par1, par2, abyte);
		 ravineGenerator.generate(this, worldObj, par1, par2, abyte);

		 if (mapFeaturesEnabled)
		 {
			 mineshaftGenerator.generate(this, worldObj, par1, par2, abyte);
			 villageGenerator.generate(this, worldObj, par1, par2, abyte);
			 strongholdGenerator.generate(this, worldObj, par1, par2, abyte);
			 scatteredFeatureGenerator.generate(this, worldObj, par1, par2, abyte);
		 }

        Chunk chunk = new Chunk(worldObj, par1, par2);
        ExtendedBlockStorage aextendedblockstorage[] = chunk.getBlockStorageArray();
        byte[] abyte1 = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        
        for (int k = 0; k < 16; k++)
        {
            for (int l = 0; l < 16; l++)
            {
                for (int i1 = 0; i1 < 256; i1++)
                {
                    byte byte0 = abyte[k << 12 | l << 8 | i1];

                    if (byte0 == 0)
                    {
                        continue;
                    }

                    int j1 = i1 >> 4;

                    if (aextendedblockstorage[j1] == null)
                    {
                        aextendedblockstorage[j1] = new ExtendedBlockStorage(j1 << 4, true);
                    }

                    aextendedblockstorage[j1].setExtBlockID(k, i1 & 0xf, l, byte0 & 0xff);
                }
            }
        }
		
        chunk.generateSkylightMap();
        return chunk;
    }

	 /**
	  * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
	  * size.
	  */
	 private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
	 {
		 ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
		 MinecraftForge.EVENT_BUS.post(event);
		 if (event.getResult() == Result.DENY) return event.noisefield;

		 if (par1ArrayOfDouble == null)
		 {
			 par1ArrayOfDouble = new double[par5 * par6 * par7];
		 }

		 if (parabolicField == null)
		 {
			 parabolicField = new float[25];

			 for (int k1 = -2; k1 <= 2; ++k1)
			 {
				 for (int l1 = -2; l1 <= 2; ++l1)
				 {
					 float f = 10.0F / MathHelper.sqrt_float(k1 * k1 + l1 * l1 + 0.2F);
					 parabolicField[k1 + 2 + (l1 + 2) * 5] = f;
				 }
			 }
		 }

		 double d0 = 684.412D;
		 double d1 = 684.412D;
		 noise5 = noiseGen5.generateNoiseOctaves(noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
		 noise6 = noiseGen6.generateNoiseOctaves(noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
		 noise3 = noiseGen3.generateNoiseOctaves(noise3, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
		 noise1 = noiseGen1.generateNoiseOctaves(noise1, par2, par3, par4, par5, par6, par7, d0, d1, d0);
		 noise2 = noiseGen2.generateNoiseOctaves(noise2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
		 boolean flag = false;
		 boolean flag1 = false;
		 int i2 = 0;
		 int j2 = 0;

		 for (int k2 = 0; k2 < par5; ++k2)
		 {
			 for (int l2 = 0; l2 < par7; ++l2)
			 {
				 float f1 = 0.0F;
				 float f2 = 0.0F;
				 float f3 = 0.0F;
				 byte b0 = 2;
				 BiomeGenBase biomegenbase = biomesForGeneration[k2 + 2 + (l2 + 2) * (par5 + 5)];

				 for (int i3 = -b0; i3 <= b0; ++i3)
				 {
					 for (int j3 = -b0; j3 <= b0; ++j3)
					 {
						 BiomeGenBase biomegenbase1 = biomesForGeneration[k2 + i3 + 2 + (l2 + j3 + 2) * (par5 + 5)];
						 
						 float f4 = parabolicField[i3 + 2 + (j3 + 2) * 5] / (biomegenbase1.minHeight + 2.0F);

						 if (biomegenbase1.minHeight > biomegenbase.minHeight)
						 {
							 f4 /= 2.0F;
						 }

						 f1 += biomegenbase1.maxHeight * f4;
						 f2 += (biomegenbase1.minHeight - 2.0) * f4;
						 f3 += f4;
					 }
				 }

				 f1 /= f3;
				 f2 /= f3;
				 f1 = f1 * 0.9F + 0.1F;
				 f2 = (f2 * 4.0F - 1.0F) / 8.0F;
				 double d2 = noise6[j2] / 8000.0D;

				 if (d2 < 0.0D)
				 {
					 d2 = -d2 * 0.3D;
				 }

				 d2 = d2 * 3.0D - 2.0D;

				 if (d2 < 0.0D)
				 {
					 d2 /= 2.0D;

					 if (d2 < -1.0D)
					 {
						 d2 = -1.0D;
					 }

					 d2 /= 1.4D;
					 d2 /= 2.0D;
				 }
				 else
				 {
					 if (d2 > 1.0D)
					 {
						 d2 = 1.0D;
					 }

					 d2 /= 8.0D;
				 }

				 ++j2;

				 for (int k3 = 0; k3 < par6; ++k3)
				 {
					 double d3 = f2;
					 double d4 = f1;
					 d3 += d2 * 0.2D;
					 d3 = d3 * par6 / 16.0D;
					 double d5 = par6 / 2.0D + d3 * 4.0D;
					 double d6 = 0.0D;
					 double d7 = (k3 - d5) * 12.0D * 128.0D / 128.0D / d4;

					 if (d7 < 0.0D)
					 {
						 d7 *= 4.0D;
					 }

					 double d8 = noise1[i2] / 512.0D;
					 double d9 = noise2[i2] / 512.0D;
					 double d10 = (noise3[i2] / 10.0D + 1.0D) / 2.0D;

					 if (d10 < 0.0D)
					 {
						 d6 = d8;
					 }
					 else if (d10 > 1.0D)
					 {
						 d6 = d9;
					 }
					 else
					 {
						 d6 = d8 + (d9 - d8) * d10;
					 }

					 d6 -= d7;

					 if (k3 > par6 - 4)
					 {
						 double d11 = (k3 - (par6 - 4)) / 3.0F;
						 d6 = d6 * (1.0D - d11) + -10.0D * d11;
					 }

					 par1ArrayOfDouble[i2] = d6;
					 ++i2;
				 }
			 }
		 }

		 return par1ArrayOfDouble;
	 }

	 /**
	  * Checks to see if a chunk exists at x, y
	  */
	 @Override
	 public boolean chunkExists(int par1, int par2)
	 {
		 return true;
	 }

	 /**
	  * Populates chunk with ores etc etc
	  */
	 @Override
	 public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	 {
		 BlockSand.fallInstantly = true;
		 int k = par2 * 16;
		 int l = par3 * 16;
		 BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(k + 16, l + 16);
		 rand.setSeed(worldObj.getSeed());
		 long i1 = rand.nextLong() / 2L * 2L + 1L;
		 long j1 = rand.nextLong() / 2L * 2L + 1L;
		 rand.setSeed(par2 * i1 + par3 * j1 ^ worldObj.getSeed());
		 boolean flag = false;

		 MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, worldObj, rand, par2, par3, flag));

		 if (mapFeaturesEnabled)
		 {
			 mineshaftGenerator.generateStructuresInChunk(worldObj, rand, par2, par3);
			 flag = villageGenerator.generateStructuresInChunk(worldObj, rand, par2, par3);
			 strongholdGenerator.generateStructuresInChunk(worldObj, rand, par2, par3);
			 scatteredFeatureGenerator.generateStructuresInChunk(worldObj, rand, par2, par3);
		 }

		 int k1;
		 int l1;
		 int i2;

		 if (biomegenbase != BiomeGenBase.desert && biomegenbase != BiomeGenBase.desertHills  && biomegenbase != Biomes.desertNew.get()  && biomegenbase != Biomes.glacier.get()  && biomegenbase != Biomes.volcano.get()  && biomegenbase != Biomes.scrubland.get()  && biomegenbase != Biomes.dunes.get()  && biomegenbase != Biomes.arctic.get()  && biomegenbase != Biomes.pasture.get()  && biomegenbase != Biomes.silkglades.get() && !flag && this.rand.nextInt(4) == 0
		            && TerrainGen.populate(par1IChunkProvider, worldObj, rand, par2, par3, flag, LAKE))
		        {
		            k1 = k + this.rand.nextInt(16) + 8;
		            l1 = this.rand.nextInt(128);
		            i2 = l + this.rand.nextInt(16) + 8;
		            (new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, k1, l1, i2);
		        }

		 if (TerrainGen.populate(par1IChunkProvider, worldObj, rand, par2, par3, flag, LAVA) &&
				 !flag && rand.nextInt(8) == 0)
		 {
			 k1 = k + rand.nextInt(16) + 8;
			 l1 = rand.nextInt(rand.nextInt(120) + 8);
			 i2 = l + rand.nextInt(16) + 8;

			 if (l1 < 63 || rand.nextInt(10) == 0)
			 {
				 (new WorldGenLakes(Block.lavaStill.blockID)).generate(worldObj, rand, k1, l1, i2);
			 }
		 }

		 boolean doGen = TerrainGen.populate(par1IChunkProvider, worldObj, rand, par2, par3, flag, DUNGEON);
		 for (k1 = 0; doGen && k1 < 8; ++k1)
		 {
			 l1 = k + rand.nextInt(16) + 8;
			 i2 = rand.nextInt(128);
			 int j2 = l + rand.nextInt(16) + 8;

			 if ((new WorldGenDungeons()).generate(worldObj, rand, l1, i2, j2))
			 {
				 ;
			 }
		 }

		 biomegenbase.decorate(worldObj, rand, k, l);
		 SpawnerAnimals.performWorldGenSpawning(worldObj, biomegenbase, k + 8, l + 8, 16, 16, rand);

		 k += 8;
		 l += 8;

		 doGen = TerrainGen.populate(par1IChunkProvider, worldObj, rand, par2, par3, flag, ICE);
		 for (k1 = 0; doGen && k1 < 16; ++k1)
		 {
			 for (l1 = 0; l1 < 16; ++l1)
			 {
				 i2 = worldObj.getPrecipitationHeight(k + k1, l + l1);

				 if (worldObj.isBlockFreezable(k1 + k, i2 - 1, l1 + l))
				 {
					 worldObj.setBlock(k1 + k, i2 - 1, l1 + l, Block.ice.blockID, 0, 2);
				 }

				 if (worldObj.canSnowAt(k1 + k, i2, l1 + l))
				 {
					 worldObj.setBlock(k1 + k, i2, l1 + l, Block.snow.blockID, 0, 2);
				 }
			 }
		 }

		 MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, rand, par2, par3, flag));

		 BlockSand.fallInstantly = false;
	 }

	 /**
	  * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	  * Return true if all chunks have been saved.
	  */
	 @Override
	 public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	 {
		 return true;
	 }

	 /**
	  * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	  */
	 @Override
	 public boolean unloadQueuedChunks()
	 {
		 return false;
	 }

	 /**
	  * Returns if the IChunkProvider supports saving.
	  */
	 @Override
	 public boolean canSave()
	 {
		 return true;
	 }

	 /**
	  * Converts the instance data to a readable string.
	  */
	 @Override
	 public String makeString()
	 {
		 return "RandomLevelSource";
	 }

	 /**
	  * Returns a list of creatures of the specified type that can spawn at the given location.
	  */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	 {
		 BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(par2, par4);
		 return biomegenbase == null ? null : (biomegenbase == BiomeGenBase.swampland && par1EnumCreatureType == EnumCreatureType.monster && scatteredFeatureGenerator.hasStructureAt(par2, par3, par4) ? scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(par1EnumCreatureType));
	 }

	 /**
	  * Returns the location of the closest structure of the specified type. If not found returns null.
	  */
	 @Override
	 public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
	 {
		 return "Stronghold".equals(par2Str) && strongholdGenerator != null ? strongholdGenerator.getNearestInstance(par1World, par3, par4, par5) : null;
	 }

	 @Override
	 public int getLoadedChunkCount()
	 {
		 return 0;
	 }

	 @Override
	 public void recreateStructures(int par1, int par2)
	 {
		 if (mapFeaturesEnabled)
		 {
			 mineshaftGenerator.generate(this, worldObj, par1, par2, (byte[])null);
			 villageGenerator.generate(this, worldObj, par1, par2, (byte[])null);
			 strongholdGenerator.generate(this, worldObj, par1, par2, (byte[])null);
			 scatteredFeatureGenerator.generate(this, worldObj, par1, par2, (byte[])null);
		 }
	 }

	@Override
	public void saveExtraData() 
	{
	}
}
