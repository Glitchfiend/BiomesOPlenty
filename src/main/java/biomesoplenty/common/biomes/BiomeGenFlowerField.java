package biomesoplenty.common.biomes;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;

public class BiomeGenFlowerField extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.1F);

	public BiomeGenFlowerField(int par1)
	{
		super(par1);
		
		//TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO:	setColor()
        this.setColor(4044093);
        this.setTemperatureRainfall(0.6F, 0.7F);
		
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 90;

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
			
			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 4, 2);
			}
		}
	}
	
    @Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
    {
    	return random.nextInt(7) == 0 ? new WorldGenTallGrass(Blocks.red_flower, 7) : (random.nextInt(5) == 0 ? new WorldGenTallGrass(Blocks.red_flower, 6) : (random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.red_flower, 5) : (random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.red_flower, 4) : new WorldGenTallGrass(Blocks.tallgrass, 1))));
    }
    
    @Override
    //TODO:		getBiomeGrassColor()
	public int func_150558_b(int x, int y, int z)
    {
    	return 7390273;
    }
    
    @SideOnly(Side.CLIENT)
    //TODO:		getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
        return 7390273;
    }
}
