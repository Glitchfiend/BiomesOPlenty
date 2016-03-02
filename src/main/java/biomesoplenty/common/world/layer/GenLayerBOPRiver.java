package biomesoplenty.common.world.layer;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBOPRiver extends BOPGenLayer
{
    public GenLayerBOPRiver(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int areaX_ = areaX - 1;
        int areaY_ = areaY - 1;
        int areaWidth_ = areaWidth + 2;
        int areaHeight_ = areaHeight + 2;
        int[] parentVals = this.parent.getInts(areaX_, areaY_, areaWidth_, areaHeight_);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                int westVal = this.mod2(parentVals[x + 0 + (y + 1) * areaWidth_]);
                int eastVal = this.mod2(parentVals[x + 2 + (y + 1) * areaWidth_]);
                int northVal = this.mod2(parentVals[x + 1 + (y + 0) * areaWidth_]);
                int southVal = this.mod2(parentVals[x + 1 + (y + 2) * areaWidth_]);
                int val = this.mod2(parentVals[x + 1 + (y + 1) * areaWidth_]);

                if (val == westVal && val == northVal && val == eastVal && val == southVal)
                {
                    out[x + y * areaWidth] = -1;
                }
                else
                {
                    out[x + y * areaWidth] = BiomeGenBase.getIdForBiome(Biomes.river);
                }
            }
        }

        return out;
    }

    private int mod2(int a)
    {
        return a >= 2 ? 2 + (a & 1) : a;
    }
}