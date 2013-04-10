package forestry.api.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;

public class ItemInterface {

	/**
	 * Get yer items here!
	 * 
	 * Blocks currently not supported.
	 * 
	 * @param ident
	 * @return
	 */
	public static ItemStack getItem(String ident) {
		ItemStack item = null;

		try {
			String pack = ItemInterface.class.getPackage().getName();
			pack = pack.substring(0, pack.lastIndexOf('.'));
			String itemClass = pack.substring(0, pack.lastIndexOf('.')) + ".core.config.ForestryItem";
			Object obj = Class.forName(itemClass).getField(ident).get(null);
			if (obj instanceof Item)
				item = new ItemStack((Item) obj);
			else if (obj instanceof ItemStack)
				item = (ItemStack) obj;
		} catch (Exception ex) {
			FMLLog.warning("Could not retrieve Forestry item identified by: " + ident);
		}

		return item;
	}

	/*
	 * public static Item fertilizerBio; public static Item fertilizerCompound; public static Item apatite;
	 * 
	 * // Ingots public static ItemStack ingotCopper; public static ItemStack ingotTin; public static ItemStack ingotBronze;
	 * 
	 * public static Item wrench; public static Item bucketBiomass; public static Item vialEmpty; public static Item vialCatalyst; public static Item
	 * liquidBiomass; public static Item liquidBiofuel; public static Item bucketBiofuel; public static Item liquidMilk;
	 * 
	 * // Crafting public static Item sturdyMachine; public static Item hardenedMachine; public static Item craftingMaterial;
	 * 
	 * // Rainmaker public static Item iodineCapsule;
	 * 
	 * // Gears public static Item gearBronze; public static Item gearCopper; public static Item gearTin;
	 * 
	 * // Carpenter public static Item oakStick; public static Item woodPulp; public static Item carton; public static Item crate;
	 * 
	 * // Tools public static Item bronzePickaxe; public static Item brokenBronzePickaxe; public static Item kitPickaxe; public static Item bronzeShovel; public
	 * static Item brokenBronzeShovel; public static Item kitShovel;
	 * 
	 * // Do not touch - contagious! public static Item tent;
	 * 
	 * // Moistener public static Item mouldyWheat; public static Item decayingWheat; public static Item mulch;
	 * 
	 * // Peat public static Item peat; public static Item bituminousPeat; public static Item ash;
	 * 
	 * // Bees public static Item beeQueen; public static Item beeDrone; public static Item beePrincess; public static Item beeQueenGE; public static Item
	 * beeDroneGE; public static Item beePrincessGE;
	 * 
	 * public static Item beealyzer;
	 * 
	 * public static Item honeyDrop; public static Item scoop; public static Item beeswax; public static Item pollen; public static Item propolis; public static
	 * Item honeydew; public static Item royalJelly; public static Item honeyedSlice; public static Item shortMead; public static Item ambrosia; public static
	 * Item honeyPot; public static Item phosphor; public static Item refractoryWax;
	 * 
	 * // Apiarist's Armor public static Item apiaristHat; public static Item apiaristChest; public static Item apiaristLegs; public static Item apiaristBoots;
	 * 
	 * // Combs public static Item beeComb;
	 * 
	 * public static Item honeyComb; public static Item cocoaComb; public static Item simmeringComb; public static Item stringyComb; public static Item
	 * frozenComb; public static Item drippingComb;
	 * 
	 * // Backpacks public static Item apiaristBackpack; public static Item minerBackpack; public static Item diggerBackpack; public static Item
	 * foresterBackpack; public static Item hunterBackpack; public static Item masonBackpack; // unused/null public static Item dyerBackpack; // unused/null
	 * public static Item railroaderBackpack; // unused/null public static Item tinkererBackpack; // unused/null public static Item adventurerBackpack; // T2
	 * public static Item minerBackpackT2; public static Item diggerBackpackT2; public static Item foresterBackpackT2; public static Item hunterBackpackT2;
	 * public static Item masonBackpackT2; // unused/null public static Item dyerBackpackT2; // unused/null public static Item railroaderBackpackT2; //
	 * unused/null public static Item tinkererBackpackT2; // unused/null public static Item adventurerBackpackT2;
	 * 
	 * // Liquids public static Item liquidSeedOil; public static Item liquidJuice; public static Item liquidHoney;
	 * 
	 * // Capsules public static Item waxCapsule; public static Item waxCapsuleWater; public static Item waxCapsuleBiomass; public static Item
	 * waxCapsuleBiofuel; public static Item waxCapsuleOil; public static Item waxCapsuleFuel; public static Item waxCapsuleSeedOil; public static Item
	 * waxCapsuleHoney; public static Item waxCapsuleJuice;
	 * 
	 * // Refractory Capsules public static Item refractoryEmpty; public static Item refractoryWater; public static Item refractoryBiomass; public static Item
	 * refractoryBiofuel; public static Item refractoryOil; public static Item refractoryFuel; public static Item refractoryLava; public static Item
	 * refractorySeedOil; public static Item refractoryHoney; public static Item refractoryJuice;
	 * 
	 * // Cans public static Item canWater; public static Item canEmpty; public static Item canBiomass; public static Item canBiofuel; public static Item
	 * canOil; public static Item canFuel; public static Item canLava; public static Item canSeedOil; public static Item canHoney; public static Item canJuice;
	 * 
	 * // Crating public static ItemGenericCrate cratedWood; public static ItemGenericCrate cratedCobblestone; public static ItemGenericCrate cratedDirt; public
	 * static ItemGenericCrate cratedStone; public static ItemGenericCrate cratedBrick; public static ItemGenericCrate cratedCacti; public static
	 * ItemGenericCrate cratedSand; public static ItemGenericCrate cratedObsidian; public static ItemGenericCrate cratedNetherrack; public static
	 * ItemGenericCrate cratedSoulsand; public static ItemGenericCrate cratedSandstone; public static ItemGenericCrate cratedBogearth; public static
	 * ItemGenericCrate cratedHumus; public static ItemGenericCrate cratedNetherbrick; public static ItemGenericCrate cratedPeat; public static ItemGenericCrate
	 * cratedApatite; public static ItemGenericCrate cratedFertilizer; public static ItemGenericCrate cratedTin; public static ItemGenericCrate cratedCopper;
	 * public static ItemGenericCrate cratedBronze; public static ItemGenericCrate cratedWheat; public static ItemGenericCrate cratedMycelium; public static
	 * ItemGenericCrate cratedMulch; public static ItemGenericCrate cratedSilver; public static ItemGenericCrate cratedBrass; public static ItemGenericCrate
	 * cratedNikolite; public static ItemGenericCrate cratedCookies; public static ItemGenericCrate cratedHoneycombs; public static ItemGenericCrate
	 * cratedBeeswax; public static ItemGenericCrate cratedPollen; public static ItemGenericCrate cratedPropolis; public static ItemGenericCrate cratedHoneydew;
	 * public static ItemGenericCrate cratedRoyalJelly; public static ItemGenericCrate cratedCocoaComb; public static ItemGenericCrate cratedRedstone; public
	 * static ItemGenericCrate cratedLapis; public static ItemGenericCrate cratedReeds; public static ItemGenericCrate cratedClay; public static
	 * ItemGenericCrate cratedGlowstone; public static ItemGenericCrate cratedApples; public static ItemGenericCrate cratedNetherwart; public static
	 * ItemGenericCrate cratedResin; public static ItemGenericCrate cratedRubber; public static ItemGenericCrate cratedScrap; public static ItemGenericCrate
	 * cratedUUM; public static ItemGenericCrate cratedSimmeringCombs; public static ItemGenericCrate cratedStringyCombs; public static ItemGenericCrate
	 * cratedFrozenCombs; public static ItemGenericCrate cratedDrippingCombs; public static ItemGenericCrate cratedRefractoryWax; public static ItemGenericCrate
	 * cratedPhosphor; public static ItemGenericCrate cratedAsh; public static ItemGenericCrate cratedCharcoal; public static ItemGenericCrate cratedGravel;
	 * public static ItemGenericCrate cratedCoal; public static ItemGenericCrate cratedSeeds; public static ItemGenericCrate cratedSaplings;
	 */

}
