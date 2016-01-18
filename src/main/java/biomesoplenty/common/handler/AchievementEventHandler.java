/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.handler;

import java.util.Iterator;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import biomesoplenty.api.achievement.BOPAchievements;
import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPPlants;

import com.google.common.collect.Sets;

public class AchievementEventHandler 
{
    private static final Set<BiomeGenBase> BOP_BIOMES_TO_EXPLORE = Sets.union(BOPBiomes.REG_INSTANCE.getPresentBiomes(), BiomeGenBase.explorationBiomesList);

    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event)
    {
        Item item = event.pickedUp.getEntityItem().getItem();
        Block block = Block.getBlockFromItem(item);
        EntityPlayer player = event.player;

        if (block != null && block instanceof BlockBOPLog)
        {
            player.triggerAchievement(AchievementList.mineWood);
        }

        //Flower Child Achievement
        if (block != null && block instanceof BlockBOPFlower)
        {
            player.triggerAchievement(BOPAchievements.obtain_flowers);
        }

        //Berry Good Achievement
        if (item != null && item == BOPItems.berries)
        {
            player.triggerAchievement(BOPAchievements.obtain_berry);
        }

        //Totally Coral Achievement
        if (block != null && block == BOPBlocks.coral)
        {
            player.triggerAchievement(BOPAchievements.obtain_coral);
        }

        //Rather Thorny Achievement
        if (block != null && block == BlockBOPPlant.paging.getBlock(BOPPlants.THORN))
        {
            player.triggerAchievement(BOPAchievements.obtain_thorn);
        }

        //Pick Your Poison Achievement
        if (block != null && block == BlockBOPPlant.paging.getBlock(BOPPlants.POISONIVY))
        {
            player.triggerAchievement(BOPAchievements.obtain_poison_ivy);
        }
        
        //I am Become Death Achievement
        if (block != null && block == BlockBOPFlower.paging.getBlock(BOPFlowers.DEATHBLOOM))
        {
            player.triggerAchievement(BOPAchievements.obtain_deathbloom);
        }

        //Stalk Market Achievement
        if (item != null && item == BOPItems.turnip)
        {
            player.triggerAchievement(BOPAchievements.obtain_turnip);
        }
        
        //Soul Searching Achievement
        if (item != null && item == BOPItems.ghastly_soul)
        {
            player.triggerAchievement(BOPAchievements.obtain_ghastly_soul);
        }

        //Honeycomb's Big Achievement
        if (item != null && item == BOPItems.filled_honeycomb)
        {
            player.triggerAchievement(BOPAchievements.obtain_honeycomb);
        }

        //Don't Breathe This Achievement
        if (item != null && item == BOPItems.pixie_dust)
        {
            player.triggerAchievement(BOPAchievements.obtain_pixie_dust);
        }

        //Far Out Achievement
        if (item != null && item == BOPItems.crystal_shard)
        {
            player.triggerAchievement(BOPAchievements.obtain_celestial_crystal);
        }
    }
    
    @SubscribeEvent
    public void onItemCrafted(ItemCraftedEvent event)
    {
        Item item = event.crafting.getItem();
        EntityPlayer player = event.player;
        
        //Nectar of the Gods Achievement
        if (item != null && item == BOPItems.ambrosia)
        {
            player.triggerAchievement(BOPAchievements.craft_ambrosia);
        }
    }

    @SubscribeEvent
    public void onPlayerUpdate(LivingUpdateEvent event)
    {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP)event.entity;

            //Check every five seconds if the player has entered a new biome, if they haven't already gotten the achievement
            if (player.ticksExisted % 20 * 5 == 0 && !player.getStatFile().hasAchievementUnlocked(BOPAchievements.explore_all_biomes))
            {
                this.updateBiomesExplored(player);
            }
        }
    }

    public void updateBiomesExplored(EntityPlayerMP player)
    {
        BiomeGenBase currentBiome = player.worldObj.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(player.posX), 0, MathHelper.floor_double(player.posZ)));
        String biomeName = currentBiome.biomeName;
        //Get a list of the current explored biomes
        JsonSerializableSet exploredBiomeNames = (JsonSerializableSet)player.getStatFile().func_150870_b(BOPAchievements.explore_all_biomes);

        if (exploredBiomeNames == null)
        {
            //Set the stat data
            exploredBiomeNames = (JsonSerializableSet)player.getStatFile().func_150872_a(BOPAchievements.explore_all_biomes, new JsonSerializableSet());
        }

        //Add the current biome to the set of biomes that the player has explored
        exploredBiomeNames.add(biomeName);

        if (player.getStatFile().canUnlockAchievement(BOPAchievements.explore_all_biomes) && exploredBiomeNames.size() >= BOP_BIOMES_TO_EXPLORE.size())
        {
            //Create a copy of the set of biomes that need to be explored to fulfil the achievement
            Set<BiomeGenBase> set = Sets.newHashSet(BOP_BIOMES_TO_EXPLORE);

            //Iterate over the names of all the biomes the player has explored
            for (String exploredBiomeName : exploredBiomeNames)
            {
                Iterator<BiomeGenBase> iterator = set.iterator();

                //Iterate over the set of biomes required to be explored and remove those that already have been explored
                while (iterator.hasNext())
                {
                    BiomeGenBase biome = (BiomeGenBase)iterator.next();

                    if (biome.biomeName.equals(exploredBiomeName))
                    {
                        iterator.remove();
                    }
                }

                //If there are no biomes remaining in the set to be explored, then there's no point continuing
                if (set.isEmpty())
                {
                    break;
                }
            }

            //Has the player fulfilled the achievement (there are no biomes left in the set of biomes to be explored)
            if (set.isEmpty())
            {
                player.triggerAchievement(BOPAchievements.explore_all_biomes);
            }
        }
    }
}
