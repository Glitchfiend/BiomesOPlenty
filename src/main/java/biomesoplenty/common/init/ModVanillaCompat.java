package biomesoplenty.common.init;

import java.util.List;

import com.google.common.collect.Lists;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.entities.item.EntityBOPBoat;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviorBOPBoat;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviorMudball;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.world.BOPMapGenScatteredFeature;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.WoodlandMansion;

public class ModVanillaCompat
{
    public static void init()
    {
    	registerDispenserBehaviors();
    	
    	MapGenStructureIO.registerStructure(BOPMapGenScatteredFeature.Start.class, "BOPTemple");
    	List<Biome> mansionBiomes = BiomeUtils.filterPresentBiomes(BOPBiomes.coniferous_forest, BOPBiomes.dead_forest, BOPBiomes.ominous_woods, BOPBiomes.snowy_coniferous_forest, BOPBiomes.woodland);
    	mansionBiomes.addAll(Lists.newArrayList(Biomes.ROOFED_FOREST, Biomes.MUTATED_ROOFED_FOREST));
    	WoodlandMansion.ALLOWED_BIOMES = mansionBiomes;
    }
    
    private static void registerDispenserBehaviors()
    {
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.mudball, new DispenserBehaviorMudball());
    	
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_cherry, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.CHERRY));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_umbran, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.UMBRAN));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_fir, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.FIR));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_ethereal, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.ETHEREAL));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_magic, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.MAGIC));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_palm, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.PALM));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_redwood, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.REDWOOD));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_willow, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.WILLOW));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_hellbark, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.HELLBARK));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_jacaranda, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.JACARANDA));
    	BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BOPItems.boat_mahogany, new DispenserBehaviorBOPBoat(EntityBOPBoat.Type.MAHOGANY));
    }
}
