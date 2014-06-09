package biomesoplenty.api.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BOPInheritedBiome<T extends BOPBiomeDecorator> extends BOPOverriddenBiome<T>
{
	protected BiomeGenBase inheritedBiome;
	
	public BOPInheritedBiome(int biomeID, Class<T> clazz, BiomeGenBase inheritedBiome) 
	{
		super(biomeID, clazz);
		
		this.inheritedBiome = inheritedBiome;
		
        this.func_150557_a(inheritedBiome.color, true);
        this.biomeName = inheritedBiome.biomeName;
        this.topBlock = inheritedBiome.topBlock;
        this.fillerBlock = inheritedBiome.fillerBlock;
        this.field_76754_C = inheritedBiome.field_76754_C;
        this.rootHeight = inheritedBiome.rootHeight;
        this.heightVariation = inheritedBiome.heightVariation;
        this.temperature = inheritedBiome.temperature;
        this.rainfall = inheritedBiome.rainfall;
        this.waterColorMultiplier = inheritedBiome.waterColorMultiplier;
        this.enableSnow = inheritedBiome.getEnableSnow();
        this.enableRain = inheritedBiome.canSpawnLightningBolt();
        this.spawnableCreatureList = inheritedBiome.getSpawnableList(EnumCreatureType.creature);
        this.spawnableMonsterList = inheritedBiome.getSpawnableList(EnumCreatureType.monster);
        this.spawnableCaveCreatureList = inheritedBiome.getSpawnableList(EnumCreatureType.ambient);
        this.spawnableWaterCreatureList = inheritedBiome.getSpawnableList(EnumCreatureType.waterCreature);
	}

    @Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
    	this.inheritedBiome.theBiomeDecorator.decorateChunk(world, random, this.inheritedBiome, chunkX, chunkZ);
        this.theBiomeDecorator.decorateChunk(world, random, this, chunkX, chunkZ);
    }
	
	@Override
	public void genTerrainBlocks(World world, Random random, Block[] blockArray, byte[] metaArray, int chunkX, int chunkZ, double noise)
	{
		this.inheritedBiome.genTerrainBlocks(world, random, blockArray, metaArray, chunkX, chunkZ, noise);
	}
	
    @Override
	public float getSpawningChance()
    {
        return this.inheritedBiome.getSpawningChance();
    }

    @Override
	public WorldGenAbstractTree func_150567_a(Random random)
    {
        return this.inheritedBiome.func_150567_a(random);
    }
	
    @Override
	public int getBiomeGrassColor(int x, int y, int z)
    {
    	return this.inheritedBiome.getBiomeGrassColor(x, y, z);
    }

    @Override
	public int getBiomeFoliageColor(int x, int y, int z)
    {
        return this.inheritedBiome.getBiomeFoliageColor(x, y, z);
    }
    
    @Override
	public boolean isEqualTo(BiomeGenBase biome)
    {
        return this.inheritedBiome.isEqualTo(biome);
    }
	
    @Override
	public BiomeGenBase.TempCategory getTempCategory()
    {
        return this.inheritedBiome.getTempCategory();
    }
}
