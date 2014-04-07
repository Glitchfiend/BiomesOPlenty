package biomesoplenty.common.world;

import java.util.Calendar;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import biomesoplenty.common.biomes.overworld.BiomeGenCandyland;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.layer.GenLayerBiomeBOPA;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldTypeBOPA extends WorldType
{
    public static BiomeGenCandyland candyland;

    public WorldTypeBOPA()
    {
        super("CANDYOP");

        candyland = new BiomeGenCandyland(BOPConfigurationIDs.candylandID);
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerBiomeBOPA(200L, parentLayer, this);

        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }

    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerBOP(world);
    }
    
    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderCandyland(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean getCanBeCreated()
    {
        if (!BOPConfigurationMisc.behaveNormally) return WorldTypeBOPA.isTime();
        else return false;
    }

    public static boolean isTime()
    {
        Calendar calendar = Calendar.getInstance();

        
        return (calendar.get(2) + 1 == 4 && calendar.get(5) < 23 && calendar.get(5) > 14);
    }
}
