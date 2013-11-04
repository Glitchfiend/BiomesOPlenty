package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import biomesoplenty.api.Blocks;
import biomesoplenty.interfaces.IBOPFog;

public class BiomeGenMarsh extends BiomeGenBase implements IBOPFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMarsh(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = 50;
		customBiomeDecorator.wheatGrassPerChunk = 50;
		customBiomeDecorator.highGrassPerChunk = 50;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.waterLakesPerChunk = 100;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.mudPerChunk = 1;
		customBiomeDecorator.mudPerChunk2 = 1;
		customBiomeDecorator.koruPerChunk = 1;
		customBiomeDecorator.waterReedsPerChunk = 10;
		customBiomeDecorator.generatePumpkins = false;
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		
		 int var55 = 12 + par2Random.nextInt(6);

		for (int var66 = 0; var66 < var55; ++var66)
		{
			int var77 = par3 + par2Random.nextInt(16);
			int var88 = par2Random.nextInt(28) + 4;
			int var99 = par4 + par2Random.nextInt(16);
			int var100 = par1World.getBlockId(var77, var88, var99);

			if (var100 == Block.stone.blockID)
			{
				par1World.setBlock(var77, var88, var99, Blocks.amethystOre.get().blockID, 12, 2);
			}
		}
	}
	
	@Override
	public int getFogColour()
	{
		return 12638463;
	}
	
    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
}
