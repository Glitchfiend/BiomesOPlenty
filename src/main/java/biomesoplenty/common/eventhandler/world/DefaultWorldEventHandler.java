package biomesoplenty.common.eventhandler.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import biomesoplenty.api.BOPBiomeHelper.TemperatureType;
import biomesoplenty.common.configuration.BOPConfigurationMain;
import biomesoplenty.common.utils.BOPLogger;
import biomesoplenty.common.world.BOPBiomeManager.BiomeEntry;
import biomesoplenty.common.world.layer.GenLayerBiomeBOP;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DefaultWorldEventHandler 
{
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onInitBiomeGens(InitBiomeGens event)
	{
		GenLayer[] originalBiomeGens = event.originalBiomeGens;

		GenLayer parent = originalBiomeGens[0];
		GenLayerBiomeBOP genLayerBiome = null;

		if (parent instanceof GenLayerRiverMix)
		{
			GenLayerRiverMix genLayerRiverMix = (GenLayerRiverMix)parent;
			GenLayer biomePatternGeneratorChain = ObfuscationReflectionHelper.getPrivateValue(GenLayerRiverMix.class, genLayerRiverMix, "biomePatternGeneratorChain", "field_75910_b");

			if (biomePatternGeneratorChain != null) parent = biomePatternGeneratorChain;
		}

		while (genLayerBiome == null)
		{
			if (parent instanceof GenLayerBiomeBOP)
			{
				genLayerBiome = (GenLayerBiomeBOP)parent;
			}

			GenLayer newParent = ObfuscationReflectionHelper.getPrivateValue(GenLayer.class, parent, "parent", "field_75909_a");

			if (newParent == null)
			{
				throw new RuntimeException("Failed to find GenLayerBiome in chain");
			}
			else
			{
				parent = newParent;
			}
		}

		if (genLayerBiome != null)
		{
			BiomeGenBase[] vanillaDesertBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, genLayerBiome, "field_151623_c");
			BiomeGenBase[] vanillaWarmBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, genLayerBiome, "field_151621_d");
			BiomeGenBase[] vanillaCoolBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, genLayerBiome, "field_151622_e");
			BiomeGenBase[] vanillaIcyBiomes = (BiomeGenBase[])ObfuscationReflectionHelper.getPrivateValue(GenLayerBiome.class, genLayerBiome, "field_151620_f");
			
			for (BiomeGenBase biome : vanillaDesertBiomes)
			{
				if (biome != null)
				{
					if (BOPConfigurationMain.debugMode) BOPLogger.info("Adding biome " + biome.biomeName + " from the default world.");
					
					BiomeEntry entry = new BiomeEntry(biome, TemperatureType.HOT, 10);
					
					genLayerBiome.desertBiomes.add(entry);
				}
			}

			for (BiomeGenBase biome : vanillaWarmBiomes)
			{
				if (biome != null)
				{
					if (BOPConfigurationMain.debugMode) BOPLogger.info("Adding biome " + biome.biomeName + " from the default world.");
					
					BiomeEntry entry = new BiomeEntry(biome, TemperatureType.WARM, 10);

					genLayerBiome.warmBiomes.add(entry);
				}
			}

			for (BiomeGenBase biome : vanillaCoolBiomes)
			{
				if (biome != null)
				{
					if (BOPConfigurationMain.debugMode) BOPLogger.info("Adding biome " + biome.biomeName + " from the default world.");
					
					BiomeEntry entry = new BiomeEntry(biome, TemperatureType.COOL, 10);

					genLayerBiome.coolBiomes.add(entry);
				}
			}

			for (BiomeGenBase biome : vanillaIcyBiomes)
			{
				if (biome != null)
				{
					if (BOPConfigurationMain.debugMode) BOPLogger.info("Adding biome " + biome.biomeName + " from the default world.");
					
					BiomeEntry entry = new BiomeEntry(biome, TemperatureType.ICY, 10);

					genLayerBiome.icyBiomes.add(entry);
				}
			}
		}
	}
}
