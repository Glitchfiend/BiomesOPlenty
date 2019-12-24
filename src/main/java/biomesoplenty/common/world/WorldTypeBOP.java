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
	        OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
	        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings(world.getWorldInfo());
	        biomeProviderSettings.setGeneratorSettings(overworldGenSettings);
	
	        return new ChunkGeneratorOverworldBOP(world, new BOPBiomeProvider(biomeProviderSettings), overworldGenSettings);
    	}
		else if (world.getDimension().getType() == DimensionType.THE_NETHER)
		{
			NetherGenSettings nethergensettings = ChunkGeneratorType.CAVES.createSettings();
			nethergensettings.setDefaultBlock(Blocks.NETHERRACK.getDefaultState());
			nethergensettings.setDefaultFluid(Blocks.LAVA.getDefaultState());

			// The nether shares biome provider settings with the overworld
			OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings(world.getWorldInfo());

			return ChunkGeneratorType.CAVES.create(world, new NetherBiomeProvider(biomeProviderSettings), nethergensettings);
		}
    	/*else if (world.getDimension().getType() == DimensionType.THE_END)
    	{
    		BlockPos SPAWN = new BlockPos(100, 50, 0);

			EndGenerationSettings endgenerationsettings = ChunkGeneratorType.FLOATING_ISLANDS.createSettings();
			EndBiomeProviderSettings endBiomeProviderSettings = new EndBiomeProviderSettings();
			endBiomeProviderSettings.setSeed(world.getSeed());
			endgenerationsettings.setDefaultBlock(Blocks.END_STONE.getDefaultState());
			endgenerationsettings.setDefaultFluid(Blocks.AIR.getDefaultState());
			endgenerationsettings.setSpawnPos(SPAWN);
			return ChunkGeneratorType.FLOATING_ISLANDS.create(world, new BOPEndBiomeProvider(endBiomeProviderSettings), endgenerationsettings);
    	}*/
    	else
    	{
    		return super.createChunkGenerator(world);
    	}
    }
}
