package biomesoplenty.core;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.api.BOPItems;
import biomesoplenty.configuration.BOPConfigurationMisc;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

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
	public static Achievement achAmbrosia;
	
	public static Achievement achAllBOP;

	public static AchievementPage pageBiome;
	
	static Achievement[] biomesOPlentyAchievementList;

	public static void init()
	{
		achFlower = (new Achievement(3080, "bop.achFlower", 0, 0, new ItemStack(Block.plantRed, 1, 0), null)).registerAchievement();
		
		achFlowerBand = (new Achievement(3081, "bop.achFlowerBand", 2, -1, new ItemStack(BOPItems.flowerBand.get(), 1, 0), achFlower)).registerAchievement();
		achDartBlower = (new Achievement(3082, "bop.achDartBlower", 4, -2, new ItemStack(BOPItems.dartBlower.get(), 1, 0), achFlowerBand)).registerAchievement();
		achScythe = (new Achievement(3083, "bop.achScythe", 3, 1, new ItemStack(BOPItems.scytheIron.get(), 1, 0), achDartBlower)).registerAchievement();
		achEnderporter = (new Achievement(3084, "bop.achEnderporter", 5, 3, new ItemStack(BOPItems.enderporter.get(), 1, 0), achScythe)).registerAchievement().setSpecial();
		
		achBerry = (new Achievement(3085, "bop.achBerry", -2, 0, new ItemStack(BOPItems.food.get(), 1, 0), achFlower)).registerAchievement();
		achMoss = (new Achievement(3086, "bop.achMoss", -4, 1, new ItemStack(BOPBlocks.moss.get(), 1, 0), achBerry)).registerAchievement();
		achThorn = (new Achievement(3087, "bop.achThorn", -5, -1, new ItemStack(BOPBlocks.plants.get(), 1, 5), achMoss)).registerAchievement();
		achCoral = (new Achievement(3088, "bop.achCoral", -3, -2, new ItemStack(BOPBlocks.coral.get(), 1, 4), achThorn)).registerAchievement();
		
		achHoney = (new Achievement(3089, "bop.achHoney", -1, 2, new ItemStack(BOPItems.food.get(), 1, 9), achFlower)).registerAchievement();
		achWitherWart = (new Achievement(3090, "bop.achWitherWart", 1, 3, new ItemStack(BOPBlocks.plants.get(), 1, 13), achHoney)).registerAchievement();
		achGrave = (new Achievement(3091, "bop.achGrave", -2, 4, new ItemStack(BOPBlocks.grave.get(), 1, 0), achWitherWart)).registerAchievement();
		achPhantom = (new Achievement(3092, "bop.achPhantom", 0, 6, new ItemStack(BOPItems.miscItems.get(), 1, 10), achGrave)).registerAchievement().setSpecial();
		
		achPromised = (new Achievement(3093, "bop.achPromised", 1, -3, new ItemStack(BOPBlocks.holyGrass.get(), 1, 0), achFlower)).registerAchievement().setSpecial();
		achCelestial = (new Achievement(3094, "bop.achCelestial", -1, -4, new ItemStack(BOPItems.miscItems.get(), 1, 4), achPromised)).registerAchievement();
		achAmbrosia = (new Achievement(3095, "bop.achAmbrosia", 0, -6, new ItemStack(BOPItems.food.get(), 1, 10), achCelestial)).registerAchievement();
		
		achAllBOP = (new Achievement(3096, "bop.achAllBOP", 0, -8, new ItemStack(BOPItems.food.get(), 1, 7), null)).registerAchievement().setSpecial();
		
		biomesOPlentyAchievementList = new Achievement[] { achFlower, achFlowerBand, achDartBlower, achScythe, achEnderporter, achBerry, achMoss, achThorn, achCoral,
				achHoney, achWitherWart, achGrave, achPhantom, achPromised, achCelestial, achAmbrosia, achAllBOP};
		
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

					int i = 0;
					int biomeID = world.getBiomeGenForCoords(x, z).biomeID;
					
					if (FMLCommonHandler.instance().getSide().isClient())
					{
					    if (Minecraft.getMinecraft().statFileWriter.hasAchievementUnlocked(achAmbrosia) && Minecraft.getMinecraft().statFileWriter.hasAchievementUnlocked(achPhantom) && Minecraft.getMinecraft().statFileWriter.hasAchievementUnlocked(achCoral) && Minecraft.getMinecraft().statFileWriter.hasAchievementUnlocked(achEnderporter))
					    {
					        if (!Minecraft.getMinecraft().statFileWriter.hasAchievementUnlocked(achAllBOP))
					        {
					            player.addStat(achAllBOP, 1);
					        }
					    }
					}
					
					if (biomeID == Biomes.promisedLandForest.get().biomeID)
					{
						player.addStat(BOPAchievements.achPromised, 1);
					}
					
					if (biomeID == Biomes.promisedLandPlains.get().biomeID)
					{
						player.addStat(BOPAchievements.achPromised, 1);
					}
					
					if (biomeID == Biomes.promisedLandShrub.get().biomeID)
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
