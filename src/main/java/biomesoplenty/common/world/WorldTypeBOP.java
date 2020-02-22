/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.NetherGenSettings;
import net.minecraft.world.gen.OverworldGenSettings;

public class WorldTypeBOP extends WorldType
{
    public WorldTypeBOP()
    {
        super("biomesoplenty");
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator(World world)
    {
    	if (world.getDimension().getType() == DimensionType.OVERWORLD)
    	{
			BOPOverworldGenSettings genSettings = new BOPOverworldGenSettings();
	        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings(world.getLevelData());
	        biomeProviderSettings.setGeneratorSettings(genSettings);
	
	        return new ChunkGeneratorOverworldBOP(world, new BOPBiomeProvider(biomeProviderSettings), genSettings);
    	}
		else if (world.getDimension().getType() == DimensionType.NETHER)
		{
			NetherGenSettings nethergensettings = ChunkGeneratorType.CAVES.createSettings();
			nethergensettings.setDefaultBlock(Blocks.NETHERRACK.defaultBlockState());
			nethergensettings.setDefaultFluid(Blocks.LAVA.defaultBlockState());

			// The nether shares biome provider settings with the overworld
			OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings(world.getLevelData());

			return ChunkGeneratorType.CAVES.create(world, new NetherBiomeProvider(biomeProviderSettings), nethergensettings);
		}
    	else
    	{
    		return super.createChunkGenerator(world);
    	}
    }
}
