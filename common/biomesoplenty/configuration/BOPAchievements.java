package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
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
	public static Achievement achFlower;
	
	public static Achievement achFlowerBand;
	public static Achievement achDartBlower;
	public static Achievement achScythe;
	public static Achievement achEnderporter;
	
	public static Achievement achBerry;
	public static Achievement achMoss;
	public static Achievement achThorn;
	public static Achievement achCoral;
	
	public static Achievement achHoney;
	public static Achievement achWitherWart;
	public static Achievement achGrave;
	public static Achievement achPhantom;
	
	public static Achievement achPromised;
	public static Achievement achCelestial;
	public static Achievement achBird;

	public static AchievementPage pageBiome;
	
	static Achievement[] biomesOPlentyAchievementList;

	public static void init()
	{
		achFlower = (new Achievement(3080, "bop.achFlower", 0, 0, new ItemStack(Block.plantRed, 1, 0), null)).registerAchievement();
		
		achFlowerBand = (new Achievement(3081, "bop.achFlowerBand", 2, -1, new ItemStack(Items.flowerBand.get(), 1, 0), achFlower)).registerAchievement();
		achDartBlower = (new Achievement(3082, "bop.achDartBlower", 4, -2, new ItemStack(Items.dartBlower.get(), 1, 0), achFlowerBand)).registerAchievement();
		achScythe = (new Achievement(3083, "bop.achScythe", 3, 1, new ItemStack(Items.scytheIron.get(), 1, 0), achDartBlower)).registerAchievement();
		achEnderporter = (new Achievement(3084, "bop.achEnderporter", 5, 3, new ItemStack(Items.enderporter.get(), 1, 0), achScythe)).registerAchievement().setSpecial();
		
		achBerry = (new Achievement(3085, "bop.achBerry", -2, 0, new ItemStack(Items.food.get(), 1, 0), achFlower)).registerAchievement();
		achMoss = (new Achievement(3086, "bop.achMoss", -4, 1, new ItemStack(Blocks.moss.get(), 1, 0), achBerry)).registerAchievement();
		achThorn = (new Achievement(3087, "bop.achThorn", -5, -1, new ItemStack(Blocks.plants.get(), 1, 5), achMoss)).registerAchievement();
		achCoral = (new Achievement(3088, "bop.achCoral", -3, -2, new ItemStack(Blocks.coral.get(), 1, 4), achThorn)).registerAchievement();
		
		achHoney = (new Achievement(3089, "bop.achHoney", -1, 2, new ItemStack(Fluids.bopBucket.get(), 1, 3), achFlower)).registerAchievement();
		achWitherWart = (new Achievement(3090, "bop.achWitherWart", 1, 3, new ItemStack(Blocks.plants.get(), 1, 13), achHoney)).registerAchievement();
		achGrave = (new Achievement(3091, "bop.achGrave", -2, 4, new ItemStack(Blocks.grave.get(), 1, 0), achWitherWart)).registerAchievement();
		achPhantom = (new Achievement(3092, "bop.achPhantom", 0, 6, new ItemStack(Items.miscItems.get(), 1, 16), achGrave)).registerAchievement().setSpecial();
		
		achPromised = (new Achievement(3093, "bop.achPromised", 1, -3, new ItemStack(Blocks.holyGrass.get(), 1, 0), achFlower)).registerAchievement().setSpecial();
		achCelestial = (new Achievement(3094, "bop.achCelestial", -1, -4, new ItemStack(Items.miscItems.get(), 1, 4), achPromised)).registerAchievement();
		achBird = (new Achievement(3095, "bop.achBird", 0, -6, new ItemStack(Item.feather, 1, 0), achCelestial)).registerAchievement();
		
		biomesOPlentyAchievementList = new Achievement[] { achFlower, achFlowerBand, achDartBlower, achScythe, achEnderporter, achBerry, achMoss, achThorn, achCoral,
				achHoney, achWitherWart, achGrave, achPhantom, achPromised, achCelestial, achBird};
		
		pageBiome = new AchievementPage("Biomes O\' Plenty", biomesOPlentyAchievementList);
		
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
