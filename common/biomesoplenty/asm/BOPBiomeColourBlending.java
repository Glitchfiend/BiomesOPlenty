package biomesoplenty.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.asm.biomecolourblending.BlockFluid;
import biomesoplenty.asm.biomecolourblending.BlockGrass;
import biomesoplenty.asm.biomecolourblending.BlockLeaves;
import biomesoplenty.asm.biomecolourblending.BlockTallGrass;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BOPBiomeColourBlending implements IClassTransformer
{
    @Override
    public byte[] transform(String name, String newname, byte[] bytes)
    {
        if (name.equals("net.minecraft.block.BlockFluid")) 
        {
            return BlockFluid.patchColourMultiplier(newname, bytes, false);
        }

        if (name.equals("net.minecraft.block.BlockGrass")) 
        {
            return BlockGrass.patchColourMultiplier(newname, bytes, false);
        }

        if (name.equals("net.minecraft.block.BlockLeaves")) 
        {
            return BlockLeaves.patchColourMultiplier(newname, bytes, false);
        }

        if (name.equals("net.minecraft.block.BlockTallGrass")) 
        {
            return BlockTallGrass.patchColourMultiplier(newname, bytes, false);
        }

        if (name.equals("apc"))
        { 
            return BlockFluid.patchColourMultiplier(newname, bytes, true);
        }


        if (name.equals("aon")) 
        {
            return BlockGrass.patchColourMultiplier(newname, bytes, true);
        }


        if (name.equals("aoz")) 
        {
            return BlockLeaves.patchColourMultiplier(newname, bytes, true);
        }


        if (name.equals("aqv")) 
        {
            return BlockTallGrass.patchColourMultiplier(newname, bytes, true);
        }

        return bytes;
    }

    @SideOnly(Side.CLIENT)
    public static int getGrassColourMultiplier(IBlockAccess world, int ox, int oy, int oz)
    {
        int distance = FMLClientHandler.instance().getClient().gameSettings.fancyGraphics ? BOPConfigurationMisc.grassColourSmoothingArea : 1;
        
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
    
    @SideOnly(Side.CLIENT)
    public static int getLeavesColourMultiplier(IBlockAccess world, int ox, int oy, int oz)
    {
        int distance = FMLClientHandler.instance().getClient().gameSettings.fancyGraphics ? BOPConfigurationMisc.leavesColourSmoothingArea : 1;
        
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
    
    @SideOnly(Side.CLIENT)
    public static int getWaterColourMultiplier(IBlockAccess world, int ox, int oy, int oz)
    {
        int distance = FMLClientHandler.instance().getClient().gameSettings.fancyGraphics ? BOPConfigurationMisc.waterColourSmoothingArea : 1;
        
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
