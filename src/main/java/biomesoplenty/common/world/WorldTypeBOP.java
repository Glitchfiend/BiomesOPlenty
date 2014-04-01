package biomesoplenty.common.world;

import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.layer.GenLayerBiomeBOP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraftforge.common.DimensionManager;

public class WorldTypeBOP extends WorldType
{
    public static WorldTypeBOPA worldTypeBOPA;

	public WorldTypeBOP() 
	{
        super("BIOMESOP");

        worldTypeBOPA = new WorldTypeBOPA();
        
        DimensionManager.unregisterProviderType(0);
        DimensionManager.registerProviderType(0, WorldProviderSurfaceBOP.class, true);
	}

    @Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerBiomeBOP(200L, parentLayer, this);

        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
    
    @Override
	public WorldChunkManager getChunkManager(World world)
    {
    	return new WorldChunkManagerBOP(world);
    }
}
