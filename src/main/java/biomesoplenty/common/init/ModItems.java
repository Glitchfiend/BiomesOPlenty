/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.item.BOPItems.ash;
import static biomesoplenty.api.item.BOPItems.berries;
import static biomesoplenty.api.item.BOPItems.boat_cherry;
import static biomesoplenty.api.item.BOPItems.boat_ebony;
import static biomesoplenty.api.item.BOPItems.boat_ethereal;
import static biomesoplenty.api.item.BOPItems.boat_fir;
import static biomesoplenty.api.item.BOPItems.boat_hellbark;
import static biomesoplenty.api.item.BOPItems.boat_jacaranda;
import static biomesoplenty.api.item.BOPItems.boat_magic;
import static biomesoplenty.api.item.BOPItems.boat_mahogany;
import static biomesoplenty.api.item.BOPItems.boat_palm;
import static biomesoplenty.api.item.BOPItems.boat_redwood;
import static biomesoplenty.api.item.BOPItems.boat_umbran;
import static biomesoplenty.api.item.BOPItems.boat_willow;
import static biomesoplenty.api.item.BOPItems.bop_icon;
import static biomesoplenty.api.item.BOPItems.fleshchunk;
import static biomesoplenty.api.item.BOPItems.mud_brick;
import static biomesoplenty.api.item.BOPItems.mudball;
import static biomesoplenty.api.item.BOPItems.peach;
import static biomesoplenty.api.item.BOPItems.pear;
import static biomesoplenty.api.item.BOPItems.persimmon;
import static biomesoplenty.api.item.BOPItems.record_wanderer;

import javax.annotation.Nullable;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.entities.item.EntityBOPBoat;
import biomesoplenty.common.item.ItemBOPBoat;
import biomesoplenty.common.item.ItemBOPFood;
import biomesoplenty.common.item.ItemBOPRecord;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
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
    	boat_fir = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.FIR), "boat_fir");
    	boat_redwood = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.REDWOOD), "boat_redwood");
    	boat_cherry = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.CHERRY), "boat_cherry");
    	boat_mahogany = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.MAHOGANY), "boat_mahogany");
    	boat_jacaranda = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.JACARANDA), "boat_jacaranda");
    	boat_palm = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.PALM), "boat_palm");
    	boat_willow = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.WILLOW), "boat_willow");
    	boat_ebony = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.EBONY), "boat_ebony");
    	boat_magic = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.MAGIC), "boat_magic");
    	boat_umbran = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.UMBRAN), "boat_umbran");
    	boat_hellbark = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.HELLBARK), "boat_hellbark");
    	boat_ethereal = registerItem(new ItemBOPBoat(EntityBOPBoat.Type.ETHEREAL), "boat_ethereal");
        
        mudball = registerItem(new ItemMudball(), "mudball");
        mud_brick = registerItem(new Item(), "mud_brick");
        ash = registerItem(new Item(), "ash");
        fleshchunk = registerItem(new Item(), "fleshchunk");
        
        // food
    	berries =           registerItem(new ItemBOPFood(1, 0.1F, 8), "berries"); 
    	pear =              registerItem(new ItemFood(5, 0.3F, false), "pear");
    	peach =             registerItem(new ItemFood(5, 0.2F, false), "peach");
        persimmon =         registerItem(new ItemFood(5, 0.2F, false), "persimmon");
        
        record_wanderer = registerItem(new ItemBOPRecord("wanderer", BOPSounds.records_wanderer), "record_wanderer");
        
        bop_icon = registerItem(new Item(), "bop_icon", null);
    }
    
    public static Item registerItem(Item item, String name)
    {
        return registerItem(item, name, CreativeTabBOP.instance);
    }
    
    public static Item registerItem(Item item, String name, @Nullable CreativeTabs tab)
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
