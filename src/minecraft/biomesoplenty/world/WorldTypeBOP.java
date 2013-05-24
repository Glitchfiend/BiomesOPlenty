package biomesoplenty.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.MinecraftForge;

import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.BOPConfiguration;

import com.google.common.base.Optional;

public class WorldTypeBOP extends WorldType
{
	public WorldTypeBOP() {
		super(4, "BIOMESOP");
		this.removeAllBiomes();

		if (BOPConfiguration.alpsGen == true)
		{
			addNewBiome(Biomes.alps);
		}
		if (BOPConfiguration.arcticGen == true)
		{
			addNewBiome(Biomes.arctic);
		}
		if (BOPConfiguration.badlandsGen == true)
		{
			addNewBiome(Biomes.badlands);
		}
		if (BOPConfiguration.bambooForestGen == true)
		{
			addNewBiome(Biomes.bambooForest);
		}
		if (BOPConfiguration.bayouGen == true)
		{
			addNewBiome(Biomes.bayou);
		}
		if (BOPConfiguration.birchForestGen == true)
		{
			addNewBiome(Biomes.birchForest);
		}
		if (BOPConfiguration.bogGen == true)
		{
			addNewBiome(Biomes.bog);
		}
		if (BOPConfiguration.borealForestGen == true)
		{
			addNewBiome(Biomes.borealForest);
		}
		if (BOPConfiguration.brushlandGen == true)
		{
			addNewBiome(Biomes.brushland);
		}
		if (BOPConfiguration.canyonGen == true)
		{
			addNewBiome(Biomes.canyon);
		}
		if (BOPConfiguration.chaparralGen == true)
		{
			addNewBiome(Biomes.chaparral);
		}
		if (BOPConfiguration.cherryBlossomGroveGen == true)
		{
			addNewBiome(Biomes.cherryBlossomGrove);
		}
		if (BOPConfiguration.coniferousForestGen == true)
		{
			addNewBiome(Biomes.coniferousForest);
		}
		if (BOPConfiguration.coniferousForestSnowGen == true)
		{
			addNewBiome(Biomes.coniferousForestSnow);
		}
		if (BOPConfiguration.cragGen == true)
		{
			addNewBiome(Biomes.crag);
		}
		if (BOPConfiguration.deadForestGen == true)
		{
			addNewBiome(Biomes.deadForest);
		}
		if (BOPConfiguration.deadForestSnowGen == true)
		{
			addNewBiome(Biomes.deadForestSnow);
		}
		if (BOPConfiguration.deadSwampGen == true)
		{
			addNewBiome(Biomes.deadSwamp);
		}
		if (BOPConfiguration.deadlandsGen == true)
		{
			addNewBiome(Biomes.deadlands);
		}
		if (BOPConfiguration.deciduousForestGen == true)
		{
			addNewBiome(Biomes.deciduousForest);
		}
		if (BOPConfiguration.dunesGen == true)
		{
			addNewBiome(Biomes.dunes);
		}
		if (BOPConfiguration.fenGen == true)
		{
			addNewBiome(Biomes.fen);
		}
		if (BOPConfiguration.fieldGen == true)
		{
			addNewBiome(Biomes.field);
		}
		if (BOPConfiguration.frostForestGen == true)
		{
			addNewBiome(Biomes.frostForest);
		}
		if (BOPConfiguration.fungiForestGen == true)
		{
			addNewBiome(Biomes.fungiForest);
		}
		if (BOPConfiguration.gardenGen == true)
		{
			addNewBiome(Biomes.garden);
		}
		if (BOPConfiguration.glacierGen == true)
		{
			addNewBiome(Biomes.glacier);
		}
		if (BOPConfiguration.grasslandGen == true)
		{
			addNewBiome(Biomes.grassland);
		}
		if (BOPConfiguration.groveGen == true)
		{
			addNewBiome(Biomes.grove);
		}
		if (BOPConfiguration.heathlandGen == true)
		{
			addNewBiome(Biomes.heathland);
		}
		if (BOPConfiguration.highlandGen == true)
		{
			addNewBiome(Biomes.highland);
		}
		if (BOPConfiguration.icyHillsGen == true)
		{
			addNewBiome(Biomes.icyHills);
		}
		if (BOPConfiguration.jadeCliffsGen == true)
		{
			addNewBiome(Biomes.jadeCliffs);
		}
		if (BOPConfiguration.lushDesertGen == true)
		{
			addNewBiome(Biomes.lushDesert);
		}
		if (BOPConfiguration.lushSwampGen == true)
		{
			addNewBiome(Biomes.lushSwamp);
		}
		if (BOPConfiguration.mangroveGen == true)
		{
			addNewBiome(Biomes.mangrove);
		}
		if (BOPConfiguration.mapleWoodsGen == true)
		{
			addNewBiome(Biomes.mapleWoods);
		}
		if (BOPConfiguration.marshGen == true)
		{
			addNewBiome(Biomes.marsh);
		}
		if (BOPConfiguration.meadowGen == true)
		{
			addNewBiome(Biomes.meadow);
		}
		if (BOPConfiguration.mesaGen == true)
		{
			addNewBiome(Biomes.mesa);
		}
		if (BOPConfiguration.moorGen == true)
		{
			addNewBiome(Biomes.moor);
		}
		if (BOPConfiguration.mountainGen == true)
		{
			addNewBiome(Biomes.mountain);
		}
		if (BOPConfiguration.mysticGroveGen == true)
		{
			addNewBiome(Biomes.mysticGrove);
		}
		if (BOPConfiguration.oasisGen == true)
		{
			addNewBiome(Biomes.oasis);
		}
		if (BOPConfiguration.ominousWoodsGen == true)
		{
			addNewBiome(Biomes.ominousWoods);
		}
		if (BOPConfiguration.orchardGen == true)
		{
			addNewBiome(Biomes.orchard);
		}
		if (BOPConfiguration.originValleyGen == true)
		{
			addNewBiome(Biomes.originValley);
		}
		if (BOPConfiguration.outbackGen == true)
		{
			addNewBiome(Biomes.outback);
		}
		if (BOPConfiguration.pastureGen == true)
		{
			addNewBiome(Biomes.pasture);
		}
		if (BOPConfiguration.polarGen == true)
		{
			addNewBiome(Biomes.polar);
		}
		if (BOPConfiguration.prairieGen == true)
		{
			addNewBiome(Biomes.prairie);
		}
		if (BOPConfiguration.quagmireGen == true)
		{
			addNewBiome(Biomes.quagmire);
		}
		if (BOPConfiguration.rainforestGen == true)
		{
			addNewBiome(Biomes.rainforest);
		}
		if (BOPConfiguration.redwoodForestGen == true)
		{
			addNewBiome(Biomes.redwoodForest);
		}
		if (BOPConfiguration.sacredSpringsGen == true)
		{
			addNewBiome(Biomes.sacredSprings);
		}
		if (BOPConfiguration.savannaGen == true)
		{
			addNewBiome(Biomes.savanna);
		}
		if (BOPConfiguration.scrublandGen == true)
		{
			addNewBiome(Biomes.scrubland);
		}
		if (BOPConfiguration.seasonalForestGen == true)
		{
			addNewBiome(Biomes.seasonalForest);
		}
		if (BOPConfiguration.shieldGen == true)
		{
			addNewBiome(Biomes.shield);
		}
		if (BOPConfiguration.shrublandGen == true)
		{
			addNewBiome(Biomes.shrubland);
		}
		if (BOPConfiguration.spruceWoodsGen == true)
		{
			addNewBiome(Biomes.spruceWoods);
		}
		if (BOPConfiguration.steppeGen == true)
		{
			addNewBiome(Biomes.steppe);
		}
		if (BOPConfiguration.swampwoodsGen == true)
		{
			addNewBiome(Biomes.swampwoods);
		}
		if (BOPConfiguration.temperateRainforestGen == true)
		{
			addNewBiome(Biomes.temperateRainforest);
		}
		if (BOPConfiguration.thicketGen == true)
		{
			addNewBiome(Biomes.thicket);
		}
		if (BOPConfiguration.tropicalRainforestGen == true)
		{
			addNewBiome(Biomes.tropicalRainforest);
		}
		if (BOPConfiguration.tropicsGen == true)
		{
			addNewBiome(Biomes.tropics);
		}
		if (BOPConfiguration.tundraGen == true)
		{
			addNewBiome(Biomes.tundra);
		}
		if (BOPConfiguration.volcanoGen == true)
		{
			addNewBiome(Biomes.volcano);
		}
		if (BOPConfiguration.wastelandGen == true)
		{
			addNewBiome(Biomes.wasteland);
		}
		if (BOPConfiguration.wetlandGen == true)
		{
			addNewBiome(Biomes.wetland);
		}
		if (BOPConfiguration.woodlandGen == true)
		{
			addNewBiome(Biomes.woodland);
		}
		
		
		if (BOPConfiguration.plainsGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				addNewBiome(Biomes.plainsNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.plains);
				}
		}
		if (BOPConfiguration.desertGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				addNewBiome(Biomes.desertNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.desert);
				}
		}
		if (BOPConfiguration.extremeHillsGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				addNewBiome(Biomes.extremeHillsNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.extremeHills);
				}
		}
		if (BOPConfiguration.forestGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				addNewBiome(Biomes.forestNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.forest);
				}
		}
		if (BOPConfiguration.taigaGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				addNewBiome(Biomes.taigaNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.taiga);
				}
		}
		if (BOPConfiguration.swamplandGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				addNewBiome(Biomes.swamplandNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.swampland);
				}
		}
		if (BOPConfiguration.jungleGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				addNewBiome(Biomes.jungleNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.jungle);
				}
		}
	}
	
	public WorldChunkManager getChunkManager(World var1)
    {
        return new WorldChunkManagerBOP(var1);
    }
	
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderBOP(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
	
	public void removeAllBiomes()
	{
		this.removeBiome(BiomeGenBase.plains);
		this.removeBiome(BiomeGenBase.desert);
		this.removeBiome(BiomeGenBase.forest);
		this.removeBiome(BiomeGenBase.extremeHills);
		this.removeBiome(BiomeGenBase.taiga);
		this.removeBiome(BiomeGenBase.swampland);
		this.removeBiome(BiomeGenBase.jungle);
	}

	public void addNewBiome(Optional<? extends BiomeGenBase> biome)
    {
		if (biome.isPresent())
			this.addNewBiome(biome.get());
    }
}
