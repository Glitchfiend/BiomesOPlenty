package tdwp_ftw.biomesop.worldtype;

import net.minecraft.world.biome.BiomeGenBase;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBiomes;
import tdwp_ftw.biomesop.declarations.BOPConfiguration;

public class WTBiomesOP extends WorldTypeBase
{
	public WTBiomesOP() {
		super(4, "BIOMESOP");
		this.removeAllBiomes();
		this.removeBiome(BiomeGenBase.plains);
        this.removeBiome(BiomeGenBase.desert);
        this.removeBiome(BiomeGenBase.forest);
        this.removeBiome(BiomeGenBase.extremeHills);
        this.removeBiome(BiomeGenBase.taiga);
        this.removeBiome(BiomeGenBase.swampland);
        this.removeBiome(BiomeGenBase.jungle);

		if (BOPConfiguration.alpsGen == true)
		{
			this.addNewBiome(BOPBiomes.alps);
		}
		if (BOPConfiguration.arcticGen == true)
		{
			this.addNewBiome(BOPBiomes.arctic);
		}
		if (BOPConfiguration.badlandsGen == true)
		{
			this.addNewBiome(BOPBiomes.badlands);
		}
		if (BOPConfiguration.bambooForestGen == true)
		{
			this.addNewBiome(BOPBiomes.bambooForest);
		}
		if (BOPConfiguration.bayouGen == true)
		{
			this.addNewBiome(BOPBiomes.bayou);
		}
		if (BOPConfiguration.birchForestGen == true)
		{
			this.addNewBiome(BOPBiomes.birchForest);
		}
		if (BOPConfiguration.bogGen == true)
		{
			this.addNewBiome(BOPBiomes.bog);
		}
		if (BOPConfiguration.borealForestGen == true)
		{
			this.addNewBiome(BOPBiomes.borealForest);
		}
		if (BOPConfiguration.canyonGen == true)
		{
			this.addNewBiome(BOPBiomes.canyon);
		}
		if (BOPConfiguration.chaparralGen == true)
		{
			this.addNewBiome(BOPBiomes.chaparral);
		}
		if (BOPConfiguration.cherryBlossomGroveGen == true)
		{
			this.addNewBiome(BOPBiomes.cherryBlossomGrove);
		}
		if (BOPConfiguration.coniferousForestGen == true)
		{
			this.addNewBiome(BOPBiomes.coniferousForest);
		}
		if (BOPConfiguration.cragGen == true)
		{
			this.addNewBiome(BOPBiomes.crag);
		}
		if (BOPConfiguration.deadForestGen == true)
		{
			this.addNewBiome(BOPBiomes.deadForest);
		}
		if (BOPConfiguration.deadSwampGen == true)
		{
			this.addNewBiome(BOPBiomes.deadSwamp);
		}
		if (BOPConfiguration.deadlandsGen == true)
		{
			this.addNewBiome(BOPBiomes.deadlands);
		}
		if (BOPConfiguration.deciduousForestGen == true)
		{
			this.addNewBiome(BOPBiomes.deciduousForest);
		}
		if (BOPConfiguration.drylandsGen == true)
		{
			this.addNewBiome(BOPBiomes.drylands);
		}
		if (BOPConfiguration.dunesGen == true)
		{
			this.addNewBiome(BOPBiomes.dunes);
		}
		if (BOPConfiguration.fenGen == true)
		{
			this.addNewBiome(BOPBiomes.fen);
		}
		if (BOPConfiguration.fieldGen == true)
		{
			this.addNewBiome(BOPBiomes.field);
		}
		if (BOPConfiguration.frostForestGen == true)
		{
			this.addNewBiome(BOPBiomes.frostForest);
		}
		if (BOPConfiguration.fungiForestGen == true)
		{
			this.addNewBiome(BOPBiomes.fungiForest);
		}
		if (BOPConfiguration.gardenGen == true)
		{
			this.addNewBiome(BOPBiomes.garden);
		}
		if (BOPConfiguration.glacierGen == true)
		{
			this.addNewBiome(BOPBiomes.glacier);
		}
		if (BOPConfiguration.grasslandGen == true)
		{
			this.addNewBiome(BOPBiomes.grassland);
		}
		if (BOPConfiguration.groveGen == true)
		{
			this.addNewBiome(BOPBiomes.grove);
		}
		if (BOPConfiguration.heathlandGen == true)
		{
			this.addNewBiome(BOPBiomes.heathland);
		}
		if (BOPConfiguration.highlandGen == true)
		{
			this.addNewBiome(BOPBiomes.highland);
		}
		if (BOPConfiguration.iceSheetGen == true)
		{
			this.addNewBiome(BOPBiomes.iceSheet);
		}
		if (BOPConfiguration.icyHillsGen == true)
		{
			this.addNewBiome(BOPBiomes.icyHills);
		}
		if (BOPConfiguration.jadeCliffsGen == true)
		{
			this.addNewBiome(BOPBiomes.jadeCliffs);
		}
		if (BOPConfiguration.lushDesertGen == true)
		{
			this.addNewBiome(BOPBiomes.lushDesert);
		}
		if (BOPConfiguration.lushSwampGen == true)
		{
			this.addNewBiome(BOPBiomes.lushSwamp);
		}
		if (BOPConfiguration.mangroveGen == true)
		{
			this.addNewBiome(BOPBiomes.mangrove);
		}
		if (BOPConfiguration.mapleWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.mapleWoods);
		}
		if (BOPConfiguration.marshGen == true)
		{
			this.addNewBiome(BOPBiomes.marsh);
		}
		if (BOPConfiguration.meadowGen == true)
		{
			this.addNewBiome(BOPBiomes.meadow);
		}
		if (BOPConfiguration.mesaGen == true)
		{
			this.addNewBiome(BOPBiomes.mesa);
		}
		if (BOPConfiguration.moorGen == true)
		{
			this.addNewBiome(BOPBiomes.moor);
		}
		if (BOPConfiguration.mountainGen == true)
		{
			this.addNewBiome(BOPBiomes.mountain);
		}
		if (BOPConfiguration.mushroomIslandGen == true)
		{
			this.addNewBiome(BiomeGenBase.mushroomIsland);
		}
		if (BOPConfiguration.mysticGroveGen == true)
		{
			this.addNewBiome(BOPBiomes.mysticGrove);
		}
		if (BOPConfiguration.oasisGen == true)
		{
			this.addNewBiome(BOPBiomes.oasis);
		}
		if (BOPConfiguration.ominousWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.ominousWoods);
		}
		if (BOPConfiguration.orchardGen == true)
		{
			this.addNewBiome(BOPBiomes.orchard);
		}
		if (BOPConfiguration.originValleyGen == true)
		{
			this.addNewBiome(BOPBiomes.originValley);
		}
		if (BOPConfiguration.outbackGen == true)
		{
			this.addNewBiome(BOPBiomes.outback);
		}
		if (BOPConfiguration.pastureGen == true)
		{
			this.addNewBiome(BOPBiomes.pasture);
		}
		if (BOPConfiguration.prairieGen == true)
		{
			this.addNewBiome(BOPBiomes.prairie);
		}
		if (BOPConfiguration.quagmireGen == true)
		{
			this.addNewBiome(BOPBiomes.quagmire);
		}
		if (BOPConfiguration.rainforestGen == true)
		{
			this.addNewBiome(BOPBiomes.rainforest);
		}
		if (BOPConfiguration.redwoodForestGen == true)
		{
			this.addNewBiome(BOPBiomes.redwoodForest);
		}
		if (BOPConfiguration.sacredSpringsGen == true)
		{
			this.addNewBiome(BOPBiomes.sacredSprings);
		}
		if (BOPConfiguration.savannaGen == true)
		{
			this.addNewBiome(BOPBiomes.savanna);
		}
		if (BOPConfiguration.scrublandGen == true)
		{
			this.addNewBiome(BOPBiomes.scrubland);
		}
		if (BOPConfiguration.seasonalForestGen == true)
		{
			this.addNewBiome(BOPBiomes.seasonalForest);
		}
		if (BOPConfiguration.shieldGen == true)
		{
			this.addNewBiome(BOPBiomes.shield);
		}
		if (BOPConfiguration.shrublandGen == true)
		{
			this.addNewBiome(BOPBiomes.shrubland);
		}
		if (BOPConfiguration.snowyWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.snowyWoods);
		}
		if (BOPConfiguration.spruceWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.spruceWoods);
		}
		if (BOPConfiguration.steppeGen == true)
		{
			this.addNewBiome(BOPBiomes.steppe);
		}
		if (BOPConfiguration.swampwoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.swampwoods);
		}
		if (BOPConfiguration.temperateRainforestGen == true)
		{
			this.addNewBiome(BOPBiomes.temperateRainforest);
		}
		if (BOPConfiguration.thicketGen == true)
		{
			this.addNewBiome(BOPBiomes.thicket);
		}
		if (BOPConfiguration.tropicalRainforestGen == true)
		{
			this.addNewBiome(BOPBiomes.tropicalRainforest);
		}
		if (BOPConfiguration.tropicsGen == true)
		{
			this.addNewBiome(BOPBiomes.tropics);
		}
		if (BOPConfiguration.tundraGen == true)
		{
			this.addNewBiome(BOPBiomes.tundra);
		}
		if (BOPConfiguration.volcanoGen == true)
		{
			this.addNewBiome(BOPBiomes.volcano);
		}
		if (BOPConfiguration.wastelandGen == true)
		{
			this.addNewBiome(BOPBiomes.wasteland);
		}
		if (BOPConfiguration.wetlandGen == true)
		{
			this.addNewBiome(BOPBiomes.wetland);
		}
		if (BOPConfiguration.woodlandGen == true)
		{
			this.addNewBiome(BOPBiomes.woodland);
		}
		
		
		if (BOPConfiguration.plainsGen == true)
		{
			if (BOPConfiguration.vanillaEnhanced == true)
				{
				this.addNewBiome(BOPBiomes.plainsNew);
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
				this.addNewBiome(BOPBiomes.desertNew);
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
				this.addNewBiome(BOPBiomes.extremeHillsNew);
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
				this.addNewBiome(BOPBiomes.forestNew);
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
				this.addNewBiome(BOPBiomes.taigaNew);
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
				this.addNewBiome(BOPBiomes.swamplandNew);
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
				this.addNewBiome(BOPBiomes.jungleNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.jungle);
				}
		}
	}
}
