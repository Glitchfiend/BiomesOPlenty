package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;

public class BiomeGenDesertNew extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenDesertNew(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Block.sand.blockID;
		fillerBlock = (byte)Block.sand.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.deadBushPerChunk = 2;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.cactiPerChunk = 5;
		customBiomeDecorator.desertSproutsPerChunk = 1;
		customBiomeDecorator.tinyCactiPerChunk = 2;
		customBiomeDecorator.generateQuicksand = true;
		customBiomeDecorator.aloePerChunk = 1;
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);

        if (BOPConfiguration.Misc.generateAmethystOres)
        {
            int var55 = 12 + par2Random.nextInt(6);

            for (int var66 = 0; var66 < var55; ++var66)
            {
                int var77 = par3 + par2Random.nextInt(16);
                int var88 = par2Random.nextInt(28) + 4;
                int var99 = par4 + par2Random.nextInt(16);
                int var100 = par1World.getBlockId(var77, var88, var99);

                if (var100 == Block.stone.blockID)
                {
                    par1World.setBlock(var77, var88, var99, Blocks.amethystOre.get().blockID, 2, 2);
                }
            }
        }

		if (par2Random.nextInt(1000) == 0)
		{
			int var5 = par3 + par2Random.nextInt(16) + 8;
			int var6 = par4 + par2Random.nextInt(16) + 8;
			WorldGenDesertWells var7 = new WorldGenDesertWells();
			var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
		}
	}
	
	/**
	 * takes temperature, returns color
	 */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfiguration.Misc.skyColors)
			 return 13877903;
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
}
