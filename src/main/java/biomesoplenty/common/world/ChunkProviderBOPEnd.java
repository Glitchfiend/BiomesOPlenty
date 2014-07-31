package biomesoplenty.common.world;

import java.util.List;
import java.util.Random;

import biomesoplenty.common.utils.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderBOPEnd implements IChunkProvider
{
    private Random rand;
    private World worldObj;
    private PerlinNoise perlin;
    private BiomeGenBase[] biomesForGeneration;
    
    public ChunkProviderBOPEnd(World world, long l)
    {
        worldObj = world;
        rand = new Random(l);
        perlin = new PerlinNoise(l);
    }

    public Chunk provideChunk(int cx, int cy)
    {
    	BiomeGenBase biome = BiomeGenBase.sky;
    	
    	rand.setSeed((long)cx * 0x4f9939f508L + (long)cy * 0x1ef1565bd5L);
        Block[] blocks = new Block[65536];
        byte[] metadata = new byte[65536];
        biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, cx * 16, cy * 16, 16, 16);
    	
        generateTerrain(cx, cy, blocks, metadata, biomesForGeneration);
        replaceBlocksForBiome(cx, cy, blocks, metadata, biomesForGeneration);

        Chunk chunk = new Chunk(this.worldObj, blocks, metadata, cx, cy);
        byte[] abyte1 = chunk.getBiomeArray();
        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)biomesForGeneration[k].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }
    
    public void generateTerrain(int cx, int cy, Block[] blocks, byte[] metadata, BiomeGenBase[] biome)
    {	
    	int p;
    	float noiseA, noiseB, noiseC, noise3, spawnDis;
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			spawnDis = (20000 - ((cx * 16 + j) * (cx * 16 + j) + (cy * 16 + i) * (cy * 16 + i))) / 13000f;
    			noiseC = perlin.noise2((cx * 16 + j) / 80f, (cy * 16 + i) / 80f) * 6 - 1.2f;
    			noiseC = spawnDis > noiseC ? spawnDis : noiseC;
    			
    			noiseA = 64f + (perlin.noise2((cx * 16 + j) / 40f, (cy * 16 + i) / 40f) * 6) + (perlin.noise2((cx * 16 + j) / 12f, (cy * 16 + i) / 12f) * 3) + noiseC * 3;
    			noiseB = 12f + (perlin.noise2((1000f + cx * 16 + j) / 40f, (cy * 16 + i) / 40f) * 6) + (perlin.noise2((cx * 16 + j) / 12f, (cy * 16 + i) / 12f) * 3) - noiseC * 3;
    			
    			for(int k = 0; k < 256; k++)
    			{
    				p = (j * 16 + i) * 256 + k;
    				if(k > 72)
    				{
    					blocks[p] = Blocks.air;
    				}
    				else
    				{
    					if(k < noiseA && k > noiseB)
    					{
    						noise3 = perlin.noise3((cx * 16 + j) / 30f, (cy * 16 + i) / 30f, k / 20f) + noiseC;
    						if(noise3 > 0.2f)
    						{
        						blocks[p] = Blocks.end_stone;
    						}
    						else
    						{
    							blocks[p] = Blocks.air;
    						}
    					}
    					else
    					{
        					blocks[p] = Blocks.air;
    					}
    				}
    			}
    			
    			
    			/*	
    				if(k > noise[0] + noise[1])
    				{
    					if(k < 63)
        				{
        					blocks[p] = Blocks.water;
        				}
        				else
        				{
        					blocks[p] = Blocks.air;
        				}
    				}
    				else if(k <= noise[0] + noise[1] * 6 && k >= noise[0] - noise[1] * 6)
    				{
        				noise3 = perlin.noise3((cx * 16 + j) / 20f, (cy * 16 + i) / 20f, k / 20f) + (noise[0] - k) / (noise[1] * 2);
        				if(noise3 > 0f)
        				{
        					blocks[p] = Blocks.stone;
        				}
        				else if(k < 63)
        				{
        					blocks[p] = Blocks.water;
        				}
        				else
        				{
        					blocks[p] = Blocks.air;
        				}
    				}
    				else if(k < noise[0] - noise[1])
    				{
    					blocks[p] = Blocks.stone;
    				}
    			}
    			n[j * 16 + i] = noise[0];
    			*/
    		}
    	}
    }

    public void replaceBlocksForBiome(int cx, int cy, Block[] blocks, byte[] metadata, BiomeGenBase[] biomes)
    {
    	int depth = -1;
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			BiomeGenBase biome = biomes[i * 16 + j];
				for(int k = 255; k > -1; k--)
				{
					Block b = blocks[(j * 16 + i) * 256 + k];
				    if(b == Blocks.air)
				    {
				    	depth = -1;
				    }
				    else if(b == Blocks.end_stone)
				    {
				    	depth++;
				    	if(depth == 0)
				    	{
				    		blocks[(j * 16 + i) * 256 + k] = biome.topBlock;
				    	}
				    	else if(depth < 4)
				    	{
				    		blocks[(j * 16 + i) * 256 + k] = biome.fillerBlock;
				    	}
				    }
				}
    		}
    	}
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    private double[] func_4061_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
    	return null;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockFalling.fallInstantly = true;
        int x = i * 16;
        int y = j * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(x + 16, y + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)i * i1 + (long)j * j1 ^ this.worldObj.getSeed());

        biomegenbase.decorate(this.worldObj, this.rand, x, y);

        SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, x + 8, y + 8, 16, 16, this.rand);

        BlockFalling.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }
	
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }
	
    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }
    
    public void saveExtraData() {}

    public void recreateStructures(int par1, int par2)
    {
	}
}