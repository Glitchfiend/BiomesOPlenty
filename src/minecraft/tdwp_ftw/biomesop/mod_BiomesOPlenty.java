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
import tdwp_ftw.biomesop.declarations.BOPBiomes;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.declarations.BOPConfiguration;
import tdwp_ftw.biomesop.declarations.BOPItems;
import tdwp_ftw.biomesop.helpers.*;
import tdwp_ftw.biomesop.items.*;
import tdwp_ftw.biomesop.items.projectiles.EntityMudball;
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
		boolean isClient = proxy instanceof ClientProxy;
		
		String[] soundFiles = { "bopdisc.ogg", "bopdiscmud.ogg"};

		if (isClient)
		{
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
		}
		
		BOPConfiguration.init(event.getSuggestedConfigurationFile());

		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");
		
		BOPBlocks.init();
		
		BOPItems.init();
		
		BOPBlocks.dependantinit();
		
		BOPBiomes.init();
	}

	@Init
	public void load(FMLInitializationEvent event)
	{

		// Achievement declaration
		if (BOPConfiguration.achievements == true)
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

		if (BOPConfiguration.achievements == true)
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

		EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", BOPConfiguration.jungleSpiderID, this, 80, 3, true);
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.JungleSpider.name", "en_US", "Jungle Spider");
		EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, BOPBiomes.jungleNew, BOPBiomes.tropicalRainforest, BOPBiomes.oasis, BOPBiomes.tropics);
		registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);

		EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", BOPConfiguration.rosesterID, this, 80, 3, true);
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.Rosester.name", "en_US", "Rosester");
		EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, BOPBiomes.garden);    
		registerEntityEgg(EntityRosester.class, 14831439, 16756224);
		
		EntityRegistry.registerModEntity(EntityMudball.class, "MudBall", EntityRegistry.findGlobalUniqueEntityId(), this, 80, 3, true); 

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

	public static CreativeTabs tabBiomesOPlenty;

	public static ChestGenHooks dungeon;
	public static ChestGenHooks mineshaft;
	public static ChestGenHooks strongholdCorridor;
	public static ChestGenHooks strongholdCrossing;
	public static ChestGenHooks village;

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
		if (BOPConfiguration.achievements == true)
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