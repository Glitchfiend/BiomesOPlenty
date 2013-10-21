package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPAchievements
{
	// Achievement declaration
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
	public static Achievement achOvergrownGreens;
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
	public static Achievement achSilkglades;
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
	
	public static Achievement achPromised;
	public static Achievement achSacrifice;

	public static AchievementPage pageBiome;
	
	static Achievement[] biomeFinderAchievementList;

	public static void init()
	{
		achBOP = (new Achievement(3080, "bop.achBOP", -10, -6, new ItemStack(Items.food.get(), 1, 7), null)).registerAchievement().setSpecial();
		achAlps = (new Achievement(3082, "bop.achAlps", -8, -6, new ItemStack(Block.stone, 1, 0), achBOP)).registerAchievement();
		achArctic = (new Achievement(3083, "bop.achArctic", -6, -6, new ItemStack(Block.blockSnow, 1, 0), achBOP)).registerAchievement();
		achBadlands = (new Achievement(3084, "bop.achBadlands", -4, -6, new ItemStack(Block.hardenedClay, 1, 0), achBOP)).registerAchievement();
		achBambooForest = (new Achievement(3085, "bop.achBambooForest", -2, -6, new ItemStack(Blocks.saplings.get(), 1, 2), achBOP)).registerAchievement();
		achBayou = (new Achievement(3086, "bop.achBayou", 0, -6, new ItemStack(Blocks.willow.get(), 1, 0), achBOP)).registerAchievement();
		achBirchForest = (new Achievement(3087, "bop.achBirchForest", 2, -6, new ItemStack(Block.sapling, 1, 2), achBOP)).registerAchievement();
		achBog = (new Achievement(3088, "bop.achBog", 4, -6, new ItemStack(Blocks.mushrooms.get(), 1, 2), achBOP)).registerAchievement();
		achBorealForest = (new Achievement(3089, "bop.achBorealForest", 6, -6, new ItemStack(Blocks.saplings.get(), 1, 1), achBOP)).registerAchievement();
		achBrushland = (new Achievement(3090, "bop.achBrushland", 8, -6, new ItemStack(Block.leaves, 1, 2), achBOP)).registerAchievement();
		achCanyon = (new Achievement(3091, "bop.achCanyon", 10, -6, new ItemStack(Blocks.hardDirt.get(), 1, 0), achBOP)).registerAchievement();
		
		achChaparral = (new Achievement(3092, "bop.achChaparral", -10, -4, new ItemStack(Blocks.foliage.get(), 1, 4), achBOP)).registerAchievement();
		achCherryBlossom = (new Achievement(3093, "bop.achCherryBlossom", -8, -4, new ItemStack(Blocks.saplings.get(), 1, 10), achBOP)).registerAchievement();
		achConiferousForest = (new Achievement(3094, "bop.achConiferousForest", -6, -4, new ItemStack(Blocks.saplings.get(), 1, 6), achBOP)).registerAchievement();
		achConiferousForestSnow = (new Achievement(3096, "bop.achConiferousForestSnow", -4, -4, new ItemStack(Blocks.saplings.get(), 1, 6), achBOP)).registerAchievement();
		achCrag = (new Achievement(3097, "bop.achCrag", -2, -4, new ItemStack(Blocks.cragRock.get(), 1, 0), achBOP)).registerAchievement().setSpecial(); 
		achDeadForest = (new Achievement(3098, "bop.achDeadForest", 0, -4, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achDeadForestSnow = (new Achievement(3099, "bop.achDeadForestSnow", 2, -4, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achDeadSwamp = (new Achievement(3100, "bop.achDeadSwamp", 4, -4, new ItemStack(Blocks.logs3.get(), 1, 2), achBOP)).registerAchievement();
		achDeadlands = (new Achievement(3101, "bop.achDeadlands", 6, -4, new ItemStack(Blocks.holyGrass.get(), 1, 1), achBOP)).registerAchievement().setSpecial();
		achDeciduousForest = (new Achievement(3102, "bop.achDeciduousForest", 8, -4, new ItemStack(Blocks.foliage.get(), 1, 7), achBOP)).registerAchievement();
		achDunes = (new Achievement(3103, "bop.achDunes", 10, -4, new ItemStack(Blocks.plants.get(), 1, 3), achBOP)).registerAchievement(); 
		
		achFen = (new Achievement(3104, "bop.achFen", -10, -2, new ItemStack(Blocks.plants.get(), 1, 8), achBOP)).registerAchievement(); 
		achField = (new Achievement(3105, "bop.achField", -8, -2, new ItemStack(Block.tallGrass, 1, 1), achBOP)).registerAchievement();
		achFrostForest = (new Achievement(3106, "bop.achFrostForest", -6, -2, new ItemStack(Blocks.flowers2.get(), 1, 7), achBOP)).registerAchievement();
		achFungiForest = (new Achievement(3107, "bop.achFungiForest", -4, -2, new ItemStack(Blocks.mushrooms.get(), 1, 3), achBOP)).registerAchievement().setSpecial();
		achGarden = (new Achievement(3108, "bop.achGarden", -2, -2, new ItemStack(Block.plantRed, 1, 1), achBOP)).registerAchievement().setSpecial();
		achGlacier = (new Achievement(3109, "bop.achGlacier", 0, -2, new ItemStack(Blocks.hardIce.get(), 1, 0), achBOP)).registerAchievement(); 
		achGrassland = (new Achievement(3110, "bop.achGrassland", 2, -2, new ItemStack(Block.grass, 1, 0), achBOP)).registerAchievement(); 
		achGrove = (new Achievement(3111, "bop.achGrove", 4, -2, new ItemStack(Blocks.flowers.get(), 1, 0), achBOP)).registerAchievement();
		achHeathland = (new Achievement(3112, "bop.achHeathland", 6, -2, new ItemStack(Blocks.saplings.get(), 1, 14), achBOP)).registerAchievement(); 	
		achHighland = (new Achievement(3113, "bop.achHighland", 8, -2, new ItemStack(Blocks.foliage.get(), 1, 3), achBOP)).registerAchievement();
		achHotSprings = (new Achievement(3114, "bop.achHotSprings", 10, -2, new ItemStack(Fluids.bopBucket.get(), 1, 2), achBOP)).registerAchievement();
		
		achIcyHills = (new Achievement(3115, "bop.achIcyHills", -10, 0, new ItemStack(Block.ice, 1, 0), achBOP)).registerAchievement().setSpecial();  
		achJadeCliffs = (new Achievement(3116, "bop.achJadeCliffs", -8, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 5), achBOP)).registerAchievement().setSpecial(); 
		achLushDesert = (new Achievement(3117, "bop.achLushDesert", -6, 0, new ItemStack(Blocks.plants.get(), 1, 1), achBOP)).registerAchievement(); 
		achLushSwamp = (new Achievement(3118, "bop.achLushSwamp", -4, 0, new ItemStack(Blocks.ivy.get(), 1, 0), achBOP)).registerAchievement(); 
		achMangrove = (new Achievement(3119, "bop.achMangrove", -2, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 1), achBOP)).registerAchievement(); 
		achMapleWoods = (new Achievement(3120, "bop.achMapleWoods", 0, 0, new ItemStack(Blocks.saplings.get(), 1, 11), achBOP)).registerAchievement();
		achMarsh = (new Achievement(3121, "bop.achMarsh", 2, 0, new ItemStack(Blocks.plants.get(), 1, 14), achBOP)).registerAchievement(); 
		achMeadow = (new Achievement(3122, "bop.achMeadow", 4, 0, new ItemStack(Blocks.flowers.get(), 1, 4), achBOP)).registerAchievement();
		achMesa = (new Achievement(3123, "bop.achMesa", 6, 0, new ItemStack(Blocks.redRock.get(), 1, 0), achBOP)).registerAchievement(); 
		achMoor = (new Achievement(3124, "bop.achMoor", 8, 0, new ItemStack(Blocks.flowers.get(), 1, 1), achBOP)).registerAchievement(); 
		achMountain = (new Achievement(3125, "bop.achMountain", 10, 0, new ItemStack(Blocks.foliage.get(), 1, 8), achBOP)).registerAchievement();  
		
		achMysticGrove = (new Achievement(3126, "bop.achMysticGrove", -10, 2, new ItemStack(Blocks.flowers.get(), 1, 3), achBOP)).registerAchievement().setSpecial();
		achOasis = (new Achievement(3127, "bop.achOasis", -8, 2, new ItemStack(Blocks.plants.get(), 1, 12), achBOP)).registerAchievement();
		achOminousWoods = (new Achievement(3128, "bop.achOminousWoods", -6, 2, new ItemStack(Blocks.flowers.get(), 1, 2), achBOP)).registerAchievement().setSpecial();
		achOrchard = (new Achievement(3129, "bop.achOrchard", -4, 2, new ItemStack(Blocks.saplings.get(), 1, 0), achBOP)).registerAchievement();
		achOriginValley = (new Achievement(3130, "bop.achOriginValley", -2, 2, new ItemStack(Blocks.originGrass.get(), 1, 0), achBOP)).registerAchievement().setSpecial();
		achOutback = (new Achievement(3131, "bop.achOutback", -0, 2, new ItemStack(Blocks.hardSand.get(), 1, 0), achBOP)).registerAchievement();
		achOvergrownGreens = (new Achievement(3132, "bop.achOvergrownGreens", 2, 2, new ItemStack(Blocks.foliage.get(), 1, 3), achBOP)).registerAchievement();
		achPasture = (new Achievement(3133, "bop.achPasture", 4, 2, new ItemStack(Blocks.plants.get(), 1, 6), achBOP)).registerAchievement();
		achPolar = (new Achievement(3134, "bop.achPolar", 6, 2, new ItemStack(Block.ice, 1, 0), achBOP)).registerAchievement();
		achPrairie = (new Achievement(3135, "bop.achPrairie", 8, 2, new ItemStack(Blocks.flowers2.get(), 1, 4), achBOP)).registerAchievement();
		achQuagmire = (new Achievement(3136, "bop.achQuagmire", 10, 2, new ItemStack(Items.mudball.get(), 1, 0), achBOP)).registerAchievement();

		achRainforest = (new Achievement(3137, "bop.achRainforest", -10, 4, new ItemStack(Blocks.flowers.get(), 1, 6), achBOP)).registerAchievement();
		achRedwoodForest = (new Achievement(3138, "bop.achRedwoodForest", -8, 4, new ItemStack(Blocks.colorizedSaplings.get(), 1, 3), achBOP)).registerAchievement(); 
		achSacredSprings = (new Achievement(3139, "bop.achSacredSprings", -6, 4, new ItemStack(Blocks.colorizedSaplings.get(), 1, 6), achBOP)).registerAchievement().setSpecial(); 
		achSavanna = (new Achievement(3140, "bop.achSavanna", -4, 4, new ItemStack(Blocks.colorizedSaplings.get(), 1, 0), achBOP)).registerAchievement();
		achScrubland = (new Achievement(3141, "bop.achScrubland", -2, 4, new ItemStack(Block.tallGrass, 1, 0), achBOP)).registerAchievement();
		achSeasonalForest = (new Achievement(3142, "bop.achSeasonalForest", 0, 4, new ItemStack(Blocks.saplings.get(), 1, 8), achBOP)).registerAchievement();
		achShield = (new Achievement(3143, "bop.achShield", 2, 4, new ItemStack(Blocks.moss.get(), 1, 0), achBOP)).registerAchievement();
		achShrubland = (new Achievement(3144, "bop.achShrubland", 4, 4, new ItemStack(Blocks.foliage.get(), 1, 9), achBOP)).registerAchievement();
		achSilkglades = (new Achievement(3145, "bop.achSilkglades", 6, 4, new ItemStack(Block.web, 1, 0), achBOP)).registerAchievement();
		achSludgepit = (new Achievement(3146, "bop.achSludgepit", 8, 4, new ItemStack(Blocks.foliage.get(), 1, 0), achBOP)).registerAchievement().setSpecial();
		achSpruceWoods = (new Achievement(3147, "bop.achSpruceWoods", 10, 4, new ItemStack(Blocks.flowers2.get(), 1, 5), achBOP)).registerAchievement();
		
		achSteppe = (new Achievement(3148, "bop.achSteppe", -10, 6, new ItemStack(Blocks.flowers.get(), 1, 12), achBOP)).registerAchievement();
		achTemperateRainforest = (new Achievement(3149, "bop.achTemperateRainforest", -8, 6, new ItemStack(Block.tallGrass, 1, 2), achBOP)).registerAchievement();
		achThicket = (new Achievement(3150, "bop.achThicket", -6, 6, new ItemStack(Blocks.plants.get(), 1, 5), achBOP)).registerAchievement();
		achTimber = (new Achievement(3151, "bop.achTimber", -4, 6, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achTropicalRainforest = (new Achievement(3152, "bop.achTropicalRainforest", -2, 6, new ItemStack(Blocks.flowers.get(), 1, 5), achBOP)).registerAchievement();
		achTropics = (new Achievement(3153, "bop.achTropics", 0, 6, new ItemStack(Blocks.colorizedSaplings.get(), 1, 2), achBOP)).registerAchievement();
		achTundra = (new Achievement(3154, "bop.achTundra", 2, 6, new ItemStack(Block.gravel, 1, 0), achBOP)).registerAchievement();
		achVolcano = (new Achievement(3155, "bop.achVolcano", 4, 6, new ItemStack(Blocks.ash.get(), 1, 0), achBOP)).registerAchievement();
		achWasteland = (new Achievement(3156, "bop.achWasteland", 6, 6, new ItemStack(Blocks.driedDirt.get(), 1, 0), achBOP)).registerAchievement().setSpecial();
		achWetland = (new Achievement(3157, "bop.achWetland", 8, 6, new ItemStack(Blocks.plants.get(), 1, 7), achBOP)).registerAchievement();
		achWoodland = (new Achievement(3158, "bop.achWoodland", 10, 6, new ItemStack(Blocks.mushrooms.get(), 1, 0), achBOP)).registerAchievement();
		
		achDesert = (new Achievement(3159, "bop.achDesert", -10, 8, new ItemStack(Block.sand, 1, 0), achBOP)).registerAchievement();
		achExtremeHills = (new Achievement(3160, "bop.achExtremeHills", -8, 8, new ItemStack(Blocks.flowers.get(), 1, 8), achBOP)).registerAchievement();
		achForest = (new Achievement(3161, "bop.achForest", -6, 8, new ItemStack(Block.sapling, 1, 0), achBOP)).registerAchievement();
		achIcePlains = (new Achievement(3162, "bop.achIcePlains", -4, 8, new ItemStack(Block.blockSnow, 1, 0), achBOP)).registerAchievement();
		achJungle = (new Achievement(3163, "bop.achJungle", -2, 8, new ItemStack(Block.sapling, 1, 3), achBOP)).registerAchievement();
		achMushroomIsland = (new Achievement(3164, "bop.achMushroomIsland", 0, 8, new ItemStack(Block.mushroomRed, 1, 0), achBOP)).registerAchievement().setSpecial();
		achPlains = (new Achievement(3165, "bop.achPlains",2, 8, new ItemStack(Blocks.flowers.get(), 1, 13), achBOP)).registerAchievement();
		achSwampland = (new Achievement(3166, "bop.achSwampland", 4, 8, new ItemStack(Blocks.colorizedSaplings.get(), 1, 4), achBOP)).registerAchievement();
		achTaiga = (new Achievement(3167, "bop.achTaiga", 6, 8, new ItemStack(Block.sapling, 1, 1), achBOP)).registerAchievement();
		achAllBiomes = (new Achievement(3081, "bop.achAllBiomes", 8, 8, new ItemStack(Items.food.get(), 1, 7), null)).registerAchievement().setSpecial();
		achPromised = (new Achievement(3168, "bop.achPromised", 10, 8, new ItemStack(Blocks.holyGrass.get(), 1, 0), null)).registerAchievement().setSpecial();
		achSacrifice = (new Achievement(3169, "bop.achSacrifice", 0, 10, new ItemStack(Items.miscItems.get(), 1, 16), null)).registerAchievement().setSpecial();
		
		biomeFinderAchievementList = new Achievement[] { achBOP, achAlps, achArctic, achBadlands, achBambooForest, achBayou, achBirchForest, achBog, achBorealForest, achBrushland, achCanyon, achChaparral, achCherryBlossom, achConiferousForest, 
				achConiferousForestSnow, achCrag, achDeadForest, achDeadForestSnow, achDeadSwamp, achDeadlands, achDeciduousForest, achDunes, achFen, achField, achFrostForest, achFungiForest, achGarden, achGlacier, achGrassland, achGrove, achHeathland, achHighland, achHotSprings, achIcyHills, 
				achJadeCliffs, achLushDesert, achLushSwamp, achMangrove, achMapleWoods, achMarsh, achMeadow, achMesa, achMoor, achMountain, achMysticGrove, achOasis, achOminousWoods, achOrchard, achOriginValley, achOutback, achOvergrownGreens, achPasture, achPolar, achPrairie, achQuagmire, achRainforest, achRedwoodForest, achSacredSprings,  
				achSavanna, achScrubland, achSeasonalForest, achShield, achShrubland, achSilkglades, achSludgepit, achSpruceWoods, achSteppe, achTemperateRainforest, achThicket, achTimber, achTropicalRainforest, achTropics, achTundra, achVolcano, achWasteland, achWetland, achWoodland, achDesert, achExtremeHills, achForest, achIcePlains,
				achJungle, achMushroomIsland, achPlains, achSwampland, achTaiga, achAllBiomes, achPromised, achSacrifice};
		
		pageBiome = new AchievementPage("Biomes O\' Plenty", biomeFinderAchievementList);
		
		AchievementPage.registerAchievementPage(pageBiome);
	}
	
	@ForgeSubscribe
	public void chunkEntered(EntityEvent.EnteringChunk event)
	{
		if (BOPConfigurationMisc.achievements)
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
								player.addStat(BOPAchievements.achAllBiomes, 1);
								break;
							}
						}
					}

					if (biomeID == Biomes.alps.get().biomeID)
					{
						player.addStat(BOPAchievements.achAlps, 1);
					}
					
					if (biomeID == Biomes.alpsForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achAlps, 1);
					}
					
					if (biomeID == Biomes.alpsBase.get().biomeID)
					{
						player.addStat(BOPAchievements.achAlps, 1);
					}
					
					if (biomeID == Biomes.arctic.get().biomeID)
					{
						player.addStat(BOPAchievements.achArctic, 1);
					}
					
					if (biomeID == Biomes.badlands.get().biomeID)
					{
						player.addStat(BOPAchievements.achBadlands, 1);
					}
					
					if (biomeID == Biomes.bambooForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achBambooForest, 1);
					}
					
					if (biomeID == Biomes.bayou.get().biomeID)
					{
						player.addStat(BOPAchievements.achBayou, 1);
					}
					
					if (biomeID == Biomes.birchForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achBirchForest, 1);
					}
					
					if (biomeID == Biomes.bog.get().biomeID)
					{
						player.addStat(BOPAchievements.achBog, 1);
					}
					
					if (biomeID == Biomes.borealForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achBorealForest, 1);
					}
					
					if (biomeID == Biomes.brushland.get().biomeID)
					{
						player.addStat(BOPAchievements.achBrushland, 1);
					}
					
					if (biomeID == Biomes.canyon.get().biomeID)
					{
						player.addStat(BOPAchievements.achCanyon, 1);
					}
					
					if (biomeID == Biomes.canyonRavine.get().biomeID)
					{
						player.addStat(BOPAchievements.achCanyon, 1);
					}
					
					if (biomeID == Biomes.chaparral.get().biomeID)
					{
						player.addStat(BOPAchievements.achChaparral, 1);
					}
					
					if (biomeID == Biomes.cherryBlossomGrove.get().biomeID)
					{
						player.addStat(BOPAchievements.achCherryBlossom, 1);
					}

					if (biomeID == Biomes.coniferousForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achConiferousForest, 1);
					}

					if (biomeID == Biomes.coniferousForestSnow.get().biomeID)
					{
						player.addStat(BOPAchievements.achConiferousForestSnow, 1);
					}

					if (biomeID == Biomes.crag.get().biomeID)
					{
						player.addStat(BOPAchievements.achCrag, 1);
					}

					if (biomeID == Biomes.deadForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achDeadForest, 1);
					}

					if (biomeID == Biomes.deadForestSnow.get().biomeID)
					{
						player.addStat(BOPAchievements.achDeadForestSnow, 1);
					}

					if (biomeID == Biomes.deadSwamp.get().biomeID)
					{
						player.addStat(BOPAchievements.achDeadSwamp, 1);
					}

					if (biomeID == Biomes.deadlands.get().biomeID)
					{
						player.addStat(BOPAchievements.achDeadlands, 1);
					}

					if (biomeID == Biomes.deciduousForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achDeciduousForest, 1);
					}

					if (biomeID == Biomes.dunes.get().biomeID)
					{
						player.addStat(BOPAchievements.achDunes, 1);
					}

					if (biomeID == Biomes.fen.get().biomeID)
					{
						player.addStat(BOPAchievements.achFen, 1);
					}

					if (biomeID == Biomes.field.get().biomeID)
					{
						player.addStat(BOPAchievements.achField, 1);
					}

					if (biomeID == Biomes.frostForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achFrostForest, 1);
					}

					if (biomeID == Biomes.fungiForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achFungiForest, 1);
					}

					if (biomeID == Biomes.garden.get().biomeID)
					{
						player.addStat(BOPAchievements.achGarden, 1);
					}

					if (biomeID == Biomes.glacier.get().biomeID)
					{
						player.addStat(BOPAchievements.achGlacier, 1);
					}

					if (biomeID == Biomes.grassland.get().biomeID)
					{
						player.addStat(BOPAchievements.achGrassland, 1);
					}

					if (biomeID == Biomes.grove.get().biomeID)
					{
						player.addStat(BOPAchievements.achGrove, 1);
					}

					if (biomeID == Biomes.heathland.get().biomeID)
					{
						player.addStat(BOPAchievements.achHeathland, 1);
					}

					if (biomeID == Biomes.highland.get().biomeID)
					{
						player.addStat(BOPAchievements.achHighland, 1);
					}

					if (biomeID == Biomes.hotSprings.get().biomeID)
					{
						player.addStat(BOPAchievements.achHotSprings, 1);
					}

					if (biomeID == Biomes.icyHills.get().biomeID)
					{
						player.addStat(BOPAchievements.achIcyHills, 1);
					}

					if (biomeID == Biomes.jadeCliffs.get().biomeID)
					{
						player.addStat(BOPAchievements.achJadeCliffs, 1);
					}
					
					if (biomeID == Biomes.lushDesert.get().biomeID)
					{
						player.addStat(BOPAchievements.achLushDesert, 1);
					}

					if (biomeID == Biomes.lushSwamp.get().biomeID)
					{
						player.addStat(BOPAchievements.achLushSwamp, 1);
					}

					if (biomeID == Biomes.mangrove.get().biomeID)
					{
						player.addStat(BOPAchievements.achMangrove, 1);
					}

					if (biomeID == Biomes.mapleWoods.get().biomeID)
					{
						player.addStat(BOPAchievements.achMapleWoods, 1);
					}

					if (biomeID == Biomes.marsh.get().biomeID)
					{
						player.addStat(BOPAchievements.achMarsh, 1);
					}

					if (biomeID == Biomes.meadow.get().biomeID)
					{
						player.addStat(BOPAchievements.achMeadow, 1);
					}
					
					if (biomeID == Biomes.meadowForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achMeadow, 1);
					}

					if (biomeID == Biomes.mesa.get().biomeID)
					{
						player.addStat(BOPAchievements.achMesa, 1);
					}

					if (biomeID == Biomes.moor.get().biomeID)
					{
						player.addStat(BOPAchievements.achMoor, 1);
					}

					if (biomeID == Biomes.mountain.get().biomeID)
					{
						player.addStat(BOPAchievements.achMountain, 1);
					}
					
					if (biomeID == Biomes.mysticGrove.get().biomeID)
					{
						player.addStat(BOPAchievements.achMysticGrove, 1);
					}

					if (biomeID == Biomes.oasis.get().biomeID)
					{
						player.addStat(BOPAchievements.achOasis, 1);
					}

					if (biomeID == Biomes.ominousWoods.get().biomeID)
					{
						player.addStat(BOPAchievements.achOminousWoods, 1);
					}
					
					if (biomeID == Biomes.ominousWoodsThick.get().biomeID)
					{
						player.addStat(BOPAchievements.achOminousWoods, 1);
					}

					if (biomeID == Biomes.orchard.get().biomeID)
					{
						player.addStat(BOPAchievements.achOrchard, 1);
					}

					if (biomeID == Biomes.originValley.get().biomeID)
					{
						player.addStat(BOPAchievements.achOriginValley, 1);
					}

					if (biomeID == Biomes.outback.get().biomeID)
					{
						player.addStat(BOPAchievements.achOutback, 1);
					}
					
					if (biomeID == Biomes.overgrownGreens.get().biomeID)
					{
						player.addStat(BOPAchievements.achOvergrownGreens, 1);
					}

					if (biomeID == Biomes.pasture.get().biomeID)
					{
						player.addStat(BOPAchievements.achPasture, 1);
					}
					
					if (biomeID == Biomes.pastureMeadow.get().biomeID)
					{
						player.addStat(BOPAchievements.achPasture, 1);
					}
					
					if (biomeID == Biomes.pastureThin.get().biomeID)
					{
						player.addStat(BOPAchievements.achPasture, 1);
					}

					if (biomeID == Biomes.polar.get().biomeID)
					{
						player.addStat(BOPAchievements.achPolar, 1);
					}

					if (biomeID == Biomes.prairie.get().biomeID)
					{
						player.addStat(BOPAchievements.achPrairie, 1);
					}

					if (biomeID == Biomes.quagmire.get().biomeID)
					{
						player.addStat(BOPAchievements.achQuagmire, 1);
					}

					if (biomeID == Biomes.rainforest.get().biomeID)
					{
						player.addStat(BOPAchievements.achRainforest, 1);
					}

					if (biomeID == Biomes.redwoodForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achRedwoodForest, 1);
					}

					if (biomeID == Biomes.sacredSprings.get().biomeID)
					{
						player.addStat(BOPAchievements.achSacredSprings, 1);
					}

					if (biomeID == Biomes.savanna.get().biomeID)
					{
						player.addStat(BOPAchievements.achSavanna, 1);
					}

					if (biomeID == Biomes.scrubland.get().biomeID)
					{
						player.addStat(BOPAchievements.achScrubland, 1);
					}

					if (biomeID == Biomes.seasonalForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achSeasonalForest, 1);
					}
					
					if (biomeID == Biomes.seasonalSpruceForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achSeasonalForest, 1);
					}

					if (biomeID == Biomes.shield.get().biomeID)
					{
						player.addStat(BOPAchievements.achShield, 1);
					}

					if (biomeID == Biomes.shrubland.get().biomeID)
					{
						player.addStat(BOPAchievements.achShrubland, 1);
					}
					
					if (biomeID == Biomes.shrublandForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achShrubland, 1);
					}
					
					if (biomeID == Biomes.silkglades.get().biomeID)
					{
						player.addStat(BOPAchievements.achSilkglades, 1);
					}

					if (biomeID == Biomes.sludgepit.get().biomeID)
					{
						player.addStat(BOPAchievements.achSludgepit, 1);
					}

					if (biomeID == Biomes.spruceWoods.get().biomeID)
					{
						player.addStat(BOPAchievements.achSpruceWoods, 1);
					}

					if (biomeID == Biomes.steppe.get().biomeID)
					{
						player.addStat(BOPAchievements.achSteppe, 1);
					}

					if (biomeID == Biomes.temperateRainforest.get().biomeID)
					{
						player.addStat(BOPAchievements.achTemperateRainforest, 1);
					}

					if (biomeID == Biomes.thicket.get().biomeID)
					{
						player.addStat(BOPAchievements.achThicket, 1);
					}

					if (biomeID == Biomes.timber.get().biomeID)
					{
						player.addStat(BOPAchievements.achTimber, 1);
					}
					
					if (biomeID == Biomes.timberThin.get().biomeID)
					{
						player.addStat(BOPAchievements.achTimber, 1);
					}

					if (biomeID == Biomes.tropicalRainforest.get().biomeID)
					{
						player.addStat(BOPAchievements.achTropicalRainforest, 1);
					}

					if (biomeID == Biomes.tropics.get().biomeID)
					{
						player.addStat(BOPAchievements.achTropics, 1);
					}

					if (biomeID == Biomes.tundra.get().biomeID)
					{
						player.addStat(BOPAchievements.achTundra, 1);
					}

					if (biomeID == Biomes.volcano.get().biomeID)
					{
						player.addStat(BOPAchievements.achVolcano, 1);
					}

					if (biomeID == Biomes.wasteland.get().biomeID)
					{
						player.addStat(BOPAchievements.achWasteland, 1);
					}

					if (biomeID == Biomes.wetland.get().biomeID)
					{
						player.addStat(BOPAchievements.achWetland, 1);
					}

					if (biomeID == Biomes.woodland.get().biomeID)
					{
						player.addStat(BOPAchievements.achWoodland, 1);
					}

					if (biomeID == Biomes.desertNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achDesert, 1);
					}
					
					if (biomeID == BiomeGenBase.desert.biomeID)
					{
						player.addStat(BOPAchievements.achDesert, 1);
					}
					
					if (biomeID == BiomeGenBase.desertHills.biomeID)
					{
						player.addStat(BOPAchievements.achDesert, 1);
					}

					if (biomeID == Biomes.extremeHillsNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achExtremeHills, 1);
					}
					
					if (biomeID == BiomeGenBase.extremeHills.biomeID)
					{
						player.addStat(BOPAchievements.achExtremeHills, 1);
					}
					
					if (biomeID == BiomeGenBase.extremeHillsEdge.biomeID)
					{
						player.addStat(BOPAchievements.achExtremeHills, 1);
					}

					if (biomeID == Biomes.forestNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achForest, 1);
					}
					
					if (biomeID == Biomes.forestHillsNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achForest, 1);
					}
					
					if (biomeID == BiomeGenBase.forest.biomeID)
					{
						player.addStat(BOPAchievements.achForest, 1);
					}
					
					if (biomeID == BiomeGenBase.forestHills.biomeID)
					{
						player.addStat(BOPAchievements.achForest, 1);
					}

					if (biomeID == BiomeGenBase.icePlains.biomeID)
					{
						player.addStat(BOPAchievements.achIcePlains, 1);
					}
					
					if (biomeID == BiomeGenBase.iceMountains.biomeID)
					{
						player.addStat(BOPAchievements.achIcePlains, 1);
					}

					if (biomeID == Biomes.jungleNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achJungle, 1);
					}
					
					if (biomeID == Biomes.jungleHillsNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achJungle, 1);
					}
					
					if (biomeID == BiomeGenBase.jungle.biomeID)
					{
						player.addStat(BOPAchievements.achJungle, 1);
					}
					
					if (biomeID == BiomeGenBase.jungleHills.biomeID)
					{
						player.addStat(BOPAchievements.achJungle, 1);
					}

					if (biomeID == BiomeGenBase.mushroomIsland.biomeID)
					{
						player.addStat(BOPAchievements.achMushroomIsland, 1);
					}

					if (biomeID == Biomes.plainsNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achPlains, 1);
					}

					if (biomeID == Biomes.swamplandNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achSwampland, 1);
					}

					if (biomeID == Biomes.taigaNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achTaiga, 1);
					}
					
					if (biomeID == Biomes.taigaHillsNew.get().biomeID)
					{
						player.addStat(BOPAchievements.achTaiga, 1);
					}
					
					if (biomeID == BiomeGenBase.taigaHills.biomeID)
					{
						player.addStat(BOPAchievements.achTaiga, 1);
					}
					
					if (biomeID == Biomes.promisedLandForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achPromised, 1);
					}
					
					if (biomeID == Biomes.promisedLandPlains.get().biomeID)
					{
						player.addStat(BOPAchievements.achPromised, 1);
					}
					
					if (biomeID == Biomes.promisedLandSwamp.get().biomeID)
					{
						player.addStat(BOPAchievements.achPromised, 1);
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
