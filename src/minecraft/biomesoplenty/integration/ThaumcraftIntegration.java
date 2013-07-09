package biomesoplenty.integration;

import net.minecraft.item.ItemStack;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import biomesoplenty.api.BlockReferences;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.api.Fluids;
import biomesoplenty.blocks.BlockBOPAmethyst;
import cpw.mods.fml.common.event.FMLInterModComms;

public class ThaumcraftIntegration {

	public static void init()
	{
		FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Blocks.leavesFruit.get(), 1, 3));
		FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Blocks.leavesFruit.get(), 1, 11));
		FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Blocks.foliage.get(), 1, 8));
	}
	
	public static void postInit()
	{
		addAspects();
	}

	private static void addAspects()
	{

		//TODO: Ensure all tags are balanced (Logs shouldn't give more light when turned into planks)

		//Woods

		ThaumcraftApi.registerObjectTag(getBID("acaciaLog"), getBMeta("acaciaLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));
		ThaumcraftApi.registerObjectTag(getBID("cherryLog"), getBMeta("cherryLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));

		ThaumcraftApi.registerObjectTag(getBID("darkLog"), getBMeta("darkLog"), (new ObjectTags()).add(EnumTag.WOOD, 8).add(EnumTag.DARK, 2));
		ThaumcraftApi.registerObjectTag(getBID("firLog"), getBMeta("firLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));

		ThaumcraftApi.registerObjectTag(getBID("holyLog"), getBMeta("holyLog"), (new ObjectTags()).add(EnumTag.WOOD, 8).add(EnumTag.LIGHT, 2));
		ThaumcraftApi.registerObjectTag(getBID("magicLog"), getBMeta("magicLog"), (new ObjectTags()).add(EnumTag.WOOD, 8).add(EnumTag.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(getBID("mangroveLog"), getBMeta("mangroveLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));
		ThaumcraftApi.registerObjectTag(getBID("palmLog"), getBMeta("palmLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));

		ThaumcraftApi.registerObjectTag(getBID("redwoodLog"), getBMeta("redwoodLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));
		ThaumcraftApi.registerObjectTag(getBID("willowLog"), getBMeta("willowLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));
		ThaumcraftApi.registerObjectTag(getBID("pineLog"), getBMeta("pineLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkLog"), getBMeta("hellBarkLog"), (new ObjectTags()).add(EnumTag.WOOD, 8).add(EnumTag.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaLog"), getBMeta("jacarandaLog"), (new ObjectTags()).add(EnumTag.WOOD, 8));
		ThaumcraftApi.registerObjectTag(getBID("deadLog"), getBMeta("deadLog"), (new ObjectTags()).add(EnumTag.WOOD, 8)); ///Working
		ThaumcraftApi.registerObjectTag(getBID("bigFlowerStem"), getBMeta("bigFlowerStem"), (new ObjectTags()).add(EnumTag.FLOWER, 4).add(EnumTag.MAGIC, 1));

		//Planks
		ThaumcraftApi.registerObjectTag(getBID("acaciaPlank"), getBMeta("acaciaPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("cherryPlank"), getBMeta("cherryPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("darkPlank"), getBMeta("darkPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2)/*.add(EnumTag.DARK, 1)*/);
		ThaumcraftApi.registerObjectTag(getBID("firPlank"), getBMeta("firPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("holyPlank"), getBMeta("holyPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2)/*.add(EnumTag.LIGHT, 1)*/);
		ThaumcraftApi.registerObjectTag(getBID("magicPlank"), getBMeta("magicPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2)/*.add(EnumTag.MAGIC, 1)*/);
		ThaumcraftApi.registerObjectTag(getBID("mangrovePlank"), getBMeta("mangrovePlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("palmPlank"), getBMeta("palmPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("redwoodPlank"), getBMeta("redwoodPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("willowPlank"), getBMeta("willowPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("pinePlank"), getBMeta("pinePlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkPlank"), getBMeta("hellBarkPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2).add(EnumTag.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaPlank"), getBMeta("jacarandaPlank"), (new ObjectTags()).add(EnumTag.WOOD, 2));
		ThaumcraftApi.registerObjectTag(getBID("bambooThatching"), getBMeta("bambooThatching"), (new ObjectTags()).add(EnumTag.WATER, 14).add(EnumTag.PLANT, 14));

		//Leaves
		ThaumcraftApi.registerObjectTag(getBID("holyLeaves"), getBMeta("holyLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("magicLeaves"), getBMeta("magicLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(getBID("darkLeaves"), getBMeta("darkLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.DARK, 1));
		ThaumcraftApi.registerObjectTag(getBID("deadLeaves"), getBMeta("deadLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.DEATH, 1));
		ThaumcraftApi.registerObjectTag(getBID("appleLeaves"), getBMeta("appleLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.LIFE, 1));
		ThaumcraftApi.registerObjectTag(getBID("acaciaLeaves"), getBMeta("acaciaLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("firLeaves"), getBMeta("firLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mangroveLeaves"), getBMeta("mangroveLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("palmLeaves"), getBMeta("palmLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("redwoodLeaves"), getBMeta("redwoodLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("willowLeaves"), getBMeta("willowLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("yellowAutumnLeaves"), getBMeta("yellowAutumnLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mapleLeaves"), getBMeta("mapleLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("orangeAutumnLeaves"), getBMeta("orangeAutumnLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pinkCherryLeaves"), getBMeta("pinkCherryLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("whiteCherryLeaves"), getBMeta("whiteCherryLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pineLeaves"), getBMeta("pineLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkLeaves"), getBMeta("hellBarkLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaLeaves"), getBMeta("jacarandaLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2).add(EnumTag.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("bambooLeaves"), getBMeta("bambooLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("originLeaves"), getBMeta("originLeaves"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("appleLeavesFruitless"), getBMeta("appleLeavesFruitless"), (new ObjectTags()).add(EnumTag.PLANT, 2));

		//Saplings
		ThaumcraftApi.registerObjectTag(getBID("holySapling"), getBMeta("holySapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2).add(EnumTag.LIGHT, 2));
		ThaumcraftApi.registerObjectTag(getBID("magicSapling"), getBMeta("magicSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2).add(EnumTag.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(getBID("darkSapling"), getBMeta("darkSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2).add(EnumTag.DARK, 2));
		ThaumcraftApi.registerObjectTag(getBID("deadSapling"), getBMeta("deadSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2).add(EnumTag.DEATH, 2));
		ThaumcraftApi.registerObjectTag(getBID("acaciaSapling"), getBMeta("acaciaSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("firSapling"), getBMeta("firSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mangroveSapling"), getBMeta("mangroveSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("palmSapling"), getBMeta("palmSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("redwoodSapling"), getBMeta("redwoodSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("willowSapling"), getBMeta("willowSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mapleSapling"), getBMeta("mapleSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("orangeAutumnSapling"), getBMeta("orangeAutumnSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pinkCherrySapling"), getBMeta("pinkCherrySapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("whiteCherrySapling"), getBMeta("whiteCherrySapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("appleSapling"), getBMeta("appleSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("originSapling"), getBMeta("originSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("yellowAutumnSapling"), getBMeta("yellowAutumnSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pineSapling"), getBMeta("pineSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkSapling"), getBMeta("hellBarkSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2).add(EnumTag.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaSapling"), getBMeta("jacarandaSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("bambooSapling"), getBMeta("bambooSapling"), (new ObjectTags()).add(EnumTag.WOOD, 4).add(EnumTag.PLANT, 2));

		//Blocks
		ThaumcraftApi.registerObjectTag(getBID("mud"), getBMeta("mud"), (new ObjectTags()).add(EnumTag.WATER, 3).add(EnumTag.EARTH, 6));
		ThaumcraftApi.registerObjectTag(getBID("driedDirt"), getBMeta("driedDirt"), (new ObjectTags()).add(EnumTag.DESTRUCTION, 1).add(EnumTag.EARTH, 1));
		ThaumcraftApi.registerObjectTag(getBID("redRock"), getBMeta("redRock"), (new ObjectTags()).add(EnumTag.ROCK, 2));
		ThaumcraftApi.registerObjectTag(getBID("ash"), getBMeta("ash"), (new ObjectTags()).add(EnumTag.DESTRUCTION, 3).add(EnumTag.EXCHANGE, 3));
		ThaumcraftApi.registerObjectTag(getBID("ashStone"), getBMeta("ashStone"), (new ObjectTags()).add(EnumTag.ROCK, 1).add(EnumTag.EXCHANGE, 1));
		ThaumcraftApi.registerObjectTag(getBID("hardIce"), getBMeta("hardIce"), (new ObjectTags()).add(EnumTag.ROCK, 2).add(EnumTag.COLD, 2));
		ThaumcraftApi.registerObjectTag(getBID("originGrass"), getBMeta("originGrass"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("hardSand"), getBMeta("hardSand"), (new ObjectTags()).add(EnumTag.ROCK, 1).add(EnumTag.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("hardDirt"), getBMeta("hardDirt"), (new ObjectTags()).add(EnumTag.ROCK, 1).add(EnumTag.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("holyGrass"), getBMeta("holyGrass"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.EARTH, 2).add(EnumTag.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("holyDirt"), getBMeta("holyDirt"), (new ObjectTags()).add(EnumTag.LIGHT, 1).add(EnumTag.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("holyStone"), getBMeta("holyStone"), (new ObjectTags()).add(EnumTag.LIGHT, 1).add(EnumTag.ROCK, 2));
		ThaumcraftApi.registerObjectTag(getBID("crystal"), getBMeta("crystal"), (new ObjectTags()).add(EnumTag.VALUABLE, 58).add(EnumTag.LIGHT, 15).add(EnumTag.MAGIC, 20).add(EnumTag.CRYSTAL, 50));
		ThaumcraftApi.registerObjectTag(getBID("cragRock"), getBMeta("cragRock"), (new ObjectTags()).add(EnumTag.ROCK, 2));
		ThaumcraftApi.registerObjectTag(getBID("quicksand"), getBMeta("quicksand"), (new ObjectTags()).add(EnumTag.EARTH, 2).add(EnumTag.TRAP, 4));
		ThaumcraftApi.registerObjectTag(getBID("smolderingGrass"), getBMeta("smolderingGrass"), (new ObjectTags()).add(EnumTag.EARTH, 2).add(EnumTag.FIRE, 1));
		ThaumcraftApi.registerObjectTag(getBID("amethystBlock"), getBMeta("amethystBlock"), (new ObjectTags()).add(EnumTag.VALUABLE, 58).add(EnumTag.PURE, 58).add(EnumTag.CRYSTAL, 87));
		ThaumcraftApi.registerObjectTag(getBID("amethystOre"), getBMeta("amethystOre"), (new ObjectTags()).add(EnumTag.VALUABLE, 6).add(EnumTag.PURE, 6).add(EnumTag.CRYSTAL, 14).add(EnumTag.ROCK, 4));
		ThaumcraftApi.registerObjectTag(getBID("redRockCobble"), getBMeta("redRockCobble"), (new ObjectTags()).add(EnumTag.DESTRUCTION, 1).add(EnumTag.ROCK, 1));
		ThaumcraftApi.registerObjectTag(getBID("giantFlowerRed"), getBMeta("giantFlowerRed"), (new ObjectTags()).add(EnumTag.FLOWER, 4).add(EnumTag.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(getBID("giantFlowerYellow"), getBMeta("giantFlowerYellow"), (new ObjectTags()).add(EnumTag.FLOWER, 4).add(EnumTag.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(getBID("smallBoneSegment"), getBMeta("smallBoneSegment"), (new ObjectTags()).add(EnumTag.FLESH, 1).add(EnumTag.DEATH, 3));
		ThaumcraftApi.registerObjectTag(getBID("mediumBoneSegment"), getBMeta("mediumBoneSegment"), (new ObjectTags()).add(EnumTag.FLESH, 2).add(EnumTag.DEATH, 6));
		ThaumcraftApi.registerObjectTag(getBID("largeBoneSegment"), getBMeta("largeBoneSegment"), (new ObjectTags()).add(EnumTag.FLESH, 4).add(EnumTag.DEATH, 12));
		ThaumcraftApi.registerObjectTag(getBID("ivy"), getBMeta("ivy"), (new ObjectTags()).add(EnumTag.PLANT, 2).add(EnumTag.FIRE, 1));
		
		for (int i = 10; i < 15; i++)
		{
			ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, i, (new ObjectTags()).add(EnumTag.VALUABLE, 4).add(EnumTag.CRYSTAL, 8));
		}
		
		String[] oreTypes = BlockBOPAmethyst.types;
		
		for (int i = 2; i < oreTypes.length; i+=2)
		{
			ThaumcraftApi.registerObjectTag(Blocks.amethystOre.get().blockID, i, (new ObjectTags()).add(EnumTag.VALUABLE, 3).add(EnumTag.CRYSTAL, 7).add(EnumTag.ROCK, 4));
		}

		//Items
		/*Amethyst*/ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, 2, (new ObjectTags()).add(EnumTag.VALUABLE, 8).add(EnumTag.PURE, 8).add(EnumTag.CRYSTAL, 16));
		/*Ashes*/ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, 1, (new ObjectTags()).add(EnumTag.DESTRUCTION, 1).add(EnumTag.EXCHANGE, 1));
		/*Mudbrick*/ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, 0, (new ObjectTags()).add(EnumTag.EARTH, 2).add(EnumTag.FIRE, 1));
		/*Dart*/ThaumcraftApi.registerObjectTag(Items.dart.get().itemID, 0, (new ObjectTags()).add(EnumTag.WEAPON, 1));
		/*Poison Dart*/ThaumcraftApi.registerObjectTag(Items.dart.get().itemID, 1, (new ObjectTags()).add(EnumTag.PLANT, 2).add(EnumTag.WEAPON, 2));
		/*Spring Water Bucket*/ThaumcraftApi.registerObjectTag(Fluids.bopBucket.get().itemID, 0, (new ObjectTags()).add(EnumTag.METAL, 13).add(EnumTag.VOID, 1).add(EnumTag.WATER, 4).add(EnumTag.LIFE, 2).add(EnumTag.HEAL, 4));
		/*Liquid Poison Bucket*/ThaumcraftApi.registerObjectTag(Fluids.bopBucket.get().itemID, 1, (new ObjectTags()).add(EnumTag.METAL, 13).add(EnumTag.VOID, 1).add(EnumTag.WATER, 2).add(EnumTag.WEAPON, 4).add(EnumTag.POISON, 4));
		ThaumcraftApi.registerObjectTag(Items.berries.get().itemID, 0, (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Items.sunflowerSeeds.get().itemID, 0, (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.EXCHANGE, 1));
		ThaumcraftApi.registerObjectTag(Items.mudball.get().itemID, 0, (new ObjectTags()).add(EnumTag.WATER, 1).add(EnumTag.EARTH, 2));
		ThaumcraftApi.registerObjectTag(Items.shroomPowder.get().itemID, -1, (new ObjectTags()).add(EnumTag.FUNGUS, 2).add(EnumTag.DESTRUCTION, 1));
		ThaumcraftApi.registerObjectTag(Items.bopDisc.get().itemID, -1, (new ObjectTags()).add(EnumTag.SOUND, 12).add(EnumTag.VALUABLE, 4).add(EnumTag.CROP, 4));
		ThaumcraftApi.registerObjectTag(Items.bopDiscMud.get().itemID, -1, (new ObjectTags()).add(EnumTag.SOUND, 12).add(EnumTag.VALUABLE, 4).add(EnumTag.EARTH, 2).add(EnumTag.WATER, 2));

		//Placer Items
		ThaumcraftApi.registerObjectTag(getBID("barley"), getBMeta("barley"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.LIFE, 1));
		ThaumcraftApi.registerObjectTag(getBID("cattail"), getBMeta("cattail"), (new ObjectTags()).add(EnumTag.WATER, 1).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("reed"), getBMeta("reed"), (new ObjectTags()).add(EnumTag.WOOD, 1).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("bamboo"), getBMeta("bamboo"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.LIFE, 1));
		ThaumcraftApi.registerObjectTag(getBID("sproutItem"), getBMeta("sproutItem"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("bushItem"), getBMeta("bushItem"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("poisonIvyItem"), getBMeta("poisonIvyItem"), (new ObjectTags()).add(EnumTag.PLANT, 2).add(EnumTag.WEAPON, 1));
		ThaumcraftApi.registerObjectTag(getBID("mediumGrassItem"), getBMeta("mediumGrassItem"), (new ObjectTags()).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("shortGrassItem"), getBMeta("shortGrassItem"), (new ObjectTags()).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("highGrassItem"), getBMeta("highGrassItem"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("bushItem"), getBMeta("bushItem"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("algae"), getBMeta("algae"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.WATER, 1));

		//Plants
		ThaumcraftApi.registerObjectTag(getBID("toadstool"), getBMeta("toadstool"), (new ObjectTags()).add(EnumTag.FUNGUS, 4));
		ThaumcraftApi.registerObjectTag(getBID("portobello"), getBMeta("portobello"), (new ObjectTags()).add(EnumTag.FUNGUS, 4));
		ThaumcraftApi.registerObjectTag(getBID("bluemilk"), getBMeta("bluemilk"), (new ObjectTags()).add(EnumTag.FUNGUS, 4));
		ThaumcraftApi.registerObjectTag(getBID("glowshroom"), getBMeta("glowshroom"), (new ObjectTags()).add(EnumTag.FUNGUS, 4).add(EnumTag.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("tinyCactus"), getBMeta("tinyCactus"), (new ObjectTags()).add(EnumTag.PLANT, 2).add(EnumTag.WEAPON, 1));
		ThaumcraftApi.registerObjectTag(getBID("holyTallGrass"), getBMeta("holyTallGrass"), (new ObjectTags()).add(EnumTag.WIND, 1).add(EnumTag.PLANT, 1).add(EnumTag.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("desertSprouts"), getBMeta("desertSprouts"), (new ObjectTags()).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("duneGrass"), getBMeta("duneGrass"), (new ObjectTags()).add(EnumTag.PLANT, 1).add(EnumTag.WIND, 1));
		ThaumcraftApi.registerObjectTag(getBID("thorn"), getBMeta("thorn"), (new ObjectTags()).add(EnumTag.PLANT, 2).add(EnumTag.WEAPON, 1));
		ThaumcraftApi.registerObjectTag(getBID("desertGrass"), getBMeta("desertGrass"), (new ObjectTags()).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("deadGrass"), getBMeta("deadGrass"), (new ObjectTags()).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("treeMoss"), getBMeta("treeMoss"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("moss"), getBMeta("moss"), (new ObjectTags()).add(EnumTag.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("willow"), getBMeta("willow"), (new ObjectTags()).add(EnumTag.PLANT, 2));

		//Flowers
		ThaumcraftApi.registerObjectTag(getBID("violet"), getBMeta("violet"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("hydrangea"), getBMeta("hydrangea"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("deathbloom"), getBMeta("deathbloom"), (new ObjectTags()).add(EnumTag.FLOWER, 4).add(EnumTag.DEATH, 1));
		ThaumcraftApi.registerObjectTag(getBID("glowFlower"), getBMeta("glowFlower"), (new ObjectTags()).add(EnumTag.FLOWER, 4).add(EnumTag.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("anenome"), getBMeta("anenome"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("swampFlower"), getBMeta("swampFlower"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("wildFlower"), getBMeta("wildFlower"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("daisy"), getBMeta("daisy"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("dandelion"), getBMeta("dandelion"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("tulip"), getBMeta("tulip"), (new ObjectTags()).add(EnumTag.FLOWER, 4));
		ThaumcraftApi.registerObjectTag(getBID("aloe"), getBMeta("aloe"), (new ObjectTags()).add(EnumTag.FLOWER, 4).add(EnumTag.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("clover"), getBMeta("clover"), (new ObjectTags()).add(EnumTag.FLOWER, 1).add(EnumTag.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("lilyflower"), getBMeta("lilyflower"), (new ObjectTags()).add(EnumTag.FLOWER, 1).add(EnumTag.PLANT, 1).add(EnumTag.WATER, 1));
		ThaumcraftApi.registerObjectTag(getBID("sunflower"), getBMeta("sunflower"), (new ObjectTags()).add(EnumTag.FLOWER, 1).add(EnumTag.PLANT, 1).add(EnumTag.LIGHT, 1));

	}

	private static int getBID(String name) {
		return BlockReferences.getBlockID(name);
	}

	private static int getBMeta(String name) {
		return BlockReferences.getBlockMeta(name);
	}
}
