package biomesoplenty.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfiguration;
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
        achRedRock = (new Achievement(3058, "achRedRock", -1, 2, new ItemStack(Blocks.redRock.get(),1,0), achFlower)).registerAchievement();
        achThorn = (new Achievement(3059, "achThorn", 2, 1, new ItemStack(Blocks.plants.get(),1,5), achFlower)).registerAchievement();
        achAsh = (new Achievement(3060, "achAsh", 1, 3, new ItemStack(Items.miscItems.get(), 1, 1), achFlower)).registerAchievement();
        achOrigin = (new Achievement(3061, "achOrigin", 0, 5, Blocks.originGrass.get(), achFlower)).setSpecial().registerAchievement();
        achPromised = (new Achievement(3062, "achPromised", 0, -5, Blocks.holyGrass.get(), achFlower)).setSpecial().registerAchievement();
        achMud = (new Achievement(3063, "achMud", -2, -1, Items.mudball.get(), achFlower)).registerAchievement();
        achShroom = (new Achievement(3064, "achShroom", 1, -2, new ItemStack(Blocks.flowers.get(),1,10), achFlower)).registerAchievement();
        achBarley = (new Achievement(3065, "achBarley", -2, 4, new ItemStack(Blocks.plants.get(),1,6), achFlower)).registerAchievement();
        achMoss = (new Achievement(3066, "achMoss", -1, -3, Blocks.moss.get(), achFlower)).registerAchievement();

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
            if (item.itemID == Blocks.flowers.get().blockID && item.getItemDamage() == 10)
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
    }
    
    private static void addAchievementDesc(String ach, String name, String desc)
    {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
    }
}