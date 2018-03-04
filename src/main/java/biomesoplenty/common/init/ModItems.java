/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.item.BOPItems.ambrosia;
import static biomesoplenty.api.item.BOPItems.ash;
import static biomesoplenty.api.item.BOPItems.berries;
import static biomesoplenty.api.item.BOPItems.biome_essence;
import static biomesoplenty.api.item.BOPItems.biome_finder;
import static biomesoplenty.api.item.BOPItems.black_dye;
import static biomesoplenty.api.item.BOPItems.blue_dye;
import static biomesoplenty.api.item.BOPItems.boat_cherry;
import static biomesoplenty.api.item.BOPItems.boat_ebony;
import static biomesoplenty.api.item.BOPItems.boat_ethereal;
import static biomesoplenty.api.item.BOPItems.boat_eucalyptus;
import static biomesoplenty.api.item.BOPItems.boat_fir;
import static biomesoplenty.api.item.BOPItems.boat_hellbark;
import static biomesoplenty.api.item.BOPItems.boat_jacaranda;
import static biomesoplenty.api.item.BOPItems.boat_magic;
import static biomesoplenty.api.item.BOPItems.boat_mahogany;
import static biomesoplenty.api.item.BOPItems.boat_mangrove;
import static biomesoplenty.api.item.BOPItems.boat_palm;
import static biomesoplenty.api.item.BOPItems.boat_pine;
import static biomesoplenty.api.item.BOPItems.boat_redwood;
import static biomesoplenty.api.item.BOPItems.boat_sacred_oak;
import static biomesoplenty.api.item.BOPItems.boat_umbran;
import static biomesoplenty.api.item.BOPItems.boat_willow;
import static biomesoplenty.api.item.BOPItems.brown_dye;
import static biomesoplenty.api.item.BOPItems.crystal_shard;
import static biomesoplenty.api.item.BOPItems.earth;
import static biomesoplenty.api.item.BOPItems.filled_honeycomb;
import static biomesoplenty.api.item.BOPItems.fleshchunk;
import static biomesoplenty.api.item.BOPItems.flower_basket;
import static biomesoplenty.api.item.BOPItems.gem;
import static biomesoplenty.api.item.BOPItems.green_dye;
import static biomesoplenty.api.item.BOPItems.honeycomb;
import static biomesoplenty.api.item.BOPItems.jar_empty;
import static biomesoplenty.api.item.BOPItems.jar_filled;
import static biomesoplenty.api.item.BOPItems.mud_brick;
import static biomesoplenty.api.item.BOPItems.mudball;
import static biomesoplenty.api.item.BOPItems.peach;
import static biomesoplenty.api.item.BOPItems.pear;
import static biomesoplenty.api.item.BOPItems.persimmon;
import static biomesoplenty.api.item.BOPItems.pinecone;
import static biomesoplenty.api.item.BOPItems.pixie_dust;
import static biomesoplenty.api.item.BOPItems.record_wanderer;
import static biomesoplenty.api.item.BOPItems.ricebowl;
import static biomesoplenty.api.item.BOPItems.saladfruit;
import static biomesoplenty.api.item.BOPItems.saladshroom;
import static biomesoplenty.api.item.BOPItems.saladveggie;
import static biomesoplenty.api.item.BOPItems.shroompowder;
import static biomesoplenty.api.item.BOPItems.terrestrial_artifact;
import static biomesoplenty.api.item.BOPItems.turnip;
import static biomesoplenty.api.item.BOPItems.turnip_seeds;
import static biomesoplenty.api.item.BOPItems.white_dye;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.entities.item.EntityBOPBoat;
import biomesoplenty.common.item.ItemAmbrosia;
import biomesoplenty.common.item.ItemBOPBoat;
import biomesoplenty.common.item.ItemBOPFood;
import biomesoplenty.common.item.ItemBOPRecord;
import biomesoplenty.common.item.ItemBiomeEssence;
import biomesoplenty.common.item.ItemBiomeFinder;
import biomesoplenty.common.item.ItemFlowerBasket;
import biomesoplenty.common.item.ItemGem;
import biomesoplenty.common.item.ItemJarEmpty;
import biomesoplenty.common.item.ItemJarFilled;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSoup;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModItems
{    
    public static void init()
    {
        registerItems();
    }
    
    public static void registerItems()
    {
        jar_filled = registerItem(new ItemJarFilled(), "jar_filled");
        jar_empty = registerItem(new ItemJarEmpty(), "jar_empty");
        
    	boat_sacred_oak = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.SACRED_OAK), "boat_sacred_oak");
    	boat_cherry = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.CHERRY), "boat_cherry");
    	boat_umbran = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.UMBRAN), "boat_umbran");
    	boat_fir = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.FIR), "boat_fir");
    	boat_ethereal = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.ETHEREAL), "boat_ethereal");
    	boat_magic = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.MAGIC), "boat_magic");
    	boat_mangrove = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.MANGROVE), "boat_mangrove");
    	boat_palm = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.PALM), "boat_palm");
    	boat_redwood = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.REDWOOD), "boat_redwood");
    	boat_willow = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.WILLOW), "boat_willow");
    	boat_pine = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.PINE), "boat_pine");
    	boat_hellbark = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.HELLBARK), "boat_hellbark");
    	boat_jacaranda = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.JACARANDA), "boat_jacaranda");
    	boat_mahogany = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.MAHOGANY), "boat_mahogany");
    	boat_ebony = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.EBONY), "boat_ebony");
    	boat_eucalyptus = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.EUCALYPTUS), "boat_eucalyptus");
        
        biome_finder = registerItem(new ItemBiomeFinder(), "biome_finder");
        flower_basket = registerItem(new ItemFlowerBasket(), "flower_basket");
        
        record_wanderer = registerItem(new ItemBOPRecord("wanderer", BOPSounds.records_wanderer), "record_wanderer");
        
        mudball = registerItem(new ItemMudball(), "mudball");
        mud_brick = registerItem(new Item(), "mud_brick");
        ash = registerItem(new Item(), "ash");
        fleshchunk = registerItem(new Item(), "fleshchunk");
        
        pixie_dust = registerItem(new Item(), "pixie_dust");
        
        gem = registerItem(new ItemGem(), "gem");
        
        terrestrial_artifact = registerItem(new Item(), "terrestrial_artifact");
        terrestrial_artifact.setMaxStackSize(1);
        
        crystal_shard = registerItem(new Item(), "crystal_shard");
        
        biome_essence = registerItem(new ItemBiomeEssence(), "biome_essence");
        
        // food
    	berries =           registerItem(new ItemBOPFood(1, 0.1F, 8), "berries"); 
    	pear =              registerItem(new ItemFood(5, 0.3F, false), "pear");
    	peach =             registerItem(new ItemFood(5, 0.2F, false), "peach");
        persimmon =         registerItem(new ItemFood(5, 0.2F, false), "persimmon");
        pinecone = registerItem(new Item(), "pinecone");
        
        turnip_seeds = registerItem(new ItemSeeds(BOPBlocks.turnip_block, Blocks.FARMLAND), "turnip_seeds");
        turnip =            registerItem(new ItemFood(3, 0.4F, false), "turnip");
        
        honeycomb = registerItem(new Item(), "honeycomb");
        filled_honeycomb =  registerItem(new ItemBOPFood(3, 0.4F, 16), "filled_honeycomb");
        
        shroompowder =      registerItem(new ItemFood(1, 0.1F, false), "shroompowder");
        ((ItemFood)shroompowder).setAlwaysEdible();
        ((ItemFood)shroompowder).setPotionEffect(new PotionEffect(MobEffects.NAUSEA, 225, 0), 1.0F);
        
        saladfruit =        registerItem(new ItemSoup(6), "saladfruit");
        ((ItemFood)saladfruit).setPotionEffect(new PotionEffect(MobEffects.HASTE, 775, 1), 0.05F);
        saladveggie =       registerItem(new ItemSoup(6), "saladveggie");
        ((ItemFood)saladveggie).setPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1100, 1), 0.05F); // TODO: Is this the right potion effect for veggie salad?
        saladshroom =       registerItem(new ItemSoup(6), "saladshroom");
        ((ItemFood)saladshroom).setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 550, 1), 0.05F);
        ricebowl =          registerItem(new ItemSoup(2), "ricebowl");
        
        ambrosia =          registerItem(new ItemAmbrosia(), "ambrosia");
        
        // TODO: move dyes to their own class?
        blue_dye = registerItem(new Item(), "blue_dye");
        brown_dye = registerItem(new Item(), "brown_dye");
        green_dye = registerItem(new Item(), "green_dye");
        white_dye = registerItem(new Item(), "white_dye");
        black_dye = registerItem(new Item(), "black_dye");
        
        earth = registerItem(new Item(), "earth");
        earth.setCreativeTab(null);
    }
    
    public static Item registerItem(Item item, String name)
    {
        return registerItem(item, name, CreativeTabBOP.instance);
    }
    
    public static Item registerItem(Item item, String name, CreativeTabs tab)
    {    
        item.setUnlocalizedName(name);
        if (tab != null)
        {
            item.setCreativeTab(CreativeTabBOP.instance);
        }

        item.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name));
        ForgeRegistries.ITEMS.register(item);
        BOPCommand.itemCount++;
        BiomesOPlenty.proxy.registerItemSided(item);
        
        return item;   
    }
}
