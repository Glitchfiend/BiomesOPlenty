package biomesoplenty.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.api.Liquids;
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

	public static AchievementPage pageBOP;
	public static AchievementPage pageBiome;

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
		achAllBiomes = (new Achievement(3081, "achAllBiomes", 12, 6, new ItemStack(Blocks.saplings.get(), 1, 6), null)).registerAchievement().setSpecial();
		achAlps = (new Achievement(3082, "achAlps", -8, -6, new ItemStack(Block.stone, 1, 0), achBOP)).registerAchievement();
		achArctic = (new Achievement(3083, "achArctic", -6, -6, new ItemStack(Block.blockSnow, 1, 0), achBOP)).registerAchievement();
		achBadlands = (new Achievement(3084, "achBadlands", -4, -6, new ItemStack(Blocks.hardSand.get(), 1, 0), achBOP)).registerAchievement();
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
		achCrag = (new Achievement(3097, "achCrag", -2, -4, new ItemStack(Blocks.cragRock.get(), 1, 0), achBOP)).registerAchievement(); 
		achDeadForest = (new Achievement(3098, "achDeadForest", 0, -4, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achDeadForestSnow = (new Achievement(3099, "achDeadForestSnow", 2, -4, new ItemStack(Blocks.saplings.get(), 1, 5), achBOP)).registerAchievement();
		achDeadSwamp = (new Achievement(3100, "achDeadSwamp", 4, -4, new ItemStack(Blocks.mud.get(), 1, 0), achBOP)).registerAchievement();
		achDeadlands = (new Achievement(3101, "achDeadlands", 6, -4, new ItemStack(Blocks.holyGrass.get(), 1, 1), achBOP)).registerAchievement();
		achDeciduousForest = (new Achievement(3102, "achDeciduousForest", 8, -4, new ItemStack(Block.leaves, 1, 2), achBOP)).registerAchievement();
		achDunes = (new Achievement(3103, "achDunes", 10, -4, new ItemStack(Block.sand, 1, 0), achBOP)).registerAchievement(); 
		
		achFen = (new Achievement(3104, "achFen", -10, -2, new ItemStack(Blocks.mushrooms.get(), 1, 0), achBOP)).registerAchievement(); 
		achField = (new Achievement(3105, "achField", -8, -2, new ItemStack(Block.tallGrass, 1, 1), achBOP)).registerAchievement();
		achFrostForest = (new Achievement(3106, "achFrostForest", -6, -2, new ItemStack(Block.snow, 1, 0), achBOP)).registerAchievement();
		achFungiForest = (new Achievement(3107, "achFungiForest", -4, -2, new ItemStack(Block.mushroomRed, 1, 0), achBOP)).registerAchievement();
		achGarden = (new Achievement(3108, "achGarden", -2, -2, new ItemStack(Block.plantRed, 1, 1), achBOP)).registerAchievement();
		achGlacier = (new Achievement(3109, "achGlacier", 0, -2, new ItemStack(Blocks.hardIce.get(), 1, 0), achBOP)).registerAchievement(); 
		achGrassland = (new Achievement(3110, "achGrassland", 2, -2, new ItemStack(Block.grass, 1, 0), achBOP)).registerAchievement(); 
		achGrove = (new Achievement(3111, "achGrove", 4, -2, new ItemStack(Block.sapling, 1, 1), achBOP)).registerAchievement();
		achHeathland = (new Achievement(3112, "achHeathland", 6, -2, new ItemStack(Blocks.leaves1.get(), 1, 13), achBOP)).registerAchievement(); 	
		achHighland = (new Achievement(3113, "achHighland", 8, -2, new ItemStack(Blocks.foliage.get(), 1, 3), achBOP)).registerAchievement();
		achHotSprings = (new Achievement(3114, "achHotSprings", 10, -2, new ItemStack(Liquids.bopBucket.get(), 1, 0), achBOP)).registerAchievement();
		
		achIcyHills = (new Achievement(3115, "achIcyHills", -10, 0, new ItemStack(Block.ice, 1, 0), achBOP)).registerAchievement();  
		achJadeCliffs = (new Achievement(3116, "achJadeCliffs", -8, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 5), achBOP)).registerAchievement(); 
		achLushDesert = (new Achievement(3117, "achLushDesert", -6, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 0), achBOP)).registerAchievement(); 
		achLushSwamp = (new Achievement(3118, "achLushSwamp", -4, 0, new ItemStack(Blocks.ivy.get(), 1, 0), achBOP)).registerAchievement(); 
		achMangrove = (new Achievement(3119, "achMangrove", -2, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 1), achBOP)).registerAchievement(); 
		achMapleWoods = (new Achievement(3120, "achMapleWoods", 0, 0, new ItemStack(Blocks.saplings.get(), 1, 11), achBOP)).registerAchievement();
		achMarsh = (new Achievement(3121, "achMarsh", 2, 0, new ItemStack(Item.bucketWater, 1, 0), achBOP)).registerAchievement(); 
		achMeadow = (new Achievement(3122, "achMeadow", 4, 0, new ItemStack(Block.leaves, 1, 1), achBOP)).registerAchievement();
		achMesa = (new Achievement(3123, "achMesa", 6, 0, new ItemStack(Blocks.redRock.get(), 1, 0), achBOP)).registerAchievement(); 
		achMoor = (new Achievement(3124, "achMoor", 8, 0, new ItemStack(Blocks.foliage.get(), 1, 1), achBOP)).registerAchievement(); 
		achMountain = (new Achievement(3125, "achMountain", 10, 0, new ItemStack(Blocks.colorizedSaplings.get(), 1, 5), achBOP)).registerAchievement();  
		
		achMysticGrove = (new Achievement(3126, "achMysticGrove", -10, 2, new ItemStack(Blocks.saplings.get(), 1, 3), achBOP)).registerAchievement();

		pageBOP = new AchievementPage("Biomes O\' Plenty", new Achievement[] {achFlower, achRedRock, achThorn, achAsh, achOrigin, achPromised, achMud, achShroom, achBarley, achMoss, achFlowerP});
		
		pageBiome = new AchievementPage("Biome Finder", new Achievement[] {achBOP, achAllBiomes, achAlps, achArctic, achBadlands, achBambooForest, achBayou, achBirchForest, achBog, achBorealForest, achBrushland, achCanyon, achChaparral, achCherryBlossom, achConiferousForest, 
				achConiferousForestSnow, achCrag, achDeadForest, achDeadForestSnow, achDeadSwamp, achDeadlands, achDeciduousForest, achDunes, achFen, achField, achFrostForest, achFungiForest, achGarden, achGlacier, achGrassland, achGrove, achHeathland, achHighland, achHotSprings, achIcyHills, 
				achJadeCliffs, achLushDesert, achLushSwamp, achMangrove, achMapleWoods, achMarsh, achMeadow, achMesa, achMoor, achMountain, achMysticGrove});
		
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
	}

	// Achievement checker
	private static void onItemPickup(EntityPlayer player, ItemStack item)
	{
		if (BOPConfiguration.achievements == true)
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
			}
		}
	}

	private static void addAchievementDesc(String ach, String name, String desc)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}
}