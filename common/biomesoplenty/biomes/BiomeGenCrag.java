package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import worldcore.interfaces.IWCFog;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;

public class BiomeGenCrag extends BiomeGenBase implements IWCFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenCrag(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		topBlock = (byte)Blocks.cragRock.get().blockID;
		fillerBlock = (byte)Blocks.cragRock.get().blockID;
		waterColorMultiplier = 944693;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 12 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			Block block = Block.blocksList[var10]; 
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 12, 2);
			}
		}
	}
	
	/**
	 * Fog Color
	 */
	@Override
	public int getFogColour()
	{
		return 10514245;
	}

	/**
	 * takes temperature, returns color
	 */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors)
			 return 4944498;
		 else
		 {
			 par1 /= 3.0F;

			 if (par1 < -1.0F)
			 {
				 par1 = -1.0F;
			 }

			 if (par1 > 1.0F)
			 {
				 par1 = 1.0F;
			 }

			 return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		 }
	 }

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
}
