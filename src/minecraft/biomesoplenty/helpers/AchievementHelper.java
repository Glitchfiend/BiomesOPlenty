package biomesoplenty.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.configuration.BOPItems;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class AchievementHelper
{
 // Achievement declaration
    private static Achievement achFlower;
    private static Achievement achRedRock;
    private static Achievement achThorn;
    private static Achievement achAsh;
    private static Achievement achOrigin;
    private static Achievement achPromised;
    private static Achievement achMud;
    private static Achievement achShroom;
    private static Achievement achBarley;
    private static Achievement achMoss;
    
    public static AchievementPage pageBOP;

	@ForgeSubscribe
	public void EntityItemPickupEvent(EntityItemPickupEvent event)
	{
		onItemPickup(event.entityPlayer, event.item.getEntityItem());
	}
	
	public static void init()
	{
	    achFlower = (new Achievement(3057, "achFlower", 0, 0, Block.plantRed, null)).registerAchievement();
        achRedRock = (new Achievement(3058, "achRedRock", -1, 2, BOPBlocks.redRock, achFlower)).registerAchievement();
        achThorn = (new Achievement(3059, "achThorn", 2, 1, BOPBlocks.thorn, achFlower)).registerAchievement();
        achAsh = (new Achievement(3060, "achAsh", 1, 3, BOPItems.ashes, achFlower)).registerAchievement();
        achOrigin = (new Achievement(3061, "achOrigin", 0, 5, BOPBlocks.originGrass, achFlower)).setSpecial().registerAchievement();
        achPromised = (new Achievement(3062, "achPromised", 0, -5, BOPBlocks.holyGrass, achFlower)).setSpecial().registerAchievement();
        achMud = (new Achievement(3063, "achMud", -2, -1, BOPItems.mudBall, achFlower)).registerAchievement();
        achShroom = (new Achievement(3064, "achShroom", 1, -2, BOPBlocks.toadstool, achFlower)).registerAchievement();
        achBarley = (new Achievement(3065, "achBarley", -2, 4, BOPItems.barleyItem, achFlower)).registerAchievement();
        achMoss = (new Achievement(3066, "achMoss", -1, -3, BOPItems.mossItem, achFlower)).registerAchievement();

        pageBOP = new AchievementPage("Biomes O\' Plenty", new Achievement[] {achFlower, achRedRock, achThorn, achAsh, achOrigin, achPromised, achMud, achShroom, achBarley, achMoss});
        AchievementPage.registerAchievementPage(pageBOP);
        
     // Add Achievement registration
        addAchievementDesc("achFlower", "Flower Child", "Pick some flowers!");
        addAchievementDesc("achRedRock", "Red Rocky Mountain High", "Dig out some red rocks.");
        addAchievementDesc("achThorn", "Rather Thorny...", "Don\'t get cut!");
        addAchievementDesc("achAsh", "Ash-ievement", "Get it?  \'Cause it\'s ash.");
        addAchievementDesc("achOrigin", "Alpha...", "Get some grass from the Origin Valley.");
        addAchievementDesc("achPromised", "...Omega", "Welcome to the Promised Land!");
        addAchievementDesc("achMud", "Sticky Situation", "I just had these boots cleaned!");
        addAchievementDesc("achShroom", "Trippin\'", "Don\'t try this at home, kids!");
        addAchievementDesc("achBarley", "Fields Of Gold", "Upon the fields of barley.");
        addAchievementDesc("achMoss", "Mossman", "Mothman's long-lost cousin.");
	}
	
	// Achievement checker
    private static void onItemPickup(EntityPlayer player, ItemStack item)
    {
        if (BOPConfiguration.achievements == true)
        {
            if (item.itemID == BOPBlocks.glowFlower.blockID || item.itemID == BOPBlocks.orangeFlower.blockID || item.itemID == BOPBlocks.blueFlower.blockID || item.itemID == BOPBlocks.purpleFlower.blockID || item.itemID == BOPBlocks.pinkFlower.blockID || item.itemID == BOPBlocks.whiteFlower.blockID || item.itemID == BOPBlocks.tinyFlower.blockID || item.itemID == BOPBlocks.deathbloom.blockID || item.itemID == BOPBlocks.hydrangea.blockID || item.itemID == BOPBlocks.violet.blockID || item.itemID == Block.plantRed.blockID || item.itemID == Block.plantYellow.blockID)
            {
                player.addStat(achFlower, 1);
            }
            if (item.itemID == BOPBlocks.redRockCobble.blockID)
            {
                player.addStat(achRedRock, 1);
            }
            if (item.itemID == BOPBlocks.thorn.blockID)
            {
                player.addStat(achThorn, 1);
            }
            if (item.itemID == BOPItems.ashes.itemID)
            {
                player.addStat(achAsh, 1);
            }
            if (item.itemID == BOPBlocks.originGrass.blockID)
            {
                player.addStat(achOrigin, 1);
            }
            if (item.itemID == BOPBlocks.holyGrass.blockID || item.itemID == BOPBlocks.holyStone.blockID)
            {
                player.addStat(achPromised, 1);
            }
            if (item.itemID == BOPItems.mudBall.itemID)
            {
                player.addStat(achMud, 1);
            }
            if (item.itemID == BOPBlocks.toadstool.blockID)
            {
                player.addStat(achShroom, 1);
            }
            if (item.itemID == BOPItems.barleyItem.itemID)
            {
                player.addStat(achBarley, 1);
            }
            if (item.itemID == BOPItems.mossItem.itemID)
            {
                player.addStat(achMoss, 1);
            }
        }
    }
    
    private static void addAchievementDesc(String ach, String name, String desc)
    {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
    }
}