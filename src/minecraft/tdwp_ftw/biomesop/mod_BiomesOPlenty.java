package tdwp_ftw.biomesop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.ChestGenHooks;
import tdwp_ftw.biomesop.armor.*;
import tdwp_ftw.biomesop.biomes.*;
import tdwp_ftw.biomesop.blocks.*;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.declarations.BOPItems;
import tdwp_ftw.biomesop.helpers.*;
import tdwp_ftw.biomesop.items.*;
import tdwp_ftw.biomesop.mobs.*;
import tdwp_ftw.biomesop.worldtype.WTBiomesOP;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="BiomesOPlenty", name="Biomes O' Plenty", version="0.4.9")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class mod_BiomesOPlenty
{	    
	// The instance of your mod that Forge uses.
	@Instance("BiomesOPlenty")
	public static mod_BiomesOPlenty instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="tdwp_ftw.biomesop.ClientProxy", serverSide="tdwp_ftw.biomesop.CommonProxy")
	public static CommonProxy proxy;

	public static int promisedLandDim = 20;

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{

		String[] soundFiles = { "bopdisc.ogg", "bopdiscmud.ogg"};

		for (String soundFile : soundFiles) try
		{
			File file = new File("resources/mod/streaming/" + soundFile);
			if (!file.exists()) {
				System.out.println("[BoP] " + soundFile + " doesn't exist, creating...");
				file.getParentFile().mkdirs();
				file.createNewFile();
				InputStream istream = getClass().getResourceAsStream("/mods/BiomesOPlenty/audio/" + soundFile);
				OutputStream out = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				int size = 0;
				int len;
				while ((len = istream.read(buf)) > 0) {
					out.write(buf, 0, len);
					size += len;
				}
				out.close();
				istream.close();
				if (size == 0) file.delete();
			}
		}
		catch (Exception e)
		{
			FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BoP] Failed to load sound file: " + soundFile);
			e.printStackTrace();
		}

		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		skyColors = true;
		biomeSize = config.get("Biome Settings", "Biome Size", 4, null).getInt();
		achievements = config.get("Achievement Settings", "Add Biomes O Plenty Achievemnets (Currently Broken)", false).getBoolean(false);
		addToDefault = config.get("Biome Settings", "Add Biomes To Default World", false).getBoolean(false);
		vanillaEnhanced = config.get("Biome Settings", "Enhanced Vanilla Biomes", true).getBoolean(false);
		promisedLandDimID = config.get("Dimension Settings", "Promised Land Dimension ID", 20, null).getInt();

		alpsGen = config.get("Biomes To Generate", "Alps", true).getBoolean(false);
		arcticGen = config.get("Biomes To Generate", "Arctic", true).getBoolean(false);
		badlandsGen = config.get("Biomes To Generate", "Badlands", true).getBoolean(false);
		bambooForestGen = config.get("Biomes To Generate", "BambooForest", true).getBoolean(false);
		bayouGen = config.get("Biomes To Generate", "Bayou", true).getBoolean(false);
		birchForestGen = config.get("Biomes To Generate", "BirchForest", true).getBoolean(false);
		bogGen = config.get("Biomes To Generate", "Bog", true).getBoolean(false);
		borealForestGen = config.get("Biomes To Generate", "BorealForest", true).getBoolean(false);
		canyonGen = config.get("Biomes To Generate", "Canyon", true).getBoolean(false);
		chaparralGen = config.get("Biomes To Generate", "Chaparral", true).getBoolean(false);
		cherryBlossomGroveGen = config.get("Biomes To Generate", "CherryBlossomGrove", true).getBoolean(false);
		coniferousForestGen = config.get("Biomes To Generate", "ConiferousForest", true).getBoolean(false);
		cragGen = config.get("Biomes To Generate", "Crag", true).getBoolean(false);
		deadForestGen = config.get("Biomes To Generate", "DeadForest", true).getBoolean(false);
		deadSwampGen = config.get("Biomes To Generate", "DeadSwamp", true).getBoolean(false);
		deadlandsGen = config.get("Biomes To Generate", "Deadlands", true).getBoolean(false);
		deciduousForestGen = config.get("Biomes To Generate", "DeciduousForest", true).getBoolean(false);
		desertGen = config.get("Biomes To Generate", "Desert", true).getBoolean(false);
		drylandsGen = config.get("Biomes To Generate", "Drylands", true).getBoolean(false);
		dunesGen = config.get("Biomes To Generate", "Dunes", true).getBoolean(false);
		extremeHillsGen = config.get("Biomes To Generate", "ExtremeHills", true).getBoolean(false);
		fenGen = config.get("Biomes To Generate", "Fen", true).getBoolean(false);
		fieldGen = config.get("Biomes To Generate", "Field", true).getBoolean(false);
		forestGen = config.get("Biomes To Generate", "Forest", true).getBoolean(false);
		frostForestGen = config.get("Biomes To Generate", "FrostForest", true).getBoolean(false);
		fungiForestGen = config.get("Biomes To Generate", "FungiForest", true).getBoolean(false);
		gardenGen = config.get("Biomes To Generate", "Garden", true).getBoolean(false);
		glacierGen = config.get("Biomes To Generate", "Glacier", true).getBoolean(false);
		grasslandGen = config.get("Biomes To Generate", "Grassland", true).getBoolean(false);
		groveGen = config.get("Biomes To Generate", "Grove", true).getBoolean(false);
		heathlandGen = config.get("Biomes To Generate", "Heathland", true).getBoolean(false);
		highlandGen = config.get("Biomes To Generate", "Highland", true).getBoolean(false);
		iceSheetGen = config.get("Biomes To Generate", "IcySheet", true).getBoolean(false);
		icyHillsGen = config.get("Biomes To Generate", "IcyHills", true).getBoolean(false);
		jadeCliffsGen = config.get("Biomes To Generate", "JadeCliffs", true).getBoolean(false);
		jungleGen = config.get("Biomes To Generate", "Jungle", true).getBoolean(false);
		lushDesertGen = config.get("Biomes To Generate", "LushDesert", true).getBoolean(false);
		lushSwampGen = config.get("Biomes To Generate", "LushSwamp", true).getBoolean(false);
		mangroveGen = config.get("Biomes To Generate", "Mangrove", true).getBoolean(false);
		mapleWoodsGen = config.get("Biomes To Generate", "MapleWoods", true).getBoolean(false);
		marshGen = config.get("Biomes To Generate", "Marsh", true).getBoolean(false);
		meadowGen = config.get("Biomes To Generate", "Meadow", true).getBoolean(false);
		mesaGen = config.get("Biomes To Generate", "Mesa", true).getBoolean(false);
		moorGen = config.get("Biomes To Generate", "Moor", true).getBoolean(false);
		mountainGen = config.get("Biomes To Generate", "Mountain", true).getBoolean(false);
		mushroomIslandGen = config.get("Biomes To Generate", "MushroomIsland", true).getBoolean(false);
		mysticGroveGen = config.get("Biomes To Generate", "MysticGrove", true).getBoolean(false);
		oasisGen = config.get("Biomes To Generate", "Oasis", true).getBoolean(false);
		ominousWoodsGen = config.get("Biomes To Generate", "OminousWoods", true).getBoolean(false);
		orchardGen = config.get("Biomes To Generate", "Orchard", true).getBoolean(false);
		originValleyGen = config.get("Biomes To Generate", "OriginValley", true).getBoolean(false);
		outbackGen = config.get("Biomes To Generate", "Outback", true).getBoolean(false);
		pastureGen = config.get("Biomes To Generate", "Pasture", true).getBoolean(false);
		plainsGen = config.get("Biomes To Generate", "Plains", true).getBoolean(false);
		prairieGen = config.get("Biomes To Generate", "Prairie", true).getBoolean(false);
		quagmireGen = config.get("Biomes To Generate", "Quagmire", true).getBoolean(false);
		rainforestGen = config.get("Biomes To Generate", "Rainforest", true).getBoolean(false);
		redwoodForestGen = config.get("Biomes To Generate", "RedwoodForest", true).getBoolean(false);
		sacredSpringsGen = config.get("Biomes To Generate", "SacredSprings", true).getBoolean(false);
		savannaGen = config.get("Biomes To Generate", "Savanna", true).getBoolean(false);
		scrublandGen = config.get("Biomes To Generate", "Scrubland", true).getBoolean(false);
		seasonalForestGen = config.get("Biomes To Generate", "SeasonalForest", true).getBoolean(false);
		shieldGen = config.get("Biomes To Generate", "Shield", true).getBoolean(false);
		shrublandGen = config.get("Biomes To Generate", "Shrubland", true).getBoolean(false);
		snowyWoodsGen = config.get("Biomes To Generate", "SnowyWoods", true).getBoolean(false);
		spruceWoodsGen = config.get("Biomes To Generate", "SpruceWoods", true).getBoolean(false);
		steppeGen = config.get("Biomes To Generate", "Steppe", true).getBoolean(false);
		swamplandGen = config.get("Biomes To Generate", "Swampland", true).getBoolean(false);
		swampwoodsGen = config.get("Biomes To Generate", "Swampwoods", true).getBoolean(false);
		taigaGen = config.get("Biomes To Generate", "Taiga", true).getBoolean(false);
		temperateRainforestGen = config.get("Biomes To Generate", "TemperateRainforest", true).getBoolean(false);
		thicketGen = config.get("Biomes To Generate", "Thicket", true).getBoolean(false);
		tropicalRainforestGen = config.get("Biomes To Generate", "TropicalRainforest", true).getBoolean(false);
		tropicsGen = config.get("Biomes To Generate", "Tropics", true).getBoolean(false);
		tundraGen = config.get("Biomes To Generate", "Tundra", true).getBoolean(false);
		volcanoGen = config.get("Biomes To Generate", "Volcano", true).getBoolean(false);
		wastelandGen = config.get("Biomes To Generate", "Wasteland", true).getBoolean(false);
		wetlandGen = config.get("Biomes To Generate", "Wetland", true).getBoolean(false);
		woodlandGen = config.get("Biomes To Generate", "Woodland", true).getBoolean(false);

		// Get Terrain Block ID's
		mudID = config.getTerrainBlock("Terrain Block IDs", "Mud ID", 160, null).getInt();
		driedDirtID = config.getTerrainBlock("Terrain Block IDs", "Dried Dirt ID", 161, null).getInt();
		redRockID = config.getTerrainBlock("Terrain Block IDs", "Red Rock ID", 162, null).getInt();
		ashID = config.getTerrainBlock("Terrain Block IDs", "Ash Block ID", 163, null).getInt();
		ashStoneID = config.getTerrainBlock("Terrain Block IDs", "Ash Stone ID", 164, null).getInt();
		hardIceID = config.getTerrainBlock("Terrain Block IDs", "Hard Ice ID", 165, null).getInt();
		originGrassID = config.getTerrainBlock("Terrain Block IDs", "Origin Grass ID", 166, null).getInt();
		hardSandID = config.getTerrainBlock("Terrain Block IDs", "Hard Sand ID", 167, null).getInt();
		hardDirtID = config.getTerrainBlock("Terrain Block IDs", "Hard Dirt ID", 168, null).getInt();
		holyGrassID = config.getTerrainBlock("Terrain Block IDs", "Holy Grass ID", 169, null).getInt();
		holyStoneID = config.getTerrainBlock("Terrain Block IDs", "Holy Stone ID", 170, null).getInt();
		cragRockID = config.getTerrainBlock("Terrain Block IDs", "Crag Rock ID", 171, null).getInt();

		// Get Crafted Block ID's
		mudBrickBlockID = config.getBlock("Mud Bricks ID", 256, null).getInt();
		redwoodPlankID = config.getBlock("Redwood Plank ID", 257, null).getInt();
		redwoodDoubleSlabID = config.getBlock("Redwood Double Slab ID", 258, null).getInt();
		redwoodSingleSlabID = config.getBlock("Redwood Single Slab ID", 259, null).getInt();
		redwoodStairsID = config.getBlock("Redwood Stairs ID", 260, null).getInt();
		willowPlankID = config.getBlock("Willow Plank ID", 261, null).getInt();
		willowDoubleSlabID = config.getBlock("Willow Double Slab ID", 262, null).getInt();
		willowSingleSlabID = config.getBlock("Willow Single Slab ID", 263, null).getInt();
		willowStairsID = config.getBlock("Willow Stairs ID", 264, null).getInt();
		firPlankID = config.getBlock("Fir Plank ID", 265, null).getInt();
		firDoubleSlabID = config.getBlock("Fir Double Slab ID", 266, null).getInt();
		firSingleSlabID = config.getBlock("Fir Single Slab ID", 267, null).getInt();
		firStairsID = config.getBlock("Fir Stairs ID", 268, null).getInt();
		acaciaPlankID = config.getBlock("Acacia Plank ID", 269, null).getInt();
		acaciaDoubleSlabID = config.getBlock("Acacia Double Slab ID", 270, null).getInt();
		acaciaSingleSlabID = config.getBlock("Acacia Single Slab ID", 271, null).getInt();
		acaciaStairsID = config.getBlock("Acacia Stairs ID", 272, null).getInt();
		cherryPlankID = config.getBlock("Cherry Plank ID", 273, null).getInt();
		cherryDoubleSlabID = config.getBlock("Cherry Double Slab ID", 274, null).getInt();
		cherrySingleSlabID = config.getBlock("Cherry Single Slab ID", 275, null).getInt();
		cherryStairsID = config.getBlock("Cherry Stairs ID", 276, null).getInt();
		darkPlankID = config.getBlock("Dark Plank ID", 277, null).getInt();
		darkDoubleSlabID = config.getBlock("Dark Double Slab ID", 278, null).getInt();
		darkSingleSlabID = config.getBlock("Dark Single Slab ID", 279, null).getInt();
		darkStairsID = config.getBlock("Dark Stairs ID", 280, null).getInt();
		magicPlankID = config.getBlock("Magic Plank ID", 281, null).getInt();
		magicDoubleSlabID = config.getBlock("Magic Double Slab ID", 282, null).getInt();
		magicSingleSlabID = config.getBlock("Magic Single Slab ID", 283, null).getInt();
		magicStairsID = config.getBlock("Magic Stairs ID", 284, null).getInt();
		palmPlankID = config.getBlock("Palm Plank ID", 285, null).getInt();
		palmDoubleSlabID = config.getBlock("Palm Double Slab ID", 286, null).getInt();
		palmSingleSlabID = config.getBlock("Palm Single Slab ID", 287, null).getInt();
		palmStairsID = config.getBlock("Palm Stairs ID", 288, null).getInt();
		originLeavesID = config.getBlock("Origin Leaves ID", 289, null).getInt();
		redwoodWoodID = config.getBlock("Redwood Log ID", 290, null).getInt();
		redwoodLeavesID = config.getBlock("Redwood Leaves ID", 291, null).getInt();
		willowWoodID = config.getBlock("Willow Log ID", 292, null).getInt();
		willowLeavesID = config.getBlock("Willow Leaves ID", 293, null).getInt();
		firWoodID = config.getBlock("Fir Log ID", 294, null).getInt();
		firLeavesID = config.getBlock("Fir Leaves ID", 295, null).getInt();
		acaciaWoodID = config.getBlock("Acacia Log ID", 296, null).getInt();
		acaciaLeavesID = config.getBlock("Acacia Leaves ID", 297, null).getInt();
		cherryWoodID = config.getBlock("Cherry Log ID", 298, null).getInt();
		pinkFlowerID = config.getBlock("Pink Flower ID", 299, null).getInt();
		darkWoodID = config.getBlock("Dark Log ID", 300, null).getInt();
		darkLeavesID = config.getBlock("Dark Leaves ID", 301, null).getInt();
		treeMossID = config.getBlock("Tree Moss ID", 302, null).getInt();
		magicWoodID = config.getBlock("Magic Log ID", 303, null).getInt();
		deadWoodID = config.getBlock("Dead Log ID", 304, null).getInt();
		appleLeavesFruitlessID = config.getBlock("Fruitless Apple Leaves ID", 305, null).getInt();
		barleyID = config.getBlock("Barley ID", 306, null).getInt();
		palmWoodID = config.getBlock("Palm Log ID", 307, null).getInt();
		palmLeavesID = config.getBlock("Palm Leaves ID", 308, null).getInt();
		giantFlowerRedID = config.getBlock("Giant Red Flower ID", 309, null).getInt();
		giantFlowerStemID = config.getBlock("Giant Flower Stem ID", 310, null).getInt();
		giantFlowerYellowID = config.getBlock("Giant Yellow Flower ID", 311, null).getInt();
		redLeavesID = config.getBlock("Maple Leaves ID", 312, null).getInt();
		orangeLeavesID = config.getBlock("Orange Autumn Leaves ID", 313, null).getInt();
		pinkLeavesID = config.getBlock("Pink Cherry Leaves ID", 314, null).getInt();
		blueLeavesID = config.getBlock("Magic Leaves ID", 315, null).getInt();
		whiteLeavesID = config.getBlock("White Cherry Leaves ID", 316, null).getInt();
		deadLeavesID = config.getBlock("Dying Leaves ID", 317, null).getInt();
		shortGrassID = config.getBlock("Short Grass ID", 318, null).getInt();
		appleLeavesID = config.getBlock("Apple Leaves ID", 319, null).getInt();
		sproutID = config.getBlock("Sprout ID", 320, null).getInt();
		bushID = config.getBlock("Bush ID", 321, null).getInt();
		bambooID = config.getBlock("Bamboo ID", 322, null).getInt();
		bambooLeavesID = config.getBlock("Bamboo Leaves ID", 323, null).getInt();
		deadGrassID = config.getBlock("Dead Grass ID", 324, null).getInt();
		desertGrassID = config.getBlock("Desert Grass ID", 325, null).getInt();
		whiteFlowerID = config.getBlock("Anenome ID", 326, null).getInt();
		blueFlowerID = config.getBlock("Swampflower ID", 327, null).getInt();
		purpleFlowerID = config.getBlock("Wildflower ID", 328, null).getInt();
		orangeFlowerID = config.getBlock("Daisy ID", 329, null).getInt();
		tinyFlowerID = config.getBlock("Clover ID", 330, null).getInt();
		glowFlowerID = config.getBlock("Glowflower ID", 331, null).getInt();
		cattailID = config.getBlock("Cattail ID", 332, null).getInt();
		willowID = config.getBlock("Willow ID", 333, null).getInt();
		autumnLeavesID = config.getBlock("Yellow Autumn Leaves ID", 334, null).getInt();
		thornID = config.getBlock("Thorns ID", 335, null).getInt();
		toadstoolID = config.getBlock("Toadstool ID", 336, null).getInt();
		highGrassBottomID = config.getBlock("High Grass Bottom ID", 337, null).getInt();
		highGrassTopID = config.getBlock("High Grass Top ID", 338, null).getInt();
		tinyCactusID = config.getBlock("Tiny Cactus ID", 339, null).getInt();
		firSaplingID = config.getBlock("Fir Sapling ID", 340, null).getInt();
		redwoodSaplingID = config.getBlock("Redwood Sapling ID", 341, null).getInt();
		palmSaplingID = config.getBlock("Palm Sapling ID", 342, null).getInt();
		redSaplingID = config.getBlock("Maple Sapling ID", 343, null).getInt();
		orangeSaplingID = config.getBlock("Orange Autumn Sapling ID", 344, null).getInt();
		yellowSaplingID = config.getBlock("Yellow Autumn Sapling ID", 345, null).getInt();
		brownSaplingID = config.getBlock("Dying Sapling ID", 346, null).getInt();
		willowSaplingID = config.getBlock("Willow Sapling ID", 347, null).getInt();
		appleSaplingID = config.getBlock("Apple Sapling ID", 348, null).getInt();
		originSaplingID = config.getBlock("Origin Sapling ID", 349, null).getInt();
		pinkSaplingID = config.getBlock("Pink Cherry Sapling ID", 350, null).getInt();
		whiteSaplingID = config.getBlock("White Cherry Sapling ID", 351, null).getInt();
		darkSaplingID = config.getBlock("Dark Sapling ID", 352, null).getInt();
		magicSaplingID = config.getBlock("Magic Sapling ID", 353, null).getInt();
		deathbloomID = config.getBlock("Deathbloom ID", 354, null).getInt();
		redRockCobbleID = config.getBlock("Red Rock Cobblestone ID", 355, null).getInt();
		redRockBrickID = config.getBlock("Red Rock Bricks ID", 356, null).getInt();
		hydrangeaID = config.getBlock("Hydrangea ID", 357, null).getInt();
		violetID = config.getBlock("Violet ID", 358, null).getInt();
		mediumGrassID = config.getBlock("Medium Grass ID", 359, null).getInt();
		duneGrassID = config.getBlock("Dune Grass ID", 360, null).getInt();
		desertSproutsID = config.getBlock("Desert Sprouts ID", 361, null).getInt();
		redRockCobbleDoubleSlabID = config.getBlock("Red Rock Cobblestone Double Slab ID", 362, null).getInt();
		redRockCobbleSingleSlabID = config.getBlock("Red Rock Cobblestone Single Slab ID", 363, null).getInt();
		redRockCobbleStairsID = config.getBlock("Red Rock Cobblestone Stairs ID", 364, null).getInt();
		redRockBrickDoubleSlabID = config.getBlock("Red Rock Brick Double Slab ID", 365, null).getInt();
		redRockBrickSingleSlabID = config.getBlock("Red Rock Brick Single Slab ID", 366, null).getInt();
		redRockBrickStairsID = config.getBlock("Red Rock Brick Stairs ID", 367, null).getInt();
		mudBrickDoubleSlabID = config.getBlock("Mud Brick Double Slab ID", 368, null).getInt();
		mudBrickSingleSlabID = config.getBlock("Mud Brick Single Slab ID", 369, null).getInt();
		mudBrickStairsID = config.getBlock("Mud Brick Stairs ID", 370, null).getInt();
		mangroveWoodID = config.getBlock("Mangrove Log ID", 371, null).getInt();
		mangroveLeavesID = config.getBlock("Mangrove Leaves ID", 372, null).getInt();
		mangroveSaplingID = config.getBlock("Mangrove Sapling ID", 373, null).getInt();
		mangrovePlankID = config.getBlock("Mangrove Plank ID", 374, null).getInt();
		mangroveDoubleSlabID = config.getBlock("Mangrove Double Slab ID", 375, null).getInt();
		mangroveSingleSlabID = config.getBlock("Mangrove Single Slab ID", 376, null).getInt();
		mangroveStairsID = config.getBlock("Mangrove Stairs ID", 377, null).getInt();
		acaciaSaplingID = config.getBlock("Acacia Sapling ID", 378, null).getInt();
		holyTallGrassID = config.getBlock("Holy Tall Grass ID", 379, null).getInt();
		promisedLandPortalID = config.getBlock("Promised Land Portal ID", 380, null).getInt();
		holyWoodID = config.getBlock("Holy Log ID", 381, null).getInt();
		holyLeavesID = config.getBlock("Holy Leaves ID", 382, null).getInt();
		holySaplingID = config.getBlock("Holy Sapling ID", 383, null).getInt();
		holyPlankID = config.getBlock("Holy Plank ID", 384, null).getInt();
		holyDoubleSlabID = config.getBlock("Holy Double Slab ID", 385, null).getInt();
		holySingleSlabID = config.getBlock("Holy Single Slab ID", 386, null).getInt();
		holyStairsID = config.getBlock("Holy Stairs ID", 387, null).getInt();
		amethystOreID = config.getBlock("Amethyst Ore ID", 388, null).getInt();
		amethystBlockID = config.getBlock("Block of Amethyst ID", 389, null).getInt();
		bambooThatchingID = config.getBlock("Bamboo Thatching ID", 390, null).getInt();
		mossID = config.getBlock("Moss ID", 391, null).getInt();
		algaeID = config.getBlock("Algae ID", 392, null).getInt();
		smolderingGrassID = config.getBlock("Smoldering Grass ID", 393, null).getInt();
		quicksandID = config.getBlock("Quicksand ID", 394, null).getInt();

		// Get Item ID's
		shroomPowderID = config.getItem("Shroom Powder ID", 1001, null).getInt();
		mudBallID = config.getItem("Mud Ball ID", 1002, null).getInt();
		mudBrickID = config.getItem("Mud Brick ID", 1003, null).getInt();
		bambooItemID = config.getItem("Bamboo ID", 1004).getInt();
		cattailItemID = config.getItem("Cattail ID", 1005).getInt();
		ancientStaffID = config.getItem("Ancient Staff ID", 1006).getInt();
		enderporterID = config.getItem("Enderporter ID", 1007).getInt();
		ashesID = config.getItem("Pile of Ashes ID", 1008, null).getInt();
		barleyItemID = config.getItem("Barley ID", 1009).getInt();
		amethystID = config.getItem("Amethyst ID", 1010).getInt();
		ancientStaffHandleID = config.getItem("Ancient Staff Handle ID", 1011, null).getInt();
		ancientStaffPoleID = config.getItem("Ancient Staff Pole ID", 1012, null).getInt();
		ancientStaffTopperID = config.getItem("Ancient Staff Topper ID", 1013, null).getInt();
		shortGrassItemID = config.getItem("Short Grass (Item) ID", 1014, null).getInt();
		mediumGrassItemID = config.getItem("Medium Grass (Item) ID", 1015, null).getInt();
		bushItemID = config.getItem("Bush (Item) ID", 1016, null).getInt();
		sproutItemID = config.getItem("Sprout (Item) ID", 1017, null).getInt();
		mossItemID = config.getItem("Moss (Item) ID", 1018, null).getInt();
		bopDiscID = config.getItem("Traversia Music Disc ID", 1019, null).getInt();
		bopDiscMudID = config.getItem("Muddy Music Disc ID", 1020, null).getInt();
		swordMudID = config.getItem("Muddy Sword ID", 1060, null).getInt();
		shovelMudID = config.getItem("Muddy Shovel ID", 1061, null).getInt();
		pickaxeMudID = config.getItem("Muddy Pickaxe ID", 1062, null).getInt();
		axeMudID = config.getItem("Muddy Axe ID", 1063, null).getInt();
		hoeMudID = config.getItem("Muddy Hoe ID", 1064, null).getInt();
		helmetMudID = config.getItem("Muddy Helmet ID", 1065, null).getInt();
		chestplateMudID = config.getItem("Muddy Chestplate ID", 1066, null).getInt();
		leggingsMudID = config.getItem("Muddy Leggings ID", 1067, null).getInt();
		bootsMudID = config.getItem("Muddy Boots ID", 1068, null).getInt();
		swordAmethystID = config.getItem("Amethyst Sword ID", 1069, null).getInt();
		shovelAmethystID = config.getItem("Amethyst Shovel ID", 1070, null).getInt();
		pickaxeAmethystID = config.getItem("Amethyst Pickaxe ID", 1071, null).getInt();
		axeAmethystID = config.getItem("Amethyst Axe ID", 1072, null).getInt();
		hoeAmethystID = config.getItem("Amethyst Hoe ID", 1073, null).getInt();
		helmetAmethystID = config.getItem("Amethyst Helmet ID", 1074, null).getInt();
		chestplateAmethystID = config.getItem("Amethyst Chestplate ID", 1075, null).getInt();
		leggingsAmethystID = config.getItem("Amethyst Leggings ID", 1076, null).getInt();
		bootsAmethystID = config.getItem("Amethyst Boots ID", 1077, null).getInt();

		//Mob IDs
		jungleSpiderID = config.get("Mob IDs", "Jungle Spider ID", 101, null).getInt();
		rosesterID = config.get("Mob IDs", "Rosester ID", 102, null).getInt();

		System.out.println("Generating Biome ID's");
		alpsID = config.get("Biome IDs", "Alps ID", 23).getInt();
		arcticID = config.get("Biome IDs", "Arctic ID", 24).getInt();
		badlandsID = config.get("Biome IDs", "Badlands ID", 25).getInt();
		bambooForestID = config.get("Biome IDs", "Bamboo Forest ID", 26).getInt();
		bayouID = config.get("Biome IDs", "Bayou ID", 27).getInt();
		birchForestID = config.get("Biome IDs", "Birch Forest ID", 28).getInt();
		bogID = config.get("Biome IDs", "Bog ID", 29).getInt();
		borealForestID = config.get("Biome IDs", "Boreal Forest ID", 30).getInt();
		canyonID = config.get("Biome IDs", "Canyon ID", 31).getInt();
		chaparralID = config.get("Biome IDs", "Chaparral ID", 32).getInt();
		cherryBlossomGroveID = config.get("Biome IDs", "Cherry Blossom Grove ID", 33).getInt();
		coniferousForestID = config.get("Biome IDs", "Coniferous Forest ID", 34).getInt();
		cragID = config.get("Biome IDs", "Crag ID", 35).getInt();
		deadForestID = config.get("Biome IDs", "Dead Forest ID", 36).getInt();
		deadSwampID = config.get("Biome IDs", "Dead Swamp ID", 37).getInt();
		deadlandsID = config.get("Biome IDs", "Deadlands ID", 38).getInt();
		deciduousForestID = config.get("Biome IDs", "Deciduous Forest ID", 39).getInt();
		drylandsID = config.get("Biome IDs", "Drylands ID", 40).getInt();
		dunesID = config.get("Biome IDs", "Dunes ID", 41).getInt();
		fenID = config.get("Biome IDs", "Fen ID", 42).getInt();
		fieldID = config.get("Biome IDs", "Field ID", 43).getInt();
		frostForestID = config.get("Biome IDs", "Frost Forest ID", 44).getInt();
		fungiForestID = config.get("Biome IDs", "Fungi Forest ID", 45).getInt();
		gardenID = config.get("Biome IDs", "Garden ID", 46).getInt();
		glacierID = config.get("Biome IDs", "Glacier ID", 47).getInt();
		grasslandID = config.get("Biome IDs", "Grassland ID", 48).getInt();
		groveID = config.get("Biome IDs", "Grove ID", 49).getInt();
		heathlandID = config.get("Biome IDs", "Heathland ID", 50).getInt();
		highlandID = config.get("Biome IDs", "Highland ID", 51).getInt();
		iceSheetID = config.get("Biome IDs", "Ice Sheet ID", 52).getInt();
		icyHillsID = config.get("Biome IDs", "Icy Hills ID", 53).getInt();
		jadeCliffsID = config.get("Biome IDs", "Jade Cliffs ID", 54).getInt();
		lushDesertID = config.get("Biome IDs", "Lush Desert ID", 55).getInt();
		lushSwampID = config.get("Biome IDs", "Lush Swamp ID", 56).getInt();
		mangroveID = config.get("Biome IDs", "Mangrove ID", 57).getInt();
		mapleWoodsID = config.get("Biome IDs", "Maple Woods ID", 58).getInt();
		marshID = config.get("Biome IDs", "Marsh ID", 59).getInt();
		meadowID = config.get("Biome IDs", "Meadow ID", 60).getInt();
		mesaID = config.get("Biome IDs", "Mesa ID", 61).getInt();
		moorID = config.get("Biome IDs", "Moor ID", 62).getInt();
		mountainID = config.get("Biome IDs", "Mountain ID", 63).getInt();
		mysticGroveID = config.get("Biome IDs", "Mystic Grove ID", 64).getInt();
		oasisID = config.get("Biome IDs", "Oasis ID", 65).getInt();
		ominousWoodsID = config.get("Biome IDs", "Ominous Woods ID", 66).getInt();
		orchardID = config.get("Biome IDs", "Orchard ID", 67).getInt();
		originValleyID = config.get("Biome IDs", "Origin Valley ID", 68).getInt();
		outbackID = config.get("Biome IDs", "Outback ID", 69).getInt();
		pastureID = config.get("Biome IDs", "Pasture ID", 70).getInt();
		prairieID = config.get("Biome IDs", "Prairie ID", 71).getInt();
		promisedLandID = config.get("Biome IDs", "Promised Land ID", 72).getInt();
		quagmireID = config.get("Biome IDs", "Quagmire ID", 73).getInt();
		rainforestID = config.get("Biome IDs", "Rainforest ID", 74).getInt();
		redwoodForestID = config.get("Biome IDs", "Redwood Forest ID", 75).getInt();
		sacredSpringsID = config.get("Biome IDs", "Sacred Springs ID", 76).getInt();
		savannaID = config.get("Biome IDs", "Savanna ID", 77).getInt();
		scrublandID = config.get("Biome IDs", "Scrubland ID", 78).getInt();
		seasonalForestID = config.get("Biome IDs", "Seasonal Forest ID", 79).getInt();
		shieldID = config.get("Biome IDs", "Shield ID", 80).getInt();
		shoreID = config.get("Biome IDs", "Shore ID", 81).getInt();
		shrublandID = config.get("Biome IDs", "Shrubland ID", 82).getInt();
		snowyWoodsID = config.get("Biome IDs", "Snowy Woods ID", 83).getInt();
		spruceWoodsID = config.get("Biome IDs", "Spruce Woods ID", 84).getInt();
		steppeID = config.get("Biome IDs", "Steppe ID", 85).getInt();
		swampwoodsID = config.get("Biome IDs", "Swampwoods ID", 86).getInt();
		temperateRainforestID = config.get("Biome IDs", "Temperate Rainforest ID", 87).getInt();
		thicketID = config.get("Biome IDs", "Thicket ID", 88).getInt();
		tropicalRainforestID = config.get("Biome IDs", "Tropical Rainforest ID", 89).getInt();
		tropicsID = config.get("Biome IDs", "Tropics ID", 90).getInt();
		tundraID = config.get("Biome IDs", "Tundra ID", 91).getInt();
		volcanoID = config.get("Biome IDs", "Volcano ID", 92).getInt();
		wastelandID = config.get("Biome IDs", "Wasteland ID", 93).getInt();
		wetlandID = config.get("Biome IDs", "Wetland ID", 94).getInt();
		woodlandID = config.get("Biome IDs", "Woodland ID", 95).getInt();

		plainsNewID = config.get("Biome IDs", "Plains (New) ID", 96).getInt();
		desertNewID = config.get("Biome IDs", "Desert (New) ID", 97).getInt();
		forestNewID = config.get("Biome IDs", "Forest (New) ID", 98).getInt();
		taigaNewID = config.get("Biome IDs", "Taiga (New) ID", 99).getInt();
		swamplandNewID = config.get("Biome IDs", "Swampland (New) ID", 100).getInt();
		extremeHillsNewID = config.get("Biome IDs", "Extreme Hills (New) ID", 101).getInt();
		jungleNewID = config.get("Biome IDs", "Jungle (New) ID", 102).getInt();

		System.out.println("Generated!");
		config.save();
		
		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");
		
		BOPBlocks.init();
		
		BOPItems.init();
		
		BOPBlocks.dependantinit();
	}

	@Init
	public void load(FMLInitializationEvent event)
	{

		// Achievement declaration
		if (achievements == true)
		{
			achFlower2 = (new Achievement(3057, "achFlower2", 0, 0, Block.plantRed, null)).registerAchievement();
			achRedRock2 = (new Achievement(3058, "achRedRock2", -1, 2, BOPBlocks.redRock, achFlower2)).registerAchievement();
			achThorn2 = (new Achievement(3059, "achThorn2", 2, 1, BOPBlocks.thorn, achFlower2)).registerAchievement();
			achAsh2 = (new Achievement(3060, "achAsh2", 1, 3, BOPItems.ashes, achFlower2)).registerAchievement();
			achOrigin2 = (new Achievement(3061, "achOrigin2", 0, 5, BOPBlocks.originGrass, achFlower2)).setSpecial().registerAchievement();
			achPromised2 = (new Achievement(3062, "achPromised2", 0, -5, BOPBlocks.holyGrass, achFlower2)).setSpecial().registerAchievement();
			achMud2 = (new Achievement(3063, "achMud2", -2, -1, BOPItems.mudBall, achFlower2)).registerAchievement();
			achShroom2 = (new Achievement(3064, "achShroom2", 1, -2, BOPBlocks.toadstool, achFlower2)).registerAchievement();
			achBarley2 = (new Achievement(3065, "achBarley2", -2, 4, BOPItems.barleyItem, achFlower2)).registerAchievement();
			achMoss2 = (new Achievement(3066, "achMoss2", -1, -3, BOPItems.mossItem, achFlower2)).registerAchievement();

			pageBOP = new AchievementPage("Biomes O\' Plenty", new Achievement[] {achFlower2, achRedRock2, achThorn2, achAsh2, achOrigin2, achPromised2, achMud2, achShroom2, achBarley2, achMoss2});
			AchievementPage.registerAchievementPage(pageBOP);
		}

		LanguageRegistry.instance().addStringLocalization("itemGroup.tabBiomesOPlenty", "en_US", "Biomes O\' Plenty");

		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystBlock, "pickaxe", 3);

		if (achievements == true)
		{
			// Add Achievement registration
			addAchievementDesc("achFlower2", "Flower Child", "Pick some flowers!");
			addAchievementDesc("achRedRock2", "Red Rocky Mountain High", "Dig out some red rocks.");
			addAchievementDesc("achThorn2", "Rather Thorny...", "Don\'t get cut!");
			addAchievementDesc("achAsh2", "Ash-ievement", "Get it?  \'Cause it\'s ash.");
			addAchievementDesc("achOrigin2", "Alpha...", "Get some grass from the Origin Valley.");
			addAchievementDesc("achPromised2", "...Omega", "Welcome to the Promised Land!");
			addAchievementDesc("achMud2", "Sticky Situation", "I just had these boots cleaned!");
			addAchievementDesc("achShroom2", "Trippin\'", "Don\'t try this at home, kids!");
			addAchievementDesc("achBarley2", "Fields Of Gold", "Upon the fields of barley.");
			addAchievementDesc("achMoss2", "Mossman", "Mothman's long-lost cousin.");
		}

		LanguageRegistry.instance().addStringLocalization("generator.BIOMESOP", "en_US", "Biomes O\' Plenty");

		// Add helpers for compatibility
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldTypeSize());
		MinecraftForge.EVENT_BUS.register(new AchievementPickup());
		MinecraftForge.EVENT_BUS.register(new BonemealUse());

		proxy.registerRenderers();


		// Initialize biomes
		alps = (new BiomeGenAlps(alpsID)).setColor(353825).setBiomeName("Alps").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(5.0F, 5.0F);
		arctic = (new BiomeGenArctic(arcticID)).setColor(14090235).setBiomeName("Arctic").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.7F);
		badlands = (new BiomeGenBadlands(badlandsID)).setColor(16421912).setBiomeName("Badlands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.9F);
		bambooForest = (new BiomeGenBambooForest(bambooForestID)).setColor(112).setBiomeName("Bamboo Forest").setMinMaxHeight(0.0F, 0.3F);
		bayou = (new BiomeGenBayou(bayouID)).setColor(522674).setBiomeName("Bayou").func_76733_a(9154376).setMinMaxHeight(-0.3F, 0.2F);
		birchForest = (new BiomeGenBirchForest(birchForestID)).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.3F);
		bog = (new BiomeGenBog(bogID)).setColor(522674).setBiomeName("Bog").func_76733_a(9154376).setMinMaxHeight(-0.3F, -0.1F).setTemperatureRainfall(0.8F, 0.9F);
		borealForest = (new BiomeGenBorealForest(borealForestID)).setColor(353825).setBiomeName("Boreal Forest").func_76733_a(5159473).setMinMaxHeight(0.0F, 1.0F).setTemperatureRainfall(0.6F, 0.7F);
		canyon = (new BiomeGenCanyon(canyonID)).setColor(9286496).setBiomeName("Canyon").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(3.0F, 5.0F);
		chaparral = (new BiomeGenChaparral(chaparralID)).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F);
		cherryBlossomGrove = (new BiomeGenCherryBlossomGrove(cherryBlossomGroveID)).setColor(9286496).setBiomeName("Cherry Blossom Grove").setMinMaxHeight(0.1F, 0.2F);
		coniferousForest = (new BiomeGenConiferousForest(coniferousForestID)).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.4F).setMinMaxHeight(0.1F, 0.8F);
		crag = (new BiomeGenCrag(cragID)).setColor(9286496).setBiomeName("Crag").setMinMaxHeight(0.0F, 9.9F);
		deadForest = (new BiomeGenDeadForest(deadForestID)).setColor(522674).setBiomeName("Dead Forest").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.7F);
		deadSwamp = (new BiomeGenDeadSwamp(deadSwampID)).setColor(522674).setBiomeName("Dead Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
		deadlands = (new BiomeGenDeadlands(deadlandsID)).setColor(522674).setBiomeName("Deadlands").setDisableRain().func_76733_a(9154376).setMinMaxHeight(0.1F, 0.5F);
		deciduousForest = (new BiomeGenDeciduousForest(deciduousForestID)).setColor(353825).setBiomeName("Deciduous Forest").func_76733_a(5159473);
		drylands = (new BiomeGenDrylands(drylandsID)).setColor(16421912).setBiomeName("Drylands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.5F);
		dunes = (new BiomeGenDunes(dunesID)).setColor(13786898).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.3F);
		fen = (new BiomeGenFen(fenID)).setColor(9286496).setBiomeName("Fen").setTemperatureRainfall(0.4F, 0.0F).setMinMaxHeight(-0.2F, 0.1F);
		field = (new BiomeGenField(fieldID)).setColor(9286496).setBiomeName("Field").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F);
		frostForest = (new BiomeGenFrostForest(frostForestID)).setColor(14090235).setBiomeName("Frost Forest").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
		fungiForest = (new BiomeGenFungiForest(fungiForestID)).setColor(747097).setBiomeName("Fungi Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.0F, 0.4F);
		garden = (new BiomeGenGarden(gardenID)).setColor(9286496).setBiomeName("Garden").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.2F);
		glacier = (new BiomeGenGlacier(glacierID)).setColor(6316128).setBiomeName("Glacier").setEnableSnow().setMinMaxHeight(0.4F, 1.0F).setTemperatureRainfall(0.0F, 0.0F);
		grassland = (new BiomeGenGrassland(grasslandID)).setColor(9286496).setBiomeName("Grassland").setTemperatureRainfall(0.7F, 0.7F).setMinMaxHeight(0.2F, 0.2F);
		grove = (new BiomeGenGrove(groveID)).setColor(9286496).setBiomeName("Grove").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F);
		heathland = (new BiomeGenHeathland(heathlandID)).setColor(353825).setBiomeName("Heathland").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.1F, 0.3F);
		highland = (new BiomeGenHighland(highlandID)).setColor(6316128).setBiomeName("Highland").setMinMaxHeight(0.9F, 1.9F).setTemperatureRainfall(0.5F, 0.5F);
		iceSheet = (new BiomeGenIceSheet(iceSheetID)).setColor(6316128).setBiomeName("Ice Sheet").setEnableSnow().setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.0F, 0.0F);
		icyHills = (new BiomeGenIcyHills(icyHillsID)).setColor(14090235).setBiomeName("Icy Hills").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(-0.2F, 0.5F);
		jadeCliffs = (new BiomeGenJadeCliffs(jadeCliffsID)).setColor(14090235).setBiomeName("Jade Cliffs").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.1F, 2.0F);
		lushDesert = (new BiomeGenLushDesert(lushDesertID)).setColor(16421912).setBiomeName("Lush Desert").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.8F);
		lushSwamp = (new BiomeGenLushSwamp(lushSwampID)).setColor(522674).setBiomeName("Lush Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.7F, 1.0F);
		mangrove = (new BiomeGenMangrove(mangroveID)).setColor(16440917).setBiomeName("Mangrove").setMinMaxHeight(-0.4F, -0.1F);
		mapleWoods = (new BiomeGenMapleWoods(mapleWoodsID)).setColor(747097).setBiomeName("Maple Woods").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.1F, 0.6F);
		marsh = (new BiomeGenMarsh(marshID)).setColor(10486015).setBiomeName("Marsh").setMinMaxHeight(-0.5F, 0.0F);
		meadow = (new BiomeGenMeadow(meadowID)).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 0.7F);
		mesa = (new BiomeGenMesa(mesaID)).setColor(16421912).setBiomeName("Mesa").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.8F, 1.0F);
		moor = (new BiomeGenMoor(moorID)).setColor(16421912).setBiomeName("Moor").setTemperatureRainfall(0.5F, 1.0F).setMinMaxHeight(0.7F, 0.8F);
		mountain = (new BiomeGenMountain(mountainID)).setColor(14090235).setBiomeName("Mountain").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(1.2F, 1.2F);
		mysticGrove = (new BiomeGenMysticGrove(mysticGroveID)).setColor(353825).setBiomeName("Mystic Grove").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F);
		oasis = (new BiomeGenOasis(oasisID)).setColor(16421912).setBiomeName("Oasis").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.2F);
		ominousWoods = (new BiomeGenOminousWoods(ominousWoodsID)).setColor(353825).setBiomeName("Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F);
		orchard = (new BiomeGenOrchard(orchardID)).setColor(9286496).setBiomeName("Orchard").setTemperatureRainfall(0.8F, 0.4F);
		originValley = (new BiomeGenOriginValley(originValleyID)).setColor(353825).setBiomeName("Origin Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(-0.1F, 0.6F);
		outback = (new BiomeGenOutback(outbackID)).setColor(9286496).setBiomeName("Outback").setTemperatureRainfall(0.8F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
		pasture = (new BiomeGenPasture(pastureID)).setColor(9286496).setBiomeName("Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.1F, 0.2F);
		prairie = (new BiomeGenPrairie(prairieID)).setColor(353825).setBiomeName("Prairie").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.6F).setMinMaxHeight(0.1F, 0.1F);
		promisedLand = (new BiomeGenPromisedLand(promisedLandID)).setColor(112).setBiomeName("Promised Land").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F);
		quagmire = (new BiomeGenQuagmire(quagmireID)).setColor(522674).setBiomeName("Quagmire").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
		rainforest = (new BiomeGenRainforest(rainforestID)).setColor(5470985).setBiomeName("Rainforest").func_76733_a(5470985).setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.7F, 1.8F);
		redwoodForest = (new BiomeGenRedwoodForest(redwoodForestID)).setColor(747097).setBiomeName("Redwood Forest").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.4F);
		sacredSprings = (new BiomeGenSacredSprings(sacredSpringsID)).setColor(522674).setBiomeName("Sacred Springs").func_76733_a(9154376).setMinMaxHeight(0.0F, 1.2F).setTemperatureRainfall(0.8F, 0.9F);
		savanna = (new BiomeGenSavanna(savannaID)).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(2.0F, 0.1F).setMinMaxHeight(0.1F, 0.1F);
		scrubland = (new BiomeGenScrubland(scrublandID)).setColor(9286496).setBiomeName("Scrubland").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.3F);
		seasonalForest = (new BiomeGenSeasonalForest(seasonalForestID)).setColor(353825).setBiomeName("Seasonal Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.7F);
		shield = (new BiomeGenShield(shieldID)).setColor(522674).setBiomeName("Shield").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.5F, 0.8F);
		shore = (new BiomeGenShore(shoreID)).setColor(9286496).setBiomeName("Shore").setMinMaxHeight(-1.0F, 0.4F);
		shrubland = (new BiomeGenShrubland(shrublandID)).setColor(9286496).setBiomeName("Shrubland").setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.6F, 0.0F);
		snowyWoods = (new BiomeGenSnowyWoods(snowyWoodsID)).setColor(522674).setBiomeName("Snowy Woods").func_76733_a(9154376).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F);
		spruceWoods = (new BiomeGenSpruceWoods(spruceWoodsID)).setColor(353825).setBiomeName("Spruce Woods").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.7F);
		steppe = (new BiomeGenSteppe(steppeID)).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		swampwoods = (new BiomeGenSwampwoods(swampwoodsID)).setColor(522674).setBiomeName("Swampwoods").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.2F).setTemperatureRainfall(0.8F, 0.9F);
		temperateRainforest = (new BiomeGenTemperateRainforest(temperateRainforestID)).setColor(353825).setBiomeName("Temperate Rainforest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 1.2F);
		thicket = (new BiomeGenThicket(thicketID)).setColor(353825).setBiomeName("Thicket").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.2F).setMinMaxHeight(0.0F, 0.2F);
		tropicalRainforest = (new BiomeGenTropicalRainforest(tropicalRainforestID)).setColor(9286496).setBiomeName("Tropical Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.7F);
		tropics = (new BiomeGenTropics(tropicsID)).setColor(9286496).setBiomeName("Tropics").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.8F);
		tundra = (new BiomeGenTundra(tundraID)).setColor(14090235).setBiomeName("Tundra").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(-0.2F, 0.0F);
		volcano = (new BiomeGenVolcano(volcanoID)).setColor(9286496).setBiomeName("Volcano").setDisableRain().setMinMaxHeight(0.6F, 0.9F);
		wasteland = (new BiomeGenWasteland(wastelandID)).setColor(16421912).setBiomeName("Wasteland").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.0F);
		wetland = (new BiomeGenWetland(wetlandID)).setColor(522674).setBiomeName("Wetland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.8F, 0.9F);
		woodland = (new BiomeGenWoodland(woodlandID)).setColor(353825).setBiomeName("Woodland").func_76733_a(5159473).setTemperatureRainfall(2.0F, 0.2F).setMinMaxHeight(0.1F, 0.2F);
		plainsNew = (new BiomeGenPlainsNew(plainsNewID)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
		desertNew = (new BiomeGenDesertNew(desertNewID)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		extremeHillsNew = (new BiomeGenHillsNew(extremeHillsNewID)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.2F, 0.3F);
		forestNew = (new BiomeGenForestNew(forestNewID)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F);
		taigaNew = (new BiomeGenTaigaNew(taigaNewID)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
		swamplandNew = (new BiomeGenSwampNew(swamplandNewID)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
		jungleNew = (new BiomeGenJungleNew(jungleNewID)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F);


		//Initialize new world type
		WTBiomesOP = new WTBiomesOP();


		//Spawning
		BiomeManager.addSpawnBiome(alps);
		BiomeManager.addSpawnBiome(arctic);
		BiomeManager.addSpawnBiome(badlands);
		BiomeManager.addSpawnBiome(bambooForest);
		BiomeManager.addSpawnBiome(bayou);
		BiomeManager.addSpawnBiome(birchForest);
		BiomeManager.addSpawnBiome(bog);
		BiomeManager.addSpawnBiome(borealForest);
		BiomeManager.addSpawnBiome(canyon);
		BiomeManager.addSpawnBiome(chaparral);
		BiomeManager.addSpawnBiome(cherryBlossomGrove);
		BiomeManager.addSpawnBiome(coniferousForest);
		BiomeManager.addSpawnBiome(deadForest);
		BiomeManager.addSpawnBiome(deadSwamp);
		BiomeManager.addSpawnBiome(deciduousForest);
		BiomeManager.addSpawnBiome(drylands);
		BiomeManager.addSpawnBiome(dunes);
		BiomeManager.addSpawnBiome(fen);
		BiomeManager.addSpawnBiome(field);
		BiomeManager.addSpawnBiome(frostForest);
		BiomeManager.addSpawnBiome(glacier);
		BiomeManager.addSpawnBiome(grassland);
		BiomeManager.addSpawnBiome(grove);
		BiomeManager.addSpawnBiome(heathland);
		BiomeManager.addSpawnBiome(highland);
		BiomeManager.addSpawnBiome(iceSheet);
		BiomeManager.addSpawnBiome(jadeCliffs);
		BiomeManager.addSpawnBiome(lushDesert);
		BiomeManager.addSpawnBiome(lushSwamp);
		BiomeManager.addSpawnBiome(mangrove);
		BiomeManager.addSpawnBiome(mapleWoods);
		BiomeManager.addSpawnBiome(marsh);
		BiomeManager.addSpawnBiome(meadow);
		BiomeManager.addSpawnBiome(mesa);
		BiomeManager.addSpawnBiome(moor);
		BiomeManager.addSpawnBiome(mountain);
		BiomeManager.addSpawnBiome(oasis);
		BiomeManager.addSpawnBiome(orchard);
		BiomeManager.addSpawnBiome(outback);
		BiomeManager.addSpawnBiome(pasture);
		BiomeManager.addSpawnBiome(prairie);
		BiomeManager.addSpawnBiome(quagmire);
		BiomeManager.addSpawnBiome(rainforest);
		BiomeManager.addSpawnBiome(redwoodForest);
		BiomeManager.addSpawnBiome(savanna);
		BiomeManager.addSpawnBiome(scrubland);
		BiomeManager.addSpawnBiome(seasonalForest);
		BiomeManager.addSpawnBiome(shield);
		BiomeManager.addSpawnBiome(shrubland);
		BiomeManager.addSpawnBiome(snowyWoods);
		BiomeManager.addSpawnBiome(spruceWoods);
		BiomeManager.addSpawnBiome(swampwoods);
		BiomeManager.addSpawnBiome(temperateRainforest);
		BiomeManager.addSpawnBiome(thicket);
		BiomeManager.addSpawnBiome(tropicalRainforest);
		BiomeManager.addSpawnBiome(tropics);
		BiomeManager.addSpawnBiome(tundra);
		BiomeManager.addSpawnBiome(volcano);
		BiomeManager.addSpawnBiome(wetland);
		BiomeManager.addSpawnBiome(woodland);
		BiomeManager.addSpawnBiome(plainsNew);
		BiomeManager.addSpawnBiome(desertNew);
		BiomeManager.addSpawnBiome(forestNew);
		BiomeManager.addSpawnBiome(extremeHillsNew);
		BiomeManager.addSpawnBiome(taigaNew);
		BiomeManager.addSpawnBiome(swamplandNew);
		BiomeManager.addSpawnBiome(jungleNew);

		//Village spawning 
		BiomeManager.addVillageBiome(arctic, true);				
		BiomeManager.addVillageBiome(bayou, true);
		BiomeManager.addVillageBiome(birchForest, true);
		BiomeManager.addVillageBiome(chaparral, true);
		BiomeManager.addVillageBiome(coniferousForest, true);
		BiomeManager.addVillageBiome(deadForest, true);
		BiomeManager.addVillageBiome(field, true);
		BiomeManager.addVillageBiome(frostForest, true);
		BiomeManager.addVillageBiome(grassland, true);
		BiomeManager.addVillageBiome(grove, true);
		BiomeManager.addVillageBiome(heathland, true);
		BiomeManager.addVillageBiome(lushSwamp, true);
		BiomeManager.addVillageBiome(mapleWoods, true);
		BiomeManager.addVillageBiome(orchard, true);
		BiomeManager.addVillageBiome(prairie, true);
		BiomeManager.addVillageBiome(redwoodForest, true);
		BiomeManager.addVillageBiome(savanna, true);
		BiomeManager.addVillageBiome(scrubland, true);
		BiomeManager.addVillageBiome(shield, true);
		BiomeManager.addVillageBiome(shrubland, true);
		BiomeManager.addVillageBiome(snowyWoods, true);
		BiomeManager.addVillageBiome(spruceWoods, true);
		BiomeManager.addVillageBiome(tropicalRainforest, true);
		BiomeManager.addVillageBiome(woodland, true);
		BiomeManager.addVillageBiome(plainsNew, true);
		BiomeManager.addVillageBiome(desertNew, true);
		BiomeManager.addVillageBiome(forestNew, true);
		BiomeManager.addVillageBiome(taigaNew, true);
		BiomeManager.addVillageBiome(swamplandNew, true);

		//Stronghold spawning
		BiomeManager.addStrongholdBiome(alps);
		BiomeManager.addStrongholdBiome(arctic);
		BiomeManager.addStrongholdBiome(badlands);
		BiomeManager.addStrongholdBiome(bambooForest);
		BiomeManager.addStrongholdBiome(bayou);
		BiomeManager.addStrongholdBiome(birchForest);
		BiomeManager.addStrongholdBiome(bog);
		BiomeManager.addStrongholdBiome(borealForest);
		BiomeManager.addStrongholdBiome(canyon);
		BiomeManager.addStrongholdBiome(chaparral);
		BiomeManager.addStrongholdBiome(cherryBlossomGrove);
		BiomeManager.addStrongholdBiome(coniferousForest);
		BiomeManager.addStrongholdBiome(crag);
		BiomeManager.addStrongholdBiome(deadForest);
		BiomeManager.addStrongholdBiome(deadSwamp);
		BiomeManager.addStrongholdBiome(deadlands);
		BiomeManager.addStrongholdBiome(deciduousForest);
		BiomeManager.addStrongholdBiome(drylands);
		BiomeManager.addStrongholdBiome(dunes);
		BiomeManager.addStrongholdBiome(fen);
		BiomeManager.addStrongholdBiome(field);
		BiomeManager.addStrongholdBiome(frostForest);
		BiomeManager.addStrongholdBiome(fungiForest);
		BiomeManager.addStrongholdBiome(garden);
		BiomeManager.addStrongholdBiome(glacier);
		BiomeManager.addStrongholdBiome(grassland);
		BiomeManager.addStrongholdBiome(grove);
		BiomeManager.addStrongholdBiome(heathland);
		BiomeManager.addStrongholdBiome(highland);
		BiomeManager.addStrongholdBiome(iceSheet);
		BiomeManager.addStrongholdBiome(icyHills);
		BiomeManager.addStrongholdBiome(jadeCliffs);
		BiomeManager.addStrongholdBiome(lushDesert);
		BiomeManager.addStrongholdBiome(lushSwamp);
		BiomeManager.addStrongholdBiome(mangrove);
		BiomeManager.addStrongholdBiome(mapleWoods);
		BiomeManager.addStrongholdBiome(marsh);
		BiomeManager.addStrongholdBiome(meadow);
		BiomeManager.addStrongholdBiome(mesa);
		BiomeManager.addStrongholdBiome(moor);
		BiomeManager.addStrongholdBiome(mountain);
		BiomeManager.addStrongholdBiome(mysticGrove);
		BiomeManager.addStrongholdBiome(oasis);
		BiomeManager.addStrongholdBiome(ominousWoods);
		BiomeManager.addStrongholdBiome(orchard);
		BiomeManager.addStrongholdBiome(outback);
		BiomeManager.addStrongholdBiome(pasture);
		BiomeManager.addStrongholdBiome(prairie);
		BiomeManager.addStrongholdBiome(quagmire);
		BiomeManager.addStrongholdBiome(rainforest);
		BiomeManager.addStrongholdBiome(redwoodForest);
		BiomeManager.addStrongholdBiome(sacredSprings);
		BiomeManager.addStrongholdBiome(savanna);
		BiomeManager.addStrongholdBiome(scrubland);
		BiomeManager.addStrongholdBiome(seasonalForest);
		BiomeManager.addStrongholdBiome(shield);
		BiomeManager.addStrongholdBiome(shrubland);
		BiomeManager.addStrongholdBiome(snowyWoods);
		BiomeManager.addStrongholdBiome(spruceWoods);
		BiomeManager.addStrongholdBiome(steppe);
		BiomeManager.addStrongholdBiome(swampwoods);
		BiomeManager.addStrongholdBiome(temperateRainforest);
		BiomeManager.addStrongholdBiome(thicket);
		BiomeManager.addStrongholdBiome(tropicalRainforest);
		BiomeManager.addStrongholdBiome(tropics);
		BiomeManager.addStrongholdBiome(tundra);
		BiomeManager.addStrongholdBiome(volcano);
		BiomeManager.addStrongholdBiome(wasteland);
		BiomeManager.addStrongholdBiome(wetland);
		BiomeManager.addStrongholdBiome(woodland);
		BiomeManager.addStrongholdBiome(plainsNew);
		BiomeManager.addStrongholdBiome(desertNew);
		BiomeManager.addStrongholdBiome(forestNew);
		BiomeManager.addStrongholdBiome(extremeHillsNew);
		BiomeManager.addStrongholdBiome(taigaNew);
		BiomeManager.addStrongholdBiome(swamplandNew);
		BiomeManager.addStrongholdBiome(jungleNew);

		if (addToDefault == true)
		{
			if (alpsGen == true)
			{
				GameRegistry.addBiome(alps);
			}
			if (arcticGen == true)
			{
				GameRegistry.addBiome(arctic);
			}
			if (badlandsGen == true)
			{
				GameRegistry.addBiome(badlands);
			}
			if (bambooForestGen == true)
			{
				GameRegistry.addBiome(bambooForest);
			}
			if (bayouGen == true)
			{
				GameRegistry.addBiome(bayou);
			}
			if (birchForestGen == true)
			{
				GameRegistry.addBiome(birchForest);
			}
			if (bogGen == true)
			{
				GameRegistry.addBiome(bog);
			}
			if (borealForestGen == true)
			{
				GameRegistry.addBiome(borealForest);
			}
			if (canyonGen == true)
			{
				GameRegistry.addBiome(canyon);
			}
			if (chaparralGen == true)
			{
				GameRegistry.addBiome(chaparral);
			}
			if (cherryBlossomGroveGen == true)
			{
				GameRegistry.addBiome(cherryBlossomGrove);
			}
			if (coniferousForestGen == true)
			{
				GameRegistry.addBiome(coniferousForest);
			}
			if (cragGen == true)
			{
				GameRegistry.addBiome(crag);
			}
			if (deadForestGen == true)
			{
				GameRegistry.addBiome(deadForest);
			}
			if (deadSwampGen == true)
			{
				GameRegistry.addBiome(deadSwamp);
			}
			if (deadlandsGen == true)
			{
				GameRegistry.addBiome(deadlands);
			}
			if (deciduousForestGen == true)
			{
				GameRegistry.addBiome(deciduousForest);
			}
			if (drylandsGen == true)
			{
				GameRegistry.addBiome(drylands);
			}
			if (dunesGen == true)
			{
				GameRegistry.addBiome(dunes);
			}
			if (fenGen == true)
			{
				GameRegistry.addBiome(fen);
			}
			if (fieldGen == true)
			{
				GameRegistry.addBiome(field);
			}
			if (frostForestGen == true)
			{
				GameRegistry.addBiome(frostForest);
			}
			if (fungiForestGen == true)
			{
				GameRegistry.addBiome(fungiForest);
			}
			if (gardenGen == true)
			{
				GameRegistry.addBiome(garden);
			}
			if (glacierGen == true)
			{
				GameRegistry.addBiome(glacier);
			}
			if (grasslandGen == true)
			{
				GameRegistry.addBiome(grassland);
			}
			if (groveGen == true)
			{
				GameRegistry.addBiome(grove);
			}
			if (heathlandGen == true)
			{
				GameRegistry.addBiome(heathland);
			}
			if (highlandGen == true)
			{
				GameRegistry.addBiome(highland);
			}
			if (iceSheetGen == true)
			{
				GameRegistry.addBiome(iceSheet);
			}
			if (icyHillsGen == true)
			{
				GameRegistry.addBiome(icyHills);
			}
			if (jadeCliffsGen == true)
			{
				GameRegistry.addBiome(jadeCliffs);
			}
			if (lushDesertGen == true)
			{
				GameRegistry.addBiome(lushDesert);
			}
			if (lushSwampGen == true)
			{
				GameRegistry.addBiome(lushSwamp);
			}
			if (mangroveGen == true)
			{
				GameRegistry.addBiome(mangrove);
			}
			if (mapleWoodsGen == true)
			{
				GameRegistry.addBiome(mapleWoods);
			}
			if (marshGen == true)
			{
				GameRegistry.addBiome(marsh);
			}
			if (meadowGen == true)
			{
				GameRegistry.addBiome(meadow);
			}
			if (mesaGen == true)
			{
				GameRegistry.addBiome(mesa);
			}
			if (moorGen == true)
			{
				GameRegistry.addBiome(moor);
			}
			if (mountainGen == true)
			{
				GameRegistry.addBiome(mountain);
			}
			if (mushroomIslandGen == true)
			{
				GameRegistry.addBiome(BiomeGenBase.mushroomIsland);
			}
			if (mysticGroveGen == true)
			{
				GameRegistry.addBiome(mysticGrove);
			}
			if (oasisGen == true)
			{
				GameRegistry.addBiome(oasis);
			}
			if (ominousWoodsGen == true)
			{
				GameRegistry.addBiome(ominousWoods);
			}
			if (orchardGen == true)
			{
				GameRegistry.addBiome(orchard);
			}
			if (originValleyGen == true)
			{
				GameRegistry.addBiome(originValley);
			}
			if (outbackGen == true)
			{
				GameRegistry.addBiome(outback);
			}
			if (pastureGen == true)
			{
				GameRegistry.addBiome(pasture);
			}
			if (prairieGen == true)
			{
				GameRegistry.addBiome(prairie);
			}
			if (quagmireGen == true)
			{
				GameRegistry.addBiome(quagmire);
			}
			if (rainforestGen == true)
			{
				GameRegistry.addBiome(rainforest);
			}
			if (redwoodForestGen == true)
			{
				GameRegistry.addBiome(redwoodForest);
			}
			if (sacredSpringsGen == true)
			{
				GameRegistry.addBiome(sacredSprings);
			}
			if (savannaGen == true)
			{
				GameRegistry.addBiome(savanna);
			}
			if (scrublandGen == true)
			{
				GameRegistry.addBiome(scrubland);
			}
			if (seasonalForestGen == true)
			{
				GameRegistry.addBiome(seasonalForest);
			}
			if (shieldGen == true)
			{
				GameRegistry.addBiome(shield);
			}
			if (shrublandGen == true)
			{
				GameRegistry.addBiome(shrubland);
			}
			if (snowyWoodsGen == true)
			{
				GameRegistry.addBiome(snowyWoods);
			}
			if (spruceWoodsGen == true)
			{
				GameRegistry.addBiome(spruceWoods);
			}
			if (steppeGen == true)
			{
				GameRegistry.addBiome(steppe);
			}
			if (swampwoodsGen == true)
			{
				GameRegistry.addBiome(swampwoods);
			}
			if (temperateRainforestGen == true)
			{
				GameRegistry.addBiome(temperateRainforest);
			}
			if (thicketGen == true)
			{
				GameRegistry.addBiome(thicket);
			}
			if (tropicalRainforestGen == true)
			{
				GameRegistry.addBiome(tropicalRainforest);
			}
			if (tropicsGen == true)
			{
				GameRegistry.addBiome(tropics);
			}
			if (tundraGen == true)
			{
				GameRegistry.addBiome(tundra);
			}
			if (volcanoGen == true)
			{
				GameRegistry.addBiome(volcano);
			}
			if (wastelandGen == true)
			{
				GameRegistry.addBiome(wasteland);
			}
			if (wetlandGen == true)
			{
				GameRegistry.addBiome(wetland);
			}
			if (woodlandGen == true)
			{
				GameRegistry.addBiome(woodland);
			}
			if (plainsGen == true)
			{
				if (vanillaEnhanced == true)
				{
					GameRegistry.addBiome(plainsNew);
					GameRegistry.removeBiome(BiomeGenBase.plains);
				}
			}
			if (desertGen == true)
			{
				if (vanillaEnhanced == true)
				{
					GameRegistry.addBiome(desertNew);
					GameRegistry.removeBiome(BiomeGenBase.desert);
				}
			}
			if (extremeHillsGen == true)
			{
				if (vanillaEnhanced == true)
				{
					GameRegistry.addBiome(extremeHillsNew);
					GameRegistry.removeBiome(BiomeGenBase.extremeHills);
				}
			}
			if (forestGen == true)
			{
				if (vanillaEnhanced == true)
				{
					GameRegistry.addBiome(forestNew);
					GameRegistry.removeBiome(BiomeGenBase.forest);
				}
			}
			if (taigaGen == true)
			{
				if (vanillaEnhanced == true)
				{
					GameRegistry.addBiome(taigaNew);
					GameRegistry.removeBiome(BiomeGenBase.taiga);
				}
			}
			if (swamplandGen == true)
			{
				if (vanillaEnhanced == true)
				{
					GameRegistry.addBiome(swamplandNew);
					GameRegistry.removeBiome(BiomeGenBase.swampland);
				}
			}
			if (jungleGen == true)
			{
				if (vanillaEnhanced == true)
				{
					GameRegistry.addBiome(jungleNew);
					GameRegistry.removeBiome(BiomeGenBase.jungle);
				}
			}
		}

		EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", jungleSpiderID, this, 80, 3, true);
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.JungleSpider.name", "en_US", "Jungle Spider");
		EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, jungleNew, tropicalRainforest, oasis, tropics);
		registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);

		EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", rosesterID, this, 80, 3, true);
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.Rosester.name", "en_US", "Rosester");
		EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, garden);    
		registerEntityEgg(EntityRosester.class, 14831439, 16756224);

		DimensionManager.registerProviderType(promisedLandDim, WorldProviderPromised.class, false);

		DimensionManager.registerDimension(promisedLandDim, promisedLandDim);

		dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		strongholdCorridor = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
		strongholdCrossing = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
		village = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);

		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.bopDisc), 1, 1, 2));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mossItem), 2, 8, 50));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 2), 4, 12, 75));

		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.ashes), 2, 8, 25));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.thorn), 4, 6, 15));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mudBall), 2, 8, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 3), 4, 12, 75));

		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mossItem), 2, 8, 50));
		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.glowFlower), 1, 4, 25));
		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.deathbloom), 1, 4, 25));

		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mossItem), 2, 8, 50));
		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.glowFlower), 1, 4, 25));
		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.deathbloom), 1, 4, 25));

		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.barleyItem), 4, 10, 75));
		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.shroomPowder), 1, 5, 50));
		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.thorn), 2, 6, 25));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 2), 4, 12, 75));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 3), 4, 12, 75));
	}

	//Find the first available egg ID after our egg ID counter
	public static int getUniqueEntityEggId() {
		do {
			eggIdCounter++;
		} while (EntityList.getStringFromID(eggIdCounter) != null);

		return eggIdCounter;
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
		int id = getUniqueEntityEggId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor,
				secondaryColor));
	}

	//Eggs
	public static int eggIdCounter = 300;

	public static AchievementPage pageBOP;

	// Item declaration

	// Achievement declaration
	public static Achievement achFlower2;
	public static Achievement achRedRock2;
	public static Achievement achThorn2;
	public static Achievement achAsh2;
	public static Achievement achOrigin2;
	public static Achievement achPromised2;
	public static Achievement achMud2;
	public static Achievement achShroom2;
	public static Achievement achBarley2;
	public static Achievement achMoss2;

	// Biome declaration
	public static BiomeGenBase alps;
	public static BiomeGenBase arctic;
	public static BiomeGenBase badlands;
	public static BiomeGenBase bambooForest;
	public static BiomeGenBase bayou;
	public static BiomeGenBase birchForest;
	public static BiomeGenBase bog;
	public static BiomeGenBase borealForest;
	public static BiomeGenBase canyon;
	public static BiomeGenBase chaparral;
	public static BiomeGenBase cherryBlossomGrove;
	public static BiomeGenBase coniferousForest;
	public static BiomeGenBase crag;
	public static BiomeGenBase deadForest;
	public static BiomeGenBase deadSwamp;
	public static BiomeGenBase deadlands;
	public static BiomeGenBase deciduousForest;
	public static BiomeGenBase drylands;
	public static BiomeGenBase dunes;
	public static BiomeGenBase fen;
	public static BiomeGenBase field;
	public static BiomeGenBase frostForest;
	public static BiomeGenBase fungiForest;
	public static BiomeGenBase garden;
	public static BiomeGenBase glacier;
	public static BiomeGenBase grassland;
	public static BiomeGenBase grove;
	public static BiomeGenBase heathland;
	public static BiomeGenBase highland;
	public static BiomeGenBase iceSheet;
	public static BiomeGenBase icyHills;
	public static BiomeGenBase jadeCliffs;
	public static BiomeGenBase lushDesert;
	public static BiomeGenBase lushSwamp;
	public static BiomeGenBase mangrove;
	public static BiomeGenBase mapleWoods;
	public static BiomeGenBase marsh;
	public static BiomeGenBase meadow;
	public static BiomeGenBase mesa;
	public static BiomeGenBase moor;
	public static BiomeGenBase mountain;
	public static BiomeGenBase mysticGrove;
	public static BiomeGenBase oasis;
	public static BiomeGenBase ominousWoods;
	public static BiomeGenBase orchard;
	public static BiomeGenBase originValley;
	public static BiomeGenBase outback;
	public static BiomeGenBase pasture;
	public static BiomeGenBase prairie;
	public static BiomeGenBase promisedLand;
	public static BiomeGenBase quagmire;
	public static BiomeGenBase rainforest;
	public static BiomeGenBase redwoodForest;
	public static BiomeGenBase sacredSprings;
	public static BiomeGenBase savanna;
	public static BiomeGenBase scrubland;
	public static BiomeGenBase seasonalForest;
	public static BiomeGenBase shield;
	public static BiomeGenBase shore;
	public static BiomeGenBase shrubland;
	public static BiomeGenBase snowyWoods;
	public static BiomeGenBase spruceWoods;
	public static BiomeGenBase steppe;
	public static BiomeGenBase swampwoods;
	public static BiomeGenBase temperateRainforest;
	public static BiomeGenBase thicket;
	public static BiomeGenBase tropicalRainforest;
	public static BiomeGenBase tropics;
	public static BiomeGenBase tundra;
	public static BiomeGenBase volcano;
	public static BiomeGenBase wasteland;
	public static BiomeGenBase wetland;
	public static BiomeGenBase woodland;
	public static BiomeGenBase plainsNew;
	public static BiomeGenBase desertNew;
	public static BiomeGenBase extremeHillsNew;
	public static BiomeGenBase forestNew;
	public static BiomeGenBase taigaNew;
	public static BiomeGenBase swamplandNew;
	public static BiomeGenBase jungleNew;

	public static CreativeTabs tabBiomesOPlenty;

	public static ChestGenHooks dungeon;
	public static ChestGenHooks mineshaft;
	public static ChestGenHooks strongholdCorridor;
	public static ChestGenHooks strongholdCrossing;
	public static ChestGenHooks village;

	// Configuration variables
	private Configuration config;
	public static boolean skyColors;
	public static int biomeSize;
	public static boolean addToDefault;
	public static boolean achievements;
	public static boolean vanillaEnhanced;
	public static int promisedLandDimID;

	public static boolean alpsGen;
	public static boolean arcticGen;
	public static boolean badlandsGen;
	public static boolean bambooForestGen;
	public static boolean bayouGen;
	public static boolean birchForestGen;
	public static boolean bogGen;
	public static boolean borealForestGen;
	public static boolean canyonGen;
	public static boolean chaparralGen;
	public static boolean cherryBlossomGroveGen;
	public static boolean coniferousForestGen;
	public static boolean cragGen;
	public static boolean deadForestGen;
	public static boolean deadSwampGen;
	public static boolean deadlandsGen;
	public static boolean deciduousForestGen;
	public static boolean drylandsGen;
	public static boolean dunesGen;
	public static boolean fenGen;
	public static boolean fieldGen;
	public static boolean frostForestGen;
	public static boolean fungiForestGen;
	public static boolean gardenGen;
	public static boolean glacierGen;
	public static boolean grasslandGen;
	public static boolean groveGen;
	public static boolean heathlandGen;
	public static boolean highlandGen;
	public static boolean iceSheetGen;
	public static boolean icyHillsGen;
	public static boolean jadeCliffsGen;
	public static boolean lushDesertGen;
	public static boolean lushSwampGen;
	public static boolean mangroveGen;
	public static boolean mapleWoodsGen;
	public static boolean marshGen;
	public static boolean meadowGen;
	public static boolean mesaGen;
	public static boolean moorGen;
	public static boolean mountainGen;
	public static boolean mushroomIslandGen;
	public static boolean mysticGroveGen;
	public static boolean oasisGen;
	public static boolean ominousWoodsGen;
	public static boolean orchardGen;
	public static boolean originValleyGen;
	public static boolean outbackGen;
	public static boolean pastureGen;
	public static boolean prairieGen;
	public static boolean quagmireGen;
	public static boolean rainforestGen;
	public static boolean redwoodForestGen;
	public static boolean sacredSpringsGen;
	public static boolean savannaGen;
	public static boolean scrublandGen;
	public static boolean seasonalForestGen;
	public static boolean shieldGen;
	public static boolean shrublandGen;
	public static boolean snowyWoodsGen;
	public static boolean spruceWoodsGen;
	public static boolean steppeGen;
	public static boolean swampwoodsGen;
	public static boolean temperateRainforestGen;
	public static boolean thicketGen;
	public static boolean tropicalRainforestGen;
	public static boolean tropicsGen;
	public static boolean tundraGen;
	public static boolean volcanoGen;
	public static boolean wastelandGen;
	public static boolean wetlandGen;
	public static boolean woodlandGen;

	public static boolean plainsGen;
	public static boolean desertGen;
	public static boolean extremeHillsGen;
	public static boolean forestGen;
	public static boolean taigaGen;
	public static boolean swamplandGen;
	public static boolean jungleGen;

	public static WTBiomesOP WTBiomesOP;

	public static int mudID;
	public static int driedDirtID;
	public static int redRockID;
	public static int ashID;
	public static int deadGrassID;
	public static int desertGrassID;
	public static int whiteFlowerID;
	public static int blueFlowerID;
	public static int purpleFlowerID;
	public static int orangeFlowerID;
	public static int tinyFlowerID;
	public static int glowFlowerID;
	public static int cattailID;
	public static int willowID;
	public static int autumnLeavesID;
	public static int thornID;
	public static int toadstoolID;
	public static int highGrassBottomID;
	public static int highGrassTopID;
	public static int ashStoneID;
	public static int hardIceID;
	public static int redLeavesID;
	public static int orangeLeavesID;
	public static int pinkLeavesID;
	public static int blueLeavesID;
	public static int whiteLeavesID;
	public static int deadLeavesID;
	public static int shortGrassID;
	public static int appleLeavesID;
	public static int sproutID;
	public static int bushID;
	public static int bambooID;
	public static int bambooLeavesID;
	public static int mudBrickBlockID;
	public static int mudBrickDoubleSlabID;
	public static int mudBrickSingleSlabID;
	public static int mudBrickStairsID;
	public static int originGrassID;
	public static int originLeavesID;
	public static int pinkFlowerID;
	public static int treeMossID;
	public static int deadWoodID;
	public static int appleLeavesFruitlessID;
	public static int barleyID;
	public static int giantFlowerStemID;
	public static int giantFlowerRedID;
	public static int giantFlowerYellowID;
	public static int tinyCactusID;
	public static int firSaplingID;
	public static int redwoodSaplingID;
	public static int palmSaplingID;
	public static int redSaplingID;
	public static int orangeSaplingID;
	public static int yellowSaplingID;
	public static int brownSaplingID;
	public static int willowSaplingID;
	public static int appleSaplingID;
	public static int originSaplingID;
	public static int pinkSaplingID;
	public static int whiteSaplingID;
	public static int darkSaplingID;
	public static int magicSaplingID;
	public static int deathbloomID;
	public static int redRockCobbleID;
	public static int redRockCobbleDoubleSlabID;
	public static int redRockCobbleSingleSlabID;
	public static int redRockCobbleStairsID;
	public static int redRockBrickID;
	public static int redRockBrickDoubleSlabID;
	public static int redRockBrickSingleSlabID;
	public static int redRockBrickStairsID;
	public static int hydrangeaID;
	public static int violetID;
	public static int mediumGrassID;
	public static int duneGrassID;
	public static int desertSproutsID;
	public static int mangroveSaplingID;
	public static int hardSandID;
	public static int acaciaSaplingID;
	public static int hardDirtID;
	public static int holyGrassID;
	public static int holyStoneID;
	public static int holyTallGrassID;
	public static int promisedLandPortalID;
	public static int holySaplingID;
	public static int amethystOreID;
	public static int amethystBlockID;
	public static int bambooThatchingID;
	public static int mossID;
	public static int algaeID;
	public static int smolderingGrassID;
	public static int cragRockID;
	public static int quicksandID;

	//Redwood
	public static int redwoodPlankID;
	public static int redwoodWoodID;
	public static int redwoodLeavesID;
	public static int redwoodDoubleSlabID;
	public static int redwoodSingleSlabID;
	public static int redwoodStairsID;

	//Willow
	public static int willowPlankID;
	public static int willowWoodID;
	public static int willowLeavesID;
	public static int willowDoubleSlabID;
	public static int willowSingleSlabID;
	public static int willowStairsID;

	//Fir
	public static int firPlankID;
	public static int firWoodID;
	public static int firLeavesID;
	public static int firDoubleSlabID;
	public static int firSingleSlabID;
	public static int firStairsID;

	//Acacia
	public static int acaciaPlankID;
	public static int acaciaWoodID;
	public static int acaciaLeavesID;
	public static int acaciaDoubleSlabID;
	public static int acaciaSingleSlabID;
	public static int acaciaStairsID;

	//Cherry
	public static int cherryPlankID;
	public static int cherryWoodID;
	public static int cherryDoubleSlabID;
	public static int cherrySingleSlabID;
	public static int cherryStairsID;

	//Dark
	public static int darkPlankID;
	public static int darkWoodID;
	public static int darkLeavesID;
	public static int darkDoubleSlabID;
	public static int darkSingleSlabID;
	public static int darkStairsID;

	//Magic
	public static int magicPlankID;
	public static int magicWoodID;
	public static int magicDoubleSlabID;
	public static int magicSingleSlabID;
	public static int magicStairsID;

	//Palm
	public static int palmPlankID;
	public static int palmWoodID;
	public static int palmLeavesID;
	public static int palmDoubleSlabID;
	public static int palmSingleSlabID;
	public static int palmStairsID;

	//Mangrove
	public static int mangrovePlankID;
	public static int mangroveWoodID;
	public static int mangroveLeavesID;
	public static int mangroveDoubleSlabID;
	public static int mangroveSingleSlabID;
	public static int mangroveStairsID;

	//Holy
	public static int holyPlankID;
	public static int holyWoodID;
	public static int holyLeavesID;
	public static int holyDoubleSlabID;
	public static int holySingleSlabID;
	public static int holyStairsID;

	public static int shroomPowderID;
	public static int mudBallID;
	public static int mudBrickID;
	public static int cattailItemID;
	public static int bambooItemID;
	public static int barleyItemID;
	public static int shortGrassItemID;
	public static int mediumGrassItemID;
	public static int bushItemID;
	public static int sproutItemID;
	public static int mossItemID;
	public static int ashesID;
	public static int ancientStaffID;
	public static int ancientStaffHandleID;
	public static int ancientStaffPoleID;
	public static int ancientStaffTopperID;
	public static int enderporterID;
	public static int bopDiscID;
	public static int bopDiscMudID;
	public static int swordMudID;
	public static int shovelMudID;
	public static int pickaxeMudID;
	public static int axeMudID;
	public static int hoeMudID;
	public static int helmetMudID;
	public static int chestplateMudID;
	public static int leggingsMudID;
	public static int bootsMudID;
	public static int amethystID;
	public static int swordAmethystID;
	public static int shovelAmethystID;
	public static int pickaxeAmethystID;
	public static int axeAmethystID;
	public static int hoeAmethystID;
	public static int helmetAmethystID;
	public static int chestplateAmethystID;
	public static int leggingsAmethystID;
	public static int bootsAmethystID;

	public static int alpsID;
	public static int arcticID;
	public static int arcticForestID;
	public static int badlandsID;
	public static int bambooForestID;
	public static int bayouID;
	public static int birchForestID;
	public static int bogID;
	public static int borealForestID;
	public static int canyonID;
	public static int chaparralID;
	public static int cherryBlossomGroveID;
	public static int coniferousForestID;
	public static int coniferousForestThinID;
	public static int cragID;
	public static int deadForestID;
	public static int deadSwampID;
	public static int deadlandsID;
	public static int deciduousForestID;
	public static int drylandsID;
	public static int dunesID;
	public static int fenID;
	public static int fieldID;
	public static int frostForestID;
	public static int fungiForestID;
	public static int gardenID;
	public static int glacierID;
	public static int grasslandID;
	public static int groveID;
	public static int groveThinID;
	public static int heathlandID;
	public static int highlandID;
	public static int iceSheetID;
	public static int icyHillsID;
	public static int jadeCliffsID;
	public static int lushDesertID;
	public static int lushSwampID;
	public static int mangroveID;
	public static int mapleWoodsID;
	public static int marshID;
	public static int meadowID;
	public static int meadowForestID;
	public static int mesaID;
	public static int moorID;
	public static int mountainID;
	public static int mysticGroveID;
	public static int oasisID;
	public static int ominousWoodsID;
	public static int orchardID;
	public static int originValleyID;
	public static int outbackID;
	public static int pastureID;
	public static int prairieID;
	public static int promisedLandID;
	public static int promisedLandHillsID;
	public static int promisedLandPlainsID;
	public static int promisedLandSwampID;
	public static int quagmireID;
	public static int rainforestID;
	public static int redwoodForestID;
	public static int reefID;
	public static int sacredSpringsID;
	public static int savannaID;
	public static int savannaThickID;
	public static int scrublandID;
	public static int seasonalForestID;
	public static int shieldID;
	public static int shoreID;
	public static int shrublandID;
	public static int snowyWoodsID;
	public static int spruceWoodsID;
	public static int steppeID;
	public static int swampwoodsID;
	public static int temperateRainforestID;
	public static int thicketID;
	public static int tropicalRainforestID;
	public static int tropicsID;
	public static int tundraID;
	public static int tundraDryID;
	public static int volcanoID;
	public static int wastelandID;
	public static int wastelandTreesID;
	public static int wetlandID;
	public static int woodlandID;
	public static int plainsNewID;
	public static int desertNewID;
	public static int desertHillsNewID;
	public static int extremeHillsNewID;
	public static int extremeHillsEdgeNewID;
	public static int forestNewID;
	public static int forestHillsNewID;
	public static int taigaNewID;
	public static int taigaHillsNewID;
	public static int swamplandNewID;
	public static int jungleNewID;
	public static int jungleHillsNewID;

	public static int jungleSpiderID;
	public static int rosesterID;

	// Add Fuel rates
	public static int addFuel(int par1, int par2)
	{
		if(par1 == BOPBlocks.redwoodSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.redwoodSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.redwoodStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.willowSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.willowSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.willowStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.firSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.firSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.firStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.acaciaSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.acaciaSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.acaciaStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.pinkSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.whiteSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.orangeSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.yellowSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.redSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.brownSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.appleSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.originSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.cherrySingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.cherryStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.darkSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.darkSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.darkStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.magicSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.magicSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.magicStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.palmSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.palmSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.palmStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.mangroveSapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.mangroveSingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.mangroveStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPBlocks.holySapling.blockID)
		{
			return 100;
		}
		if(par1 == BOPBlocks.holySingleSlab.blockID)
		{
			return 150;
		}
		if(par1 == BOPBlocks.holyStairs.blockID)
		{
			return 300;
		}
		if(par1 == BOPItems.ashes.itemID)
		{
			return 400;
		}

		return 0;
	}

	// Achievement checker
	public static void onItemPickup(EntityPlayer player, ItemStack item)
	{
		if (achievements == true)
		{
			if (item.itemID == BOPBlocks.glowFlower.blockID || item.itemID == BOPBlocks.orangeFlower.blockID || item.itemID == BOPBlocks.blueFlower.blockID || item.itemID == BOPBlocks.purpleFlower.blockID || item.itemID == BOPBlocks.pinkFlower.blockID || item.itemID == BOPBlocks.whiteFlower.blockID || item.itemID == BOPBlocks.tinyFlower.blockID || item.itemID == BOPBlocks.deathbloom.blockID || item.itemID == BOPBlocks.hydrangea.blockID || item.itemID == BOPBlocks.violet.blockID || item.itemID == Block.plantRed.blockID || item.itemID == Block.plantYellow.blockID)
			{
				player.addStat(achFlower2, 1);
			}
			if (item.itemID == BOPBlocks.redRockCobble.blockID)
			{
				player.addStat(achRedRock2, 1);
			}
			if (item.itemID == BOPBlocks.thorn.blockID)
			{
				player.addStat(achThorn2, 1);
			}
			if (item.itemID == BOPItems.ashes.itemID)
			{
				player.addStat(achAsh2, 1);
			}
			if (item.itemID == BOPBlocks.originGrass.blockID)
			{
				player.addStat(achOrigin2, 1);
			}
			if (item.itemID == BOPBlocks.holyGrass.blockID || item.itemID == BOPBlocks.holyStone.blockID)
			{
				player.addStat(achPromised2, 1);
			}
			if (item.itemID == BOPItems.mudBall.itemID)
			{
				player.addStat(achMud2, 1);
			}
			if (item.itemID == BOPBlocks.toadstool.blockID)
			{
				player.addStat(achShroom2, 1);
			}
			if (item.itemID == BOPItems.barleyItem.itemID)
			{
				player.addStat(achBarley2, 1);
			}
			if (item.itemID == BOPItems.mossItem.itemID)
			{
				player.addStat(achMoss2, 1);
			}
		}
	}

	public static void addAchievementDesc(String ach, String name, String desc)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}

	public static int getLastBiomeID()
	{
		int x;
		for(x = 255; x >= 0; x--) {
			if (BiomeGenBase.biomeList[x] == null) 
			{
				break;
			}
		}
		return x;
	}
}