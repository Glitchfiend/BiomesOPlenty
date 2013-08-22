package biomesoplenty.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.BOPConfiguration;

public class BiomeLayerSub extends BiomeLayer
{
    public BiomeLayerSub(long par1, GenLayer par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
    }

    @Override
	public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; ++i1)
        {
            for (int j1 = 0; j1 < par3; ++j1)
            {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];

                int l1 = k1;
                
                //New biome gen test
                if (BOPConfiguration.TerrainGen.fancyGen)
                {
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.birchForest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.woodland.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.spruceWoods.get().biomeID; }
	                
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.coniferousForest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.temperateRainforest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.redwoodForest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.mountain.get().biomeID; }
	                
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.mapleWoods.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.seasonalForest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.borealForest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.deciduousForest.get().biomeID; }
	                
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.deadForest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.grove.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.timber.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.thicket.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.shield.get().biomeID; }
	                
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(10) == 0) { l1 = Biomes.fungiForest.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(10) == 0) { l1 = Biomes.cherryBlossomGrove.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(10) == 0) { l1 = Biomes.mysticGrove.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(10) == 0) { l1 = Biomes.hotSprings.get().biomeID; }
	                if (k1 == Biomes.forestNew.get().biomeID && nextInt(10) == 0) { l1 = Biomes.originValley.get().biomeID; }
	                
	                //
	                
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(2) == 0) { l1 = Biomes.marsh.get().biomeID; }
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(2) == 0) { l1 = Biomes.lushSwamp.get().biomeID; }
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(2) == 0) { l1 = Biomes.bayou.get().biomeID; }
	                
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(4) == 0) { l1 = Biomes.fen.get().biomeID; }
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(4) == 0) { l1 = Biomes.bog.get().biomeID; }
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(4) == 0) { l1 = Biomes.swamplandNew.get().biomeID; }
	                
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(6) == 0) { l1 = Biomes.moor.get().biomeID; }
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(6) == 0) { l1 = Biomes.deadSwamp.get().biomeID; }
	                
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(8) == 0) { l1 = Biomes.quagmire.get().biomeID; }
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(8) == 0) { l1 = Biomes.sludgepit.get().biomeID; }
	                
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(10) == 0) { l1 = Biomes.ominousWoods.get().biomeID; }
	                if (k1 == Biomes.wetland.get().biomeID && nextInt(10) == 0) { l1 = Biomes.silkglades.get().biomeID; }
	                
	                //
	                
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.dunes.get().biomeID; }
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.canyon.get().biomeID; }
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.mesa.get().biomeID; }
	                
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.outback.get().biomeID; }
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.steppe.get().biomeID; }
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.scrubland.get().biomeID; }
	                
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.lushDesert.get().biomeID; }
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.savanna.get().biomeID; }
	                
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.brushland.get().biomeID; }
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.heathland.get().biomeID; }
	                
	                if (k1 == Biomes.desertNew.get().biomeID && nextInt(10) == 0) { l1 = Biomes.oasis.get().biomeID; }
	                
	                //
	                
	                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.tropicalRainforest.get().biomeID; }
	                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(2) == 0) { l1 = Biomes.rainforest.get().biomeID; }
	                
	                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(4) == 0) { l1 = Biomes.tropics.get().biomeID; }
	                
	                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.bambooForest.get().biomeID; }
	                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(6) == 0) { l1 = Biomes.jadeCliffs.get().biomeID; }
	                
	                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(8) == 0) { l1 = Biomes.mangrove.get().biomeID; }
	                
	                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(10) == 0) { l1 = Biomes.sacredSprings.get().biomeID; }
	                
	                //
	                
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(2) == 0) { l1 = Biomes.plainsNew.get().biomeID; }
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(2) == 0) { l1 = Biomes.chaparral.get().biomeID; }
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(2) == 0) { l1 = Biomes.prairie.get().biomeID; }
	                
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(4) == 0) { l1 = Biomes.field.get().biomeID; }
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(4) == 0) { l1 = Biomes.grassland.get().biomeID; }
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(4) == 0) { l1 = Biomes.highland.get().biomeID; }
	                
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(6) == 0) { l1 = Biomes.pasture.get().biomeID; }
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(6) == 0) { l1 = Biomes.meadow.get().biomeID; }
	                
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(8) == 0) { l1 = Biomes.orchard.get().biomeID; }
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(8) == 0) { l1 = Biomes.overgrownGreens.get().biomeID; }
	                
	                if (k1 == Biomes.shrubland.get().biomeID && nextInt(10) == 0) { l1 = Biomes.garden.get().biomeID; }
	                
	                //
	                
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(2) == 0) { l1 = Biomes.taigaNew.get().biomeID; }
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(2) == 0) { l1 = Biomes.frostForest.get().biomeID; }
	                
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(4) == 0) { l1 = Biomes.deadForestSnow.get().biomeID; }
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(4) == 0) { l1 = Biomes.coniferousForestSnow.get().biomeID; }
	                
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(6) == 0) { l1 = Biomes.alps.get().biomeID; }
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(6) == 0) { l1 = BiomeGenBase.icePlains.biomeID; }
	                
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(8) == 0) { l1 = Biomes.polar.get().biomeID; }
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(8) == 0) { l1 = Biomes.glacier.get().biomeID; }
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(8) == 0) { l1 = Biomes.arctic.get().biomeID; }
	                
	                if (k1 == Biomes.tundra.get().biomeID && nextInt(10) == 0) { l1 = Biomes.icyHills.get().biomeID; }
	                
	                //
	                
	                if (k1 == Biomes.badlands.get().biomeID && nextInt(2) == 0) { l1 = Biomes.crag.get().biomeID; }
	                if (k1 == Biomes.badlands.get().biomeID && nextInt(2) == 0) { l1 = Biomes.volcano.get().biomeID; }
	                
	                if (k1 == Biomes.badlands.get().biomeID && nextInt(6) == 0) { l1 = Biomes.wasteland.get().biomeID; }
	                
	                if (k1 == Biomes.badlands.get().biomeID && nextInt(10) == 0) { l1 = Biomes.deadlands.get().biomeID; }
                }
                
                //
                
                //LIST
                if (k1 == Biomes.meadow.get().biomeID && nextInt(2) == 0) { l1 = Biomes.meadowForest.get().biomeID; }
                if (k1 == Biomes.canyon.get().biomeID && nextInt(2) == 0) { l1 = Biomes.canyonRavine.get().biomeID; }
                if (k1 == Biomes.shrubland.get().biomeID && nextInt(3) == 0) { l1 = Biomes.shrublandForest.get().biomeID; }
                if (k1 == Biomes.ominousWoods.get().biomeID && nextInt(3) == 0) { l1 = Biomes.ominousWoodsThick.get().biomeID; }
                if (k1 == Biomes.pasture.get().biomeID && nextInt(2) == 0) { l1 = Biomes.pastureMeadow.get().biomeID; }
                if (k1 == Biomes.pasture.get().biomeID && nextInt(3) == 0) { l1 = Biomes.pastureThin.get().biomeID; }
                if (k1 == Biomes.timber.get().biomeID && nextInt(3) == 0) { l1 = Biomes.timberThin.get().biomeID; }
                if (k1 == Biomes.alps.get().biomeID && nextInt(2) == 0) { l1 = Biomes.alpsForest.get().biomeID; }
                if (k1 == Biomes.alps.get().biomeID && nextInt(3) == 0) { l1 = Biomes.alpsBase.get().biomeID; }
                if (k1 == Biomes.seasonalForest.get().biomeID && nextInt(2) == 0) { l1 = Biomes.seasonalSpruceForest.get().biomeID; }
                if (k1 == Biomes.field.get().biomeID && nextInt(2) == 0) { l1 = Biomes.fieldForest.get().biomeID; }
                if (k1 == Biomes.savanna.get().biomeID && nextInt(4) == 0) { l1 = Biomes.savannaPlateau.get().biomeID; }
                
                if (k1 == Biomes.forestNew.get().biomeID && nextInt(3) == 0) { l1 = Biomes.forestHillsNew.get().biomeID; }
                if (k1 == Biomes.taigaNew.get().biomeID && nextInt(3) == 0) { l1 = Biomes.taigaHillsNew.get().biomeID; }
                if (k1 == Biomes.jungleNew.get().biomeID && nextInt(3) == 0) { l1 = Biomes.jungleHillsNew.get().biomeID; }
                
                //if (k1 == BiomeGenBase.ocean.biomeID && nextInt(2) == 0) { l1 = Biomes.tropics.get().biomeID; }
                //if (k1 == BiomeGenBase.ocean.biomeID && nextInt(3) == 0) { l1 = Biomes.volcano.get().biomeID; }
                //if (k1 == BiomeGenBase.ocean.biomeID && nextInt(3) == 0) { l1 = Biomes.polar.get().biomeID; }

                if (l1 == k1)
                {
                    aint1[j1 + i1 * par3] = k1;
                }
                else
                {
                    int i2 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    int j2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    int k2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    int l2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (i2 == k1 && j2 == k1 && k2 == k1 && l2 == k1)
                    {
                        aint1[j1 + i1 * par3] = l1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = k1;
                    }
                }
            }
        }

        return aint1;
    }
}
