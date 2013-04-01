package tdwp_ftw.biomesop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
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
import tdwp_ftw.biomesop.helpers.*;
import tdwp_ftw.biomesop.items.*;
import tdwp_ftw.biomesop.mobs.*;
import tdwp_ftw.biomesop.worldtype.WTBiomesOP;
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

@Mod(modid="BiomesOPlenty", name="Biomes O' Plenty", version="0.4.8")
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
        }
        
        @Init
        public void load(FMLInitializationEvent event)
        {
				tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");
		
        		// Block declaration
	    	    mud = (new BlockMud(mudID)).setHardness(0.6F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("mud");
	    	    driedDirt = (new BlockDriedDirt(driedDirtID)).setHardness(0.1F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("driedDirt");
	    	    redRock = (new BlockRedRock(redRockID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRock");
	    	    ash = (new BlockAsh(ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("ash");
	    	    deadGrass = (new BlockDeadGrass(deadGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deadGrass");
	    	    desertGrass = (new BlockDesertGrass(desertGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("desertGrass");
	    	    whiteFlower = (new BlockWhiteFlower(whiteFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteFlower");
	    	    blueFlower = (new BlockBlueFlower(blueFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("blueFlower");
	    	    purpleFlower = (new BlockPurpleFlower(purpleFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("purpleFlower");
	    	    orangeFlower = (new BlockOrangeFlower(orangeFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeFlower");
	    	    tinyFlower = (new BlockTinyFlower(tinyFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("tinyFlower");
	    	    glowFlower = (new BlockGlowFlower(glowFlowerID)).setHardness(0.0F).setLightValue(0.65F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("glowFlower");
	    	    cattail = (new BlockCattail(cattailID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("cattail");
	    	    willow = (new BlockWillow(willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willow");
	    	    autumnLeaves = (BlockAutumnLeaves)(new BlockAutumnLeaves(autumnLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("autumnLeaves");
	    	    thorn = (new BlockThorn(thornID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("thorn");
	    	    toadstool = (new BlockToadstool(toadstoolID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("toadstool");
	    	    highGrassBottom = (BlockHighGrassBottom)(new BlockHighGrassBottom(highGrassBottomID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("highGrassBottom");
	    	    highGrassTop = (BlockHighGrassTop)(new BlockHighGrassTop(highGrassTopID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("highGrassTop");
	    	    ashStone = (new BlockAshStone(ashStoneID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ashStone");
	    	    hardIce = (new BlockHardIce(hardIceID)).setHardness(0.75F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardIce");
	    	    redLeaves = (BlockRedLeaves)(new BlockRedLeaves(redLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redLeaves");
	    	    orangeLeaves = (BlockOrangeLeaves)(new BlockOrangeLeaves(orangeLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeLeaves");
	    	    pinkLeaves = (BlockPinkLeaves)(new BlockPinkLeaves(pinkLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkLeaves");
	    	    blueLeaves = (BlockBlueLeaves)(new BlockBlueLeaves(blueLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("blueLeaves");
	    	    whiteLeaves = (BlockWhiteLeaves)(new BlockWhiteLeaves(whiteLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteLeaves");
	    	    deadLeaves = (BlockDeadLeaves)(new BlockDeadLeaves(deadLeavesID)).setHardness(0.1F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deadLeaves");
	    	    shortGrass = (BlockShortGrass)(new BlockShortGrass(shortGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("shortGrass");
	    	    appleLeaves = (BlockAppleLeaves)(new BlockAppleLeaves(appleLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleLeaves");
	    	    sprout = (BlockSprout)(new BlockSprout(sproutID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sprout");
	    	    bush = (BlockBush)(new BlockBush(bushID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bush");
	    	    bamboo = new BlockBamboo(bambooID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bamboo");
	    	    bambooLeaves = (BlockBambooLeaves)(new BlockBambooLeaves(bambooLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bambooLeaves");
	    	    mudBrickBlock = (new BlockMudBrick(mudBrickBlockID)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrickBlock");
				originGrass = (BlockOriginGrass)(new BlockOriginGrass(originGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originGrass");
				originLeaves = (BlockOriginLeaves)(new BlockOriginLeaves(originLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originLeaves");
	    	    pinkFlower = (new BlockPinkFlower(pinkFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkFlower");
	    	    treeMoss = (new BlockTreeMoss(treeMossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("treeMoss");
	    	    deadWood = (new BlockDeadLog(deadWoodID)).setHardness(0.8F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("deadWood");
	    	    appleLeavesFruitless = (BlockAppleLeavesFruitless)(new BlockAppleLeavesFruitless(appleLeavesFruitlessID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleLeavesFruitless");
	    	    barley = (new BlockBarley(barleyID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("barley");
	    	    giantFlowerStem = (new BlockGiantFlowerStem(giantFlowerStemID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerStem");
	    	    giantFlowerRed = (BlockGiantFlowerRed)(new BlockGiantFlowerRed(giantFlowerRedID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerRed");
				giantFlowerYellow = (BlockGiantFlowerYellow)(new BlockGiantFlowerYellow(giantFlowerYellowID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerYellow");
	    	    tinyCactus = (new BlockTinyCactus(tinyCactusID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("tinyCactus");
	    	    firSapling = (new BlockFirSapling(firSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("firSapling");
	    	    redwoodSapling = (new BlockRedwoodSapling(redwoodSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redwoodSapling");
	    	    palmSapling = (new BlockPalmSapling(palmSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("palmSapling");
	    	    redSapling = (new BlockRedSapling(redSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redSapling");
				orangeSapling = (new BlockOrangeSapling(orangeSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeSapling");
				yellowSapling = (new BlockYellowSapling(yellowSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("yellowSapling");
				brownSapling = (new BlockBrownSapling(brownSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("brownSapling");
				willowSapling = (new BlockWillowSapling(willowSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willowSapling");
				appleSapling = (new BlockAppleSapling(appleSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleSapling");
				originSapling = (new BlockOriginSapling(originSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originSapling");
				pinkSapling = (new BlockPinkSapling(pinkSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkSapling");
				whiteSapling = (new BlockWhiteSapling(whiteSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteSapling");
				darkSapling = (new BlockDarkSapling(darkSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("darkSapling");
				magicSapling = (new BlockMagicSapling(magicSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("magicSapling");
	    	    deathbloom = (new BlockDeathbloom(deathbloomID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deathbloom");
	    	    redRockCobble = (new BlockRedRockCobble(redRockCobbleID)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockCobble");
	    	    redRockBrick = (new BlockRedRockBrick(redRockBrickID)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockBrick");
	    	    hydrangea = (new BlockHydrangea(hydrangeaID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hydrangea");
	    	    violet = (new BlockViolet(violetID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("violet");
	    	    mediumGrass = (BlockMediumGrass)(new BlockMediumGrass(mediumGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mediumGrass");
	    	    duneGrass = (new BlockDuneGrass(duneGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("duneGrass");
	    	    desertSprouts = (new BlockDesertSprouts(desertSproutsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("desertSprouts");
	    	    redRockCobbleDoubleSlab = (BlockHalfSlab)(new BlockRedRockCobbleSlab(redRockCobbleDoubleSlabID, true)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redRockCobbleSlab");
	    	    redRockCobbleSingleSlab = (BlockHalfSlab)(new BlockRedRockCobbleSlab(redRockCobbleSingleSlabID, false)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redRockCobbleSlab");
	    		redRockCobbleStairs = (new BlockRedRockCobbleStairs(redRockCobbleStairsID, redRockCobble)).setUnlocalizedName("redRockCobbleStairs");
	    	    redRockBrickDoubleSlab = (BlockHalfSlab)(new BlockRedRockBrickSlab(redRockBrickDoubleSlabID, true)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redRockBrickSlab");
	    	    redRockBrickSingleSlab = (BlockHalfSlab)(new BlockRedRockBrickSlab(redRockBrickSingleSlabID, false)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redRockBrickSlab");
	    		redRockBrickStairs = (new BlockRedRockBrickStairs(redRockBrickStairsID, redRockBrick)).setUnlocalizedName("redRockBrickStairs");					
	    	    mudBrickDoubleSlab = (BlockHalfSlab)(new BlockMudBrickSlab(mudBrickDoubleSlabID, true)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mudBrickSlab");
	    	    mudBrickSingleSlab = (BlockHalfSlab)(new BlockMudBrickSlab(mudBrickSingleSlabID, false)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mudBrickSlab");
	    		mudBrickStairs = (new BlockMudBrickStairs(mudBrickStairsID, mudBrickBlock)).setUnlocalizedName("mudBrickStairs");
	    	    mangroveSapling = (new BlockMangroveSapling(mangroveSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mangroveSapling");
	    	    hardSand = (new BlockHardSand(hardSandID)).setHardness(0.7F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("hardSand");
	    	    acaciaSapling = (new BlockAcaciaSapling(acaciaSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("acaciaSapling");
	    	    hardDirt = (new BlockHardDirt(hardDirtID)).setHardness(0.9F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardDirt");
				holyGrass = (BlockHolyGrass)(new BlockHolyGrass(holyGrassID)).setHardness(1.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyGrass");
	    	    holyStone = (new BlockHolyStone(holyStoneID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("holyStone");
	    	    holyTallGrass = (new BlockHolyTallGrass(holyTallGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyTallGrass");
				promisedPortal = new BlockPromisedPortal(promisedLandPortalID).setUnlocalizedName("promisedPortal").setBlockUnbreakable().setResistance(6000000.0F).setLightValue(1.0F);	
	    	    holySapling = (new BlockHolySapling(holySaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holySapling");
				amethystOre = (new BlockAmethystOre(amethystOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("amethystOre");
	    	    amethystBlock = (new BlockAmethystBlock(amethystBlockID)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("amethystBlock");
	    	    bambooThatching = (new BlockBambooThatching(bambooThatchingID)).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooThatching");
	    	    moss = (new BlockMoss(mossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("moss");
	    	    algae = (new BlockAlgae(algaeID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("algae");
				smolderingGrass = (BlockSmolderingGrass)(new BlockSmolderingGrass(smolderingGrassID)).setHardness(0.6F).setLightValue(0.25F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("smolderingGrass");
	    	    cragRock = (new BlockCragRock(cragRockID)).setHardness(1.0F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("cragRock");
	    	    quicksand = (new BlockQuicksand(quicksandID)).setHardness(0.3F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("quicksand");
				
	    		//Redwood
	    	    redwoodPlank = (new BlockRedwoodPlank(redwoodPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodPlank");
	    	    redwoodWood = (new BlockRedwoodLog(redwoodWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodWood");
	    	    redwoodLeaves = (BlockRedwoodLeaves)(new BlockRedwoodLeaves(redwoodLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redwoodLeaves");
	    	    redwoodDoubleSlab = (BlockHalfSlab)(new BlockRedwoodSlab(redwoodDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodSlab");
	    	    redwoodSingleSlab = (BlockHalfSlab)(new BlockRedwoodSlab(redwoodSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodSlab");
	    	    redwoodStairs = (new BlockRedwoodStairs(redwoodStairsID, redwoodPlank)).setUnlocalizedName("redwoodStairs");
	    		
	    		//Willow
	    	    willowPlank = (new BlockWillowPlank(willowPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowPlank");
	    	    willowWood = (new BlockWillowLog(willowWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowWood");
	    	    willowLeaves = (BlockWillowLeaves)(new BlockWillowLeaves(willowLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willowLeaves");
	    	    willowDoubleSlab = (BlockHalfSlab)(new BlockWillowSlab(willowDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowSlab");
	    	    willowSingleSlab = (BlockHalfSlab)(new BlockWillowSlab(willowSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowSlab");
	    		willowStairs = (new BlockWillowStairs(willowStairsID, willowPlank)).setUnlocalizedName("willowStairs");
	    		
	    		//Fir
	    	    firPlank = (new BlockFirPlank(firPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firPlank");
	    	    firWood = (new BlockFirLog(firWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firWood");
	    	    firLeaves = (BlockFirLeaves)(new BlockFirLeaves(firLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("firLeaves");
	    	    firDoubleSlab = (BlockHalfSlab)(new BlockFirSlab(firDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firSlab");
	    	    firSingleSlab = (BlockHalfSlab)(new BlockFirSlab(firSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firSlab");
	    		firStairs = (new BlockFirStairs(firStairsID, firPlank)).setUnlocalizedName("firStairs");
	    		
	    		//Acacia
	    	    acaciaPlank = (new BlockAcaciaPlank(acaciaPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaPlank");
	    	    acaciaWood = (new BlockAcaciaLog(acaciaWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaWood");
	    	    acaciaLeaves = (BlockAcaciaLeaves)(new BlockAcaciaLeaves(acaciaLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("acaciaLeaves");
	    	    acaciaDoubleSlab = (BlockHalfSlab)(new BlockAcaciaSlab(acaciaDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaSlab");
	    	    acaciaSingleSlab = (BlockHalfSlab)(new BlockAcaciaSlab(acaciaSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaSlab");
	    		acaciaStairs = (new BlockAcaciaStairs(acaciaStairsID, acaciaPlank)).setUnlocalizedName("acaciaStairs");
				
	    		//Cherry
	    	    cherryPlank = (new BlockCherryPlank(cherryPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherryPlank");
	    	    cherryWood = (new BlockCherryLog(cherryWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherryWood");
	    	    cherryDoubleSlab = (BlockHalfSlab)(new BlockCherrySlab(cherryDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrySlab");
	    	    cherrySingleSlab = (BlockHalfSlab)(new BlockCherrySlab(cherrySingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrySlab");
	    		cherryStairs = (new BlockCherryStairs(cherryStairsID, cherryPlank)).setUnlocalizedName("cherryStairs");
				
	    		//Dark
	    	    darkPlank = (new BlockDarkPlank(darkPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkPlank");
	    	    darkWood = (new BlockDarkLog(darkWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkWood");
	    	    darkLeaves = (BlockDarkLeaves)(new BlockDarkLeaves(darkLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("darkLeaves");
	    	    darkDoubleSlab = (BlockHalfSlab)(new BlockDarkSlab(darkDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkSlab");
	    	    darkSingleSlab = (BlockHalfSlab)(new BlockDarkSlab(darkSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkSlab");
	    		darkStairs = (new BlockDarkStairs(darkStairsID, darkPlank)).setUnlocalizedName("darkStairs");
			
	    		//Magic
	    	    magicPlank = (new BlockMagicPlank(magicPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicPlank");
	    	    magicWood = (new BlockMagicLog(magicWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicWood");
	    	    magicDoubleSlab = (BlockHalfSlab)(new BlockMagicSlab(magicDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicSlab");
	    	    magicSingleSlab = (BlockHalfSlab)(new BlockMagicSlab(magicSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicSlab");
	    		magicStairs = (new BlockMagicStairs(magicStairsID, magicPlank)).setUnlocalizedName("magicStairs");
				
	    		//Palm
	    	    palmPlank = (new BlockPalmPlank(palmPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmPlank");
	    	    palmWood = (new BlockPalmLog(palmWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmWood");
	    	    palmLeaves = (BlockPalmLeaves)(new BlockPalmLeaves(palmLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("palmLeaves");
	    	    palmDoubleSlab = (BlockHalfSlab)(new BlockPalmSlab(palmDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmSlab");
	    	    palmSingleSlab = (BlockHalfSlab)(new BlockPalmSlab(palmSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmSlab");
	    		palmStairs = (new BlockPalmStairs(palmStairsID, palmPlank)).setUnlocalizedName("palmStairs");
				
	    		//Mangrove
	    	    mangrovePlank = (new BlockMangrovePlank(mangrovePlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangrovePlank");
	    	    mangroveWood = (new BlockMangroveLog(mangroveWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveWood");
	    	    mangroveLeaves = (BlockMangroveLeaves)(new BlockMangroveLeaves(mangroveLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mangroveLeaves");
	    	    mangroveDoubleSlab = (BlockHalfSlab)(new BlockMangroveSlab(mangroveDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveSlab");
	    	    mangroveSingleSlab = (BlockHalfSlab)(new BlockMangroveSlab(mangroveSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveSlab");
	    	    mangroveStairs = (new BlockMangroveStairs(mangroveStairsID, mangrovePlank)).setUnlocalizedName("mangroveStairs");
				
	    		//Holy
	    	    holyPlank = (new BlockHolyPlank(holyPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holyPlank");
	    	    holyWood = (new BlockHolyLog(holyWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holyWood");
	    	    holyLeaves = (BlockHolyLeaves)(new BlockHolyLeaves(holyLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyLeaves");
	    	    holyDoubleSlab = (BlockHalfSlab)(new BlockHolySlab(holyDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holySlab");
	    	    holySingleSlab = (BlockHalfSlab)(new BlockHolySlab(holySingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holySlab");
	    	    holyStairs = (new BlockHolyStairs(holyStairsID, holyPlank)).setUnlocalizedName("holyStairs");
        	
	    		// Material declaration
	    		EnumArmorMaterialMud = EnumHelper.addArmorMaterial("MUD", 2, new int[]{1, 1, 1, 1}, 5);
	    		EnumToolMaterialMud = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0, 1);
				EnumArmorMaterialAmethyst = EnumHelper.addArmorMaterial("AMETHYST", 40, new int[]{6, 12, 10, 6}, 20);
	    		EnumToolMaterialAmethyst = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5, 16);
	    	    
	    		// Item declaration
	       		shroomPowder = (new ItemShroomPowder(shroomPowderID, 1, 0.5F, false)).setPotionEffect(Potion.confusion.id, 30, 0, 0.6F).setAlwaysEdible().setUnlocalizedName("shroomPowder").setCreativeTab(tabBiomesOPlenty);
	    		mudBall = (new ItemBOP(mudBallID, 0)).setUnlocalizedName("mudBall").setCreativeTab(tabBiomesOPlenty);
	    		mudBrick = (new ItemBOP(mudBrickID, 1)).setUnlocalizedName("mudBrick").setCreativeTab(tabBiomesOPlenty);
	    		bambooItem = (new ItemBamboo(bambooItemID, bamboo)).setUnlocalizedName("bambooItem").setCreativeTab(tabBiomesOPlenty);
	    		cattailItem = (new ItemCattail(cattailItemID, cattail)).setUnlocalizedName("cattailItem").setCreativeTab(tabBiomesOPlenty);
	    		barleyItem = (new ItemBarley(barleyItemID, barley)).setUnlocalizedName("barleyItem").setCreativeTab(tabBiomesOPlenty);
				shortGrassItem = (new ItemShortGrass(shortGrassItemID, shortGrass)).setUnlocalizedName("shortGrassItem").setCreativeTab(tabBiomesOPlenty);
				mediumGrassItem = (new ItemMediumGrass(mediumGrassItemID, mediumGrass)).setUnlocalizedName("mediumGrassItem").setCreativeTab(tabBiomesOPlenty);
				bushItem = (new ItemBush(bushItemID, bush)).setUnlocalizedName("bushItem").setCreativeTab(tabBiomesOPlenty);
				sproutItem = (new ItemSprout(sproutItemID, sprout)).setUnlocalizedName("sproutItem").setCreativeTab(tabBiomesOPlenty);
				mossItem = (new ItemBOP(mossItemID, 2)).setUnlocalizedName("mossItem").setCreativeTab(tabBiomesOPlenty);
				ancientStaff = new ItemAncientStaff(ancientStaffID).setUnlocalizedName("ancientStaff").setCreativeTab(tabBiomesOPlenty);
				enderporter = new ItemEnderporter(enderporterID).setUnlocalizedName("enderporter").setCreativeTab(tabBiomesOPlenty);
	    		ashes = (new ItemBOP(ashesID, 3)).setUnlocalizedName("ashes").setCreativeTab(tabBiomesOPlenty);
				amethyst = (new ItemBOP(amethystID, 4)).setUnlocalizedName("amethyst").setCreativeTab(tabBiomesOPlenty);
				ancientStaffHandle = (new ItemBOP(ancientStaffHandleID, 5)).setUnlocalizedName("ancientStaffHandle").setCreativeTab(tabBiomesOPlenty);
				ancientStaffPole = (new ItemBOP(ancientStaffPoleID, 6)).setUnlocalizedName("ancientStaffPole").setCreativeTab(tabBiomesOPlenty);
				ancientStaffTopper = (new ItemBOP(ancientStaffTopperID, 7)).setUnlocalizedName("ancientStaffTopper").setCreativeTab(tabBiomesOPlenty);
				bopDisc = (new ItemBOPRecord(bopDiscID, "bopdisc")).setUnlocalizedName("bopDisc").setCreativeTab(tabBiomesOPlenty);
				bopDiscMud = (new ItemBOPRecordMud(bopDiscMudID, "bopdiscmud")).setUnlocalizedName("bopDiscMud").setCreativeTab(tabBiomesOPlenty);
				
	    		swordMud = (new ItemBOPSword(swordMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("swordMud").setCreativeTab(tabBiomesOPlenty);
	    		shovelMud = (new ItemBOPSpade(shovelMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("shovelMud").setCreativeTab(tabBiomesOPlenty);
	    		pickaxeMud = (new ItemBOPPickaxe(pickaxeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("pickaxeMud").setCreativeTab(tabBiomesOPlenty);
	    		axeMud = (new ItemBOPAxe(axeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("hatchetMud").setCreativeTab(tabBiomesOPlenty);
	    		hoeMud = (new ItemBOPHoe(hoeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("hoeMud").setCreativeTab(tabBiomesOPlenty);
    			helmetMud = (new ArmorMuddy(helmetMudID, EnumArmorMaterialMud, proxy.addArmor("mud"), 0)).setUnlocalizedName("helmetMud").setCreativeTab(tabBiomesOPlenty);
    			chestplateMud = (new ArmorMuddy(chestplateMudID, EnumArmorMaterialMud, proxy.addArmor("mud"), 1)).setUnlocalizedName("chestplateMud").setCreativeTab(tabBiomesOPlenty);
    			leggingsMud = (new ArmorMuddy(leggingsMudID, EnumArmorMaterialMud, proxy.addArmor("mud"), 2)).setUnlocalizedName("leggingsMud").setCreativeTab(tabBiomesOPlenty);
    			bootsMud = (new ArmorMuddy(bootsMudID, EnumArmorMaterialMud, proxy.addArmor("mud"), 3)).setUnlocalizedName("bootsMud").setCreativeTab(tabBiomesOPlenty);
				
	    		swordAmethyst = (new ItemBOPSword(swordAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("swordAmethyst").setCreativeTab(tabBiomesOPlenty);
	    		shovelAmethyst = (new ItemBOPSpade(shovelAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("shovelAmethyst").setCreativeTab(tabBiomesOPlenty);
	    		pickaxeAmethyst = (new ItemBOPPickaxe(pickaxeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("pickaxeAmethyst").setCreativeTab(tabBiomesOPlenty);
	    		axeAmethyst = (new ItemBOPAxe(axeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("hatchetAmethyst").setCreativeTab(tabBiomesOPlenty);
	    		hoeAmethyst = (new ItemBOPHoe(hoeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("hoeAmethyst").setCreativeTab(tabBiomesOPlenty);
    			helmetAmethyst = (new ArmorAmethyst(helmetAmethystID, EnumArmorMaterialAmethyst, proxy.addArmor("amethyst"), 0)).setCreativeTab(tabBiomesOPlenty).setUnlocalizedName("helmetAmethyst");
    			chestplateAmethyst = (new ArmorAmethyst(chestplateAmethystID, EnumArmorMaterialAmethyst, proxy.addArmor("amethyst"), 1)).setCreativeTab(tabBiomesOPlenty).setUnlocalizedName("chestplateAmethyst");
    			leggingsAmethyst = (new ArmorAmethyst(leggingsAmethystID, EnumArmorMaterialAmethyst, proxy.addArmor("amethyst"), 2)).setCreativeTab(tabBiomesOPlenty).setUnlocalizedName("leggingsAmethyst");
    			bootsAmethyst = (new ArmorAmethyst(bootsAmethystID, EnumArmorMaterialAmethyst, proxy.addArmor("amethyst"), 3)).setCreativeTab(tabBiomesOPlenty).setUnlocalizedName("bootsAmethyst");

    	        // Achievement declaration
			if (achievements == true)
			{
				achFlower2 = (new Achievement(3057, "achFlower2", 0, 0, Block.plantRed, (Achievement)null)).registerAchievement();
				achRedRock2 = (new Achievement(3058, "achRedRock2", -1, 2, redRock, achFlower2)).registerAchievement();
				achThorn2 = (new Achievement(3059, "achThorn2", 2, 1, thorn, achFlower2)).registerAchievement();
				achAsh2 = (new Achievement(3060, "achAsh2", 1, 3, ashes, achFlower2)).registerAchievement();
				achOrigin2 = (new Achievement(3061, "achOrigin2", 0, 5, originGrass, achFlower2)).setSpecial().registerAchievement();
				achPromised2 = (new Achievement(3062, "achPromised2", 0, -5, holyGrass, achFlower2)).setSpecial().registerAchievement();
				achMud2 = (new Achievement(3063, "achMud2", -2, -1, mudBall, achFlower2)).registerAchievement();
				achShroom2 = (new Achievement(3064, "achShroom2", 1, -2, toadstool, achFlower2)).registerAchievement();
				achBarley2 = (new Achievement(3065, "achBarley2", -2, 4, barleyItem, achFlower2)).registerAchievement();
				achMoss2 = (new Achievement(3066, "achMoss2", -1, -3, mossItem, achFlower2)).registerAchievement();

				pageBOP = new AchievementPage("Biomes O\' Plenty", achFlower2, achRedRock2, achThorn2, achAsh2, achOrigin2, achPromised2, achMud2, achShroom2, achBarley2, achMoss2);
				AchievementPage.registerAchievementPage(pageBOP);
			}
    	        
        		// Add block names
	            LanguageRegistry.addName(mud, "Mud");
	            LanguageRegistry.addName(driedDirt, "Dried Dirt");
	            LanguageRegistry.addName(redRock, "Red Rock");
	            LanguageRegistry.addName(ash, "Ash Block");
	            LanguageRegistry.addName(deadGrass, "Dead Grass");
	            LanguageRegistry.addName(desertGrass, "Desert Grass");
	            LanguageRegistry.addName(whiteFlower, "Anenome");
	            LanguageRegistry.addName(blueFlower, "Swampflower");
	            LanguageRegistry.addName(purpleFlower, "Wildflower");
	            LanguageRegistry.addName(orangeFlower, "Daisy");
	            LanguageRegistry.addName(tinyFlower, "Clover");
	            LanguageRegistry.addName(glowFlower, "Glowflower");
	            LanguageRegistry.addName(cattail, "Cattail");
	            LanguageRegistry.addName(willow, "Willow");
	            LanguageRegistry.addName(autumnLeaves, "Autumn Leaves");
	            LanguageRegistry.addName(thorn, "Thorns");
	            LanguageRegistry.addName(toadstool, "Toadstool");
	            LanguageRegistry.addName(highGrassBottom, "High Grass");
	            LanguageRegistry.addName(highGrassTop, "High Grass");
	            LanguageRegistry.addName(ashStone, "Ashy Stone");
	            LanguageRegistry.addName(hardIce, "Hard Ice");
	            LanguageRegistry.addName(redLeaves, "Maple Leaves");
	            LanguageRegistry.addName(orangeLeaves, "Autumn Leaves");
	            LanguageRegistry.addName(pinkLeaves, "Cherry Leaves");
	            LanguageRegistry.addName(blueLeaves, "Magic Leaves");
	            LanguageRegistry.addName(whiteLeaves, "Cherry Leaves");
	            LanguageRegistry.addName(deadLeaves, "Dying Leaves");
	            LanguageRegistry.addName(shortGrass, "Short Grass");
	            LanguageRegistry.addName(appleLeaves, "Apple Leaves");
	            LanguageRegistry.addName(sprout, "Sprout");
	            LanguageRegistry.addName(bush, "Bush");
	    		LanguageRegistry.addName(bamboo, "Bamboo");
	    		LanguageRegistry.addName(bambooLeaves, "Bamboo Leaves");
				LanguageRegistry.addName(mudBrickBlock, "Mud Bricks");
				LanguageRegistry.addName(mudBrickSingleSlab, "Mud Bricks Slab");
				LanguageRegistry.addName(mudBrickDoubleSlab, "Mud Bricks Slab");
				LanguageRegistry.addName(mudBrickStairs, "Mud Brick Stairs");
				LanguageRegistry.addName(originGrass, "Origin Grass");
				LanguageRegistry.addName(originLeaves, "Origin Leaves");
				LanguageRegistry.addName(pinkFlower, "Tulip");
				LanguageRegistry.addName(treeMoss, "Tree Moss");
				LanguageRegistry.addName(deadWood, "Dead Wood");
				LanguageRegistry.addName(appleLeavesFruitless, "Apple Leaves");
				LanguageRegistry.addName(barley, "Barley");
				LanguageRegistry.addName(giantFlowerStem, "Giant Flower Stem");
				LanguageRegistry.addName(giantFlowerRed, "Giant Red Flower");
				LanguageRegistry.addName(giantFlowerYellow, "Giant Yellow Flower");
				LanguageRegistry.addName(tinyCactus, "Tiny Cactus");
				LanguageRegistry.addName(firSapling, "Fir Sapling");
				LanguageRegistry.addName(redwoodSapling, "Redwood Sapling");
				LanguageRegistry.addName(palmSapling, "Palm Sapling");
				LanguageRegistry.addName(redSapling, "Maple Sapling");
				LanguageRegistry.addName(orangeSapling, "Autumn Sapling");
				LanguageRegistry.addName(yellowSapling, "Autumn Sapling");
				LanguageRegistry.addName(brownSapling, "Dying Sapling");
				LanguageRegistry.addName(willowSapling, "Willow Sapling");
				LanguageRegistry.addName(appleSapling, "Apple Sapling");
				LanguageRegistry.addName(originSapling, "Origin Sapling");
				LanguageRegistry.addName(pinkSapling, "Cherry Sapling");
				LanguageRegistry.addName(whiteSapling, "Cherry Sapling");
				LanguageRegistry.addName(darkSapling, "Dark Sapling");
				LanguageRegistry.addName(magicSapling, "Magic Sapling");
				LanguageRegistry.addName(deathbloom, "Deathbloom");
				LanguageRegistry.addName(redRockCobble, "Red Rock Cobblestone");
				LanguageRegistry.addName(redRockCobbleSingleSlab, "Red Rock Cobblestone Slab");
				LanguageRegistry.addName(redRockCobbleDoubleSlab, "Red Rock Cobblestone Slab");
				LanguageRegistry.addName(redRockCobbleStairs, "Red Rock Cobblestone Stairs");
				LanguageRegistry.addName(redRockBrick, "Red Rock Bricks");
				LanguageRegistry.addName(redRockBrickSingleSlab, "Red Rock Bricks Slab");
				LanguageRegistry.addName(redRockBrickDoubleSlab, "Red Rock Bricks Slab");
				LanguageRegistry.addName(redRockBrickStairs, "Red Rock Brick Stairs");
				LanguageRegistry.addName(hydrangea, "Hydrangea");
				LanguageRegistry.addName(violet, "Violet");
				LanguageRegistry.addName(mediumGrass, "Medium Grass");
				LanguageRegistry.addName(duneGrass, "Dune Grass");
				LanguageRegistry.addName(desertSprouts, "Desert Sprouts");
				LanguageRegistry.addName(mangroveSapling, "Mangrove Sapling");
				LanguageRegistry.addName(hardSand, "Hard Sand");
				LanguageRegistry.addName(acaciaSapling, "Acacia Sapling");
				LanguageRegistry.addName(hardDirt, "Hard Dirt");
				LanguageRegistry.addName(holyGrass, "Holy Grass");
				LanguageRegistry.addName(holyStone, "Holy Stone");
				LanguageRegistry.addName(holyTallGrass, "Holy Tall Grass");
				LanguageRegistry.addName(promisedPortal, "Promised Land Portal");
				LanguageRegistry.addName(holySapling, "Holy Sapling");
				LanguageRegistry.addName(amethystOre, "Amethyst Ore");
				LanguageRegistry.addName(amethystBlock, "Block of Amethyst");
				LanguageRegistry.addName(bambooThatching, "Bamboo Thatching");
				LanguageRegistry.addName(moss, "Moss");
				LanguageRegistry.addName(algae, "Algae");
				LanguageRegistry.addName(smolderingGrass, "Smoldering Grass");
				LanguageRegistry.addName(cragRock, "Crag Rock");
				LanguageRegistry.addName(quicksand, "Quicksand");
				
	    		LanguageRegistry.addName(redwoodPlank, "Redwood Wood Planks");
	    		LanguageRegistry.addName(redwoodWood, "Redwood Wood");
	    		LanguageRegistry.addName(redwoodLeaves, "Redwood Leaves");
	    		LanguageRegistry.addName(redwoodSingleSlab, "Redwood Wood Slab");
	    		LanguageRegistry.addName(redwoodDoubleSlab, "Redwood Wood Slab");
	    		LanguageRegistry.addName(redwoodStairs, "Redwood Wood Stairs");
	    		LanguageRegistry.addName(willowPlank, "Willow Wood Planks");
	    		LanguageRegistry.addName(willowWood, "Willow Wood");
	    		LanguageRegistry.addName(willowLeaves, "Willow Leaves");
	    		LanguageRegistry.addName(willowSingleSlab, "Willow Wood Slab");
	    		LanguageRegistry.addName(willowDoubleSlab, "Willow Wood Slab");
	    		LanguageRegistry.addName(willowStairs, "Willow Wood Stairs");
	    		LanguageRegistry.addName(firPlank, "Fir Wood Planks");
	    		LanguageRegistry.addName(firWood, "Fir Wood");
	    		LanguageRegistry.addName(firLeaves, "Fir Leaves");
	    		LanguageRegistry.addName(firSingleSlab, "Fir Wood Slab");
	    		LanguageRegistry.addName(firDoubleSlab, "Fir Wood Slab");
	    		LanguageRegistry.addName(firStairs, "Fir Wood Stairs");
	    		LanguageRegistry.addName(acaciaPlank, "Acacia Wood Planks");
	    		LanguageRegistry.addName(acaciaWood, "Acacia Wood");
	    		LanguageRegistry.addName(acaciaLeaves, "Acacia Leaves");
	    		LanguageRegistry.addName(acaciaSingleSlab, "Acacia Wood Slab");
	    		LanguageRegistry.addName(acaciaDoubleSlab, "Acacia Wood Slab");
	    		LanguageRegistry.addName(acaciaStairs, "Acacia Wood Stairs");
	    		LanguageRegistry.addName(cherryPlank, "Cherry Wood Planks");
	    		LanguageRegistry.addName(cherryWood, "Cherry Wood");
	    		LanguageRegistry.addName(cherrySingleSlab, "Cherry Wood Slab");
	    		LanguageRegistry.addName(cherryDoubleSlab, "Cherry Wood Slab");
	    		LanguageRegistry.addName(cherryStairs, "Cherry Wood Stairs");
	    		LanguageRegistry.addName(darkPlank, "Dark Wood Planks");
	    		LanguageRegistry.addName(darkWood, "Dark Wood");
	    		LanguageRegistry.addName(darkLeaves, "Dark Leaves");
	    		LanguageRegistry.addName(darkSingleSlab, "Dark Wood Slab");
	    		LanguageRegistry.addName(darkDoubleSlab, "Dark Wood Slab");
	    		LanguageRegistry.addName(darkStairs, "Dark Wood Stairs");
	    		LanguageRegistry.addName(magicPlank, "Magic Wood Planks");
	    		LanguageRegistry.addName(magicWood, "Magic Wood");
	    		LanguageRegistry.addName(magicSingleSlab, "Magic Wood Slab");
	    		LanguageRegistry.addName(magicDoubleSlab, "Magic Wood Slab");
	    		LanguageRegistry.addName(magicStairs, "Magic Wood Stairs");
	    		LanguageRegistry.addName(palmPlank, "Palm Wood Planks");
	    		LanguageRegistry.addName(palmWood, "Palm Wood");
	    		LanguageRegistry.addName(palmLeaves, "Palm Leaves");
	    		LanguageRegistry.addName(palmSingleSlab, "Palm Wood Slab");
	    		LanguageRegistry.addName(palmDoubleSlab, "Palm Wood Slab");
	    		LanguageRegistry.addName(palmStairs, "Palm Wood Stairs");
				LanguageRegistry.addName(mangrovePlank, "Mangrove Wood Planks");
	    		LanguageRegistry.addName(mangroveWood, "Mangrove Wood");
	    		LanguageRegistry.addName(mangroveLeaves, "Mangrove Leaves");
	    		LanguageRegistry.addName(mangroveSingleSlab, "Mangrove Wood Slab");
	    		LanguageRegistry.addName(mangroveDoubleSlab, "Mangrove Wood Slab");
	    		LanguageRegistry.addName(mangroveStairs, "Mangrove Wood Stairs");
				LanguageRegistry.addName(holyPlank, "Holy Wood Planks");
				LanguageRegistry.addName(holyWood, "Holy Wood");
	    		LanguageRegistry.addName(holyLeaves, "Holy Leaves");
	    		LanguageRegistry.addName(holySingleSlab, "Holy Wood Slab");
	    		LanguageRegistry.addName(holyDoubleSlab, "Holy Wood Slab");
	    		LanguageRegistry.addName(holyStairs, "Holy Wood Stairs");
	            
	            LanguageRegistry.addName(shroomPowder, "Shroom Powder");
	            LanguageRegistry.addName(mudBall, "Mud Ball");
	            LanguageRegistry.addName(mudBrick, "Mud Brick");
	            LanguageRegistry.addName(bambooItem, "Bamboo");
	    		LanguageRegistry.addName(cattailItem, "Cattail");
				LanguageRegistry.addName(shortGrassItem, "Short Grass");
				LanguageRegistry.addName(mediumGrassItem, "Medium Grass");
				LanguageRegistry.addName(bushItem, "Bush");
				LanguageRegistry.addName(sproutItem, "Sprout");
				LanguageRegistry.addName(mossItem, "Moss");
				LanguageRegistry.addName(barleyItem, "Barley");
				LanguageRegistry.addName(ashes, "Pile of Ashes");
	            LanguageRegistry.addName(pickaxeMud, "Muddy Pickaxe");
	            LanguageRegistry.addName(axeMud, "Muddy Axe");
	            LanguageRegistry.addName(shovelMud, "Muddy Shovel");
	            LanguageRegistry.addName(swordMud, "Muddy Sword");
	            LanguageRegistry.addName(hoeMud, "Muddy Hoe");
	            LanguageRegistry.addName(helmetMud, "Muddy Helmet");
	            LanguageRegistry.addName(chestplateMud, "Muddy Chestplate");
	            LanguageRegistry.addName(leggingsMud, "Muddy Leggings");
	            LanguageRegistry.addName(bootsMud, "Muddy Boots");
				LanguageRegistry.addName(ancientStaff, "Ancient Staff");
				LanguageRegistry.addName(ancientStaffHandle, "Ancient Staff Handle");
				LanguageRegistry.addName(ancientStaffPole, "Ancient Staff Pole");
				LanguageRegistry.addName(ancientStaffTopper, "Ancient Staff Topper");
				LanguageRegistry.addName(enderporter, "Enderporter");
				LanguageRegistry.addName(amethyst, "Amethyst");
				LanguageRegistry.addName(bopDisc, "Music Disc");
				LanguageRegistry.addName(bopDiscMud, "Music Disc");
	            LanguageRegistry.addName(pickaxeAmethyst, "Amethyst Pickaxe");
	            LanguageRegistry.addName(axeAmethyst, "Amethyst Axe");
	            LanguageRegistry.addName(shovelAmethyst, "Amethyst Shovel");
	            LanguageRegistry.addName(swordAmethyst, "Amethyst Sword");
	            LanguageRegistry.addName(hoeAmethyst, "Amethyst Hoe");
	            LanguageRegistry.addName(helmetAmethyst, "Amethyst Helmet");
	            LanguageRegistry.addName(chestplateAmethyst, "Amethyst Chestplate");
	            LanguageRegistry.addName(leggingsAmethyst, "Amethyst Leggings");
	            LanguageRegistry.addName(bootsAmethyst, "Amethyst Boots");
				
				LanguageRegistry.instance().addStringLocalization("itemGroup.tabBiomesOPlenty", "en_US", "Biomes O\' Plenty");
        		
        		// Add block registration
        		GameRegistry.registerBlock(mud, "mud");
        		GameRegistry.registerBlock(driedDirt, "driedDirt");
        		GameRegistry.registerBlock(redRock, "redRock");
        		GameRegistry.registerBlock(ash, "ash");
        		GameRegistry.registerBlock(deadGrass, "deadGrass");
        		GameRegistry.registerBlock(desertGrass, "desertGrass");
        		GameRegistry.registerBlock(whiteFlower, "whiteFlower");
        		GameRegistry.registerBlock(blueFlower, "blueFlower");
        		GameRegistry.registerBlock(purpleFlower, "purpleFlower");
        		GameRegistry.registerBlock(orangeFlower, "orangeFlower");
        		GameRegistry.registerBlock(tinyFlower, "tinyFlower");
		        GameRegistry.registerBlock(glowFlower, "glowFlower");
		        GameRegistry.registerBlock(cattail, "cattail");
		        GameRegistry.registerBlock(willow, "willow");
		        GameRegistry.registerBlock(autumnLeaves, "autumnLeaves");
		        GameRegistry.registerBlock(thorn, "thorn");
		        GameRegistry.registerBlock(toadstool, "toadstool");
		        GameRegistry.registerBlock(highGrassBottom, "highGrassBottom");
		        GameRegistry.registerBlock(highGrassTop, "highGrassTop");
		        GameRegistry.registerBlock(ashStone, "ashStone");
		        GameRegistry.registerBlock(hardIce, "hardIce");
		        GameRegistry.registerBlock(redLeaves, "redLeaves");
		        GameRegistry.registerBlock(orangeLeaves, "orangeLeaves");
		        GameRegistry.registerBlock(pinkLeaves, "pinkLeaves");
		        GameRegistry.registerBlock(blueLeaves, "blueLeaves");
		        GameRegistry.registerBlock(whiteLeaves, "whiteLeaves");
		        GameRegistry.registerBlock(deadLeaves, "deadLeaves");
		        GameRegistry.registerBlock(shortGrass, "shortGrass");
		        GameRegistry.registerBlock(appleLeaves, "appleLeaves");
		        GameRegistry.registerBlock(sprout, "sprout");
		        GameRegistry.registerBlock(bush, "bush");
				GameRegistry.registerBlock(bamboo, "bamboo");
				GameRegistry.registerBlock(bambooLeaves, "bambooLeaves");
				GameRegistry.registerBlock(mudBrickBlock, "mudBrickBlock");
				GameRegistry.registerBlock(mudBrickDoubleSlab, "mudBrickDoubleSlab");
				GameRegistry.registerBlock(mudBrickSingleSlab, "mudBrickSingleSlab");
				GameRegistry.registerBlock(mudBrickStairs, "mudBrickStairs");
				GameRegistry.registerBlock(originGrass, "originGrass");
				GameRegistry.registerBlock(originLeaves, "originLeaves");
				GameRegistry.registerBlock(pinkFlower, "pinkFlower");
				GameRegistry.registerBlock(treeMoss, "treeMoss");
				GameRegistry.registerBlock(deadWood, "deadWood");
				GameRegistry.registerBlock(appleLeavesFruitless, "appleLeavesFruitless");
				GameRegistry.registerBlock(barley, "barley");
				GameRegistry.registerBlock(giantFlowerStem, "giantFlowerStem");
				GameRegistry.registerBlock(giantFlowerRed, "giantFlowerRed");
				GameRegistry.registerBlock(giantFlowerYellow, "giantFlowerYellow");
				GameRegistry.registerBlock(tinyCactus, "tinyCactus");
				GameRegistry.registerBlock(firSapling, "firSapling");
				GameRegistry.registerBlock(redwoodSapling, "redwoodSapling");
				GameRegistry.registerBlock(palmSapling, "palmSapling");
				GameRegistry.registerBlock(redSapling, "redSapling");
				GameRegistry.registerBlock(orangeSapling, "orangeSapling");
				GameRegistry.registerBlock(yellowSapling, "yellowSapling");
				GameRegistry.registerBlock(brownSapling, "brownSapling");
				GameRegistry.registerBlock(willowSapling, "willowSapling");
				GameRegistry.registerBlock(appleSapling, "appleSapling");
				GameRegistry.registerBlock(originSapling, "originSapling");
				GameRegistry.registerBlock(pinkSapling, "pinkSapling");
				GameRegistry.registerBlock(whiteSapling, "whiteSapling");
				GameRegistry.registerBlock(darkSapling, "darkSapling");
				GameRegistry.registerBlock(magicSapling, "magicSapling");
				GameRegistry.registerBlock(deathbloom, "deathbloom");
				GameRegistry.registerBlock(redRockCobble, "redRockCobble");
				GameRegistry.registerBlock(redRockCobbleDoubleSlab, "redRockCobbleDoubleSlab");
				GameRegistry.registerBlock(redRockCobbleSingleSlab, "redRockCobbleSingleSlab");
				GameRegistry.registerBlock(redRockCobbleStairs, "redRockCobbleStairs");
				GameRegistry.registerBlock(redRockBrick, "redRockBrick");
				GameRegistry.registerBlock(redRockBrickDoubleSlab, "redRockBrickDoubleSlab");
				GameRegistry.registerBlock(redRockBrickSingleSlab, "redRockBrickSingleSlab");
				GameRegistry.registerBlock(redRockBrickStairs, "redRockBrickStairs");
				GameRegistry.registerBlock(hydrangea, "hydrangea");
				GameRegistry.registerBlock(violet, "violet");
				GameRegistry.registerBlock(mediumGrass, "mediumGrass");
				GameRegistry.registerBlock(duneGrass, "duneGrass");
				GameRegistry.registerBlock(desertSprouts, "desertSprouts");
				GameRegistry.registerBlock(mangroveSapling, "mangroveSapling");
				GameRegistry.registerBlock(hardSand, "hardSand");
				GameRegistry.registerBlock(acaciaSapling, "acaciaSapling");
				GameRegistry.registerBlock(hardDirt, "hardDirt");
				GameRegistry.registerBlock(holyGrass, "holyGrass");
				GameRegistry.registerBlock(holyStone, "holyStone");
				GameRegistry.registerBlock(holyTallGrass, "holyTallGrass");
				GameRegistry.registerBlock(promisedPortal, "promisedPortal");
				GameRegistry.registerBlock(holySapling, "holySapling");
				GameRegistry.registerBlock(amethystOre, "amethystOre");
				GameRegistry.registerBlock(amethystBlock, "amethystBlock");
				GameRegistry.registerBlock(bambooThatching, "bambooThatching");
				GameRegistry.registerBlock(moss, "moss");
				GameRegistry.registerBlock(algae, "algae");
				GameRegistry.registerBlock(smolderingGrass, "smolderingGrass");
				GameRegistry.registerBlock(cragRock, "cragRock");
				GameRegistry.registerBlock(quicksand, "quicksand");
				
				GameRegistry.registerBlock(redwoodPlank, "redwoodPlank");
				GameRegistry.registerBlock(redwoodWood, "redwoodWood");
				GameRegistry.registerBlock(redwoodLeaves, "redwoodLeaves");
				GameRegistry.registerBlock(redwoodDoubleSlab, "redwoodDoubleSlab");
				GameRegistry.registerBlock(redwoodSingleSlab, "redwoodSingleSlab");
				GameRegistry.registerBlock(redwoodStairs, "redwoodStairs");
				
				GameRegistry.registerBlock(willowPlank, "willowPlank");
				GameRegistry.registerBlock(willowWood, "willowWood");
				GameRegistry.registerBlock(willowLeaves, "willowLeaves");
				GameRegistry.registerBlock(willowDoubleSlab, "willowDoubleSlab");
				GameRegistry.registerBlock(willowSingleSlab, "willowSingleSlab");
				GameRegistry.registerBlock(willowStairs, "willowStairs");
				
				GameRegistry.registerBlock(firPlank, "firPlank");
				GameRegistry.registerBlock(firWood, "firWood");
				GameRegistry.registerBlock(firLeaves, "firLeaves");
				GameRegistry.registerBlock(firDoubleSlab, "firDoubleSlab");
				GameRegistry.registerBlock(firSingleSlab, "firSingleSlab");
				GameRegistry.registerBlock(firStairs, "firStairs");
				
				GameRegistry.registerBlock(acaciaPlank, "acaciaPlank");
				GameRegistry.registerBlock(acaciaWood, "acaciaWood");
				GameRegistry.registerBlock(acaciaLeaves, "acaciaLeaves");
				GameRegistry.registerBlock(acaciaDoubleSlab, "acaciaDoubleSlab");
				GameRegistry.registerBlock(acaciaSingleSlab, "acaciaSingleSlab");
				GameRegistry.registerBlock(acaciaStairs, "acaciaStairs");
				
				GameRegistry.registerBlock(cherryPlank, "cherryPlank");
				GameRegistry.registerBlock(cherryWood, "cherryWood");
				GameRegistry.registerBlock(cherryDoubleSlab, "cherryDoubleSlab");
				GameRegistry.registerBlock(cherrySingleSlab, "cherrySingleSlab");
				GameRegistry.registerBlock(cherryStairs, "cherryStairs");
				
				GameRegistry.registerBlock(darkPlank, "darkPlank");
				GameRegistry.registerBlock(darkWood, "darkWood");
				GameRegistry.registerBlock(darkLeaves, "darkLeaves");
				GameRegistry.registerBlock(darkDoubleSlab, "darkDoubleSlab");
				GameRegistry.registerBlock(darkSingleSlab, "darkSingleSlab");
				GameRegistry.registerBlock(darkStairs, "darkStairs");
				
				GameRegistry.registerBlock(magicPlank, "magicPlank");
				GameRegistry.registerBlock(magicWood, "magicWood");
				GameRegistry.registerBlock(magicDoubleSlab, "magicDoubleSlab");
				GameRegistry.registerBlock(magicSingleSlab, "magicSingleSlab");
				GameRegistry.registerBlock(magicStairs, "magicStairs");
				
				GameRegistry.registerBlock(palmPlank, "palmPlank");
				GameRegistry.registerBlock(palmWood, "palmWood");
				GameRegistry.registerBlock(palmLeaves, "palmLeaves");
				GameRegistry.registerBlock(palmDoubleSlab, "palmDoubleSlab");
				GameRegistry.registerBlock(palmSingleSlab, "palmSingleSlab");
				GameRegistry.registerBlock(palmStairs, "palmStairs");
				
				GameRegistry.registerBlock(mangrovePlank, "mangrovePlank");
				GameRegistry.registerBlock(mangroveWood, "mangroveWood");
				GameRegistry.registerBlock(mangroveLeaves, "mangroveLeaves");
				GameRegistry.registerBlock(mangroveDoubleSlab, "mangroveDoubleSlab");
				GameRegistry.registerBlock(mangroveSingleSlab, "mangroveSingleSlab");
				GameRegistry.registerBlock(mangroveStairs, "mangroveStairs");
				
				GameRegistry.registerBlock(holyPlank, "holyPlank");
				GameRegistry.registerBlock(holyWood, "holyWood");
				GameRegistry.registerBlock(holyLeaves, "holyLeaves");
				GameRegistry.registerBlock(holyDoubleSlab, "holyDoubleSlab");
				GameRegistry.registerBlock(holySingleSlab, "holySingleSlab");
				GameRegistry.registerBlock(holyStairs, "holyStairs");
				
				MinecraftForge.setBlockHarvestLevel(amethystOre, "pickaxe", 3);

		        // Add crafting recipes.
				
				//Plants
		        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 6), new Object[] {blueFlower});
		        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 13), new Object[] {purpleFlower});
		        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] {orangeFlower});
				GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {pinkFlower});
				GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] {ashes});
				GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] {whiteFlower});
				GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {hydrangea});
				GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {violet});
				GameRegistry.addShapelessRecipe(new ItemStack(shroomPowder, 2), new Object[] {toadstool});
				
				//Brick stairs and slabs
				GameRegistry.addRecipe(new ItemStack(redRockCobbleSingleSlab, 6), new Object[] {"RRR", 'R', redRockCobble});
				GameRegistry.addRecipe(new ItemStack(redRockCobbleStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', redRockCobble});
				GameRegistry.addRecipe(new ItemStack(redRockCobbleStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', redRockCobble});
				GameRegistry.addRecipe(new ItemStack(redRockBrickSingleSlab, 6), new Object[] {"RRR", 'R', redRockBrick});
				GameRegistry.addRecipe(new ItemStack(redRockBrickStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', redRockBrick});
				GameRegistry.addRecipe(new ItemStack(redRockBrickStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', redRockBrick});
				GameRegistry.addRecipe(new ItemStack(mudBrickSingleSlab, 6), new Object[] {"RRR", 'R', mudBrickBlock});
				GameRegistry.addRecipe(new ItemStack(mudBrickStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', mudBrickBlock});
				GameRegistry.addRecipe(new ItemStack(mudBrickStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', mudBrickBlock});
				
				//Redwood
				OreDictionary.registerOre("plankWood", new ItemStack(redwoodPlank));
				OreDictionary.registerOre("logWood", new ItemStack(redwoodWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(redwoodPlank, 4), new Object[] {redwoodWood});
				GameRegistry.addRecipe(new ItemStack(redwoodSingleSlab, 6), new Object[] {"RRR", 'R', redwoodPlank});
				GameRegistry.addRecipe(new ItemStack(redwoodStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', redwoodPlank});
				GameRegistry.addRecipe(new ItemStack(redwoodStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', redwoodPlank});
				
				//Willow
				OreDictionary.registerOre("plankWood", new ItemStack(willowPlank));
				OreDictionary.registerOre("logWood", new ItemStack(willowWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(willowPlank, 4), new Object[] {willowWood});
				GameRegistry.addRecipe(new ItemStack(willowSingleSlab, 6), new Object[] {"RRR", 'R', willowPlank});
				GameRegistry.addRecipe(new ItemStack(willowStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', willowPlank});
				GameRegistry.addRecipe(new ItemStack(willowStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', willowPlank});
				
				//Acacia
				OreDictionary.registerOre("plankWood", new ItemStack(acaciaPlank));
				OreDictionary.registerOre("logWood", new ItemStack(acaciaWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(acaciaPlank, 4), new Object[] {acaciaWood});
				GameRegistry.addRecipe(new ItemStack(acaciaSingleSlab, 6), new Object[] {"RRR", 'R', acaciaPlank});
				GameRegistry.addRecipe(new ItemStack(acaciaStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', acaciaPlank});
				GameRegistry.addRecipe(new ItemStack(acaciaStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', acaciaPlank});
				
				//Fir
				OreDictionary.registerOre("plankWood", new ItemStack(firPlank));
				OreDictionary.registerOre("logWood", new ItemStack(firWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(firPlank, 4), new Object[] {firWood});
				GameRegistry.addRecipe(new ItemStack(firSingleSlab, 6), new Object[] {"RRR", 'R', firPlank});
				GameRegistry.addRecipe(new ItemStack(firStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', firPlank});
				GameRegistry.addRecipe(new ItemStack(firStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', firPlank});
				
				//Cherry
				OreDictionary.registerOre("plankWood", new ItemStack(cherryPlank));
				OreDictionary.registerOre("logWood", new ItemStack(cherryWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(cherryPlank, 4), new Object[] {cherryWood});
				GameRegistry.addRecipe(new ItemStack(cherrySingleSlab, 6), new Object[] {"RRR", 'R', cherryPlank});
				GameRegistry.addRecipe(new ItemStack(cherryStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', cherryPlank});
				GameRegistry.addRecipe(new ItemStack(cherryStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', cherryPlank});
				
				//Dark
				OreDictionary.registerOre("plankWood", new ItemStack(darkPlank));
				OreDictionary.registerOre("logWood", new ItemStack(darkWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(darkPlank, 4), new Object[] {darkWood});
				GameRegistry.addRecipe(new ItemStack(darkSingleSlab, 6), new Object[] {"RRR", 'R', darkPlank});
				GameRegistry.addRecipe(new ItemStack(darkStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', darkPlank});
				GameRegistry.addRecipe(new ItemStack(darkStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', darkPlank});
				
				//Magic
				OreDictionary.registerOre("plankWood", new ItemStack(magicPlank));
				OreDictionary.registerOre("logWood", new ItemStack(magicWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(magicPlank, 4), new Object[] {magicWood});
				GameRegistry.addRecipe(new ItemStack(magicSingleSlab, 6), new Object[] {"RRR", 'R', magicPlank});
				GameRegistry.addRecipe(new ItemStack(magicStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', magicPlank});
				GameRegistry.addRecipe(new ItemStack(magicStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', magicPlank});
				
				//Palm
				OreDictionary.registerOre("plankWood", new ItemStack(palmPlank));
				OreDictionary.registerOre("logWood", new ItemStack(palmWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(palmPlank, 4), new Object[] {palmWood});
				GameRegistry.addRecipe(new ItemStack(palmSingleSlab, 6), new Object[] {"RRR", 'R', palmPlank});
				GameRegistry.addRecipe(new ItemStack(palmStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', palmPlank});
				GameRegistry.addRecipe(new ItemStack(palmStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', palmPlank});
				
				//Mangrove
				OreDictionary.registerOre("plankWood", new ItemStack(mangrovePlank));
				OreDictionary.registerOre("logWood", new ItemStack(mangroveWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(mangrovePlank, 4), new Object[] {mangroveWood});
				GameRegistry.addRecipe(new ItemStack(mangroveSingleSlab, 6), new Object[] {"RRR", 'R', mangrovePlank});
				GameRegistry.addRecipe(new ItemStack(mangroveStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', mangrovePlank});
				GameRegistry.addRecipe(new ItemStack(mangroveStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', mangrovePlank});
				
				//Holy
				OreDictionary.registerOre("plankWood", new ItemStack(holyPlank));
				OreDictionary.registerOre("logWood", new ItemStack(holyWood));
				
				GameRegistry.addShapelessRecipe(new ItemStack(holyPlank, 4), new Object[] {holyWood});
				GameRegistry.addRecipe(new ItemStack(holySingleSlab, 6), new Object[] {"RRR", 'R', holyPlank});
				GameRegistry.addRecipe(new ItemStack(holyStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', holyPlank});
				GameRegistry.addRecipe(new ItemStack(holyStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', holyPlank});
				
				//Mud Tools and Armor
				GameRegistry.addRecipe(new ItemStack(pickaxeMud, 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
				GameRegistry.addRecipe(new ItemStack(shovelMud, 1), new Object [] {"#", "X", "X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
				GameRegistry.addRecipe(new ItemStack(swordMud, 1), new Object [] {"#", "#", "X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
				GameRegistry.addRecipe(new ItemStack(axeMud, 1), new Object [] {"##", "#X ", " X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
				GameRegistry.addRecipe(new ItemStack(axeMud, 1), new Object [] {"##", "X#", "X ", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
				GameRegistry.addRecipe(new ItemStack(hoeMud, 1), new Object [] {"##", " X", " X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
				GameRegistry.addRecipe(new ItemStack(hoeMud, 1), new Object [] {"##", "X ", "X ", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
				GameRegistry.addRecipe(new ItemStack(helmetMud, 1), new Object [] {"###", "# #", Character.valueOf('#'), mudBall});
				GameRegistry.addRecipe(new ItemStack(chestplateMud, 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), mudBall});
				GameRegistry.addRecipe(new ItemStack(leggingsMud, 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), mudBall});
				GameRegistry.addRecipe(new ItemStack(bootsMud, 1), new Object [] {"# #", "# #", Character.valueOf('#'), mudBall});
				
				//Amethyst Tools and Armor
				GameRegistry.addRecipe(new ItemStack(pickaxeAmethyst, 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
				GameRegistry.addRecipe(new ItemStack(shovelAmethyst, 1), new Object [] {"#", "X", "X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
				GameRegistry.addRecipe(new ItemStack(swordAmethyst, 1), new Object [] {"#", "#", "X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
				GameRegistry.addRecipe(new ItemStack(axeAmethyst, 1), new Object [] {"##", "#X ", " X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
				GameRegistry.addRecipe(new ItemStack(axeAmethyst, 1), new Object [] {"##", "X#", "X ", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
				GameRegistry.addRecipe(new ItemStack(hoeAmethyst, 1), new Object [] {"##", " X", " X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
				GameRegistry.addRecipe(new ItemStack(hoeAmethyst, 1), new Object [] {"##", "X ", "X ", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
				GameRegistry.addRecipe(new ItemStack(helmetAmethyst, 1), new Object [] {"###", "# #", Character.valueOf('#'), amethyst});
				GameRegistry.addRecipe(new ItemStack(chestplateAmethyst, 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), amethyst});
				GameRegistry.addRecipe(new ItemStack(leggingsAmethyst, 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), amethyst});
				GameRegistry.addRecipe(new ItemStack(bootsAmethyst, 1), new Object [] {"# #", "# #", Character.valueOf('#'), amethyst});
				
				//Other
		        GameRegistry.addRecipe(new ItemStack(Block.cloth, 1, 0), new Object[] {"CCC", "CCC", "CCC", 'C', cattailItem});
		        GameRegistry.addRecipe(new ItemStack(Item.coal, 1), new Object[] {"AAA", "AAA", "AAA", 'A', ashes});
				GameRegistry.addRecipe(new ItemStack(mud, 1), new Object[] {"MM", "MM", 'M', mudBall});
				GameRegistry.addRecipe(new ItemStack(amethystBlock, 1), new Object[] {"AAA", "AAA", "AAA", 'A', amethyst});
				GameRegistry.addShapelessRecipe(new ItemStack(amethyst, 9), new Object[] {amethystBlock});
				GameRegistry.addRecipe(new ItemStack(ash, 1), new Object[] {"AA", "AA", 'A', ashes});
				GameRegistry.addRecipe(new ItemStack(mudBrickBlock, 1), new Object[] {"MM", "MM", 'M', mudBrick});
				GameRegistry.addRecipe(new ItemStack(redRockBrick, 4), new Object[] {"RR", "RR", 'R', redRock});
				GameRegistry.addRecipe(new ItemStack(ancientStaff, 1, 0), new Object[] {"T", "P", "H", 'T', ancientStaffTopper, 'P', ancientStaffPole, 'H', ancientStaffHandle});
				GameRegistry.addRecipe(new ItemStack(ancientStaffHandle, 1, 0), new Object[] {"ISI", "ISI", " E ", 'I', Item.ingotIron, 'S', Block.whiteStone, 'E', Item.emerald});
				GameRegistry.addRecipe(new ItemStack(ancientStaffPole, 1, 0), new Object[] {"ISI", "IRI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'R', Item.redstone});
				GameRegistry.addRecipe(new ItemStack(ancientStaffTopper, 1, 0), new Object[] {" N ", "IDI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'D', Item.diamond, 'N', Item.netherStar});
				GameRegistry.addRecipe(new ItemStack(enderporter, 1, 0), new Object[] {"IOI", "OAO", "IOI", 'I', Item.eyeOfEnder, 'O', Block.obsidian, 'A', amethystBlock});
				GameRegistry.addRecipe(new ItemStack(Item.wheat, 1), new Object[] {"###", '#', barleyItem});
				GameRegistry.addRecipe(new ItemStack(bambooThatching, 1), new Object[] {"###", "###", "###", '#', bambooItem});
				GameRegistry.addRecipe(new ItemStack(bopDiscMud, 1), new Object[] {" M ", "MDM", " M ", 'M', mudBall, 'D', bopDisc});
				GameRegistry.addShapelessRecipe(new ItemStack(bambooItem, 9), new Object[] {bambooThatching});
				GameRegistry.addShapelessRecipe(new ItemStack(Block.cobblestoneMossy, 1), new Object[] {Block.cobblestone, mossItem});
				GameRegistry.addRecipe(new ItemStack(Block.cobblestoneMossy, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', mossItem, 'C', Block.cobblestone});
				GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', mossItem, 'S', Block.stoneBrick});

		        GameRegistry.addSmelting(Block.dirt.blockID, new ItemStack(driedDirt, 1), 0F);
				GameRegistry.addSmelting(mudBall.itemID, new ItemStack(mudBrick, 1), 0F);
				GameRegistry.addSmelting(redRockCobble.blockID, new ItemStack(redRock, 1), 0.1F);
				GameRegistry.addSmelting(tinyCactus.blockID, new ItemStack(Item.dyePowder, 1, 2), 0.2F);
		        
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
				
				dungeon.addItem(new WeightedRandomChestContent(new ItemStack(bopDisc), 1, 1, 2));
				dungeon.addItem(new WeightedRandomChestContent(new ItemStack(mossItem), 2, 8, 50));
				dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 2), 4, 12, 75));
				
				mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(ashes), 2, 8, 25));
				mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(thorn), 4, 6, 15));
				mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(mudBall), 2, 8, 10));
				mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 3), 4, 12, 75));
				
				strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(mossItem), 2, 8, 50));
				strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(glowFlower), 1, 4, 25));
				strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(deathbloom), 1, 4, 25));
				
				strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(mossItem), 2, 8, 50));
				strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(glowFlower), 1, 4, 25));
				strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(deathbloom), 1, 4, 25));
				
				village.addItem(new WeightedRandomChestContent(new ItemStack(barleyItem), 4, 10, 75));
				village.addItem(new WeightedRandomChestContent(new ItemStack(shroomPowder), 1, 5, 50));
				village.addItem(new WeightedRandomChestContent(new ItemStack(thorn), 2, 6, 25));
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
        
		// Block declaration
        public static Block mud;
        public static Block driedDirt;
        public static Block redRock;
        public static Block ash;
        public static Block deadGrass;
        public static Block desertGrass;
        public static Block whiteFlower;
        public static Block blueFlower;
        public static Block purpleFlower;
        public static Block orangeFlower;
        public static Block tinyFlower;
        public static Block glowFlower;
        public static Block cattail;
        public static Block willow;
        public static Block autumnLeaves;
        public static Block thorn;
        public static Block toadstool;
        public static BlockHighGrassBottom highGrassBottom;
        public static BlockHighGrassTop highGrassTop;
        public static Block ashStone;
        public static Block hardIce;
        public static Block redLeaves;
        public static Block orangeLeaves;
        public static Block pinkLeaves;
        public static Block blueLeaves;
        public static Block whiteLeaves;
        public static Block deadLeaves;
        public static BlockShortGrass shortGrass;
        public static Block appleLeaves;
        public static BlockSprout sprout;
        public static BlockBush bush;
        public static Block bamboo;
        public static Block bambooLeaves;
		public static Block mudBrickBlock;
		public static BlockHalfSlab mudBrickDoubleSlab;
        public static BlockHalfSlab mudBrickSingleSlab;
        public static Block mudBrickStairs;
		public static Block originGrass;
		public static Block originLeaves;
		public static Block pinkFlower;
		public static Block treeMoss;
		public static Block deadWood;
		public static Block appleLeavesFruitless;
		public static Block barley;
		public static Block giantFlowerStem;
		public static Block giantFlowerRed;
		public static Block giantFlowerYellow;
		public static Block tinyCactus;
		public static Block firSapling;
		public static Block redwoodSapling;
		public static Block palmSapling;
		public static Block redSapling;
		public static Block orangeSapling;
		public static Block yellowSapling;
		public static Block brownSapling;
		public static Block willowSapling;
		public static Block appleSapling;
		public static Block originSapling;
		public static Block pinkSapling;
		public static Block whiteSapling;
		public static Block darkSapling;
		public static Block magicSapling;
		public static Block deathbloom;
		public static Block redRockCobble;
		public static BlockHalfSlab redRockCobbleDoubleSlab;
        public static BlockHalfSlab redRockCobbleSingleSlab;
        public static Block redRockCobbleStairs;
		public static Block redRockBrick;
		public static BlockHalfSlab redRockBrickDoubleSlab;
        public static BlockHalfSlab redRockBrickSingleSlab;
        public static Block redRockBrickStairs;
		public static Block hydrangea;
		public static Block violet;
		public static BlockMediumGrass mediumGrass;
		public static Block duneGrass;
		public static Block desertSprouts;
		public static Block mangroveSapling;
		public static Block hardSand;
		public static Block acaciaSapling;
		public static Block hardDirt;
		public static Block holyGrass;
		public static Block holyStone;
		public static Block holyTallGrass;
		public static Block promisedPortal;
		public static Block holySapling;
		public static Block amethystOre;
		public static Block amethystBlock;
		public static Block bambooThatching;
		public static Block moss;
		public static Block algae;
		public static Block smolderingGrass;
		public static Block cragRock;
		public static Block quicksand;
    
    	//Redwood
        public static Block redwoodPlank;
        public static Block redwoodWood;
        public static Block redwoodLeaves;
        public static BlockHalfSlab redwoodDoubleSlab;
        public static BlockHalfSlab redwoodSingleSlab;
        public static Block redwoodStairs;
    	
    	//Willow
        public static Block willowPlank;
        public static Block willowWood;
        public static Block willowLeaves;
        public static BlockHalfSlab willowDoubleSlab;
        public static BlockHalfSlab willowSingleSlab;
    	public static Block willowStairs;
    	
    	//Fir
        public static Block firPlank;
        public static Block firWood;
        public static Block firLeaves;
        public static BlockHalfSlab firDoubleSlab;
        public static BlockHalfSlab firSingleSlab;
    	public static Block firStairs;
    	
    	//Acacia
        public static Block acaciaPlank;
        public static Block acaciaWood;
        public static Block acaciaLeaves;
        public static BlockHalfSlab acaciaDoubleSlab;
        public static BlockHalfSlab acaciaSingleSlab;
    	public static Block acaciaStairs;
		
    	//Cherry
        public static Block cherryPlank;
        public static Block cherryWood;
        public static BlockHalfSlab cherryDoubleSlab;
        public static BlockHalfSlab cherrySingleSlab;
    	public static Block cherryStairs;
		
    	//Dark
        public static Block darkPlank;
        public static Block darkWood;
        public static Block darkLeaves;
        public static BlockHalfSlab darkDoubleSlab;
        public static BlockHalfSlab darkSingleSlab;
    	public static Block darkStairs;
		
    	//Magic
        public static Block magicPlank;
        public static Block magicWood;
        public static BlockHalfSlab magicDoubleSlab;
        public static BlockHalfSlab magicSingleSlab;
    	public static Block magicStairs;
		
    	//Palm
        public static Block palmPlank;
        public static Block palmWood;
        public static Block palmLeaves;
        public static BlockHalfSlab palmDoubleSlab;
        public static BlockHalfSlab palmSingleSlab;
    	public static Block palmStairs;
		
		//Mangrove
        public static Block mangrovePlank;
        public static Block mangroveWood;
        public static Block mangroveLeaves;
        public static BlockHalfSlab mangroveDoubleSlab;
        public static BlockHalfSlab mangroveSingleSlab;
    	public static Block mangroveStairs;
		
		//Holy
        public static Block holyPlank;
        public static Block holyWood;
        public static Block holyLeaves;
        public static BlockHalfSlab holyDoubleSlab;
        public static BlockHalfSlab holySingleSlab;
    	public static Block holyStairs;
		
		// Material declaration
		public static EnumArmorMaterial EnumArmorMaterialMud;
		public static EnumToolMaterial EnumToolMaterialMud;
		public static EnumArmorMaterial EnumArmorMaterialAmethyst;
		public static EnumToolMaterial EnumToolMaterialAmethyst;
		
		public static AchievementPage pageBOP;
		
		// Item declaration
		public static Item shroomPowder;
		public static Item mudBall;
		public static Item mudBrick;
		public static Item cattailItem;
		public static Item barleyItem;
		public static Item shortGrassItem;
		public static Item mediumGrassItem;
		public static Item bushItem;
		public static Item sproutItem;
		public static Item mossItem;
		public static Item ashes;
		public static Item bambooItem;
		public static Item ancientStaff;
		public static Item ancientStaffHandle;
		public static Item ancientStaffPole;
		public static Item ancientStaffTopper;
		public static Item enderporter;
		public static Item amethyst;
		public static Item bopDisc;
		public static Item bopDiscMud;
		
		public static Item swordMud;
		public static Item shovelMud;
		public static Item pickaxeMud;
		public static Item axeMud;
		public static Item hoeMud;
		public static Item helmetMud;
		public static Item chestplateMud;
		public static Item leggingsMud;
		public static Item bootsMud;
		
		public static Item swordAmethyst;
		public static Item shovelAmethyst;
		public static Item pickaxeAmethyst;
		public static Item axeAmethyst;
		public static Item hoeAmethyst;
		public static Item helmetAmethyst;
		public static Item chestplateAmethyst;
		public static Item leggingsAmethyst;
		public static Item bootsAmethyst;
		
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
		
		private int mudID;
		private int driedDirtID;
		private int redRockID;
		private int ashID;
		private int deadGrassID;
		private int desertGrassID;
		private int whiteFlowerID;
		private int blueFlowerID;
		private int purpleFlowerID;
		private int orangeFlowerID;
		private int tinyFlowerID;
		private int glowFlowerID;
		private int cattailID;
		private int willowID;
		private int autumnLeavesID;
		private int thornID;
		private int toadstoolID;
		private int highGrassBottomID;
		private int highGrassTopID;
		private int ashStoneID;
		private int hardIceID;
		private int redLeavesID;
		private int orangeLeavesID;
		private int pinkLeavesID;
		private int blueLeavesID;
		private int whiteLeavesID;
		private int deadLeavesID;
		private int shortGrassID;
		private int appleLeavesID;
		private int sproutID;
		private int bushID;
        private int bambooID;
        private int bambooLeavesID;
		private int mudBrickBlockID;
		private int mudBrickDoubleSlabID;
		private int mudBrickSingleSlabID;
		private int mudBrickStairsID;
		private int originGrassID;
		private int originLeavesID;
		private int pinkFlowerID;
		private int treeMossID;
		private int deadWoodID;
		private int appleLeavesFruitlessID;
		private int barleyID;
		private int giantFlowerStemID;
		private int giantFlowerRedID;
		private int giantFlowerYellowID;
		private int tinyCactusID;
		private int firSaplingID;
		private int redwoodSaplingID;
		private int palmSaplingID;
		private int redSaplingID;
		private int orangeSaplingID;
		private int yellowSaplingID;
		private int brownSaplingID;
		private int willowSaplingID;
		private int appleSaplingID;
		private int originSaplingID;
		private int pinkSaplingID;
		private int whiteSaplingID;
		private int darkSaplingID;
		private int magicSaplingID;
		private int deathbloomID;
		private int redRockCobbleID;
		private int redRockCobbleDoubleSlabID;
		private int redRockCobbleSingleSlabID;
		private int redRockCobbleStairsID;
		private int redRockBrickID;
		private int redRockBrickDoubleSlabID;
		private int redRockBrickSingleSlabID;
		private int redRockBrickStairsID;
		private int hydrangeaID;
		private int violetID;
		private int mediumGrassID;
		private int duneGrassID;
		private int desertSproutsID;
		private int mangroveSaplingID;
		private int hardSandID;
		private int acaciaSaplingID;
		private int hardDirtID;
		private int holyGrassID;
		private int holyStoneID;
		private int holyTallGrassID;
		private int promisedLandPortalID;
		private int holySaplingID;
		private int amethystOreID;
		private int amethystBlockID;
		private int bambooThatchingID;
		private int mossID;
		private int algaeID;
		private int smolderingGrassID;
		private int cragRockID;
		private int quicksandID;
    	
    	//Redwood
        private int redwoodPlankID;
        private int redwoodWoodID;
        private int redwoodLeavesID;
        private int redwoodDoubleSlabID;
        private int redwoodSingleSlabID;
        private int redwoodStairsID;
    	
    	//Willow
        private int willowPlankID;
        private int willowWoodID;
        private int willowLeavesID;
        private int willowDoubleSlabID;
        private int willowSingleSlabID;
    	private int willowStairsID;
    	
    	//Fir
        private int firPlankID;
        private int firWoodID;
        private int firLeavesID;
        private int firDoubleSlabID;
        private int firSingleSlabID;
    	private int firStairsID;
    	
    	//Acacia
        private int acaciaPlankID;
        private int acaciaWoodID;
        private int acaciaLeavesID;
        private int acaciaDoubleSlabID;
        private int acaciaSingleSlabID;
    	private int acaciaStairsID;
		
    	//Cherry
        private int cherryPlankID;
        private int cherryWoodID;
        private int cherryDoubleSlabID;
        private int cherrySingleSlabID;
    	private int cherryStairsID;
		
    	//Dark
        private int darkPlankID;
        private int darkWoodID;
        private int darkLeavesID;
        private int darkDoubleSlabID;
        private int darkSingleSlabID;
    	private int darkStairsID;
		
    	//Magic
        private int magicPlankID;
        private int magicWoodID;
        private int magicDoubleSlabID;
        private int magicSingleSlabID;
    	private int magicStairsID;
		
    	//Palm
        private int palmPlankID;
        private int palmWoodID;
        private int palmLeavesID;
        private int palmDoubleSlabID;
        private int palmSingleSlabID;
    	private int palmStairsID;
		
		//Mangrove
        private int mangrovePlankID;
        private int mangroveWoodID;
        private int mangroveLeavesID;
        private int mangroveDoubleSlabID;
        private int mangroveSingleSlabID;
    	private int mangroveStairsID;
		
		//Holy
        private int holyPlankID;
        private int holyWoodID;
        private int holyLeavesID;
        private int holyDoubleSlabID;
        private int holySingleSlabID;
    	private int holyStairsID;
		
		private int shroomPowderID;
		private int mudBallID;
		private int mudBrickID;
		private int cattailItemID;
		private int bambooItemID;
		private int barleyItemID;
		private int shortGrassItemID;
		private int mediumGrassItemID;
		private int bushItemID;
		private int sproutItemID;
		private int mossItemID;
		private int ashesID;
		private int ancientStaffID;
		private int ancientStaffHandleID;
		private int ancientStaffPoleID;
		private int ancientStaffTopperID;
		private int enderporterID;
		private int bopDiscID;
		private int bopDiscMudID;
		private int swordMudID;
		private int shovelMudID;
		private int pickaxeMudID;
		private int axeMudID;
		private int hoeMudID;
		private int helmetMudID;
		private int chestplateMudID;
		private int leggingsMudID;
		private int bootsMudID;
		private int amethystID;
		private int swordAmethystID;
		private int shovelAmethystID;
		private int pickaxeAmethystID;
		private int axeAmethystID;
		private int hoeAmethystID;
		private int helmetAmethystID;
		private int chestplateAmethystID;
		private int leggingsAmethystID;
		private int bootsAmethystID;
		
        private int alpsID;
        private int arcticID;
        private int arcticForestID;
        private int badlandsID;
        private int bambooForestID;
        private int bayouID;
        private int birchForestID;
        private int bogID;
        private int borealForestID;
		private int canyonID;
        private int chaparralID;
        private int cherryBlossomGroveID;
        private int coniferousForestID;
        private int coniferousForestThinID;
        private int cragID;
        private int deadForestID;
		private int deadSwampID;
        private int deadlandsID;
        private int deciduousForestID;
        private int drylandsID;
        private int dunesID;
		private int fenID;
		private int fieldID;
        private int frostForestID;
        private int fungiForestID;
        private int gardenID;
        private int glacierID;
        private int grasslandID;
        private int groveID;
        private int groveThinID;
        private int heathlandID;
        private int highlandID;
		private int iceSheetID;
        private int icyHillsID;
		private int jadeCliffsID;
        private int lushDesertID;
        private int lushSwampID;
        private int mangroveID;
        private int mapleWoodsID;
        private int marshID;
        private int meadowID;
        private int meadowForestID;
        private int mesaID;
		private int moorID;
        private int mountainID;
        private int mysticGroveID;
        private int oasisID;
        private int ominousWoodsID;
        private int orchardID;
        private int originValleyID;
		private int outbackID;
        private int pastureID;
        private int prairieID;
        private int promisedLandID;
        private int promisedLandHillsID;
		private int promisedLandPlainsID;
        private int promisedLandSwampID;
        private int quagmireID;
        private int rainforestID;
        private int redwoodForestID;
		private int reefID;
        private int sacredSpringsID;
        private int savannaID;
        private int savannaThickID;
        private int scrublandID;
        private int seasonalForestID;
		private int shieldID;
        private int shoreID;
        private int shrublandID;
		private int snowyWoodsID;
		private int spruceWoodsID;
        private int steppeID;
		private int swampwoodsID;
        private int temperateRainforestID;
        private int thicketID;
        private int tropicalRainforestID;
        private int tropicsID;
        private int tundraID;
        private int tundraDryID;
        private int volcanoID;
        private int wastelandID;
        private int wastelandTreesID;
        private int wetlandID;
        private int woodlandID;
        private int plainsNewID;
        private int desertNewID;
        private int desertHillsNewID;
        private int extremeHillsNewID;
        private int extremeHillsEdgeNewID;
        private int forestNewID;
        private int forestHillsNewID;
        private int taigaNewID;
        private int taigaHillsNewID;
        private int swamplandNewID;
        private int jungleNewID;
        private int jungleHillsNewID;
		
		private int jungleSpiderID;
		private int rosesterID;
        
		// Add Fuel rates
		public static int addFuel(int par1, int par2)
		{
			if(par1 == redwoodSapling.blockID)
			{
				return 100;
			}
			if(par1 == redwoodSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == redwoodStairs.blockID)
			{
				return 300;
			}
			if(par1 == willowSapling.blockID)
			{
				return 100;
			}
			if(par1 == willowSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == willowStairs.blockID)
			{
				return 300;
			}
			if(par1 == firSapling.blockID)
			{
				return 100;
			}
			if(par1 == firSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == firStairs.blockID)
			{
				return 300;
			}
			if(par1 == acaciaSapling.blockID)
			{
				return 100;
			}
			if(par1 == acaciaSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == acaciaStairs.blockID)
			{
				return 300;
			}
			if(par1 == pinkSapling.blockID)
			{
				return 100;
			}
			if(par1 == whiteSapling.blockID)
			{
				return 100;
			}
			if(par1 == orangeSapling.blockID)
			{
				return 100;
			}
			if(par1 == yellowSapling.blockID)
			{
				return 100;
			}
			if(par1 == redSapling.blockID)
			{
				return 100;
			}
			if(par1 == brownSapling.blockID)
			{
				return 100;
			}
			if(par1 == appleSapling.blockID)
			{
				return 100;
			}
			if(par1 == originSapling.blockID)
			{
				return 100;
			}
			if(par1 == cherrySingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == cherryStairs.blockID)
			{
				return 300;
			}
			if(par1 == darkSapling.blockID)
			{
				return 100;
			}
			if(par1 == darkSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == darkStairs.blockID)
			{
				return 300;
			}
			if(par1 == magicSapling.blockID)
			{
				return 100;
			}
			if(par1 == magicSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == magicStairs.blockID)
			{
				return 300;
			}
			if(par1 == palmSapling.blockID)
			{
				return 100;
			}
			if(par1 == palmSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == palmStairs.blockID)
			{
				return 300;
			}
			if(par1 == mangroveSapling.blockID)
			{
				return 100;
			}
			if(par1 == mangroveSingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == mangroveStairs.blockID)
			{
				return 300;
			}
			if(par1 == holySapling.blockID)
			{
				return 100;
			}
			if(par1 == holySingleSlab.blockID)
			{
				return 150;
			}
			if(par1 == holyStairs.blockID)
			{
				return 300;
			}
			if(par1 == ashes.itemID)
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
			if (item.itemID == glowFlower.blockID || item.itemID == orangeFlower.blockID || item.itemID == blueFlower.blockID || item.itemID == purpleFlower.blockID || item.itemID == pinkFlower.blockID || item.itemID == whiteFlower.blockID || item.itemID == tinyFlower.blockID || item.itemID == deathbloom.blockID || item.itemID == hydrangea.blockID || item.itemID == violet.blockID || item.itemID == Block.plantRed.blockID || item.itemID == Block.plantYellow.blockID)
			{
				player.addStat(achFlower2, 1);
			}
			if (item.itemID == redRockCobble.blockID)
			{
				player.addStat(achRedRock2, 1);
			}
			if (item.itemID == thorn.blockID)
			{
				player.addStat(achThorn2, 1);
			}
			if (item.itemID == ashes.itemID)
			{
				player.addStat(achAsh2, 1);
			}
			if (item.itemID == originGrass.blockID)
			{
				player.addStat(achOrigin2, 1);
			}
			if (item.itemID == holyGrass.blockID || item.itemID == holyStone.blockID)
			{
				player.addStat(achPromised2, 1);
			}
			if (item.itemID == mudBall.itemID)
			{
				player.addStat(achMud2, 1);
			}
			if (item.itemID == toadstool.blockID)
			{
				player.addStat(achShroom2, 1);
			}
			if (item.itemID == barleyItem.itemID)
			{
				player.addStat(achBarley2, 1);
			}
			if (item.itemID == mossItem.itemID)
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