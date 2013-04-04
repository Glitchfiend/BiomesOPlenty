package tdwp_ftw.biomesop.worldtype;

import net.minecraft.world.biome.BiomeGenBase;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;

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

		if (mod_BiomesOPlenty.alpsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.alps);
		}
		if (mod_BiomesOPlenty.arcticGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.arctic);
		}
		if (mod_BiomesOPlenty.badlandsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.badlands);
		}
		if (mod_BiomesOPlenty.bambooForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.bambooForest);
		}
		if (mod_BiomesOPlenty.bayouGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.bayou);
		}
		if (mod_BiomesOPlenty.birchForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.birchForest);
		}
		if (mod_BiomesOPlenty.bogGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.bog);
		}
		if (mod_BiomesOPlenty.borealForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.borealForest);
		}
		if (mod_BiomesOPlenty.canyonGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.canyon);
		}
		if (mod_BiomesOPlenty.chaparralGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.chaparral);
		}
		if (mod_BiomesOPlenty.cherryBlossomGroveGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.cherryBlossomGrove);
		}
		if (mod_BiomesOPlenty.coniferousForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.coniferousForest);
		}
		if (mod_BiomesOPlenty.cragGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.crag);
		}
		if (mod_BiomesOPlenty.deadForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.deadForest);
		}
		if (mod_BiomesOPlenty.deadSwampGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.deadSwamp);
		}
		if (mod_BiomesOPlenty.deadlandsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.deadlands);
		}
		if (mod_BiomesOPlenty.deciduousForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.deciduousForest);
		}
		if (mod_BiomesOPlenty.drylandsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.drylands);
		}
		if (mod_BiomesOPlenty.dunesGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.dunes);
		}
		if (mod_BiomesOPlenty.fenGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.fen);
		}
		if (mod_BiomesOPlenty.fieldGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.field);
		}
		if (mod_BiomesOPlenty.frostForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.frostForest);
		}
		if (mod_BiomesOPlenty.fungiForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.fungiForest);
		}
		if (mod_BiomesOPlenty.gardenGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.garden);
		}
		if (mod_BiomesOPlenty.glacierGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.glacier);
		}
		if (mod_BiomesOPlenty.grasslandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.grassland);
		}
		if (mod_BiomesOPlenty.groveGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.grove);
		}
		if (mod_BiomesOPlenty.heathlandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.heathland);
		}
		if (mod_BiomesOPlenty.highlandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.highland);
		}
		if (mod_BiomesOPlenty.iceSheetGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.iceSheet);
		}
		if (mod_BiomesOPlenty.icyHillsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.icyHills);
		}
		if (mod_BiomesOPlenty.jadeCliffsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.jadeCliffs);
		}
		if (mod_BiomesOPlenty.lushDesertGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.lushDesert);
		}
		if (mod_BiomesOPlenty.lushSwampGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.lushSwamp);
		}
		if (mod_BiomesOPlenty.mangroveGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.mangrove);
		}
		if (mod_BiomesOPlenty.mapleWoodsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.mapleWoods);
		}
		if (mod_BiomesOPlenty.marshGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.marsh);
		}
		if (mod_BiomesOPlenty.meadowGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.meadow);
		}
		if (mod_BiomesOPlenty.mesaGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.mesa);
		}
		if (mod_BiomesOPlenty.moorGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.moor);
		}
		if (mod_BiomesOPlenty.mountainGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.mountain);
		}
		if (mod_BiomesOPlenty.mushroomIslandGen == true)
		{
			this.addNewBiome(BiomeGenBase.mushroomIsland);
		}
		if (mod_BiomesOPlenty.mysticGroveGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.mysticGrove);
		}
		if (mod_BiomesOPlenty.oasisGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.oasis);
		}
		if (mod_BiomesOPlenty.ominousWoodsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.ominousWoods);
		}
		if (mod_BiomesOPlenty.orchardGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.orchard);
		}
		if (mod_BiomesOPlenty.originValleyGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.originValley);
		}
		if (mod_BiomesOPlenty.outbackGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.outback);
		}
		if (mod_BiomesOPlenty.pastureGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.pasture);
		}
		if (mod_BiomesOPlenty.prairieGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.prairie);
		}
		if (mod_BiomesOPlenty.quagmireGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.quagmire);
		}
		if (mod_BiomesOPlenty.rainforestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.rainforest);
		}
		if (mod_BiomesOPlenty.redwoodForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.redwoodForest);
		}
		if (mod_BiomesOPlenty.sacredSpringsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.sacredSprings);
		}
		if (mod_BiomesOPlenty.savannaGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.savanna);
		}
		if (mod_BiomesOPlenty.scrublandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.scrubland);
		}
		if (mod_BiomesOPlenty.seasonalForestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.seasonalForest);
		}
		if (mod_BiomesOPlenty.shieldGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.shield);
		}
		if (mod_BiomesOPlenty.shrublandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.shrubland);
		}
		if (mod_BiomesOPlenty.snowyWoodsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.snowyWoods);
		}
		if (mod_BiomesOPlenty.spruceWoodsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.spruceWoods);
		}
		if (mod_BiomesOPlenty.steppeGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.steppe);
		}
		if (mod_BiomesOPlenty.swampwoodsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.swampwoods);
		}
		if (mod_BiomesOPlenty.temperateRainforestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.temperateRainforest);
		}
		if (mod_BiomesOPlenty.thicketGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.thicket);
		}
		if (mod_BiomesOPlenty.tropicalRainforestGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.tropicalRainforest);
		}
		if (mod_BiomesOPlenty.tropicsGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.tropics);
		}
		if (mod_BiomesOPlenty.tundraGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.tundra);
		}
		if (mod_BiomesOPlenty.volcanoGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.volcano);
		}
		if (mod_BiomesOPlenty.wastelandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.wasteland);
		}
		if (mod_BiomesOPlenty.wetlandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.wetland);
		}
		if (mod_BiomesOPlenty.woodlandGen == true)
		{
			this.addNewBiome(mod_BiomesOPlenty.woodland);
		}
		
		
		if (mod_BiomesOPlenty.plainsGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(mod_BiomesOPlenty.plainsNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.plains);
				}
		}
		if (mod_BiomesOPlenty.desertGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(mod_BiomesOPlenty.desertNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.desert);
				}
		}
		if (mod_BiomesOPlenty.extremeHillsGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(mod_BiomesOPlenty.extremeHillsNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.extremeHills);
				}
		}
		if (mod_BiomesOPlenty.forestGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(mod_BiomesOPlenty.forestNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.forest);
				}
		}
		if (mod_BiomesOPlenty.taigaGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(mod_BiomesOPlenty.taigaNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.taiga);
				}
		}
		if (mod_BiomesOPlenty.swamplandGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(mod_BiomesOPlenty.swamplandNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.swampland);
				}
		}
		if (mod_BiomesOPlenty.jungleGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(mod_BiomesOPlenty.jungleNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.jungle);
				}
		}
	}
}
