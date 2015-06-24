package biomesoplenty.common.world.layer;

import biomesoplenty.common.world.SimplexNoise;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRainfallNoise extends BOPGenLayer {

    private final double xOffset;
    private final double yOffset;
    private final double scale;
    
    public GenLayerRainfallNoise(long seed, long worldSeed, double scale) {
        super(seed);
        this.xOffset = (worldSeed >> 32) * 0.000003D;
        this.yOffset = (worldSeed >> 32) * 0.000004D;
        this.scale = scale;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);
        
        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {                
                this.initChunkSeed((long)(areaX + x), (long)(areaY + y));
                
                double noiseVal = SimplexNoise.noise((areaX + x + this.xOffset) * this.scale, (areaY + y + this.yOffset) * this.scale);

                // boundaries were determined empirically by analyzing statistically output from the SimplexNoise function, and splitting into 12 equally likely groups
                if (noiseVal < -0.637D) {out[x + y * areaWidth] = 0;}
                else if (noiseVal < -0.575D) {out[x + y * areaWidth] = 1;}
                else if (noiseVal < -0.465D) {out[x + y * areaWidth] = 2;}
                else if (noiseVal < -0.295D) {out[x + y * areaWidth] = 3;}
                else if (noiseVal < -0.148D) {out[x + y * areaWidth] = 4;}
                else if (noiseVal < -0.034D) {out[x + y * areaWidth] = 5;}
                else if (noiseVal < 0.132D) {out[x + y * areaWidth] = 6;}
                else if (noiseVal < 0.246D) {out[x + y * areaWidth] = 7;}
                else if (noiseVal < 0.400D) {out[x + y * areaWidth] = 8;}
                else if (noiseVal < 0.551D) {out[x + y * areaWidth] = 9;}
                else if (noiseVal < 0.634D) {out[x + y * areaWidth] = 10;}
                else {out[x + y * areaWidth] = 11;}
                
            }
        }
        
        return out;
    }

}
