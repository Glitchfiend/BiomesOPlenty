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

import com.google.common.collect.Sets;

import biomesoplenty.api.achievement.BOPAchievements;
import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.item.ItemJarFilled;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class AchievementEventHandler 
{
    private static final Set<BiomeGenBase> BOP_BIOMES_TO_EXPLORE = Sets.union(BOPBiomes.REG_INSTANCE.getPresentBiomes(), BiomeGenBase.explorationBiomesList);

    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event)
    {
        ItemStack stack = event.pickedUp.getEntityItem();
        Item item = stack.getItem();
        
        Block block = Block.getBlockFromItem(item);
        IBlockState state = block != null ? block.getStateFromMeta(stack.getItemDamage()) : null;
        EntityPlayer player = event.player;

        if (block != null && block instanceof BlockBOPLog)
        {
            player.triggerAchievement(AchievementList.mineWood);
        }

        //Flower Child Achievement
        if (block != null && block instanceof BlockBOPFlower || block == Blocks.red_flower || block == Blocks.yellow_flower)
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
        
        //Life Finds a Way Achievement
        if (block != null && state == BlockBOPFlower.paging.getVariantState(BOPFlowers.MINERS_DELIGHT))
        {
            player.triggerAchievement(BOPAchievements.obtain_miners_delight);
        }


        //Rather Thorny Achievement
        if (block != null && state == BlockBOPPlant.paging.getVariantState(BOPPlants.THORN))
        {
            player.triggerAchievement(BOPAchievements.obtain_thorn);
        }
        
        //I am Become Death Achievement
        if (block != null && state == BlockBOPFlower.paging.getVariantState(BOPFlowers.DEATHBLOOM))
        {
            player.triggerAchievement(BOPAchievements.obtain_deathbloom);
        }
        
        //Godsend Achievement
        if (block != null && state == BlockBOPFlower.paging.getVariantState(BOPFlowers.WILTED_LILY))
        {
            player.triggerAchievement(BOPAchievements.obtain_wilted_lily);
        }

        //Stalk Market Achievement
        if (item != null && item == BOPItems.turnip)
        {
            player.triggerAchievement(BOPAchievements.obtain_turnip);
        }
        
        //Soul Searching Achievement
        if (item != null && item == BOPItems.soul)
        {
            player.triggerAchievement(BOPAchievements.obtain_soul);
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
    public void onItemUsed(PlayerInteractEvent event)
    {
        if (event.action != Action.LEFT_CLICK_BLOCK)
        {
            ItemStack stack = event.entityPlayer.getHeldItem();
            Item item = stack != null ? stack.getItem() : null;
            EntityPlayer player = event.entityPlayer;

            //Gone Home
            if (item == BOPItems.enderporter)
            {
                player.triggerAchievement(BOPAchievements.use_enderporter);
            }
        }
    }
    
    @SubscribeEvent
    public void onItemUsed(PlayerUseItemEvent.Finish event)
    {
        ItemStack stack = event.item;
        Item item = stack.getItem();
        EntityPlayer player = event.entityPlayer;
        
        //Trippin'
        if (item == BOPItems.shroompowder)
        {
            player.triggerAchievement(BOPAchievements.eat_shroom_powder);
        }
    }
    
    @SubscribeEvent
    public void onBlockPlaced(BlockEvent.PlaceEvent event)
    {
        ItemStack stack = event.itemInHand;
        
        //Blocks can be placed by things other than players
        if (stack != null)
        {
            Item item = stack.getItem();
            Block block = Block.getBlockFromItem(item);
            IBlockState state = block != null ? block.getStateFromMeta(stack.getItemDamage()) : null;

            //Yggdrasil
            if (state == BlockBOPSapling.paging.getVariantState(BOPTrees.SACRED_OAK))
            {
                event.player.triggerAchievement(BOPAchievements.grow_sacred_oak);
            }
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
        
        //Flaxen Fun Achievement
        if (item != null && item == BOPItems.flax_string)
        {
            player.triggerAchievement(BOPAchievements.craft_flax_string);
        }
        
        //True Swordsman Achievement
        if (item != null && item == BOPItems.amethyst_sword)
        {
            player.triggerAchievement(BOPAchievements.craft_amethyst_sword);
        }
        
        //Getting a Downgrade Achievement
        if (item != null && item == BOPItems.mud_pickaxe)
        {
            player.triggerAchievement(BOPAchievements.craft_muddy_pickaxe);
        }
        
        //By Your Powers Combined Achievement
        if (item != null && item == BOPItems.terrestrial_artifact)
        {
            player.triggerAchievement(BOPAchievements.craft_terrestrial_artifact);
        }
        
        //Darts and Crafts Achievement
        if (item != null && item == BOPItems.dart_blower)
        {
            player.triggerAchievement(BOPAchievements.craft_dart_blower);
        }
        
        //Pick Your Poison Achievement
        if (item != null && item == new ItemStack(BOPItems.jar_filled, 1, ItemJarFilled.JarContents.POISON.ordinal()).getItem())
        {
            player.triggerAchievement(BOPAchievements.craft_poison_jar);
        }
        
    }

    @SubscribeEvent
    public void onPlayerUpdate(LivingUpdateEvent event)
    {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP)event.entity;

            //Check every five seconds if the player has entered a new biome, if they haven't already gotten the achievement
            if (player.ticksExisted % 20 * 5 == 0)
            {
                if (!player.getStatFile().hasAchievementUnlocked(BOPAchievements.use_biome_finder))
                {
                    this.updateBiomeRadarExplore(player);
                }
                
                if (!player.getStatFile().hasAchievementUnlocked(BOPAchievements.explore_all_biomes))
                {
                    this.updateBiomesExplored(player);
                }
            }
        }
    }

    private void updateBiomeRadarExplore(EntityPlayerMP player)
    {
        BiomeGenBase currentBiome = player.worldObj.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(player.posX), 0, MathHelper.floor_double(player.posZ)));

        //Search every item in the player's main inventory for a biome radar
        for (ItemStack stack : player.inventory.mainInventory)
        {
            //If the stack is null, skip it
            if (stack == null)
                continue;
            
            if (stack.getItem() == BOPItems.biome_finder && stack.hasTagCompound() && stack.getTagCompound().hasKey("biomeIDToFind"))
            {
                int biomeIdToFind = stack.getTagCompound().getInteger("biomeIDToFind");

                //If the current biome id is the id on the radar, award the achievement and stop searching
                if (biomeIdToFind == currentBiome.biomeID) 
                {
                    player.triggerAchievement(BOPAchievements.use_biome_finder);
                    return;
                }
            }
        }
    }
    
    private void updateBiomesExplored(EntityPlayerMP player)
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
