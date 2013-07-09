package biomesoplenty.helpers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.api.Fluids;
import biomesoplenty.configuration.BOPConfiguration;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class AchievementHelper
{
	// Achievement declaration
	public static Achievement achFlower;
	public static Achievement achRedRock;
	public static Achievement achThorn;
	public static Achievement achAsh;
	public static Achievement achOrigin;
	public static Achievement achPromised;
	public static Achievement achMud;
	public static Achievement achShroom;
	public static Achievement achBarley;
	public static Achievement achMoss;
	public static Achievement achFlowerP;
	
	public static Achievement achBOP;
	public static Achievement achAllBiomes;
	
	public static Achievement achAlps;
	public static Achievement achArctic;
	public static Achievement achBadlands;
	public static Achievement achBambooForest;
	public static Achievement achBayou;
	public static Achievement achBirchForest;
	public static Achievement achBog;
	public static Achievement achBorealForest;
	public static Achievement achBrushland;
	public static Achievement achCanyon;
	public static Achievement achChaparral;
	public static Achievement achCherryBlossom;
	public static Achievement achConiferousForest;
	public static Achievement achConiferousForestSnow;
	public static Achievement achCrag; 
	public static Achievement achDeadForest;
	public static Achievement achDeadForestSnow;
	public static Achievement achDeadSwamp;
	public static Achievement achDeadlands;
	public static Achievement achDeciduousForest;
	public static Achievement achDunes; 
	public static Achievement achFen; 
	public static Achievement achField;
	public static Achievement achFrostForest;
	public static Achievement achFungiForest;
	public static Achievement achGarden;
	public static Achievement achGlacier; 
	public static Achievement achGrassland; 
	public static Achievement achGrove;
	public static Achievement achHeathland; 
	public static Achievement achHighland;
	public static Achievement achHotSprings;
	public static Achievement achIcyHills;  
	public static Achievement achJadeCliffs; 
	public static Achievement achLushDesert; 
	public static Achievement achLushSwamp; 
	public static Achievement achMangrove; 
	public static Achievement achMapleWoods;
	public static Achievement achMarsh; 
	public static Achievement achMeadow;
	public static Achievement achMesa; 
	public static Achievement achMoor; 
	public static Achievement achMountain;  
	public static Achievement achMysticGrove;
	public static Achievement achOasis;
	public static Achievement achOminousWoods;
	public static Achievement achOrchard;
	public static Achievement achOriginValley;
	public static Achievement achOutback;
	public static Achievement achPasture;
	public static Achievement achPolar;
	public static Achievement achPrairie;
	public static Achievement achQuagmire;
	public static Achievement achRainforest;
	public static Achievement achRedwoodForest; 
	public static Achievement achSacredSprings; 
	public static Achievement achSavanna;
	public static Achievement achScrubland;
	public static Achievement achSeasonalForest;
	
	public static Achievement achShield;
	public static Achievement achShrubland;
	public static Achievement achSludgepit;
	public static Achievement achSpruceWoods;
	public static Achievement achSteppe;
	public static Achievement achTemperateRainforest;
	public static Achievement achThicket;
	public static Achievement achTimber;
	public static Achievement achTropicalRainforest;
	public static Achievement achTropics;
	public static Achievement achTundra;
	public static Achievement achVolcano;
	public static Achievement achWasteland;
	public static Achievement achWetland;
	public static Achievement achWoodland;
	
	public static Achievement achDesert;
	public static Achievement achExtremeHills;
	public static Achievement achForest;
	public static Achievement achIcePlains;
	public static Achievement achJungle;
	public static Achievement achMushroomIsland;
	public static Achievement achPlains;
	public static Achievement achSwampland;
	public static Achievement achTaiga;

	public static AchievementPage pageBOP;
	public static AchievementPage pageBiome;
	
	static Achievement[] biomeFinderAchievementList;

	@ForgeSubscribe
	public void EntityItemPickupEvent(EntityItemPickupEvent event)
	{
		onItemPickup(event.entityPlayer, event.item.getEntityItem());
	}

	public static void init()
	{
		achFlower = (new Achievement(3057, "achFlower", 0, 0, Block.plantRed, null)).registerAchievement();
		achRedRock = (new Achievement(3058, "achRedRock", -2, 2, new ItemStack(Blocks.redRock.get(),1,0), achFlower)).registerAchievement();
		achThorn = (new Achievement(3059, "achThorn", 2, 1, new ItemStack(Blocks.plants.get(),1,5), achFlower)).registerAchievement();
		achAsh = (new Achievement(3060, "achAsh", 1, 3, new ItemStack(Items.miscItems.get(), 1, 1), achFlower)).registerAchievement();
		achOrigin = (new Achievement(3061, "achOrigin", 0, 5, Blocks.originGrass.get(), achFlower)).setSpecial().registerAchievement();
		achPromised = (new Achievement(3062, "achPromised", 0, -5, Blocks.holyGrass.get(), achFlower)).setSpecial().registerAchievement();
		achMud = (new Achievement(3063, "achMud", -2, -1, Items.mudball.get(), achFlower)).registerAchievement();
		achShroom = (new Achievement(3064, "achShroom", 2, -2, new ItemStack(Blocks.mushrooms.get(),1,0), achFlower)).registerAchievement();
		achBarley = (new Achievement(3065, "achBarley", -1, 4, new ItemStack(Blocks.plants.get(),1,6), achFlower)).registerAchievement();
		achMoss = (new Achievement(3066, "achMoss", -1, -3, Blocks.moss.get(), achFlower)).registerAchievement();
		achFlowerP = (new Achievement(3067, "achFlowerP", 1, -4, new ItemStack(Items.flowerBand.get(), 1, 0), achFlower)).registerAchievement();
		
		achBOP = (new Achievement(3080, "achBOP", -10, -6, new ItemStack(Blocks.saplings.get(), 1, 6), null)).registerAchievement().setSpecial();
		achAllBiomes = (new Achievement(3081, "achAllBiomes", 4, 8, new ItemStack(Blocks.saplings.get(), 1, 7), null)).registerAchievement().setSpecial();
		achAlps = (new Achievement(3082, "achAlps", -8, -6, new ItemStack(Block.stone, 1, 0), achBOP)).registerAchievement();
		achArctic = (new Achievement(3083, "achArctic", -6, -6, new ItemStack(Block.blockSnow, 1, 0), achBOP)).registerAchievement();
		achBadlands = (new Achievement(3084, "achBadlands", -4, -6, new ItemStack(Block.field_111032_cD, 1, 0), achBOP)).registerAchievement();
		achBambooForest = (new Achievement(3085, "achBambooForest", -2, -6, new ItemStack(Blocks.saplings.get(), 1, 2), achBOP)).registerAchievement();
		achBayou = (new Achievement(3086, "achBayou", 0, -6, new ItemStack(Blocks.logs3.get(), 1, 1), achBOP)).registerAchievement();
		achBirchForest = (new Achievement(3087, "achBirchForest", 2, -6, new ItemStack(Block.sapling, 1, 2), achBOP)).registerAchievement();
		achBog = (new Achievement(3088, "achBog", 4, -6, new ItemStack(Blocks.plants.get(), 1, 8), achBOP)).registerAchievement();
		achBorealForest = (new Achievement(3089, "achBorealForest", 6, -6, new ItemStack(Blocks.saplings.get(), 1, 1), achBOP)).registerAchievement();
		achBrushland = (new Achievement(3090, "achBrushland", 8, -6, new ItemStack(Block.leaves, 1, 2), achBOP)).registerAchievement();
		achCanyon = (new Achievement(3091, "achCanyon", 10, -6, new ItemStack(Blocks.hardDirt.get(), 1, 0), achBOP)).registerAchievement();
		
		achChaparral = (new Achievement(3092, "achChaparral", -10, -4, new ItemStack(Blocks.foliage.get(), 1, 4), achBOP)).registerAchievement();
		achCherryBlossom = (new Achievement(3093, "achCherryBlossom", -8, -4, new ItemStack(Blocks.saplings.get(), 1, 10), achBOP)).registerAchievement();
		achConiferousForest = (new Achievement(3094, "achConiferousForest", -6, -4, new ItemStack(Blocks.saplings.get(), 1, 6), achBOP)).registerAchievement();
		achConiferousForestSnow = (new Achievement(3096, "achConiferousForestSnow", -4, -4, new ItemStack(Blocks.saplings.get(), 1, 6), achBOP)).registerAchievement();
		achCrag = (new Achievement(3097, "achCrag", -2, -4, new ItemStack(Blocks.cragRock.get(), 1, 0), achBOP)).registerAchievement().setSpecial(); 
		achDeadForest = (new Achievement(3098, "achDeadForest", 0, -4, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achDeadForestSnow = (new Achievement(3099, "achDeadForestSnow", 2, -4, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achDeadSwamp = (new Achievement(3100, "achDeadSwamp", 4, -4, new ItemStack(Blocks.logs3.get(), 1, 2), achBOP)).registerAchievement();
		achDeadlands = (new Achievement(3101, "achDeadlands", 6, -4, new ItemStack(Blocks.holyGrass.get(), 1, 1), achBOP)).registerAchievement().setSpecial();
		achDeciduousForest = (new Achievement(3102, "achDeciduousForest", 8, -4, new ItemStack(Blocks.foliage.get(), 1, 7), achBOP)).registerAchievement();
		achDunes = (new Achievement(3103, "achDunes", 10, -4, new ItemStack(Blocks.plants.get(), 1, 3), achBOP)).registerAchievement(); 
		
		achFen = (new Achievement(3104, "achFen", -10, -2, new ItemStack(Blocks.mushrooms.get(), 1, 0), achBOP)).registerAchievement(); 
		achField = (new Achievement(3105, "achField", -8, -2, new ItemStack(Block.tallGrass, 1, 1), achBOP)).registerAchievement();
		achFrostForest = (new Achievement(3106, "achFrostForest", -6, -2, new ItemStack(Block.blockSnow, 1, 0), achBOP)).registerAchievement();
		achFungiForest = (new Achievement(3107, "achFungiForest", -4, -2, new ItemStack(Blocks.mushrooms.get(), 1, 3), achBOP)).registerAchievement().setSpecial();
		achGarden = (new Achievement(3108, "achGarden", -2, -2, new ItemStack(Block.plantRed, 1, 1), achBOP)).registerAchievement().setSpecial();
		achGlacier = (new Achievement(3109, "achGlacier", 0, -2, new ItemStack(Blocks.hardIce.get(), 1, 0), achBOP)).registerAchievement(); 
		achGrassland = (new Achievement(3110, "achGrassland", 2, -2, new ItemStack(Blocks.mushrooms.get(), 1, 1), achBOP)).registerAchievement(); 
		achGrove = (new Achievement(3111, "achGrove", 4, -2, new ItemStack(Blocks.flowers.get(), 1, 0), achBOP)).registerAchievement();
		achHeathland = (new Achievement(3112, "achHeathland", 6, -2, new ItemStack(Blocks.saplings.get(), 1, 14), achBOP)).registerAchievement(); 	
		achHighland = (new Achievement(3113, "achHighland", 8, -2, new ItemStack(Blocks.foliage.get(), 1, 3), achBOP)).registerAchievement();
		achHotSprings = (new Achievement(3114, "achHotSprings", 10, -2, new ItemStack(Fluids.bopBucket.get(), 1, 0), achBOP)).registerAchievement();
		
		achIcyHills = (new Achievement(3115, "achIcyHills", -10, 0, new ItemStack(Block.ice, 1, 0), achBOP)).registerAchievement().setSpecial();  
		achJadeCliffs = (new Achievement(3116, "achJadeCliffs", -8, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 5), achBOP)).registerAchievement(); 
		achLushDesert = (new Achievement(3117, "achLushDesert", -6, 0, new ItemStack(Blocks.plants.get(), 1, 1), achBOP)).registerAchievement(); 
		achLushSwamp = (new Achievement(3118, "achLushSwamp", -4, 0, new ItemStack(Blocks.ivy.get(), 1, 0), achBOP)).registerAchievement(); 
		achMangrove = (new Achievement(3119, "achMangrove", -2, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 1), achBOP)).registerAchievement(); 
		achMapleWoods = (new Achievement(3120, "achMapleWoods", 0, 0, new ItemStack(Blocks.saplings.get(), 1, 11), achBOP)).registerAchievement();
		achMarsh = (new Achievement(3121, "achMarsh", 2, 0, new ItemStack(Item.bucketWater, 1, 0), achBOP)).registerAchievement(); 
		achMeadow = (new Achievement(3122, "achMeadow", 4, 0, new ItemStack(Blocks.flowers.get(), 1, 4), achBOP)).registerAchievement();
		achMesa = (new Achievement(3123, "achMesa", 6, 0, new ItemStack(Blocks.redRock.get(), 1, 0), achBOP)).registerAchievement(); 
		achMoor = (new Achievement(3124, "achMoor", 8, 0, new ItemStack(Items.mudball.get(), 1, 0), achBOP)).registerAchievement(); 
		achMountain = (new Achievement(3125, "achMountain", 10, 0, new ItemStack(Blocks.foliage.get(), 1, 8), achBOP)).registerAchievement();  
		
		achMysticGrove = (new Achievement(3126, "achMysticGrove", -10, 2, new ItemStack(Blocks.flowers.get(), 1, 3), achBOP)).registerAchievement().setSpecial();
		achOasis = (new Achievement(3127, "achOasis", -8, 2, new ItemStack(Blocks.flowers.get(), 1, 11), achBOP)).registerAchievement();
		achOminousWoods = (new Achievement(3128, "achOminousWoods", -6, 2, new ItemStack(Blocks.flowers.get(), 1, 2), achBOP)).registerAchievement().setSpecial();
		achOrchard = (new Achievement(3129, "achOrchard", -4, 2, new ItemStack(Blocks.saplings.get(), 1, 0), achBOP)).registerAchievement();
		achOriginValley = (new Achievement(3130, "achOriginValley", -2, 2, new ItemStack(Blocks.originGrass.get(), 1, 0), achBOP)).registerAchievement().setSpecial();
		achOutback = (new Achievement(3131, "achOutback", -0, 2, new ItemStack(Blocks.hardSand.get(), 1, 0), achBOP)).registerAchievement();
		achPasture = (new Achievement(3132, "achPasture", 2, 2, new ItemStack(Blocks.plants.get(), 1, 6), achBOP)).registerAchievement();
		achPolar = (new Achievement(3133, "achPolar", 4, 2, new ItemStack(Block.ice, 1, 0), achBOP)).registerAchievement();
		achPrairie = (new Achievement(3134, "achPrairie", 6, 2, new ItemStack(Blocks.flowers.get(), 1, 9), achBOP)).registerAchievement();
		achQuagmire = (new Achievement(3135, "achQuagmire", 8, 2, new ItemStack(Blocks.mud.get(), 1, 0), achBOP)).registerAchievement();
		achRainforest = (new Achievement(3136, "achRainforest", 10, 2, new ItemStack(Blocks.flowers.get(), 1, 6), achBOP)).registerAchievement();

		achRedwoodForest = (new Achievement(3137, "achRedwoodForest", -10, 4, new ItemStack(Blocks.colorizedSaplings.get(), 1, 3), achBOP)).registerAchievement(); 
		achSacredSprings = (new Achievement(3138, "achSacredSprings", -8, 4, new ItemStack(Block.waterlily, 1, 0), achBOP)).registerAchievement().setSpecial(); 
		achSavanna = (new Achievement(3139, "achSavanna", -6, 4, new ItemStack(Blocks.colorizedSaplings.get(), 1, 0), achBOP)).registerAchievement();
		achScrubland = (new Achievement(3140, "achScrubland", -4, 4, new ItemStack(Block.tallGrass, 1, 0), achBOP)).registerAchievement();
		achSeasonalForest = (new Achievement(3141, "achSeasonalForest", -2, 4, new ItemStack(Blocks.saplings.get(), 1, 8), achBOP)).registerAchievement();
		achShield = (new Achievement(3142, "achShield", 0, 4, new ItemStack(Blocks.moss.get(), 1, 0), achBOP)).registerAchievement();
		achShrubland = (new Achievement(3143, "achShrubland", 2, 4, new ItemStack(Blocks.foliage.get(), 1, 1), achBOP)).registerAchievement();
		achSludgepit = (new Achievement(3144, "achSludgepit", 4, 4, new ItemStack(Blocks.foliage.get(), 1, 0), achBOP)).registerAchievement();
		achSpruceWoods = (new Achievement(3145, "achSpruceWoods", 6, 4, new ItemStack(Block.sapling, 1, 1), achBOP)).registerAchievement();
		achSteppe = (new Achievement(3146, "achSteppe", 8, 4, new ItemStack(Blocks.flowers.get(), 1, 12), achBOP)).registerAchievement();
		achTemperateRainforest = (new Achievement(3147, "achTemperateRainforest", 10, 4, new ItemStack(Block.tallGrass, 1, 2), achBOP)).registerAchievement();
		
		achThicket = (new Achievement(3148, "achThicket", -10, 6, new ItemStack(Blocks.plants.get(), 1, 5), achBOP)).registerAchievement();
		achTimber = (new Achievement(3149, "achTimber", -8, 6, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achTropicalRainforest = (new Achievement(3150, "achTropicalRainforest", -6, 6, new ItemStack(Blocks.flowers.get(), 1, 5), achBOP)).registerAchievement();
		achTropics = (new Achievement(3151, "achTropics", -4, 6, new ItemStack(Blocks.colorizedSaplings.get(), 1, 2), achBOP)).registerAchievement();
		achTundra = (new Achievement(3152, "achTundra", -2, 6, new ItemStack(Block.gravel, 1, 0), achBOP)).registerAchievement();
		achVolcano = (new Achievement(3153, "achVolcano", 0, 6, new ItemStack(Item.bucketLava, 1, 0), achBOP)).registerAchievement();
		achWasteland = (new Achievement(3154, "achWasteland", 2, 6, new ItemStack(Blocks.driedDirt.get(), 1, 0), achBOP)).registerAchievement().setSpecial();
		achWetland = (new Achievement(3155, "achWetland", 4, 6, new ItemStack(Blocks.plants.get(), 1, 7), achBOP)).registerAchievement();
		achWoodland = (new Achievement(3156, "achWoodland", 6, 6, new ItemStack(Block.sapling, 1, 0), achBOP)).registerAchievement();
		achDesert = (new Achievement(3157, "achDesert", 8, 6, new ItemStack(Block.sand, 1, 0), achBOP)).registerAchievement();
		achExtremeHills = (new Achievement(3158, "achExtremeHills", 10, 6, new ItemStack(Blocks.flowers.get(), 1, 8), achBOP)).registerAchievement();
		
		achForest = (new Achievement(3159, "achForest", -10, 8, new ItemStack(Block.sapling, 1, 0), achBOP)).registerAchievement();
		achIcePlains = (new Achievement(3160, "achIcePlains", -8, 8, new ItemStack(Block.blockSnow, 1, 0), achBOP)).registerAchievement();
		achJungle = (new Achievement(3161, "achJungle", -6, 8, new ItemStack(Block.sapling, 1, 3), achBOP)).registerAchievement();
		achMushroomIsland = (new Achievement(3162, "achMushroomIsland", -4, 8, new ItemStack(Block.mycelium, 1, 0), achBOP)).registerAchievement().setSpecial();
		achPlains = (new Achievement(3163, "achPlains", -2, 8, new ItemStack(Blocks.flowers.get(), 1, 13), achBOP)).registerAchievement();
		achSwampland = (new Achievement(3164, "achSwampland", 0, 8, new ItemStack(Blocks.colorizedSaplings.get(), 1, 4), achBOP)).registerAchievement();
		achTaiga = (new Achievement(3165, "achTaiga", 2, 8, new ItemStack(Block.sapling, 1, 1), achBOP)).registerAchievement();

		pageBOP = new AchievementPage("Biomes O\' Plenty", new Achievement[] {achFlower, achRedRock, achThorn, achAsh, achOrigin, achPromised, achMud, achShroom, achBarley, achMoss, achFlowerP});
		
		biomeFinderAchievementList = new Achievement[] { achBOP, achAlps, achArctic, achBadlands, achBambooForest, achBayou, achBirchForest, achBog, achBorealForest, achBrushland, achCanyon, achChaparral, achCherryBlossom, achConiferousForest, 
				achConiferousForestSnow, achCrag, achDeadForest, achDeadForestSnow, achDeadSwamp, achDeadlands, achDeciduousForest, achDunes, achFen, achField, achFrostForest, achFungiForest, achGarden, achGlacier, achGrassland, achGrove, achHeathland, achHighland, achHotSprings, achIcyHills, 
				achJadeCliffs, achLushDesert, achLushSwamp, achMangrove, achMapleWoods, achMarsh, achMeadow, achMesa, achMoor, achMountain, achMysticGrove, achOasis, achOminousWoods, achOrchard, achOriginValley, achOutback, achPasture, achPolar, achPrairie, achQuagmire, achRainforest, achRedwoodForest, achSacredSprings,  
				achSavanna, achScrubland, achSeasonalForest, achShield, achShrubland, achSludgepit, achSpruceWoods, achSteppe, achTemperateRainforest, achThicket, achTimber, achTropicalRainforest, achTropics, achTundra, achVolcano, achWasteland, achWetland, achWoodland, achDesert, achExtremeHills, achForest, achIcePlains,
				achJungle, achMushroomIsland, achPlains, achSwampland, achTaiga, achAllBiomes, };
		
		pageBiome = new AchievementPage("Biome Finder", biomeFinderAchievementList);
		
		AchievementPage.registerAchievementPage(pageBOP);
		AchievementPage.registerAchievementPage(pageBiome);

		// Add Achievement registration
		addAchievementDesc("achFlower", "Flower Child", "Pick some flowers!");
		addAchievementDesc("achRedRock", "Red Rocky Mountain High", "Dig out some red rocks.");
		addAchievementDesc("achThorn", "Rather Thorny...", "Don\'t get cut!");
		addAchievementDesc("achAsh", "Ashes, Ashes...", "Man, this song is creepy.");
		addAchievementDesc("achOrigin", "Alpha...", "Where it all began.");
		addAchievementDesc("achPromised", "...Omega", "Welcome to the Promised Land!");
		addAchievementDesc("achMud", "Sticky Situation", "I just had these boots cleaned!");
		addAchievementDesc("achShroom", "Trippin\'", "Don\'t try this at home, kids!");
		addAchievementDesc("achBarley", "Fields Of Gold", "Upon the fields of barley.");
		addAchievementDesc("achMoss", "A Rolling Stone Gathers No Moss", "Wait, cubes can't roll...");
		addAchievementDesc("achFlowerP", "Flower Power!", "Groovy, man.");
		
		addAchievementDesc("achBOP", "Biomes O\' Plenty", "Let the fun begin!");
		addAchievementDesc("achAllBiomes", "All Biomes Found!", "You've been to every biome!");
		
		addAchievementDesc("achAlps", "Alps", "Biome Found!");
		addAchievementDesc("achArctic", "Arctic", "Biome Found!");
		addAchievementDesc("achBadlands", "Badlands", "Biome Found!");
		addAchievementDesc("achBambooForest", "Bamboo Forest", "Biome Found!");
		addAchievementDesc("achBayou", "Bayou", "Biome Found!");
		addAchievementDesc("achBirchForest", "Birch Forest", "Biome Found!");
		addAchievementDesc("achBog", "Bog", "Biome Found!");
		addAchievementDesc("achBorealForest", "Boreal Forest", "Biome Found!");
		addAchievementDesc("achBrushland", "Brushland", "Biome Found!");
		addAchievementDesc("achCanyon", "Canyon", "Biome Found!");
		addAchievementDesc("achChaparral", "Chaparral", "Biome Found!");
		addAchievementDesc("achCherryBlossom", "Cherry Blossom Grove", "Biome Found!");
		
		addAchievementDesc("achConiferousForest", "Coniferous Forest", "Biome Found!");    
		addAchievementDesc("achConiferousForestSnow", "Snowy Coniferous Forest", "Biome Found!");
		addAchievementDesc("achCrag", "Crag", "Biome Found!");                
		addAchievementDesc("achDeadForest", "Dead Forest", "Biome Found!");       
		addAchievementDesc("achDeadForestSnow", "Snowy Dead Forest", "Biome Found!");     
		addAchievementDesc("achDeadSwamp", "Dead Swamp", "Biome Found!");          
		addAchievementDesc("achDeadlands", "Deadlands", "Biome Found!");          
		addAchievementDesc("achDeciduousForest", "Deciduous Forest", "Biome Found!");    
		addAchievementDesc("achDunes", "Dunes", "Biome Found!");              
		addAchievementDesc("achFen", "Fen", "Biome Found!");                
		addAchievementDesc("achField", "Field", "Biome Found!");              
		addAchievementDesc("achFrostForest", "Frost Forest", "Biome Found!");        
		addAchievementDesc("achFungiForest", "Fungi Forest", "Biome Found!");        
		addAchievementDesc("achGarden", "Garden", "Biome Found!");             
		addAchievementDesc("achGlacier", "Glacier", "Biome Found!");            
		addAchievementDesc("achGrassland", "Grassland", "Biome Found!");          
		addAchievementDesc("achGrove", "Grove", "Biome Found!");              
		addAchievementDesc("achHeathland", "Heathland", "Biome Found!");         
		addAchievementDesc("achHighland", "Highland", "Biome Found!");           
		addAchievementDesc("achHotSprings", "Hot Springs", "Biome Found!");         
		addAchievementDesc("achIcyHills", "Icy Hills", "Biome Found!");           
		addAchievementDesc("achJadeCliffs", "Jade Cliffs", "Biome Found!");         
		addAchievementDesc("achLushDesert", "Lush Desert", "Biome Found!");         
		addAchievementDesc("achLushSwamp", "Lush Swamp", "Biome Found!");          
		addAchievementDesc("achMangrove", "Mangrove", "Biome Found!");           
		addAchievementDesc("achMapleWoods", "Maple Woods", "Biome Found!");         
		addAchievementDesc("achMarsh", "Marsh", "Biome Found!");              
		addAchievementDesc("achMeadow", "Meadow", "Biome Found!");             
		addAchievementDesc("achMesa", "Mesa", "Biome Found!");               
		addAchievementDesc("achMoor", "Moor", "Biome Found!");               
		addAchievementDesc("achMountain", "Mountain", "Biome Found!");           
		addAchievementDesc("achMysticGrove", "Mystic Grove", "Biome Found!"); 
		
		addAchievementDesc("achOasis", "Oasis", "Biome Found!");
		addAchievementDesc("achOminousWoods", "Ominous Woods", "Biome Found!");
		addAchievementDesc("achOrchard", "Orchard", "Biome Found!");
		addAchievementDesc("achOriginValley", "Origin Valley", "Biome Found!");
		addAchievementDesc("achOutback", "Outback", "Biome Found!");
		addAchievementDesc("achPasture", "Pasture", "Biome Found!");
		addAchievementDesc("achPolar", "Polar", "Biome Found!");
		addAchievementDesc("achPrairie", "Prairie", "Biome Found!");
		addAchievementDesc("achQuagmire", "Quagmire", "Biome Found!");
		addAchievementDesc("achRainforest", "Rainforest", "Biome Found!");
		addAchievementDesc("achRedwoodForest", "Redwood Forest", "Biome Found!");
		addAchievementDesc("achSacredSprings", "Sacred Springs", "Biome Found!"); 
		addAchievementDesc("achSavanna", "Savanna", "Biome Found!");
		addAchievementDesc("achScrubland", "Scrubland", "Biome Found!");
		addAchievementDesc("achSeasonalForest", "Seasonal Forest", "Biome Found!");
		addAchievementDesc("achShield", "Shield", "Biome Found!");
		addAchievementDesc("achShrubland", "Shrubland", "Biome Found!");
		addAchievementDesc("achSludgepit", "Sludgepit", "Biome Found!");
		addAchievementDesc("achSpruceWoods", "Spruce Woods", "Biome Found!");
		addAchievementDesc("achSteppe", "Steppe", "Biome Found!");
		addAchievementDesc("achTemperateRainforest", "Temperate Rainforest", "Biome Found!");
		addAchievementDesc("achThicket", "Thicket", "Biome Found!");
		addAchievementDesc("achTimber", "Timber", "Biome Found!");
		addAchievementDesc("achTropicalRainforest", "Tropical Rainforest", "Biome Found!");
		addAchievementDesc("achTropics", "Tropics", "Biome Found!");
		addAchievementDesc("achTundra", "Tundra", "Biome Found!");
		addAchievementDesc("achVolcano", "Volcano", "Biome Found!");
		addAchievementDesc("achWasteland", "Wasteland", "Biome Found!");
		addAchievementDesc("achWetland", "Wetland", "Biome Found!");
		addAchievementDesc("achWoodland", "Woodland", "Biome Found!");
		
		addAchievementDesc("achDesert", "Desert", "Biome Found!");
		addAchievementDesc("achExtremeHills", "Extreme Hills", "Biome Found!");
		addAchievementDesc("achForest", "Forest", "Biome Found!");
		addAchievementDesc("achIcePlains", "Ice Plains", "Biome Found!");
		addAchievementDesc("achJungle", "Jungle", "Biome Found!");
		addAchievementDesc("achMushroomIsland", "Mushroom Island", "Biome Found!");
		addAchievementDesc("achPlains", "Plains", "Biome Found!");
		addAchievementDesc("achSwampland", "Swampland", "Biome Found!");
		addAchievementDesc("achTaiga", "Taiga", "Biome Found!");
	}

	// Achievement checker
	private static void onItemPickup(EntityPlayer player, ItemStack item)
	{
		if (BOPConfiguration.achievements)
		{
			if (item.itemID == Blocks.flowers.get().blockID || item.itemID == Block.plantRed.blockID || item.itemID == Block.plantYellow.blockID)
			{
				player.addStat(achFlower, 1);
			}
			if (item.itemID == Blocks.redRock.get().blockID)
			{
				player.addStat(achRedRock, 1);
			}
			if (item.itemID == Blocks.plants.get().blockID && item.getItemDamage() == 5)
			{
				player.addStat(achThorn, 1);
			}
			if (item.itemID == Items.miscItems.get().itemID && item.getItemDamage() == 1)
			{
				player.addStat(achAsh, 1);
			}
			if (item.itemID == Blocks.originGrass.get().blockID)
			{
				player.addStat(achOrigin, 1);
			}
			if (item.itemID == Blocks.holyGrass.get().blockID || item.itemID == Blocks.holyStone.get().blockID)
			{
				player.addStat(achPromised, 1);
			}
			if (item.itemID == Items.mudball.get().itemID)
			{
				player.addStat(achMud, 1);
			}
			if (item.itemID == Blocks.mushrooms.get().blockID && item.getItemDamage() == 0)
			{
				player.addStat(achShroom, 1);
			}
			if (item.itemID == Blocks.planks.get().blockID && item.getItemDamage() == 6)
			{
				player.addStat(achBarley, 1);
			}
			if (item.itemID == Blocks.moss.get().blockID)
			{
				player.addStat(achMoss, 1);
			}
		}

		if (item.itemID == Blocks.logs1.get().blockID || item.itemID == Blocks.logs2.get().blockID || (item.itemID == Blocks.logs3.get().blockID && item.getItemDamage() < 3) || item.itemID == Blocks.logs4.get().blockID) {
			player.addStat(AchievementList.mineWood, 1);
		}
	}
	
	@ForgeSubscribe
	public void chunkEntered(EntityEvent.EnteringChunk event)
	{
		if (BOPConfiguration.achievements)
		{
			if (event.entity != null)
			{
				if (event.entity instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer)event.entity;
					World world = player.worldObj;

					int x = MathHelper.floor_double(player.posX);
					int y = MathHelper.floor_double(player.boundingBox.minY);
					int z = MathHelper.floor_double(player.posZ);

					int biomeID = world.getBiomeGenForCoords(x, z).biomeID;
					int i = 0;

					if (world.isRemote)
					{
						while (Minecraft.getMinecraft().statFileWriter.hasAchievementUnlocked(biomeFinderAchievementList[i]))
						{
							if (i + 1 != biomeFinderAchievementList.length - 1)
							{
								i++;
							}
							else
							{						
								player.addStat(AchievementHelper.achAllBiomes, 1);
								break;
							}
						}
					}

					if (biomeID == Biomes.alps.get().biomeID)
					{
						player.addStat(AchievementHelper.achAlps, 1);
					}
					if (biomeID == Biomes.arctic.get().biomeID)
					{
						player.addStat(AchievementHelper.achArctic, 1);
					}
					if (biomeID == Biomes.badlands.get().biomeID)
					{
						player.addStat(AchievementHelper.achBadlands, 1);
					}
					if (biomeID == Biomes.bambooForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achBambooForest, 1);
					}
					if (biomeID == Biomes.bayou.get().biomeID)
					{
						player.addStat(AchievementHelper.achBayou, 1);
					}
					if (biomeID == Biomes.birchForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achBirchForest, 1);
					}
					if (biomeID == Biomes.bog.get().biomeID)
					{
						player.addStat(AchievementHelper.achBog, 1);
					}
					if (biomeID == Biomes.borealForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achBorealForest, 1);
					}
					if (biomeID == Biomes.brushland.get().biomeID)
					{
						player.addStat(AchievementHelper.achBrushland, 1);
					}
					if (biomeID == Biomes.canyon.get().biomeID)
					{
						player.addStat(AchievementHelper.achCanyon, 1);
					}
					
					if (biomeID == Biomes.canyonRavine.get().biomeID)
					{
						player.addStat(AchievementHelper.achCanyon, 1);
					}
					
					if (biomeID == Biomes.chaparral.get().biomeID)
					{
						player.addStat(AchievementHelper.achChaparral, 1);
					}
					if (biomeID == Biomes.cherryBlossomGrove.get().biomeID)
					{
						player.addStat(AchievementHelper.achCherryBlossom, 1);
					}

					if (biomeID == Biomes.coniferousForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achConiferousForest, 1);
					}

					if (biomeID == Biomes.coniferousForestSnow.get().biomeID)
					{
						player.addStat(AchievementHelper.achConiferousForestSnow, 1);
					}

					if (biomeID == Biomes.crag.get().biomeID)
					{
						player.addStat(AchievementHelper.achCrag, 1);
					}

					if (biomeID == Biomes.deadForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achDeadForest, 1);
					}

					if (biomeID == Biomes.deadForestSnow.get().biomeID)
					{
						player.addStat(AchievementHelper.achDeadForestSnow, 1);
					}

					if (biomeID == Biomes.deadSwamp.get().biomeID)
					{
						player.addStat(AchievementHelper.achDeadSwamp, 1);
					}

					if (biomeID == Biomes.deadlands.get().biomeID)
					{
						player.addStat(AchievementHelper.achDeadlands, 1);
					}

					if (biomeID == Biomes.deciduousForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achDeciduousForest, 1);
					}

					if (biomeID == Biomes.dunes.get().biomeID)
					{
						player.addStat(AchievementHelper.achDunes, 1);
					}

					if (biomeID == Biomes.fen.get().biomeID)
					{
						player.addStat(AchievementHelper.achFen, 1);
					}

					if (biomeID == Biomes.field.get().biomeID)
					{
						player.addStat(AchievementHelper.achField, 1);
					}

					if (biomeID == Biomes.field.get().biomeID)
					{
						player.addStat(AchievementHelper.achField, 1);
					}

					if (biomeID == Biomes.frostForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achFrostForest, 1);
					}

					if (biomeID == Biomes.fungiForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achFungiForest, 1);
					}

					if (biomeID == Biomes.garden.get().biomeID)
					{
						player.addStat(AchievementHelper.achGarden, 1);
					}

					if (biomeID == Biomes.glacier.get().biomeID)
					{
						player.addStat(AchievementHelper.achGlacier, 1);
					}

					if (biomeID == Biomes.grassland.get().biomeID)
					{
						player.addStat(AchievementHelper.achGrassland, 1);
					}

					if (biomeID == Biomes.grove.get().biomeID)
					{
						player.addStat(AchievementHelper.achGrove, 1);
					}

					if (biomeID == Biomes.heathland.get().biomeID)
					{
						player.addStat(AchievementHelper.achHeathland, 1);
					}

					if (biomeID == Biomes.highland.get().biomeID)
					{
						player.addStat(AchievementHelper.achHighland, 1);
					}

					if (biomeID == Biomes.hotSprings.get().biomeID)
					{
						player.addStat(AchievementHelper.achHotSprings, 1);
					}

					if (biomeID == Biomes.icyHills.get().biomeID)
					{
						player.addStat(AchievementHelper.achIcyHills, 1);
					}

					if (biomeID == Biomes.jadeCliffs.get().biomeID)
					{
						player.addStat(AchievementHelper.achJadeCliffs, 1);
					}

					if (biomeID == Biomes.lushSwamp.get().biomeID)
					{
						player.addStat(AchievementHelper.achLushSwamp, 1);
					}

					if (biomeID == Biomes.mangrove.get().biomeID)
					{
						player.addStat(AchievementHelper.achMangrove, 1);
					}

					if (biomeID == Biomes.mapleWoods.get().biomeID)
					{
						player.addStat(AchievementHelper.achMapleWoods, 1);
					}

					if (biomeID == Biomes.marsh.get().biomeID)
					{
						player.addStat(AchievementHelper.achMarsh, 1);
					}

					if (biomeID == Biomes.meadow.get().biomeID)
					{
						player.addStat(AchievementHelper.achMeadow, 1);
					}
					
					if (biomeID == Biomes.meadowForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achMeadow, 1);
					}

					if (biomeID == Biomes.mesa.get().biomeID)
					{
						player.addStat(AchievementHelper.achMesa, 1);
					}

					if (biomeID == Biomes.moor.get().biomeID)
					{
						player.addStat(AchievementHelper.achMoor, 1);
					}

					if (biomeID == Biomes.mountain.get().biomeID)
					{
						player.addStat(AchievementHelper.achMountain, 1);
					}

					if (biomeID == Biomes.oasis.get().biomeID)
					{
						player.addStat(AchievementHelper.achOasis, 1);
					}

					if (biomeID == Biomes.ominousWoods.get().biomeID)
					{
						player.addStat(AchievementHelper.achOminousWoods, 1);
					}
					
					if (biomeID == Biomes.ominousWoodsThick.get().biomeID)
					{
						player.addStat(AchievementHelper.achOminousWoods, 1);
					}

					if (biomeID == Biomes.orchard.get().biomeID)
					{
						player.addStat(AchievementHelper.achOrchard, 1);
					}

					if (biomeID == Biomes.originValley.get().biomeID)
					{
						player.addStat(AchievementHelper.achOriginValley, 1);
					}

					if (biomeID == Biomes.outback.get().biomeID)
					{
						player.addStat(AchievementHelper.achOutback, 1);
					}

					if (biomeID == Biomes.pasture.get().biomeID)
					{
						player.addStat(AchievementHelper.achPasture, 1);
					}
					
					if (biomeID == Biomes.pastureMeadow.get().biomeID)
					{
						player.addStat(AchievementHelper.achPasture, 1);
					}
					
					if (biomeID == Biomes.pastureThin.get().biomeID)
					{
						player.addStat(AchievementHelper.achPasture, 1);
					}

					if (biomeID == Biomes.polar.get().biomeID)
					{
						player.addStat(AchievementHelper.achPolar, 1);
					}

					if (biomeID == Biomes.prairie.get().biomeID)
					{
						player.addStat(AchievementHelper.achPrairie, 1);
					}

					if (biomeID == Biomes.quagmire.get().biomeID)
					{
						player.addStat(AchievementHelper.achQuagmire, 1);
					}

					if (biomeID == Biomes.rainforest.get().biomeID)
					{
						player.addStat(AchievementHelper.achRainforest, 1);
					}

					if (biomeID == Biomes.redwoodForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achRedwoodForest, 1);
					}

					if (biomeID == Biomes.sacredSprings.get().biomeID)
					{
						player.addStat(AchievementHelper.achSacredSprings, 1);
					}

					if (biomeID == Biomes.savanna.get().biomeID)
					{
						player.addStat(AchievementHelper.achSavanna, 1);
					}

					if (biomeID == Biomes.scrubland.get().biomeID)
					{
						player.addStat(AchievementHelper.achScrubland, 1);
					}

					if (biomeID == Biomes.seasonalForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achSeasonalForest, 1);
					}

					if (biomeID == Biomes.shield.get().biomeID)
					{
						player.addStat(AchievementHelper.achShield, 1);
					}

					if (biomeID == Biomes.shrubland.get().biomeID)
					{
						player.addStat(AchievementHelper.achShrubland, 1);
					}
					
					if (biomeID == Biomes.shrublandForest.get().biomeID)
					{
						player.addStat(AchievementHelper.achShrubland, 1);
					}

					if (biomeID == Biomes.sludgepit.get().biomeID)
					{
						player.addStat(AchievementHelper.achSludgepit, 1);
					}

					if (biomeID == Biomes.spruceWoods.get().biomeID)
					{
						player.addStat(AchievementHelper.achSpruceWoods, 1);
					}

					if (biomeID == Biomes.steppe.get().biomeID)
					{
						player.addStat(AchievementHelper.achSteppe, 1);
					}

					if (biomeID == Biomes.temperateRainforest.get().biomeID)
					{
						player.addStat(AchievementHelper.achTemperateRainforest, 1);
					}

					if (biomeID == Biomes.thicket.get().biomeID)
					{
						player.addStat(AchievementHelper.achThicket, 1);
					}

					if (biomeID == Biomes.timber.get().biomeID)
					{
						player.addStat(AchievementHelper.achTimber, 1);
					}
					
					if (biomeID == Biomes.timberThin.get().biomeID)
					{
						player.addStat(AchievementHelper.achTimber, 1);
					}

					if (biomeID == Biomes.tropicalRainforest.get().biomeID)
					{
						player.addStat(AchievementHelper.achTropicalRainforest, 1);
					}

					if (biomeID == Biomes.tropics.get().biomeID)
					{
						player.addStat(AchievementHelper.achTropics, 1);
					}

					if (biomeID == Biomes.tundra.get().biomeID)
					{
						player.addStat(AchievementHelper.achTundra, 1);
					}

					if (biomeID == Biomes.volcano.get().biomeID)
					{
						player.addStat(AchievementHelper.achVolcano, 1);
					}

					if (biomeID == Biomes.wasteland.get().biomeID)
					{
						player.addStat(AchievementHelper.achWasteland, 1);
					}

					if (biomeID == Biomes.wetland.get().biomeID)
					{
						player.addStat(AchievementHelper.achWetland, 1);
					}

					if (biomeID == Biomes.woodland.get().biomeID)
					{
						player.addStat(AchievementHelper.achWoodland, 1);
					}

					if (biomeID == Biomes.desertNew.get().biomeID)
					{
						player.addStat(AchievementHelper.achDesert, 1);
					}

					if (biomeID == Biomes.extremeHillsNew.get().biomeID)
					{
						player.addStat(AchievementHelper.achExtremeHills, 1);
					}

					if (biomeID == Biomes.forestNew.get().biomeID)
					{
						player.addStat(AchievementHelper.achForest, 1);
					}

					if (biomeID == BiomeGenBase.icePlains.biomeID)
					{
						player.addStat(AchievementHelper.achIcePlains, 1);
					}

					if (biomeID == Biomes.jungleNew.get().biomeID)
					{
						player.addStat(AchievementHelper.achJungle, 1);
					}

					if (biomeID == BiomeGenBase.mushroomIsland.biomeID)
					{
						player.addStat(AchievementHelper.achMushroomIsland, 1);
					}

					if (biomeID == Biomes.plainsNew.get().biomeID)
					{
						player.addStat(AchievementHelper.achPlains, 1);
					}

					if (biomeID == Biomes.swamplandNew.get().biomeID)
					{
						player.addStat(AchievementHelper.achSwampland, 1);
					}

					if (biomeID == Biomes.taigaNew.get().biomeID)
					{
						player.addStat(AchievementHelper.achTaiga, 1);
					}
				}
			}
		}
	}

	private static void addAchievementDesc(String ach, String name, String desc)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}
}