package biomesoplenty.asm.smoothing;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.asm.smoothing.block.BlockFluid;
import biomesoplenty.asm.smoothing.block.BlockGrass;
import biomesoplenty.asm.smoothing.block.BlockLeaves;
import biomesoplenty.asm.smoothing.block.BlockTallGrass;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;

public class BOPBiomeTransitionSmoothing implements IClassTransformer
{
    @Override
    public byte[] transform(String name, String newname, byte[] bytes)
    {
        if (name.equals("net.minecraft.block.BlockFluid")) 
        {
            return BlockFluid.patchColourMultiplier(newname, bytes, false);
        }
        if (name.equals("pc"))
        { 
            return BlockFluid.patchColourMultiplier(newname, bytes, true);
        }
        
        if (name.equals("net.minecraft.block.BlockGrass")) 
        {
            return BlockGrass.patchColourMultiplier(newname, bytes, false);
        }
        if (name.equals("on")) 
        {
            return BlockGrass.patchColourMultiplier(newname, bytes, true);
        }
        
        if (name.equals("net.minecraft.block.BlockLeaves")) 
        {
            return BlockLeaves.patchColourMultiplier(newname, bytes, false);
        }
        if (name.equals("oz")) 
        {
            return BlockLeaves.patchColourMultiplier(newname, bytes, true);
        }
        
        if (name.equals("net.minecraft.block.BlockTallGrass")) 
        {
            return BlockTallGrass.patchColourMultiplier(newname, bytes, false);
        }
        if (name.equals("qv")) 
        {
            return BlockTallGrass.patchColourMultiplier(newname, bytes, true);
        }
        
        return bytes;
    }
    
    public static int getGrassColourMultiplier(IBlockAccess world, int ox, int oy, int oz)
    {
        int distance = BOPConfigurationMisc.grassColourSmoothingArea;
        
        int r = 0;
        int g = 0;
        int b = 0;

        int divider = 0;
        
        for (int x = -distance; x <= distance; ++x)
        {
            for (int z = -distance; z <= distance; ++z)
            {
                BiomeGenBase biome = world.getBiomeGenForCoords(ox + x, oz + z);
                int colour = biome.getBiomeGrassColor();
                r += (colour & 0xFF0000) >> 16;
                g += (colour & 0x00FF00) >> 8;
                b += colour & 0x0000FF;
                divider++;
            }
        }

        return (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;
    }
    
    public static int getLeavesColourMultiplier(IBlockAccess world, int ox, int oy, int oz)
    {
        int distance = BOPConfigurationMisc.leavesColourSmoothingArea;
        
        int r = 0;
        int g = 0;
        int b = 0;

        int divider = 0;
        
        for (int x = -distance; x <= distance; ++x)
        {
            for (int z = -distance; z <= distance; ++z)
            {
                BiomeGenBase biome = world.getBiomeGenForCoords(ox + x, oz + z);
                int colour = biome.getBiomeFoliageColor();
                r += (colour & 0xFF0000) >> 16;
                g += (colour & 0x00FF00) >> 8;
                b += colour & 0x0000FF;
                divider++;
            }
        }

        return (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;
    }
    
    public static int getWaterColourMultiplier(IBlockAccess world, int ox, int oy, int oz)
    {
        int distance = BOPConfigurationMisc.waterColourSmoothingArea;
        
        int r = 0;
        int g = 0;
        int b = 0;

        int divider = 0;
        
        for (int x = -distance; x <= distance; ++x)
        {
            for (int z = -distance; z <= distance; ++z)
            {
                BiomeGenBase biome = world.getBiomeGenForCoords(ox + x, oz + z);
                int colour = biome.getWaterColorMultiplier();
                r += (colour & 0xFF0000) >> 16;
                g += (colour & 0x00FF00) >> 8;
                b += colour & 0x0000FF;
                divider++;
            }
        }

        return (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;
    }
}
