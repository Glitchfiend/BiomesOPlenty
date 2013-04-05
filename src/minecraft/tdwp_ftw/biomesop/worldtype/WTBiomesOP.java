package tdwp_ftw.biomesop.worldtype;

import net.minecraft.world.biome.BiomeGenBase;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBiomes;

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
			this.addNewBiome(BOPBiomes.alps);
		}
		if (mod_BiomesOPlenty.arcticGen == true)
		{
			this.addNewBiome(BOPBiomes.arctic);
		}
		if (mod_BiomesOPlenty.badlandsGen == true)
		{
			this.addNewBiome(BOPBiomes.badlands);
		}
		if (mod_BiomesOPlenty.bambooForestGen == true)
		{
			this.addNewBiome(BOPBiomes.bambooForest);
		}
		if (mod_BiomesOPlenty.bayouGen == true)
		{
			this.addNewBiome(BOPBiomes.bayou);
		}
		if (mod_BiomesOPlenty.birchForestGen == true)
		{
			this.addNewBiome(BOPBiomes.birchForest);
		}
		if (mod_BiomesOPlenty.bogGen == true)
		{
			this.addNewBiome(BOPBiomes.bog);
		}
		if (mod_BiomesOPlenty.borealForestGen == true)
		{
			this.addNewBiome(BOPBiomes.borealForest);
		}
		if (mod_BiomesOPlenty.canyonGen == true)
		{
			this.addNewBiome(BOPBiomes.canyon);
		}
		if (mod_BiomesOPlenty.chaparralGen == true)
		{
			this.addNewBiome(BOPBiomes.chaparral);
		}
		if (mod_BiomesOPlenty.cherryBlossomGroveGen == true)
		{
			this.addNewBiome(BOPBiomes.cherryBlossomGrove);
		}
		if (mod_BiomesOPlenty.coniferousForestGen == true)
		{
			this.addNewBiome(BOPBiomes.coniferousForest);
		}
		if (mod_BiomesOPlenty.cragGen == true)
		{
			this.addNewBiome(BOPBiomes.crag);
		}
		if (mod_BiomesOPlenty.deadForestGen == true)
		{
			this.addNewBiome(BOPBiomes.deadForest);
		}
		if (mod_BiomesOPlenty.deadSwampGen == true)
		{
			this.addNewBiome(BOPBiomes.deadSwamp);
		}
		if (mod_BiomesOPlenty.deadlandsGen == true)
		{
			this.addNewBiome(BOPBiomes.deadlands);
		}
		if (mod_BiomesOPlenty.deciduousForestGen == true)
		{
			this.addNewBiome(BOPBiomes.deciduousForest);
		}
		if (mod_BiomesOPlenty.drylandsGen == true)
		{
			this.addNewBiome(BOPBiomes.drylands);
		}
		if (mod_BiomesOPlenty.dunesGen == true)
		{
			this.addNewBiome(BOPBiomes.dunes);
		}
		if (mod_BiomesOPlenty.fenGen == true)
		{
			this.addNewBiome(BOPBiomes.fen);
		}
		if (mod_BiomesOPlenty.fieldGen == true)
		{
			this.addNewBiome(BOPBiomes.field);
		}
		if (mod_BiomesOPlenty.frostForestGen == true)
		{
			this.addNewBiome(BOPBiomes.frostForest);
		}
		if (mod_BiomesOPlenty.fungiForestGen == true)
		{
			this.addNewBiome(BOPBiomes.fungiForest);
		}
		if (mod_BiomesOPlenty.gardenGen == true)
		{
			this.addNewBiome(BOPBiomes.garden);
		}
		if (mod_BiomesOPlenty.glacierGen == true)
		{
			this.addNewBiome(BOPBiomes.glacier);
		}
		if (mod_BiomesOPlenty.grasslandGen == true)
		{
			this.addNewBiome(BOPBiomes.grassland);
		}
		if (mod_BiomesOPlenty.groveGen == true)
		{
			this.addNewBiome(BOPBiomes.grove);
		}
		if (mod_BiomesOPlenty.heathlandGen == true)
		{
			this.addNewBiome(BOPBiomes.heathland);
		}
		if (mod_BiomesOPlenty.highlandGen == true)
		{
			this.addNewBiome(BOPBiomes.highland);
		}
		if (mod_BiomesOPlenty.iceSheetGen == true)
		{
			this.addNewBiome(BOPBiomes.iceSheet);
		}
		if (mod_BiomesOPlenty.icyHillsGen == true)
		{
			this.addNewBiome(BOPBiomes.icyHills);
		}
		if (mod_BiomesOPlenty.jadeCliffsGen == true)
		{
			this.addNewBiome(BOPBiomes.jadeCliffs);
		}
		if (mod_BiomesOPlenty.lushDesertGen == true)
		{
			this.addNewBiome(BOPBiomes.lushDesert);
		}
		if (mod_BiomesOPlenty.lushSwampGen == true)
		{
			this.addNewBiome(BOPBiomes.lushSwamp);
		}
		if (mod_BiomesOPlenty.mangroveGen == true)
		{
			this.addNewBiome(BOPBiomes.mangrove);
		}
		if (mod_BiomesOPlenty.mapleWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.mapleWoods);
		}
		if (mod_BiomesOPlenty.marshGen == true)
		{
			this.addNewBiome(BOPBiomes.marsh);
		}
		if (mod_BiomesOPlenty.meadowGen == true)
		{
			this.addNewBiome(BOPBiomes.meadow);
		}
		if (mod_BiomesOPlenty.mesaGen == true)
		{
			this.addNewBiome(BOPBiomes.mesa);
		}
		if (mod_BiomesOPlenty.moorGen == true)
		{
			this.addNewBiome(BOPBiomes.moor);
		}
		if (mod_BiomesOPlenty.mountainGen == true)
		{
			this.addNewBiome(BOPBiomes.mountain);
		}
		if (mod_BiomesOPlenty.mushroomIslandGen == true)
		{
			this.addNewBiome(BiomeGenBase.mushroomIsland);
		}
		if (mod_BiomesOPlenty.mysticGroveGen == true)
		{
			this.addNewBiome(BOPBiomes.mysticGrove);
		}
		if (mod_BiomesOPlenty.oasisGen == true)
		{
			this.addNewBiome(BOPBiomes.oasis);
		}
		if (mod_BiomesOPlenty.ominousWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.ominousWoods);
		}
		if (mod_BiomesOPlenty.orchardGen == true)
		{
			this.addNewBiome(BOPBiomes.orchard);
		}
		if (mod_BiomesOPlenty.originValleyGen == true)
		{
			this.addNewBiome(BOPBiomes.originValley);
		}
		if (mod_BiomesOPlenty.outbackGen == true)
		{
			this.addNewBiome(BOPBiomes.outback);
		}
		if (mod_BiomesOPlenty.pastureGen == true)
		{
			this.addNewBiome(BOPBiomes.pasture);
		}
		if (mod_BiomesOPlenty.prairieGen == true)
		{
			this.addNewBiome(BOPBiomes.prairie);
		}
		if (mod_BiomesOPlenty.quagmireGen == true)
		{
			this.addNewBiome(BOPBiomes.quagmire);
		}
		if (mod_BiomesOPlenty.rainforestGen == true)
		{
			this.addNewBiome(BOPBiomes.rainforest);
		}
		if (mod_BiomesOPlenty.redwoodForestGen == true)
		{
			this.addNewBiome(BOPBiomes.redwoodForest);
		}
		if (mod_BiomesOPlenty.sacredSpringsGen == true)
		{
			this.addNewBiome(BOPBiomes.sacredSprings);
		}
		if (mod_BiomesOPlenty.savannaGen == true)
		{
			this.addNewBiome(BOPBiomes.savanna);
		}
		if (mod_BiomesOPlenty.scrublandGen == true)
		{
			this.addNewBiome(BOPBiomes.scrubland);
		}
		if (mod_BiomesOPlenty.seasonalForestGen == true)
		{
			this.addNewBiome(BOPBiomes.seasonalForest);
		}
		if (mod_BiomesOPlenty.shieldGen == true)
		{
			this.addNewBiome(BOPBiomes.shield);
		}
		if (mod_BiomesOPlenty.shrublandGen == true)
		{
			this.addNewBiome(BOPBiomes.shrubland);
		}
		if (mod_BiomesOPlenty.snowyWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.snowyWoods);
		}
		if (mod_BiomesOPlenty.spruceWoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.spruceWoods);
		}
		if (mod_BiomesOPlenty.steppeGen == true)
		{
			this.addNewBiome(BOPBiomes.steppe);
		}
		if (mod_BiomesOPlenty.swampwoodsGen == true)
		{
			this.addNewBiome(BOPBiomes.swampwoods);
		}
		if (mod_BiomesOPlenty.temperateRainforestGen == true)
		{
			this.addNewBiome(BOPBiomes.temperateRainforest);
		}
		if (mod_BiomesOPlenty.thicketGen == true)
		{
			this.addNewBiome(BOPBiomes.thicket);
		}
		if (mod_BiomesOPlenty.tropicalRainforestGen == true)
		{
			this.addNewBiome(BOPBiomes.tropicalRainforest);
		}
		if (mod_BiomesOPlenty.tropicsGen == true)
		{
			this.addNewBiome(BOPBiomes.tropics);
		}
		if (mod_BiomesOPlenty.tundraGen == true)
		{
			this.addNewBiome(BOPBiomes.tundra);
		}
		if (mod_BiomesOPlenty.volcanoGen == true)
		{
			this.addNewBiome(BOPBiomes.volcano);
		}
		if (mod_BiomesOPlenty.wastelandGen == true)
		{
			this.addNewBiome(BOPBiomes.wasteland);
		}
		if (mod_BiomesOPlenty.wetlandGen == true)
		{
			this.addNewBiome(BOPBiomes.wetland);
		}
		if (mod_BiomesOPlenty.woodlandGen == true)
		{
			this.addNewBiome(BOPBiomes.woodland);
		}
		
		
		if (mod_BiomesOPlenty.plainsGen == true)
		{
			if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
				this.addNewBiome(BOPBiomes.plainsNew);
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
				this.addNewBiome(BOPBiomes.desertNew);
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
				this.addNewBiome(BOPBiomes.extremeHillsNew);
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
				this.addNewBiome(BOPBiomes.forestNew);
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
				this.addNewBiome(BOPBiomes.taigaNew);
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
				this.addNewBiome(BOPBiomes.swamplandNew);
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
				this.addNewBiome(BOPBiomes.jungleNew);
				}
			else
				{
				this.addNewBiome(BiomeGenBase.jungle);
				}
		}
	}
}
