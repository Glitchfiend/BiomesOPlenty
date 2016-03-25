/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.item.BOPItemHelper.amethyst_armor_material;
import static biomesoplenty.api.item.BOPItemHelper.amethyst_tool_material;
import static biomesoplenty.api.item.BOPItemHelper.dull_flower_band_material;
import static biomesoplenty.api.item.BOPItemHelper.exotic_flower_band_material;
import static biomesoplenty.api.item.BOPItemHelper.flippers_material;
import static biomesoplenty.api.item.BOPItemHelper.lush_flower_band_material;
import static biomesoplenty.api.item.BOPItemHelper.mud_armor_material;
import static biomesoplenty.api.item.BOPItemHelper.mud_tool_material;
import static biomesoplenty.api.item.BOPItemHelper.plain_flower_band_material;
import static biomesoplenty.api.item.BOPItemHelper.wading_boots_material;
import static biomesoplenty.api.item.BOPItems.ambrosia;
import static biomesoplenty.api.item.BOPItems.amethyst_axe;
import static biomesoplenty.api.item.BOPItems.amethyst_boots;
import static biomesoplenty.api.item.BOPItems.amethyst_chestplate;
import static biomesoplenty.api.item.BOPItems.amethyst_helmet;
import static biomesoplenty.api.item.BOPItems.amethyst_hoe;
import static biomesoplenty.api.item.BOPItems.amethyst_leggings;
import static biomesoplenty.api.item.BOPItems.amethyst_pickaxe;
import static biomesoplenty.api.item.BOPItems.amethyst_scythe;
import static biomesoplenty.api.item.BOPItems.amethyst_shovel;
import static biomesoplenty.api.item.BOPItems.amethyst_sword;
import static biomesoplenty.api.item.BOPItems.ash;
import static biomesoplenty.api.item.BOPItems.berries;
import static biomesoplenty.api.item.BOPItems.biome_essence;
import static biomesoplenty.api.item.BOPItems.biome_finder;
import static biomesoplenty.api.item.BOPItems.black_dye;
import static biomesoplenty.api.item.BOPItems.blue_dye;
import static biomesoplenty.api.item.BOPItems.brown_dye;
import static biomesoplenty.api.item.BOPItems.crystal_shard;
import static biomesoplenty.api.item.BOPItems.dart;
import static biomesoplenty.api.item.BOPItems.dart_blower;
import static biomesoplenty.api.item.BOPItems.diamond_scythe;
import static biomesoplenty.api.item.BOPItems.dull_flower_band;
import static biomesoplenty.api.item.BOPItems.earth;
import static biomesoplenty.api.item.BOPItems.enderporter;
import static biomesoplenty.api.item.BOPItems.exotic_flower_band;
import static biomesoplenty.api.item.BOPItems.filled_honeycomb;
import static biomesoplenty.api.item.BOPItems.flax_string;
import static biomesoplenty.api.item.BOPItems.fleshchunk;
import static biomesoplenty.api.item.BOPItems.flippers;
import static biomesoplenty.api.item.BOPItems.flower_basket;
import static biomesoplenty.api.item.BOPItems.gem;
import static biomesoplenty.api.item.BOPItems.gold_scythe;
import static biomesoplenty.api.item.BOPItems.green_dye;
import static biomesoplenty.api.item.BOPItems.honeycomb;
import static biomesoplenty.api.item.BOPItems.ichor;
import static biomesoplenty.api.item.BOPItems.iron_scythe;
import static biomesoplenty.api.item.BOPItems.jar_empty;
import static biomesoplenty.api.item.BOPItems.jar_filled;
import static biomesoplenty.api.item.BOPItems.lush_flower_band;
import static biomesoplenty.api.item.BOPItems.mud_axe;
import static biomesoplenty.api.item.BOPItems.mud_boots;
import static biomesoplenty.api.item.BOPItems.mud_brick;
import static biomesoplenty.api.item.BOPItems.mud_chestplate;
import static biomesoplenty.api.item.BOPItems.mud_helmet;
import static biomesoplenty.api.item.BOPItems.mud_hoe;
import static biomesoplenty.api.item.BOPItems.mud_leggings;
import static biomesoplenty.api.item.BOPItems.mud_pickaxe;
import static biomesoplenty.api.item.BOPItems.mud_scythe;
import static biomesoplenty.api.item.BOPItems.mud_shovel;
import static biomesoplenty.api.item.BOPItems.mud_sword;
import static biomesoplenty.api.item.BOPItems.mudball;
import static biomesoplenty.api.item.BOPItems.peach;
import static biomesoplenty.api.item.BOPItems.pear;
import static biomesoplenty.api.item.BOPItems.persimmon;
import static biomesoplenty.api.item.BOPItems.pinecone;
import static biomesoplenty.api.item.BOPItems.pixie_dust;
import static biomesoplenty.api.item.BOPItems.plain_flower_band;
import static biomesoplenty.api.item.BOPItems.record_corruption;
import static biomesoplenty.api.item.BOPItems.record_wanderer;
import static biomesoplenty.api.item.BOPItems.ricebowl;
import static biomesoplenty.api.item.BOPItems.saladfruit;
import static biomesoplenty.api.item.BOPItems.saladshroom;
import static biomesoplenty.api.item.BOPItems.saladveggie;
import static biomesoplenty.api.item.BOPItems.shroompowder;
import static biomesoplenty.api.item.BOPItems.soul;
import static biomesoplenty.api.item.BOPItems.spawn_egg;
import static biomesoplenty.api.item.BOPItems.stone_scythe;
import static biomesoplenty.api.item.BOPItems.terrestrial_artifact;
import static biomesoplenty.api.item.BOPItems.turnip;
import static biomesoplenty.api.item.BOPItems.turnip_seeds;
import static biomesoplenty.api.item.BOPItems.wading_boots;
import static biomesoplenty.api.item.BOPItems.white_dye;
import static biomesoplenty.api.item.BOPItems.wood_scythe;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.item.ItemAmbrosia;
import biomesoplenty.common.item.ItemBOPFood;
import biomesoplenty.common.item.ItemBOPRecord;
import biomesoplenty.common.item.ItemBOPScythe;
import biomesoplenty.common.item.ItemBOPSpawnEgg;
import biomesoplenty.common.item.ItemBiomeEssence;
import biomesoplenty.common.item.ItemBiomeFinder;
import biomesoplenty.common.item.ItemDart;
import biomesoplenty.common.item.ItemDartBlower;
import biomesoplenty.common.item.ItemEnderporter;
import biomesoplenty.common.item.ItemFlippers;
import biomesoplenty.common.item.ItemFlowerBand;
import biomesoplenty.common.item.ItemFlowerBasket;
import biomesoplenty.common.item.ItemGem;
import biomesoplenty.common.item.ItemJarEmpty;
import biomesoplenty.common.item.ItemJarFilled;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.item.ItemWadingBoots;
import biomesoplenty.common.util.BOPReflectionHelper;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ModItems
{    
    public static void init()
    {
        registerItems();
    }
    
    public static void registerItems()
    {
        // food
    	berries =           registerItem(new ItemBOPFood(1, 0.1F, 8), "berries"); 
    	pear =              registerItem(new ItemFood(5, 0.3F, false), "pear");
    	peach =             registerItem(new ItemFood(5, 0.2F, false), "peach");
        persimmon =         registerItem(new ItemFood(5, 0.2F, false), "persimmon");
        turnip_seeds = registerItem(new ItemSeeds(BOPBlocks.turnip_block, Blocks.farmland), "turnip_seeds");
        turnip =            registerItem(new ItemFood(3, 0.4F, false), "turnip");
        saladfruit =        registerItem(new ItemSoup(6), "saladfruit");
        ((ItemFood)saladfruit).setPotionEffect(new PotionEffect(MobEffects.digSpeed, 775, 1), 0.05F);
        saladveggie =       registerItem(new ItemSoup(6), "saladveggie");
        ((ItemFood)saladveggie).setPotionEffect(new PotionEffect(MobEffects.nightVision, 1100, 1), 0.05F); // TODO: Is this the right potion effect for veggie salad?
        saladshroom =       registerItem(new ItemSoup(6), "saladshroom");
        ((ItemFood)saladshroom).setPotionEffect(new PotionEffect(MobEffects.jump, 550, 1), 0.05F);
        ricebowl =          registerItem(new ItemSoup(2), "ricebowl");
        honeycomb = registerItem(new Item(), "honeycomb");
        filled_honeycomb =  registerItem(new ItemBOPFood(3, 0.4F, 16), "filled_honeycomb");
        pinecone = registerItem(new Item(), "pinecone");
        shroompowder =      registerItem(new ItemFood(1, 0.1F, false), "shroompowder");
        ((ItemFood)shroompowder).setAlwaysEdible();
        ((ItemFood)shroompowder).setPotionEffect(new PotionEffect(MobEffects.confusion, 225, 0), 0.6F);
        ambrosia =          registerItem(new ItemAmbrosia(), "ambrosia");
        
        earth = registerItem(new Item(), "earth");
        earth.setCreativeTab(null);
        flax_string = registerItem(new Item(), "flax_string");
        mudball = registerItem(new ItemMudball(), "mudball");
        mud_brick = registerItem(new Item(), "mud_brick");
        ash = registerItem(new Item(), "ash");
        fleshchunk = registerItem(new Item(), "fleshchunk");
        gem = registerItem(new ItemGem(), "gem");
        terrestrial_artifact = registerItem(new Item(), "terrestrial_artifact");
        terrestrial_artifact.setMaxStackSize(1);
        crystal_shard = registerItem(new Item(), "crystal_shard");
        biome_essence = registerItem(new ItemBiomeEssence(), "biome_essence");
        pixie_dust = registerItem(new Item(), "pixie_dust");
        ichor = registerItem(new Item(), "ichor");
        soul = registerItem(new Item(), "soul");
        soul.setMaxStackSize(1);
        // TODO: move dyes to their own class?
        blue_dye = registerItem(new Item(), "blue_dye");
        brown_dye = registerItem(new Item(), "brown_dye");
        green_dye = registerItem(new Item(), "green_dye");
        white_dye = registerItem(new Item(), "white_dye");
        black_dye = registerItem(new Item(), "black_dye");
    
        // armor
        
        // addArmorMaterial arguments:
        // (String name, String textureName, int durability, int[] reductionAmounts, int enchantability)
        // Vanilla armor material values for comparison:
        // LEATHER("leather", 5, new int[]{1, 3, 2, 1}, 15),
        // CHAIN("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
        // IRON("iron", 15, new int[]{2, 6, 5, 2}, 9),
        // GOLD("gold", 7, new int[]{2, 5, 3, 1}, 25),
        // DIAMOND("diamond", 33, new int[]{3, 8, 6, 3}, 10);
        
        // TODO: do we really want durability of -1 for these unprotective armor items?  does that mean it lasts forever?
        wading_boots_material = addArmorMaterial("WADING_BOOTS", "biomesoplenty:wading_boots", -1, new int[]{0,0,0,0}, 0, SoundEvents.item_armor_equip_generic);
        flippers_material = addArmorMaterial("FLIPPERS", "biomesoplenty:flippers", -1, new int[]{0,0,0,0}, 0, SoundEvents.item_armor_equip_generic);
        plain_flower_band_material = addArmorMaterial("PLAIN_FLOWER_BAND", "biomesoplenty:plain_flower_band", -1, new int[]{0,0,0,0}, 0, SoundEvents.item_armor_equip_generic);
        lush_flower_band_material = addArmorMaterial("LUSH_FLOWER_BAND", "biomesoplenty:lush_flower_band", -1, new int[]{0,0,0,0}, 0, SoundEvents.item_armor_equip_generic);
        exotic_flower_band_material = addArmorMaterial("EXOTIC_FLOWER_BAND", "biomesoplenty:exotic_flower_band", -1, new int[]{0,0,0,0}, 0, SoundEvents.item_armor_equip_generic);
        dull_flower_band_material = addArmorMaterial("DULL_FLOWER_BAND", "biomesoplenty:dull_flower_band", -1, new int[]{0,0,0,0}, 0, SoundEvents.item_armor_equip_generic);
        
        mud_armor_material = addArmorMaterial("MUD", "biomesoplenty:mud_armor", 2, new int[]{1,1,1,1}, 5, SoundEvents.item_armor_equip_generic);
        mud_armor_material.customCraftingMaterial = mudball;
        amethyst_armor_material = addArmorMaterial("AMETHYST", "biomesoplenty:amethyst_armor", 40, new int[]{3,8,8,3}, 20, SoundEvents.item_armor_equip_generic);
        
        wading_boots = registerItem(new ItemWadingBoots(wading_boots_material, 0), "wading_boots");
        flippers = registerItem(new ItemFlippers(flippers_material, 0), "flippers");
        plain_flower_band = registerItem(new ItemFlowerBand(plain_flower_band_material, 0), "plain_flower_band");
        lush_flower_band = registerItem(new ItemFlowerBand(lush_flower_band_material, 0), "lush_flower_band");
        exotic_flower_band = registerItem(new ItemFlowerBand(exotic_flower_band_material, 0), "exotic_flower_band");
        dull_flower_band = registerItem(new ItemFlowerBand(dull_flower_band_material, 0), "dull_flower_band");
        
        mud_helmet = registerItem(new ItemArmor(mud_armor_material, 0, EntityEquipmentSlot.HEAD), "mud_helmet");
        mud_chestplate = registerItem(new ItemArmor(mud_armor_material, 0, EntityEquipmentSlot.CHEST), "mud_chestplate");
        mud_leggings = registerItem(new ItemArmor(mud_armor_material, 0, EntityEquipmentSlot.LEGS), "mud_leggings");
        mud_boots = registerItem(new ItemArmor(mud_armor_material, 0, EntityEquipmentSlot.FEET), "mud_boots");
        amethyst_helmet = registerItem(new ItemArmor(amethyst_armor_material, 0, EntityEquipmentSlot.HEAD), "amethyst_helmet");
        amethyst_chestplate = registerItem(new ItemArmor(amethyst_armor_material, 0, EntityEquipmentSlot.CHEST), "amethyst_chestplate");
        amethyst_leggings = registerItem(new ItemArmor(amethyst_armor_material, 0, EntityEquipmentSlot.LEGS), "amethyst_leggings");
        amethyst_boots = registerItem(new ItemArmor(amethyst_armor_material, 0, EntityEquipmentSlot.FEET), "amethyst_boots");
        
        // tools

        // addToolMaterial arguments:
        // (String name, int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability)
        // Vanilla tool material values for comparison:
        // WOOD(0, 59, 2.0F, 0.0F, 15),
        // STONE(1, 131, 4.0F, 1.0F, 5),
        // IRON(2, 250, 6.0F, 2.0F, 14),
        // EMERALD(3, 1561, 8.0F, 3.0F, 10),
        // GOLD(0, 32, 12.0F, 0.0F, 22);
        mud_tool_material = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0.0F, 1);
        mud_tool_material.setRepairItem(new ItemStack(mudball));
        amethyst_tool_material = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5.0F, 16);
        // no repair item for amethyst tool - they can't be repaired
        setAxeDamageAndSpeed(mud_tool_material, 3.0F, -3.3F);
        setAxeDamageAndSpeed(amethyst_tool_material, 8.0F, -2.8F);

        // ItemAxe and ItemPickaxe have protected constructors - use reflection to construct
        mud_axe = registerItem(BOPReflectionHelper.construct(ItemAxe.class, mud_tool_material), "mud_axe");
        mud_pickaxe = registerItem(BOPReflectionHelper.construct(ItemPickaxe.class, mud_tool_material), "mud_pickaxe");
        amethyst_axe = registerItem(BOPReflectionHelper.construct(ItemAxe.class, amethyst_tool_material), "amethyst_axe");
        amethyst_pickaxe = registerItem(BOPReflectionHelper.construct(ItemPickaxe.class, amethyst_tool_material), "amethyst_pickaxe");
                
        // the other tools have public constructors, so we create instances in the normal way
        mud_hoe = registerItem(new ItemHoe(mud_tool_material), "mud_hoe");
        mud_shovel = registerItem(new ItemSpade(mud_tool_material), "mud_shovel");
        mud_sword = registerItem(new ItemSword(mud_tool_material), "mud_sword");
        amethyst_hoe = registerItem(new ItemHoe(amethyst_tool_material), "amethyst_hoe");
        amethyst_shovel = registerItem(new ItemSpade(amethyst_tool_material), "amethyst_shovel");
        amethyst_sword = registerItem(new ItemSword(amethyst_tool_material), "amethyst_sword");
        
        mud_scythe = registerItem(new ItemBOPScythe(mud_tool_material), "mud_scythe");
        wood_scythe = registerItem(new ItemBOPScythe(ToolMaterial.WOOD), "wood_scythe");
        stone_scythe = registerItem(new ItemBOPScythe(ToolMaterial.STONE), "stone_scythe");
        iron_scythe = registerItem(new ItemBOPScythe(ToolMaterial.IRON), "iron_scythe");
        gold_scythe = registerItem(new ItemBOPScythe(ToolMaterial.GOLD), "gold_scythe");
        diamond_scythe = registerItem(new ItemBOPScythe(ToolMaterial.DIAMOND), "diamond_scythe");
        amethyst_scythe = registerItem(new ItemBOPScythe(amethyst_tool_material), "amethyst_scythe");
        
        dart = registerItem(new ItemDart(), "dart");
        dart_blower = registerItem(new ItemDartBlower(), "dart_blower");
        
        jar_empty = registerItem(new ItemJarEmpty(), "jar_empty");
        jar_filled = registerItem(new ItemJarFilled(), "jar_filled");
        flower_basket = registerItem(new ItemFlowerBasket(), "flower_basket");
        biome_finder = registerItem(new ItemBiomeFinder(), "biome_finder");
        enderporter = registerItem(new ItemEnderporter(), "enderporter");
        
        record_wanderer = registerItem(new ItemBOPRecord("wanderer", BOPSounds.records_wanderer), "record_wanderer");
        record_corruption = registerItem(new ItemBOPRecord("corruption", BOPSounds.records_corruption), "record_corruption");
        
        // TODO: use Forge for eggs now?  https://github.com/MinecraftForge/MinecraftForge/commit/c158af902f2a689f612fd20427b5a1590fc2f1ba
        spawn_egg = registerItem(new ItemBOPSpawnEgg(), "spawn_egg");
        
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
        GameRegistry.registerItem(item,name);
        BOPCommand.itemCount++;
        BiomesOPlenty.proxy.registerItemSided(item);
        
        return item;   
    }

    private static ItemArmor.ArmorMaterial addArmorMaterial(String name, String textureName, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip)
    {
        return EnumHelper.addArmorMaterial(name, textureName, durability, reductionAmounts, enchantability, soundOnEquip);
    }
    
    private static void setAxeDamageAndSpeed(ToolMaterial material, float damage, float speed)
    {
        int index = material.ordinal(); 
        
        //Expand the arrays if necessary
        if (ItemAxe.ATTACK_DAMAGES.length - 1 < index)
        {
            float[] attackDamages = new float[index + 1];
            System.arraycopy(ItemAxe.ATTACK_DAMAGES, 0, attackDamages, 0, ItemAxe.ATTACK_DAMAGES.length);
            ItemAxe.ATTACK_DAMAGES = attackDamages;
        }  
        
        if (ItemAxe.ATTACK_SPEEDS.length - 1 < index)
        {
            float[] attackSpeeds = new float[index + 1];
            System.arraycopy(ItemAxe.ATTACK_SPEEDS, 0, attackSpeeds, 0, ItemAxe.ATTACK_SPEEDS.length);
            ItemAxe.ATTACK_SPEEDS = attackSpeeds;
        }
        
        //Update the values associated with this material
        ItemAxe.ATTACK_DAMAGES[index] = damage;
        ItemAxe.ATTACK_SPEEDS[index] = damage;
    }
    
}