package biomesoplenty.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.Biomes;

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
