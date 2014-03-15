package biomesoplenty.common.world.decoration;

import java.util.HashMap;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.world.forceddecorators.BirchForestForcedDecorator;
import biomesoplenty.common.world.forceddecorators.DesertForcedDecorator;
import biomesoplenty.common.world.forceddecorators.ExtremeHillsForcedDecorator;
import biomesoplenty.common.world.forceddecorators.ForcedDecorator;
import biomesoplenty.common.world.forceddecorators.ForestForcedDecorator;
import biomesoplenty.common.world.forceddecorators.IcePlainsForcedDecorator;
import biomesoplenty.common.world.forceddecorators.JungleForcedDecorator;
import biomesoplenty.common.world.forceddecorators.MesaForcedDecorator;
import biomesoplenty.common.world.forceddecorators.MushroomIslandForcedDecorator;
import biomesoplenty.common.world.forceddecorators.OceanForcedDecorator;
import biomesoplenty.common.world.forceddecorators.PlainsForcedDecorator;
import biomesoplenty.common.world.forceddecorators.RiverForcedDecorator;
import biomesoplenty.common.world.forceddecorators.RoofedForestForcedDecorator;
import biomesoplenty.common.world.forceddecorators.SavannaForcedDecorator;
import biomesoplenty.common.world.forceddecorators.SwampForcedDecorator;
import biomesoplenty.common.world.forceddecorators.TaigaForcedDecorator;

public class ForcedDecorators 
{
	public static HashMap<Integer, ForcedDecorator> forcedDecoratorMap = new HashMap();
	
	public static void init()
	{
		addForcedDecorators();
	}
	
	private static void addForcedDecorators()
	{
		addForcedDecorator(BiomeGenBase.birchForest.biomeID, new BirchForestForcedDecorator());
		addForcedDecorator(BiomeGenBase.birchForestHills.biomeID, new BirchForestForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.desert.biomeID, new DesertForcedDecorator());
		addForcedDecorator(BiomeGenBase.desertHills.biomeID, new DesertForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.extremeHills.biomeID, new ExtremeHillsForcedDecorator());
		addForcedDecorator(BiomeGenBase.extremeHillsEdge.biomeID, new ExtremeHillsForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.forest.biomeID, new ForestForcedDecorator());
		addForcedDecorator(BiomeGenBase.forestHills.biomeID, new ForestForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.icePlains.biomeID, new IcePlainsForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.jungle.biomeID, new JungleForcedDecorator());
		addForcedDecorator(BiomeGenBase.jungleEdge.biomeID, new JungleForcedDecorator());
		addForcedDecorator(BiomeGenBase.jungleHills.biomeID, new JungleForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.mesa.biomeID, new MesaForcedDecorator());
		addForcedDecorator(BiomeGenBase.mesaPlateau.biomeID, new MesaForcedDecorator());
		addForcedDecorator(BiomeGenBase.mesaPlateau_F.biomeID, new MesaForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.mushroomIsland.biomeID, new MushroomIslandForcedDecorator());
		addForcedDecorator(BiomeGenBase.mushroomIslandShore.biomeID, new MushroomIslandForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.ocean.biomeID, new OceanForcedDecorator());
		addForcedDecorator(BiomeGenBase.plains.biomeID, new PlainsForcedDecorator());
		addForcedDecorator(BiomeGenBase.river.biomeID, new RiverForcedDecorator());
		addForcedDecorator(BiomeGenBase.roofedForest.biomeID, new RoofedForestForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.savanna.biomeID, new SavannaForcedDecorator());
		addForcedDecorator(BiomeGenBase.savannaPlateau.biomeID, new SavannaForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.swampland.biomeID, new SwampForcedDecorator());
		
		addForcedDecorator(BiomeGenBase.taiga.biomeID, new TaigaForcedDecorator());
		addForcedDecorator(BiomeGenBase.taigaHills.biomeID, new TaigaForcedDecorator());
		addForcedDecorator(BiomeGenBase.coldTaiga.biomeID, new TaigaForcedDecorator());
		addForcedDecorator(BiomeGenBase.coldTaigaHills.biomeID, new TaigaForcedDecorator());
	}
	
	public static void addForcedDecorator(int biomeID, ForcedDecorator decorator)
	{
		forcedDecoratorMap.put(biomeID, decorator);
	}
	
	public static IBOPDecoration getForcedDecorator(int biomeID)
	{
		return forcedDecoratorMap.get(biomeID);
	}
	
	public static boolean biomeHasForcedDecorator(int biomeID)
	{
		return forcedDecoratorMap.containsKey(biomeID);
	}
}
