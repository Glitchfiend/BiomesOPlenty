package biomesoplenty.common.world.decoration;

import biomesoplenty.common.world.forceddecorators.*;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.HashMap;
import java.util.List;

public class BiomeTweaker
{
	public static HashMap<Integer, ForcedDecorator> forcedDecoratorMap = new HashMap();
	
	public static void init()
	{
		addForcedDecorators();
        tweakDecorationProperties();
	}
	
	private static void addForcedDecorators()
	{
		addForcedDecorator(BiomeGenBase.birchForest.biomeID, BirchForestForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.birchForestHills.biomeID, BirchForestForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.desert.biomeID, DesertForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.desertHills.biomeID, DesertForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.extremeHills.biomeID, ExtremeHillsForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.extremeHillsEdge.biomeID, ExtremeHillsForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.forest.biomeID, ForestForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.forestHills.biomeID, ForestForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.icePlains.biomeID, IcePlainsForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.jungle.biomeID, JungleForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.jungleEdge.biomeID, JungleForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.jungleHills.biomeID, JungleForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.mesa.biomeID, MesaForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.mesaPlateau.biomeID, MesaForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.mesaPlateau_F.biomeID, MesaForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.mushroomIsland.biomeID, MushroomIslandForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.mushroomIslandShore.biomeID, MushroomIslandForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.ocean.biomeID, OceanForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.plains.biomeID, PlainsForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.river.biomeID, RiverForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.roofedForest.biomeID, RoofedForestForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.savanna.biomeID, SavannaForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.savannaPlateau.biomeID, SavannaForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.swampland.biomeID, SwampForcedDecorator.class);
		
		addForcedDecorator(BiomeGenBase.taiga.biomeID, TaigaForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.taigaHills.biomeID, TaigaForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.coldTaiga.biomeID, TaigaForcedDecorator.class);
		addForcedDecorator(BiomeGenBase.coldTaigaHills.biomeID, TaigaForcedDecorator.class);
	}

    private static void tweakDecorationProperties()
    {
        BiomeGenBase.hell.topBlock = Blocks.netherrack;
        BiomeGenBase.hell.fillerBlock = Blocks.netherrack;

        for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++)
        {
            BiomeGenBase biome = BiomeGenBase.getBiome(i);

            if (biome != null)
            {
                BOPWorldFeatures biomeFeatures = BOPDecorationManager.getBiomeFeatures(i);

                if (biomeFeatures != null)
                {
                    List<BiomeGenBase.FlowerEntry> flowers = ReflectionHelper.getPrivateValue(BiomeGenBase.class, biome, "flowers");

                    flowers.clear();

                    biome.addDefaultFlowers();

                    biome.theBiomeDecorator.flowersPerChunk = 0;
                    biome.theBiomeDecorator.grassPerChunk = 0;
                }
            }
        }
    }
	
	public static void addForcedDecorator(int biomeID, Class<? extends ForcedDecorator> decoratorClass)
	{
        try
        {
            ForcedDecorator decorator = decoratorClass.getConstructor(int.class).newInstance(biomeID);

            forcedDecoratorMap.put(biomeID, decorator);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public static IBOPBiome getForcedDecorator(int biomeID)
	{
		return forcedDecoratorMap.get(biomeID);
	}
	
	public static boolean biomeHasForcedDecorator(int biomeID)
	{
		return forcedDecoratorMap.containsKey(biomeID);
	}
}
