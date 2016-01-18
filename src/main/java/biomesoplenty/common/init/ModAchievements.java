/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.init;

import static biomesoplenty.api.achievement.BOPAchievements.craft_ambrosia;
import static biomesoplenty.api.achievement.BOPAchievements.craft_ornamental_artifact;
import static biomesoplenty.api.achievement.BOPAchievements.explore_all_biomes;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_berry;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_celestial_crystal;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_coral;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_flowers;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_honeycomb;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_pixie_dust;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_poison_ivy;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_thorn;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_turnip;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_deathbloom;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_glowshroom;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_ghastly_soul;
import static biomesoplenty.api.achievement.BOPAchievements.use_enderporter;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPPlants;

public class ModAchievements 
{
    public static final AchievementPage achievementPage = new AchievementPage("Biomes O\' Plenty");
    
    public static void init()
    {
        AchievementPage.registerAchievementPage(achievementPage);
        
        addAchievements();
    }
    
    private static void addAchievements()
    {
        obtain_flowers = addAchievement("achievement.obtain_flowers", "obtain_flowers", 0, 0, new ItemStack(Blocks.red_flower), null);
        obtain_berry = addAchievement("achievement.obtain_berry", "obtain_berry", 2, 1, new ItemStack(BOPItems.berries), obtain_flowers);
        obtain_coral = addAchievement("achievement.obtain_coral", "obtain_coral", 1, -2, new ItemStack(BOPBlocks.coral), obtain_berry);
        obtain_thorn = addAchievement("achievement.obtain_thorn", "obtain_thorn", -2, -2, BlockBOPPlant.paging.getVariantItem(BOPPlants.THORN), obtain_coral);
        obtain_glowshroom = addAchievement("achievement.obtain_glowshroom", "obtain_glowshroom", -5, -4, BlockBOPPlant.paging.getVariantItem(BOPPlants.KORU), obtain_coral);
        obtain_poison_ivy = addAchievement("achievement.obtain_poison_ivy", "obtain_poison_ivy", -3, 1, BlockBOPPlant.paging.getVariantItem(BOPPlants.POISONIVY), obtain_thorn);
        obtain_deathbloom = addAchievement("achievement.obtain_deathbloom", "obtain_deathbloom", -6, 2, BlockBOPFlower.paging.getVariantItem(BOPFlowers.DEATHBLOOM), obtain_poison_ivy);
        obtain_turnip = addAchievement("achievement.obtain_turnip", "obtain_turnip", -1, -5, new ItemStack(BOPItems.turnip), obtain_coral);
        obtain_honeycomb = addAchievement("achievement.obtain_honeycomb", "obtain_honeycomb", 3, -3, new ItemStack(BOPItems.filled_honeycomb), obtain_coral);
        craft_ornamental_artifact = addAchievement("achievement.craft_ornamental_artifact", "craft_ornamental_artifact", 5, -4, new ItemStack(BOPItems.gem), obtain_honeycomb);
        obtain_pixie_dust = addAchievement("achievement.obtain_pixie_dust", "obtain_pixie_dust", 7, -2, new ItemStack(BOPItems.pixie_dust), craft_ornamental_artifact);
        obtain_ghastly_soul = addAchievement("achievement.obtain_ghastly_soul", "obtain_ghastly_soul", 4, 2, new ItemStack(BOPItems.ghastly_soul), obtain_honeycomb);
        obtain_celestial_crystal = addAchievement("achievement.obtain_celestial_crystal", "obtain_celestial_crystal", 6, 0, new ItemStack(BOPItems.crystal_shard), obtain_honeycomb);
        craft_ambrosia = addAchievement("achievement.craft_ambrosia", "craft_ambrosia", 8, 4, new ItemStack(BOPItems.ambrosia), obtain_celestial_crystal).setSpecial();
        explore_all_biomes = addAchievement("achievement.explore_all_biomes", "explore_all_biomes", 2, -7, new ItemStack(BOPItems.earth), craft_ornamental_artifact).setSpecial();
        use_enderporter = addAchievement("achievement.use_enderporter", "use_enderporter", 1, 3, new ItemStack(BOPItems.enderporter), obtain_ghastly_soul).setSpecial();
    }
    
    private static Achievement addAchievement(String unlocalizedName, String identifier, int column, int row, ItemStack iconStack, Achievement parent)
    {
        Achievement achievement = new Achievement(unlocalizedName, identifier, column, row, iconStack, parent);
        achievement.registerStat();
        achievementPage.getAchievements().add(achievement);
        return achievement;
    }
}
