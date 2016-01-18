/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.init;

import static biomesoplenty.api.achievement.BOPAchievements.explore_all_biomes;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_berry;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_coral;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_flowers;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_thorn;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_poison_ivy;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_turnip;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_honeycomb;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_pixie_dust;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_celestial_crystal;
import static biomesoplenty.api.achievement.BOPAchievements.craft_ornamental_artifact;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPPlant;
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
        obtain_poison_ivy = addAchievement("achievement.obtain_poison_ivy", "obtain_poison_ivy", -3, 0, BlockBOPPlant.paging.getVariantItem(BOPPlants.POISONIVY), obtain_thorn);
        obtain_turnip = addAchievement("achievement.obtain_turnip", "obtain_turnip", -1, -4, new ItemStack(BOPItems.turnip), obtain_coral);
        obtain_honeycomb = addAchievement("achievement.obtain_honeycomb", "obtain_honeycomb", 3, -3, new ItemStack(BOPItems.honeycomb), obtain_coral);
        obtain_pixie_dust = addAchievement("achievement.obtain_pixie_dust", "obtain_pixie_dust", 5, -4, new ItemStack(BOPItems.pixie_dust), obtain_honeycomb);
        obtain_celestial_crystal = addAchievement("achievement.obtain_celestial_crystal", "obtain_celestial_crystal", 7, -1, new ItemStack(BOPItems.crystal_shard), obtain_pixie_dust);
        craft_ornamental_artifact = addAchievement("achievement.craft_ornamental_artifact", "craft_ornamental_artifact", 3, -8, new ItemStack(BOPItems.gem), obtain_pixie_dust);
        explore_all_biomes = addAchievement("achievement.explore_all_biomes", "explore_all_biomes", 0, -8, new ItemStack(BOPItems.earth), craft_ornamental_artifact).setSpecial();
    }
    
    private static Achievement addAchievement(String unlocalizedName, String identifier, int column, int row, ItemStack iconStack, Achievement parent)
    {
        Achievement achievement = new Achievement(unlocalizedName, identifier, column, row, iconStack, parent);
        achievementPage.getAchievements().add(achievement);
        return achievement;
    }
}
