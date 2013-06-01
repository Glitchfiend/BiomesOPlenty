package biomesoplenty.world;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.FIRE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.GLOWSTONE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_LAVA;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderBOPNaturaHell implements IChunkProvider
{
	private Random hellRNG;
	private NoiseGeneratorOctaves netherNoiseGen1;
	private NoiseGeneratorOctaves netherNoiseGen2;
	private NoiseGeneratorOctaves netherNoiseGen3;
	private NoiseGeneratorOctaves slowsandGravelNoiseGen;
	private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
	public NoiseGeneratorOctaves netherNoiseGen6;
	public NoiseGeneratorOctaves netherNoiseGen7;
	private World worldObj;
	private double[] noiseField;
	public MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
	private double[] slowsandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	private BiomeGenBase[] biomesForGeneration;
	private double[] netherrackExclusivityNoise = new double[256];
	private MapGenBase netherCaveGenerator = new MapGenCavesHell();
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	
	private static Block taintedSoil = null;
	private static Block heatSand = null;
	private static Block glowshroom = null;
	
	private static Constructor flowerGenConstructor = null;
	
	private static Class FlowerGen = null;

	{
		genNetherBridge = (MapGenNetherBridge) TerrainGen.getModdedMapGen(genNetherBridge, NETHER_BRIDGE);
		netherCaveGenerator = TerrainGen.getModdedMapGen(netherCaveGenerator, NETHER_CAVE);
	}

	public ChunkProviderBOPNaturaHell(World par1World, long par2)
	{
		worldObj = par1World;
		hellRNG = new Random(par2);
		netherNoiseGen1 = new NoiseGeneratorOctaves(hellRNG, 16);
		netherNoiseGen2 = new NoiseGeneratorOctaves(hellRNG, 16);
		netherNoiseGen3 = new NoiseGeneratorOctaves(hellRNG, 8);
		slowsandGravelNoiseGen = new NoiseGeneratorOctaves(hellRNG, 4);
		netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(hellRNG, 4);
		netherNoiseGen6 = new NoiseGeneratorOctaves(hellRNG, 10);
		netherNoiseGen7 = new NoiseGeneratorOctaves(hellRNG, 16);

		NoiseGeneratorOctaves[] noiseGens = {netherNoiseGen1, netherNoiseGen2, netherNoiseGen3, slowsandGravelNoiseGen, netherrackExculsivityNoiseGen, netherNoiseGen6, netherNoiseGen7};
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, hellRNG, noiseGens);
		netherNoiseGen1 = noiseGens[0];
		netherNoiseGen2 = noiseGens[1];
		netherNoiseGen3 = noiseGens[2];
		slowsandGravelNoiseGen = noiseGens[3];
		netherrackExculsivityNoiseGen = noiseGens[4];
		netherNoiseGen6 = noiseGens[5];
		netherNoiseGen7 = noiseGens[6];
	}
	
	private static void naturaReflection()
	{
		try 
		{
			taintedSoil = (Block) Class.forName("mods.natura.common.NContent").getField("taintedSoil").get(null);
			heatSand = (Block) Class.forName("mods.natura.common.NContent").getField("heatSand").get(null);
			glowshroom = (Block) Class.forName("mods.natura.common.NContent").getField("glowshroom").get(null);
			
			FlowerGen = Class.forName("mods.natura.worldgen.FlowerGen");
			
			flowerGenConstructor = FlowerGen.getConstructor(new Class[]{int.class, int.class});
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Generates the shape of the terrain in the nether.
	 */
	 public void generateNetherTerrain(int par1, int par2, byte[] par3ArrayOfByte)
	{
		byte b0 = 4;
		byte b1 = 32;
		int k = b0 + 1;
		byte b2 = 17;
		int l = b0 + 1;
		noiseField = this.initializeNoiseField(noiseField, par1 * b0, 0, par2 * b0, k, b2, l);

		for (int i1 = 0; i1 < b0; ++i1)
		{
			for (int j1 = 0; j1 < b0; ++j1)
			{
				for (int k1 = 0; k1 < 16; ++k1)
				{
					double d0 = 0.125D;
					double d1 = noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
					double d2 = noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
					double d3 = noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
					double d4 = noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
					double d5 = (noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
					double d6 = (noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
					double d7 = (noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
					double d8 = (noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

					for (int l1 = 0; l1 < 8; ++l1)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int i2 = 0; i2 < 4; ++i2)
						{
							int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
							short short1 = 128;
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;

							for (int k2 = 0; k2 < 4; ++k2)
							{
								int l2 = 0;

								if (k1 * 8 + l1 < b1)
								{
									l2 = Block.lavaStill.blockID;
								}

								if (d15 > 0.0D)
								{
									l2 = Block.netherrack.blockID;
								}
								
                                if (d15 > 56.0D)
                                {
                                    l2 = this.taintedSoil.blockID;
                                }

								par3ArrayOfByte[j2] = (byte)l2;
								j2 += short1;
								d15 += d16;
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
	  * name based on ChunkProviderGenerate
	  */
	 public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase)
	 {
		 ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3ArrayOfByte, par4ArrayOfBiomeGenBase);
		 MinecraftForge.EVENT_BUS.post(event);
		 if (event.getResult() == Result.DENY) return;

		 byte b0 = 32;
		 double d0 = 0.03125D;
		 slowsandNoise = slowsandGravelNoiseGen.generateNoiseOctaves(slowsandNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0, d0, 1.0D);
		 gravelNoise = slowsandGravelNoiseGen.generateNoiseOctaves(gravelNoise, par1 * 16, 109, par2 * 16, 16, 1, 16, d0, 1.0D, d0);
		 netherrackExclusivityNoise = netherrackExculsivityNoiseGen.generateNoiseOctaves(netherrackExclusivityNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		 for (int k = 0; k < 16; ++k)
		 {
			 for (int l = 0; l < 16; ++l)
			 {
				 BiomeGenBase biomegenbase = par4ArrayOfBiomeGenBase[l + k * 16];

				 boolean flag = slowsandNoise[k + l * 16] + hellRNG.nextDouble() * 0.2D > 0.0D;
				 boolean flag1 = gravelNoise[k + l * 16] + hellRNG.nextDouble() * 0.2D > 0.0D;
				 int i1 = (int)(netherrackExclusivityNoise[k + l * 16] / 3.0D + 3.0D + hellRNG.nextDouble() * 0.25D);
				 int j1 = -1;
				 byte b1 = biomegenbase.topBlock;
				 byte b2 = (byte) this.taintedSoil.blockID;

				 for (int k1 = 127; k1 >= 0; --k1)
				 {
					 int l1 = (l * 16 + k) * 128 + k1;

					 if (k1 < 127 - hellRNG.nextInt(5) && k1 > 0 + hellRNG.nextInt(5))
					 {
						 byte b3 = par3ArrayOfByte[l1];

						 if (b3 == 0)
						 {
							 j1 = -1;
						 }
						 else if (b3 == Block.netherrack.blockID)
						 {
							 if (j1 == -1)
							 {
								 if (i1 <= 0)
								 {
									 b1 = 0;
									 b2 = (byte)Block.netherrack.blockID;
								 }
								 else if (k1 >= b0 - 4 && k1 <= b0 + 1)
								 {
									 b1 = (byte)Block.netherrack.blockID;
	                                 b2 = (byte) this.taintedSoil.blockID;

									 if (flag1)
									 {
										 b1 = (byte)Block.gravel.blockID;
									 }

									 if (flag1)
									 {
										 b2 = (byte)Block.netherrack.blockID;
									 }

									 if (flag)
									 {
										 b1 = (byte)Block.slowSand.blockID;
									 }

	                                    if (flag)
	                                    {
	                                        b2 = (byte) this.heatSand.blockID;
	                                    }
								 }

								 if (k1 < b0 && b1 == 0)
								 {
									 b1 = (byte)Block.lavaStill.blockID;
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
							 }
						 }
					 }
					 else
					 {
						 par3ArrayOfByte[l1] = (byte)Block.bedrock.blockID;
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
		 hellRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		 byte[] abyte = new byte[32768];
		 this.generateNetherTerrain(par1, par2, abyte);
		 biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		 this.replaceBlocksForBiome(par1, par2, abyte, biomesForGeneration);
		 netherCaveGenerator.generate(this, worldObj, par1, par2, abyte);
		 genNetherBridge.generate(this, worldObj, par1, par2, abyte);
		 Chunk chunk = new Chunk(worldObj, abyte, par1, par2);
		 BiomeGenBase[] abiomegenbase = worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
		 byte[] abyte1 = chunk.getBiomeArray();

		 for (int k = 0; k < abyte1.length; ++k)
		 {
			 abyte1[k] = (byte)abiomegenbase[k].biomeID;
		 }

		 chunk.resetRelightChecks();
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

		 double d0 = 684.412D;
		 double d1 = 2053.236D;
		 noiseData4 = netherNoiseGen6.generateNoiseOctaves(noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
		 noiseData5 = netherNoiseGen7.generateNoiseOctaves(noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
		 noiseData1 = netherNoiseGen3.generateNoiseOctaves(noiseData1, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
		 noiseData2 = netherNoiseGen1.generateNoiseOctaves(noiseData2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
		 noiseData3 = netherNoiseGen2.generateNoiseOctaves(noiseData3, par2, par3, par4, par5, par6, par7, d0, d1, d0);
		 int k1 = 0;
		 int l1 = 0;
		 double[] adouble1 = new double[par6];
		 int i2;

		 for (i2 = 0; i2 < par6; ++i2)
		 {
			 adouble1[i2] = Math.cos(i2 * Math.PI * 6.0D / par6) * 2.0D;
			 double d2 = i2;

			 if (i2 > par6 / 2)
			 {
				 d2 = par6 - 1 - i2;
			 }

			 if (d2 < 4.0D)
			 {
				 d2 = 4.0D - d2;
				 adouble1[i2] -= d2 * d2 * d2 * 10.0D;
			 }
		 }

		 for (i2 = 0; i2 < par5; ++i2)
		 {
			 for (int j2 = 0; j2 < par7; ++j2)
			 {
				 double d3 = (noiseData4[l1] + 256.0D) / 512.0D;

				 if (d3 > 1.0D)
				 {
					 d3 = 1.0D;
				 }

				 double d4 = 0.0D;
				 double d5 = noiseData5[l1] / 8000.0D;

				 if (d5 < 0.0D)
				 {
					 d5 = -d5;
				 }

				 d5 = d5 * 3.0D - 3.0D;

				 if (d5 < 0.0D)
				 {
					 d5 /= 2.0D;

					 if (d5 < -1.0D)
					 {
						 d5 = -1.0D;
					 }

					 d5 /= 1.4D;
					 d5 /= 2.0D;
					 d3 = 0.0D;
				 }
				 else
				 {
					 if (d5 > 1.0D)
					 {
						 d5 = 1.0D;
					 }

					 d5 /= 6.0D;
				 }

				 d3 += 0.5D;
				 d5 = d5 * par6 / 16.0D;
				 ++l1;

				 for (int k2 = 0; k2 < par6; ++k2)
				 {
					 double d6 = 0.0D;
					 double d7 = adouble1[k2];
					 double d8 = noiseData2[k1] / 512.0D;
					 double d9 = noiseData3[k1] / 512.0D;
					 double d10 = (noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

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
					 double d11;

					 if (k2 > par6 - 4)
					 {
						 d11 = (k2 - (par6 - 4)) / 3.0F;
						 d6 = d6 * (1.0D - d11) + -10.0D * d11;
					 }

					 if (k2 < d4)
					 {
						 d11 = (d4 - k2) / 4.0D;

						 if (d11 < 0.0D)
						 {
							 d11 = 0.0D;
						 }

						 if (d11 > 1.0D)
						 {
							 d11 = 1.0D;
						 }

						 d6 = d6 * (1.0D - d11) + -10.0D * d11;
					 }

					 par1ArrayOfDouble[k1] = d6;
					 ++k1;
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

		 MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, worldObj, hellRNG, par2, par3, false));

		 int k = par2 * 16;
		 int l = par3 * 16;
		 BiomeGenBase var6 = worldObj.getBiomeGenForCoords(k + 16, l + 16);

		 genNetherBridge.generateStructuresInChunk(worldObj, hellRNG, par2, par3);
		 int i1;
		 int j1;
		 int k1;
		 int l1;

		 boolean doGen = TerrainGen.populate(par1IChunkProvider, worldObj, hellRNG, par2, par3, false, NETHER_LAVA);
		 for (i1 = 0; doGen && i1 < 8; ++i1)
		 {
			 j1 = k + hellRNG.nextInt(16) + 8;
			 k1 = hellRNG.nextInt(120) + 4;
			 l1 = l + hellRNG.nextInt(16) + 8;
			 (new WorldGenHellLava(Block.lavaMoving.blockID, false)).generate(worldObj, hellRNG, j1, k1, l1);
		 }

		 i1 = hellRNG.nextInt(hellRNG.nextInt(10) + 1) + 1;
		 int i2;

		 doGen = TerrainGen.populate(par1IChunkProvider, worldObj, hellRNG, par2, par3, false, FIRE);
		 for (j1 = 0; doGen && j1 < i1; ++j1)
		 {
			 k1 = k + hellRNG.nextInt(16) + 8;
			 l1 = hellRNG.nextInt(120) + 4;
			 i2 = l + hellRNG.nextInt(16) + 8;
			 (new WorldGenFire()).generate(worldObj, hellRNG, k1, l1, i2);
		 }

		 i1 = hellRNG.nextInt(hellRNG.nextInt(10) + 1);

		 doGen = TerrainGen.populate(par1IChunkProvider, worldObj, hellRNG, par2, par3, false, GLOWSTONE);
		 for (j1 = 0; doGen && j1 < i1; ++j1)
		 {
			 k1 = k + hellRNG.nextInt(16) + 8;
			 l1 = hellRNG.nextInt(120) + 4;
			 i2 = l + hellRNG.nextInt(16) + 8;
			 (new WorldGenGlowStone1()).generate(worldObj, hellRNG, k1, l1, i2);
		 }

		 for (j1 = 0; doGen && j1 < 10; ++j1)
		 {
			 k1 = k + hellRNG.nextInt(16) + 8;
			 l1 = hellRNG.nextInt(128);
			 i2 = l + hellRNG.nextInt(16) + 8;
			 (new WorldGenGlowStone2()).generate(worldObj, hellRNG, k1, l1, i2);
		 }

		 MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldObj, hellRNG, k, l));

		 doGen = TerrainGen.decorate(worldObj, hellRNG, k, l, SHROOM);
		 if (doGen && hellRNG.nextInt(1) == 0)
		 {
			 j1 = k + hellRNG.nextInt(16) + 8;
			 k1 = hellRNG.nextInt(128);
			 l1 = l + hellRNG.nextInt(16) + 8;
			 (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, hellRNG, j1, k1, l1);
		 }

		 if (doGen && hellRNG.nextInt(1) == 0)
		 {
			 j1 = k + hellRNG.nextInt(16) + 8;
			 k1 = hellRNG.nextInt(128);
			 l1 = l + hellRNG.nextInt(16) + 8;
			 (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, hellRNG, j1, k1, l1);
		 }

		 WorldGenMinable worldgenminable = new WorldGenMinable(Block.oreNetherQuartz.blockID, 13, Block.netherrack.blockID);
		 int j2;

		 for (k1 = 0; k1 < 16; ++k1)
		 {
			 l1 = k + hellRNG.nextInt(16);
			 i2 = hellRNG.nextInt(108) + 10;
			 j2 = l + hellRNG.nextInt(16);
			 worldgenminable.generate(worldObj, hellRNG, l1, i2, j2);
		 }

		 for (k1 = 0; k1 < 16; ++k1)
		 {
			 l1 = k + hellRNG.nextInt(16);
			 i2 = hellRNG.nextInt(108) + 10;
			 j2 = l + hellRNG.nextInt(16);
			 (new WorldGenHellLava(Block.lavaMoving.blockID, true)).generate(worldObj, hellRNG, l1, i2, j2);
		 }
		 
		 if (doGen && hellRNG.nextInt(7) == 0)
		 {
			 int l2 = k + hellRNG.nextInt(16) + 8;
			 int k4 = hellRNG.nextInt(128);
			 int j6 = l + hellRNG.nextInt(16) + 8;
			 
			 try 
			 {
				Method generate = flowerGenConstructor.newInstance(this.glowshroom.blockID, 0).getClass().getMethod("generate", World.class, Random.class, int.class, int.class, int.class);
				 
				generate.invoke(worldObj, hellRNG, l2, k4, j6);
			 } 
			 catch (Exception e) 
			 {
				e.printStackTrace();
			 }
		 }
		 /*if (doGen && hellRNG.nextInt(8) == 0)
		 {
			 int i3 = k + hellRNG.nextInt(16) + 8;
			 int l4 = hellRNG.nextInt(128);
			 int k6 = l + hellRNG.nextInt(16) + 8;
			 (FlowerGen(NContent.glowshroom.blockID, 1)).generate(worldObj, hellRNG, i3, l4, k6);
		 }
		 if (doGen && hellRNG.nextInt(9) == 0)
		 {
			 int i3 = k + hellRNG.nextInt(16) + 8;
			 int l4 = hellRNG.nextInt(128);
			 int k6 = l + hellRNG.nextInt(16) + 8;
			 (new FlowerGen(NContent.glowshroom.blockID, 2)).generate(worldObj, hellRNG, i3, l4, k6);
		 }*/

		 MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, hellRNG, k, l));

		 var6.decorate(worldObj, hellRNG, k, l);

		 MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, hellRNG, par2, par3, false));

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

	 @Override
	 public void func_104112_b() {}

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
		 return "HellRandomLevelSource";
	 }

	 /**
	  * Returns a list of creatures of the specified type that can spawn at the given location.
	  */
	 @Override
	 public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	 {
		 if (par1EnumCreatureType == EnumCreatureType.monster && genNetherBridge.hasStructureAt(par2, par3, par4))
			 return genNetherBridge.getSpawnList();
		 else
		 {
			 BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(par2, par4);
			 return biomegenbase == null ? null : biomegenbase.getSpawnableList(par1EnumCreatureType);
		 }
	 }

	 /**
	  * Returns the location of the closest structure of the specified type. If not found returns null.
	  */
	 @Override
	 public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
	 {
		 return null;
	 }

	 @Override
	 public int getLoadedChunkCount()
	 {
		 return 0;
	 }

	 @Override
	 public void recreateStructures(int par1, int par2)
	 {
		 genNetherBridge.generate(this, worldObj, par1, par2, (byte[])null);
	 }
}
