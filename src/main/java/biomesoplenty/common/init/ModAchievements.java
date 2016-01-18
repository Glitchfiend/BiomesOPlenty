/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.init;

import static biomesoplenty.api.achievement.BOPAchievements.*;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

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
        obtain_flowers = addAchievement("achievement.obtain_flowers", "obtain_flowers", 2, 0, new ItemStack(Blocks.red_flower), null);
        explore_all_biomes = addAchievement("achievement.explore_all_biomes", "explore_all_biomes", 0, 0, new ItemStack(BOPItems.earth), null);
    }
    
    private static Achievement addAchievement(String unlocalizedName, String identifier, int column, int row, ItemStack iconStack, Achievement parent)
    {
        Achievement achievement = new Achievement(unlocalizedName, identifier, column, row, iconStack, parent);
        achievementPage.getAchievements().add(achievement);
        return achievement;
    }
}
