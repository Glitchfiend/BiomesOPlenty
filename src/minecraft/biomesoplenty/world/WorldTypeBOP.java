package biomesoplenty.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.BOPConfiguration;

import com.google.common.base.Optional;

public class WorldTypeBOP extends WorldType
{
	public WorldTypeBOP() {
		super(4, "BIOMESOP");
		this.biomesForWorldType = super.getBiomesForWorldType();
		this.removeAllBiomes();

		if (BOPConfiguration.BiomeGen.alpsGen == true)
		{
			addNewBiome(Biomes.alps);
		}
		if (BOPConfiguration.BiomeGen.arcticGen == true)
		{
			addNewBiome(Biomes.arctic);
		}
		if (BOPConfiguration.BiomeGen.badlandsGen == true)
		{
			addNewBiome(Biomes.badlands);
		}
		if (BOPConfiguration.BiomeGen.bambooForestGen == true)
		{
			addNewBiome(Biomes.bambooForest);
		}
		if (BOPConfiguration.BiomeGen.bayouGen == true)
		{
			addNewBiome(Biomes.bayou);
		}
		if (BOPConfiguration.BiomeGen.birchForestGen == true)
		{
			addNewBiome(Biomes.birchForest);
		}
		if (BOPConfiguration.BiomeGen.bogGen == true)
		{
			addNewBiome(Biomes.bog);
		}
		if (BOPConfiguration.BiomeGen.borealForestGen == true)
		{
			addNewBiome(Biomes.borealForest);
		}
		if (BOPConfiguration.BiomeGen.brushlandGen == true)
		{
			addNewBiome(Biomes.brushland);
		}
		if (BOPConfiguration.BiomeGen.canyonGen == true)
		{
			addNewBiome(Biomes.canyon);
		}
		if (BOPConfiguration.BiomeGen.chaparralGen == true)
		{
			addNewBiome(Biomes.chaparral);
		}
		if (BOPConfiguration.BiomeGen.cherryBlossomGroveGen == true)
		{
			addNewBiome(Biomes.cherryBlossomGrove);
		}
		if (BOPConfiguration.BiomeGen.coniferousForestGen == true)
		{
			addNewBiome(Biomes.coniferousForest);
		}
		if (BOPConfiguration.BiomeGen.coniferousForestSnowGen == true)
		{
			addNewBiome(Biomes.coniferousForestSnow);
		}
		if (BOPConfiguration.BiomeGen.cragGen == true)
		{
			addNewBiome(Biomes.crag);
		}
		if (BOPConfiguration.BiomeGen.deadForestGen == true)
		{
			addNewBiome(Biomes.deadForest);
		}
		if (BOPConfiguration.BiomeGen.deadForestSnowGen == true)
		{
			addNewBiome(Biomes.deadForestSnow);
		}
		if (BOPConfiguration.BiomeGen.deadSwampGen == true)
		{
			addNewBiome(Biomes.deadSwamp);
		}
		if (BOPConfiguration.BiomeGen.deadlandsGen == true)
		{
			addNewBiome(Biomes.deadlands);
		}
		if (BOPConfiguration.BiomeGen.deciduousForestGen == true)
		{
			addNewBiome(Biomes.deciduousForest);
		}
		if (BOPConfiguration.BiomeGen.dunesGen == true)
		{
			addNewBiome(Biomes.dunes);
		}
		if (BOPConfiguration.BiomeGen.fenGen == true)
		{
			addNewBiome(Biomes.fen);
		}
		if (BOPConfiguration.BiomeGen.fieldGen == true)
		{
			addNewBiome(Biomes.field);
		}
		if (BOPConfiguration.BiomeGen.frostForestGen == true)
		{
			addNewBiome(Biomes.frostForest);
		}
		if (BOPConfiguration.BiomeGen.fungiForestGen == true)
		{
			addNewBiome(Biomes.fungiForest);
		}
		if (BOPConfiguration.BiomeGen.gardenGen == true)
		{
			addNewBiome(Biomes.garden);
		}
		if (BOPConfiguration.BiomeGen.glacierGen == true)
		{
			addNewBiome(Biomes.glacier);
		}
		if (BOPConfiguration.BiomeGen.grasslandGen == true)
		{
			addNewBiome(Biomes.grassland);
		}
		if (BOPConfiguration.BiomeGen.groveGen == true)
		{
			addNewBiome(Biomes.grove);
		}
		if (BOPConfiguration.BiomeGen.heathlandGen == true)
		{
			addNewBiome(Biomes.heathland);
		}
		if (BOPConfiguration.BiomeGen.highlandGen == true)
		{
			addNewBiome(Biomes.highland);
		}
		if (BOPConfiguration.BiomeGen.hotSpringsGen == true)
		{
			addNewBiome(Biomes.hotSprings);
		}
		if (BOPConfiguration.BiomeGen.icyHillsGen == true)
		{
			addNewBiome(Biomes.icyHills);
		}
		if (BOPConfiguration.BiomeGen.jadeCliffsGen == true)
		{
			addNewBiome(Biomes.jadeCliffs);
		}
		if (BOPConfiguration.BiomeGen.lushDesertGen == true)
		{
			addNewBiome(Biomes.lushDesert);
		}
		if (BOPConfiguration.BiomeGen.lushSwampGen == true)
		{
			addNewBiome(Biomes.lushSwamp);
		}
		if (BOPConfiguration.BiomeGen.mangroveGen == true)
		{
			addNewBiome(Biomes.mangrove);
		}
		if (BOPConfiguration.BiomeGen.mapleWoodsGen == true)
		{
			addNewBiome(Biomes.mapleWoods);
		}
		if (BOPConfiguration.BiomeGen.marshGen == true)
		{
			addNewBiome(Biomes.marsh);
		}
		if (BOPConfiguration.BiomeGen.meadowGen == true)
		{
			addNewBiome(Biomes.meadow);
		}
		if (BOPConfiguration.BiomeGen.mesaGen == true)
		{
			addNewBiome(Biomes.mesa);
		}
		if (BOPConfiguration.BiomeGen.moorGen == true)
		{
			addNewBiome(Biomes.moor);
		}
		if (BOPConfiguration.BiomeGen.mountainGen == true)
		{
			addNewBiome(Biomes.mountain);
		}
		if (BOPConfiguration.BiomeGen.mysticGroveGen == true)
		{
			addNewBiome(Biomes.mysticGrove);
		}
		if (BOPConfiguration.BiomeGen.oasisGen == true)
		{
			addNewBiome(Biomes.oasis);
		}
		if (BOPConfiguration.BiomeGen.ominousWoodsGen == true)
		{
			addNewBiome(Biomes.ominousWoods);
		}
		if (BOPConfiguration.BiomeGen.orchardGen == true)
		{
			addNewBiome(Biomes.orchard);
		}
		if (BOPConfiguration.BiomeGen.originValleyGen == true)
		{
			addNewBiome(Biomes.originValley);
		}
		if (BOPConfiguration.BiomeGen.outbackGen == true)
		{
			addNewBiome(Biomes.outback);
		}
		if (BOPConfiguration.BiomeGen.overgrownGreensGen == true)
		{
			addNewBiome(Biomes.overgrownGreens);
		}
		if (BOPConfiguration.BiomeGen.pastureGen == true)
		{
			addNewBiome(Biomes.pasture);
		}
		if (BOPConfiguration.BiomeGen.polarGen == true)
		{
			addNewBiome(Biomes.polar);
		}
		if (BOPConfiguration.BiomeGen.prairieGen == true)
		{
			addNewBiome(Biomes.prairie);
		}
		if (BOPConfiguration.BiomeGen.quagmireGen == true)
		{
			addNewBiome(Biomes.quagmire);
		}
		if (BOPConfiguration.BiomeGen.rainforestGen == true)
		{
			addNewBiome(Biomes.rainforest);
		}
		if (BOPConfiguration.BiomeGen.redwoodForestGen == true)
		{
			addNewBiome(Biomes.redwoodForest);
		}
		if (BOPConfiguration.BiomeGen.sacredSpringsGen == true)
		{
			addNewBiome(Biomes.sacredSprings);
		}
		if (BOPConfiguration.BiomeGen.savannaGen == true)
		{
			addNewBiome(Biomes.savanna);
		}
		if (BOPConfiguration.BiomeGen.scrublandGen == true)
		{
			addNewBiome(Biomes.scrubland);
		}
		if (BOPConfiguration.BiomeGen.seasonalForestGen == true)
		{
			addNewBiome(Biomes.seasonalForest);
		}
		if (BOPConfiguration.BiomeGen.shieldGen == true)
		{
			addNewBiome(Biomes.shield);
		}
		if (BOPConfiguration.BiomeGen.shrublandGen == true)
		{
			addNewBiome(Biomes.shrubland);
		}
		if (BOPConfiguration.BiomeGen.silkgladesGen == true)
		{
			addNewBiome(Biomes.silkglades);
		}
		if (BOPConfiguration.BiomeGen.sludgepitGen == true)
		{
			addNewBiome(Biomes.sludgepit);
		}
		if (BOPConfiguration.BiomeGen.spruceWoodsGen == true)
		{
			addNewBiome(Biomes.spruceWoods);
		}
		if (BOPConfiguration.BiomeGen.steppeGen == true)
		{
			addNewBiome(Biomes.steppe);
		}
		if (BOPConfiguration.BiomeGen.temperateRainforestGen == true)
		{
			addNewBiome(Biomes.temperateRainforest);
		}
		if (BOPConfiguration.BiomeGen.thicketGen == true)
		{
			addNewBiome(Biomes.thicket);
		}
		if (BOPConfiguration.BiomeGen.timberGen == true)
		{
			addNewBiome(Biomes.timber);
		}
		if (BOPConfiguration.BiomeGen.tropicalRainforestGen == true)
		{
			addNewBiome(Biomes.tropicalRainforest);
		}
		if (BOPConfiguration.BiomeGen.tropicsGen == true)
		{
			addNewBiome(Biomes.tropics);
		}
		if (BOPConfiguration.BiomeGen.tundraGen == true)
		{
			addNewBiome(Biomes.tundra);
		}
		if (BOPConfiguration.BiomeGen.volcanoGen == true)
		{
			addNewBiome(Biomes.volcano);
		}
		if (BOPConfiguration.BiomeGen.wastelandGen == true)
		{
			addNewBiome(Biomes.wasteland);
		}
		if (BOPConfiguration.BiomeGen.wetlandGen == true)
		{
			addNewBiome(Biomes.wetland);
		}
		if (BOPConfiguration.BiomeGen.woodlandGen == true)
		{
			addNewBiome(Biomes.woodland);
		}


		if (BOPConfiguration.BiomeGen.plainsGen == true)
		{
			if (BOPConfiguration.TerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.plainsNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.plains);
			}
		}
		if (BOPConfiguration.BiomeGen.desertGen == true)
		{
			if (BOPConfiguration.TerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.desertNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.desert);
			}
		}
		if (BOPConfiguration.BiomeGen.extremeHillsGen == true)
		{
			if (BOPConfiguration.TerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.extremeHillsNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.extremeHills);
			}
		}
		if (BOPConfiguration.BiomeGen.forestGen == true)
		{
			if (BOPConfiguration.TerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.forestNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.forest);
			}
		}
		if (BOPConfiguration.BiomeGen.taigaGen == true)
		{
			if (BOPConfiguration.TerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.taigaNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.taiga);
			}
		}
		if (BOPConfiguration.BiomeGen.swamplandGen == true)
		{
			if (BOPConfiguration.TerrainGen.vanillaEnhanced == true)
			{
				addNewBiome(Biomes.swamplandNew);
			}
			else
			{
				this.addNewBiome(BiomeGenBase.swampland);
			}
		}
		if (BOPConfiguration.BiomeGen.jungleGen == true)
		{
			if (BOPConfiguration.TerrainGen.vanillaEnhanced == true)
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
