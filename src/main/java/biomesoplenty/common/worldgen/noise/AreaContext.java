/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.noise;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import net.minecraft.util.LinearCongruentialGenerator;

public class AreaContext
{
    private static final int MAX_CACHE = 1024;
    private final int maxCache;
    private final long seed;
    private long rval;

    public AreaContext(int maxCache, long worldSeed, long seedModifier) {
        this.seed = mixSeed(worldSeed, seedModifier);
        this.maxCache = maxCache;
    }

    public Area createResult(PixelTransformer transformer)
    {
        return new Area(transformer, this.maxCache);
    }

    public Area createResult(PixelTransformer p_76541_, Area p_76542_)
    {
        return new Area(p_76541_, Math.min(MAX_CACHE, p_76542_.getMaxCache() * 4));
    }

    public Area createResult(PixelTransformer transformer, Area p_76545_, Area p_76546_) {
        return new Area(transformer, Math.min(MAX_CACHE, Math.max(p_76545_.getMaxCache(), p_76546_.getMaxCache()) * 4));
    }

    public void initRandom(long x, long y)
    {
        long i = this.seed;
        i = LinearCongruentialGenerator.next(i, x);
        i = LinearCongruentialGenerator.next(i, y);
        i = LinearCongruentialGenerator.next(i, x);
        i = LinearCongruentialGenerator.next(i, y);
        this.rval = i;
    }

    public int nextRandom(int bound)
    {
        int i = Math.floorMod(this.rval >> 24, bound);
        this.rval = LinearCongruentialGenerator.next(this.rval, this.seed);
        return i;
    }

    public int random(int a, int b)
    {
        return this.nextRandom(2) == 0 ? a : b;
    }

    public int random(int a, int b, int c, int d)
    {
        int i = this.nextRandom(4);
        if (i == 0) {
            return a;
        } else if (i == 1) {
            return b;
        } else {
            return i == 2 ? c : d;
        }
    }

    private static long mixSeed(long worldSeed, long seedModifier)
    {
        long i = LinearCongruentialGenerator.next(seedModifier, seedModifier);
        i = LinearCongruentialGenerator.next(i, seedModifier);
        i = LinearCongruentialGenerator.next(i, seedModifier);
        long j = LinearCongruentialGenerator.next(worldSeed, i);
        j = LinearCongruentialGenerator.next(j, i);
        return LinearCongruentialGenerator.next(j, i);
    }
}
