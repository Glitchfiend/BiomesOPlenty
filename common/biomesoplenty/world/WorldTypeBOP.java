package biomesoplenty.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.configfile.BOPConfigurationBiomeGen;
import biomesoplenty.configuration.configfile.BOPConfigurationTerrainGen;

import com.google.common.base.Optional;

public class WorldTypeBOP extends WorldType
{
	public WorldTypeBOP() {
		super(4, "BIOMESOP");
		this.biomesForWorldType = WorldType.DEFAULT.getBiomesForWorldType();
		this.removeAllBiomes();

		if (BOPConfigurationBiomeGen.alpsGen == true)
		{
			addNewBiome(Biomes.alps);
		}
		if (BOPConfigurationBiomeGen.arcticGen == true)
		{
			addNewBiome(Biomes.arctic);
		}
		if (BOPConfigurationBiomeGen.autumnHillsGen == true)
		{
			addNewBiome(Biomes.autumnHills);
		}
		if (BOPConfigurationBiomeGen.badlandsGen == true)
		{
			addNewBiome(Biomes.badlands);
		}
		if (BOPConfigurationBiomeGen.bambooForestGen == true)
		{
			addNewBiome(Biomes.bambooForest);
		}
		if (BOPConfigurationBiomeGen.bayouGen == true)
		{
			addNewBiome(Biomes.bayou);
		}
		if (BOPConfigurationBiomeGen.birchForestGen == true)
		{
			addNewBiome(Biomes.birchForest);
		}
		if (BOPConfigurationBiomeGen.bogGen == true)
		{
			addNewBiome(Biomes.bog);
		}
		if (BOPConfigurationBiomeGen.borealForestGen == true)
		{
			addNewBiome(Biomes.borealForest);
		}
		if (BOPConfigurationBiomeGen.brushlandGen == true)
		{
			addNewBiome(Biomes.brushland);
		}
		if (BOPConfigurationBiomeGen.canyonGen == true)
		{
			addNewBiome(Biomes.canyon);
		}
		if (BOPConfigurationBiomeGen.chaparralGen == true)
		{
			addNewBiome(Biomes.chaparral);
		}
		if (BOPConfigurationBiomeGen.cherryBlossomGroveGen == true)
		{
			addNewBiome(Biomes.cherryBlossomGrove);
		}
		if (BOPConfigurationBiomeGen.coniferousForestGen == true)
		{
			addNewBiome(Biomes.coniferousForest);
		}
		if (BOPConfigurationBiomeGen.coniferousForestSnowGen == true)
		{
			addNewBiome(Biomes.coniferousForestSnow);
		}
		if (BOPConfigurationBiomeGen.cragGen == true)
		{
			addNewBiome(Biomes.crag);
		}
		if (BOPConfigurationBiomeGen.deadForestGen == true)
		{
			addNewBiome(Biomes.deadForest);
		}
		if (BOPConfigurationBiomeGen.deadForestSnowGen == true)
		{
			addNewBiome(Biomes.deadForestSnow);
		}
		if (BOPConfigurationBiomeGen.deadSwampGen == true)
		{
			addNewBiome(Biomes.deadSwamp);
		}
		if (BOPConfigurationBiomeGen.deadlandsGen == true)
		{
			addNewBiome(Biomes.deadlands);
		}
		if (BOPConfigurationBiomeGen.deciduousForestGen == true)
		{
			addNewBiome(Biomes.deciduousForest);
		}
		if (BOPConfigurationBiomeGen.dunesGen == true)
		{
			addNewBiome(Biomes.dunes);
		}
		if (BOPConfigurationBiomeGen.fenGen == true)
		{
			addNewBiome(Biomes.fen);
		}
		if (BOPConfigurationBiomeGen.fieldGen == true)
		{
			addNewBiome(Biomes.field);
		}
		if (BOPConfigurationBiomeGen.frostForestGen == true)
		{
			addNewBiome(Biomes.frostForest);
		}
		if (BOPConfigurationBiomeGen.fungiForestGen == true)
		{
			addNewBiome(Biomes.fungiForest);
		}
		if (BOPConfigurationBiomeGen.gardenGen == true)
		{
			addNewBiome(Biomes.garden);
		}
		if (BOPConfigurationBiomeGen.glacierGen == true)
		{
			addNewBiome(Biomes.glacier);
		}
		if (BOPConfigurationBiomeGen.grasslandGen == true)
		{
			addNewBiome(Biomes.grassland);
		}
		if (BOPConfigurationBiomeGen.groveGen == true)
		{
			addNewBiome(Biomes.grove);
		}
		if (BOPConfigurationBiomeGen.heathlandGen == true)
		{
			addNewBiome(Biomes.heathland);
		}
		if (BOPConfigurationBiomeGen.highlandGen == true)
		{
			addNewBiome(Biomes.highland);
		}
		if (BOPConfigurationBiomeGen.hotSpringsGen == true)
		{
			addNewBiome(Biomes.hotSprings);
		}
		if (BOPConfigurationBiomeGen.icyHillsGen == true)
		{
			addNewBiome(Biomes.icyHills);
		}
		if (BOPConfigurationBiomeGen.jadeCliffsGen == true)
		{
			addNewBiome(Biomes.jadeCliffs);
		}
		if (BOPConfigurationBiomeGen.lavenderFieldsGen == true)
		{
			addNewBiome(Biomes.lavenderFields);
		}
		if (BOPConfigurationBiomeGen.lushDesertGen == true)
		{
			addNewBiome(Biomes.lushDesert);
		}
		if (BOPConfigurationBiomeGen.lushSwampGen == true)
		{
			addNewBiome(Biomes.lushSwamp);
		}
		if (BOPConfigurationBiomeGen.mangroveGen == true)
		{
			addNewBiome(Biomes.mangrove);
		}
		if (BOPConfigurationBiomeGen.mapleWoodsGen == true)
		{
			addNewBiome(Biomes.mapleWoods);
		}
		if (BOPConfigurationBiomeGen.marshGen == true)
		{
			addNewBiome(Biomes.marsh);
		}
		if (BOPConfigurationBiomeGen.meadowGen == true)
		{
			addNewBiome(Biomes.meadow);
		}
		if (BOPConfigurationBiomeGen.mesaGen == true)
		{
			addNewBiome(Biomes.mesa);
		}
		if (BOPConfigurationBiomeGen.moorGen == true)
		{
			addNewBiome(Biomes.moor);
		}
		if (BOPConfigurationBiomeGen.mountainGen == true)
		{
			addNewBiome(Biomes.mountain);
		}
		if (BOPConfigurationBiomeGen.mysticGroveGen == true)
		{
			addNewBiome(Biomes.mysticGrove);
		}
		if (BOPConfigurationBiomeGen.oasisGen == true)
		{
			addNewBiome(Biomes.oasis);
		}
		if (BOPConfigurationBiomeGen.ominousWoodsGen == true)
		{
			addNewBiome(Biomes.ominousWoods);
		}
		if (BOPConfigurationBiomeGen.orchardGen == true)
		{
			addNewBiome(Biomes.orchard);
		}
		if (BOPConfigurationBiomeGen.originValleyGen == true)
		{
			addNewBiome(Biomes.originValley);
		}
		if (BOPConfigurationBiomeGen.outbackGen == true)
		{
			addNewBiome(Biomes.outback);
		}
		if (BOPConfigurationBiomeGen.overgrownGreensGen == true)
		{
			addNewBiome(Biomes.overgrownGreens);
		}
		if (BOPConfigurationBiomeGen.pastureGen == true)
		{
			addNewBiome(Biomes.pasture);
		}
		if (BOPConfigurationBiomeGen.polarGen == true)
		{
			addNewBiome(Biomes.polar);
		}
		if (BOPConfigurationBiomeGen.prairieGen == true)
		{
			addNewBiome(Biomes.prairie);
		}
		if (BOPConfigurationBiomeGen.quagmireGen == true)
		{
			addNewBiome(Biomes.quagmire);
		}
		if (BOPConfigurationBiomeGen.rainforestGen == true)
		{
			addNewBiome(Biomes.rainforest);
		}
		if (BOPConfigurationBiomeGen.redwoodForestGen == true)
		{
			addNewBiome(Biomes.redwoodForest);
		}
		if (BOPConfigurationBiomeGen.sacredSpringsGen == true)
		{
			addNewBiome(Biomes.sacredSprings);
		}
		if (BOPConfigurationBiomeGen.savannaGen == true)
		{
			addNewBiome(Biomes.savanna);
		}
		if (BOPConfigurationBiomeGen.scrublandGen == true)
		{
			addNewBiome(Biomes.scrubland);
		}
		if (BOPConfigurationBiomeGen.seasonalForestGen == true)
		{
			addNewBiome(Biomes.seasonalForest);
		}
		if (BOPConfigurationBiomeGen.shieldGen == true)
		{
			addNewBiome(Biomes.shield);
		}
		if (BOPConfigurationBiomeGen.shrublandGen == true)
		{
			addNewBiome(Biomes.shrubland);
		}
		if (BOPConfigurationBiomeGen.silkgladesGen == true)
		{
			addNewBiome(Biomes.silkglades);
		}
		if (BOPConfigurationBiomeGen.sludgepitGen == true)
		{
			addNewBiome(Biomes.sludgepit);
		}
		if (BOPConfigurationBiomeGen.spruceWoodsGen == true)
		{
			addNewBiome(Biomes.spruceWoods);
		}
		if (BOPConfigurationBiomeGen.steppeGen == true)
		{
			addNewBiome(Biomes.steppe);
		}
		if (BOPConfigurationBiomeGen.temperateRainforestGen == true)
		{
			addNewBiome(Biomes.temperateRainforest);
		}
		if (BOPConfigurationBiomeGen.thicketGen == true)
		{
			addNewBiome(Biomes.thicket);
		}
		if (BOPConfigurationBiomeGen.timberGen == true)
		{
			addNewBiome(Biomes.timber);
		}
		if (BOPConfigurationBiomeGen.tropicalRainforestGen == true)
		{
			addNewBiome(Biomes.tropicalRainforest);
		}
		if (BOPConfigurationBiomeGen.tropicsGen == true)
		{
			addNewBiome(Biomes.tropics);
		}
		if (BOPConfigurationBiomeGen.tundraGen == true)
		{
			addNewBiome(Biomes.tundra);
		}
		if (BOPConfigurationBiomeGen.volcanoGen == true)
		{
			addNewBiome(Biomes.volcano);
		}
		if (BOPConfigurationBiomeGen.wastelandGen == true)
		{
			addNewBiome(Biomes.wasteland);
		}
		if (BOPConfigurationBiomeGen.wetlandGen == true)
		{
			addNewBiome(Biomes.wetland);
		}
		if (BOPConfigurationBiomeGen.woodlandGen == true)
		{
			addNewBiome(Biomes.woodland);
		}


		if (BOPConfigurationBiomeGen.plainsGen == true)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.plainsNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.plains);
			}
		}
		if (BOPConfigurationBiomeGen.desertGen == true)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.desertNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.desert);
			}
		}
		if (BOPConfigurationBiomeGen.extremeHillsGen == true)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.extremeHillsNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.extremeHills);
			}
		}
		if (BOPConfigurationBiomeGen.forestGen == true)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.forestNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.forest);
			}
		}
		if (BOPConfigurationBiomeGen.taigaGen == true)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.taigaNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.taiga);
			}
		}
		if (BOPConfigurationBiomeGen.swamplandGen == true)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.swamplandNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.swampland);
			}
		}
		if (BOPConfigurationBiomeGen.jungleGen == true)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.jungleNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.jungle);
			}
		}
	}

	@Override
	public WorldChunkManager getChunkManager(World var1)
	{
		return new WorldChunkManagerBOP(var1);
	}

	@Override
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
		if (biome.isPresent()) {
			this.addNewBiome(biome.get());
		}
	}
}
