package biomesoplenty.integration;

import biomesoplenty.api.Biomes;
import biomesoplenty.api.BlockReferences;
import cpw.mods.fml.common.event.FMLInterModComms;

public class BCIntegration {
	
	public static void init()
	{
		addFacades();
		excludeOilGeneration();
	}

	private static void excludeOilGeneration()
	{
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.promisedLandForest.get().biomeID));
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.promisedLandPlains.get().biomeID));
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.promisedLandShrub.get().biomeID));
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.promisedLandSwamp.get().biomeID));

		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.netherBase.get().biomeID));
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.netherBone.get().biomeID));
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.netherDesert.get().biomeID));
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.netherGarden.get().biomeID));
		FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", Integer.toString(Biomes.netherLava.get().biomeID));
	}

	private static void addFacades()
	{
		//Wood
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("acaciaLog") + "@" + getBMeta("acaciaLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("cherryLog") + "@" + getBMeta("cherryLog"));

		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("darkLog") + "@" + getBMeta("darkLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("firLog") + "@" + getBMeta("firLog"));

		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("holyLog") + "@" + getBMeta("holyLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("magicLog") + "@" + getBMeta("magicLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("mangroveLog") + "@" + getBMeta("mangroveLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("palmLog") + "@" + getBMeta("palmLog"));

		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("redwoodLog") + "@" + getBMeta("redwoodLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("willowLog") + "@" + getBMeta("willowLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("deadLog") + "@" + getBMeta("deadLog"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("bigFlowerStem") + "@" + getBMeta("bigFlowerStem"));

		//Planks
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("acaciaPlank") + "@" + getBMeta("acaciaPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("cherryPlank") + "@" + getBMeta("cherryPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("darkPlank") + "@" + getBMeta("darkPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("firPlank") + "@" + getBMeta("firPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("holyPlank") + "@" + getBMeta("holyPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("magicPlank") + "@" + getBMeta("magicPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("mangrovePlank") + "@" + getBMeta("mangrovePlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("palmPlank") + "@" + getBMeta("palmPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("redwoodPlank") + "@" + getBMeta("redwoodPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("willowPlank") + "@" + getBMeta("willowPlank"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("bambooThatching") + "@" + getBMeta("bambooThatching"));

		//Blocks
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("mud") + "@" + getBMeta("mud"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("driedDirt") + "@" + getBMeta("driedDirt"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("redRock") + "@" + getBMeta("redRock"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("ash") + "@" + getBMeta("ash"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("ashStone") + "@" + getBMeta("ashStone"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("hardIce") + "@" + getBMeta("hardIce"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("originGrass") + "@" + getBMeta("originGrass"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("hardSand") + "@" + getBMeta("hardSand"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("hardDirt") + "@" + getBMeta("hardDirt"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("holyGrass") + "@" + getBMeta("holyGrass"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("holyDirt") + "@" + getBMeta("holyDirt"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("holyStone") + "@" + getBMeta("holyStone"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("crystal") + "@" + getBMeta("crystal"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("cragRock") + "@" + getBMeta("cragRock"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("quicksand") + "@" + getBMeta("quicksand"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("smolderingGrass") + "@" + getBMeta("smolderingGrass"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("amethystBlock") + "@" + getBMeta("amethystBlock"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("amethystOre") + "@" + getBMeta("amethystOre"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("redRockCobble") + "@" + getBMeta("redRockCobble"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("giantFlowerRed") + "@" + getBMeta("giantFlowerRed"));
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", getBID("giantFlowerYellow") + "@" + getBMeta("giantFlowerYellow"));
	}

	private static int getBID(String name) {
		return BlockReferences.getBlockID(name);
	}

	private static int getBMeta(String name) {
		return BlockReferences.getBlockMeta(name);
	}
}
