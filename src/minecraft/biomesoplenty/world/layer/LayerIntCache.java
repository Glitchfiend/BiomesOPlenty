package biomesoplenty.world.layer;

import java.util.ArrayList;
import java.util.List;

public class LayerIntCache
{
    private static int intCacheSize = 256;

    /**
     * A list of pre-allocated int[256] arrays that are currently unused and can be returned by getIntCache()
     */
    private static List freeSmallArrays = new ArrayList();

    /**
     * A list of pre-allocated int[256] arrays that were previously returned by getIntCache() and which will not be re-
     * used again until resetIntCache() is called.
     */
    private static List inUseSmallArrays = new ArrayList();

    /**
     * A list of pre-allocated int[cacheSize] arrays that are currently unused and can be returned by getIntCache()
     */
    private static List freeLargeArrays = new ArrayList();

    /**
     * A list of pre-allocated int[cacheSize] arrays that were previously returned by getIntCache() and which will not
     * be re-used again until resetIntCache() is called.
     */
    private static List inUseLargeArrays = new ArrayList();

    public static synchronized int[] getIntCache(int par0)
    {
        int[] aint;

        if (par0 <= 256)
        {
            if (freeSmallArrays.isEmpty())
            {
                aint = new int[256];
                inUseSmallArrays.add(aint);
                return aint;
            }
            else
            {
                aint = (int[])freeSmallArrays.remove(freeSmallArrays.size() - 1);
                inUseSmallArrays.add(aint);
                return aint;
            }
        }
        else if (par0 > intCacheSize)
        {
            intCacheSize = par0;
            freeLargeArrays.clear();
            inUseLargeArrays.clear();
            aint = new int[intCacheSize];
            inUseLargeArrays.add(aint);
            return aint;
        }
        else if (freeLargeArrays.isEmpty())
        {
            aint = new int[intCacheSize];
            inUseLargeArrays.add(aint);
            return aint;
        }
        else
        {
            aint = (int[])freeLargeArrays.remove(freeLargeArrays.size() - 1);
            inUseLargeArrays.add(aint);
            return aint;
        }
    }

    /**
     * Mark all pre-allocated arrays as available for re-use by moving them to the appropriate free lists.
     */
    public static synchronized void resetIntCache()
    {
        if (!freeLargeArrays.isEmpty())
        {
            freeLargeArrays.remove(freeLargeArrays.size() - 1);
        }

        if (!freeSmallArrays.isEmpty())
        {
            freeSmallArrays.remove(freeSmallArrays.size() - 1);
        }

        freeLargeArrays.addAll(inUseLargeArrays);
        freeSmallArrays.addAll(inUseSmallArrays);
        inUseLargeArrays.clear();
        inUseSmallArrays.clear();
    }

    public static synchronized String func_85144_b()
    {
        return "cache: " + freeLargeArrays.size() + ", tcache: " + freeSmallArrays.size() + ", allocated: " + inUseLargeArrays.size() + ", tallocated: " + inUseSmallArrays.size();
    }
}
