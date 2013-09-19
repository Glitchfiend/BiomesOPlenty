package biomesoplenty.integration;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import biomesoplenty.api.BlockReferences;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.api.Items;
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

		//TODO: Compare against Thaumcraft 3.1 defaults and check balancing

		//Woods

		ThaumcraftApi.registerObjectTag(getBID("acaciaLog"), getBMeta("acaciaLog"), (new AspectList()).add(Aspect.TREE, 4));
		ThaumcraftApi.registerObjectTag(getBID("cherryLog"), getBMeta("cherryLog"), (new AspectList()).add(Aspect.TREE, 4));

		ThaumcraftApi.registerObjectTag(getBID("darkLog"), getBMeta("darkLog"), (new AspectList()).add(Aspect.TREE, 4).add(Aspect.DARKNESS, 2));
		ThaumcraftApi.registerObjectTag(getBID("firLog"), getBMeta("firLog"), (new AspectList()).add(Aspect.TREE, 4));

		ThaumcraftApi.registerObjectTag(getBID("holyLog"), getBMeta("holyLog"), (new AspectList()).add(Aspect.TREE, 4).add(Aspect.LIGHT, 2));
		ThaumcraftApi.registerObjectTag(getBID("magicLog"), getBMeta("magicLog"), (new AspectList()).add(Aspect.TREE, 4).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(getBID("mangroveLog"), getBMeta("mangroveLog"), (new AspectList()).add(Aspect.TREE, 4));
		ThaumcraftApi.registerObjectTag(getBID("palmLog"), getBMeta("palmLog"), (new AspectList()).add(Aspect.TREE, 4));

		ThaumcraftApi.registerObjectTag(getBID("redwoodLog"), getBMeta("redwoodLog"), (new AspectList()).add(Aspect.TREE, 4));
		ThaumcraftApi.registerObjectTag(getBID("willowLog"), getBMeta("willowLog"), (new AspectList()).add(Aspect.TREE, 4));
		ThaumcraftApi.registerObjectTag(getBID("pineLog"), getBMeta("pineLog"), (new AspectList()).add(Aspect.TREE, 4));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkLog"), getBMeta("hellBarkLog"), (new AspectList()).add(Aspect.TREE, 4).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaLog"), getBMeta("jacarandaLog"), (new AspectList()).add(Aspect.TREE, 4));
		ThaumcraftApi.registerObjectTag(getBID("deadLog"), getBMeta("deadLog"), (new AspectList()).add(Aspect.TREE, 4)); ///Working
		ThaumcraftApi.registerObjectTag(getBID("bigFlowerStem"), getBMeta("bigFlowerStem"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.MAGIC, 1));

		//Planks
		ThaumcraftApi.registerObjectTag(getBID("acaciaPlank"), getBMeta("acaciaPlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("cherryPlank"), getBMeta("cherryPlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("darkPlank"), getBMeta("darkPlank"), (new AspectList()).add(Aspect.TREE, 1)/*.add(Aspect.DARK, 1)*/);
		ThaumcraftApi.registerObjectTag(getBID("firPlank"), getBMeta("firPlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("holyPlank"), getBMeta("holyPlank"), (new AspectList()).add(Aspect.TREE, 1)/*.add(Aspect.LIGHT, 1)*/);
		ThaumcraftApi.registerObjectTag(getBID("magicPlank"), getBMeta("magicPlank"), (new AspectList()).add(Aspect.TREE, 1)/*.add(Aspect.MAGIC, 1)*/);
		ThaumcraftApi.registerObjectTag(getBID("mangrovePlank"), getBMeta("mangrovePlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("palmPlank"), getBMeta("palmPlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("redwoodPlank"), getBMeta("redwoodPlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("willowPlank"), getBMeta("willowPlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("pinePlank"), getBMeta("pinePlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkPlank"), getBMeta("hellBarkPlank"), (new AspectList()).add(Aspect.TREE, 1).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaPlank"), getBMeta("jacarandaPlank"), (new AspectList()).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(getBID("bambooThatching"), getBMeta("bambooThatching"), (new AspectList()).add(Aspect.WATER, 14).add(Aspect.PLANT, 14));

		//Leaves
		ThaumcraftApi.registerObjectTag(getBID("holyLeaves"), getBMeta("holyLeaves"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("magicLeaves"), getBMeta("magicLeaves"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(getBID("darkLeaves"), getBMeta("darkLeaves"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(getBID("deadLeaves"), getBMeta("deadLeaves"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(getBID("appleLeaves"), getBMeta("appleLeaves"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(getBID("persimmonLeaves"), getBMeta("persimmonLeaves"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(getBID("acaciaLeaves"), getBMeta("acaciaLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("firLeaves"), getBMeta("firLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mangroveLeaves"), getBMeta("mangroveLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("palmLeaves"), getBMeta("palmLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("redwoodLeaves"), getBMeta("redwoodLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("willowLeaves"), getBMeta("willowLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("yellowAutumnLeaves"), getBMeta("yellowAutumnLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mapleLeaves"), getBMeta("mapleLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("orangeAutumnLeaves"), getBMeta("orangeAutumnLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pinkCherryLeaves"), getBMeta("pinkCherryLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("whiteCherryLeaves"), getBMeta("whiteCherryLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pineLeaves"), getBMeta("pineLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkLeaves"), getBMeta("hellBarkLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaLeaves"), getBMeta("jacarandaLeaves"), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("bambooLeaves"), getBMeta("bambooLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("originLeaves"), getBMeta("originLeaves"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("appleLeavesFruitless"), getBMeta("appleLeavesFruitless"), (new AspectList()).add(Aspect.PLANT, 2));

		//Saplings
		ThaumcraftApi.registerObjectTag(getBID("holySapling"), getBMeta("holySapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2).add(Aspect.LIGHT, 2));
		ThaumcraftApi.registerObjectTag(getBID("magicSapling"), getBMeta("magicSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(getBID("darkSapling"), getBMeta("darkSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2).add(Aspect.DARKNESS, 2));
		ThaumcraftApi.registerObjectTag(getBID("deadSapling"), getBMeta("deadSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2).add(Aspect.DEATH, 2));
		ThaumcraftApi.registerObjectTag(getBID("acaciaSapling"), getBMeta("acaciaSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("firSapling"), getBMeta("firSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mangroveSapling"), getBMeta("mangroveSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("palmSapling"), getBMeta("palmSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("redwoodSapling"), getBMeta("redwoodSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("willowSapling"), getBMeta("willowSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("mapleSapling"), getBMeta("mapleSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("orangeAutumnSapling"), getBMeta("orangeAutumnSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pinkCherrySapling"), getBMeta("pinkCherrySapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("whiteCherrySapling"), getBMeta("whiteCherrySapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("appleSapling"), getBMeta("appleSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("persimmonSapling"), getBMeta("persimmonSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("originSapling"), getBMeta("originSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("yellowAutumnSapling"), getBMeta("yellowAutumnSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("pineSapling"), getBMeta("pineSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("hellBarkSapling"), getBMeta("hellBarkSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerObjectTag(getBID("jacarandaSapling"), getBMeta("jacarandaSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("bambooSapling"), getBMeta("bambooSapling"), (new AspectList()).add(Aspect.TREE, 2).add(Aspect.PLANT, 2));

		//Blocks
		ThaumcraftApi.registerObjectTag(getBID("mud"), getBMeta("mud"), (new AspectList()).add(Aspect.WATER, 3).add(Aspect.EARTH, 6));
		ThaumcraftApi.registerObjectTag(getBID("driedDirt"), getBMeta("driedDirt"), (new AspectList()).add(Aspect.ENTROPY, 1).add(Aspect.EARTH, 1));
		ThaumcraftApi.registerObjectTag(getBID("redRock"), getBMeta("redRock"), (new AspectList()).add(Aspect.STONE, 2));
		ThaumcraftApi.registerObjectTag(getBID("hardIce"), getBMeta("hardIce"), (new AspectList()).add(Aspect.STONE, 2).add(Aspect.ICE, 2));
		ThaumcraftApi.registerObjectTag(getBID("originGrass"), getBMeta("originGrass"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("ashStone"), getBMeta("ashStone"), (new AspectList()).add(Aspect.STONE, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(getBID("hardSand"), getBMeta("hardSand"), (new AspectList()).add(Aspect.STONE, 1).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("hardDirt"), getBMeta("hardDirt"), (new AspectList()).add(Aspect.STONE, 1).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("holyGrass"), getBMeta("holyGrass"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.EARTH, 2).add(Aspect.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("holyDirt"), getBMeta("holyDirt"), (new AspectList()).add(Aspect.LIGHT, 1).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerObjectTag(getBID("holyStone"), getBMeta("holyStone"), (new AspectList()).add(Aspect.LIGHT, 1).add(Aspect.STONE, 2));
		ThaumcraftApi.registerObjectTag(getBID("crystal"), getBMeta("crystal"), (new AspectList()).add(Aspect.GREED, 58).add(Aspect.LIGHT, 15).add(Aspect.MAGIC, 20).add(Aspect.CRYSTAL, 50));
		ThaumcraftApi.registerObjectTag(getBID("cragRock"), getBMeta("cragRock"), (new AspectList()).add(Aspect.STONE, 2));
		ThaumcraftApi.registerObjectTag(getBID("quicksand"), getBMeta("quicksand"), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.TRAP, 4));
		ThaumcraftApi.registerObjectTag(getBID("smolderingGrass"), getBMeta("smolderingGrass"), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(getBID("amethystBlock"), getBMeta("amethystBlock"), (new AspectList()).add(Aspect.GREED, 58).add(Aspect.ORDER, 58).add(Aspect.CRYSTAL, 87));
		ThaumcraftApi.registerObjectTag(getBID("amethystOre"), getBMeta("amethystOre"), (new AspectList()).add(Aspect.GREED, 6).add(Aspect.ORDER, 6).add(Aspect.CRYSTAL, 14).add(Aspect.STONE, 4));
		ThaumcraftApi.registerObjectTag(getBID("redRockCobble"), getBMeta("redRockCobble"), (new AspectList()).add(Aspect.ENTROPY, 1).add(Aspect.STONE, 1));
		ThaumcraftApi.registerObjectTag(getBID("giantFlowerRed"), getBMeta("giantFlowerRed"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(getBID("giantFlowerYellow"), getBMeta("giantFlowerYellow"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(getBID("smallBoneSegment"), getBMeta("smallBoneSegment"), (new AspectList()).add(Aspect.FLESH, 1).add(Aspect.DEATH, 3));
		ThaumcraftApi.registerObjectTag(getBID("mediumBoneSegment"), getBMeta("mediumBoneSegment"), (new AspectList()).add(Aspect.FLESH, 2).add(Aspect.DEATH, 6));
		ThaumcraftApi.registerObjectTag(getBID("largeBoneSegment"), getBMeta("largeBoneSegment"), (new AspectList()).add(Aspect.FLESH, 4).add(Aspect.DEATH, 12));
		ThaumcraftApi.registerObjectTag(getBID("ivy"), getBMeta("ivy"), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.FIRE, 1));
		
		for (int i = 10; i < 15; i++)
		{
			ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, i, (new AspectList()).add(Aspect.GREED, 4).add(Aspect.CRYSTAL, 8));
		}
		
		String[] oreTypes = BlockBOPAmethyst.types;
		
		for (int i = 2; i < oreTypes.length; i+=2)
		{
			ThaumcraftApi.registerObjectTag(Blocks.amethystOre.get().blockID, i, (new AspectList()).add(Aspect.GREED, 3).add(Aspect.CRYSTAL, 7).add(Aspect.STONE, 4));
		}

		//Items
		/*Amethyst*/
		ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, 2, (new AspectList()).add(Aspect.GREED, 8).add(Aspect.ORDER, 8).add(Aspect.CRYSTAL, 8));
		/*Ashes*/
		ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, 1, (new AspectList()).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1));
		/*Celestial Crystal Shard*/
		ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, 4, (new AspectList()).add(Aspect.CRYSTAL, 8).add(Aspect.LIGHT, 2).add(Aspect.MAGIC, 2).add(Aspect.GREED, 4));
		/*Mudbrick*/
		ThaumcraftApi.registerObjectTag(Items.miscItems.get().itemID, 0, (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.FIRE, 1));
		/*Dart*/
		ThaumcraftApi.registerObjectTag(Items.dart.get().itemID, 0, (new AspectList()).add(Aspect.WEAPON, 1));
		/*Poison Dart*/
		ThaumcraftApi.registerObjectTag(Items.dart.get().itemID, 1, (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.WEAPON, 2));
		/*Spring Water Bucket*/
		ThaumcraftApi.registerObjectTag(Fluids.bopBucket.get().itemID, 0, (new AspectList()).add(Aspect.METAL, 13).add(Aspect.VOID, 1).add(Aspect.WATER, 4).add(Aspect.LIFE, 2).add(Aspect.HEAL, 4));
		/*Liquid Poison Bucket*/
		ThaumcraftApi.registerObjectTag(Fluids.bopBucket.get().itemID, 1, (new AspectList()).add(Aspect.METAL, 13).add(Aspect.VOID, 1).add(Aspect.WATER, 2).add(Aspect.WEAPON, 4).add(Aspect.POISON, 4));
		ThaumcraftApi.registerObjectTag(Items.food.get().itemID, 0, (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Items.mudball.get().itemID, 0, (new AspectList()).add(Aspect.WATER, 1).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerObjectTag(Items.bopDisc.get().itemID, -1, (new AspectList()).add(Aspect.SENSES, 12).add(Aspect.GREED, 4).add(Aspect.CROP, 4));
		ThaumcraftApi.registerObjectTag(Items.bopDiscMud.get().itemID, -1, (new AspectList()).add(Aspect.SENSES, 12).add(Aspect.GREED, 4).add(Aspect.EARTH, 2).add(Aspect.WATER, 2));

		//Placer Items
		ThaumcraftApi.registerObjectTag(getBID("barley"), getBMeta("barley"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(getBID("cattail"), getBMeta("cattail"), (new AspectList()).add(Aspect.WATER, 1).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("reed"), getBMeta("reed"), (new AspectList()).add(Aspect.TREE, 1).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("bamboo"), getBMeta("bamboo"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(getBID("sproutItem"), getBMeta("sproutItem"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("bushItem"), getBMeta("bushItem"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("poisonIvyItem"), getBMeta("poisonIvyItem"), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.WEAPON, 1));
		ThaumcraftApi.registerObjectTag(getBID("mediumGrassItem"), getBMeta("mediumGrassItem"), (new AspectList()).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("shortGrassItem"), getBMeta("shortGrassItem"), (new AspectList()).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("highGrassItem"), getBMeta("highGrassItem"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("bushItem"), getBMeta("bushItem"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("algae"), getBMeta("algae"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.WATER, 1));

		//Plants
		ThaumcraftApi.registerObjectTag(getBID("toadstool"), getBMeta("toadstool"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("portobello"), getBMeta("portobello"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("bluemilk"), getBMeta("bluemilk"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("glowshroom"), getBMeta("glowshroom"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("tinyCactus"), getBMeta("tinyCactus"), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.WEAPON, 1));
		ThaumcraftApi.registerObjectTag(getBID("holyTallGrass"), getBMeta("holyTallGrass"), (new AspectList()).add(Aspect.AIR, 1).add(Aspect.PLANT, 1).add(Aspect.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("desertSprouts"), getBMeta("desertSprouts"), (new AspectList()).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("duneGrass"), getBMeta("duneGrass"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(getBID("thorn"), getBMeta("thorn"), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.WEAPON, 1));
		ThaumcraftApi.registerObjectTag(getBID("desertGrass"), getBMeta("desertGrass"), (new AspectList()).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("deadGrass"), getBMeta("deadGrass"), (new AspectList()).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("treeMoss"), getBMeta("treeMoss"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("moss"), getBMeta("moss"), (new AspectList()).add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(getBID("willow"), getBMeta("willow"), (new AspectList()).add(Aspect.PLANT, 2));

		//Flowers
		ThaumcraftApi.registerObjectTag(getBID("violet"), getBMeta("violet"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("hydrangea"), getBMeta("hydrangea"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("deathbloom"), getBMeta("deathbloom"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(getBID("glowFlower"), getBMeta("glowFlower"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.LIGHT, 1));
		ThaumcraftApi.registerObjectTag(getBID("anenome"), getBMeta("anenome"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("swampFlower"), getBMeta("swampFlower"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("wildFlower"), getBMeta("wildFlower"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("cosmos"), getBMeta("cosmos"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("dandelion"), getBMeta("dandelion"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("daffodil"), getBMeta("daffodil"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("aloe"), getBMeta("aloe"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("clover"), getBMeta("clover"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(getBID("lilyflower"), getBMeta("lilyflower"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.PLANT, 1).add(Aspect.WATER, 1));
		ThaumcraftApi.registerObjectTag(getBID("rainbowflower"), getBMeta("rainbowflower"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.PLANT, 1).add(Aspect.LIGHT, 1).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(getBID("sunflower"), getBMeta("sunflower"), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.PLANT, 1).add(Aspect.LIGHT, 1));
		
		ThaumcraftApi.registerObjectTag(getBID("hibiscus"), getBMeta("hibiscus"), (new AspectList()).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(getBID("lilyofthevalley"), getBMeta("lilyofthevalley"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.POISON, 1));
		ThaumcraftApi.registerObjectTag(getBID("burningblossom"), getBMeta("burningblossom"), (new AspectList()).add(Aspect.PLANT, 4).add(Aspect.FIRE, 2));

	}

	private static int getBID(String name) {
		return BlockReferences.getBlockID(name);
	}

	private static int getBMeta(String name) {
		return BlockReferences.getBlockMeta(name);
	}
}
