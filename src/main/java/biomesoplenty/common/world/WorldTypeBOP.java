/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
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
	        // Mojang does this through a billion builders.
	        OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
	        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();
	        biomeProviderSettings.setWorldInfo(world.getWorldInfo());
	        biomeProviderSettings.setGeneratorSettings(overworldGenSettings);
	
	        return new ChunkGeneratorOverworldBOP(world, new BOPBiomeProvider(biomeProviderSettings), overworldGenSettings);
    	}
    	/*else if (world.getDimension().getType() == DimensionType.THE_END)
    	{
    		BlockPos SPAWN = new BlockPos(100, 50, 0);
    		
    	    EndGenSettings endgensettings = ChunkGeneratorType.FLOATING_ISLANDS.createSettings();
	        EndBiomeProviderSettings endBiomeProviderSettings = new EndBiomeProviderSettings();
	        endBiomeProviderSettings.setSeed(world.getSeed());
    	    endgensettings.setDefautBlock(Blocks.END_STONE.getDefaultState());
    	    endgensettings.setDefaultFluid(Blocks.AIR.getDefaultState());
    	    endgensettings.setSpawnPos(SPAWN);
    	    return ChunkGeneratorType.FLOATING_ISLANDS.create(world, new BOPEndBiomeProvider(endBiomeProviderSettings), endgensettings);
    	}*/
    	else
    	{
    		return super.createChunkGenerator(world);
    	}
    }
}
